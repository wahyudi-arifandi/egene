package edu.ntu.eee.csn.ism.egene.util;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.util.Codec;

public class CodecTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(CodecTest.class);

	/**
	 * Constructor
	 */
	public CodecTest() {
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
	public CodecTest(String name) {
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
		TestSuite testSuite = new TestSuite(CodecTest.class);
		return testSuite;
	}

	public void testDecodeUrl() {
		String url = "C:\\Program%20Files";
		String expectedResult = "C:\\Program Files";
		assertEquals(expectedResult, Codec.decodeUrl(url));
		assertEquals(null, Codec.decodeUrl(null));
		assertEquals("", Codec.decodeUrl(""));
	}

	public void testIntArrayToString() {
		String strArr = "[1;2;3;4;]";
		int[] arr = { 1, 2, 3, 4 };
		assertEquals(strArr, Codec.intArrayToString(arr));
		assertEquals(null, Codec.intArrayToString(null));
		assertEquals("[]", Codec.intArrayToString(new int[0]));
	}
}
