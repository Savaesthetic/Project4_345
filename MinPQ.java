public class MinPQ<T extends Comparable<T>> {
    private T[] array;//the array-based heap
    private int nextOpen;//indicates the next open index in the array
    private final int CAP;//the initial capacity of the array
    private int size;

    /***
     *constructor: constructs a new 
     *MinPQ with starting capacity of 10
     ***/
    public MinPQ() {
	this.CAP = 10;
	this.array = (T[]) new Comparable[CAP];
	this.nextOpen = 0;
    this.size = 10;
    }

    /**
     *constructor: constructs a new MinPQ with 
     *starting capacity of cap
     ***/
    public MinPQ(int cap) {
	this.CAP = cap;
	this.array = (T[]) new Comparable[CAP];
	this.nextOpen = 0;
    this.size = cap;
    }

    /***
     *@param item to be inserted into PQ
     *if the array is full before the insert, 
     *resize the array to be twice as large
     ***/
    public void insert(T item) {
	    if (this.size == this.nextOpen) {
            // Create new array for size*2 and populate it with old values
            T[] newArr = (T[]) new Comparable[this.size * 2];
            for (int i = 0; i < this.size; i++) {
                newArr[i] = this.array[i];
            }
            
            this.array = newArr;
            this.size = newArr.length;
            this.array[nextOpen] = item;
            this.swim(nextOpen);
            nextOpen++;
        } else {
            this.array[nextOpen] = item;
            this.swim(nextOpen);
            nextOpen++;
        }
    }

    /***
     *@return and remove the min item in the PQ and re-heapify
     *throw EmptyQueueException if the PQ is empty
     *If (after the remove) the size drops to less than or equal to 1/4 of the array's capacity
     *and if halving the array would not make it go below the original capacity,
     *then resize the array to half its size.
     ***/
    public T delMin() throws EmptyQueueException {
	    if (this.isEmpty()) {
            throw new EmptyQueueException();
        } else {
            T val = this.array[0];
            this.array[0] = this.array[nextOpen-1];
            sink(0);
            return val;
        }
    }

    /***
     *@return but do not remove the min item in the PQ
     *throw EmptyQueueException if the PQ is empty
     ***/
    public T getMin() throws EmptyQueueException {
	    if (this.isEmpty()) {
            throw new EmptyQueueException();
        } else {
            return array[0];
        }
    }

    /***
     *@return the number of items currently in 
     *the PQ
     ***/
    public int size() {
	    return nextOpen;
    }

    /***
     *@return true if the PQ is empty and false
     *otherwise
     ***/
    public boolean isEmpty() {
	    return nextOpen == 0;
    }

    /***
     *returns the underlying array
     *This method is only used for testing.
     *Do not change it, and do not use it in your
     *own code!!!
     ***/
    public T[] getArray() {
	return this.array;
    }

    private void swim(int index) {
        if (this.array[(index-1)/2].compareTo(array[index]) > 0) {
            T temp = this.array[(index-1)/2];
            this.array[(index-1)/2] = this.array[index];
            this.array[index] = temp;
            this.swim((index-1)/2);
        }
    }

    private void sink(int index) {
        int childIndex = this.array[(index*2)+1].compareTo(this.array[(index*2) + 2]) > 0 ? (index*2)+2 : (index*2)+1; // Does it matter which child we swap with if equal?
        if (this.array[index].compareTo(array[childIndex]) > 0) {
            T temp = this.array[index];
            this.array[index] = this.array[childIndex];
            this.array[childIndex] = temp;
            this.sink(childIndex);
        }
    }
}
    
