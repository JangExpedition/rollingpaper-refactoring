<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="rollingpaper" name="title"/> 
</jsp:include>
<style>
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	h1{text-align:center; margin-top:20px;}
</style>
<section id="board-container" class="container">
	<h1>${ owner.name }</h1>
	<c:if test="${ loginMember.name == '장원정' }">
   		<input type="button" onclick="resetPwd(${ owner.no })" value="비밀번호 초기화"/>
    </c:if>
	<input type="button" value="글쓰기" id="btn-add" class="btn btn-outline-success" onclick="location.href='${pageContext.request.contextPath}/board/boardForm.do?no=${param.no}'"/>
	<table id="tbl-board" class="table table-striped table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach items="${ boardList }" var="board" varStatus="vs">
		<tr data-writer="${ board.writer }" data-no="${ board.no }">
			<td>${ vs.count }</td>	
			<c:if test="${ board.writer == loginMember.name }">
				<td>${ board.title }</td>			
				<td>${ board.writer }</td>			
			</c:if>
			<c:if test="${ board.writer != loginMember.name }">
				<td>***</td>
				<td>*****</td>
			</c:if>		
		</tr>		
		</c:forEach>
	</table>
</section>
<form action="${pageContext.request.contextPath}/board/boardDetail.do" name="boardDetailFrm">
	<input type="hidden" name="no">
</form>
<form method="post" name="resetPwdFrm">
	<input type="hidden" name="resetPwd"/>
</form>
<script>
const resetPwd = (no) => {
	const frm = document.resetPwdFrm;
	frm.resetPwd.value = no;
	frm.action = "${pageContext.request.contextPath}/member/resetPwd.do?no=" + no;
	frm.submit();
}

document.querySelectorAll("tr[data-writer]").forEach((tr) => {
	tr.addEventListener("click", (e)=> {
		const writer = tr.dataset.writer;
		const frm = document.boardDetailFrm;
		frm.no.value = tr.dataset.no;
		
		if(writer === '${ loginMember.name}'){
			console.log("확인가능");
			frm.submit();
		}else{
			console.log("확인불가");
		}
	});
});
</script>
</body>
</html>