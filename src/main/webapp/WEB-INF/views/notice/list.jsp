<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>공지게시판 목록</title>
</head>
<body>
	<c:set value="${paging.dataList }" var="noticeList"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">공지게시판 목록</h1>
		</div>
	</div>
	<div class="container">
		<form action="" method="post" id="searchForm">
			<div style="padding-top: 50px;">
				<div class="row" style="padding-bottom: 20px;">
					<div class="col-md-7"></div>
					<div class="col-md-10">
						<input type="hidden" name="page" id="page"/>
						<select name="searchType">
							<option value="subject" <c:if test="${searchType == 'subject' }"><c:out value="selected"/></c:if>>제목</option>
							<option value="writer" <c:if test="${searchType == 'writer' }"><c:out value="selected"/></c:if>>작성자</option>
							<option value="subandwri" <c:if test="${searchType == 'subandwri' }"><c:out value="selected"/></c:if>>제목+작성자</option>
						</select>	
						<input type="text" name="searchWord" value="${searchWord }">
						<input type="submit" value="검색">
					</div>
				</div>
			</div>
			<div>
				<div class="text-right">
					<span>전체 건 ${all}</span>
				</div>
			</div>
			<div style="padding-top: 50px">
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty noticeList }">
								<tr>
									<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${noticeList }" var="notice">
									<tr class="listTr">
										<td class="noticeNo">${notice.no }</td>
										<td>${notice.subject }</td>
										<td>${notice.regDate }</td>
										<td>${notice.writer }</td>
										<td>${notice.hit }</td>
									</tr>
								</c:forEach>							
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div align="left">
				<a href="/notice/insert.do" onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
			</div>
		</form>
		<div class="card-footer clearfix" id="pagingArea">
				${paging.pagingHTML }
		</div>
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
</body>
<script type="text/javascript">
$(function(){
	var listTr = $('#listTr');
	
	var searchForm = $('#searchForm');
	
	var pagingArea = $('#pagingArea');
	
	pagingArea.on('click', 'a', function(event){
		event.preventDefault();
		var pageNo = $(this).data("page");
		searchForm.find('#page').val(pageNo);
		searchForm.submit();
	});
	
	$(document).on('click','.listTr',function(){
		console.log();
		var boNo = $(this).find('.noticeNo').text();
		location.href = "/notice/detail.do?no="+boNo;
	})
});
</script>
</html>





