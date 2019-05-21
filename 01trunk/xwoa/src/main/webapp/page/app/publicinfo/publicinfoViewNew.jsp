<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<style>
    table.forum {
        table-layout: fixed;
        width: 100%;
        border-collapse:separate; border-spacing:0;
    }

    table.forum td h1 {
        font-size: 18px;
        font-weight: bold;
        display: inline;
    }

    table.forum td {
        overflow: hidden;
        vertical-align: top;
        padding: 20px;
        border:none;
        border-bottom: 1px solid #cdcdcd;
    }

    table.forum td.userinfo {
/*         background: #e5edf2; */
       /*  border-right: 1px solid #C2D5E3; */
        width: 80px;
    }

    table.forum td.content {
        font: 12px/1.5 Tahoma, 'Microsoft Yahei', 'Simsun';
        /* color: #333;
        background: #F4F4F4; */
    }

    table.forum td.content div.postinfo {
        margin-bottom: 10px;
        padding: 10px 0;
        height: 16px;
        /* border-bottom: 1px dashed #CDCDCD; */
        float:right
    }
    
    table.forum td.content ul.comment{
        float:right
    }

</style>

<div id="viewcomment">
	<div class="row-fluid">

		<c:if test="${null eq filesrc }">
			<label>找不到指定的路径</label>
		</c:if>

		<c:if test="${null ne filesrc}">
			<c:choose>
				<c:when
					test="${'word' eq type || 'excel' eq type || 'ppt' eq type || 'txt' eq type}">
					<iframe src="${pageContext.request.contextPath }\upload${filesrc}"
						width="99%" height="100%" scrolling="no" onLoad="iFrameHeight2()"
						id="iframepage2" style="overflow: auto;" frameborder="0" style="margin-left: 10px;></iframe>
				</c:when>

				<c:when test="${'pdf' eq type}">
					<embed src="${pageContext.request.contextPath }\upload${filesrc}"
						width="99%" height="300">
					</embed>
				</c:when>
			</c:choose>
		</c:if>

		<script type="text/javascript" language="javascript">
			function iFrameHeight2() {
				var ifm = document.getElementById("iframepage2");
				var subWeb = document.frames ? document.frames["iframepage2"].document
						: ifm.contentDocument;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
				}
			}
		</script>

		<div style="overflow: auto;">
			<form action="${contextPath}/app/publicinfo!saveComment.do" method="post" id="commentForm">
				<input type="hidden" name="thread.threadid" value="${reply.thread.threadid}" /> 
				<input id="hid_reply_annex" type="hidden" name="replyAnnexId" /> 
				<input type="hidden" name="infocode" value="${infocode }" /> 
				<input type="hidden" name="viewNew" value="1" /> 
				<input type="hidden" name="type" value="${type }" />

								<table
									class="table table-striped table-bordered bootstrap-datatable datatable custom"
									id="commentTable">

									<tbody>

										<c:forEach var="obj" items="${replys}">
											<tr>
												<td class="userinfo">${cp:MAPVALUE('usercode',
													obj.userid)}：</td>
												<td class="content">
													<div class="postinfo">
														<fmt:formatDate value="${obj.replytime }"
															pattern="yyyy-MM-dd HH:mm:ss" />
													</div> ${obj.reply } <c:if
														test="${not empty replyAnnexs[obj.replyid]}">
														<div class="postinfo"></div>
														<b>附件：</b>
														<br />
														<br />
														<em> <c:forEach var="fi"
																items="${replyAnnexs[obj.replyid]}">
																<a target="download" filecode="${fi.filecode}">${fi.filename}.${fi.fileext}</a>
																<br />
																<br />
															</c:forEach>
														</em>
													</c:if>

												</td>
											</tr>
										</c:forEach>

										<tr>
											<td class="userinfo"></td>
											<td class="content">

												<div>
													<input type="file" id="upload-reply-thread" optID="FORUM"
														inputID="hid_reply_annex" /> <br />

												</div> <textarea rows="5" cols="120" name="reply" class="editor"></textarea>

												<div class="row searchBar">
													<div class="span2 offset10">
														<a class="btn btn-primary" target="submit"
															form="#commentForm" href="javascript:;"> <i
															class="icon-search icon-white"></i>

														</a>
													</div>
												</div>
											</td>
										</tr>

									</tbody>

								</table>
							</form>
		</div>
	</div>
</div>
</div>
