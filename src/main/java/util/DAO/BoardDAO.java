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
	
//	�˻� ���� ȣ���ϴ� sql���� �����ϱ� ���� ���� ����
	String sqlTb;
	
	/* �Խñ� ���� ��ȯ */
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
	
	/* �Խñ� ��� ��� */
	public PageVO pageList(PageVO pvo,SearchVO svo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
//		�˻� �ɼ�
		String opt = svo.getOpt();
//		�˻� ����
		String search = svo.getSearch();
//		�±׷� �˻��� ��
		String tag= svo.getTag();
		
		System.out.println("opt => " + opt);
		System.out.println("search => " + search);
		System.out.println("tag => " + tag);
		
//		�����Ǵ� �±װ�
		String tagValue = (String)session.getAttribute("tagValue");
		
		/* �˻� ���뿡 ���� sql�� */
		
		if (opt == null && tag==null) {
//			�˻����ϰ� Ȩ���� �� ��
			System.out.println("�Ѵ� null");
			
//			���� �������� �ƴ� ��� "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			'share_tag'�� ���� ���� ��� ���̱� ������ Ȩ���� ��� �ȵ�
//			'visitor'�� ���� �۵� �����̱� ������ ���� ������������ Ȯ�� ����
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor"
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+" where share_tag is null and visitor is null)";
		}else if(!"tag".equals(opt) && tagValue!=null){
//			������ ���� �ְ� �˻��� �� ��
			System.out.println("�±� �����ǰ� �˻�");
			
//			���� �������� �ƴ� ��� "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			�±װ� �����Ǹ鼭 �˻� �� ���� tag like '%" + tagValue+"%'�� ��� ������
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where "+ opt+ " like '%" + search + "%' and tag like '%" + tagValue+"%'"
					+" and share_tag is null and visitor is null)";
		}else if ("share_tag".equals(opt)) {
//			������ �� ��
			System.out.println("���� �˻�");
			
//			���� �������� �ƴ� ��� "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			'%#��������#" + search +"@��@%'�� ����ؼ� ���� ��бۿ� ���� �� �� ����
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where "+ opt+ " like '%#��������#" + search +"@��@%')";
		}else if ("myPost".equals(opt)) {
//			���� �� �� �� ��
			System.out.println("���� �� ��");
			
//			���� ������ �� ��� "personalPage"=�� �̸�
			session.setAttribute("personalPage",search);
			
//			writer = "search", search���� ���� �̸��� ��
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where writer = '"+search+"')";
		}else if ("post".equals(opt)) {
//			���� �� �� �� ��
			System.out.println("�ۼ��� ����");
			
//			�ٸ� ������ ������ �� ��� "personalPage"=�ش� ������ �̸�
			session.setAttribute("personalPage",search);
			
//			search���� �ش� ������ �̸��� ��
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where writer = '"+search
					+"' and share_tag is null)";
		} else if (tag!=null) {
//			�±� �˻� �� ���� ��
			System.out.println("�±׸� �˻�");
			
//			���� �������� �ƴ� ��� "personalPage"=null
			session.setAttribute("personalPage",null);
			
//			�±״� �Խ��� ������ �� ex)����� �Խ���, ������ �Խ���
//			�׷��� �� ������Ʈ������ �±״� ����ڵ��� ��� �� �� �ִٴ� �������� ����
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b" 
					+ " where tag like '%" + search + "%'"
					+" and share_tag is null)";
		}else if (opt!=null) {
//			�˻��� ��
			System.out.println("�׳� �˻�");
			
//			���� �������� �ƴ� ��� "personalPage"=null
			session.setAttribute("personalPage",null);
			
			sql = "select seq,writer,content,"
					+ "to_char(regdate,'yy/mm/dd/ hh24:mi') regdate,tag,uploadfile,share_tag,visitor" 
					+ " from (select b.* , ROW_NUMBER() OVER(order by seq desc) rnum from board b"
					+ " where "+ opt+ " like '%" + search + "%'"
					+" and share_tag is null and visitor is null)";
		}else {
//			���� ���� ����
			session.setAttribute("personalPage",null);
			sql = null;
		}
		
//		������ ����� sql���� 'sqlTb'��� ���������� �־ 'totalCount()'�� ������ ������
//		����¡�� �� �Ŀ� ������ ��Ȯ�� ������ ��ȯ���� ���ϱ� ������ ����¡�� �ϱ����� �����͸� ����ϰ�
//		�� ���� ����� ��ģ �Ŀ� ����¡�� �ϴ� ����
		sqlTb=sql;
		
//		�Խñ� �� ���� ��ȯ
		pvo.setTotalCount(totalCount());
		
		System.out.println("totalCount => " + pvo.getTotalCount());
		System.out.println("sql => " + sql);
		
		try {
//			start rownum : sno
			int sno = ((pvo.getCurrPage() - 1) * pvo.getPerPage()) + 1;
//			end rownum : eno
//			���� ��ġ : ������ġ + page�� ��� ����
			int eno = sno + pvo.getPerPage();
			
//			���⼭ " where rnum >=? and rnum <?"�� ���� sql���� ���ؼ� ����¡ ������
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
				System.out.println("pageList: ����� �ڷᰡ �����ϴ�. ~~~");
		} catch (Exception e) {
			System.out.println("pageList Exception : " + e.toString());
			pvo = null;
		}
		return pvo;
	} // pageList 
	
	/* �� �ۼ� */
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
	
	/* �Խñ� ������ ������ ��� */
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
				System.out.println("** board Detail �� ��� �ڷᰡ �����ϴ�. ~~");
			}
		} catch (Exception e) {
			System.out.println("** board Detail Exception : " + e.toString());
		}
		return vo;
	} // detail
	
	/* �� ���� */
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
	
	/* �� ���� */
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
	
	/* ��� �Է� */
	public int comment(CommentVO cvo) {
//		�ش� ���� 'boardseq'�� ���� �����͸� ������
//		'commentseq'�� ���������� ��� ��(ex)2�� �ְ� ���ڷ� ��� �� ���� ��� 3���� ���)
//		'recommentseq'�� 0���� ���
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
	
	/* ���� �Է� */
	public int recomment(CommentVO cvo) {
//		�ش� ���� 'boardseq', �ش� ����� 'commentseq'�� ���� �����͸� ������
//		'recommentseq'�� ���������� ��� ��(ex)2�� �ְ� ���ڷ� ��� �� ���� ��� 3���� ���)
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
	
	/* ��� ����Ʈ ��� */
	public ArrayList<CommentVO> commentList(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		ArrayList<CommentVO> list = new ArrayList<CommentVO>();
		
//		'recommentseq'�� '0'�̰� �ش� ���� 'boardseq'�� ���� ������ ���
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
				System.out.println("commentList: ����� �ڷᰡ �����ϴ�.");
			}
		} catch (Exception e) {
			System.out.println("commentList Exception : " + e.toString());
		} // catch
		return list;
	} // commentList
	
	/* ���� ��� ��� */
	public ArrayList<CommentVO> recommentList(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		ArrayList<CommentVO> relist = new ArrayList<CommentVO>();
		
		System.out.println("boardSeq => " +session.getAttribute("boardSeq"));
		System.out.println("compk => " +session.getAttribute("compk"));
		
//		'recommentseq'�� '0'�� �ƴϰ� �ش� ���� 'boardseq', �ش� ����� 'commentseq'�� ���� ������ ���
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
				System.out.println("recommentList: ����� �ڷᰡ �����ϴ�.");
			}
		} catch (Exception e) {
			System.out.println("recommentList Exception : " + e.toString());
		} // catch
		return relist;
	} // recommentList
	 
	/* ���, ���� ���� */
	public int commentDelete(CommentVO cvo) {
		if(cvo.getCommentseq()>0) {
//			���� ����
			sql = "delete bcomment where compk=?";
		}else if(cvo.getCommentseq()==0 && cvo.getBoardseq()==0){
//			���, ��ۿ� �޸� ���� ����
			sql = "delete bcomment where commentseq="
					+"(select commentseq from bcomment where compk=?)";
		}else if(cvo.getBoardseq()>0) {
//			�ۻ����Ҷ� ���� ����
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
	
	/* ���, ���� ���� */
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
	
	/* ȸ�� Ż�� �� ��� �� ���� */
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
	
	/* ȸ�� Ż�� �� ���� ���õ� ��� ��� ���� */
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
