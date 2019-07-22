function visitorCheck() {
	var visitContent = $('#contenttestVisit').val();
	/*방명록 유효성 검사*/
	if (visitContent.length < 1) {
		alert('내용을 입력해주세요.');
		$('#contenttestVisit').focus();
		return false;
	}
	
} // binsertCheck
