 $(document).ready(function(){
//	 이전에 눌렀던 버튼의 value값을 저장하는 전역 변수 
	 var buttonVal2;
	 /*대댓글 리스트 출력*/
	$('.commentPlus').click(function(){
//		'답글'버튼의 value값
		buttonVal = this.value;
		compk= $('#compk'+buttonVal).val();
		$.ajax({
			type: 'Post',
			url:'commentPlusList',
			data:{
				"buttonVal":buttonVal,
				compk:compk	
			},//컨트롤러로 데이터 보내기
			success:function(result) {
//				해당 버튼의 ajax를 실행하고
				$("#resultArea"+buttonVal).html(result);
//				이전 ajax는 닫음
				$("#resultArea"+buttonVal2).html("");
				buttonVal2 = buttonVal;
			}	
		}); //ajax
		
	}); // click
	
	
	
}); //	ready 
