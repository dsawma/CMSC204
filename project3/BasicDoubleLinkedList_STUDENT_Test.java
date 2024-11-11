import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * @author David Sawma
 * CMSC 204 23393
 *
 */



public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("World");
		comparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("Last");
		assertEquals("Last", linkedString.getLast());
		
		assertEquals(100.0, linkedDouble.getLast(),.00);
		linkedDouble.addToEnd(200.0);
		assertEquals(200.0, linkedDouble.getLast(),.00);
		
		
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("First");
		assertEquals("First", linkedString.getFirst());
		
		assertEquals(15.0, linkedDouble.getFirst(),.00);
		linkedDouble.addToFront(50.0);
		assertEquals(50.0, linkedDouble.getFirst(),.00);
		
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Front");
		assertEquals("Front", linkedString.getFirst());
		
		assertEquals(15.0, linkedDouble.getFirst(),.00);
		linkedDouble.addToFront(1.0);
		assertEquals(1.0, linkedDouble.getFirst(),.00);
		
		
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
		
		assertEquals(100.0, linkedDouble.getLast(),.00);
		linkedDouble.addToEnd(500.0);
		assertEquals(500.0, linkedDouble.getLast(),.00);
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Double> list;
		linkedDouble.addToFront(1.0);
		linkedDouble.addToEnd(250.0);
		list=linkedDouble.toArrayList();
		
		assertEquals(1.0,list.get(0),.00);
		assertEquals(15.0,list.get(1),.00);
		assertEquals(100.0,list.get(2),.00);
		assertEquals(250.0,list.get(3),.00);
		
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("Front");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Front", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
		
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedDouble.addToFront(1.0);
		linkedDouble.addToEnd(200.0);
		ListIterator<Double> iteratorD = linkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(1.0, iteratorD.next(),.0);
		assertEquals(15.0, iteratorD.next(),.0);
		assertEquals(100.0, iteratorD.next(),.0);
		assertEquals(200.0, iteratorD.next(),.0);
		assertEquals(true, iteratorD.hasPrevious());
		assertEquals(200.0, iteratorD.previous(),.0);
		assertEquals(100.0, iteratorD.previous(),.0);
		assertEquals(15.0, iteratorD.previous(),.0);
		assertEquals(1.0, iteratorD.previous(),.0);
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedDouble.addToFront(1.0);
		linkedDouble.addToEnd(200.0);
		ListIterator<Double> iteratorD = linkedDouble.iterator();		
		assertEquals(true, iteratorD.hasNext());
		assertEquals(1.0, iteratorD.next(),.0);
		assertEquals(15.0, iteratorD.next(),.0);
		assertEquals(100.0, iteratorD.next(),.0);
		assertEquals(true, iteratorD.hasNext());
		assertEquals(200.0, iteratorD.next(),.0);
		
		try{
			iteratorD.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedString.addToFront("Front");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();		
		assertEquals(true, iterator.hasNext());
		assertEquals("Front", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals("End", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("End", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("Front", iterator.previous());
		
		try{
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedDouble.addToFront(1.0);
		linkedDouble.addToEnd(200.0);
		ListIterator<Double> iteratorD = linkedDouble.iterator();		
		assertEquals(true, iteratorD.hasNext());
		assertEquals(1.0, iteratorD.next(),.0);
		assertEquals(15.0, iteratorD.next(),.0);
		assertEquals(100.0, iteratorD.next(),.0);
		assertEquals(true, iteratorD.hasNext());
		assertEquals(200.0, iteratorD.next(),.0);
		
		try{
			iteratorD.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		assertEquals(15.0, linkedDouble.getFirst(),.0);
		assertEquals(100.0, linkedDouble.getLast(),.0);
		linkedDouble.addToFront(1.0);
		assertEquals(1.0, linkedDouble.getFirst(),.0);
		linkedDouble.remove(1.0, comparatorD);
		assertEquals(15.0, linkedDouble.getFirst(),.0);
		
		linkedDouble.addToEnd(200.0);
		assertEquals(200.0, linkedDouble.getLast(),.0);
		linkedDouble.remove(200.0, comparatorD);
		assertEquals(100.0, linkedDouble.getLast(),.0);
		
		linkedDouble.addToFront(1.0);
		assertEquals(1.0, linkedDouble.getFirst(),.0);
		assertEquals(100.0, linkedDouble.getLast(),.0);
		linkedDouble.remove(15.0, comparatorD);
		assertEquals(1.0, linkedDouble.getFirst(),.0);
		assertEquals(100.0, linkedDouble.getLast(), .0);
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(15.0, linkedDouble.getFirst(),.0);
		linkedDouble.addToFront(1.0);
		assertEquals(1.0, linkedDouble.getFirst(),.0);
		assertEquals(1.0, linkedDouble.retrieveFirstElement(),.0);
		assertEquals(15.0,linkedDouble.getFirst(),.0);
		assertEquals(15.0, linkedDouble.retrieveFirstElement(),.0);
		assertEquals(100.0,linkedDouble.getFirst(),.0);
		
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Front");
		assertEquals("Front", linkedString.getFirst());
		assertEquals("Front", linkedString.retrieveFirstElement());
		assertEquals("Hello",linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World",linkedString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(100.0, linkedDouble.getLast(),.0);
		linkedDouble.addToEnd(200.0);
		assertEquals(200.0, linkedDouble.getLast(),.0);
		assertEquals(200.0, linkedDouble.retrieveLastElement(),.0);
		assertEquals(100.0,linkedDouble.getLast(),.0);
		
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
		assertEquals("End", linkedString.retrieveLastElement());
		assertEquals("World",linkedString.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	
	

}