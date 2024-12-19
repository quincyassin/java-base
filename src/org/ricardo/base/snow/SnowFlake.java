package org.ricardo.base.snow;

public class SnowFlake {

    /**
     * 起始时间
     */
    private final static long START_TIME = System.currentTimeMillis();

    /**
     * 每一部分占用位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用位数

    private final static long MACHINE_BIT = 5; //机器标识占用位数

    private final static long DATACENTER_BIT = 5; //数据中心占用位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);

    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);

    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;

    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long dataCenterId; //数据中心
    private long machineId; //机器标识
    private long sequence = 0L; //序列号
    private long lastTimestamp = -1L; //上次时间戳

    public SnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException();
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException();
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currTimestamp = System.currentTimeMillis();

        //为了避免产生重复的id 因为当前发生了时钟回拨
        if (currTimestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards. Refusing to generate id");
        }
        if (currTimestamp == lastTimestamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currTimestamp = getNextMill();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = currTimestamp;

        return (currTimestamp - START_TIME) << TIMESTMP_LEFT //时间戳
                | dataCenterId << DATACENTER_LEFT //数据中心
                | machineId << MACHINE_LEFT //机器标识
                | sequence; //序列号
    }

    private long getNextMill() {
        long currTimestamp = System.currentTimeMillis();
        while (currTimestamp <= lastTimestamp) {
            currTimestamp = System.currentTimeMillis();
        }
        return currTimestamp;
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(snowFlake.nextId());
            }).start();
        }
    }
}
