package util.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import util.JDBCUtil;
import util.VO.BoardVO;
import util.VO.CommentVO;
import util.VO.MemberVO;
import util.VO.PageVO;
import util.VO.SearchVO;

@Repository
public class BoardDAO {
	private Connection cn = JDBCUtil.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
//	검색 값을 호출하는 sql문을 저장하기 위한 전역 변수
	String sqlTb;
	
	/* 게시글 갯수 반환 */
	public int totalCount() {
		String sqlT = "select count(*) from ("+sqlTb+")";
		int cnt = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sqlT);
			if (rs.next())
				cnt = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("board totalCount Exception : " + e.toString());
		}
		return cnt;
	} // totalCount
	
	/* 게시글 목록 출력 */
	public PageVO pageList(PageVO pvo,SearchVO svo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
//		검색 옵션
		String opt = svo.getOpt();
//		검색 내용
		String search = svo.getSearch();
//		태그로 검색된 값
		String tag= svo.getTag();
		
		System.out.println("opt => " + opt);
		System.out.println("search => " + search);
		System.out.println("tag => " + tag);
		
//		유지되는 태그값
		String tagValue = (String)session.getAttribute("tagValue");
		
		/* 검색 내용에 따른 sql문 */
		
		if (opt == null && tag==null) {
//			검색안하고 홈으로 갈 때
			System.out.println("둘다 null");
			
//			개인 페이지가 아닌 경우 "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			'share_tag'가 붙은 글은 비밀 글이기 때문에 홈에서 출력 안됨
//			'visitor'가 붙은 글도 방명록이기 때문에 개인 페이지에서만 확인 가능
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor"
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+" where share_tag is null and visitor is null)";
		}else if(!"tag".equals(opt) && tagValue!=null){
//			유지된 값이 있고 검색도 할 때
			System.out.println("태그 유지되고 검색");
			
//			개인 페이지가 아닌 경우 "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			태그가 유지되면서 검색 할 때는 tag like '%" + tagValue+"%'가 계속 유지됨
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where "+ opt+ " like '%" + search + "%' and tag like '%" + tagValue+"%'"
					+" and share_tag is null and visitor is null)";
		}else if ("share_tag".equals(opt)) {
//			공유글 볼 때
			System.out.println("공유 검색");
			
//			개인 페이지가 아닌 경우 "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			'%#공유시작#" + search +"@끝@%'를 사용해서 나의 비밀글에 접근 할 수 있음
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where "+ opt+ " like '%#공유시작#" + search +"@끝@%')";
		}else if ("myPost".equals(opt)) {
//			내가 쓴 글 볼 때
			System.out.println("내가 쓴 글");
			
//			마이 페이지 일 경우 "personalPage"=내 이름
			session.setAttribute("personalPage",search);
			
//			writer = "search", search에는 나의 이름이 들어감
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where writer = '"+search+"')";
		}else if ("post".equals(opt)) {
//			남이 쓴 글 볼 때
			System.out.println("작성자 선택");
			
//			다른 유저의 페이지 일 경우 "personalPage"=해당 유저의 이름
			session.setAttribute("personalPage",search);
			
//			search에는 해당 유저의 이름이 들어감
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where writer = '"+search
					+"' and share_tag is null)";
		} else if (tag!=null) {
//			태그 검색 만 했을 때
			System.out.println("태그만 검색");
			
//			개인 페이지가 아닌 경우 "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			태그는 게시판 역할을 함 ex)고양이 게시판, 강아지 게시판
//			그러나 이 프로젝트에서의 태그는 사용자들이 등록 할 수 있다는 차이점이 있음
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b" 
					+ " where tag like '%" + search + "%'"
					+" and share_tag is null)";
		}else if (opt!=null) {
//			검색할 때
			System.out.println("그냥 검색");
			
//			개인 페이지가 아닌 경우 "personalPage"=null
			session.setAttribute("personalPage",null);
			
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where "+ opt+ " like '%" + search + "%'"
					+" and share_tag is null and visitor is null)";
		}else {
//			예상 못한 오류
			session.setAttribute("personalPage",null);
			sql = null;
		}
		
//		위에서 산출된 sql문을 'sqlTb'라는 전역변수에 넣어서 'totalCount()'로 보내는 이유는
//		페이징을 한 후에 보내면 정확한 갯수를 반환하지 못하기 때문에 페이징을 하기전의 데이터를 계산하고
//		총 갯수 계산을 마친 후에 페이징을 하는 것임
		sqlTb=sql;
		
//		게시글 총 갯수 반환
		pvo.setTotalCount(totalCount());
		
		System.out.println("totalCount => " + pvo.getTotalCount());
		System.out.println("sql => " + sql);
		
		try {
//			start rownum : sno
			int sno = ((pvo.getCurrPage() - 1) * pvo.getPerPage()) + 1;
//			end rownum : eno
//			종료 위치 : 시작위치 + page당 출력 갯수
			int eno = sno + pvo.getPerPage();
			
//			여기서 " where rnum >=? and rnum <?"을 기존 sql문에 더해서 페이징 마무리
			pst = cn.prepareStatement(sql+" where rnum >=? and rnum <?");
			pst.setInt(1, sno);
			pst.setInt(2, eno);
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					BoardVO vo = new BoardVO();
					vo.setSeq(rs.getInt(1));
					vo.setWriter(rs.getString(2));
					vo.setContent(rs.getString(3));
					vo.setRegdate(rs.getString(4));
					vo.setTag(rs.getString(5));
					vo.setUploadfile(rs.getString(6));
					vo.setShare_tag(rs.getString(7));
					vo.setVisitor(rs.getString(8));
					list.add(vo);
				} while (rs.next());
				pvo.setList(list);
			} else
				System.out.println("pageList: 출력할 자료가 없습니다. ~~~");
		} catch (Exception e) {
			System.out.println("pageList Exception : " + e.toString());
			pvo = null;
		}
		return pvo;
	} // pageList 
	
	/* 글 작성 */
	public int insert(BoardVO vo) {
		sql = "insert into board values " 
				+ "((select nvl(max(seq), 0)+1 from board),"
				+ "?,?,sysdate,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getWriter());
			pst.setString(2, vo.getContent());
			pst.setString(3, vo.getTag());
			pst.setString(4,vo.getUploadfile());
			pst.setString(5,vo.getShare_tag());
			pst.setString(6,vo.getVisitor());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("board Insert Exception : " + e.toString());
			return 0;
		}
	} // insert
	
	/* 게시글 디테일 페이지 출력 */
	public BoardVO detail(BoardVO vo) {
		sql = "select seq,writer,content,"
				+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,visitor from board where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			rs = pst.executeQuery();
			if (rs.next()) {
				vo.setSeq(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setContent(rs.getString(3));  
				vo.setRegdate(rs.getString(4));
				vo.setTag(rs.getString(5));
				vo.setUploadfile(rs.getString(6));
				vo.setVisitor(rs.getString(7));
			} else {
				vo = null;
				System.out.println("** board Detail 의 출력 자료가 없습니다. ~~");
			}
		} catch (Exception e) {
			System.out.println("** board Detail Exception : " + e.toString());
		}
		return vo;
	} // detail
	
	/* 글 수정 */
	public int update(BoardVO vo) {
		sql = "update board set content=? where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getContent());
			pst.setInt(2, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardUpdate Exception =>" + e.toString());
			return 0;
		}
	} // update
	
	/* 글 삭제 */
	public int delete(BoardVO vo) {
		sql = "delete board where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardDelete Exception =>" + e.toString());
			return 0;
		}
	} // delete
	
	/* 댓글 입력 */
	public int comment(CommentVO cvo) {
//		해당 글의 'boardseq'와 같은 데이터를 가지고
//		'commentseq'는 순차적으로 등록 됨(ex)2가 최고 숫자로 등록 돼 있을 경우 3으로 등록)
//		'recommentseq'는 0으로 등록
		sql = "insert into bcomment values " 
				+ "((select nvl(max(commentseq),0)+1 from bcomment where boardseq=?),?,?,?,sysdate,0,"
				+"(select nvl(max(compk),0)+1 from bcomment),"
				+ "(select writer from board where seq=?))";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, cvo.getBoardseq());
			pst.setString(2, cvo.getContent());
			pst.setString(3, cvo.getWriter());
			pst.setInt(4, cvo.getBoardseq());
			pst.setInt(5, cvo.getBoardseq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("comment Exception =>" + e.toString());
			return 0;
		}
	} // comment
	
	/* 대댓글 입력 */
	public int recomment(CommentVO cvo) {
//		해당 글의 'boardseq', 해당 댓글의 'commentseq'와 같은 데이터를 가지고
//		'recommentseq'는 순차적으로 등록 됨(ex)2가 최고 숫자로 등록 돼 있을 경우 3으로 등록)
		sql = "insert into bcomment values " 
				+ "((select commentseq from bcomment where compk=?),?,?,?,sysdate,"
				+"(select nvl(max(recommentseq),0)+1 from bcomment where boardseq=? and "
				+"commentseq=(select commentseq from bcomment where compk=?)),"
				+"(select nvl(max(compk),0)+1 from bcomment),"
				+ "(select writer from bcomment where compk=?))";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, cvo.getCompk());
			pst.setString(2, cvo.getContent());
			pst.setString(3, cvo.getWriter());
			pst.setInt(4, cvo.getBoardseq());
			pst.setInt(5, cvo.getBoardseq());
			pst.setInt(6, cvo.getCompk());
			pst.setInt(7, cvo.getCompk());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("recomment Exception =>" + e.toString());
			return 0;
		}
	} // recomment
	
	/* 댓글 리스트 출력 */
	public ArrayList<CommentVO> commentList(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		ArrayList<CommentVO> list = new ArrayList<CommentVO>();
		
//		'recommentseq'가 '0'이고 해당 글의 'boardseq'와 같은 데이터 출력
		sql = "select content,writer,"
				+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,compk,parent"
				+ " from bcomment"
				+ " where boardseq='"+session.getAttribute("boardSeq")
				+"' and recommentseq = 0"
				+" order by commentseq";
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {

				do { 
					CommentVO cvo = new CommentVO();
					cvo.setContent(rs.getString(1));
					cvo.setWriter(rs.getString(2));
					cvo.setRegdate(rs.getString(3));
					cvo.setCompk(rs.getInt(4));
					cvo.setParent(rs.getString(5));
					list.add(cvo);
				} while (rs.next());
			} else {
				System.out.println("commentList: 출력할 자료가 없습니다.");
			}
		} catch (Exception e) {
			System.out.println("commentList Exception : " + e.toString());
		} // catch
		return list;
	} // commentList
	
	/* 대댓글 목록 출력 */
	public ArrayList<CommentVO> recommentList(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		ArrayList<CommentVO> relist = new ArrayList<CommentVO>();
		
		System.out.println("boardSeq => " +session.getAttribute("boardSeq"));
		System.out.println("compk => " +session.getAttribute("compk"));
		
//		'recommentseq'가 '0'이 아니고 해당 글의 'boardseq', 해당 댓글의 'commentseq'와 같은 데이터 출력
		sql = "select content,writer,"
				+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,compk,parent"
				+ " from bcomment"
				+ " where boardseq='"+session.getAttribute("boardSeq")
				+"' and commentseq= (select commentseq from bcomment where compk="+session.getAttribute("compk")
				+") and recommentseq !=0"
				+" order by recommentseq";
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {

				do { 
					CommentVO cvo = new CommentVO();
					cvo.setContent(rs.getString(1));
					cvo.setWriter(rs.getString(2));
					cvo.setRegdate(rs.getString(3));
					cvo.setCompk(rs.getInt(4));
					cvo.setParent(rs.getString(5));
					relist.add(cvo);
				} while (rs.next());
			} else {
				System.out.println("recommentList: 출력할 자료가 없습니다.");
			}
		} catch (Exception e) {
			System.out.println("recommentList Exception : " + e.toString());
		} // catch
		return relist;
	} // recommentList
	 
	/* 댓글, 대댓글 삭제 */
	public int commentDelete(CommentVO cvo) {
		if(cvo.getCommentseq()>0) {
//			대댓글 삭제
			sql = "delete bcomment where compk=?";
		}else if(cvo.getCommentseq()==0 && cvo.getBoardseq()==0){
//			댓글, 댓글에 달린 대댓글 삭제
			sql = "delete bcomment where commentseq="
					+"(select commentseq from bcomment where compk=?)";
		}else if(cvo.getBoardseq()>0) {
//			글삭제할때 같이 삭제
			sql = "delete bcomment where boardseq=?";
		}
		try {
			pst = cn.prepareStatement(sql);
			if(cvo.getBoardseq()>0) pst.setInt(1, cvo.getBoardseq());
			else pst.setInt(1, cvo.getCompk());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** commentDelete Exception =>" + e.toString());
			return 0;
		}
	} // commentDelete
	
	/* 댓글, 대댓글 수정 */
	public int commentUpdate(CommentVO cvo) {
		sql = "update bcomment set content=? where compk=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, cvo.getContent());
			pst.setInt(2, cvo.getCompk());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** commentUpdate Exception =>" + e.toString());
			return 0;
		}
	} // commentUpdate
	
	/* 회원 탈퇴 시 모든 글 삭제 */
	public int allBoardDelete(MemberVO vo) {
		sql = "delete board where writer=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** allBoardDelete Exception =>" + e.toString());
			return 0;
		}
	} // delete
	
	/* 회원 탈퇴 시 나와 관련된 모든 댓글 삭제 */
	public int allCommentDelete(MemberVO vo) {
		sql = "delete bcomment where parent=? or writer=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			pst.setString(2, vo.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** allCommentDelete Exception =>" + e.toString());
			return 0;
		}
	} // allCommentDelete
	
} // DAO
