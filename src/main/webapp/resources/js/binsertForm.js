function binsertCheck() {
	/*게시글 작성 유효성 검사*/
	var tag = $('#tagtest').val();
	var content = $('#contenttest').val();
	
	if (content.length < 1) {
		alert('내용을 입력해주세요.');
		$('#contenttest').focus();
		return false;
	}
	
	if (tag.search(/\s/) != -1) {
		alert('공백은 입력 될 수 없습니다.');
		$('#tagtest').focus();
		return false;
	}
	if (tag.search(/#/) == -1) {
		alert('#을 입력하지 않으면 태그를 사용할 수 없습니다.');
		$('#tagtest').focus();
		return false;
	}
	if (tag.charAt(0)!='#') {
		alert('태그의 첫마디는 #이어야 합니다.');
		$('#tagtest').focus();
		return false;
	}
} // binsertCheck
