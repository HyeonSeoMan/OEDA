package business;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.DAO.FriendDAO;
import util.VO.FriendVO;
import util.VO.MemberVO;

@Service("friend")
public class FServiceImple implements FService {
	@Autowired
	FriendDAO dao; // = new MemberDAO() ; 자동주입
	
	@Override
	public ArrayList<String> friendRecommendation(HttpServletRequest request) {
		return dao.friendRecommendation(request) ;
	};
	@Override
	public int plusFriend(FriendVO fvo) {
		return dao.plusFriend(fvo) ;
	};
//	@Override
//	public int friendRequest(MemberVO vo) {
//		return dao.friendRequest(vo) ;
//	}
	@Override
	public ArrayList<String> friendRequestCheck(HttpServletRequest request) {
		return dao.friendRequestCheck(request) ;
	}
	@Override
	public ArrayList<String> myFriend(HttpServletRequest request) {
		return dao.myFriend(request) ;
	}
	@Override
	public ArrayList<String> myFriendRequest(HttpServletRequest request) {
		return dao.myFriendRequest(request) ;
	}
	@Override
	public int reqUpdate(FriendVO fvo) {
		return dao.reqUpdate(fvo) ;
	}
//	@Override
//	public int friendRequestDelete(MemberVO vo) {
//		return dao.friendRequestDelete(vo) ;
//	}
	@Override
	public int allFriendDelete(MemberVO vo) {
		return dao.allFriendDelete(vo) ;
	}
}
