	function updateCheck() {
		/*회원 정보 수정 유효성 검사*/
		var password = $('#updatePassword').val();
		var password2 = $('#updatePassword2').val();
		var pLength = password.length;
		
		if (password.length < 4) {
			alert('password를  4자리이상 정확하게 입력하세요.');
			$('#updatePassword').focus();
			return false;
		}
		if (password.replace(/[!-*]/g, '').length < pLength) {
			if (password.replace(/[!-*.0-9]/g, '').length > 0) { 
				alert('password는 숫자와 특수문자로만 정확하게 입력하세요.');
				$('#updatePassword').focus();
				return false;
			} // 내부 if
		} else {
			alert('password는 특수문자를 반드시 입력해야 됩니다.');
			$('#updatePassword').focus();
			return false;
		}

		// password 확인 기능 추가
		if (password != password2) {
			alert('password1, 2가 다릅니다.');
			$('#updatePassword2').focus();
			return false;
		}
	} // inCheck
