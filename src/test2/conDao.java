package test2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class conDao {
	
	public Connection conn = null; // ����Connection�����ʵ��
	public Statement stmt = null; // ����Statement�����ʵ��
	public  ResultSet rs = null; // ����ResultSet�����ʵ��
	private static String propFileName = "connDB.properties"; // ָ����Դ�ļ������λ��
	private static Properties prop = new Properties(); // ������ʵ����Properties�����ʵ��
	private static String dbClassName = "com.mysql.jdbc.Driver"  ;//���屣�����ݿ������ı���
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
					.println("����: DbConnectionManager.getConnection() ������ݿ�����ʧ��.\r\n\r\n��������:"
							+ dbClassName
							+ "\r\n����λ��:"
							+ dbUrl
							+ "\r\n�û�/����"
							+ dbUser + "/" + dbPwd);
		}
		return conn;
	}
	
	/*
	 * ���ܣ�ִ�в�ѯ���
	 */
	public  ResultSet executeQuery(String sql) {
		try { // ��׽�쳣
			conn = getConnection(); // ����getConnection()��������Connection�����һ��ʵ��conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); // ����쳣��Ϣ
		}
		return rs; // ���ؽ��������
	}

	public static void main(String[] args) {  
        getConnection();
        System.out.println("success");

    }  	
}
