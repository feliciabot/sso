package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

abstract public class JDBCTemplate<T> {
	private String driverClass="com.mysql.jdbc.Driver";
	private String jdbcURL="jdbc:mysql://114.55.95.14/SSO?useUnicode=true&characterEncoding=UTF-8";
	private String user="SSO";
	private String pwd="123456";
	abstract public T execute() throws Exception;
	protected Connection getConnection()throws Exception {
			Class.forName(driverClass);
			Connection conn=DriverManager.getConnection(jdbcURL, user, pwd);
			return conn;
	}
}
