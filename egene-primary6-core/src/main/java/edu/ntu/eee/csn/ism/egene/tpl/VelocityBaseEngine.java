package edu.ntu.eee.csn.ism.egene.tpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.SortTool;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.VelocityBaseException;

public class VelocityBaseEngine {

	private static Logger LOGGER = Logger.getLogger(VelocityBaseEngine.class);
	private VelocityEngine ve = null;
	private Properties veProp = new Properties();
	private Config config = Config.getInstance();

	@SuppressWarnings("unused")
	private static Logger VELOCITY_LOGGER = Logger
			.getLogger("org.apache.velocity.runtime.log.SimpleLog4JLogSystem");

	public VelocityBaseEngine() throws VelocityBaseException {
		try {
			this.loadInitVE();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("VelocityEngine is initiated");
				LOGGER.debug("file.resource.loader.path=["
						+ Velocity.getProperty("file.resource.loader.path")
						+ "]");
			}
		} catch (Exception e) {
			throw new VelocityBaseException(e.getMessage(), e);
		}

	}

	private void loadInitVE() {
		String velocityPropFile = this.config.getEgeneCfgDirName()
				+ File.separator
				+ this.config.getString(ConfigParam.VELOCITY_PROPERTIES_FILE);
		File file = new File(velocityPropFile);
		if (!file.exists()) {
			if (LOGGER.isEnabledFor(Level.WARN)) {
				LOGGER.warn("CAN'T find "
						+ ConfigParam.VELOCITY_PROPERTIES_FILE.getName() + "=["
						+ file.getAbsolutePath() + "]");
				LOGGER.warn("USE default VELOCITY PROPERTIES");
			}
			return;
		}

		try {
			this.veProp.load(new FileInputStream(file));

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FOUND "
						+ ConfigParam.VELOCITY_PROPERTIES_FILE.getName() + "=["
						+ file.getAbsolutePath() + "]");
				LOGGER.debug("LOAD VELOCITY_PROPERTIES "
						+ ConfigParam.VELOCITY_PROPERTIES_FILE.getName() + "=["
						+ file.getAbsolutePath() + "]");

			}

			// Enumeration<Object> enumeration = properties.keys();
			// while (enumeration.hasMoreElements()) {
			// String key = (String) enumeration.nextElement();
			// String val = (String) properties.get(key);
			// this.ve.setProperty(key, val);
			// }

			this.veProp.setProperty("runtime.log.logsystem.class",
					"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
			this.veProp.setProperty("runtime.log.logsystem.log4j.category",
					"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");

			this.ve = new VelocityEngine(this.veProp);

		} catch (FileNotFoundException e) {
			if (LOGGER.isEnabledFor(Level.WARN)) {
				LOGGER.warn(e.getMessage(), e);
				LOGGER.warn("USE default VELOCITY PROPERTIES");
			}
			return;
		} catch (IOException e) {
			if (LOGGER.isEnabledFor(Level.WARN)) {
				LOGGER.warn(e.getMessage(), e);
				LOGGER.warn("USE default VELOCITY PROPERTIES");
			}
			return;
		}

	}

	public String generateFromTemplateString(String tplString)
			throws VelocityBaseException {
		return this.generateFromTemplateString(tplString, null);
	}

	public String generateFromTemplateString(String tplString,
			VelocityContext context) throws VelocityBaseException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("generateFromTemplateString=[" + tplString + "]");

		if (null == tplString) {
			return null;
		}

		if (null == context) {
			context = new VelocityContext();
		}

		String res = null;
		try {

			context.put("nl", "\n");
			context.put("cr", "\r");
			context.put("vNumberGenerator", new VNumberGenerator());
			context.put("vTemplateGenerator", new VTemplateGenerator());
			context.put("vNounFormatter", new VNounFormatter());
			context.put("vVerbFormatter", new VVerbFormatter());
			context.put("vOptGenerator", new VOptGenerator());
			context.put("sortTool", new SortTool());
			context.put("mathTool", new MathTool());
			context.put("escapeTool", new EscapeTool());
			context.put("vFormatter", new VFormatter());

			StringWriter writer = new StringWriter();
			this.ve.evaluate(context, writer, "egene.vm", tplString);

			res = writer.toString();
			writer.close();

		} catch (ResourceNotFoundException e) {
			throw new VelocityBaseException(e.getMessage(), e);
		} catch (ParseErrorException e) {
			throw new VelocityBaseException(e.getMessage(), e);
		} catch (Exception e) {
			throw new VelocityBaseException(e.getMessage(), e);
		}

		return res;

	}

	public String generateFromTemplateFile(String templateFileName)
			throws VelocityBaseException {
		return this.generateFromTemplateFile(templateFileName, null);
	}

	public String generateFromTemplateFile(String templateFileName,
			VelocityContext context) throws VelocityBaseException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("generateFromTemplateFile=[" + templateFileName + "]");

		if (null == templateFileName) {
			return null;
		}

		if (null == context) {
			context = new VelocityContext();
		}


		String res = null;
		try {

			context.put("nl", "\n");
			context.put("cr", "\r");
			context.put("vNumberGenerator", new VNumberGenerator());
			context.put("vTemplateGenerator", new VTemplateGenerator());
			context.put("vNounFormatter", new VNounFormatter());
			context.put("vVerbFormatter", new VVerbFormatter());
			context.put("vOptGenerator", new VOptGenerator());
			context.put("sortTool", new SortTool());
			context.put("mathTool", new MathTool());
			context.put("escapeTool", new EscapeTool());
			context.put("vFormatter", new VFormatter());

			StringWriter writer = new StringWriter();

			Template t = this.ve.getTemplate(templateFileName);
			t.merge(context, writer);
			res = writer.toString();
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("RES: " + res);
			writer.close();

		} catch (ResourceNotFoundException e) {
			throw new VelocityBaseException(e.getMessage(), e);
		} catch (ParseErrorException e) {
			throw new VelocityBaseException(e.getMessage(), e);
		} catch (Exception e) {
			throw new VelocityBaseException(e.getMessage(), e);
		}

		return res;

	}

}
