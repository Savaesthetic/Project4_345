public class MinPQ<T extends Comparable<T>> {
    private T[] array;//the array-based heap
    private int nextOpen;//indicates the next open index in the array
    private final int CAP;//the initial capacity of the array

    /***
     *constructor: constructs a new 
     *MinPQ with starting capacity of 10
     ***/
    public MinPQ() {
	this.CAP = 10;
	this.array = (T[]) new Comparable[CAP];
	this.nextOpen = 0;
    }

    /**
     *constructor: constructs a new MinPQ with 
     *starting capacity of cap
     ***/
    public MinPQ(int cap) {
	this.CAP = cap;
	this.array = (T[]) new Comparable[CAP];
	this.nextOpen = 0;
    }

    /***
     *@param item to be inserted into PQ
     *if the array is full before the insert, 
     *resize the array to be twice as large
     ***/
    public void insert(T item) {
	//TO BE IMPLEMENTED
    }

    /***
     *@return and remove the min item in the PQ and re-heapify
     *throw EmptyQueueException if the PQ is empty
     *If (after the remove) the size drops to less than or equal to 1/4 of the array's capacity
     *and if halving the array would not make it go below the original capacity,
     *then resize the array to half its size.
     ***/
    public T delMin() throws EmptyQueueException {
	//TO BE IMPLEMENTED
    }

    /***
     *@return but do not remove the min item in the PQ
     *throw EmptyQueueException if the PQ is empty
     ***/
    public T getMin() throws EmptyQueueException {
	//TO BE IMPLEMENTED
    }

    /***
     *@return the number of items currently in 
     *the PQ
     ***/
    public int size() {
	//TO BE IMPLEMENTED
    }

    /***
     *@return true if the PQ is empty and false
     *otherwise
     ***/
    public boolean isEmpty() {
	//TO BE IMPLEMENTED
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
}
    
