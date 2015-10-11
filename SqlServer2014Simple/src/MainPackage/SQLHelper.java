package MainPackage;

import java.sql.*;

public class SQLHelper {

	private String connectionURL;
	private String connectionPort;
	private String connectionDBName;
	private String connectionString;
	private String DriverName;
	private String userName;
	private String password;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;

	public SQLHelper() {
		
		this.DriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//this.connectionURL = "jdbc:sqlserver://jcs4hftqiv.database.windows.net:1433;databaseName=CISC181";
		this.connectionURL = "jdbc:sqlserver://jcs4hftqiv.database.windows.net";
		this.connectionPort = "1433";
		this.connectionDBName = "CISC181";
		this.connectionString = this.connectionURL + ":" + this.connectionPort + ";" + "databaseName=" + this.connectionDBName;
		
		
		this.userName = "CISC181";
		this.password = "Section1";
	}

	/**
	 * getConn - sets the current connection based on given parameters. 
	 * @return
	 */
	public Connection getConn() { //
		try {
			Class.forName(DriverName);
			conn = DriverManager.getConnection(connectionString, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * executeCallableStatement - pass in a 'callable statement' and this method will execute it
	 * @param cstmt
	 * @return
	 * @throws SQLException
	 */
	public CallableStatement executeCallableStatement(CallableStatement cstmt) throws SQLException {
		
		pstmt = null;
		try {			
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return cstmt;
	}
	
	/**
	 * executeQuery - pass in some scalar sql (update, insert, delete) and this method will execute it
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		rs = null;
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return rs;
	}

	
	/**
	 * executeUpdate - pass in an SQL string that can be 'prepared', this method will execute it
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String sql) throws SQLException {// SQL
		int i = 0;
		pstmt = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return i;
	}

	/**
	 * preparedStatement - pass in a good SQL string, this query will 'prepare' it
	 * @param sql
	 * @return
	 */
	public PreparedStatement preparedStatement(String sql) {// SQL
		pstmt = null;
		try {
			pstmt = getConn().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * callableStatement - pass in a good sql string, this method will make it callable
	 * @param sql
	 * @return
	 */
	public CallableStatement callableStatement(String sql) {// SQL
		cstmt = null;
		try {
			cstmt = getConn().prepareCall(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cstmt;
	}
	
	/**
	 * close - closes the sql connection
	 */
	public void close() {//
		if (getConn() != null) {
			try {
				getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void setAutoCommit(Boolean b) {//
		if (b) {
			try {
				getConn().commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				getConn().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void commit() {//
		try {
			getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	public void rollback() {//
		try {
			getConn().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}
}