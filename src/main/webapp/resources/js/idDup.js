function idCheck() {
	/*아이디 유효성 검사*/
	var id=$('#id').val();
	
	if (id.length<4) {
		alert('ID 를 4자리이상 정확하게 입력하세요 ~~~') ;
		alert("id=> " +id);
		//$('#id').val('') ;
		$('#id').focus();
		return false;
	}
	// id 영문과 숫자만 입력 확인	
	if (id.replace(/[a-z.0-9]/gi,'').length>0) {
		alert('ID는 영문과 숫자로만 정확하게 입력하세요 ~~~') ;
		$('#id').focus();
		return false;
	}
  } // idCheck()