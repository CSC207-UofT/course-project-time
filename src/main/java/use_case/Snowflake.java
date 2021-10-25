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

    // maximum values for the IDs
    private final long maxWorkerId = ~(-1L << workerIdBits);
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);
    private final long maxSequenceBits = ~(-1L << sequenceBits);

    // bit shifting for each id
    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    private final long initialTimeStamp = 1288834974657L;
    private long lastTimestamp = -1L;

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

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * generates the next ID
     * @return the next ID
     */
    public synchronized long nextId() {
        long timestamp = getTimestamp();

        // check if clock has not moved backwards
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // check if last timestamp equals the current timestamp
        if (lastTimestamp == timestamp) {
            // if so, increase <sequence>
            sequence = (sequence + 1) & maxSequenceBits;
            // if <sequence> has been looped to 0 after too many increments, wail until the next millisecond
            if (sequence == 0) {
                waitUntilNextMillisecond(lastTimestamp);
            }
        } else {
            // if not, reset sequence to 0
            sequence = 0;
        }

        // update the previous timestamp
        lastTimestamp = timestamp;

        return ((timestamp - initialTimeStamp) << timestampShift) | (datacenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    private long waitUntilNextMillisecond(long lastTimestamp) {
        long timestamp = getTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getTimestamp();
        }
        return timestamp;
    }
}
