package util.VO;
public class SearchVO {
	
//	검색 옵션
	private String opt ;
//	검색 내용
	private String search ;
//	태그로 저장된 검색 내용
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
