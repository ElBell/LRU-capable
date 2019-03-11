class LFUItem<V> {
    private V data;
    private FrequencyNode parent;

    LFUItem(V data, FrequencyNode parent) {
        this.setData(data);
        this.setParent(parent);
    }

    public V getData() {
        return data;
    }

    public FrequencyNode getParent() {
        return parent;
    }

    public void setData(V data) {
        this.data = data;
    }

    public void setParent(FrequencyNode parent) {
        this.parent = parent;
    }
}
