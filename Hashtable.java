public class Hashtable<K, V> {
    private Pair[] table;//the underlying array
    private int n;//the number of elements in the hashtable
    private int m;//the size of the hashtable
    private final int CAP;//the starting capacity of the hashtable
    private final double MAX_ALPHA = 0.5;//the upper bound for the load factor
    private final double MIN_ALPHA = 1.0/8.0;//the lower bound for the load factor
    
    /**
     *default table size is 11
     **/
    public Hashtable () {
        CAP = 11;
        n = 0;
        m = 11;
        table = new Pair[m];
    }

    /**
     *constructor for passing in starting capacity
     **/
    public Hashtable(int cap) {
        CAP = cap;
        n = 0;
        m = CAP;
        table = new Pair[m];
    }

    /**
     *return the value associated with key
     *return null if no such key exists in the table
     **/
    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % m;
        if (index < 0) { // Make sure hashing doesn't result in negative index
            index += m;
        }

        while (!(table[index % m] == null || 
        table[index % m].key().equals(key) && !table[index % m].removed())) {
                index ++;
        }
        if (table[index % m] == null) {
            return null;
        } else {
            return (V) table[index % m].value();
        }
    }

    /**
     *put the (key, value) pair into the table
     *if the key is already in the table, update the value;
     *resize if necessary
     **/
    public void put(K key, V value) { // error in hashtable.get() test for update
        int hash = key.hashCode();
        int index = hash % m;
        if (index < 0) { // make sure hashing doesn't result in negative index
            index += m;
        }

        while (!(table[index % m] == null || 
        table[index % m].key().equals(key) && !table[index % m].removed())) { // Iterate until null or if key is found and not removed
            index ++;
        }
        if (table[index % m] == null) { // Never put into a removed spot.
            table[index % m] = new Pair(key, value);
            n++;
            if ((double) n > m*MAX_ALPHA) { // Resize and rehash the array.
                Pair[] oldTable = table;
                this.table = new Pair[nextPrime(2*m)];
                m = table.length;
                n = 0;
                for (Pair element: oldTable) {
                    if (element != null && !element.removed()) {
                        this.put((K) element.key(), (V) element.value());
                    }
                }
            }
        } else {
            table[index % m].setVal(value);
        }
    }

    /**
     *Remove the (key, value) pair associated with key
     *from the table--remember that with linear probing,
     *you can't actually set the item to null--instead, use the
     *"remove" function available in the Pair class.
     *Also, resize the table if necessary.
     **/
    public V remove(K key) {
        int hash = key.hashCode();
        int index = hash % m;
        if (index < 0) { // Make sure hashing doesn't result in negative index
            index += m;
        }

        while (!(table[index % m] == null || 
        table[index % m].key().equals(key) && !table[index % m].removed())) { // Iterate until null or if key is found and not removed
            index ++;
        }
        if (table[index % m] == null) {
            return null;
        } else {
            V val = (V) table[index % m].value();
            table[index % m].remove();
            n--;
            if ((double) n < m*MIN_ALPHA && m/2 >= CAP) { // Resize and rehash the array.
                Pair[] oldTable = table;
                this.table = new Pair[nextPrime(m/2)];
                m = table.length;
                n = 0;
                for (Pair element: oldTable) {
                    if (element != null && !element.removed()) {
                        this.put((K) element.key(), (V) element.value());
                    }
                }
            }
            return val;
        }
    }

    /**
     *returns the number of elements in the table
     **/
    public int size() {
	    return n;
    }

    /**
     *This method is only used for testing. 
     *Do not change it or use it in your own submitted code!!!
     **/
    public Pair<K, V>[] getArray() {
	return table;
    }

    /**
     *This method can be used to get the next prime
     *p that is greater than or equal to num. Use this
     *when resizing your table.
     **/
    private int nextPrime(int num) {
	if(num <= 3) return 3;
	if(num <= 5) return 5;
	if(isPrime(num))
	    return num;
	int rem = num % 6;
	switch(rem) {
	case 0: num++; break;
	case 2: num+=3; break;
	case 3: num+=2; break;
	case 4: num++; break;
	}
	while(!isPrime(num)) {
	    if(num % 6 == 5)
		num+=2;
	    else
		num+=4;
	}
	return num;
    }

    /**
     *This method determines if a number is prime.
     **/
    private boolean isPrime(int num) {
	if(num % 2 == 0)
	    return false;
	for(int i = 3; i <= (int)Math.sqrt(num); i+=2)
	    if(num % i == 0)
		return false;
	return true;
    }
}
      
