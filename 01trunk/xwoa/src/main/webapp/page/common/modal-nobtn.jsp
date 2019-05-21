<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<c:if test="${empty modalID }">
	<c:set var="modalID" value="myModal"></c:set>
</c:if>
<c:if test="${empty modalTitle }">
	<c:set var="modalTitle" value="窗口"></c:set>
</c:if>
<c:if test="${empty modalSaveBtn }">
	<c:set var="modalSaveBtn" value="保存"></c:set>
</c:if>

<!-- Modal -->
<div id="${modalID }" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3 id="myModalLabel">${modalTitle }</h3>
	</div>
	<div class="modal-body">
	</div>
	<div class="modal-footer">
	</div>
</div>