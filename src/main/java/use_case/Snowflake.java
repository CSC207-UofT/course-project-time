package main.java.use_case;

/**
 * Revised from Twitter's snowflake algorithm for generating unique IDs
 * Github: https://github.com/twitter-archive/snowflake
 */
public class Snowflake {
    private long workerId;
    private long datacenterId;
    private long sequence;

    private final long workerIdBits = 5L;  // 5 bits for worker id
    private final long datacenterIdBits = 5L;  // 5 bits for data center id
    private final long sequenceBits = 12L;  // 12 bits for serial number

    private final long maxWorkerId = ~(-1L << workerIdBits);
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);
    private final long maxSequenceBits = ~(-1L << sequenceBits);

    // bit shifting for each id
    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampMilliShift = sequenceBits + workerIdBits + datacenterIdBits;

    private final long initialTimeStampMilli = 1288834974657L;
    private long lastTimestampMilli = -1L;

    public Snowflake(long workerId, long datacenterId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker ID cannot be greater than %d or less than 0", maxWorkerId));
        } else if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter ID cannot be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestampMilli() {
        return System.currentTimeMillis();
    }

    /**
     * generates the next ID
     * @return the next ID
     */
    public synchronized long nextId() {
        long timestampMilli = getTimestampMilli();

        // check if clock has not moved backwards
        if (timestampMilli < lastTimestampMilli) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestampMilli - timestampMilli));
        }

        if (lastTimestampMilli == timestampMilli) {
            // maxSequenceBits, in binary, is 01111...1
            // if sequence <= maxSequenceBits, then sequence is increased by 1
            // if sequence == maxSequenceBits + 1, then it becomes 10000...0 and the
            // bitwise AND operation makes it 0
            sequence = (sequence + 1) & maxSequenceBits;
            // if <sequence> has been looped to 0 after too many increments, wail until the next millisecond
            // because not waiting results in the generation of non-unique ids
            if (sequence == 0) {
                waitUntilNextMillisecond(lastTimestampMilli);
            }
        } else {
            sequence = 0;
        }

        lastTimestampMilli = timestampMilli;

        return ((timestampMilli - initialTimeStampMilli) << timestampMilliShift) | (datacenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    private long waitUntilNextMillisecond(long lastTimestampMilli) {
        long timestampMilli = getTimestampMilli();
        while (timestampMilli <= lastTimestampMilli) {
            timestampMilli = getTimestampMilli();
        }
        return timestampMilli;
    }
}