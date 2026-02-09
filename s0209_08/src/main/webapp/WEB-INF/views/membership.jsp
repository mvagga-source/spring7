<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<h2>회원가입</h2>
		<form action="/member/membershipOk" method="post" name="frm">
			<input type="text" name="id" placeholder="아이디 입력">	<br>
			<input type="text" name="pw" placeholder="비밀번호 입력">	<br>
			<input type="text" name="name" placeholder="이름 입력">	<br>
			<input type="text" name="phone" placeholder="전화번호 입력"><br>
			<input type="text" name="email" placeholder="이메일 입력"><br>

			<input type="radio" name="gender" id="male" value="남자">
			<label for="male">남자</label> 
			<input type="radio" name="gender" id="female" value="여자">
			<label for="female">여자</label>
			<br>
			
			<input type="checkbox" name="hobby" id="golf" value="골프">
			<label for="golf">골프</label> 
			<input type="checkbox" name="hobby" id="swin" value="수영">
			<label for="swin">수영</label>
			<input type="checkbox" name="hobby" id="game" value="게임">
			<label for="game">게임</label>
			 <input type="checkbox" name="hobby" id="book" value="독서">
			<label for="book">독서</label> 
			<br>
			
			<input type="submit" value="회원가입">			
		</form>	
	</body>
</html>