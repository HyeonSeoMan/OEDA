package jung.hs.oeda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import business.BService;
import business.FService;
import business.MService;
import util.VO.BoardVO;
import util.VO.CommentVO;
import util.VO.MemberVO;
import util.VO.PageVO;
import util.VO.SearchVO;

@Controller
public class BoardController {
	@Autowired
	@Qualifier("board")
	private BService service;
	
	@Autowired
	@Qualifier("friend")
	private FService fdao ;
	 
//	���� �������� �����ϱ� ���� ���� ����
	int cp;
//	���� �˻��� �ɼ��� �����ϱ� ���� ���� ����
	String optKeep;
//	���� �˻� ������ �����ϱ� ���� ���� ����
	String searchKeep;
//	���� �±� ���� �����ϱ� ���� ���� ����
	String tagKeep;
	
	/* �Խñ� ��� */
	@RequestMapping(value = "/blist")
	public ModelAndView blist(ModelAndView mv, PageVO pvo, SearchVO svo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
//		�˻� �ɼ�
		String opt = svo.getOpt();
//		�˻� ����
		String search = svo.getSearch();
//		�±� �˻��� ������ ����
		String tag=null;
		
		if("tag".equals(opt)) {
//			�˻� �ɼ��� �±� �� ��
//			"tagValue"�� �±� �˻� �� ����
			session.setAttribute("tagValue",search);
			svo.setTag(opt);
		}else if(opt==null&&search==null&&pvo.getCurrPage()==0){
//			�˻����� Ȩ���� �̵�
			session.setAttribute("tagValue",null);
		}else if("share_tag".equals(opt)||"myPost".equals(opt)||"post".equals(opt)){
//			���� ��������, '���� �� ����'�� �̵�
			session.setAttribute("tagValue",null);
		}
		
//		�������� �ٲܶ����� ������ �ʱ�ȭ �Ǳ⶧���� �������� �ٲٱ��� ������ ����
		if (tag != null || opt != null) {
//			�˻� �� ���
			tagKeep = tag;
			searchKeep = search;
			optKeep = opt;
			cp = pvo.getCurrPage();
		} else if (cp != pvo.getCurrPage() && pvo.getCurrPage() != 0) {
			// ���� �������� ������(cp)�� ���� ������(currPage)�� �ٸ��� 
//			=> (�������� �ٲ��) ���� ������ ����
			svo.setOpt(optKeep);
			svo.setSearch(searchKeep);
			svo.setTag(tagKeep);			
			cp = pvo.getCurrPage();
		} else {
//			Ȩ
			tagKeep = null;
			searchKeep = null;
			optKeep = null;
		}
		
		System.out.println("currP => "+pvo.getCurrPage());
		
//		���� ������ ���
		int currPage = 1;
		if (pvo.getCurrPage() > 1) currPage = pvo.getCurrPage();
		else pvo.setCurrPage(currPage);
		
//		�Խñ� ��� ���
		pvo = service.pageList(pvo, svo, request);

//		��ü ������ ���
		int totalPage = pvo.getTotalCount() / pvo.getPerPage();
//		�� �Խñ� ������ �������� �Խñ� ������ ������ �������� ������
//		��ü ������ �ϳ� �� �߰�
		if ((pvo.getTotalCount() % pvo.getPerPage()) != 0) totalPage++;
		
//		�˻� ���� �˻�â�� �����ϱ� ���� ���� 
		mv.addObject("Search", search);
		mv.addObject("Opt", opt);
		
		mv.addObject("Banana", pvo.getList());
		
//		�� ������, ���� ������ ����
		mv.addObject("totalPage", totalPage);
		mv.addObject("currPage", currPage);
		
		if(pvo.getCurrPage()>1){//���� �������� 2 �̻��̸� ������ �߰�
			mv.setViewName("/board/pageList");
		}else {//1�̸� Ȩ
			mv.setViewName("home");
		}
		return mv;
	}// blist

	/* �Խñ� �ۼ� */
	@RequestMapping(value = "/binsert")
	public ModelAndView binsert(ModelAndView mv, BoardVO vo)
						throws ServletException, IOException{
//		�̹��� ������ ����
		List<MultipartFile> uploadfilef = vo.getUploadfilef() ;
		
		String file1 , file2 = "No Image" ;
		
//		���������� DB�� �ԷµǴ� �̹��� �ּ� ��
		String resultFile="";
		
		for (MultipartFile mf : uploadfilef) {
            String originFileName = mf.getOriginalFilename(); // ���� ���� ��
            long fileSize = mf.getSize(); // ���� ������

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            if (fileSize!=0) {
    			
    			file1 = "C:/eclipse-workspace/Oeda/src/main/webapp/resources/uploadImage/"
    					+ mf.getOriginalFilename() ;
    			mf.transferTo(new File(file1));
//    			�̹��� split�� ���� �̹��� �ּ� �տ� #�������#�� ����
    			file2 = "#�������#resources/uploadImage/" + mf.getOriginalFilename() ;
    			
//    			#�������#�� ���� �����͵��� 'resultFile'�� ���� ��
    			resultFile +=file2;
    		}
        }
		
//		���� ����� �ϴ� ��쿡�� setUploadfile
		if (!uploadfilef.isEmpty()) {
			vo.setUploadfile(resultFile);
		}
		
//		���� ���� ������ ����
		List<String> share_tag_list = vo.getShare_tag_list();
		
		System.out.println("������ => "+share_tag_list.size());
		System.out.println(share_tag_list);
//		�� �ۼ���
		String writer = (String)vo.getWriter();
		
//		���������� DB�� �ö󰡴�'result_Share_tag'��
//		�⺻������ �ڽ��� �̸��� ��� ��
		String result_Share_tag="#��������#"+writer+"@��@";
		
		if(share_tag_list.size()>1) {
//			�����ڰ� �θ� �̻��̸� �����͸� ������
			for (String st : share_tag_list) {
				System.out.println(st);
				result_Share_tag +="#��������#"+st+"@��@";
			}
			vo.setShare_tag(result_Share_tag);
		}else if(share_tag_list.size()==1) {
//			�����ڰ� �Ѹ��� ���
			String stl=share_tag_list.get(0);
			if(stl.equals(writer)) {
//				���� ���� �� ���
				vo.setShare_tag(result_Share_tag);
			}else {
//				���� ���Ⱑ �ƴ� ��� �ڽŰ� �����ڸ� ���
				result_Share_tag+="#��������#"+stl+"@��@";
				vo.setShare_tag(result_Share_tag);
			}
		}
		
//		�Խñ� ��� ����
		int cnt = service.insert(vo);
		
		System.out.println(vo);
		
		if (cnt > 0) { //����
			mv.addObject("test", "tttt");
			mv.setViewName("redirect:home");
		} else { //����
			mv.addObject("resultID", "I");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // binsert
	
	/* �Խñ� ������ ������ */
	@RequestMapping(value = "/bdetail")
	public ModelAndView bdetail(HttpServletRequest request, ModelAndView mv, BoardVO vo) {
		HttpSession session = request.getSession(false);
		
//		������ ������ ���
		vo = service.detail(vo);
		
		if (session != null) {
			session.setAttribute("boardSeq", vo.getSeq());
			session.setAttribute("pWriter", vo.getWriter());
		} else {
			System.out.println("detail ==> session is null");
		}
		
//		��� ����Ʈ ���
		ArrayList<CommentVO> list = service.commentList(request) ;
		 
		mv.addObject("Comment",list);

		mv.addObject("Yellow", vo);
		mv.setViewName("board/boardDetail");
		
		return mv;
	} // bdetail
	
	/* �Խñ� �ۼ� �� */
	@RequestMapping(value = "/binsertf")
	public ModelAndView binsertf(ModelAndView mv) {
		mv.setViewName("board/binsertForm");
		return mv;
	} // binsertf
	
	/* �Խñ� ���� */
	@RequestMapping(value = "/bupdate")
	public ModelAndView bupdate(ModelAndView mv, BoardVO vo) {
		
		System.out.println(vo.getSeq());
		System.out.println(vo.getContent());
		
//		�� ���� ����
		int cnt = service.update(vo);
		
		if (cnt > 0) //����
			mv.setViewName("redirect:bdetail?seq="+vo.getSeq());
		else { //����
			mv.addObject("resultID", "U");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // bupdate
	
	/* �Խñ� ���� */
	@RequestMapping(value = "/bdelete")
	public ModelAndView bdelete(ModelAndView mv, BoardVO vo,CommentVO cvo) {		
//		�� ���� ����
		int cnt = service.delete(vo);
		
//		���� ���� ��ȣ
		cvo.setBoardseq(vo.getSeq());
		
		if (cnt > 0) { //����
//			�ۻ��� �����ϸ� �� ���� ��۵� ����
//			��� ����
			int cnt2 = service.commentDelete(cvo);
			
			mv.addObject("resultID", "DSuccess");
			mv.setViewName("board/boardFinish");
		}else { //����
			mv.addObject("resultID", "D");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // bdelete
	
	/* ��� �ۼ� */
	@RequestMapping(value = "/commentInsert")
	public ModelAndView commentInsert(ModelAndView mv, CommentVO cvo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
//		�ش� ���� ���� ��ȣ
		String boardseq= request.getParameter("boardseq");
//		�ش� �۷� �����ϱ� ���� �� �ۼ��� ������
		String writer=(String)session.getAttribute("pWriter");
		
		System.out.println("boardseq=> "+boardseq);
		System.out.println("writer=> "+writer);
		
//		��� ����Ʈ ���
		int cnt = service.comment(cvo);
		
		if (cnt > 0)//����
			mv.setViewName("redirect:bdetail?seq="+boardseq+"&writer="+writer);
		else {//����
			mv.addObject("resultID", "R");
			mv.setViewName("redirect:bdetail");
		}
		
		return mv;
	} // commentInsert
	
	/* ���� �ۼ� */
	@RequestMapping(value = "/recomment")
	public ModelAndView recomment(ModelAndView mv, CommentVO cvo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);// �̰� �м��� ����
		
//		�ش� ���� ���� ��ȣ
		String boardseq= request.getParameter("boardseq");
//		�ش� �۷� �����ϱ� ���� �� �ۼ��� ������
		String writer=(String)session.getAttribute("pWriter");
		
		System.out.println("boardseq=> "+boardseq);
		System.out.println("writer=> "+writer);
		
//		���� �ۼ� ����
		int cnt = service.recomment(cvo);
		
//		���� ����Ʈ
		ArrayList<CommentVO> relist = service.recommentList(request) ;
		 
		mv.addObject("ReComment",relist);
		
		if (cnt > 0)//����
			mv.setViewName("board/commentPlusList");
		else {//����
			mv.addObject("resultID", "R");
			mv.setViewName("redirect:bdetail");
		}
		
		return mv;
	} // recomment
	
	
	/* ��� ����Ʈ */
	@RequestMapping(value = "/commentList")
	public ModelAndView commentList(ModelAndView mv,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
//		���� ���� ��ȣ
		session.setAttribute("boardSeq", request.getParameter("boardseq"));
		
//		��� ����Ʈ ���
		ArrayList<CommentVO> list = service.commentList(request) ;
		
		mv.addObject("Comment",list);
		mv.setViewName("board/commentList");
		
		return mv;
	} // commentList 
	
	
	
	/* ���� ����Ʈ */
	@RequestMapping(value = "/commentPlusList")
	public ModelAndView commentPlusList(ModelAndView mv,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
//		��� ��ư�� value��
		int buttonVal = Integer.parseInt(request.getParameter("buttonVal"))+1;
		System.out.println("buttonVal => "+buttonVal);
		session.setAttribute("commentSeq",buttonVal);
		
//		����� ���� ��ȣ
		int compk = Integer.parseInt(request.getParameter("compk"));
		System.out.println("compk => "+compk);
		session.setAttribute("compk",compk);
		
//		���� ����Ʈ ���
		ArrayList<CommentVO> relist = service.recommentList(request) ;
		 
		mv.addObject("ReComment",relist);
		mv.setViewName("board/commentPlusList");
		
		return mv;
	} // commentPlusList 
	
	/* ���, ���� ���� */
	@RequestMapping(value = "/commentDelete")
	public ModelAndView commentDelete(HttpServletRequest request, ModelAndView mv, CommentVO cvo) {
		HttpSession session = request.getSession(false);
//		����� �ۼ��� ���� ���� ��ȣ
		String boardseq= request.getParameter("boardseq");
		
//		���� �� �ش� �Խñ۷� �����ϱ� ���� �� �ۼ��� ����
		String writer=(String)session.getAttribute("pWriter");
		
//		�׸� ���� �� �� ���,���۵� �Բ� �����ϱ� ���� ���� 
		cvo.setBoardseq(0);
		
//		"commentseq"�� null�� �ƴϸ� �����̱� ������ ���� ������ ����
		if(request.getParameter("commentseq")!=null) {
			int commentseq = Integer.parseInt(request.getParameter("commentseq"))+1;
			cvo.setRecommentseq(commentseq);
		}
		
//		���, ���� ���� ����
		int cnt = service.commentDelete(cvo);
		
		if (cnt > 0)//����
			mv.setViewName("redirect:bdetail?seq="+boardseq+"&writer="+writer);
		else {//���� 
			mv.addObject("resultID", "D");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // commentDelete
	
	/* ���, ���� ���� */
	@RequestMapping(value = "/commentUpdate")
	public ModelAndView commentUpdate(ModelAndView mv, CommentVO cvo) {
//		��� ���� ����
		int cnt = service.commentUpdate(cvo);
		
		if (cnt > 0) //����
			mv.setViewName("board/commentPlusList");
		else { //���� 
			mv.addObject("resultID", "U");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // commentUpdate
	
	/* ��ƿ ���̵� �� ���� ��ħ */
	@RequestMapping(value = "/sideBar")
	public ModelAndView sideBar(ModelAndView mv,HttpServletRequest request) {
//		���� ������
		String currPage = request.getParameter("currPage");
		System.out.println("sideBar�� Ŀ������=> "+currPage);
		
//		�������� �ٲ� �� ���� ��ƿ ���̵� �� ���� ��ħ 
		mv.addObject("currPage", currPage);
		
		mv.setViewName("UIutill/sideBar");
		
		return mv;
	} // sideBar
	
	/* ���� ���� Ȱ��ȭ */
	@RequestMapping(value = "/shareSetting")
	public ModelAndView shareSetting(ModelAndView mv) {
		mv.setViewName("share/shareSetting");
		
		return mv;
	} // shareSetting
	
	/* ���� ���� �ݱ� */
	@RequestMapping(value = "/resetShare")
	public ModelAndView resetShare(ModelAndView mv) {
		mv.setViewName("share/resetShare");
		
		return mv;
	} // resetShare
	
	/* ���� ���� */
	@RequestMapping(value = "/shareMySelf")
	public ModelAndView shareMySelf(ModelAndView mv) {
		mv.setViewName("share/shareMySelf");
		
		return mv;
	} // resetShare
	
	/* ���� �ϱ� */
	@RequestMapping(value = "/shareTogether")
	public ModelAndView shareTogether(ModelAndView mv,HttpServletRequest request) {
//		�� ģ�� ��� ���
		ArrayList<String> fList = fdao.myFriend(request) ;
		
		mv.addObject("Orange",fList);
		
		mv.setViewName("share/shareTogether");
		
		return mv;
	} // shareTogether
} // class
