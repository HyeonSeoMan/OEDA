 $(document).ready(function(){
	 /*대댓글 수정*/
	 $('.resoojung').click(function(){
			buttonVal = this.value;
			recompk= $('#recompk'+buttonVal).val();
			alert("대댓글이 수정 되었습니다.");
			$.ajax({
				type: 'Post',
				url:'commentUpdate',
				data:{
					content:$('#recontent'+buttonVal).val(),
					compk:recompk
				},
				success:function(result) {
					$().html("");
				}	 
			}); //ajax
			
		}); // click
	
	
}); //	ready 
