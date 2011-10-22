package edu.ntu.eee.csn.ism.egene.servlet;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.ConfigException;
import edu.ntu.eee.csn.ism.egene.exception.EgeneWebException;
import edu.ntu.eee.csn.ism.egene.exception.VelocityBaseException;
import edu.ntu.eee.csn.ism.egene.tpl.VelocityBaseEngine;
import edu.ntu.eee.csn.ism.egene.util.TplmDecimals;

@WebServlet(description = "Generate exam paper", urlPatterns = { "/SvGenerateExam" })
public class SvGenerateExam extends HttpServlet {

	private static final long serialVersionUID = -6443702586581697411L;
	private static Logger LOGGER = Logger.getLogger(SvFunctPingDB.class);

	private Config config = Config.getInstance();
	private String templateDirName = null;

	private class QAggregate {
		private TplmDecimals tpl = null;
		private int count = 0;

		public QAggregate(TplmDecimals tpl, int count) {
			this.tpl = tpl;
			this.count = count;
		}

		public TplmDecimals getTpl() {
			return this.tpl;
		}

		public void decrease() {
			this.count--;
		}

		public int getCount() {
			return this.count;
		}

		@Override
		public String toString() {
			return "<" + this.tpl.getId() + "," + Integer.toString(this.count)
					+ ">";
		}
	}

	@Override
	public void init() {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("init:started");

		this.templateDirName = this.config
				.getString(ConfigParam.VELOCITY_EXAMPAPER_TEMPLATE_DIR);

		File file = new File(this.config.getEgeneCfgDirName() + File.separator
				+ this.templateDirName);
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

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("init:ended");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet:started");
			LOGGER.debug("params=" + request.getParameterMap());
		}

		response.setContentType("text/xml");
		this.generateExam(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doGet:ended");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost:started");
			LOGGER.debug("params=" + request.getParameterMap());
		}

		response.setContentType("text/xml");
		this.generateExam(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:ended");

	}

	private void generateExam(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		StringWriter sw = null;
		XMLOutputFactory xof = null;
		XMLStreamWriter xtw = null;
		try {

			sw = new StringWriter();
			xof = XMLOutputFactory.newInstance();
			xtw = xof.createXMLStreamWriter(sw);
			xtw.writeStartElement("egene");
			xtw.writeStartElement("eps");

			String qtypes = request.getParameter("qtypes").trim();
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("qtypes=[" + qtypes + "]");
			if (null == qtypes)
				return;

			try {
				List<QAggregate> list = new ArrayList<QAggregate>();
				String[] arQtypes = qtypes.split("\\|");
				for (int i = 0; i < arQtypes.length; i++) {

					String[] qtypeElm = arQtypes[i].split(";");
					int id = Integer.parseInt(qtypeElm[0]);
					int count = Integer.parseInt(qtypeElm[1]);

					if (count > 0) {
						TplmDecimals tpl = TplmDecimals.getTplmDecimals(id);
						QAggregate aggr = new QAggregate(tpl, count);
						list.add(aggr);
					}
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("list=" + list);
				}

				if (null != list && list.size() > 0) {

					int idx = 0;
					while (list.size() > 0) {

						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("idx=[" + idx + "]; list=" + list);
						}
						QAggregate aggr = list.get(idx);

						TplmDecimals tpl = aggr.getTpl();
						VelocityBaseEngine ve = new VelocityBaseEngine();
						String str = null;
						if (tpl.getValueType() == 1) {
							str = ve.generateFromTemplateFile(this.templateDirName
									+ File.separator + tpl.getValue());
						} else {
							str = ve.generateFromTemplateString(tpl.getValue());
						}
						if (LOGGER.isDebugEnabled())
							LOGGER.debug("generate: [" + str + "]");

						if (!StringUtils.isEmpty(str)) {
							String[] arrStr = str.split("\\|\\|");
							xtw.writeStartElement("ep");

							xtw.writeStartElement("question");
							xtw.writeCharacters(arrStr[0].trim());
							xtw.writeEndElement();

							xtw.writeStartElement("answer");
							if (arrStr.length > 1)
								xtw.writeCharacters(arrStr[1].trim());
							xtw.writeEndElement();

							xtw.writeEndElement();
						} else {
							if (LOGGER.isEnabledFor(Level.WARN))
								LOGGER.warn("IGNORE NULL or EMPTY exam paper");
						}

						aggr.decrease();
						if (0 == aggr.getCount()) {
							list.remove(aggr);
						}

						if (list.size() > 0) {
							if (idx == list.size()) {
								idx = 0;
							} else {
								idx = (idx + 1) % list.size();
							}
						}
					}
					// list.size() == 0

				} else {
					if (LOGGER.isEnabledFor(Level.WARN))
						LOGGER.warn("IGNORE NULL or EMPTY exam paper");
				}

			} catch (RuntimeException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
			}

			xtw.writeEndElement();
			xtw.writeEndElement();
			xtw.writeEndDocument();

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("SERVLET-OUTPUT=" + sw.toString());
			response.getOutputStream().print(sw.toString());

		} catch (XMLStreamException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new EgeneWebException(e.getMessage(), e);
		} catch (VelocityBaseException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new EgeneWebException(e.getMessage(), e);
		} finally {

			try {
				xtw.flush();
				xtw.close();
				sw.close();
			} catch (XMLStreamException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
			} catch (IOException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
			}

		}

	}

}
