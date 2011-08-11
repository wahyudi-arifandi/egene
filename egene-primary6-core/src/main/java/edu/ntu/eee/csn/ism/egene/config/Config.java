package edu.ntu.eee.csn.ism.egene.config;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.ConfigException;

/**
 * Class for accessing configuration file epgen.properties
 * 
 * @author arif
 * 
 */
public class Config {

	/**
	 * Logger
	 */
	private static Logger LOGGER = Logger.getLogger(Config.class);

	/**
	 * Configuration file name
	 */
	public static final String CONFIG_PROPERTIES_FILE_NAME = "egene.properties";

	/**
	 * Variable for binding the configuration file
	 */
	PropertiesConfiguration binding = null;

	private File egeneCfgDir = null;

	/**
	 * Instance of singleton Config
	 */
	private static Config config = null;

	/**
	 * Constructor
	 */
	private Config() {

		// check egeneCfgDir
		String egeneCfgDirName = System.getProperty("egene.cfgdir");
		if (null == egeneCfgDirName) {
			String msg = "CAN'T find egene-cfgdir at the system property. "
					+ "Please check that Tomcat startup script includes -Degene-cfgdir=<path-to-egene-cfgdir>";
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(msg);
			throw new ConfigException(msg);
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("FOUND egene-cfgdir=[" + egeneCfgDirName + "]");
		}
		this.egeneCfgDir = new File(egeneCfgDirName);
		if (!this.egeneCfgDir.exists()) {
			String msg = "egene-cfgdir=[" + this.egeneCfgDir.getAbsolutePath()
					+ "] is NOT EXIST.";
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(msg);
			throw new ConfigException(msg);
		}

		// check egeneCfgFile
		String egeneCfgFileName = this.egeneCfgDir + File.separator
				+ CONFIG_PROPERTIES_FILE_NAME;
		File egeneCfgFile = new File(egeneCfgFileName);
		if (!egeneCfgFile.exists()) {
			throw new ConfigException("CAN'T find properties-file=["
					+ egeneCfgFile.getAbsolutePath() + "]");
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("FOUND properties-file=["
						+ egeneCfgFile.getAbsolutePath()
						+ "] at the classpath.");
		}

		try {
			this.binding = new PropertiesConfiguration(
					egeneCfgFile.getAbsolutePath());
			this.binding
					.setReloadingStrategy(new FileChangedReloadingStrategy());

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("INSTANTIATE: " + Config.class);

		} catch (ConfigurationException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new ConfigException(e.getMessage(), e);
		}
	}

	/**
	 * Get instance of singleton Config
	 * 
	 * @return instance of singleton Config
	 */
	public static Config getInstance() {
		if (null == Config.config) {
			synchronized (Config.class) {
				if (null == Config.config)
					Config.config = new Config();
			}
		}
		return Config.config;
	}

	public String getEgeneCfgDirName() {
		return this.egeneCfgDir.getAbsolutePath();
	}

	/**
	 * Get configuration value of respective parameter name as String
	 * 
	 * @param param
	 *            Name of parameter
	 * @return configuration value
	 */
	public String getString(ConfigParam param) {
		if (null == param)
			throw new ConfigException("INVALID configParam. RECEIVED [NULL]");

		return this.binding.getString(param.getName(), param.getDefVal());
	}

	/**
	 * Get configuration value of respective parameter name as integer
	 * 
	 * @param param
	 *            Name of parameter
	 * @return configuration value
	 */
	public int getInt(ConfigParam param) {
		if (null == param)
			throw new ConfigException("INVALID configParam. RECEIVED [NULL]");

		return this.binding.getInt(param.getName(),
				Integer.parseInt(param.getDefVal()));
	}

	/**
	 * Get configuration value of respective parameter name as boolean
	 * 
	 * @param param
	 *            Name of parameter
	 * @return configuration value
	 */
	public boolean getBoolean(ConfigParam param) {
		if (null == param)
			throw new ConfigException("INVALID configParam. RECEIVED [NULL]");

		return this.binding.getBoolean(param.getName(),
				Boolean.parseBoolean(param.getDefVal()));
	}

	/**
	 * Get configuration value of respective parameter name as double
	 * 
	 * @param param
	 *            Name of parameter
	 * @return configuration value
	 */
	public double getDouble(ConfigParam param) {
		if (null == param)
			throw new ConfigException("INVALID configParam. RECEIVED [NULL]");

		return this.binding.getDouble(param.getName(),
				Double.parseDouble(param.getDefVal()));
	}

	/**
	 * Get configuration value of respective parameter name as float
	 * 
	 * @param param
	 *            Name of parameter
	 * @return configuration value
	 */
	public double getFloat(ConfigParam param) {
		if (null == param)
			throw new ConfigException("INVALID configParam. RECEIVED [NULL]");

		return this.binding.getFloat(param.getName(),
				Float.parseFloat(param.getDefVal()));
	}

}
