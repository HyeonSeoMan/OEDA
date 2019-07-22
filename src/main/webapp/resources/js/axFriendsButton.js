 $(document).ready(function(){
	 /*친구 목록 출력*/
	 $('#myFriend').click(function(){
			$.ajax({
				type: 'Post',
				url:'myFriend',
				success:function(result) {
					$("#myFriendsArea").html(result);
				}
			}); //ajax
		}); // myFriend
	 /*친구 추가 가능 목록 출력*/
	$('#plusFriends').click(function(){
		$.ajax({
			type: 'Post',
			url:'friendRecommendation',
			success:function(result) {
				$("#plusFriendsArea").html(result);
			}
		}); //ajax
	}); // plusFriends	
	/*친구 요청 목록 출력*/
	$('#friendRequest').click(function(){
		$.ajax({
			type: 'Post',
			url:'friendRequest',
			success:function(result) {
				$("#friendsRequestArea").html(result);
			}
		}); //ajax
	}); // friendRequest
	/*친구 목록 닫기*/
	$('#returnMyFriend').click(function(){
		$.ajax({
			type: 'Post',
			url:'returnMyFriend',
			success:function(result) {
				$("#returnMyFriendsArea").html(result);
			}
		}); //ajax
	}); // returnMyFriend
	/*친구 추가 가능 목록 닫기*/
	$('#returnPlusFriends').click(function(){
		$.ajax({
			type: 'Post',
			url:'returnPlusFriends',
			success:function(result) {
				$("#returnPlusFriendsArea").html(result);
			}
		}); //ajax
	}); // returnPlusFriends
	/*친구 요청 목록 닫기*/
	$('#returnFriendRequest').click(function(){
		$.ajax({
			type: 'Post',
			url:'returnFriendRequest',
			success:function(result) {
				$("#returnFriendsRequestArea").html(result);
			}
		}); //ajax
	}); // returnFriendRequest
	/*친구 추가 실행*/
	$('#plusFriendAction').click(function(){
		var lists = [];
	    $("input[name='friend_user_list']:checked").each(function(i) {
	    	lists.push($(this).val());
	    });
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			type: 'Post',
			url:'plusFriendAction',
			data:{
				master_user:$('#master_user').val(),
				request_status:$('#request_status').val(),
				friend_user_list:lists
			},
			success:function(result) {
				$("#returnPlusFriendsArea").html(result);
			}
		}); //ajax
	}); // plusFriendAction
	/*친구 요청 수락*/
	$('#friendRequestAccept').click(function(){
		var lists = [];
	    $("input[name='friend_request_list']:checked").each(function(i) {
	    	lists.push($(this).val());
	    });
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			type: 'Post',
			url:'friendRequestAccept',
			data:{
				master_user:$('#master_user').val(),
				request_status:$('#request_status').val(),
				friend_request_list:lists
			},
			success:function(result) {
				$("#returnFriendsRequestArea").html(result);
			}
		}); //ajax
	}); // friendRequestAccept
}); //	ready 
