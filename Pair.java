public class Pair<K, V> {
    private K key;
    private V value;
    private boolean removed;//true if a pair has been deleted from the table

    //constructor
    public Pair(K k, V v) {
	key = k;
	value = v;
	removed = false;
    }

    //getter for the key
    public K key() {
	return key;
    }

    //getter for the value
    public V value() {
	return value;
    }

    //setter for the value
    public void setVal(V val) {
	value = val;
    }

    //sets removed to true
    public void remove() {
	removed = true;
    }

    //returns true if the pair has been removed
    public boolean removed() {
	return removed;
    }

    //toString method for printing the key-value pair
    public String toString() {
	return "(" + key.toString() + ", " + value.toString() + ")";
    }

    //returns true if the keys are the same
    public boolean equals(Pair p) {
	return this.key().equals(p.key());
    }
}
    
