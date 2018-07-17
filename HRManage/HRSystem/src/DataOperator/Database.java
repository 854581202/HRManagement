package DataOperator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������ݿ��������
 * @author ������
 * @date 2017/11/15
 */
public class Database {
	private Statement stmt = null;
	ResultSet rs = null;
	private Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/hrdatabase?useUnicode=true&amp;characterEncoding=utf-8&useSSL=true";   //���ݿ�����
	String user = "root";
	String pass = "root";
	
	//���캯��
	public Database() {
		
	}
	/**
	 * �����ݿ�����
	 */
	public void OpenConn() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pass);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * ִ�в�ѯ��䷵�ؽ����rs
	 * @throws SQLException 
	 */
	public ResultSet QueryInfo(String sql) throws SQLException {
		stmt = null;
		rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception ex) {
			System.err.println("executeQuery:" + ex.getMessage());
		}
		return rs;
	}
	
	//��ȡ��¼����
	public int RecordNumber(String sql) throws SQLException {
		stmt = null;
		rs = null;
		int row = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			if(rs.last()) {
				row = rs.getRow();
			}
		} catch(Exception ex) {
			System.err.println("executeQuery:" + ex.getMessage());
		}
		return row;
	}
	
	//��ȡ�����
	public int getMaxId(String sql) throws Exception {
		stmt = null;
		rs = null;
		int number = -1;
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(number < rs.getInt(1)) {
					number = rs.getInt(1);
				}
			}
		} catch(Exception ex) {
			System.err.println("executeQuery:" + ex.getMessage());
		}
		return number;
	}
	
	//��ȡ��С���
	public int getMinId(String sql) throws Exception {
		stmt = null;
		rs = null;
		int number = 100000;
		try{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(number > rs.getInt(1)) {
					number = rs.getInt(1);
				}
			}
		} catch(Exception ex) {
			System.err.println("executeQuery:" + ex.getMessage());
		} 
		return number;
	}
	/**
	 * ִ�и������
	 * @throws SQLException 
	 */
	public void UpdateInfo(String sql) throws SQLException {
		stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
		} catch(Exception ex) {
			System.err.println("executeQuery:" + ex.getMessage());
		}
	}
	/**
	 * �ر�Statement 
	 */
	public void closeStmt() {
		try{
			if(stmt != null) {
				stmt.close();
			}
		} catch(Exception ex) {
			System.err.println("executeQuery:" + ex.getMessage());
		}
	}
	/**
	 * �ر����ݿ�����
	 */
	public void closeConn() {
		try{
			if(conn != null) {
				conn.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
