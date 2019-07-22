function boardDelete(boardseq) {
	/*게시글 삭제 최종 확인*/
	 if (confirm("정말 삭제하시겠습니까?") == true){    //확인
		 location.href="bdelete?seq="+boardseq;
	 }else{   //취소

	     return false;

	 }

}
