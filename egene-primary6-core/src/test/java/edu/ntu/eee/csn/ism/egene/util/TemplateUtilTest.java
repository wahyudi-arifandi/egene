package edu.ntu.eee.csn.ism.egene.util;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.TemplateUtilException;
import edu.ntu.eee.csn.ism.egene.util.TemplateUtil;

public class TemplateUtilTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(TemplateUtilTest.class);

	/**
	 * Constructor
	 */
	public TemplateUtilTest() {
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
	public TemplateUtilTest(String name) {
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
		TestSuite testSuite = new TestSuite(TemplateUtilTest.class);
		return testSuite;
	}

	public void testPing() {

		Throwable t = null;
		try {
			TemplateUtil tpl = new TemplateUtil("dual");
			assertTrue(tpl.ping());
			tpl.close();
			TemplateUtil tpl2 = new TemplateUtil("dual");
			assertTrue(tpl2.ping());
			tpl2.close();
		} catch (TemplateUtilException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			t = e;
		}
		assertNull(t);
	}

//	public void testRetrieveTpl() {
//		TemplateUtil tpl = new TemplateUtil("tpl_nfruit");
//		if (LOGGER.isDebugEnabled())
//			LOGGER.debug("dataCount: " + tpl.getDataCount());
//		for (int i = 0; i < 10; i++) {
//			String str = tpl.retrieveTemplate();
//			if (LOGGER.isDebugEnabled())
//				LOGGER.debug("retrieveTpl: " + str);
//		}
//		tpl.close();
//	}
//
//	public void testRetrieveTplExcl() {
//
//		List<String> excl = new ArrayList<String>();
//		excl.add("orange");
//		excl.add("grape");
//		excl.add("banana");
//		excl.add("watermelon");
//		excl.add("melon");
//
//		TemplateUtil tpl = new TemplateUtil("tpl_nfruit");
//		if (LOGGER.isDebugEnabled())
//			LOGGER.debug("dataCountExcl: " + tpl.getDataCountExcl(excl));
//		for (int i = 0; i < 10; i++) {
//			String str = tpl.retrieveTemplateExcl(excl);
//			if (LOGGER.isDebugEnabled())
//				LOGGER.debug("retrieveTpl: " + str);
//		}
//		tpl.close();
//	}
}
