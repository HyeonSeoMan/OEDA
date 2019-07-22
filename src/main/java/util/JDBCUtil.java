package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// 1.DB ����/���� : JDBCUtil Class	
public class JDBCUtil {
	
	// Oracle Connection
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			System.out.println("** DB ���� ����~~����~ **");
			return DriverManager.getConnection(url,"system","oracle");
		}catch(Exception e){
			System.out.println("Exception => "+ e.toString());
			System.out.println("** DB ���� ���� **");
			return null;
		} // catch
	} //getConnection 
	
	// ** close�� ������ �������� �ؾ� ��  
	// �������� 
	// => Connection -> PreparedStatement, Statement -> ResultSet
	public static void close(ResultSet rs, PreparedStatement pst,
			            Statement st, Connection cn) {
		if (rs != null) {
			try {
				rs.close();
			}catch(Exception e) {
				System.out.println("ResultSet Close Exception => "+e.toString());
				rs=null ;
			}
		} // rs
		
		if (pst != null) {
			try {
				pst.close();
			}catch(Exception e) {
				System.out.println("PreparedStatement Close Exception => "+e.toString());
				pst=null ;
			}
		} // pst
		
		if (st != null) {
			try {
				st.close();
			}catch(Exception e) {
				System.out.println("Statement Close Exception => "+e.toString());
				st=null ;
			}
		} // pst
		
		if (cn != null) {
			try {
				cn.close();
			}catch(Exception e) {
				System.out.println("Connection Close Exception => "+e.toString());
				cn=null ;
			}
		} // cn
		
	} // close 
} // class
