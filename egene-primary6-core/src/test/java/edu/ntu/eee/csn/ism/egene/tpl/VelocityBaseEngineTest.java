package edu.ntu.eee.csn.ism.egene.tpl;

import java.io.File;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.ConfigException;
import edu.ntu.eee.csn.ism.egene.exception.VelocityBaseException;

public class VelocityBaseEngineTest extends TestCase {

	static {
		System.out.println(System.getProperty("egene.cfgdir"));
	}
	private static Logger LOGGER = Logger
			.getLogger(VelocityBaseEngineTest.class);
	private String templateDirName = null;
	private Config config = Config.getInstance();

	/**
	 * Constructor
	 */
	public VelocityBaseEngineTest() {
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
	public VelocityBaseEngineTest(String name) {
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
		TestSuite testSuite = new TestSuite(VelocityBaseEngineTest.class);
		return testSuite;
	}

	@Override
	public void setUp() {
		this.templateDirName = this.config
				.getString(ConfigParam.VELOCITY_EXAMPAPER_TEMPLATE_DIR);

		File file = new File(this.config.getEgeneCfgDirName()
				+ File.separator + this.templateDirName);
		if (!file.exists()) {
			throw new ConfigException("CAN'T find "
					+ ConfigParam.VELOCITY_EXAMPAPER_TEMPLATE_DIR.getName()
					+ "=[" + file.getAbsolutePath() + "]");
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("FOUND "
						+ ConfigParam.VELOCITY_EXAMPAPER_TEMPLATE_DIR.getName()
						+ "=[" + file.getAbsolutePath() + "] at the classpath.");
		}
	}

	public void testGenerateTemplateFromFile() {

		Throwable ex1 = null;
		try {
			VelocityBaseEngine ve = new VelocityBaseEngine();

			String file = "psp23.2.vm";
			// String file = "unittest.vm";
			String str = ve.generateFromTemplateFile(this.templateDirName
					+ File.separator + file, null);

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("generate1: [" + str + "]");

		} catch (VelocityBaseException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			ex1 = e;
		}
		assertNull(ex1);

	}

	public void testGenerateTemplateFromString() {

		Throwable ex1 = null;
		try {
			VelocityBaseEngine ve = new VelocityBaseEngine();

			String tplStr1 = "integer: #genInt(50,60)";
			String genStr1 = ve.generateFromTemplateString(tplStr1, null);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("generate1: [" + genStr1 + "]");

			String tplStr2 = "integer: #genDouble(50,60,2)";
			String genStr2 = ve.generateFromTemplateString(tplStr2, null);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("generate1: [" + genStr2 + "]");

		} catch (VelocityBaseException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			ex1 = e;
		}
		assertNull(ex1);

	}
}
