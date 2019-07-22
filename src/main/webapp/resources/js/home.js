function memberDelete(id) {
	/*회원 탈퇴 최종 확인*/
	 if (confirm("정말 탈퇴하시겠습니까?") == true){    //확인
		 location.href="delete?id="+id;
	 }else{   //취소

	     return false;

	 }

}
