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

@WebServlet(description = "Insert data reference of eGENE", urlPatterns = { "/SvInsertDataReference" })
public class SvInsertDataReference extends HttpServlet {

	private static final long serialVersionUID = -4578875876383297688L;
	private static Logger LOGGER = Logger
			.getLogger(SvInsertDataReference.class);

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
		this.insertDataReference(request, response);
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
		this.insertDataReference(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:ended");

	}

	private void insertDataReference(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// validate value
		String valueKey = Constant.TABLE_REF_C_VALUE.toString();
		String valueVal = request.getParameter(valueKey);
		if (StringUtils.isEmpty(valueVal)) {
			response.getOutputStream()
					.print(ServletResponse.fail(SvInsertDataReference.class
							.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \""
						+ valueKey + "\", expected non-empty String, got "
						+ valueKey + "=[" + valueVal + "]");

			return;
		}

		// validate tableName
		String tableNameKey = Constant.HTTP_REQUEST_PARAM_TABLE_NAME_KEY
				.toString();
		String tableNameVal = request.getParameter(tableNameKey);
		String tableNamePrefix1 = Constant.TABLE_REF_NOUN_NAME_PREFIX.toString();
		String tableNamePrefix2 = Constant.TABLE_REF_VERB_NAME_PREFIX.toString();
		String tableNamePrefix3 = Constant.TABLE_REF_OTHER_NAME_PREFIX.toString();
		if (StringUtils.isEmpty(tableNameVal)
				|| (!tableNameVal.toLowerCase().startsWith(tableNamePrefix1)
						&& !tableNameVal.toLowerCase().startsWith(
								tableNamePrefix2) && !tableNameVal
						.toLowerCase().startsWith(tableNamePrefix3))) {
			response.getOutputStream()
					.print(ServletResponse.fail(SvInsertDataReference.class
							.getName()));
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error("INVALID request parameter value for \""
						+ tableNameKey
						+ "\", expected non-empty String starts with \""
						+ tableNamePrefix1 + "\", \"" + tableNamePrefix2
						+ ", or " + tableNamePrefix3 + "\", got "
						+ tableNameKey + "=[" + tableNameVal + "]");

			return;
		}

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = dbUtil.getConnection();
			stmt = conn.createStatement();

			String sql = "INSERT INTO " + tableNameVal + " ('" + valueVal
					+ "')";
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("sql: " + sql);

			int affectedRows = stmt.executeUpdate(sql);
			conn.commit();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("#affectedRows=" + affectedRows);
			}

			String res = ServletResponse.success(SvInsertDataReference.class
					.getName());
			response.getOutputStream().print(res);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("SERVLET-OUTPUT=" + res);

			return;

		} catch (DBException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream()
					.print(ServletResponse.fail(SvInsertDataReference.class
							.getName()));
			return;

		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream()
					.print(ServletResponse.fail(SvInsertDataReference.class
							.getName()));
			return;

		} finally {
			dbUtil.closeResultSet(rs);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}
	}
}
