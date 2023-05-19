<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">자유게시판 상세보기 / 수정 / 삭제</h3>
		</div>
	</div>

	<div class="container">
		<form name="newUpdate" action="/free/update.do" class="form-horizontal" method="get" id="newUpdate">
			<input type="hidden" value="${free.boNo }" name="boNo">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					${free.boTitle }
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					${free.boContent }
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<p>
						<input type="button" class="btn btn-danger" id="deleteBtn" value="삭제">
						<input type="button" class="btn btn-success" value="수정" id="updateBtn">
						<a href="/free/list.do" class="btn btn-primary" id="listBtn"> 목록</a>
					</p>
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var deleteBtn = $('#deleteBtn');
	var updateBtn = $('#updateBtn');
	
	var newUpdate = $('#newUpdate');
	
	deleteBtn.on('click',function(){
		if(confirm("정말 삭제 하시겠습니까?")){
			newUpdate.attr('action','/free/delete.do')
			newUpdate.attr('method','post')
			newUpdate.submit();	
		}else {
			newUpdate.reset();	
		}
	});
	
	updateBtn.on('click',function(){
		newUpdate.submit();
	});
});
</script>
</html>


