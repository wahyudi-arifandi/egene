package edu.ntu.eee.csn.ism.egene.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

import edu.ntu.eee.csn.ism.egene.exception.DBException;
import edu.ntu.eee.csn.ism.egene.util.Constant;
import edu.ntu.eee.csn.ism.egene.util.DBUtil;
import edu.ntu.eee.csn.ism.egene.util.ServletResponse;

@WebServlet(description = "Get master topics of eGENE", urlPatterns = { "/SvGetTopics" })
public class SvGetTopics extends HttpServlet {

	private static final long serialVersionUID = -4578875876383297688L;
	private static Logger LOGGER = Logger.getLogger(SvGetTopics.class);

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
		this.getTopics(request, response);
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
		this.getTopics(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:ended");

	}

	private void getTopics(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String selectStartRowKey = Constant.TABLE_SELECT_START_ROW.toString();
		String selectStartRowVal = request.getParameter(selectStartRowKey);
		if (StringUtils.isEmpty(selectStartRowVal)
				|| !StringUtils.isNumeric(selectStartRowVal)
				|| Integer.parseInt(selectStartRowVal) < 0) {
			response.getOutputStream().print(
					ServletResponse.fail(SvGetTopics.class.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \""
						+ selectStartRowVal + "\", expected number >= 0, got "
						+ selectStartRowKey + "=[" + selectStartRowVal + "]");

			return;
		}

		String selectCountKey = Constant.TABLE_SELECT_COUNT.toString();
		String selectCountVal = request.getParameter(selectCountKey);
		if (StringUtils.isEmpty(selectCountVal)
				|| !StringUtils.isNumeric(selectCountVal)
				|| Integer.parseInt(selectCountVal) < -1) {
			response.getOutputStream().print(
					ServletResponse.fail(SvGetTopics.class.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \""
						+ selectCountVal + "\", expected number >= -1, got "
						+ selectCountKey + "=[" + selectCountVal + "]");

			return;
		}

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs0 = null;
		ResultSet rs = null;
		try {
			String tableMasterName = Constant.TABLE_MASTER_NAME.toString();

			StringWriter sw = new StringWriter();
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xtw = null;
			xtw = xof.createXMLStreamWriter(sw);
			xtw.writeStartElement(Constant.TOPICS.toString());
			xtw.writeStartElement(Constant.TOPIC.toString());

			conn = dbUtil.getConnection();
			stmt = conn.createStatement();

			String sql0 = "SELECT COUNT(*) FROM " + tableMasterName;
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("sql: " + sql0);
			rs0 = stmt.executeQuery(sql0);
			if (rs0.next()) {
				xtw.writeStartElement(Constant.ROW_COUNT.toString());
				xtw.writeCharacters(rs0.getString(1));
				xtw.writeEndElement();
			}

			String idKey = Constant.TABLE_MASTER_C_ID.toString();
			String topicNameKey = Constant.TABLE_MASTER_C_TOPIC_NAME.toString();

			String templateTableKey = Constant.TABLE_MASTER_C_TOPIC_NAME
					.toString();
			String descriptionKey = Constant.TABLE_MASTER_C_DESCRIPTION
					.toString();

			String sql = "";
			int iSelectCountVal = Integer.parseInt(selectCountVal);
			if (iSelectCountVal >= 0) {
				sql = "SELECT " + idKey + ", " + topicNameKey + ", "
						+ templateTableKey + ", " + descriptionKey + " FROM "
						+ tableMasterName + " LIMIT " + selectStartRowVal
						+ ", " + selectCountVal;
			} else {
				sql = "SELECT " + idKey + ", " + topicNameKey + ", "
						+ templateTableKey + ", " + descriptionKey + " FROM "
						+ tableMasterName;
			}
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("sql: " + sql);

			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String idVal = rs.getString(1);
				String topicNameVal = rs.getString(2);
				String templatesTableVal = rs.getString(3);
				String descriptionVal = rs.getString(4);

				xtw.writeStartElement(idKey);
				xtw.writeCharacters(idVal);
				xtw.writeEndElement();

				xtw.writeStartElement(topicNameKey);
				xtw.writeCharacters(topicNameVal);
				xtw.writeEndElement();

				xtw.writeStartElement(templateTableKey);
				xtw.writeCharacters(templatesTableVal);
				xtw.writeEndElement();

				xtw.writeStartElement(descriptionKey);
				xtw.writeCharacters(descriptionVal);
				xtw.writeEndElement();
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
			response.getOutputStream().print(
					ServletResponse.fail(SvGetTopics.class.getName()));
			return;

		} catch (DBException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream().print(
					ServletResponse.fail(SvGetTopics.class.getName()));
			return;

		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream().print(
					ServletResponse.fail(SvGetTopics.class.getName()));
			return;

		} finally {
			dbUtil.closeResultSet(rs0);
			dbUtil.closeResultSet(rs);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}
	}
}
