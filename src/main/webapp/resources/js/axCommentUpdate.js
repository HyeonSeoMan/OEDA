 $(document).ready(function(){
	 /*댓글 수정*/
	 $('.soojung').click(function(){
			buttonVal = this.value;
			compk= $('#compk'+buttonVal).val();
			alert("댓글이 수정 되었습니다.");
			$.ajax({
				type: 'Post',
				url:'commentUpdate',
				data:{
					content:$('#content'+buttonVal).val(),
					compk:compk
				},
				success:function(result) {
					$().html("");
				}	
			}); //ajax
		}); // click
}); //	ready 
