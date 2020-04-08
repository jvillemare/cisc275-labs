package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathTest {

	@Test
	public void testAdd() {
		int sum = Math.add(3, 2);
		assertEquals(5, sum);
	} 
	
	@Test
	public void testMultiply() {
		int product = Math.multiply(3, 2);
		assertEquals(6, product);
	}

}
