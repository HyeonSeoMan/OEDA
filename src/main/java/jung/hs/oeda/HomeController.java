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
	
	@Autowired  //�ڵ�����
	@Qualifier("member")
	private MService dao ;
	
	@Autowired
	@Qualifier("board")
	private BService service;
	
	@Autowired  //�ڵ�����
	@Qualifier("friend")
	private FService fdao ;
	
	/* ���� ������ */
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String start(Locale locale, Model model) {
		return "redirect:blist";
	} // home
	
	/* ȸ�� ���� �� */
	@RequestMapping(value = "/joinf")
	public ModelAndView joinf(ModelAndView mv) {
		mv.setViewName("member/joinForm"); 
		return mv;
	}
	
	/* �α��� �� */
	@RequestMapping(value = "/loginf")
	public ModelAndView loginf(ModelAndView mv) {
		mv.setViewName("login/loginForm"); 
		return mv;
	}
	
	/* �α׾ƿ� */
	@RequestMapping(value = "/logout")
	public ModelAndView logoutf(ModelAndView mv,HttpServletRequest request) {
		request.getSession().invalidate();
		mv.setViewName("redirect:blist"); 
		return mv;
	}
	
	/* ���̵� �ߺ� Ȯ�� */
	@RequestMapping(value = "/idcheck")
	public ModelAndView idcheck(ModelAndView mv, MemberVO vo) {
//		ȸ������ �� �Է��� ���̵� "userId"�� ����
		mv.addObject("userId", vo.getId()) ;
		
//		���̵� �ߺ� Ȯ�� ����
		vo = dao.loginCheck(vo);
		
		if (vo !=null ) mv.addObject("idResult", "F") ; // ��� �Ұ��� id 
		else mv.addObject("idResult", "T") ; // ��밡�� id 
		
		mv.setViewName("member/idCheck");
		return mv;
	} // idcheck
	
	/* �α��� ���� */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		HttpSession session = request.getSession();
		
		String id = vo.getId();
		String password=vo.getPassword();
		
//		��ȣȭ�� ��й�ȣ ��ġ�� ���� BCryptPasswordEncoder
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
//		�Էµ� ���̵� �������� DB���� �ڷ� �ޱ�
		vo = dao.loginCheck(vo);
			
		if(vo==null) {
//			DB�� �Էµ� ���̵� ����
			System.out.println("���̵� ����");
			session.setAttribute("logFail", "fail");
			mv.setViewName("redirect:home");
		}else if (scpwd.matches(password, vo.getPassword())) {
//			��ȣȭ�� ��й�ȣ�� �Էµ� ��ȣ�� ��ġ �� => �α��� ����
			
//			���̵� "logId"�� �����ؼ� �α����� ǥ��
			session.setAttribute("logId", id);
//			������ �̹���
			session.setAttribute("logImg", vo.getUploadfile());
			mv.setViewName("redirect:home");
		}else {
			System.out.println("���̵�� ������ ��� ��ȣ Ʋ��");
//			���̵�� ��й�ȣ�� ��ġ ���� ����
			
//			�ش� ���� ������ Ȩ���� �����ϸ� �α��� ���� alert ����
			session.setAttribute("logFail", "fail");
			mv.setViewName("redirect:home");
		}
		return mv;
	} // login
	
	/* ȸ�� ���� ���� */
	@RequestMapping(value = "/join")
	public ModelAndView join(ModelAndView mv, MemberVO vo)  
						throws ServletException, IOException{
//		�̹��� ������ ����
		MultipartFile uploadfilef = vo.getUploadfilef() ;
//		��� ��ȣ ����
		String password=vo.getPassword();
		
		String file1 , file2 = "No Image" ;
		
		if (!uploadfilef.isEmpty()) {
			file1 = "C:/eclipse-workspace/Oeda/src/main/webapp/resources/uploadImage/"
					+ uploadfilef.getOriginalFilename() ;
//			���� ��ο� ����
			uploadfilef.transferTo(new File(file1));
			file2 = "resources/uploadImage/" + uploadfilef.getOriginalFilename() ;
		}
		vo.setUploadfile(file2);
		
//		��� ��ȣ ��ȣȭ�� ���� BCryptPasswordEncoder
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
//		�Է� ��ȣ ���ڵ�
        password = scpwd.encode(password);
        
        vo.setPassword(password);
		
		//ȸ�� ���� ó��
		int cnt = dao.insert(vo);
		
		if (cnt>0) {//����
			mv.setViewName("redirect:home");
		}else {  //����
			mv.addObject("joinId",vo.getId());
			mv.addObject("isJoin","F");
			mv.setViewName("member/doFinish");
		}
		return mv;
	}//join
	
	/* ȸ�� ���� ���� */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request,  ModelAndView mv, MemberVO vo) 
						throws ServletException, IOException {
		HttpSession session = request.getSession(false) ;
		
//		�̹��� ������ ����
		MultipartFile uploadfilef = vo.getUploadfilef() ;
//		��� ��ȣ ����
		String password=vo.getPassword();
		
		String file1 , file2 = "No Image" ;
		
		if (!uploadfilef.isEmpty()) {
			file1 = "C:/eclipse-workspace/Oeda/src/main/webapp/resources/uploadImage/"
					+ uploadfilef.getOriginalFilename() ;
//			���� ��ο� ����
			uploadfilef.transferTo(new File(file1));
			file2 = "resources/uploadImage/" + uploadfilef.getOriginalFilename() ;
		}else {
//			���� �̹��� �� �״�� ��� �ϴ� ���
			if (session!=null) file2= (String)session.getAttribute("logImg") ;
		}
		
		vo.setUploadfile(file2);
		
//		��ȣȭ ����
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
//		��� ��ȣ ��ȣȭ
        password = scpwd.encode(password);
        
        vo.setPassword(password);
		
//		���� ���� ����
		int cnt = dao.update(vo) ;
		
		if (cnt>0) {//����
//			���� ����
			session.invalidate();
			mv.setViewName("redirect:home");
		}else {//����
			mv.addObject("joinId",vo.getId());
			mv.addObject("isJoin","F");
			mv.setViewName("member/doFinish");
		}
		return mv;
	}//update
	
	/* ȸ�� Ż�� */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		HttpSession session = request.getSession(false) ;
		
//		ģ�� ���� ����
		int cnt = fdao.allFriendDelete(vo);
		
//		���� �ۼ��� ���, ����, �� ��ۿ� �޸� ���� ����
		cnt = service.allCommentDelete(vo);
		
//		���� �ۼ��� �� ����
		cnt = service.allBoardDelete(vo);
		
//		ȸ�� Ż��
		cnt = dao.delete(vo) ; 
		
		if (cnt>0) {//����
//			���� ����
			session.invalidate(); 
			mv.setViewName("redirect:home");
		}else {//����
			mv.addObject("isJoin","D");
			mv.setViewName("member/doFinish");
		}
		return mv;
	}//delete
} // class
