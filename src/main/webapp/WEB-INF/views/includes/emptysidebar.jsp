<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="otree-sidebar-1">
	<div class="ws-rounded-circle mt-3 mb-3 fs-7 fw-bold">여백</div>
	<c:forEach var="workspace" items="${workspaceList}">
        <div class="ws-rounded-circle mt-3 mb-3 fs-7 fw-bold" id="${workspace.workspaceId}" name="workspaceIcon">${workspace.workspaceName.substring(0, 2)}</div>
    </c:forEach>
    <div class="ws-rounded-circle-plus mt-3 mb-3 fs-4 fw-bold border-green-300" name="workspaceIcon" id="createWorkspace">+</div>
</div>
<div class="otree-sidebar-2 d-flex flex-column flex-shrink-0 bg-green-100 vh-100">
	<div class="mt-5 mx-3">
		<h2 class="mt-5">반갑습니다!</h2>
		<div id="todo"></div>
	</div>
</div>

<div class="offcanvas offcanvas-end" tabindex="-1" id="todoOffcanvas" aria-labelledby="offcanvasRightLabel" data-bs-backdrop="false" style="margin-top: 65px;">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasRightLabel">나의 할일</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
  	<div>할일 추가</div>
  	<div>알마인드 과제</div>
  	<div>알마인드 과제</div>
  </div>
</div> 

<!-- Modal -->
<div class="modal fade position-fixed" id="createWorkspaceModal" tabindex="-1" aria-labelledby="createWorkspaceModalLabel" aria-hidden="true">
  <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header border-bottom-0">
				<h3 class="modal-title text-center">워크스페이스 생성</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			
			<form action="${pageContext.request.contextPath}/workspace" method="post">
				<div class="modal-body">
					<div class="form-outline form-white mb-4 text-start">
						<label class="form-label" for="workspaceName">워크스페이스 이름</label> 
						<input type="text" name="workspaceName" id="workspaceName" class="form-control" placeholder="2자 이상의 한글 또는 영문" />
					</div>
					<div class="form-outline form-white mb-4 text-start">
						<label class="form-label" for="description">워크스페이스 설명</label> 
						<input type="text" name="description" id="description" class="form-control" placeholder="간단한 워크스페이스 또는 프로젝트 설명" />
					</div>
				</div>
				<div class="d-grid gap-2 m-2">
					<button type="submit" class="btn btn-primary" id="createWorkspace">워크스페이스 생성</button>
				</div>
			</form>
		</div>
	</div>
</div>