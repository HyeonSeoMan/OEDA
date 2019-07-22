package util.VO;
import java.util.List;
public class PageVO {
	
//	게시글 데이터 리스트
	private List<BoardVO> list;
//	현재 페이지
	private int currPage;
//	페이지 당 게시글 갯수
	private int perPage=10;
//	총 게시글 갯수
	private int totalCount;
	private String regdate ;
	
	public List<BoardVO> getList() {
		return list;
	}
	public void setList(List<BoardVO> list) {
		this.list = list;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "PageVO [list=" + list + ", currPage=" + currPage + ", perPage=" + perPage + ", totalCount=" + totalCount
				+ ", regdate=" + regdate + "]";
	}
}
