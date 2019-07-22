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
	 
//	이전 페이지를 저장하기 위한 전역 변수
	int cp;
//	이전 검색의 옵션을 저장하기 위한 전역 변수
	String optKeep;
//	이전 검색 내용을 저장하기 위한 전역 변수
	String searchKeep;
//	이전 태그 값을 저장하기 위한 전역 변수
	String tagKeep;
	
	/* 게시글 목록 */
	@RequestMapping(value = "/blist")
	public ModelAndView blist(ModelAndView mv, PageVO pvo, SearchVO svo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
//		검색 옵션
		String opt = svo.getOpt();
//		검색 내용
		String search = svo.getSearch();
//		태그 검색값 저장할 변수
		String tag=null;
		
		if("tag".equals(opt)) {
//			검색 옵션이 태그 일 시
//			"tagValue"에 태그 검색 값 저장
			session.setAttribute("tagValue",search);
			svo.setTag(opt);
		}else if(opt==null&&search==null&&pvo.getCurrPage()==0){
//			검색없이 홈으로 이동
			session.setAttribute("tagValue",null);
		}else if("share_tag".equals(opt)||"myPost".equals(opt)||"post".equals(opt)){
//			개인 페이지나, '공유 글 보기'로 이동
			session.setAttribute("tagValue",null);
		}
		
//		페이지를 바꿀때마다 값들이 초기화 되기때문에 페이지를 바꾸기전 값들을 저장
		if (tag != null || opt != null) {
//			검색 할 경우
			tagKeep = tag;
			searchKeep = search;
			optKeep = opt;
			cp = pvo.getCurrPage();
		} else if (cp != pvo.getCurrPage() && pvo.getCurrPage() != 0) {
			// 이전 페이지의 데이터(cp)와 현재 페이지(currPage)가 다르면 
//			=> (페이지가 바뀌면) 이전 값들을 대입
			svo.setOpt(optKeep);
			svo.setSearch(searchKeep);
			svo.setTag(tagKeep);			
			cp = pvo.getCurrPage();
		} else {
//			홈
			tagKeep = null;
			searchKeep = null;
			optKeep = null;
		}
		
		System.out.println("currP => "+pvo.getCurrPage());
		
//		현재 페이지 계산
		int currPage = 1;
		if (pvo.getCurrPage() > 1) currPage = pvo.getCurrPage();
		else pvo.setCurrPage(currPage);
		
//		게시글 목록 출력
		pvo = service.pageList(pvo, svo, request);

//		전체 페이지 계산
		int totalPage = pvo.getTotalCount() / pvo.getPerPage();
//		총 게시글 갯수가 페이지당 게시글 갯수로 나누어 떨어지지 않으면
//		전체 페이지 하나 더 추가
		if ((pvo.getTotalCount() % pvo.getPerPage()) != 0) totalPage++;
		
//		검색 값을 검색창에 저장하기 위해 저장 
		mv.addObject("Search", search);
		mv.addObject("Opt", opt);
		
		mv.addObject("Banana", pvo.getList());
		
//		총 페이지, 현재 페이지 저장
		mv.addObject("totalPage", totalPage);
		mv.addObject("currPage", currPage);
		
		if(pvo.getCurrPage()>1){//현재 페이지가 2 이상이면 페이지 추가
			mv.setViewName("/board/pageList");
		}else {//1이면 홈
			mv.setViewName("home");
		}
		return mv;
	}// blist

	/* 게시글 작성 */
	@RequestMapping(value = "/binsert")
	public ModelAndView binsert(ModelAndView mv, BoardVO vo)
						throws ServletException, IOException{
//		이미지 데이터 저장
		List<MultipartFile> uploadfilef = vo.getUploadfilef() ;
		
		String file1 , file2 = "No Image" ;
		
//		최종적으로 DB에 입력되는 이미지 주소 값
		String resultFile="";
		
		for (MultipartFile mf : uploadfilef) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            if (fileSize!=0) {
    			
    			file1 = "C:/eclipse-workspace/Oeda/src/main/webapp/resources/uploadImage/"
    					+ mf.getOriginalFilename() ;
    			mf.transferTo(new File(file1));
//    			이미지 split을 위해 이미지 주소 앞에 #여기까지#을 붙임
    			file2 = "#여기까지#resources/uploadImage/" + mf.getOriginalFilename() ;
    			
//    			#여기까지#가 붙은 데이터들을 'resultFile'에 더해 줌
    			resultFile +=file2;
    		}
        }
		
//		사진 등록을 하는 경우에만 setUploadfile
		if (!uploadfilef.isEmpty()) {
			vo.setUploadfile(resultFile);
		}
		
//		공유 설정 데이터 저장
		List<String> share_tag_list = vo.getShare_tag_list();
		
		System.out.println("사이즈 => "+share_tag_list.size());
		System.out.println(share_tag_list);
//		글 작성자
		String writer = (String)vo.getWriter();
		
//		최종적으로 DB에 올라가는'result_Share_tag'에
//		기본적으로 자신의 이름을 등록 함
		String result_Share_tag="#공유시작#"+writer+"@끝@";
		
		if(share_tag_list.size()>1) {
//			공유자가 두명 이상이면 데이터를 더해줌
			for (String st : share_tag_list) {
				System.out.println(st);
				result_Share_tag +="#공유시작#"+st+"@끝@";
			}
			vo.setShare_tag(result_Share_tag);
		}else if(share_tag_list.size()==1) {
//			공유자가 한명인 경우
			String stl=share_tag_list.get(0);
			if(stl.equals(writer)) {
//				나만 보기 일 경우
				vo.setShare_tag(result_Share_tag);
			}else {
//				나만 보기가 아닌 경우 자신과 공유자만 등록
				result_Share_tag+="#공유시작#"+stl+"@끝@";
				vo.setShare_tag(result_Share_tag);
			}
		}
		
//		게시글 등록 실행
		int cnt = service.insert(vo);
		
		System.out.println(vo);
		
		if (cnt > 0) { //성공
			mv.addObject("test", "tttt");
			mv.setViewName("redirect:home");
		} else { //실패
			mv.addObject("resultID", "I");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // binsert
	
	/* 게시글 디테일 페이지 */
	@RequestMapping(value = "/bdetail")
	public ModelAndView bdetail(HttpServletRequest request, ModelAndView mv, BoardVO vo) {
		HttpSession session = request.getSession(false);
		
//		디테일 페이지 출력
		vo = service.detail(vo);
		
		if (session != null) {
			session.setAttribute("boardSeq", vo.getSeq());
			session.setAttribute("pWriter", vo.getWriter());
		} else {
			System.out.println("detail ==> session is null");
		}
		
//		댓글 리스트 출력
		ArrayList<CommentVO> list = service.commentList(request) ;
		 
		mv.addObject("Comment",list);

		mv.addObject("Yellow", vo);
		mv.setViewName("board/boardDetail");
		
		return mv;
	} // bdetail
	
	/* 게시글 작성 폼 */
	@RequestMapping(value = "/binsertf")
	public ModelAndView binsertf(ModelAndView mv) {
		mv.setViewName("board/binsertForm");
		return mv;
	} // binsertf
	
	/* 게시글 수정 */
	@RequestMapping(value = "/bupdate")
	public ModelAndView bupdate(ModelAndView mv, BoardVO vo) {
		
		System.out.println(vo.getSeq());
		System.out.println(vo.getContent());
		
//		글 수정 실행
		int cnt = service.update(vo);
		
		if (cnt > 0) //성공
			mv.setViewName("redirect:bdetail?seq="+vo.getSeq());
		else { //실패
			mv.addObject("resultID", "U");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // bupdate
	
	/* 게시글 삭제 */
	@RequestMapping(value = "/bdelete")
	public ModelAndView bdelete(ModelAndView mv, BoardVO vo,CommentVO cvo) {		
//		글 삭제 실행
		int cnt = service.delete(vo);
		
//		글의 고유 번호
		cvo.setBoardseq(vo.getSeq());
		
		if (cnt > 0) { //성공
//			글삭제 성공하면 글 안의 댓글도 삭제
//			댓글 삭제
			int cnt2 = service.commentDelete(cvo);
			
			mv.addObject("resultID", "DSuccess");
			mv.setViewName("board/boardFinish");
		}else { //실패
			mv.addObject("resultID", "D");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // bdelete
	
	/* 댓글 작성 */
	@RequestMapping(value = "/commentInsert")
	public ModelAndView commentInsert(ModelAndView mv, CommentVO cvo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
//		해당 글의 고유 번호
		String boardseq= request.getParameter("boardseq");
//		해당 글로 복귀하기 위한 글 작성자 데이터
		String writer=(String)session.getAttribute("pWriter");
		
		System.out.println("boardseq=> "+boardseq);
		System.out.println("writer=> "+writer);
		
//		댓글 리스트 출력
		int cnt = service.comment(cvo);
		
		if (cnt > 0)//성공
			mv.setViewName("redirect:bdetail?seq="+boardseq+"&writer="+writer);
		else {//실패
			mv.addObject("resultID", "R");
			mv.setViewName("redirect:bdetail");
		}
		
		return mv;
	} // commentInsert
	
	/* 대댓글 작성 */
	@RequestMapping(value = "/recomment")
	public ModelAndView recomment(ModelAndView mv, CommentVO cvo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);// 이거 분석해 보셈
		
//		해당 글의 고유 번호
		String boardseq= request.getParameter("boardseq");
//		해당 글로 복귀하기 위한 글 작성자 데이터
		String writer=(String)session.getAttribute("pWriter");
		
		System.out.println("boardseq=> "+boardseq);
		System.out.println("writer=> "+writer);
		
//		대댓글 작성 실행
		int cnt = service.recomment(cvo);
		
//		대댓글 리스트
		ArrayList<CommentVO> relist = service.recommentList(request) ;
		 
		mv.addObject("ReComment",relist);
		
		if (cnt > 0)//성공
			mv.setViewName("board/commentPlusList");
		else {//실패
			mv.addObject("resultID", "R");
			mv.setViewName("redirect:bdetail");
		}
		
		return mv;
	} // recomment
	
	
	/* 댓글 리스트 */
	@RequestMapping(value = "/commentList")
	public ModelAndView commentList(ModelAndView mv,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
//		글의 고유 번호
		session.setAttribute("boardSeq", request.getParameter("boardseq"));
		
//		댓글 리스트 출력
		ArrayList<CommentVO> list = service.commentList(request) ;
		
		mv.addObject("Comment",list);
		mv.setViewName("board/commentList");
		
		return mv;
	} // commentList 
	
	
	
	/* 대댓글 리스트 */
	@RequestMapping(value = "/commentPlusList")
	public ModelAndView commentPlusList(ModelAndView mv,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
//		답글 버튼의 value값
		int buttonVal = Integer.parseInt(request.getParameter("buttonVal"))+1;
		System.out.println("buttonVal => "+buttonVal);
		session.setAttribute("commentSeq",buttonVal);
		
//		댓글의 고유 번호
		int compk = Integer.parseInt(request.getParameter("compk"));
		System.out.println("compk => "+compk);
		session.setAttribute("compk",compk);
		
//		대댓글 리스트 출력
		ArrayList<CommentVO> relist = service.recommentList(request) ;
		 
		mv.addObject("ReComment",relist);
		mv.setViewName("board/commentPlusList");
		
		return mv;
	} // commentPlusList 
	
	/* 댓글, 대댓글 삭제 */
	@RequestMapping(value = "/commentDelete")
	public ModelAndView commentDelete(HttpServletRequest request, ModelAndView mv, CommentVO cvo) {
		HttpSession session = request.getSession(false);
//		댓글이 작성된 글의 고유 번호
		String boardseq= request.getParameter("boardseq");
		
//		삭제 후 해당 게시글로 복귀하기 위한 글 작성자 저장
		String writer=(String)session.getAttribute("pWriter");
		
//		그링 삭제 될 때 댓글,대댓글도 함께 삭제하기 위한 설정 
		cvo.setBoardseq(0);
		
//		"commentseq"가 null이 아니면 대댓글이기 때문에 대댓글 삭제를 뜻함
		if(request.getParameter("commentseq")!=null) {
			int commentseq = Integer.parseInt(request.getParameter("commentseq"))+1;
			cvo.setRecommentseq(commentseq);
		}
		
//		댓글, 대댓글 삭제 실행
		int cnt = service.commentDelete(cvo);
		
		if (cnt > 0)//성공
			mv.setViewName("redirect:bdetail?seq="+boardseq+"&writer="+writer);
		else {//실패 
			mv.addObject("resultID", "D");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // commentDelete
	
	/* 댓글, 대댓글 수정 */
	@RequestMapping(value = "/commentUpdate")
	public ModelAndView commentUpdate(ModelAndView mv, CommentVO cvo) {
//		댓글 수정 실행
		int cnt = service.commentUpdate(cvo);
		
		if (cnt > 0) //성공
			mv.setViewName("board/commentPlusList");
		else { //실패 
			mv.addObject("resultID", "U");
			mv.setViewName("board/boardFinish");
		}
		
		return mv;
	} // commentUpdate
	
	/* 유틸 사이드 바 새로 고침 */
	@RequestMapping(value = "/sideBar")
	public ModelAndView sideBar(ModelAndView mv,HttpServletRequest request) {
//		현재 페이지
		String currPage = request.getParameter("currPage");
		System.out.println("sideBar의 커페이지=> "+currPage);
		
//		페이지가 바뀔 때 마다 유틸 사이드 바 새로 고침 
		mv.addObject("currPage", currPage);
		
		mv.setViewName("UIutill/sideBar");
		
		return mv;
	} // sideBar
	
	/* 공유 설정 활성화 */
	@RequestMapping(value = "/shareSetting")
	public ModelAndView shareSetting(ModelAndView mv) {
		mv.setViewName("share/shareSetting");
		
		return mv;
	} // shareSetting
	
	/* 공유 설정 닫기 */
	@RequestMapping(value = "/resetShare")
	public ModelAndView resetShare(ModelAndView mv) {
		mv.setViewName("share/resetShare");
		
		return mv;
	} // resetShare
	
	/* 나만 보기 */
	@RequestMapping(value = "/shareMySelf")
	public ModelAndView shareMySelf(ModelAndView mv) {
		mv.setViewName("share/shareMySelf");
		
		return mv;
	} // resetShare
	
	/* 공유 하기 */
	@RequestMapping(value = "/shareTogether")
	public ModelAndView shareTogether(ModelAndView mv,HttpServletRequest request) {
//		내 친구 목록 출력
		ArrayList<String> fList = fdao.myFriend(request) ;
		
		mv.addObject("Orange",fList);
		
		mv.setViewName("share/shareTogether");
		
		return mv;
	} // shareTogether
} // class
