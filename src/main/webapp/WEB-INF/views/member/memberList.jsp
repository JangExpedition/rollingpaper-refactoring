<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="rollingpaper" name="title"/> 
</jsp:include>
	<table class="table w-50">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">No</th>
	      <th scope="col">Name</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${ memberList }" var="member" varStatus="vs">
		<tr data-no="${ member.no }">
			<th scope="row">${ vs.count }</th>
			<td>${ member.name }</td>
		</tr>	  	
	  	</c:forEach>
	  </tbody>
	</table>
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