package util.VO;
public class SearchVO {
	
//	�˻� �ɼ�
	private String opt ;
//	�˻� ����
	private String search ;
//	�±׷� ����� �˻� ����
	private String tag ;
	
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "SearchVO [opt=" + opt + ", search=" + search + ", tag=" + tag + "]";
	}
}
