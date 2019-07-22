 $(document).ready(function(){
//	 '더보기'버튼이 눌리면 실행
	 
	 /*페이지 추가*/
	$('#PlusAreaButton').click(function(){
		var currPageTest=this.value;
		$('#PlusAreaButton').hide();
		$.ajax({
			type: 'Post',
			url:'blist',
			data : {
				currPage:currPageTest
			},
			success:function(result) {
				$("#plusArea"+currPageTest).html(result);
			}
		}); //ajax
	}); // click
	/*유틸 사이드 바 새로 고침 */
	$('#PlusAreaButton').click(function(){
		var currPageTest=this.value;
		$.ajax({
			type: 'Post',
			url:'sideBar',
			data : {
				currPage:currPageTest
			},
			success:function(result) {
				$("#sideBarArea").html(result);
			}
		}); //ajax
	}); // click
	
	
}); //	ready 
