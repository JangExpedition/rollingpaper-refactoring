<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="rollingpaper" name="title"/> 
</jsp:include>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/memberList.css"/>
<section class="memberListSection">
	<div class="tableSection">
		<table class="memberTable">
		  <thead class="memberTable-thead">
		    <tr>
		      <th colspan="3">친구</th>
		    </tr>
		  </thead>
		  <tbody class="memberTable-tbody">
		  	<c:forEach items="${ memberList }" var="member" varStatus="vs">
		  		<tr>
					<th>
						<c:if test="${ member.renamedFilename == null && member.gender == 'M'}">
							<img class="memberProfileImg" src="${ pageContext.request.contextPath }/images/man.png">
						</c:if>
						<c:if test="${ member.renamedFilename == null && member.gender == 'F'}">
							<img class="memberProfileImg" src="${ pageContext.request.contextPath }/images/women.png">
						</c:if>
						<c:if test="${ member.renamedFilename != null }">
							<img class="memberProfileImg" src="${ pageContext.request.contextPath }/upload/member/${member.renamedFilename}">
						</c:if>
					</th>
					<td class="nameTd">${ member.name }</td>
					<td class="writeBtnTd"><input class="writeBtn" value="작성하기" type="button"/></td>
				</tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
</section>
<script>
document.querySelectorAll("tr[data-no]").forEach((tr) => {
	tr.addEventListener("click", (e)=> {
		const no = tr.dataset.no;
		location.href="${pageContext.request.contextPath}/board/boardList.do?no=" + no;
	});
});
</script>
</body>
</html>