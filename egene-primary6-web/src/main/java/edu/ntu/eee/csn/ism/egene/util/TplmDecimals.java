package edu.ntu.eee.csn.ism.egene.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.DBException;

public class TplmDecimals {

	private static Logger LOGGER = Logger.getLogger(TplmDecimals.class);

	private int id = -1;
	private String value = null;
	private int valueType = -1;
	private String qestionType = null;

	public TplmDecimals(int id, String value, int valueType, String questionType) {
		this.id = id;
		this.value = value;
		this.valueType = valueType;
		this.qestionType = questionType;
	}

	public int getId() {
		return this.id;
	}

	public String getValue() {
		return this.value;
	}

	public int getValueType() {
		return this.valueType;
	}

	public String getQuestionType() {
		return this.qestionType;
	}

	public static TplmDecimals getTplmDecimals(int id) {
		TplmDecimals tplmDecimals = null;

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT value, value_type, question_type FROM tplm_decimals WHERE active=1 AND id="
					+ id;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("sql: " + sql);
			}
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String value = rs.getString(1).trim();
				int valueType = rs.getInt(2);
				String questionType = rs.getString(3).trim();

				tplmDecimals = new TplmDecimals(id, value, valueType,
						questionType);
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

		return tplmDecimals;

	}

	public static List<TplmDecimals> getTplmDecimalsList() {
		List<TplmDecimals> list = new ArrayList<TplmDecimals>();

		DBUtil dbUtil = DBUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT id, value, value_type, question_type FROM tplm_decimals WHERE active=1";
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("sql: " + sql);
			}
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String value = rs.getString(2).trim();
				int valueType = rs.getInt(3);
				String questionType = rs.getString(4).trim();

				list.add(new TplmDecimals(id, value, valueType, questionType));
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

}
