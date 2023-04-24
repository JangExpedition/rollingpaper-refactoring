<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="myPage" name="title"/> 
</jsp:include>
<style>
div#update-container{width:400px; padding:10px auto; text-align:center;}
div#update-container input, div#update-container select, p.errorMsg {margin-bottom:10px;}
p.errorMsg{display: none; color: tomato;}
tr[data-no] {cursor:pointer}
</style>
<div id="update-container">
	<form name="memberUpdateFrm" action="${pageContext.request.contextPath}/member/memberUpdate.do" method="post">
		<input type="hidden" name="no" value="${ loginMember.no }" />
		<input type="text" class="form-control" placeholder="이름" name="name" id="name" value="${ loginMember.name }" readonly required/>
		<input type="password" class="form-control" placeholder="현재 비밀번호" name="nowPassword" id="nowPassword" required/>
		<p id="nowPwdErrorMsg" class="errorMsg"></p>
		<input type="password" class="form-control" placeholder="새비밀번호" name="password" id="newPassword" required/>
		<input type="password" class="form-control" placeholder="비밀번호 확인" id="newPasswordCheck" required/>
		<p id="newPwdErrorMsg" class="errorMsg"></p>
		<br />
		<input type="submit" class="btn btn-outline-success" id="submitBtn" value="수정" >&nbsp;
		<input type="reset" class="btn btn-outline-success" value="취소">
	</form>
</div>
<hr/>
<section id="board-container" class="container">
	<table id="tbl-board" class="table table-striped table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach items="${ boardList }" var="board" varStatus="vs">
		<tr data-writer="${ board.writer }" data-no="${ board.no }">
			<td>${ vs.count }</td>
			<td>${ board.title }</td>
			<td>${ board.writer }</td>
		</tr>
		</c:forEach>
	</table>
</section>
<script>
const submitBtn = document.querySelector("#submitBtn");
const now = new Date();
const end = new Date(2023, 3, 11);

document.querySelectorAll("tr[data-writer]").forEach((tr) => {
	tr.addEventListener("click", (e)=> {
		const writer = tr.dataset.writer;
		const no = tr.dataset.no;
		if(now < end){
			console.log("확인불가");
			alert("수료일에 확인이 가능합니다.")
		}
		else{
			console.log("확인가능");
			location.href = "${pageContext.request.contextPath}/board/boardDetail.do?no=" + no;
		}
	});
});

document.querySelector("#nowPassword").addEventListener('focusout', (e)=>{
	const password = e.target.value;
	const nowPwdErrorMsg = document.querySelector("#nowPwdErrorMsg");
	$.ajax({
		url: "${pageContext.request.contextPath}/member/checkPassword.do",
		data: {password},
		dataType: "json",
		success(data){
			const {available} = data;
			
			console.log(available);
			
			if(!available){
				nowPwdErrorMsg.style.display = "inline-block";
				nowPwdErrorMsg.innerHTML = "현재 비밀번호가 일치하지 않습니다.";
				submitBtn.disabled = true;
			}else{
				nowPwdErrorMsg.style.display = "none";
				nowPwdErrorMsg.innerHTML = "";
				submitBtn.disabled = false;
			}
		},
		error: console.log
	});
});

document.memberUpdateFrm.addEventListener('submit', (e)=>{
	
	const pwd = "${ loginMember.password }";
	const nowPwd = document.querySelector("#nowPassword").value;
	const newPwd = document.querySelector("#newPassword").value;
	const newPwdCheck = document.querySelector("#newPasswordCheck").value;
	const newPwdErrorMsg = document.querySelector("#newPwdErrorMsg");
	
	if(newPwd !== newPwdCheck){
		newPwdErrorMsg.style.display = "inline-block";
		newPwdErrorMsg.innerHTML = "새 비밀번호와 일치하지 않습니다.";
		e.preventDefault();
	}else{
		newPwdErrorMsg.style.display = "none";
		newPwdErrorMsg.innerHTML = "";
	}
});
</script>
</body>
</html>