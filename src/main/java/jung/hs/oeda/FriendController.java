package jung.hs.oeda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import business.FService;
import util.VO.FriendVO;
import util.VO.MemberVO;

@Controller
public class FriendController {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired  //자동주입
	@Qualifier("friend")
	private FService dao ;
	
	/* 친구추가 가능 목록 */
	@RequestMapping(value = "/friendRecommendation")
	public ModelAndView friendRecommendation(ModelAndView mv,HttpServletRequest request) {
//		걸러지기 전 나를 제외한 모든 유저 목록
		ArrayList<String> mList = dao.friendRecommendation(request) ;
//		걸러진 데이터를 받을 변수
		ArrayList<String> mList2 = mList;
		
//		내 친구 목록
		ArrayList<String> MyFriend = dao.myFriend(request) ;
		
		if(MyFriend!=null) {
			for (String mf : MyFriend) {
				for (String ml : mList) {
					if(ml.equals(mf)) {
//						전체 데이터에서 내 친구 목록 제거
						mList2.remove(ml);
					}
				}
			}
		}
		
//		내가 받은 친구 요청 목록
		ArrayList<String> friendRequestCheck = dao.friendRequestCheck(request) ;
		if(friendRequestCheck!=null) {
			for (String frq : friendRequestCheck) {
				for (String ml : mList) {
					if(ml.equals(frq)) {
//						전체 데이터에서 내가 받은 친구 요청 목록 제거
						mList2.remove(ml);
					}
				}
			}
		}
		
//		내가 보낸 친구 요청 목록
		ArrayList<String> myFriendRequest = dao.myFriendRequest(request) ; 
		
		if(myFriendRequest!=null) {
			for (String mfr : myFriendRequest) {
				for (String ml : mList) {
					if(ml.equals(mfr)) {
//						내가 보낸 친구 요청 목록 제거 후
						mList2.remove(ml);
//						요청 중을 붙여서 다시 add
						mList2.add(ml+" 요청 중");
					}
				}
			}
		}
		
//		데이터를 abc순으로 정렬
		Collections.sort(mList2);
		
		System.out.println(mList2);
		
		mv.addObject("Friend",mList2);
		
		mv.setViewName("friends/plusFriend");
		
		return mv;
	} // friendRecommendation
	
	/* 친구 요청 실행 */
	@RequestMapping(value = "/plusFriendAction")
	public ModelAndView plusFriend(ModelAndView mv, FriendVO fvo,HttpServletRequest request)  
						throws ServletException, IOException{
//		내가 보낸 친구 요청 목록
		List<String> friend_user_list = fvo.getFriend_user_list();
		
		System.out.println("사이즈 => "+friend_user_list.size());
		System.out.println(friend_user_list);
		
		for (String st : friend_user_list) {
			System.out.println(st);
			fvo.setFriend_user(st);
			int cnt = dao.plusFriend(fvo) ;
		}
		
//		걸러지기 전 나를 제외한 모든 유저 목록
		ArrayList<String> mList = dao.friendRecommendation(request) ;
//		걸러진 데이터를 받을 변수
		ArrayList<String> mList2 = mList;
		
//		나와 친구인 유저 목록
		ArrayList<String> MyFriend = dao.myFriend(request) ;
		
		if(MyFriend!=null) {
			for (String mf : MyFriend) {
				for (String ml : mList) {
					if(ml.equals(mf)) {
//						전체 유저에서 친구 목록 제거
						mList2.remove(ml);
					}
				}
			}
		}
		
//		받은 친구 요청 목록
		ArrayList<String> friendRequestCheck = dao.friendRequestCheck(request) ;
		
		if(friendRequestCheck!=null) {
			for (String frq : friendRequestCheck) {
				for (String ml : mList) {
					if(ml.equals(frq)) {
//						전체 유저에서 내가 받은 친구 요청 목록 제거
						mList2.remove(ml);
					}
				}
			}
		}
		
//		내가 보낸 친구 요청 목록
		ArrayList<String> myFriendRequest = dao.myFriendRequest(request) ; 
		
		if(myFriendRequest!=null) {
			for (String mfr : myFriendRequest) {
				for (String ml : mList) {
					if(ml.equals(mfr)) {
//						내가 보낸 요청을 목록에서 제거 후
						mList2.remove(ml);
//						"요청 중"을 붙여서 다시 add
						mList2.add(ml+" 요청 중");
					}
				}
			}
		}
		
//		총 데이터 abc 순으로 정렬
		Collections.sort(mList2);
		
		System.out.println(mList2);
		
		mv.addObject("Friend",mList2);
		mv.setViewName("friends/plusFriend");
		
		return mv;
	}//plusFriend
	
	/* 나에게 온 친구 요청 목록 */
	@RequestMapping(value = "/friendRequest")
	public ModelAndView friendRequest(ModelAndView mv,HttpServletRequest request) {
//		친구 요청 목록 출력
		ArrayList<String> fList = dao.friendRequestCheck(request) ;
		
		mv.addObject("FriendReq",fList);
		mv.setViewName("friends/friendRequest");
		
		return mv;
	} // friendRequest
	
	/* 친구 요청 수락 */
	@RequestMapping(value = "/friendRequestAccept")
	public ModelAndView friendRequestAccept(ModelAndView mv, FriendVO fvo,HttpServletRequest request)  
						throws ServletException, IOException{
//		친구 요청 수락 리스트
		List<String> friend_request_list = fvo.getFriend_request_list();
		
		System.out.println("사이즈 => "+friend_request_list.size());
		System.out.println(friend_request_list);
		
//		목록 하나당 데이터 수정 및 추가
		for (String st : friend_request_list) {
			System.out.println(st);
			fvo.setFriend_user(st);
//			내 친구 목록에 추가
			int cnt = dao.plusFriend(fvo) ;
			
//			요청자 데이터에서 '0'이었던 request_status를 '1'로 수정
			cnt = dao.reqUpdate(fvo) ;
		}
		
//		친구 요청 목록 재출력
		ArrayList<String> fList = dao.friendRequestCheck(request) ;
		
		mv.addObject("FriendReq",fList);
		mv.setViewName("friends/friendRequest");
		
		return mv;
	}//friendRequestAccept
	
	/* 내 친구 목록 */
	@RequestMapping(value = "/myFriend")
	public ModelAndView myFriend(ModelAndView mv,HttpServletRequest request) {
//		친구 목록 출력
		ArrayList<String> fList = dao.myFriend(request) ;
		
		mv.addObject("MyFriend",fList);
		mv.setViewName("friends/myFriend");
		
		return mv;
	} // myFriend
	
	/* 친구 목록 닫기 */
	@RequestMapping(value = "/returnMyFriend")
	public ModelAndView returnMyFriend(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("friends/returnMyFriend");
		
		return mv;
	} // returnMyFriend
	
	/* 친구 추가 가능 목록 닫기 */
	@RequestMapping(value = "/returnPlusFriends")
	public ModelAndView returnPlusFriends(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("friends/returnPlusFriend");
		
		return mv;
	} // returnPlusFriends
	
	/* 친구 요청 목록 닫기 */
	@RequestMapping(value = "/returnFriendRequest")
	public ModelAndView returnFriendRequest(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("friends/returnFriendRequest");
		
		return mv;
	} // returnPlusFriends
} // class
