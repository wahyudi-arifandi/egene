package edu.ntu.eee.csn.ism.egene.util;

import junit.framework.TestSuite;

/**
 * Register all of test for edu.ntu.eee.csn.ism.epgen.util
 * 
 * @author warifand
 * 
 */
public class AllTests extends TestSuite {

	/**
	 * Constructor
	 */
	public AllTests() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            test name
	 */
	public AllTests(String name) {
		super(name);
	}

	/**
	 * Declares the test suite.
	 * 
	 * @return the test suite.
	 */
	public static TestSuite suite() {

		AllTests tests = new AllTests(AllTests.class.getName());
		tests.addTest(DBUtilTest.suite());
		tests.addTest(WordnetTest.suite());
		tests.addTest(VerbUtilTest.suite());
		tests.addTest(NounUtilTest.suite());
		tests.addTest(CodecTest.suite());
		tests.addTest(NumberUtilTest.suite());
		tests.addTest(OptUtilTest.suite());
		tests.addTest(TemplateUtilTest.suite());
		return tests;
	}
}
