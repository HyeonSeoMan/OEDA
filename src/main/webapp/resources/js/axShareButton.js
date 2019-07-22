 $(document).ready(function(){
	 /*공유 설정 확장*/
	$('#shareSetting').click(function(){
		$.ajax({
			type: 'Post',
			url:'shareSetting',
			success:function(result) {
				$("#shareSettingButton").html(result);
			}
		}); //ajax
	}); // shareSetting	
	/*공유 설정 닫기*/
	$('#resetShare').click(function(){
		$.ajax({
			type: 'Post',
			url:'resetShare',
			success:function(result) {
				$("#shareSettingButton").html(result);
			}
		}); //ajax
	}); // resetShare
	/*나만 보기*/
	$('#shareMySelf').click(function(){
		$.ajax({
			type: 'Post',
			url:'shareMySelf',
			success:function(result) {
				$("#shareSettingButton").html(result);
			}
		}); //ajax
	}); // shareMySelf
	/*공유하기*/
	$('#shareTogether').click(function(){
		$.ajax({
			type: 'Post',
			url:'shareTogether',
			success:function(result) {
				$("#shareSettingButton").html(result);
			}
		}); //ajax
	}); // shareTogether
}); //	ready 
