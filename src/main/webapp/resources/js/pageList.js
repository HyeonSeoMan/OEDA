function commentListP(boardseq) {
	/*게시글 디테일 페이지 팝업*/
	var screenW = screen.availWidth;
	var screenH = screen.availHeight;
	var popW = 600;
	var popH = 580;
	var posL = (screenW - popW) / 2;
	var posT = (screenH - popH) / 5;
	window.open("bdetail?seq="+boardseq, "글 작성", 'width=' + popW + ', height=' + popH
			+ ', left=' + posL + ', top=' + posT);
}
