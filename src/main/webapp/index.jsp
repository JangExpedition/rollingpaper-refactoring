<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>khrollingpaper</title>
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/index.css"/>
	<script>
		console.log("${msg}");
	</script>
	<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
	</c:if>
</head>
<body>
	<div id="centerContainer">
		<div id="welcomeBox">
			<div id="titleBox">RollingPaper</div>
			<div id="commentBox">얼마 남지 않은 기간 화이팅하시고 수료 후 좋은 일만 있길 바랍니다.</div>
		</div>
		<div id="LoginBox">
			<form action="${ pageContext.request.contextPath }/login?${_csrf.parameterName}=${_csrf.token}" name="loginFrm" method="POST">
				<fieldset>
					<div id="loginTitle">Login</div>
					<label for="name">Name</label>
					<input type="text" id="name" name="username" placeholder="Your name" class="inputBar"/>
					<label for="Password">Password</label>
					<input type="password" id="password" name="password" placeholder="Your password" class="inputBar"/>
				</fieldset>
				<div id="submitBox">
					<input type="submit" value="Login" id="loginBtn" name="loginBtn" class="btnStyle">
				</div>
			</form>
		</div>
	</div>
<script>

</script>
</body>
</html>