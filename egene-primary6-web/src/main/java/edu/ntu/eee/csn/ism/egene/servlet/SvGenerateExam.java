package edu.ntu.eee.csn.ism.egene.servlet;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.ConfigException;
import edu.ntu.eee.csn.ism.egene.exception.DBException;
import edu.ntu.eee.csn.ism.egene.exception.EgeneWebException;
import edu.ntu.eee.csn.ism.egene.exception.VelocityBaseException;
import edu.ntu.eee.csn.ism.egene.tpl.VelocityBaseEngine;
import edu.ntu.eee.csn.ism.egene.util.Constant;
import edu.ntu.eee.csn.ism.egene.util.DBUtil;
import edu.ntu.eee.csn.ism.egene.util.NumberUtil;
import edu.ntu.eee.csn.ism.egene.util.SeedRoot;

@WebServlet(description = "Generate exam paper", urlPatterns = { "/SvGenerateExam" })
public class SvGenerateExam extends HttpServlet {

	private static final long serialVersionUID = -6443702586581697411L;
	private static Logger LOGGER = Logger.getLogger(SvFunctPingDB.class);

	private Config config = Config.getInstance();
	private String templateDirName = null;

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

	private int getExamPaperCount(String epCount) {

		int count = 0;
		try {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("epCount=[" + epCount + "]");
			count = Integer.parseInt(epCount);
		} catch (RuntimeException e) {
			if (LOGGER.isEnabledFor(Level.WARN)) {
				LOGGER.warn(e.getMessage());
			}
		} finally {
			if (count == 0) {
				count = this.config
						.getInt(ConfigParam.EGENE_DEFAULT_GENERATED_QUESTION_COUNT);
				if (LOGGER.isEnabledFor(Level.WARN)) {
					LOGGER.warn("USE DEFAULT epCount from "
							+ ConfigParam.EGENE_DEFAULT_GENERATED_QUESTION_COUNT
									.getName() + "=" + count);
				}
			}
		}
		return count;
	}

	private List<SeedRoot> getTemplateFile(String qtype) {
		List<SeedRoot> list = new ArrayList<SeedRoot>();

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			stmt = conn.createStatement();

			String sql = null;
			if ("review".equalsIgnoreCase(qtype)) {
				sql = "SELECT "
						+ Constant.TABLE_REF_C_VALUE.toString()
						+ ", "
						+ Constant.TABLE_REF_C_VALUE_TYPE
						+ " FROM tplm_decimals WHERE QUESTION_TYPE IN ('psp25.1.vm', 'psp25.2.vm', 'psp25.3.vm', 'psp26.1.vm', 'psp26.2.vm', 'psp27.1.vm', 'psp27.2.vm', 'psp28.1.vm', 'psp29.1.vm')";
			} else {
				sql = "SELECT " + Constant.TABLE_REF_C_VALUE.toString() + ", "
						+ Constant.TABLE_REF_C_VALUE_TYPE
						+ " FROM tplm_decimals WHERE QUESTION_TYPE='" + qtype
						+ "'";
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("sql: " + sql);
			}
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String value = rs.getString(1).trim();
				int valueType = rs.getInt(2);

				list.add(new SeedRoot(value, valueType));
			}
		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
		} catch (DBException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
		} finally {
			dbUtil.closeResultSet(rs);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}

		return list;
	}

	private void generateExam(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			StringWriter sw = new StringWriter();
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xtw = null;
			xtw = xof.createXMLStreamWriter(sw);
			xtw.writeStartElement("egene");
			xtw.writeStartElement("eps");

			int epCount = this.getExamPaperCount(request
					.getParameter("epcount"));
			String qtype = request.getParameter("qtype");
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("qtype=[" + qtype + "]; epcount=[" + epCount + "]");

			List<SeedRoot> tplFile = this.getTemplateFile(qtype);

			if (tplFile.size() > 0) {
				VelocityBaseEngine ve = new VelocityBaseEngine();
				// String file = "unittest.vm";
				for (int i = 0; i < epCount; i++) {

					int idx = NumberUtil.generateInt(0, tplFile.size() - 1);
					SeedRoot sr = tplFile.get(idx);
					String str = null;
					if (sr.getValueType() == 1) {
						str = ve.generateFromTemplateFile(this.templateDirName
								+ File.separator + sr.getValue());
					} else {
						str = ve.generateFromTemplateString(sr.getValue());
					}
					if (LOGGER.isDebugEnabled())
						LOGGER.debug("generate: [" + str + "]");

					if (null != str && str.length() > 0) {
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
				}
			}

			xtw.writeEndElement();
			xtw.writeEndElement();
			xtw.writeEndDocument();
			xtw.flush();
			xtw.close();

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
		}

	}

}
