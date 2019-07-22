function reCommentDelete(boardseq,commentSeq,compk) {
	/*대댓글 삭제 최종 확인*/
	 if (confirm("정말 삭제하시겠습니까?") == true){    //확인
		 location.href="commentDelete?boardseq="+boardseq+"&commentseq="+commentSeq+"&compk="+compk;
	 }else{   //취소

	     return false;

	 }

}
