	var iCheck = false;
	var pCheck = false;
	/*로그인 유효성 검사*/
	$(function() {
		$('#id').focusout(function() {
			var id = $('#id').val();
			if (id.length < 4) {
				$('#iMessage').text('ID를 4글자 이상 입력해주십시오.');
				$('#id').focus(); // 해당태그에 focus In
				iCheck = false;
			} else if (id.replace(/[a-z.0-9]/gi, '').length > 0) {
				$('#iMessage').text('ID를 영문자로 입력해주십시오.');
				$('#id').focus(); // 해당태그에 focus In
				iCheck = false;
			} else {
				iCheck = true;
				$('#iMessage').text('');
			}
		}); // id

		// password
		$('#password').focusout(function() {
			var password = $('#password').val();
			if (password.length < 4) {
				$('#pMessage').text('Password는 4글자 이상이어야 합니다.');
				$('#password').focus();
				pCheck = false;
			}
			else {
				pCheck = true;
				$('#pMessage').text('');
			}
		}); // password
	}) // ready

	function inputCheck() {
		if (iCheck == true && pCheck == true)
			return true; // submit 진행
		else {
			alert('입력 자료에 오류가 있습니다.');
			return false; // submit 무효화
		}
	} // inputCheck
