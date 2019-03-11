
import java.util.*;

class LFUCache<K, V> {
    private HashMap<K, LFUItem> byKey;
    private FrequencyNode<K> frequencyHead;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        byKey = new HashMap<>();
        frequencyHead = new FrequencyNode<>();
    }

    private void deleteNode(FrequencyNode<K> frequencyNode) {
        frequencyNode.getPrevious().setNext(frequencyNode.getNext());
        frequencyNode.getNext().setPrevious(frequencyNode.getPrevious());
    }

    public V access(K key) {
        LFUItem<V> temp = byKey.get(key);
        if (temp == null) { throw new NoSuchElementException("No such key"); }
        FrequencyNode<K> freq = temp.getParent();
        FrequencyNode<K> nextFreq = freq.getNext();

        if (nextFreq == frequencyHead || nextFreq.getValue() != freq.getValue() + 1) {
            nextFreq = new FrequencyNode<K>(freq.getValue() + 1, freq, nextFreq);
        }
        nextFreq.getItems().add(key);
        temp.setParent(nextFreq);

        freq.getItems().remove(key);
        if (freq.getItems().size() == 0) { deleteNode(freq); }
        return temp.getData();
    }

    public V insert(K key, V value) {
        if (byKey.containsKey(key)) { throw new IllegalArgumentException("The value is already in the list."); }
        V removed = sizeCheck();
        FrequencyNode<K> freq = frequencyHead.getNext();
        if (freq.getValue() != 1) {
            freq = new FrequencyNode<>(1, frequencyHead, freq);
        }
        freq.getItems().add(key);
        byKey.put(key, new LFUItem(value, freq));
        return removed;
    }

    private V sizeCheck() {
        if (byKey.size() == capacity) {
            return removeLFU();
        }
        return null;
    }

    private V removeLFU() {
        FrequencyNode<K> freq = frequencyHead.getNext();
        K toBeRemoved = freq.getItems().iterator().next();
        V value = (V) byKey.get(toBeRemoved).getData();
        byKey.remove(toBeRemoved);
        freq.getItems().remove(toBeRemoved);
        if (freq.getItems().size() == 0) {
            deleteNode(freq);
        }
        return value;
    }

    public V getLFUItem() {
        if (byKey.size() == 0) { throw new NoSuchElementException("The set is empty"); }
        return (V) byKey.get(frequencyHead.getNext().getItems().iterator().next()).getData();
    }

    public FrequencyNode getHead() {
        return frequencyHead;
    }
}

