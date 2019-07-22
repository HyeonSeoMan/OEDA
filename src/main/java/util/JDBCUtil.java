package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// 1.DB 연결/해제 : JDBCUtil Class	
public class JDBCUtil {
	
	// Oracle Connection
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			System.out.println("** DB 연결 성공~~성공~ **");
			return DriverManager.getConnection(url,"system","oracle");
		}catch(Exception e){
			System.out.println("Exception => "+ e.toString());
			System.out.println("** DB 연결 실패 **");
			return null;
		} // catch
	} //getConnection 
	
	// ** close는 생성의 역순으로 해야 함  
	// 생성순서 
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
