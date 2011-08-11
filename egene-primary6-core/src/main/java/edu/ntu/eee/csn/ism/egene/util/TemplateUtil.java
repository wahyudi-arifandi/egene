package edu.ntu.eee.csn.ism.egene.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.DBException;
import edu.ntu.eee.csn.ism.egene.exception.NumberUtilException;
import edu.ntu.eee.csn.ism.egene.exception.TemplateUtilException;

public class TemplateUtil {

	private static Logger LOGGER = Logger.getLogger(TemplateUtil.class);

	private DBUtil dbUtil = DBUtil.getInstance();
	private Connection conn = null;
	private Statement stmt = null;
	private String tableName = null;

	public TemplateUtil(String tableName) throws TemplateUtilException {
		this.tableName = tableName;

		try {
			this.conn = this.dbUtil.getConnection();
			this.stmt = this.conn.createStatement();
			// this.stmt = this.conn.prepareStatement("");
		} catch (SQLException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} catch (DBException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		}
	}

	public int getDataCount() throws TemplateUtilException {
		int dataCount = 0;

		ResultSet rs = null;
		try {
			String query = "SELECT COUNT(*) FROM " + this.tableName;
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("query=" + query);

			rs = this.stmt.executeQuery(query);
			if (rs.next()) {
				dataCount = rs.getInt(1);
			} else {
				throw new TemplateUtilException("ResultSet has NO rows");
			}

		} catch (SQLException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} finally {
			this.dbUtil.closeResultSet(rs);
		}

		return dataCount;

	}

	public int getDataCountExcl(List<String> excl) throws TemplateUtilException {
		if (null == excl || excl.size() == 0)
			return this.getDataCount();

		StringBuffer sbExcl = new StringBuffer("(");
		for (int i = 0; i < excl.size(); i++) {
			sbExcl.append("'");
			sbExcl.append(excl.get(i).trim());
			sbExcl.append("'");
			if (i != excl.size() - 1)
				sbExcl.append(",");
		}
		sbExcl.append(")");

		int dataCount = 0;

		ResultSet rs = null;
		try {
			String query = "SELECT COUNT(*) FROM " + this.tableName
					+ " WHERE VALUE NOT IN " + sbExcl.toString();
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("query=" + query);

			rs = this.stmt.executeQuery(query);
			if (rs.next()) {
				dataCount = rs.getInt(1);
			} else {
				throw new TemplateUtilException("ResultSet has NO rows");
			}

		} catch (SQLException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} finally {
			this.dbUtil.closeResultSet(rs);
		}

		return dataCount;

	}

	public String retrieveTemplate() throws TemplateUtilException {
		String str = null;

		int dataCount = this.getDataCount();
		if (dataCount < 1) {
			return null;
		}

		ResultSet rs = null;
		try {
			int rowNum = NumberUtil.generateInt(0, dataCount - 1);
			String query = "SELECT * FROM " + this.tableName + " LIMIT "
					+ Integer.toString(rowNum) + ", 1";
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("query=" + query);

			rs = this.stmt.executeQuery(query);
			if (rs.next()) {
				str = rs.getString(2);
			} else {
				throw new TemplateUtilException("ResultSet has NO rows");
			}

		} catch (SQLException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} catch (NumberUtilException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} finally {
			this.dbUtil.closeResultSet(rs);
		}

		return str;
	}

	public String retrieveTemplateExcl(List<String> excl)
			throws TemplateUtilException {
		String str = null;

		int dataCountIncl = this.getDataCountExcl(excl);
		if (dataCountIncl < 1)
			throw new TemplateUtilException(
					"NO possible int to be generated. inclSet=" + dataCountIncl);

		StringBuffer sbExcl = new StringBuffer("(");
		for (int i = 0; i < excl.size(); i++) {
			sbExcl.append("'");
			sbExcl.append(excl.get(i).trim());
			sbExcl.append("'");
			if (i != excl.size() - 1)
				sbExcl.append(",");
		}
		sbExcl.append(")");

		ResultSet rs = null;
		try {
			int rowNum = NumberUtil.generateInt(0, dataCountIncl - 1);
			String query = "SELECT * FROM " + this.tableName
					+ " WHERE VALUE NOT IN " + sbExcl.toString() + " LIMIT "
					+ Integer.toString(rowNum) + ", 1";
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("query=" + query);

			rs = this.stmt.executeQuery(query);
			if (rs.next()) {
				str = rs.getString(2);
			} else {
				throw new TemplateUtilException("ResultSet has NO rows");
			}

		} catch (SQLException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} catch (NumberUtilException e) {
			throw new TemplateUtilException(e.getMessage(), e);
		} finally {
			this.dbUtil.closeResultSet(rs);
		}

		return str;
	}

	public boolean ping() {
		boolean healthy = true;
		String str = null;

		ResultSet rs = null;
		try {
			String query = "SELECT 1 FROM DUAL";
			rs = this.stmt.executeQuery(query);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("query=" + query);

			while (rs.next()) {
				str = rs.getString(1);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("rs=[" + str + "]");
			}

		} catch (SQLException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			healthy = false;
		} catch (NumberUtilException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			healthy = false;
		} finally {
			this.dbUtil.closeResultSet(rs);
		}

		return healthy;
	}

	public void close() {
		this.dbUtil.closeStatement(this.stmt);
		this.dbUtil.closeConnection(this.conn);
	}

}
