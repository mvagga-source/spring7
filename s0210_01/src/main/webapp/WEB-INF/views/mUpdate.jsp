<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원수정 페이지</title>
	</head>
	<body>
	<h2>회원수정 페이지</h2>
	
	<form action="/dojoin" method="post" name="frm">
		<input type="text" name="id" value="${member.id}" placeholder="아이디 입력"><br>
		<input type="text" name="pw" value="${member.pw}" placeholder="비밀번호 입력"><br>
		<input type="text" name="name" value="${member.name}" placeholder="이름 입력"><br>
		<input type="text" name="phone" value="${member.phone}" placeholder="전화번호 입력"><br>
		<input type="text" name="email" value="${member.email}" placeholder="이메일 입력"><br>
		
		<label>성별</label><br>
		<input type="radio" name="gender" value="남자" id="male"
		<c:if test="${fn:contains(member.gender,'남자')}">checked</c:if>>
		<label for="male">남자</label>
		<input type="radio" name="gender" value="여자" id="female"
		<c:if test="${fn:contains(member.gender,'여자')}">checked</c:if>>
		<label for="female">여자</label>
		<br>
		
		<label>취미</label><br>
		<input type="checkbox" name="hobby" id="golf" value="골프"
		<c:if test="${fn:contains(member.hobby,'골프')}">checked</c:if>>
		<label for="golf">골프</label>
		<input type="checkbox" name="hobby" id="game" value="게임"
		<c:if test="${fn:contains(member.hobby,'게임')}">checked</c:if>>
		<label for="game">게임</label>
		<input type="checkbox" name="hobby" id="swim" value="수영"
		<c:if test="${fn:contains(member.hobby,'수영')}">checked</c:if>>
		<label for="swim">수영</label>
		<input type="checkbox" name="hobby" id="run" value="조깅"
		<c:if test="${fn:contains(member.hobby,'조깅')}">checked</c:if>>
		<label for="run">조깅</label>
		<input type="checkbox" name="hobby" id="book" value="독서"
		<c:if test="${fn:contains(member.hobby,'독서')}">checked</c:if>>
		<label for="book">독서</label>
		<br>
		
		<input type="submit" value="로그인">
	</form>	
	
	<ul>
		<li><a href="/">홈으로</a></li>
	</ul>
	</body>
</html>