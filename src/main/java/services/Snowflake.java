package services;

/**
 * Revised from Twitter's snowflake algorithm for generating unique IDs
 * GitHub: https://github.com/twitter-archive/snowflake/blob/b3f6a3c6ca8e1b6847baa6ff42bf72201e2c2231/
 *         src/main/scala/com/twitter/service/snowflake/IdWorker.scala
 */
public class Snowflake {
    private final long workerId;
    private final long datacenterId;
    private long sequence;

    private final long workerIdBits = 5L;  // 5 bits for worker id
    private final long datacenterIdBits = 5L;  // 5 bits for data center id

    private long lastTimestampMilli = -1L;  // since we do not have a previous timestamp, initialize it to -1

    /**
     * Initialize the Snowflake class for unique ID generation
     * Since this algorithm can be used for large-scale server-based projects, we will just have
     * arbitrary choices for *workerId* and *datacenterId*
     * Also since we will only instantiate one of this class, *sequence* can be set to 0
     * @param workerId ID assigned to device
     * @param datacenterId ID assigned to the server used by the device
     * @param sequence The number of IDs generated in the current millisecond
     */
    public Snowflake(long workerId, long datacenterId, long sequence) {
        long maxWorkerId = ~(-1L << workerIdBits);
        long maxDatacenterId = ~(-1L << datacenterIdBits);
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

        // 12 bits for serial number
        long sequenceBits = 12L;
        if (lastTimestampMilli == timestampMilli) {
            // maxSequenceBits, in binary, is 01111...1
            // if sequence <= maxSequenceBits, then sequence is increased by 1
            // if sequence == maxSequenceBits + 1, then it becomes 10000...0 and the
            // bitwise AND operation makes it 0
            long maxSequenceBits = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & maxSequenceBits;
            // if <sequence> has been looped to 0 after too many increments, wail until the next millisecond
            // because not waiting results in the generation of non-unique ids
            if (sequence == 0) {
                timestampMilli = waitUntilNextMillisecond(lastTimestampMilli);
            }
        } else {
            sequence = 0;
        }

        lastTimestampMilli = timestampMilli;

        // bit shifting for each id
        long dataCenterIdShift = sequenceBits + workerIdBits;
        long timestampMilliShift = sequenceBits + workerIdBits + datacenterIdBits;
        // choice could be arbitrary
        long initialTimeStampMilli = 1288834974657L;
        return ((timestampMilli - initialTimeStampMilli) << timestampMilliShift) | (datacenterId << dataCenterIdShift) |
                (workerId << sequenceBits) | sequence;
    }

    private long waitUntilNextMillisecond(long lastTimestampMilli) {
        long timestampMilli = getTimestampMilli();
        while (timestampMilli <= lastTimestampMilli) {
            timestampMilli = getTimestampMilli();
        }
        return timestampMilli;
    }
}
