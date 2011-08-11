package edu.ntu.eee.csn.ism.egene.business;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.business.Verb;

public class VerbTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(VerbTest.class);

	/**
	 * Constructor
	 */
	public VerbTest() {
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
	public VerbTest(String name) {
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
		TestSuite testSuite = new TestSuite(VerbTest.class);
		return testSuite;
	}

	public void testVerbRegular() {
		Verb verb = new Verb("push");
		assertEquals("pushing", verb.getGerund());
		assertEquals("pushed", verb.getPastParticiple());
		assertEquals("pushed", verb.getSimplePast());
		assertEquals("push", verb.getSimplePresent());
		assertEquals("pushes", verb.getThirdPersonS());
	}

	public void testVerbIrregular() {
		Verb verb = new Verb("do");
		assertEquals("doing", verb.getGerund());
		assertEquals("done", verb.getPastParticiple());
		assertEquals("did", verb.getSimplePast());
		assertEquals("do", verb.getSimplePresent());
		assertEquals("does", verb.getThirdPersonS());
	}

	public void testEquals() {
		Verb v1 = new Verb("do");
		Verb v2 = new Verb("do");
		Verb v3 = new Verb("sleep");
		assertEquals(true, v1.equals(v2));
		assertEquals(false, v1.equals(v3));
	}
}
