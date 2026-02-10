<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 완료 페이지</title>
	</head>
	<body>
	<h2>게시판 완료 페이지</h2>
	<h3>게시글번호 : ${board.bno}</h3>
	<h3>제목 : ${board.btitle}</h3>
	<h3>내용 : ${board.bcontent}</h3>
	<h3>작성자 : ${board.id}</h3>
	<h3>작성일 : ${board.bdate}</h3>
	</body>
</html>