package edu.ntu.eee.csn.ism.egene.util;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.util.WordNet;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class WordnetTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(WordnetTest.class);
	private WordNet wordnet = WordNet.getInstance();

	/**
	 * Constructor
	 */
	public WordnetTest() {
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
	public WordnetTest(String name) {
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
		TestSuite testSuite = new TestSuite(WordnetTest.class);
		return testSuite;
	}

	public void testIsWord() {
		assertEquals(true, wordnet.isWord("happy"));
		assertEquals(false, wordnet.isWord("123"));
	}

	public void testIsAdjective() {
		assertEquals(true, wordnet.isAdjective("happy"));
		assertEquals(false, wordnet.isAdjective("apple"));
	}

	public void testIsNoun() {
		assertEquals(true, wordnet.isNoun("apple"));
		assertEquals(false, wordnet.isNoun("happy"));
	}

	public void testIsVerb() {
		assertEquals(true, wordnet.isVerb("go"));
		assertEquals(false, wordnet.isVerb("apple"));
	}

}
