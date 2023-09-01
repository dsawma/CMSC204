import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradebookTester {
	GradeBook g1;
	GradeBook g2;
	
	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		g1.addScore(80);
		g1.addScore(75);
		g2.addScore(50);
		g2.addScore(60);
	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(g1.toString().equals("80.0 75.0 "));
		assertEquals(2,g2.getScoreSize(), .0001);
	}

	@Test
	void testSum() {
		assertEquals(155, g1.sum(), .0001);
	}

	@Test
	void testMinimum() {
		assertEquals(75, g1.minimum(), .0001);
	}

	@Test
	void testFinalScore() {
		assertTrue(g1.toString().equals("80.0 75.0 "));
	}


}
