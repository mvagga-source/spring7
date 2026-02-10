<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 페이지</title>
		<script>
			if("${flag}"==2){
				alert("아이디 또는 비밀번호 불일치");
			}
		</script>		
	</head>
	<body>
	<h2>로그인 페이지</h2>
	
	<form action="/login" method="post" name="frm">
		<input type="text" name="id" placeholder="아이디 입력">
		<input type="text" name="pw" placeholder="아이디 입력">
		<input type="submit" value="로그인">
	</form>
		
	<ul>
		<li><a href="/">홈으로</a></li>
	</ul>
	
	</body>
</html>