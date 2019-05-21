<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/charisma-css.jsp"%> 

<link rel="stylesheet" href="${contextPath }/scripts/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">

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
   
</style>

</head>
<body>

	<div class="container-fluid">
		<ul class="breadcrumb">
			<c:if test="${'1' eq retrieveList }">
				<li><a href=""></a> <span class="divider">/</span></li>
			</c:if>
			<li class="active">文件列表</li>
		</ul>

		<div class="row searchArea box">
			<div class="span12">
				<form id="searchForm"
					action="${pageContext.request.contextPath }/app/searcher!retrieve.do"
					method="post">
					<fieldset>

						<div class="row searchBar">
							<div class="span2 title">输入检索内容：</div>
							<div class="span2">
								<input type="text" name="keywords" class="span2" />
							</div>
							<div class="span2 offset6">
								<a class="btn btn-primary" target="submit" form="#searchForm"
									href="javascript:;"> <i class="icon-search icon-white"></i>
									查询
								</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<div class="row-fluid">
			<div class="span3">
				<div id="mulu"
					style="float: left; display: block; overflow: auto; width: 246px; background-repeat: repeat-y; background-position: right top; background-color: #F0F0F0; display: block; line-height: 21px; background-color: rgb(255, 255, 255); background-position: initial; background-repeat: initial;">
					<ul id="fileList" class="ztree"></ul>
				</div>
			</div>


			<div class="span9">
				
				<c:if test="${null ne filesrc}">
						<c:choose>
							<c:when test="${'txt' eq type}">
								
								<iframe src="${pageContext.request.contextPath }/upload${filesrc}"
							width="100%" height="100%" id="filesrc" frameborder="no"></iframe>
									
							</c:when>

							<c:when test="${'pdf' eq type || 'word' eq type || 'excel' eq type || 'ppt' eq type}">
								<embed style="overflow: auto;" src="${pageContext.request.contextPath }/upload${filesrc}" width= "100%" height= "100%" id="filesrc"></embed>
							</c:when>
						</c:choose>
				</c:if>
				

				
				<div>
					<div style="margin-bottom: 100px;">
						<span><h1 style="text-align: left; font-size: 18px; color: #90d6e5; font-style: italic; ">评论</h1></span>
						<span style="float: right;"><a id="a_comment_conrtrol" href="#" isopen='0'>展开</a></span>
					</div>
					<div id="div_comment_display" style="display: none;">
					<form action="${contextPath}/app/publicinfo!saveComment.do"
						method="post" id="commentForm">
						<input type="hidden" name="thread.threadid"
							value="${reply.thread.threadid}" /> <input id="hid_reply_annex"
							type="hidden" name="replyAnnexId" /> <input type="hidden"
							name="infocode" value="${infocode }" /> <input type="hidden"
							name="type" value="${type }" />


						<table class="table table-striped table-bordered custom"
							form="#commentForm">

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
										</div> <textarea rows="5" cols="120" name="reply" class="span6 cleditor"></textarea>


                                       <div class="row searchBar">

											<div class="span2 offset10">
												<a class="btn btn-primary" target="submit"
													form="#commentForm" href="javascript:;"> <i
													class="icon-search icon-white"></i> 评论
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
	</div>


	<%@ include file="/page/common/charisma-js.jsp"%>
	
<script type="text/javascript" src="${contextPath }/scripts/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript">

	 var setting = {					
		 data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : zTreeOnClick
		} 
	}; 

	var files = $.parseJSON('${files}');
	for(var i=0;i<files.length;i++){
		if(files[i].isFolder == "1"){
			files[i].isParent = true;
		}		
	} 
	
	var parentcodes = $.parseJSON('${parentcodes}');
	for(var i=0;i<files.length;i++){

        if($.inArray(files[i].infocode,parentcodes,0) != -1){
        	files[i].open = true;
        }

	} 
	
	function zTreeOnClick(event, treeId, treeNode) {
	    var url;
		if(treeNode.isFolder == "0"){
			url="/oa/app/publicinfo!view.do?infocode=" + treeNode.infocode + "&type=" + treeNode.type;
			//document.getElementById("filesrc").src = url;
			window.location=url;
			event.preventDefault();
			return false;
		}
		return false;
		
	};

	 $(document).ready(function(){
		 /* alert('${files}'); */
		$.fn.zTree.init($("#fileList"), setting, files);
		 
		$("#filesrc").height($(document.body).height());
		
		$('#mulu').height('height', $(document).height() - 120);
		
		
		$('#a_comment_conrtrol').click(function(){
			var isopen = $(this).attr('isopen');
			var $commentDisplay = $('#div_comment_display');
			if(0 == isopen) {
				$commentDisplay.show(1000);
				$(this).attr('isopen', 1);
				$(this).text('shouqi');
				
				$(window).scrollTop($(window).scrollTop() + 300);
			}else {
				$commentDisplay.hide(1000);
				$(this).attr('isopen', 0);
				$(this).text('zhankai');
			}
		});
	}); 
	 
	 
</script>
	
	</body>
	
	</html>
