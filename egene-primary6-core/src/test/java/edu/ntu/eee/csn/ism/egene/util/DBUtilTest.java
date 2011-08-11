package edu.ntu.eee.csn.ism.egene.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.util.DBUtil;

public class DBUtilTest extends TestCase {

	private static Logger LOGGER = Logger.getLogger(DBUtilTest.class);

	/**
	 * Constructor
	 */
	public DBUtilTest() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            test name
	 */
	public DBUtilTest(String name) {
		super(name);
	}

	/**
	 * Declares the test suite.
	 * 
	 * @return the test suite.
	 */
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite(DBUtilTest.class);
		return testSuite;
	}

	public void test1() {
		DBUtil dbUtil = DBUtil.getInstance();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Throwable t = null;
		try {

			conn = dbUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select 1 from dual");

			while (rs.next()) {
				String str = rs.getString(1);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("rs=[" + str + "]");
			}
		} catch (Throwable x) {
			x.printStackTrace();
			t = x;
		} finally {
			dbUtil.closeResultSet(rs);
			dbUtil.closeStatement(stmt);
			dbUtil.closeConnection(conn);
		}
		assertNull(t);
	}
}
