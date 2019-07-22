package util.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import util.JDBCUtil;
import util.VO.FriendVO;
import util.VO.MemberVO;

@Repository
public class MemberDAO {
	
	private Connection cn = JDBCUtil.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	/* 로그인 체크 및 아이디 중복 체크*/
	public MemberVO loginCheck(MemberVO vo) {
//		원래 로그인 체크를 위해선 DB에서 해당 아이디와 비밀번호가 있는지 검사하지만
//		비밀 번호가 암호화돼있기 때문에 아이디만 검색한 후 controll에서
//		암호화된 비밀 번호와 입력된 번호를 매치함 
		sql = "select * from member where id=?";
		try {
			pst = cn.prepareStatement(sql) ;
			pst.setString(1, vo.getId());
			rs=pst.executeQuery();
			if (rs.next()) {
				vo.setPassword(rs.getString(2));
				vo.setUploadfile(rs.getString(3));
				return vo;
			}
		}catch (Exception e) {
			System.out.println("memberLogin Exception => "+e.toString());
		}
		return null ;
	} // login
	
	/* 회원 가입 */
	public int mInsert(MemberVO vo) {
		sql = "insert into member values(?,?,?)";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getId());
			pst.setString(2,vo.getPassword());
			pst.setString(3,vo.getUploadfile());
			cnt = pst.executeUpdate();		
			System.out.println("Insert 성공 cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("Insert 실패 cnt => "+cnt);
			System.out.println("Insert Exception => "+e.toString());
		}
		return cnt ;
	} // mInsert
	
	/* 회원 정보 수정 */
	public int mUpdate(MemberVO vo) {
		sql = "update member set password=?,uploadfile=? where id=?";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getPassword());
			pst.setString(2, vo.getUploadfile());
			pst.setString(3,vo.getId());
			cnt = pst.executeUpdate();		
			System.out.println("Update 성공 cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("Update 실패 cnt => "+cnt);
			System.out.println("Update Exception => "+e.toString());
		}
		return cnt ;
	} // mUpdate
	
	/* 회원 탈퇴 */
	public int delete(MemberVO vo) {
		sql = "delete member where id=?";
		int cnt = 0;
		try {
			pst = cn.prepareStatement(sql) ;
			pst.setString(1, vo.getId());
			cnt = pst.executeUpdate();
			System.out.println("Delete 성공 cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("Delete 실패 cnt => "+cnt);
			System.out.println("Delete Exception => "+e.toString());
		}
		return cnt ;
	} // delete
} // memberDAO 
