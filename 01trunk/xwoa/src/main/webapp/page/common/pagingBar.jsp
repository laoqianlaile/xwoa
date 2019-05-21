<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<c:if test="${empty maxPageItems}">
	<c:set var="maxPageItems" value="20" />
</c:if>
<c:if test="${empty maxIndexPages}">
	<c:set var="maxIndexPages" value="10"/>
</c:if>

<div class="row">
	<div class="span12 center">
		<div class="dataTables_paginate paging_bootstrap pagination">
				
				<pg:pager url="${listURL }" items="${pageDesc.totalRows }" export="currentPageNumber=pageNumber" maxPageItems="${maxPageItems }" maxIndexPages="${maxIndexPages }">
			
				<ul>
					<pg:first>
						<c:choose>
							<c:when test="${currentPageNumber != 1}">
								<li class="first"><a href="${pageUrl}">首页</a></li>
							</c:when>
							<c:otherwise>
								<li class="first disabled"><a>首页</a></li>
							</c:otherwise>
						</c:choose>
					</pg:first>

					<pg:prev>
						<li class="prev"><a href="${pageUrl }">上一页</a></li>
					</pg:prev>

					<c:set var="pageIndex" value="0"></c:set>
					<pg:pages>
						<li><c:set var="pageIndex" value="${pageIndex + 1 }"></c:set>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber}">
									<a href="${pageUrl }" class="current" style="color: red;">${pageNumber }</a>
								</c:when>
								<c:otherwise>
									<a href="${pageUrl }">${pageNumber }</a>
								</c:otherwise>
							</c:choose></li>
					</pg:pages>

					<pg:next>
						<li class="next"><a href="${pageUrl }">下一页</a></li>
					</pg:next>

					<pg:last>
						<c:choose>
							<c:when test="${currentPageNumber != pageNumber and 0 != pageNumber}">
								<li class="last"><a href="${pageUrl}">尾页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last disabled"><a>尾页</a></li>
							</c:otherwise>
						</c:choose>
					</pg:last>
				</ul>
			</pg:pager>
		</div>
	</div>
</div>