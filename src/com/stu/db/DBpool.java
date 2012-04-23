package com.stu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库操作类
 * @author hsz
 *
 */
public class DBpool {
	/**
	 * 数据库驱动
	 */
	private static final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";	
	/**
	 * 数据库url
	 */
	private static final String DB_URL ="jdbc:oracle:thin:@127.0.0.1:1522:orcl1";
	/**
	 * 数据库用户名
	 */
	private static final String DB_USER ="sys as sysdba";
	/**
	 * 数据库用户名密码
	 */
	private static final String DB_PWD ="hsz123";
	/**
	 * 数据库连接对象
	 */
	private static Connection conn = null;
	
	/**
	 * 获取数据库连接
	 * @return connection
	 */
	public static Connection getConn(){
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动出错!"+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("连接数据库出错!"+e.getMessage());
			e.printStackTrace();
		}
		return conn ;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn connection
	 * @param pst  PreparedStatement
	 * @param rs   ResultSet
	 */
	public static void closeAll(Connection conn,Statement st,ResultSet rs){	
			try {
				if(rs!=null){
					rs.close();	
				}
				if(st!=null){
					st.close();
				}
				if(conn!=null){
					conn.close();
				}		
			} catch (SQLException e) {
				System.out.println("关闭数据库连接出错!");
				e.printStackTrace();
			}
		
	}
}
