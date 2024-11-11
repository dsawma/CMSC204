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




public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;
		
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		
		
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("End");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedDouble.addToFront(1.0);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		//order: r p t q s
		sortedLinkedDouble.add(1.0);
		sortedLinkedDouble.add(15.0);
		sortedLinkedDouble.add(100.0);
		sortedLinkedDouble.add(200.0);
		ListIterator<Double> iteratorD = sortedLinkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(1.0, iteratorD.next(),.0);
		assertEquals(15.0, iteratorD.next(),.0);
		assertEquals(100.0, iteratorD.next(),.0);
		assertEquals(true, iteratorD.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Front");
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("End");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
		assertEquals("Front", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("Front", iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(1.0);
		sortedLinkedDouble.add(15.0);
		sortedLinkedDouble.add(100.0);
		sortedLinkedDouble.add(200.0);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1.0, iterator.next(),.0);
		assertEquals(15.0, iterator.next(),.0);
		assertEquals(100.0, iterator.next(),.0);
		assertEquals(true, iterator.hasNext());	
	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(1.0);
		sortedLinkedDouble.add(15.0);
		sortedLinkedDouble.add(100.0);
		sortedLinkedDouble.add(200.0);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(1.0, iterator.next(),.0);
		assertEquals(15.0, iterator.next(),.0);
		assertEquals(100.0, iterator.next(),.0);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(100.0, iterator.previous(),.0);
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedString.add("Front");
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("End");
		
		ListIterator<String> iteratorS = sortedLinkedString.iterator();
		
		assertEquals(true, iteratorS.hasNext());
		assertEquals("End" ,iteratorS.next());
		assertEquals("Front", iteratorS.next());
		assertEquals("Hello", iteratorS.next());
		assertEquals(true, iteratorS.hasNext());
		assertEquals("World", iteratorS.next());
		try{
			iteratorS.next();
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
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		
		ListIterator<String> iteratorS = sortedLinkedString.iterator();
		
		try{
			iteratorS.remove();
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
	public void testAddDouble() {
		sortedLinkedDouble.add(2.0);
		sortedLinkedDouble.add(15.0);
		sortedLinkedDouble.add(100.0);
		assertEquals(2.0, sortedLinkedDouble.getFirst(),.0);
		assertEquals(100.0, sortedLinkedDouble.getLast(),.0);
		sortedLinkedDouble.add(200.0);
		sortedLinkedDouble.add(1.0);
		assertEquals(1.0, sortedLinkedDouble.getFirst(),.0);
		assertEquals(200.0, sortedLinkedDouble.getLast(),.0);
		
		assertEquals(200.0,sortedLinkedDouble.retrieveLastElement(),.0);
		assertEquals(100.0, sortedLinkedDouble.getLast(),.0);
	}

	@Test
	public void testRemoveFirstString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		assertEquals("Hello", sortedLinkedString.getFirst());
		assertEquals("World", sortedLinkedString.getLast());
		sortedLinkedString.add("Front");
		sortedLinkedString.add("Zzz");
		assertEquals("Front", sortedLinkedString.getFirst());
		assertEquals("Zzz", sortedLinkedString.getLast());
		sortedLinkedString.remove("Front", comparator);
		assertEquals("Hello", sortedLinkedString.getFirst());
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