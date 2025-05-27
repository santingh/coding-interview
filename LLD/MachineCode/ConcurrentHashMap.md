```java
  import java.util.concurrent.locks.ReentrantLock;

public class CustomConcurrentHashMap<K, V> {
    private final Segment<K, V>[] segments;
    private final int segmentCount;
    private final int bucketSize;
    private final double loadFactor;

    @SuppressWarnings("unchecked")
    public CustomConcurrentHashMap(int segmentCount, int bucketSize, double loadFactor) {
        this.segmentCount = segmentCount;
        this.bucketSize = bucketSize;
        this.loadFactor = loadFactor;

        // raw array creation + unchecked cast
        this.segments = (Segment<K, V>[]) new Segment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            segments[i] = new Segment<>(bucketSize, (int)(bucketSize * loadFactor));
        }
    }

    private int hash(Object key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (segmentCount - 1);
    }

    public V get(K key) {
        int idx = hash(key);
        return segments[idx].get(key);
    }

    public V put(K key, V value) {
        int idx = hash(key);
        return segments[idx].put(key, value);
    }

    public V remove(K key) {
        int idx = hash(key);
        return segments[idx].remove(key);
    }
}

class Segment<K, V> {
    private final ReentrantLock lock = new ReentrantLock();
    private final DataNode<K, V>[] buckets;
    private final int threshold;

    @SuppressWarnings("unchecked")
    public Segment(int bucketSize, int threshold) {
        this.buckets = (DataNode<K, V>[]) new DataNode[bucketSize];
        this.threshold = threshold;
    }

    private int bucketIndex(Object key) {
        int h = key.hashCode();
        return (h & 0x7fffffff) % buckets.length;
    }

    public V get(K key) {
        int idx = bucketIndex(key);
        DataNode<K, V> node = buckets[idx];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

    public V put(K key, V value) {
        lock.lock();
        try {
            int idx = bucketIndex(key);
            DataNode<K, V> node = buckets[idx];
            // update existing
            for (; node != null; node = node.next) {
                if (node.key.equals(key)) {
                    V old = node.value;
                    node.value = value;
                    return old;
                }
            }
            // insert at head
            buckets[idx] = new DataNode<>(key, value, buckets[idx]);
            // (Optional) check threshold → resize logic
            return null;
        } finally {
            lock.unlock();
        }
    }

    public V remove(K key) {
        lock.lock();
        try {
            int idx = bucketIndex(key);
            DataNode<K, V> prev = null;
            DataNode<K, V> node = buckets[idx];
            while (node != null) {
                if (node.key.equals(key)) {
                    if (prev == null) {
                        buckets[idx] = node.next;
                    } else {
                        prev.next = node.next;
                    }
                    return node.value;
                }
                prev = node;
                node = node.next;
            }
            return null;
        } finally {
            lock.unlock();
        }
    }
}

class DataNode<K, V> {
    final K key;
    V value;
    DataNode<K, V> next;

    DataNode(K key, V value, DataNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

```
