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
	
	/* 친구 추가 가능 목록(친구 추천) */
	public ArrayList<String> friendRecommendation(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		로그인 아이디를 제외한 모든 유저
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
					System.out.println("friendRecommendation => 출력할 자료가 한건도 없음.");
					mList=null ;
					}
		}catch (Exception e) {
			System.out.println("friendRecommendation Exception => "+e.toString());
			mList=null ;
		}
		return mList ;
	} // friendRecommendation
	
	/* 친구 요청 및 수락 실행 */
	public int plusFriend(FriendVO fvo) {
//		친구 요청 일 때는 'request_status'가 '0',
//		친구 수락 일 때는 'request_status'가 '1'로 등록 됨
		sql = "insert into Friends_relationship values(?,?,?)";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,fvo.getMaster_user());
			pst.setString(2,fvo.getFriend_user());
			pst.setString(3,fvo.getRequest_status());
			cnt = pst.executeUpdate();		
			System.out.println("plusFriend 성공 cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("plusFriend 실패 cnt => "+cnt);
			System.out.println("plusFriend Exception => "+e.toString());
		}
		return cnt ;
	} // plusFriend
	
	/* 나한테 온 친구 요청 */
	public ArrayList<String> friendRequestCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		'friend_user'가 '나'이고, 'request_status'가 '0'인 데이터
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
					System.out.println("friendRequestCheck => 출력할 자료가 한건도 없음.");
					fList=null;
					}
		}catch (Exception e) {
			System.out.println("friendRequestCheck Exception => "+e.toString());
			fList=null;
		}
		return fList ;
	} // friendRequestCheck
	
	/* 나랑 친구인 유저 */
	public ArrayList<String> myFriend(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		'friend_user'가 '나'이고, 'request_status'가 '1'인 데이터
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
					System.out.println("myFriend => 출력할 자료가 한건도 없음.");
					fList=null;
					}
		}catch (Exception e) {
			System.out.println("myFriend Exception => "+e.toString());
			fList=null;
		}
		return fList ;
	} // myFriend
	
	
	/* 내가 친구 신청 한 유저 */
	public ArrayList<String> myFriendRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		'master_user'가 '나'이고, 'request_status'가 '0'인 데이터
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
					System.out.println("myFriendRequest => 출력할 자료가 한건도 없음.");
					fList=null;
					}
		}catch (Exception e) {
			System.out.println("myFriendRequest Exception => "+e.toString());
			fList=null;
		}
		return fList ;
	} // myFriendRequest
	
	
	/* 친구 수락 시 요청 데이터 변경 */
	public int reqUpdate(FriendVO fvo) {
//		'0'이었던 request_status를 친구 수락을 받으면 '1'로 변환
		sql = "update friends_relationship set request_status=1 "
				+ "where master_user=? and friend_user=?";
		int cnt = 0 ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,fvo.getFriend_user());
			pst.setString(2,fvo.getMaster_user());
			cnt = pst.executeUpdate();		
			System.out.println("reqUpdate 성공 cnt => "+cnt);		
		} catch (Exception e) {
			System.out.println("reqUpdate 실패 cnt => "+cnt);
			System.out.println("reqUpdate Exception => "+e.toString());
		}
		return cnt ;
	} // reqUpdate
	
//	회원 탈퇴 시모든 친구 관계 삭제
	public int allFriendDelete(MemberVO vo) {
//		'master_user'나 'friend_user'가 '나'인 모든 데이터 삭제
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
