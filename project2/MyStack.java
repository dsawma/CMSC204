import java.util.ArrayList;
/*
 * @author David Sawma
 * CMSC 204 23393
 */

public class MyStack<T> implements StackInterface<T>{
	
	private T[] stack;  //array of stack objects
	private int topIndex; //int variable for topIndex
	private int size=0;		//int variable for the number of elements, initialize to zero
	private int capacity; 	//int variable for the capacity of stack
	private static final int DEFAULT_CAPACITY=50;	//constant int variable for the default capacity of 50
	
	//default constructor that takes in the default capacity 
	public MyStack(){
		this(DEFAULT_CAPACITY);
	}
	
	/*constructor to create the queue 
	 * @param int size of the queue
	 */
	public MyStack(int size) {
		@SuppressWarnings("unchecked")
		T[] tempStack=(T[])new Object [size+1];
		capacity = size;
		stack= tempStack;
		topIndex =-1;
	}


	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return topIndex <0;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return (size == capacity);
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			T top = stack[topIndex];
			stack[topIndex]=null;
			topIndex--;
			size--;
			return top;
		}
	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			return stack[topIndex];
		}
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException {
		if(size>=capacity) {
			throw new StackOverflowException();
			}
		else {
			stack[topIndex+1]= e;
			topIndex++;
			size++;
			return true;
		}
	}
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String str = "";
		   
	    for (int i = 0; i < size; i++) {
	      str += stack[i];
	    }
	    return str;
	}

	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String str = "";
	    
	    for (int i = 0; i < size-1; i++) {
	      str +=(stack[i] + delimiter);
	    }
	    str += stack[size-1];
	    
	    return str;
	}

	/**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	public void fill(ArrayList<T> list) {
		 ArrayList<T> copyList = new ArrayList<>(list);
		    copyList.forEach(t -> {
		      try {
		        push(t);
		      } catch (StackOverflowException e) {
		        e.getMessage();
		      }
		    }
		 );
	}
	

}
