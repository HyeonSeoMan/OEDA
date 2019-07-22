package util.VO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
public class BoardVO {
	
//	글 고유 번호
	private int seq ;
//	글 작성자
	private String writer ;
//	글의 태그(게시판의 개념)
	private String tag;
//	글 내용
	private String content ;
//	작성 일자
	private String regdate ;
//	사진 등록
	private String uploadfile ;
//	다중 사진 리스트
	private List<MultipartFile> uploadfilef ;
//	공유된 유저
	private String share_tag;
//	다중 공유 리스트
	private List<String> share_tag_list;
//	방명록 작성자
	private String visitor ;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	public List<MultipartFile> getUploadfilef() {
		return uploadfilef;
	}
	public void setUploadfilef(List<MultipartFile> uploadfilef) {
		this.uploadfilef = uploadfilef;
	}
	public String getShare_tag() {
		return share_tag;
	}
	public void setShare_tag(String share_tag) {
		this.share_tag = share_tag;
	}
	public List<String> getShare_tag_list() {
		return share_tag_list;
	}
	public void setShare_tag_list(List<String> share_tag_list) {
		this.share_tag_list = share_tag_list;
	}
	public String getVisitor() {
		return visitor;
	}
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", writer=" + writer + ", tag=" + tag + ", content=" + content + ", regdate="
				+ regdate + ", uploadfile=" + uploadfile + ", uploadfilef=" + uploadfilef + ", share_tag=" + share_tag
				+ ", share_tag_list=" + share_tag_list + ", visitor=" + visitor + "]";
	}
} // class
