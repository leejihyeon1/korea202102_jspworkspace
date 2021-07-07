<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function send(){
	form1.action="/blood.do";
	form1.method="post",
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<select name="blood">
			<option>자신의 혈액형을 선택하세요</option>
			<option value="A형">A형</option>
			<option value="B형">B형</option>
			<option value="O형">O형</option>
			<option value="AB형">AB형</option>
		</select>
	</form>
	<button onClick="send()">결과보기</button>
</body>
</html>