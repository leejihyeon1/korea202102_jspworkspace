<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var flag=false;
	
	$(function(){
		$("#bt_idCheck").click(function(){
			idCheck();
		});
		$("#bt_regist").click(function(){
			regist();
		});
	})
	
	function idCheck(){
		$.ajax({
			url:"/member/idCheck",
			data:{
				"user_id":$("#user_id").val()
			},
			type:"post",
			success:function(result,status,xhr){
				if(result=="true"){
					flag=true;
					alert("사용 가능");
				}else{
					flag=false;
					alert("사용 불가능");
					$("#user_id").val("");
				}
			},
			error:function(xhr,status,error){
				
			}
		})
	}
	
	//회원가입 버튼 누름
	function regist(){
		$("form").attr({
		"action":"/member/regist",
		"method":"post"
	})
	$("form").submit();
	}
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>

    <input type="text" id="user_id" name="user_id" placeholder="Your id"  style="width:80%">
    <input type="button" value="중복검사" id="bt_idCheck">
    <input type="text" id="lname" name="password" placeholder="Your password.">
    <input type="text" id="lname" name="name" placeholder="Your name..">


    <input type="button" value="회원가입" id="bt_regist">
  </form>
</div>

</body>
</html>