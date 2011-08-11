package edu.ntu.eee.csn.ism.egene.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.ConfigException;
import edu.ntu.eee.csn.ism.egene.exception.DBException;

public class DBUtil {

	/**
	 * Logger
	 */
	private static Logger LOGGER = Logger.getLogger(DBUtil.class);

	private static String ENV_NAMING_CONTEXT = "java:comp/env";
	private static String DB_RES_NAMING_CONTEXT = "jdbc/egenedb";

	private Config config = Config.getInstance();

	private Context initCtx = null;
	private Context envCtx = null;
	private DataSource ds = null;

	/**
	 * Instance of singleton Config
	 */
	private static DBUtil dbUtil = null;

	/**
	 * Constructor
	 */
	private DBUtil() {
		this.initPool();
	}

	private void initPool() {

		if (this.config.getBoolean(ConfigParam.JDBC_CONN_USE_JNDI_LOOKUP)) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Initiate DataSource connectivity POOL");

			try {

				// Obtain our environment naming context
				this.initCtx = new InitialContext();
				this.envCtx = (Context) this.initCtx.lookup(ENV_NAMING_CONTEXT);

				// Look up our data source
				this.ds = (DataSource) this.envCtx
						.lookup(DB_RES_NAMING_CONTEXT);

			} catch (NamingException e) {

				if (LOGGER.isEnabledFor(Level.FATAL))
					LOGGER.fatal(e.getMessage(), e);

				this.closeContext(envCtx);
				this.closeContext(initCtx);

				throw new ConfigException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Get instance of singleton Config
	 * 
	 * @return instance of singleton Config
	 */
	public static DBUtil getInstance() {
		if (null == DBUtil.dbUtil)
			synchronized (DBUtil.class) {
				if (null == DBUtil.dbUtil)
					DBUtil.dbUtil = new DBUtil();
			}
		return DBUtil.dbUtil;
	}

	public Connection getConnectionFromJNDILookup() throws DBException {

		if (!this.config.getBoolean(ConfigParam.JDBC_CONN_USE_JNDI_LOOKUP)) {
			throw new DBException(
					"JDBC_CONN_POOL was not initiated. Please ensure to activate the pool through the configuration file "
							+ Config.CONFIG_PROPERTIES_FILE_NAME);
		}

		if (LOGGER.isTraceEnabled())
			LOGGER.trace("Get a connection through JNDI Lookup");
		// Allocate and use a connection from the pool

		Connection conn = null;
		try {
			conn = ds.getConnection();
			if (null == conn) {
				throw new SQLException("RETRIEVED connection=null");
			}
		} catch (SQLException e) {

			if (LOGGER.isEnabledFor(Level.FATAL))
				LOGGER.fatal(
						"FAIL get a connection from the pool. Try to re-initialize datasource connectivity"
								+ e.getMessage(), e);

			this.closeContext(envCtx);
			this.closeContext(initCtx);
			this.initPool();

			throw new DBException(e.getMessage(), e);
		}

		return conn;
	}
	
	public Connection getConnection() throws SQLException, DBException {
		if (this.config.getBoolean(ConfigParam.JDBC_CONN_USE_JNDI_LOOKUP)) {
			return this.getConnectionFromJNDILookup();
		} else {
			return this.getConnectionFromJDBCDriver();
		}
	}

	public Connection getConnectionFromJDBCDriver() throws SQLException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Initiate DataSource connectivity WITHOUT JNDI Lookup");

		Connection conn = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			String url = config.getString(ConfigParam.JDBC_CONN_URL);
			String db = config.getString(ConfigParam.JDBC_CONN_DB);
			String user = config.getString(ConfigParam.JDBC_CONN_USER);
			String password = config.getString(ConfigParam.JDBC_CONN_PASSWORD);

			// Setup the connection with the DB
			conn = DriverManager.getConnection(url + db, user, password);

		} catch (ClassNotFoundException e) {

			if (LOGGER.isEnabledFor(Level.FATAL))
				LOGGER.fatal(e.getMessage(), e);

			throw new ConfigException(e.getMessage(), e);
		}

		return conn;

	}

	private void closeContext(Context ctx) {
		try {
			if (null != ctx)
				ctx.close();
		} catch (NamingException e) {
			if (LOGGER.isEnabledFor(Level.WARN))
				LOGGER.warn(e.getMessage(), e);
		}
	}

	public void closeConnection(Connection conn) {

		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				if (LOGGER.isEnabledFor(Level.WARN))
					LOGGER.warn(e.getMessage(), e);
			}

			if (LOGGER.isTraceEnabled())
				LOGGER.trace("Connection was closed");

		}

	}

	public void closeStatement(Statement stmt) {

		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				if (LOGGER.isEnabledFor(Level.WARN))
					LOGGER.warn(e.getMessage(), e);
			}
		}

		if (LOGGER.isTraceEnabled())
			LOGGER.trace("Statement was closed");

	}

	public void closeResultSet(ResultSet rs) {

		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				if (LOGGER.isEnabledFor(Level.WARN))
					LOGGER.warn(e.getMessage(), e);
			}
		}

		if (LOGGER.isTraceEnabled())
			LOGGER.trace("ResultSet was closed");

	}

}
