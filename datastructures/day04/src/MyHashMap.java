import java.lang.reflect.Array;
import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        int code = key.hashCode();
        int bucketIndex = code % buckets.length;
        // hint: use key.hashCode() to calculate the key's hashCode using its built in hash function
        // then use % to choose which bucket to return.
        return buckets[bucketIndex];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < chooseBucket(key).size(); i++) {
            if (chooseBucket(key).get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < buckets.length; i++) { // Get bucket
            if (!buckets[i].isEmpty()) {
                for (int bucketIndex = 0; bucketIndex < buckets[i].size(); bucketIndex++) { // Get value from bucket
                    if (buckets[i].get(bucketIndex).getValue() == value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int code = key.hashCode();
        int bucketIndex = code % buckets.length;

        for (int i = 0; i < buckets[bucketIndex].size(); i++) {
            if (buckets[bucketIndex].get(i).getKey().equals(key)) {
                return buckets[bucketIndex].get(i).getValue();
            }
        }
        return null; // Could not find in map
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        if (!containsKey(key)) {
            chooseBucket(key).add(new Entry(key,value));
            size++;

            if (((double) size / (double) buckets.length) >= ALPHA) { // Resize
                rehash(GROWTH_FACTOR);
            }

            return null;
        } else {
            V prevval = get(key);
            chooseBucket(key).get(0).setValue(value);
            return prevval;
        }
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        if (containsKey(key)) {
            V val = get(key);
            for (int i = 0; i < chooseBucket(key).size(); i++) {
                if (chooseBucket(key).get(i).getKey().equals(key)) { // Delete the entry
                    chooseBucket(key).remove(i);
                    size--;
                    break;
                }
            }

            if (((double) size / (double) buckets.length) <= BETA) { // Resize
                rehash(SHRINK_FACTOR);

            }

            return val;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        int newLength = (int) java.lang.Math.floor(growthFactor * buckets.length);
        ArrayList<Entry> mymap = new ArrayList<>();

        for (int bucketIndex = 0; bucketIndex < buckets.length; bucketIndex++) {
            for (int entryIndex = 0; entryIndex < buckets[bucketIndex].size(); entryIndex++) {
                Entry entry = buckets[bucketIndex].get(entryIndex);
                mymap.add(entry);
            }
        }

        size = 0;
        initBuckets(newLength);

        for (int entryIndex = 0; entryIndex < mymap.size(); entryIndex++) {
            if (mymap.get(entryIndex) != null) {
                put(mymap.get(entryIndex).getKey(), mymap.get(entryIndex).getValue());
            }
        }
    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
