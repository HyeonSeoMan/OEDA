package business;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import util.VO.BoardVO;
import util.VO.CommentVO;
import util.VO.FriendVO;
import util.VO.MemberVO;
import util.VO.PageVO;
import util.VO.SearchVO;

public interface BService {

	public BoardVO detail(BoardVO vo);

	public int insert(BoardVO vo);

	public int update(BoardVO vo);
	
	public int commentUpdate(CommentVO cvo);

	public int delete(BoardVO vo);
	
	public int allBoardDelete(MemberVO vo);
	
	public int commentDelete(CommentVO cvo);
	
	public int allCommentDelete(MemberVO vo);

	public int comment(CommentVO cvo);
	
	public int recomment(CommentVO cvo);
	
	public ArrayList<CommentVO> commentList(HttpServletRequest request);
	
	public ArrayList<CommentVO> recommentList(HttpServletRequest request);
	
	public PageVO pageList(PageVO pvo, SearchVO svo, HttpServletRequest request);
} // BService
