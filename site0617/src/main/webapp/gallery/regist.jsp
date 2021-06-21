<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
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
<script src="https://cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
<script>
$(function(){
	CKEDITOR.replace( 'content' );
	
	//ë²í¼ì ì´ë²¤í¸ ì°ê²°íê¸°
	$("input[type='button']").click(function(){
		regist();
	});
});

//주의!!! 클라이언트가 서버에 문자열이 아닌 파일 자체를 전송하려면 반드시
//form태그에 multipart/form-data가 명시되어야 한다!
//이때, 서버에서는 기존의 request객체가 보유한 getparameter()메서드로는
//바이너리 파일이 포함된 요청을 처리할 수 없다!
function regist(){
	$("form").attr({
		"action":"/upload",
		"enctype":"multipart/form-data",
		"method":"post"
	});
	$("form").submit();
}
</script>
</head>
<body>

<h3>upload</h3>

<div class="container">
  <form action="/action_page.php">
    <input type="text"  name="title" placeholder="title..">
    <input type="text" name="writer" placeholder="writer..">
    <textarea name="content" placeholder="content.." style="height:200px"></textarea>
    <input type="file" name="myfile"><p>
    <input type="button" value="Submit">
  </form>
</div>

</body>
</html>