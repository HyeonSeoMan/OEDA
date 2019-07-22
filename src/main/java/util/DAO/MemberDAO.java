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
	
	/* �α��� üũ �� ���̵� �ߺ� üũ*/
	public MemberVO loginCheck(MemberVO vo) {
//		���� �α��� üũ�� ���ؼ� DB���� �ش� ���̵�� ��й�ȣ�� �ִ��� �˻�������
//		��� ��ȣ�� ��ȣȭ���ֱ� ������ ���̵� �˻��� �� controll����
//		��ȣȭ�� ��� ��ȣ�� �Էµ� ��ȣ�� ��ġ�� 
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
	
	/* ȸ�� ���� */
	public int mInsert(MemberVO vo) {
		sql = "insert into member values(?,?,?)";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getId());
			pst.setString(2,vo.getPassword());
			pst.setString(3,vo.getUploadfile());
			cnt = pst.executeUpdate();		
			System.out.println("Insert ���� cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("Insert ���� cnt => "+cnt);
			System.out.println("Insert Exception => "+e.toString());
		}
		return cnt ;
	} // mInsert
	
	/* ȸ�� ���� ���� */
	public int mUpdate(MemberVO vo) {
		sql = "update member set password=?,uploadfile=? where id=?";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getPassword());
			pst.setString(2, vo.getUploadfile());
			pst.setString(3,vo.getId());
			cnt = pst.executeUpdate();		
			System.out.println("Update ���� cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("Update ���� cnt => "+cnt);
			System.out.println("Update Exception => "+e.toString());
		}
		return cnt ;
	} // mUpdate
	
	/* ȸ�� Ż�� */
	public int delete(MemberVO vo) {
		sql = "delete member where id=?";
		int cnt = 0;
		try {
			pst = cn.prepareStatement(sql) ;
			pst.setString(1, vo.getId());
			cnt = pst.executeUpdate();
			System.out.println("Delete ���� cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("Delete ���� cnt => "+cnt);
			System.out.println("Delete Exception => "+e.toString());
		}
		return cnt ;
	} // delete
} // memberDAO 
