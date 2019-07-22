package business;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import util.VO.FriendVO;
import util.VO.MemberVO;

public interface MService {

	MemberVO loginCheck(MemberVO vo);
	
	int insert(MemberVO vo);
	
	int update(MemberVO vo);
	
	int delete(MemberVO vo);

}