package test2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class conDao {
	
	public Connection conn = null; // 声明Connection对象的实例
	public Statement stmt = null; // 声明Statement对象的实例
	public  ResultSet rs = null; // 声明ResultSet对象的实例
	private static String propFileName = "connDB.properties"; // 指定资源文件保存的位置
	private static Properties prop = new Properties(); // 创建并实例化Properties对象的实例
	private static String dbClassName = "com.mysql.jdbc.Driver"  ;//定义保存数据库驱动的变量
	private static String dbUrl= "jdbc:mysql://localhost:3306/exam_system" ;
	private static String dbUser = "root";
	private static String dbPwd = "123456";
    public conDao() {
    	
    	
    }
	public static  Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_system", "root", "123456");
			System.out.println("suceess!");
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if (conn == null) {
			System.err
					.println("警告: DbConnectionManager.getConnection() 获得数据库链接失败.\r\n\r\n链接类型:"
							+ dbClassName
							+ "\r\n链接位置:"
							+ dbUrl
							+ "\r\n用户/密码"
							+ dbUser + "/" + dbPwd);
		}
		return conn;
	}
	
	/*
	 * 功能：执行查询语句
	 */
	public  ResultSet executeQuery(String sql) {
		try { // 捕捉异常
			conn = getConnection(); // 调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); // 输出异常信息
		}
		return rs; // 返回结果集对象
	}

	public static void main(String[] args) {  
        getConnection();
        System.out.println("success");

    }  	
}
