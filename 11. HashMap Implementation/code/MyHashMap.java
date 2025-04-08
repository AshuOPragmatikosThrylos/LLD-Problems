import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashMap<K, V> {
    
    class HMNode<K, V> {
        K key;
        V value;

        HMNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    int size;
    LinkedList<HMNode<K, V>>[] buckets;

    MyHashMap(int capacity) {
        this.size = 0;
        initbuckets(capacity);
    }

    void initbuckets(int capacity) {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    void put(K key, V value) {
        int bucketIndex = hashFunction(key);
        int dataIndex = getIndexWithinBucket(bucketIndex, key);

        if (dataIndex == -1) {
            HMNode<K, V> newNode = new HMNode<>(key, value);
            buckets[bucketIndex].addLast(newNode);
            size++;
        } else {
            buckets[bucketIndex].get(dataIndex).value = value;
        }

        double loadFactor = (size * 1.0) / buckets.length;
        if (loadFactor > 0.75) {
            rehash();
        }
    }

    V get(K key) {
        int bucketIndex = hashFunction(key);
        int dataIndex = getIndexWithinBucket(bucketIndex, key);

        if (dataIndex == -1) {
            return null;
        } else {
            return buckets[bucketIndex].get(dataIndex).value;
        }
    }

    boolean containsKey(K key) {
        int bucketIndex = hashFunction(key);
        int dataIndex = getIndexWithinBucket(bucketIndex, key);
        return dataIndex != -1;
    }

    V remove(K key) {
        int bucketIndex = hashFunction(key);
        int dataIndex = getIndexWithinBucket(bucketIndex, key);

        if (dataIndex == -1) {
            return null;
        } else {
            HMNode<K, V> removedNode = buckets[bucketIndex].remove(dataIndex);
            size--;
            return removedNode.value;
        }
    }

    private void rehash() {
        LinkedList<HMNode<K, V>>[] oldBuckets = buckets;
        initbuckets(oldBuckets.length * 2);
        size = 0;

        for (LinkedList<HMNode<K, V>> bucket : oldBuckets) {
            for (HMNode<K, V> node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    int getIndexWithinBucket(int bucketIndex, K key) {
        LinkedList<HMNode<K, V>> bucket = buckets[bucketIndex];
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    int size() {
        return size;
    }

    ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (LinkedList<HMNode<K, V>> bucket : buckets) {
            for (HMNode<K, V> node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    int hashFunction(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }
}