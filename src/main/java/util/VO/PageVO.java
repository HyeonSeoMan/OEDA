package util.VO;
import java.util.List;
public class PageVO {
	
//	�Խñ� ������ ����Ʈ
	private List<BoardVO> list;
//	���� ������
	private int currPage;
//	������ �� �Խñ� ����
	private int perPage=10;
//	�� �Խñ� ����
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
