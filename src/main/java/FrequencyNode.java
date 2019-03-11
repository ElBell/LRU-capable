import java.util.LinkedHashSet;
import java.util.Set;

class FrequencyNode<K> {
    private Integer value;
    private Set<K> items;
    private FrequencyNode previous;
    private FrequencyNode next;

    FrequencyNode() {
        this.setValue(0);
        this.setPrevious(this);
        this.setNext(this);
    }

    FrequencyNode(Integer value, FrequencyNode previous, FrequencyNode next) {
        this.setValue(value);
        this.setItems(new LinkedHashSet<>());
        this.setPrevious(previous);
        this.setNext(next);
        previous.setNext(this);
        next.setPrevious(this);
    }

    public int size() {
        return getItems().size();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Set<K> getItems() {
        return items;
    }

    public void setItems(Set<K> items) {
        this.items = items;
    }

    public FrequencyNode getPrevious() {
        return previous;
    }

    public void setPrevious(FrequencyNode previous) {
        this.previous = previous;
    }

    public FrequencyNode getNext() {
        return next;
    }
    public void setNext(FrequencyNode next) {
        this.next = next;
    }
}
