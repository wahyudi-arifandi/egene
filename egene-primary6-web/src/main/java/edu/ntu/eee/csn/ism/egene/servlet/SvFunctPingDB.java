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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.DBException;
import edu.ntu.eee.csn.ism.egene.util.DBUtil;

/**
 * Servlet implementation class SvDBCheck
 */
@WebServlet(description = "Check liveliness connection to database", urlPatterns = { "/SvFunctPingDB" })
public class SvFunctPingDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(SvFunctPingDB.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvFunctPingDB() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doGet:start");
		response.getOutputStream().print(
				"getenv" + System.getenv("egene.cfgdir"));
		response.getOutputStream().print(
				"getprop" + System.getProperty("egene.cfgdir"));
		response.getOutputStream().println(
				"cp" + System.getProperty("java.class.path"));

		this.checkDb(request, response);
		this.checkDb(request, response);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doGet:OK");
		response.getOutputStream().println("doGet:OK");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:start");
		response.getOutputStream().println(
				"getenv" + System.getenv("epgen.cfgdir"));
		response.getOutputStream().println(
				"getprop" + System.getProperty("epgen.cfgdir"));
		response.getOutputStream().println(
				"cp" + System.getProperty("java.class.path"));
		this.checkDb(request, response);
		this.checkDb(request, response);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:OK");
		response.getOutputStream().print("doPost:OK");

	}

	private void checkDb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = dbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from db_check");

			while (rs.next()) {
				String foo = rs.getString(1);
				String bar = rs.getString(2);
				String str = "foo=" + foo + "; bar=" + bar;
				response.getOutputStream().println(str);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug(str);
			}

		} catch (DBException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream().println("ERROR: " + e.getMessage());
		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			response.getOutputStream().println("ERROR: " + e.getMessage());
		} finally {
			dbUtil.closeResultSet(rs);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}

	}

}
