package edu.ntu.eee.csn.ism.egene.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.DBException;
import edu.ntu.eee.csn.ism.egene.util.Constant;
import edu.ntu.eee.csn.ism.egene.util.DBUtil;
import edu.ntu.eee.csn.ism.egene.util.ServletResponse;

@WebServlet(description = "Update topic of eGENE", urlPatterns = { "/SvUpdateTopic" })
public class SvUpdateTopic extends HttpServlet {

	private static final long serialVersionUID = -4578875876383297688L;
	private static Logger LOGGER = Logger.getLogger(SvUpdateTopic.class);

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
		this.updateTopic(request, response);
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
		this.updateTopic(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:ended");

	}

	private void updateTopic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// validate id
		String idKey = Constant.TABLE_MASTER_C_ID.toString();
		String idVal = request.getParameter(idKey);
		if (StringUtils.isEmpty(idVal) || !StringUtils.isNumeric(idVal)) {
			response.getOutputStream().print(
					ServletResponse.fail(SvUpdateTopic.class.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \"" + idKey
						+ "\", expected a number, got " + idKey + "=[" + idVal
						+ "]");

			return;
		}

		// validate topicName
		String topicNameKey = Constant.TABLE_MASTER_C_TOPIC_NAME.toString();
		String topicNameVal = request.getParameter(topicNameKey);
		if (StringUtils.isEmpty(topicNameVal)) {
			response.getOutputStream().print(
					ServletResponse.fail(SvUpdateTopic.class.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \""
						+ topicNameKey + "\", expected non-empty String, got "
						+ topicNameKey + "=[" + topicNameVal + "]");

			return;
		}

		// validate templateTable
		String templateTableKey = Constant.TABLE_MASTER_C_TEMPLATE_NAME
				.toString();
		String templateTableVal = request.getParameter(templateTableKey);
		String templateTableValPrefix = Constant.TABLE_REF_MASTER_NAME_PREFIX
				.toString();
		if (StringUtils.isEmpty(templateTableVal)
				|| !templateTableVal.toLowerCase().startsWith(
						templateTableValPrefix)) {
			response.getOutputStream().print(
					ServletResponse.fail(SvUpdateTopic.class.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \""
						+ templateTableKey
						+ "\", expected non-empty String starts with \""
						+ templateTableValPrefix + "\", got "
						+ templateTableKey + "=[" + templateTableVal + "]");

			return;
		}

		// validate description
		String descriptionKey = Constant.TABLE_MASTER_C_DESCRIPTION.toString();
		String descriptionVal = request.getParameter(descriptionKey);
		if (null == descriptionVal)
			descriptionVal = "";

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs0 = null;
		try {

			conn = dbUtil.getConnection();
			stmt = conn.createStatement();

			String sql0 = "SHOW TABLE LIKE '" + templateTableVal + "'";
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("sql: " + sql0);
			rs0 = stmt.executeQuery(sql0);
			if (!rs0.next()) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error("TABLE \"" + templateTableVal
							+ "\" is not exist");

				response.getOutputStream().print(
						ServletResponse.fail(SvUpdateTopic.class.getName()));
				return;
			}

			String tableMaster = Constant.TABLE_MASTER_NAME.toString();
			String sql = "UPDATE " + tableMaster + " SET " + topicNameKey
					+ "='" + topicNameVal + "', " + templateTableKey + "='"
					+ templateTableVal + "', " + descriptionKey + "='"
					+ descriptionVal + "' WHERE " + idKey + "=" + idVal;
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("sql: " + sql);

			int affectedRows = stmt.executeUpdate(sql);
			conn.commit();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("#affectedRows=" + affectedRows);
			}

			String res = ServletResponse.success(SvUpdateTopic.class.getName());
			response.getOutputStream().print(res);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("SERVLET-OUTPUT=" + res);

			return;

		} catch (DBException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream().print(
					ServletResponse.fail(SvUpdateTopic.class.getName()));
			return;

		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream().print(
					ServletResponse.fail(SvUpdateTopic.class.getName()));
			return;

		} finally {
			dbUtil.closeResultSet(rs0);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}
	}
}
