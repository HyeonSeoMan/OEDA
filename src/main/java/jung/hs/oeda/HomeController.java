package jung.hs.oeda;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import business.BService;
import business.FService;
import business.MService;
import util.VO.MemberVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired  //자동주입
	@Qualifier("member")
	private MService dao ;
	
	@Autowired
	@Qualifier("board")
	private BService service;
	
	@Autowired  //자동주입
	@Qualifier("friend")
	private FService fdao ;
	
	/* 시작 페이지 */
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String start(Locale locale, Model model) {
		return "redirect:blist";
	} // home
	
	/* 회원 가입 폼 */
	@RequestMapping(value = "/joinf")
	public ModelAndView joinf(ModelAndView mv) {
		mv.setViewName("member/joinForm"); 
		return mv;
	}
	
	/* 로그인 폼 */
	@RequestMapping(value = "/loginf")
	public ModelAndView loginf(ModelAndView mv) {
		mv.setViewName("login/loginForm"); 
		return mv;
	}
	
	/* 로그아웃 */
	@RequestMapping(value = "/logout")
	public ModelAndView logoutf(ModelAndView mv,HttpServletRequest request) {
		request.getSession().invalidate();
		mv.setViewName("redirect:blist"); 
		return mv;
	}
	
	/* 아이디 중복 확인 */
	@RequestMapping(value = "/idcheck")
	public ModelAndView idcheck(ModelAndView mv, MemberVO vo) {
//		회원가입 시 입력한 아이디를 "userId"로 저장
		mv.addObject("userId", vo.getId()) ;
		
//		아이디 중복 확인 실행
		vo = dao.loginCheck(vo);
		
		if (vo !=null ) mv.addObject("idResult", "F") ; // 사용 불가능 id 
		else mv.addObject("idResult", "T") ; // 사용가능 id 
		
		mv.setViewName("member/idCheck");
		return mv;
	} // idcheck
	
	/* 로그인 실행 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		HttpSession session = request.getSession();
		
		String id = vo.getId();
		String password=vo.getPassword();
		
//		암호화된 비밀번호 매치를 위한 BCryptPasswordEncoder
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
//		입력된 아이디를 기준으로 DB에서 자료 받기
		vo = dao.loginCheck(vo);
			
		if(vo==null) {
//			DB에 입력된 아이디가 없음
			System.out.println("아이디 없음");
			session.setAttribute("logFail", "fail");
			mv.setViewName("redirect:home");
		}else if (scpwd.matches(password, vo.getPassword())) {
//			암호화된 비밀번호와 입력된 번호가 매치 됨 => 로그인 성공
			
//			아이디를 "logId"에 저장해서 로그인을 표시
			session.setAttribute("logId", id);
//			프로필 이미지
			session.setAttribute("logImg", vo.getUploadfile());
			mv.setViewName("redirect:home");
		}else {
			System.out.println("아이디는 있지만 비밀 번호 틀림");
//			아이디와 비밀번호가 일치 하지 않음
			
//			해당 값을 가지고 홈으로 복귀하면 로그인 실패 alert 실행
			session.setAttribute("logFail", "fail");
			mv.setViewName("redirect:home");
		}
		return mv;
	} // login
	
	/* 회원 가입 실행 */
	@RequestMapping(value = "/join")
	public ModelAndView join(ModelAndView mv, MemberVO vo)  
						throws ServletException, IOException{
//		이미지 데이터 저장
		MultipartFile uploadfilef = vo.getUploadfilef() ;
//		비밀 번호 저장
		String password=vo.getPassword();
		
		String file1 , file2 = "No Image" ;
		
		if (!uploadfilef.isEmpty()) {
			file1 = "C:/eclipse-workspace/Oeda/src/main/webapp/resources/uploadImage/"
					+ uploadfilef.getOriginalFilename() ;
//			실제 경로에 저장
			uploadfilef.transferTo(new File(file1));
			file2 = "resources/uploadImage/" + uploadfilef.getOriginalFilename() ;
		}
		vo.setUploadfile(file2);
		
//		비밀 번호 암호화를 위한 BCryptPasswordEncoder
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
//		입력 번호 인코딩
        password = scpwd.encode(password);
        
        vo.setPassword(password);
		
		//회워 가입 처리
		int cnt = dao.insert(vo);
		
		if (cnt>0) {//성공
			mv.setViewName("redirect:home");
		}else {  //실패
			mv.addObject("joinId",vo.getId());
			mv.addObject("isJoin","F");
			mv.setViewName("member/doFinish");
		}
		return mv;
	}//join
	
	/* 회원 정보 수정 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request,  ModelAndView mv, MemberVO vo) 
						throws ServletException, IOException {
		HttpSession session = request.getSession(false) ;
		
//		이미지 데이터 저장
		MultipartFile uploadfilef = vo.getUploadfilef() ;
//		비밀 번호 저장
		String password=vo.getPassword();
		
		String file1 , file2 = "No Image" ;
		
		if (!uploadfilef.isEmpty()) {
			file1 = "C:/eclipse-workspace/Oeda/src/main/webapp/resources/uploadImage/"
					+ uploadfilef.getOriginalFilename() ;
//			실제 경로에 보관
			uploadfilef.transferTo(new File(file1));
			file2 = "resources/uploadImage/" + uploadfilef.getOriginalFilename() ;
		}else {
//			이전 이미지 를 그대로 사용 하는 경우
			if (session!=null) file2= (String)session.getAttribute("logImg") ;
		}
		
		vo.setUploadfile(file2);
		
//		암호화 설정
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
//		비밀 번호 암호화
        password = scpwd.encode(password);
        
        vo.setPassword(password);
		
//		정보 수정 실행
		int cnt = dao.update(vo) ;
		
		if (cnt>0) {//성공
//			세션 종료
			session.invalidate();
			mv.setViewName("redirect:home");
		}else {//실패
			mv.addObject("joinId",vo.getId());
			mv.addObject("isJoin","F");
			mv.setViewName("member/doFinish");
		}
		return mv;
	}//update
	
	/* 회원 탈퇴 */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		HttpSession session = request.getSession(false) ;
		
//		친구 정보 삭제
		int cnt = fdao.allFriendDelete(vo);
		
//		내가 작성한 댓글, 대댓글, 내 댓글에 달린 대댓글 삭제
		cnt = service.allCommentDelete(vo);
		
//		내가 작성한 글 삭제
		cnt = service.allBoardDelete(vo);
		
//		회원 탈퇴
		cnt = dao.delete(vo) ; 
		
		if (cnt>0) {//성공
//			세션 종료
			session.invalidate(); 
			mv.setViewName("redirect:home");
		}else {//실패
			mv.addObject("isJoin","D");
			mv.setViewName("member/doFinish");
		}
		return mv;
	}//delete
} // class
