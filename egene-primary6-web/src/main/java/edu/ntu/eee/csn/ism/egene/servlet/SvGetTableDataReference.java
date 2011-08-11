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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.DBException;
import edu.ntu.eee.csn.ism.egene.exception.EgeneWebException;
import edu.ntu.eee.csn.ism.egene.util.Constant;
import edu.ntu.eee.csn.ism.egene.util.DBUtil;

@WebServlet(description = "Get table data reference of eGENE", urlPatterns = { "/SvGetTableDataReference" })
public class SvGetTableDataReference extends HttpServlet {

	private static final long serialVersionUID = -4578875876383297688L;
	private static Logger LOGGER = Logger
			.getLogger(SvGetTableDataReference.class);

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
		this.getTable(request, response);
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
		this.getTable(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:ended");

	}

	private void getTable(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			StringWriter sw = new StringWriter();
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xtw = null;
			xtw = xof.createXMLStreamWriter(sw);
			xtw.writeStartElement(Constant.TOPICS.toString());

			conn = dbUtil.getConnection();
			stmt = conn.createStatement();
			
//			String sql0 = "SELECT COUNT(*) FROM " + Constant;
			
			String sql = "show tables";
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("sql: " + sql);

			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String tblName = rs.getString(1);

				xtw.writeStartElement("table");
				xtw.writeCharacters(tblName);
				xtw.writeEndElement();

			}

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
		} catch (DBException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new EgeneWebException(e.getMessage(), e);
		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new EgeneWebException(e.getMessage(), e);
		} finally {
			dbUtil.closeResultSet(rs);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}
	}

}
