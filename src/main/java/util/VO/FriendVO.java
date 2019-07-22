package util.VO;
import java.util.List;
public class FriendVO {
	
//	친구 요청자
	private String master_user ;
//	요청 받는 유저
	private String friend_user ;
//	다중 친구 신청 리스트
	private List<String> friend_user_list;
//	친구 요청
	private String friend_request ;
//	다중 친구 수락 리스트
	private List<String> friend_request_list;
//	요청, 수락을 구분하기 위한 request_status 0이면 요청 1이면 수락
	private String request_status ;
//	내 친구 목록
	private String myFriend ;
//	나의 친구 요청 목록
	private String myFriendRequest ;
	
	public String getMaster_user() {
		return master_user;
	}
	public void setMaster_user(String master_user) {
		this.master_user = master_user;
	}
	public String getFriend_user() {
		return friend_user;
	}
	public void setFriend_user(String friend_user) {
		this.friend_user = friend_user;
	}
	public String getRequest_status() {
		return request_status;
	}
	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}
	public List<String> getFriend_user_list() {
		return friend_user_list;
	}
	public void setFriend_user_list(List<String> friend_user_list) {
		this.friend_user_list = friend_user_list;
	}
	public String getFriend_request() {
		return friend_request;
	}
	public void setFriend_request(String friend_request) {
		this.friend_request = friend_request;
	}
	public List<String> getFriend_request_list() {
		return friend_request_list;
	}
	public void setFriend_request_list(List<String> friend_request_list) {
		this.friend_request_list = friend_request_list;
	}
	public String getMyFriend() {
		return myFriend;
	}
	public void setMyFriend(String myFriend) {
		this.myFriend = myFriend;
	}
	public String getMyFriendRequest() {
		return myFriendRequest;
	}
	public void setMyFriendRequest(String myFriendRequest) {
		this.myFriendRequest = myFriendRequest;
	}
	@Override
	public String toString() {
		return "FriendVO [master_user=" + master_user + ", friend_user=" + friend_user + ", request_status="
				+ request_status + ", friend_user_list=" + friend_user_list + ", friend_request=" + friend_request
				+ ", friend_request_list=" + friend_request_list + ", myFriend=" + myFriend + ", myFriendRequest="
				+ myFriendRequest + "]";
	}
}
