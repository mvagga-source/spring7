<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 페이지</title>
	</head>
	<body>
	<h2>회원가입 페이지</h2>
	
	<form action="/dojoin" method="post" name="frm">
		<input type="text" name="id" placeholder="아이디 입력"><br>
		<input type="text" name="pw" placeholder="비밀번호 입력"><br>
		<input type="text" name="name" placeholder="이름 입력"><br>
		<input type="text" name="phone" placeholder="전화번호 입력"><br>
		<input type="text" name="email" placeholder="이메일 입력"><br>
		
		<label>성별</label><br>
		<input type="radio" name="gender" value="남자" id="male">
		<label for="male">남자</label>
		<input type="radio" name="gender" value="여자" id="female">
		<label for="female">여자</label>
		<br>
		
		<label>취미</label><br>
		<input type="checkbox" name="hobby" id="golf" value="골프">
		<label for="golf">골프</label>
		<input type="checkbox" name="hobby" id="game" value="게임">
		<label for="game">게임</label>
		<input type="checkbox" name="hobby" id="swim" value="수영">
		<label for="swim">수영</label>
		<input type="checkbox" name="hobby" id="run" value="조깅">
		<label for="run">조깅</label>
		<input type="checkbox" name="hobby" id="book" value="독서">
		<label for="book">독서</label>
		<br>
		
		<input type="submit" value="로그인">
	</form>	
	
	<ul>
		<li><a href="/">홈으로</a></li>
	</ul>
	</body>
</html>