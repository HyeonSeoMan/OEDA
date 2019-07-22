package business;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.DAO.BoardDAO;
import util.DAO.MemberDAO;
import util.VO.MemberVO;

@Service("member")
public class MServiceImple implements MService {
	
	@Autowired
	private MemberDAO dao ;
	
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return dao.loginCheck(vo);
	}
	
	@Override
	public int insert(MemberVO vo) {
		return dao.mInsert(vo);
	}
	
	@Override
	public int update(MemberVO vo) {
		return dao.mUpdate(vo);
	}
	@Override
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	}
}
