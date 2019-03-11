
import java.util.*;

public class LFUCache<K, V> {
    private HashMap<K, LFUItem> byKey;//cache K and V
    private FrequencyNode frequencyHead;//K and counters
    private int capacity;
    //private int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        byKey = new HashMap<>();
        frequencyHead = new FrequencyNode();
    }

    private void deleteNode(FrequencyNode frequencyNode) {
        frequencyNode.previous.next = frequencyNode.next;
        frequencyNode.next.previous = frequencyNode.previous;
    }

    public V access(K key) {
        LFUItem temp = byKey.get(key);
        if (temp == null) {
            throw new NoSuchElementException("No such key");
        }
        FrequencyNode freq = temp.parent;
        FrequencyNode nextFreq = freq.next;

        if (nextFreq == frequencyHead
                || nextFreq.value != freq.value + 1) {
            nextFreq = new FrequencyNode(freq.value + 1, freq, nextFreq);
        }
        nextFreq.items.add(key);
        temp.parent = nextFreq;

        freq.items.remove(key);
        if (freq.items.size() == 0) {
            deleteNode(freq);
        }
        return temp.data;
    }

    public V insert(K key, V value) {
        V removed = null;
        if (byKey.containsKey(key)) {
            throw new IllegalArgumentException("The value is already in the list.");
        }

        if (byKey.size() == capacity) {
            removed = removeLFU();
        }

        FrequencyNode freq = frequencyHead.next;
        if (freq.value != 1) {
            freq = new FrequencyNode(1, frequencyHead, freq);
        }

        freq.items.add(key);
        byKey.put(key, new LFUItem(value, freq));

        return removed;
    }

    private V removeLFU() {
        FrequencyNode freq = frequencyHead.next;
        K toBeRemoved = freq.items.iterator().next();
        V value = byKey.get(toBeRemoved).data;
        byKey.remove(toBeRemoved);
        freq.items.remove(toBeRemoved);
        if (freq.items.size() == 0) {
            deleteNode(freq);
        }
        return value;
    }

    public V getLFUItem() {
        if (byKey.size() == 0) {
            throw new NullPointerException("The set is empty");
        }
        return byKey.get(frequencyHead.next.items.iterator().next()).data;
    }

    private class FrequencyNode {
        Integer value;
        Set<K> items;
        FrequencyNode previous;
        FrequencyNode next;

        FrequencyNode() {
            this.value = 0;
            this.items = new LinkedHashSet<>();
            this.previous = this;
            this.next = this;
        }

        FrequencyNode(Integer value, FrequencyNode previous, FrequencyNode next) {
            this.value = value;
            this.items = new HashSet<>();
            this.previous = previous;
            this.next = next;
            previous.next = this;
            next.previous = this;
        }
    }

    private class LFUItem {
        V data;
        FrequencyNode parent;

        LFUItem(V data, FrequencyNode parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}

