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
public class FriendDAO {
	
	private Connection cn = JDBCUtil.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	/* ģ�� �߰� ���� ���(ģ�� ��õ) */
	public ArrayList<String> friendRecommendation(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		�α��� ���̵� ������ ��� ����
		sql = "select id from member where id!='"+session.getAttribute("logId")+"'";
		ArrayList<String> mList = new ArrayList<String>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				do {
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString(1));
					mList.add(vo.getId());
				} while (rs.next());
			} else {
					System.out.println("friendRecommendation => ����� �ڷᰡ �Ѱǵ� ����.");
					mList=null ;
					}
		}catch (Exception e) {
			System.out.println("friendRecommendation Exception => "+e.toString());
			mList=null ;
		}
		return mList ;
	} // friendRecommendation
	
	/* ģ�� ��û �� ���� ���� */
	public int plusFriend(FriendVO fvo) {
//		ģ�� ��û �� ���� 'request_status'�� '0',
//		ģ�� ���� �� ���� 'request_status'�� '1'�� ��� ��
		sql = "insert into Friends_relationship values(?,?,?)";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,fvo.getMaster_user());
			pst.setString(2,fvo.getFriend_user());
			pst.setString(3,fvo.getRequest_status());
			cnt = pst.executeUpdate();		
			System.out.println("plusFriend ���� cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("plusFriend ���� cnt => "+cnt);
			System.out.println("plusFriend Exception => "+e.toString());
		}
		return cnt ;
	} // plusFriend
	
	/* ������ �� ģ�� ��û */
	public ArrayList<String> friendRequestCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		'friend_user'�� '��'�̰�, 'request_status'�� '0'�� ������
		sql = "select master_user from friends_relationship where friend_user='"
				+session.getAttribute("logId")
				+ "' AND REQUEST_STATUS='0'";
		ArrayList<String> fList = new ArrayList<String>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				do {
					FriendVO fvo = new FriendVO();
					fvo.setFriend_request(rs.getString(1));
					fList.add(fvo.getFriend_request());
				} while (rs.next());
			} else {
					System.out.println("friendRequestCheck => ����� �ڷᰡ �Ѱǵ� ����.");
					fList=null;
					}
		}catch (Exception e) {
			System.out.println("friendRequestCheck Exception => "+e.toString());
			fList=null;
		}
		return fList ;
	} // friendRequestCheck
	
	/* ���� ģ���� ���� */
	public ArrayList<String> myFriend(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		'friend_user'�� '��'�̰�, 'request_status'�� '1'�� ������
		sql = "select master_user from friends_relationship where friend_user='"
				+session.getAttribute("logId")
				+ "' AND REQUEST_STATUS='1'";
		ArrayList<String> fList = new ArrayList<String>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				do {
					FriendVO fvo = new FriendVO();
					fvo.setMyFriend(rs.getString(1));
					fList.add(fvo.getMyFriend());
				} while (rs.next());
			} else {
					System.out.println("myFriend => ����� �ڷᰡ �Ѱǵ� ����.");
					fList=null;
					}
		}catch (Exception e) {
			System.out.println("myFriend Exception => "+e.toString());
			fList=null;
		}
		return fList ;
	} // myFriend
	
	
	/* ���� ģ�� ��û �� ���� */
	public ArrayList<String> myFriendRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		'master_user'�� '��'�̰�, 'request_status'�� '0'�� ������
		sql = "select friend_user from friends_relationship where master_user='"
				+session.getAttribute("logId")
				+ "' AND REQUEST_STATUS='0'";
		ArrayList<String> fList = new ArrayList<String>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				do {
					FriendVO fvo = new FriendVO();
					fvo.setMyFriendRequest(rs.getString(1));
					fList.add(fvo.getMyFriendRequest());
				} while (rs.next());
			} else {
					System.out.println("myFriendRequest => ����� �ڷᰡ �Ѱǵ� ����.");
					fList=null;
					}
		}catch (Exception e) {
			System.out.println("myFriendRequest Exception => "+e.toString());
			fList=null;
		}
		return fList ;
	} // myFriendRequest
	
	
	/* ģ�� ���� �� ��û ������ ���� */
	public int reqUpdate(FriendVO fvo) {
//		'0'�̾��� request_status�� ģ�� ������ ������ '1'�� ��ȯ
		sql = "update friends_relationship set request_status=1 "
				+ "where master_user=? and friend_user=?";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,fvo.getFriend_user());
			pst.setString(2,fvo.getMaster_user());
			cnt = pst.executeUpdate();		
			System.out.println("reqUpdate ���� cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("reqUpdate ���� cnt => "+cnt);
			System.out.println("reqUpdate Exception => "+e.toString());
		}
		return cnt ;
	} // reqUpdate
	
//	ȸ�� Ż�� �ø�� ģ�� ���� ����
	public int allFriendDelete(MemberVO vo) {
//		'master_user'�� 'friend_user'�� '��'�� ��� ������ ����
		sql = "delete friends_relationship where master_user=? "
				+ "or friend_user=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			pst.setString(2, vo.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** allFriendDelete Exception =>" + e.toString());
			return 0;
		}
	} // allFriendDelete
}
