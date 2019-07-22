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
	
	@Autowired  //�ڵ�����
	@Qualifier("friend")
	private FService dao ;
	
	/* ģ���߰� ���� ��� */
	@RequestMapping(value = "/friendRecommendation")
	public ModelAndView friendRecommendation(ModelAndView mv,HttpServletRequest request) {
//		�ɷ����� �� ���� ������ ��� ���� ���
		ArrayList<String> mList = dao.friendRecommendation(request) ;
//		�ɷ��� �����͸� ���� ����
		ArrayList<String> mList2 = mList;
		
//		�� ģ�� ���
		ArrayList<String> MyFriend = dao.myFriend(request) ;
		
		if(MyFriend!=null) {
			for (String mf : MyFriend) {
				for (String ml : mList) {
					if(ml.equals(mf)) {
//						��ü �����Ϳ��� �� ģ�� ��� ����
						mList2.remove(ml);
					}
				}
			}
		}
		
//		���� ���� ģ�� ��û ���
		ArrayList<String> friendRequestCheck = dao.friendRequestCheck(request) ;
		if(friendRequestCheck!=null) {
			for (String frq : friendRequestCheck) {
				for (String ml : mList) {
					if(ml.equals(frq)) {
//						��ü �����Ϳ��� ���� ���� ģ�� ��û ��� ����
						mList2.remove(ml);
					}
				}
			}
		}
		
//		���� ���� ģ�� ��û ���
		ArrayList<String> myFriendRequest = dao.myFriendRequest(request) ; 
		
		if(myFriendRequest!=null) {
			for (String mfr : myFriendRequest) {
				for (String ml : mList) {
					if(ml.equals(mfr)) {
//						���� ���� ģ�� ��û ��� ���� ��
						mList2.remove(ml);
//						��û ���� �ٿ��� �ٽ� add
						mList2.add(ml+" ��û ��");
					}
				}
			}
		}
		
//		�����͸� abc������ ����
		Collections.sort(mList2);
		
		System.out.println(mList2);
		
		mv.addObject("Friend",mList2);
		
		mv.setViewName("friends/plusFriend");
		
		return mv;
	} // friendRecommendation
	
	/* ģ�� ��û ���� */
	@RequestMapping(value = "/plusFriendAction")
	public ModelAndView plusFriend(ModelAndView mv, FriendVO fvo,HttpServletRequest request)  
						throws ServletException, IOException{
//		���� ���� ģ�� ��û ���
		List<String> friend_user_list = fvo.getFriend_user_list();
		
		System.out.println("������ => "+friend_user_list.size());
		System.out.println(friend_user_list);
		
		for (String st : friend_user_list) {
			System.out.println(st);
			fvo.setFriend_user(st);
			int cnt = dao.plusFriend(fvo) ;
		}
		
//		�ɷ����� �� ���� ������ ��� ���� ���
		ArrayList<String> mList = dao.friendRecommendation(request) ;
//		�ɷ��� �����͸� ���� ����
		ArrayList<String> mList2 = mList;
		
//		���� ģ���� ���� ���
		ArrayList<String> MyFriend = dao.myFriend(request) ;
		
		if(MyFriend!=null) {
			for (String mf : MyFriend) {
				for (String ml : mList) {
					if(ml.equals(mf)) {
//						��ü �������� ģ�� ��� ����
						mList2.remove(ml);
					}
				}
			}
		}
		
//		���� ģ�� ��û ���
		ArrayList<String> friendRequestCheck = dao.friendRequestCheck(request) ;
		
		if(friendRequestCheck!=null) {
			for (String frq : friendRequestCheck) {
				for (String ml : mList) {
					if(ml.equals(frq)) {
//						��ü �������� ���� ���� ģ�� ��û ��� ����
						mList2.remove(ml);
					}
				}
			}
		}
		
//		���� ���� ģ�� ��û ���
		ArrayList<String> myFriendRequest = dao.myFriendRequest(request) ; 
		
		if(myFriendRequest!=null) {
			for (String mfr : myFriendRequest) {
				for (String ml : mList) {
					if(ml.equals(mfr)) {
//						���� ���� ��û�� ��Ͽ��� ���� ��
						mList2.remove(ml);
//						"��û ��"�� �ٿ��� �ٽ� add
						mList2.add(ml+" ��û ��");
					}
				}
			}
		}
		
//		�� ������ abc ������ ����
		Collections.sort(mList2);
		
		System.out.println(mList2);
		
		mv.addObject("Friend",mList2);
		mv.setViewName("friends/plusFriend");
		
		return mv;
	}//plusFriend
	
	/* ������ �� ģ�� ��û ��� */
	@RequestMapping(value = "/friendRequest")
	public ModelAndView friendRequest(ModelAndView mv,HttpServletRequest request) {
//		ģ�� ��û ��� ���
		ArrayList<String> fList = dao.friendRequestCheck(request) ;
		
		mv.addObject("FriendReq",fList);
		mv.setViewName("friends/friendRequest");
		
		return mv;
	} // friendRequest
	
	/* ģ�� ��û ���� */
	@RequestMapping(value = "/friendRequestAccept")
	public ModelAndView friendRequestAccept(ModelAndView mv, FriendVO fvo,HttpServletRequest request)  
						throws ServletException, IOException{
//		ģ�� ��û ���� ����Ʈ
		List<String> friend_request_list = fvo.getFriend_request_list();
		
		System.out.println("������ => "+friend_request_list.size());
		System.out.println(friend_request_list);
		
//		��� �ϳ��� ������ ���� �� �߰�
		for (String st : friend_request_list) {
			System.out.println(st);
			fvo.setFriend_user(st);
//			�� ģ�� ��Ͽ� �߰�
			int cnt = dao.plusFriend(fvo) ;
			
//			��û�� �����Ϳ��� '0'�̾��� request_status�� '1'�� ����
			cnt = dao.reqUpdate(fvo) ;
		}
		
//		ģ�� ��û ��� �����
		ArrayList<String> fList = dao.friendRequestCheck(request) ;
		
		mv.addObject("FriendReq",fList);
		mv.setViewName("friends/friendRequest");
		
		return mv;
	}//friendRequestAccept
	
	/* �� ģ�� ��� */
	@RequestMapping(value = "/myFriend")
	public ModelAndView myFriend(ModelAndView mv,HttpServletRequest request) {
//		ģ�� ��� ���
		ArrayList<String> fList = dao.myFriend(request) ;
		
		mv.addObject("MyFriend",fList);
		mv.setViewName("friends/myFriend");
		
		return mv;
	} // myFriend
	
	/* ģ�� ��� �ݱ� */
	@RequestMapping(value = "/returnMyFriend")
	public ModelAndView returnMyFriend(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("friends/returnMyFriend");
		
		return mv;
	} // returnMyFriend
	
	/* ģ�� �߰� ���� ��� �ݱ� */
	@RequestMapping(value = "/returnPlusFriends")
	public ModelAndView returnPlusFriends(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("friends/returnPlusFriend");
		
		return mv;
	} // returnPlusFriends
	
	/* ģ�� ��û ��� �ݱ� */
	@RequestMapping(value = "/returnFriendRequest")
	public ModelAndView returnFriendRequest(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("friends/returnFriendRequest");
		
		return mv;
	} // returnPlusFriends
} // class
