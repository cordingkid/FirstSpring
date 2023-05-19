<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 등록</title>
</head>
<body>
	<c:if test="${status eq 'u' }">
		<c:set value="수정" var="name"/>
	</c:if>
	<c:if test="${status ne 'u' }">
		<c:set value="등록" var="name"/>
	</c:if>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">자유 게시판 ${name }</h3>
		</div>
	</div>
	<div class="container">
		<form name="newWrite" action="" class="form-horizontal" method="post" id="newWrite">
			<input name="id" type="hidden" class="form-control" value="${free.boNo }">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input name="boTitle" type="text" class="form-control" placeholder="subject" id="boTitle" value="${free.boTitle }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea name="boContent" cols="50" rows="5" class="form-control" placeholder="content" id="boContent">${free.boContent }</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="button" class="btn btn-primary " value="${name }" id="formBtn">
					<c:if test="${status eq 'u' }">
						<a href="/free/detail.do?boNo=${free.boNo}">
							<input type="button" class="btn btn-primary " value="취소">	
						</a>
					</c:if>			
					<c:if test="${status ne 'u' }">
						<a href="/free/list.do">
							<input type="button" class="btn btn-primary " value="취소">	
						</a>
					</c:if>	
				</div>
			</div>
		</form>
		<c:if test="${not empty errors }">
			<p style="color: red;">
				${erros.subject } <br>
				${erros.content } <br>
				${erros.msg }
			</p>
		</c:if>
		<hr>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace("boContent");
	CKEDITOR.config.allowedContent = true;
	
	var formBtn = $('#formBtn');
	
	formBtn.on('click',function(){
		var title = $('#boTitle').val();
		var content = CKEDITOR.instances.boContent.getData();
		
		if(title == ""){
			alert("제목을 입력하세요!")
			$('#boTitle').focus();
			return false;
		}
		
		if(content == ""){
			alert("내용을 입력하세요!")
			$('#boContent').focus();
			return false;
		}
		
		if($(this).val() == '수정'){
			$(this).attr('action', '/notice/update.do');
		}
		
		$('#newWrite').submit();
	});
});
</script>
</html>



