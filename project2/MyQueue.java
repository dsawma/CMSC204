import java.util.ArrayList;
/*
 * @author David Sawma
 * CMSC 204 23393
 */

public class MyQueue<T> implements QueueInterface<T>{

	private T[] queue;			//array of queue objects
	private int frontIndex;		//int variable keeps track of front index
	private int backIndex;		//int variable keeps track of back index
	private int size= 0;		// int variable keeps track of the number of elements, initialize to zero
	private int capacity;		//int  variable keeps track of capacity of queue
	private static final int DEFAULT_CAPACITY = 50; //constant int variable for default capacity of 50
	
	//default constructor that takes in the default capacity
	public MyQueue(){
		this(DEFAULT_CAPACITY);
	}
	
	/*constructor to create the queue 
	 * @param int size of the queue
	 */
	public MyQueue(int size) {
		@SuppressWarnings("unchecked")
		T[] tempQueue=(T[])new Object [size+1];
		capacity = size;
		queue= tempQueue;
		frontIndex =0;
		backIndex = size;
	}
	
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		return frontIndex ==((backIndex +1)%queue.length);
	}

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		return (size == capacity);
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		else {
			T front = queue[frontIndex];
			queue[frontIndex]=null;
			frontIndex = (frontIndex+1)%queue.length;
			size--;
			return front;
		}
	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if(size==capacity) {
			throw new QueueOverflowException();
		}
		else {
			backIndex=(backIndex+1)%queue.length;
			queue[backIndex]=e;
			size++;
			return true;
		}
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		 String str = "";
		   
		    for (int i = frontIndex; i <= backIndex; i++) {
		      str += queue[i];
		    }
		    return str;
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		 String str = "";
		    
		    for (int i = frontIndex; i < backIndex; i++) {
		      str +=(queue[i] + delimiter);
		    }
		    str += queue[backIndex];
		    return str;
	}

	/**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyList = new ArrayList<>(list);
	    copyList.forEach(t -> {
	      try {
	        enqueue(t);
	      } catch (QueueOverflowException e) {
	        e.getMessage();
	      }
	    });
	   
	}
	

}
