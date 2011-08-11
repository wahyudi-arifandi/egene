package edu.ntu.eee.csn.ism.egene.business;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.business.Noun;
import edu.ntu.eee.csn.ism.egene.util.Gender;

public class NounTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(NounTest.class);

	/**
	 * Constructor
	 */
	public NounTest() {
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
	public NounTest(String name) {
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
		TestSuite testSuite = new TestSuite(NounTest.class);
		return testSuite;
	}

	public void testNoun() {
		Noun noun = new Noun("apple");
		assertEquals("apple", noun.getSingular());
		assertEquals("apples", noun.getPlural());
		assertEquals("it", noun.getPronounSubj());
		assertEquals("it", noun.getPronounObj());
		assertEquals("its", noun.getPossAdj());
		assertEquals("its", noun.getPossPron());
	}

	public void testNounWithGender() {
		Noun noun = new Noun("alice", Gender.FEMALE);
		assertEquals("alice", noun.getSingular());
		assertEquals("alice", noun.getPlural());
		assertEquals("she", noun.getPronounSubj());
		assertEquals("her", noun.getPronounObj());
		assertEquals("her", noun.getPossAdj());
		assertEquals("hers", noun.getPossPron());
	}

	public void testEquals() {
		Noun noun1 = new Noun("apple");
		Noun noun2 = new Noun("apple");
		Noun noun3 = new Noun("grape");
		assertEquals(true, noun1.equals(noun2));
		assertEquals(false, noun1.equals(noun3));
	}
}
