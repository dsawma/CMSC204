
import java.util.Comparator;
import java.util.ListIterator;
/*
 * @author David Sawma
 * CMSC 204 23393
 */
public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T>{
	
	private Comparator comparator;
	/*
	 * Creates an empty list that is associated with the specified comparator.
	 * @param comparator2 Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		comparator=compareableObject;
	}
	
	/*
	 * inserts specified element at correct position
	 * @param data data to be added to the list
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data){
		Node newNode=new Node(data);
		Node currentNode=firstNode;
		if(numberOfEntries==0) {
			firstNode=newNode;
			lastNode=newNode;
		}
		else {
			while(currentNode != null){
		
				if (comparator.compare(newNode.data, currentNode.data) == 0) {
					newNode.next=firstNode;
					firstNode.prev=newNode;	
					firstNode=newNode;
					break;
				} else if (comparator.compare(newNode.data, currentNode.data) < 0) {
					if (currentNode == firstNode) {
						newNode.next=firstNode;
						firstNode.prev=newNode;	
						firstNode=newNode;
					} else {
						newNode.next =currentNode;
						newNode.prev = currentNode.prev;
						currentNode.prev=newNode;
						newNode.prev.next=newNode;
					}
					break;
				} else if (comparator.compare(newNode.data, currentNode.data) > 0) {
					if (currentNode == lastNode) {
						lastNode.next=newNode;
						newNode.prev= lastNode;
						lastNode = newNode;
						break;
					} else {
						currentNode = currentNode.next;
					}
				}
			}
		}
		numberOfEntries++;
		return this;
 		
	}
	
	public void addToEnd(T data){
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	
	public void addToFront(T data){
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/*
	 * Implements the iterator by calling the super class iterator method.
	 * @Overrides iterator in class BasicDoubleLinkedList<T>
	 * @return an iterator positioned at the head of the list
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/*
	 * Implements the remove operation by calling the super class remove method.
	 * @Overrides remove in class BasicDoubleLinkedList<T>
	 * @param data - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @returns a node containing the data or null
	 */
	public BasicDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
		return super.remove(data, comparator);
		
	}
}