 $(document).ready(function(){
	 /*대댓글 입력*/
	$('#reCommentButton').click(function(){
		commentSeq= $('#commentSeq').val();
		$.ajax({
			type: 'Post',
			url:'recomment',
			data : {
				boardseq:$('#boardseq').val(),
				writer:$('#writer').val(),
				compk:$('#compk').val(),
				content:$('#content').val()
			},
			success:function(result) {
				$("#reComArea"+commentSeq).html(result);
			}
		}); //ajax
	}); // click
	
	
}); //	ready 
