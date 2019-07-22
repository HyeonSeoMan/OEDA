package util.VO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
//	아이디
	private String id;
//	비밀 번호
	private String password;
//	프로필 이미지
	private String uploadfile ;
//	이미지 받아서 저장
	private MultipartFile uploadfilef ; 
	
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	public MultipartFile getUploadfilef() {
		return uploadfilef;
	}
	public void setUploadfilef(MultipartFile uploadfilef) {
		this.uploadfilef = uploadfilef;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", uploadfile=" + uploadfile + ", uploadfilef="
				+ uploadfilef + "]";
	}
} // memberVO
