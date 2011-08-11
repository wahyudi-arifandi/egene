package edu.ntu.eee.csn.ism.egene.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.util.NumberUtil;

public class NumberUtilTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(NumberUtilTest.class);

	/**
	 * Constructor
	 */
	public NumberUtilTest() {
		super();
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("instantiate");
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            test name
	 */
	public NumberUtilTest(String name) {
		super(name);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("instantiate");
	}

	/**
	 * Declares the test suite.
	 * 
	 * @return the test suite.
	 */
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite(NumberUtilTest.class);
		return testSuite;
	}

	public void testGenerateInt() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				int intGen = NumberUtil.generateInt(86, 99);
				assertTrue(intGen >= 86 && intGen <= 99);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}
	}

	public void testGenerateIntMul() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> list = Arrays.asList(mul);
				int intGen = NumberUtil.generateIntMul(86, 99, list);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen % 2 == 0);
				assertTrue(intGen % 3 == 0);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3, 50 };
				List<Integer> list = Arrays.asList(mul);
				int intGen = NumberUtil.generateIntMul(86, 99, list);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen % 2 == 0);
				assertTrue(intGen % 3 == 0);
				assertTrue(intGen % 50 == 0);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				int intGen = NumberUtil.generateIntMul(86, 99, null);
				assertTrue(intGen >= 86 && intGen <= 99);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}
	}

	public void testGenerateIntExcl() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 90 };
				List<Integer> list = Arrays.asList(excl);
				int intGen = NumberUtil.generateIntExcl(86, 99, list);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen != 90);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96,
						97, 98, 99 };
				List<Integer> list = Arrays.asList(excl);
				int intGen = NumberUtil.generateIntExcl(86, 99, list);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen != 90);
				assertTrue(intGen != 96);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				int intGen = NumberUtil.generateIntExcl(86, 99, null);
				assertTrue(intGen >= 86 && intGen <= 99);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}
	}

	public void testGenerateIntMulExcl() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				Integer[] excl = { 90 };
				List<Integer> lExcl = Arrays.asList(excl);
				int intGen = NumberUtil.generateIntMulExcl(86, 99, lMul, lExcl);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen != 90);
				assertTrue((intGen % 2) == 0);
				assertTrue((intGen % 3) == 0);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				Integer[] excl = { 90, 96 };
				List<Integer> lExcl = Arrays.asList(excl);
				int intGen = NumberUtil.generateIntMulExcl(86, 99, lMul, lExcl);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen != 90);
				assertTrue((intGen % 2) == 0);
				assertTrue((intGen % 3) == 0);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 90 };
				List<Integer> lExcl = Arrays.asList(excl);
				int intGen = NumberUtil.generateIntMulExcl(86, 99, null, lExcl);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue(intGen != 90);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				int intGen = NumberUtil.generateIntMulExcl(86, 99, lMul, null);
				assertTrue(intGen >= 86 && intGen <= 99);
				assertTrue((intGen % 2) == 0);
				assertTrue((intGen % 3) == 0);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				int intGen = NumberUtil.generateIntMulExcl(86, 99, null, null);
				assertTrue(intGen >= 86 && intGen <= 99);

			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}
	}

	public void testGenerateDouble() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				double dblGen = NumberUtil.generateDouble(86, 99, 1);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				double dbl = NumberUtil.generateDouble(86, 99, -1);
				assertTrue(dbl >= 8.6d && dbl <= 9.9d);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}
	}

	public void testGenerateDoubleMul() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> list = Arrays.asList(mul);
				double dblGen = NumberUtil.generateDoubleMul(86, 99, 1, list);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) % 2 == 0);
				assertTrue((dblGen * 10) % 3 == 0);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3, 50 };
				List<Integer> list = Arrays.asList(mul);
				double dblGen = NumberUtil.generateDoubleMul(86, 99, 1, list);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) % 2 == 0);
				assertTrue((dblGen * 10) % 3 == 0);
				assertTrue((dblGen * 10) % 50 == 0);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				double dblGen = NumberUtil.generateDoubleMul(86, 99, 1, null);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> list = Arrays.asList(mul);
				double dblGen = NumberUtil.generateDoubleMul(86, 99, -1, list);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) % 2 == 0);
				assertTrue((dblGen * 10) % 3 == 0);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}
	}

	public void testGenerateDoubleExcl() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 90 };
				List<Integer> list = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleExcl(86, 99, 1, list);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96,
						97, 98, 99 };
				List<Integer> list = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleExcl(86, 99, 1, list);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
				assertTrue((dblGen * 10) != 96);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				double dblGen = NumberUtil.generateDoubleExcl(86, 99, 1, null);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 90 };
				List<Integer> list = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleExcl(86, 99, -1, list);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}
	}

	public void testGenerateDoubleMulExcl() {

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				Integer[] excl = { 90 };
				List<Integer> lExcl = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleMulExcl(86, 99, 1,
						lMul, lExcl);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
				assertTrue(((dblGen * 10) % 2) == 0);
				assertTrue(((dblGen * 10) % 3) == 0);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				Integer[] excl = { 90, 96 };
				List<Integer> lExcl = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleMulExcl(86, 99, 1,
						lMul, lExcl);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
				assertTrue(((dblGen * 10) % 2) == 0);
				assertTrue(((dblGen * 10) % 3) == 0);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] excl = { 90 };
				List<Integer> lExcl = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleMulExcl(86, 99, 1,
						null, lExcl);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				double dblGen = NumberUtil.generateDoubleMulExcl(86, 99, 1,
						lMul, null);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue(((dblGen * 10) % 2) == 0);
				assertTrue(((dblGen * 10) % 3) == 0);
			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				double dblGen = NumberUtil.generateDoubleMulExcl(86, 99, 1,
						null, null);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);

			} catch (Throwable t) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(t.getMessage(), t);

				ex = t;
			}
			assertNull(ex);
		}

		for (int i = 0; i < 15; i++) {
			Throwable ex = null;
			try {
				Integer[] mul = { 2, 3 };
				List<Integer> lMul = Arrays.asList(mul);
				Integer[] excl = { 90 };
				List<Integer> lExcl = Arrays.asList(excl);
				double dblGen = NumberUtil.generateDoubleMulExcl(86, 99, -1,
						lMul, lExcl);
				assertTrue(dblGen >= 8.6d && dblGen <= 9.9d);
				assertTrue((dblGen * 10) != 90);
				assertTrue(((dblGen * 10) % 2) == 0);
				assertTrue(((dblGen * 10) % 3) == 0);
			} catch (Throwable t) {
				ex = t;
			}
			assertNotNull(ex);
		}
	}

	public void testIsMul() {
		Integer[] mul1 = { 3 };
		List<Integer> lMul1 = Arrays.asList(mul1);
		Integer[] mul3 = { 3, -6, 7 };
		List<Integer> lMul3 = Arrays.asList(mul3);
		assertTrue(NumberUtil.isMul(42, 3));
		assertTrue(NumberUtil.isMul(42, -3));
		assertTrue(NumberUtil.isMul(-42, 3));
		assertTrue(NumberUtil.isMul(-42, -3));
		assertTrue(NumberUtil.isMul(42, lMul1));
		assertTrue(NumberUtil.isMul(42, lMul3));
		assertTrue(NumberUtil.isMul(42, null));
		assertTrue(NumberUtil.isMul(42, new ArrayList<Integer>()));
		assertFalse(NumberUtil.isMul(2, lMul3));
		assertFalse(NumberUtil.isMul(10, lMul3));

	}

	public void testGcd() {

		Throwable ex1 = null;
		try {
			assertEquals(0, NumberUtil.gcd(0, 0));
		} catch (Throwable t) {
			ex1 = t;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertEquals(5, NumberUtil.gcd(5, 0));
		} catch (Throwable t) {
			ex2 = t;
		}
		assertNotNull(ex2);

		Throwable ex3 = null;
		try {
			assertEquals(5, NumberUtil.gcd(0, 5));
		} catch (Throwable t) {
			ex3 = t;
		}
		assertNotNull(ex3);

		Throwable ex4 = null;
		try {
			assertEquals(1, NumberUtil.gcd(1, 1));
			assertEquals(1, NumberUtil.gcd(1, 4));
			assertEquals(1, NumberUtil.gcd(4, 1));
			assertEquals(4, NumberUtil.gcd(32, 12));
			assertEquals(NumberUtil.gcd(32, 12), NumberUtil.gcd(12, 32));
		} catch (Throwable t) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(t.getMessage(), t);
			ex4 = t;
		}
		assertNull(ex4);
	}

	public void testLcm() {
		Throwable ex1 = null;
		try {
			assertEquals(0, NumberUtil.lcm(0, 0));
		} catch (Throwable t) {
			ex1 = t;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertEquals(5, NumberUtil.lcm(5, 0));
		} catch (Throwable t) {
			ex2 = t;
		}
		assertNotNull(ex2);

		Throwable ex3 = null;
		try {
			assertEquals(5, NumberUtil.lcm(0, 5));
		} catch (Throwable t) {
			ex3 = t;
		}
		assertNotNull(ex3);

		Throwable ex4 = null;
		try {
			assertEquals(20, NumberUtil.lcm(null));
		} catch (Throwable t) {
			ex4 = t;
		}
		assertNotNull(ex4);

		Throwable ex5 = null;
		try {
			assertEquals(20, NumberUtil.lcm(new ArrayList<Integer>()));
		} catch (Throwable t) {
			ex5 = t;
		}
		assertNotNull(ex5);

		Throwable ex6 = null;
		try {
			assertEquals(12, NumberUtil.lcm(4, 6));
			assertEquals(12, NumberUtil.lcm(6, 4));
			assertEquals(4, NumberUtil.lcm(4, 1));
			assertEquals(4, NumberUtil.lcm(1, 4));
			assertEquals(1, NumberUtil.lcm(1, 1));

			Integer[] num = { 4, -5, 10 };
			List<Integer> lNum = Arrays.asList(num);
			assertEquals(20, NumberUtil.lcm(lNum));

		} catch (Throwable t) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(t.getMessage(), t);
			ex6 = t;
		}
		assertNull(ex6);

	}

	public void testRemoveDecimal() {
		assertEquals(0, NumberUtil.removeDecimal(0.0d));
		assertEquals(-25, NumberUtil.removeDecimal(-2.5d));
		assertEquals(12345, NumberUtil.removeDecimal(12.345d));
		assertEquals(45, NumberUtil.removeDecimal(0.045d));
	}

}
