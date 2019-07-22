package business;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.DAO.BoardDAO;
import util.VO.BoardVO;
import util.VO.CommentVO;
import util.VO.MemberVO;
import util.VO.PageVO;
import util.VO.SearchVO;
@Service("board")
public class BServiceImpl implements BService {
	
	@Autowired
	private BoardDAO dao ;
	
	public PageVO pageList(PageVO pvo,SearchVO svo, HttpServletRequest request) {
		return dao.pageList(pvo, svo, request);
	}
	public BoardVO detail(BoardVO vo)  {
		return dao.detail(vo);
	}
	public int insert(BoardVO vo)  {
		return dao.insert(vo);
	}
	public int update(BoardVO vo) {
		return dao.update(vo);
	}
	
	public int commentUpdate(CommentVO cvo) {
		return dao.commentUpdate(cvo);
	}
	
	public int delete(BoardVO vo)  {
		return dao.delete(vo);
	}
	
	public int allBoardDelete(MemberVO vo) {
		return dao.allBoardDelete(vo) ;
	}
	
	public int commentDelete(CommentVO cvo) {
		return dao.commentDelete(cvo) ;
	}
	
	public int allCommentDelete(MemberVO vo) {
		return dao.allCommentDelete(vo) ;
	}
	
	public int comment(CommentVO cvo){
		return dao.comment(cvo);
	}
	
	public int recomment(CommentVO cvo){
		return dao.recomment(cvo);
	}
	
	public ArrayList<CommentVO> commentList(HttpServletRequest request){
		return dao.commentList(request);
	}
	
	public ArrayList<CommentVO> recommentList(HttpServletRequest request){
		return dao.recommentList(request);
	}
	
} // BServiceImpl
