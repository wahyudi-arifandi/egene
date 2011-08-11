package edu.ntu.eee.csn.ism.egene.config;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test for class Config
 * 
 * @author arif
 * 
 */
public class ConfigTest extends TestCase {

	/**
	 * Constructor
	 */
	public ConfigTest() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            test name
	 */
	public ConfigTest(String name) {
		super(name);
	}

	/**
	 * Declares the test suite.
	 * 
	 * @return the test suite.
	 */
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite(ConfigTest.class);
		return testSuite;
	}

	public void test1() {
		Config config = Config.getInstance();
		assertNotNull(config);
		
		ConfigParam[] configParams = ConfigParam.values();
		assertNotNull(configParams);
		for (int i = 0; i < configParams.length; i++) {
			String val = config.getString(configParams[i]);
			assertNotNull(val);
		}
	}

}
