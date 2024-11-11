import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * @author David Sawma
 * CMSC 204 23393
 *
 */


public class BasicDoubleLinkedList<T> implements Iterable<T>{
	protected Node firstNode;
	protected Node lastNode;
	protected int numberOfEntries;
	
	/*
	 * Constructor to set to initialize head, tail and size to null, null and 0
	 */
	
	public BasicDoubleLinkedList() {
		firstNode=null;
		lastNode=null;
		numberOfEntries=0;
	}
	
	/*
	 * Returns the number of nodes in the list.
	 * @returns numberOfEntries
	 */

	public int getSize() {
		return numberOfEntries;
	}
	
	/*
	 * Adds an element to the end of the list and updated the size of the list
	 * @param data,the data to be added to the list
	 */
	
	public void addToEnd(T data){
		Node newNode = new Node(data);
		if(numberOfEntries == 0) {
			firstNode= newNode;	
			lastNode = newNode;
		}
		else {
			lastNode.next = newNode;
			newNode.prev = lastNode;
			lastNode = newNode;
		}	
		numberOfEntries++;
	}
	
	/*
	 * Adds element to the front of the list and updated the size of the list
	 * @param data, the data to be added to the list
	 */
	
	public void addToFront(T data){
		Node newNode=new Node(data);
		if(numberOfEntries==0) {
			firstNode= newNode;
			lastNode= newNode;
		}
		else {
			newNode.next=firstNode;
			firstNode.prev=newNode;	
			firstNode=newNode;
		}
		numberOfEntries++;
	}
	
	/*
	 * Returns but does not remove the first element from the list.
	 * If there are no elements the method returns null.
	 * @return the data element or null
	 */
	
	public T getFirst() {
		if(numberOfEntries==0) {
			return null;
		}
		else {
			return firstNode.data;
		}

	}
	
	/*
	 * Returns but does not remove the last element from the list.
	 * @return the data element or null
	 */
	
	public T getLast() {
		if(numberOfEntries==0) {
			return null;
		}
		else {
			return lastNode.data;
		}
	}
	
	/*
	 * This method returns an object of the DoubleLinkedListIterator.
	 * @return a ListIterator object
	 */
	
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	/*
	 * Removes the first instance of the targetData from the list.
	 * @param targetData - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return a node containing the targetData or null
	 */
	
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T>comparator){
		Node prevNode=null;
		Node found=null;
		for(Node currentNode = firstNode ; currentNode != null; currentNode = currentNode.next) {
			if(comparator.compare(targetData,currentNode.data)==0) {
				if(numberOfEntries==1) {
					found=firstNode;
					firstNode=null;
					lastNode=null;
					numberOfEntries--;
					break;
				}	
				else if(currentNode==firstNode) {
					found=currentNode;
					firstNode.next.prev=null;
					firstNode=firstNode.next;
					numberOfEntries--;
					break;
				}
				else if(currentNode==lastNode){
					found=currentNode;
					lastNode.prev.next=null;;
					lastNode=lastNode.prev;
					numberOfEntries--;
					break;
				}
				else {
					found=currentNode;
					currentNode.prev.next=currentNode.next;
					currentNode.next.prev=currentNode.prev;
					numberOfEntries--;
					break;
				}
			
			}
			prevNode=currentNode;
		}
		return this;
	}
	
	/*
	 * Removes and returns the first element from the list. If there are no elements the method returns null. 
	 * @return data element or null
	 */
	
	public T retrieveFirstElement() {
		if(numberOfEntries==0) {
			return null;
		}
		else if(numberOfEntries==1) {
			T result=getFirst();
			firstNode=null;
			lastNode=null;
			numberOfEntries--;
			return result;
		}
		else {
			T result=getFirst();
			firstNode=firstNode.next;
			numberOfEntries--;
			return result;
		}	
		
	}
	
	/*
	 * Removes and returns the last element from the list. If there are no elements the method returns null.
	 * @return data element or null
	 */
	
	public T retrieveLastElement() {
		if(numberOfEntries==0) {
			return null;
		}
		else if(numberOfEntries==1) {
			T result=getLast();
			firstNode=null;
			lastNode=null;
			numberOfEntries--;
			return result;
		}
		else {
			T result=getLast();
			lastNode=lastNode.prev;
			numberOfEntries--;
			return result;
		}
	}
	
	/*
	 * Returns an Arraylist of all the items in the Double Linked list
	 * @return an Arraylist of the items in the list
	 */
	
	public ArrayList<T> toArrayList(){
		ArrayList<T> list=new ArrayList<T>();
		int index = 0;
		Node currentNode= firstNode;
		while((index< numberOfEntries)&& (currentNode!=null)) {
			list.add(currentNode.data);
			currentNode= currentNode.next;
			index++;
		}
		return list;
	}
	
	/*
	 * A generic inner class Node
	 * data of type T, prev of type Node, next of type Node
	 */

	public class Node {
		protected Node prev;
		protected Node next;
		public T data;
	
		public Node(T dataNode){
			 prev = null;
			 next = null;
			data=dataNode;
		}

	}
	
	/*
	 *  generic inner class named DoubleLinkedListIterator that implements java’s ListIterator interface
	 */
	
	public class DoubleLinkedListIterator implements ListIterator<T> {
		private Node nextNode;
		private Node prevNode;
		
		public DoubleLinkedListIterator(){
			nextNode=firstNode;
			prevNode=null;
		}
		
		/* 
		 * Checks if there is a next node
		 * @return true if there is next node, false if not
		 */
		
		public boolean hasNext() {
			return nextNode != null;
		}
		
		/* 
		 * Gets the data of the next node
		 * @return The data of the next node 
		 *  @throws NoSuchElementException Throws if no node
		 */
		
		public T next() throws NoSuchElementException{
			T result;
			if(hasNext())
			{
				prevNode=nextNode;
				nextNode=nextNode.next;
				result= prevNode.data;
			}
			else 
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
			return result;
		}
		
		/* 
		 * Checks if there is a previous node
		 * @return true if there is previous node, false if not
		 */
		
		
		public boolean hasPrevious() {
			return prevNode!=null;
			
		}
		
		/* 
		 * Gets the data of the previous node
		 * @return The data of the previous node 
		 *  @throws NoSuchElementException Throws if no node
		 */
	
		
		public T previous() throws NoSuchElementException{
			T result;
			if (hasPrevious()) {
				nextNode=prevNode;
				prevNode=prevNode.prev;
				result= nextNode.data;
			}
			else 	
				throw new NoSuchElementException("Illegal call to previous(); " + "iterator is before beginning of list.");
			return result;
		}
		
		
		public void remove() {
			throw new UnsupportedOperationException(); 
		}
		
		public int nextIndex() {
			throw new UnsupportedOperationException(); 
		}
		
		public int previousIndex() {
			throw new UnsupportedOperationException(); 
		}
		
		public void set(T e) {
			throw new UnsupportedOperationException(); 
			
		}
		
		public void add(T e) {
			throw new UnsupportedOperationException(); 
			
		}
		
	}

}