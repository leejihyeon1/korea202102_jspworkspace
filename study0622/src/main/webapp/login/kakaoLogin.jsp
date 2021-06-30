<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kakao Login</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <a href="javascript:kakaoLogin();">
        <img src="https://developers.kakao.com/docs/static/image/m/kakaologin.png" alt="">
    </a>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>

window.Kakao.init("6c9ddf6b2cbd6e7b24e85bc621a7f165");//api키 넣어서 초기화

//카카오 로그인
function kakaoLogin(){
    window.Kakao.Auth.login({
        scope:'profile_nickname, profile_image, account_email, gender',
        success:function(obj){
            console.log(obj);
            window.Kakao.API.request({
                url:"/v2/user/me",
                success:res =>{
                    const kakao_account = res.kakao_account;
                    send(kakao_account);
                }
            })
        }

    });
}

function send(kakao_account){
	//alert(kakao_account);
	var email=kakao_account.email;
	var name=kakao_account.profile.nickname;
	$.ajax({
		"type":"post",
		"url":"/login",
		"data":{
			"email":email,
			"name":name
		},
		success:function(result,status,xhr){
			alert(result);
		}
	})
}
</script>
</body>
</html>