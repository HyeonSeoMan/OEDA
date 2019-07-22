package business;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import util.VO.FriendVO;
import util.VO.MemberVO;

public interface FService {
	
	public ArrayList<String> friendRecommendation(HttpServletRequest request);
	
	public int plusFriend(FriendVO fvo);
	
	public ArrayList<String> friendRequestCheck(HttpServletRequest request);
	
	public ArrayList<String> myFriend(HttpServletRequest request);
	
	public ArrayList<String> myFriendRequest(HttpServletRequest request);
	
	public int reqUpdate(FriendVO fvo);
	
	public int allFriendDelete(MemberVO vo);

}