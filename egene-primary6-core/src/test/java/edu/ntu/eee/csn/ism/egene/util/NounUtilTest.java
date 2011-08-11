package edu.ntu.eee.csn.ism.egene.util;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.util.NounUtil;

public class NounUtilTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(NounUtilTest.class);
	private NounUtil nounUtil = NounUtil.getInstance();

	/**
	 * Constructor
	 */
	public NounUtilTest() {
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
	public NounUtilTest(String name) {
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
		TestSuite testSuite = new TestSuite(NounUtilTest.class);
		return testSuite;
	}

	public void testPluralize() {
		assertEquals(null, this.nounUtil.pluralize(null));
		assertEquals("", this.nounUtil.pluralize(""));
		assertEquals("children", this.nounUtil.pluralize("child"));
		assertEquals("boxes", this.nounUtil.pluralize("box"));
		assertEquals("tables", this.nounUtil.pluralize("table"));
		assertEquals("chairs", this.nounUtil.pluralize("chair"));
	}

	public void testSingularize() {
		assertEquals(null, this.nounUtil.singularize(null));
		assertEquals("", this.nounUtil.singularize(""));
		assertEquals("child", this.nounUtil.singularize("children"));
		assertEquals("box", this.nounUtil.singularize("boxes"));
		assertEquals("table", this.nounUtil.singularize("tables"));
		assertEquals("chair", this.nounUtil.singularize("chairs"));

	}

	public void testIsUncountable() {
		assertEquals(false, this.nounUtil.isUncountable(null));
		assertEquals(false, this.nounUtil.isUncountable(""));
		assertEquals(true, this.nounUtil.isUncountable("information"));
		assertEquals(false, this.nounUtil.isUncountable("child"));
	}
}
