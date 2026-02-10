<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
	</head>
	<body>
	<h2>게시판 페이지</h2>
	
	<form action="/doboard" method="post" name="frm">
		<input type="text" name="bno" placeholder="게시글 번호 입력"><br>
		<input type="text" name="id" placeholder="작성자 입력"><br>
		<input type="text" name="btitle" placeholder="제목 입력"><br>
		<input type="text" name="bcontent" placeholder="내용 입력"><br>
		
		<input type="submit" value="글쓰기">
	</form>	
	
	<ul>
		<li><a href="/">홈으로</a></li>
	</ul>
	</body>
</html>