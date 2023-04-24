<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 보기" name="title"/>
</jsp:include>
<style>
div#board-container{width:400px;}
input, button, textarea {margin-bottom:15px;}
button { overflow: hidden; }
input#btn-add{float:right; margin: 0 0 15px;}
/* 부트스트랩 : 파일라벨명 정렬*/
div#board-container label.custom-file-label{text-align:left;}
</style>
<div id="board-container" class="mx-auto text-center">
<form action="" method="post" name="updateOrDeleteBoardFrm">
	<input type="text" class="form-control" 
		   placeholder="제목" name="title" id="title" 
		   value="${ board.title }" ${ board.writer == loginMember.name ? '' : 'readonly' } required>
	<input type="text" class="form-control" 
		   name="memberId" 
		   value="${ board.writer }" readonly required>
    <textarea class="form-control" name="content"
    		  placeholder="내용" ${ board.writer == loginMember.name ? '' : 'readonly' } required>${ board.content }</textarea>
	<input type="button" value="수정" id="btn-update" class="btn btn-outline-success" onclick="updateBoard()"/>
	<input type="button" value="삭제" id="btn-delete" class="btn btn-outline-danger" onclick="deleteBoard()"/>
</form>
</div>
<script>
const frm = document.updateOrDeleteBoardFrm;
const updateBoard = () => {
	
	const board = {
		title : frm.title.value;
		id : frm.memberId.value;
		content : frm.content.value;
	};
	
	$.ajax({
		url : "{pageContexgt.request.contextPath}/board",
		data : JSON.stringify(board),
		method: "PATCH",
		success(data){
			console.log(data);
		},
		error : console.log
	});
}
const deleteBoard = () => {
	if(confirm("정말로 삭제하시겠습니까?")){
		frm.action="${pageContext.request.contextPath}/board/deleteBoard.do?no=${board.no}";
		frm.submit();
	}
}
</script>
</body>
</html>
