	function idCheck() {
		/*회원 가입 유효성 검사*/
		var id = $('#joinId').val();

		if (id.length < 4) {
			alert('ID 를 4자리이상 정확하게 입력하세요 ~~~');
			//$('#id').val('') ;
			$('#joinId').focus();
			return false;
		}
		// id 영문과 숫자만 입력 확인	
		if (id.replace(/[a-z.0-9]/gi, '').length > 0) {
			alert('ID는 영문과 숫자로만 정확하게 입력하세요 ~~~');
			$('#joinId').focus();
			return false;
		}
	} // idCheck()

	function idDupCheck() {
		var result = idCheck(); // 외부js문서 참조
		if (result == false)
			return;
		var id = $('#joinId').val();
		var url = 'idcheck?id=' + id;
		window.open(url, "_blank",
						"toolbar=no,menubar=yes,scrollbars=yes,resizable=yes,width=450,height=300");
	} // idDupCheck
	function inCheck() {
		var password = $('#joinPassword').val();
		var password2 = $('#joinPassword2').val();
		var pLength = password.length;
		var jId = $('#joinId').val();
		
		if (password.length < 4) {
			alert('password를  4자리이상 정확하게 입력하세요 ~~~');
			$('#joinPassword').focus();
			return false;
		}
		if (password.replace(/[!-*]/g, '').length < pLength) {
			if (password.replace(/[!-*.0-9]/g, '').length > 0) { 
				alert('password는 숫자와 특수문자로만 정확하게 입력하세요 ~~~');
				$('#joinPassword').focus();
				return false;
			} // 내부 if
		} else {
			alert('password는 특수문자를 반드시 입력해야 됩니다 ~~~');
			$('#joinPassword').focus();
			return false;
		}

		// password 확인 기능 추가
		if (password != password2) {
			alert('password1, 2가 다릅니다~~정확하게 입력하세요 ~~~');
			$('#joinPassword2').focus();
			return false;
		}
	} // inCheck
