<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/charisma-css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/scripts/plugin/treetable/Treetable_files/jqtreetable.css" />

<title>LIST</title>
</head>
<body>



	<div class="container-fluid" style="height:100%;">




		<div class="row-fluid" style="height:100%;">
			<div class="span3"
				style="border: 1px solid #dddddd; border-radius: 4px; margin-top:10px;height:90%;">
				<table class="tablemain" style="min-width: 20px; width: 100%">
					<tbody align="center" id="table-detailTree">
						<c:forEach items="${dictDetails}" var="detail">
							<tr id="tr_${detail.datacode}">
								<td align="left"><a
									href="${pageContext.request.contextPath}/oa/oaAssetinformation!listLab.do?s_supEquipmentType=resourse&s_datacode=${detail.datacode}&hasChildNode=${detail.datastyle}" style="font-size:14px;">${detail.datavalue}</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="span9" style="height:100%;">
				<iframe id="info" name="info"
					src="${pageContext.request.contextPath}/oa/oaAssetinformation!list.do?s_datacode=${datacodeType}&isDept=${isDept}&hasChildNode=${hasChildNode}&s_state=T"
					width="100%" frameborder="no"
					scrolling="no" border="0" marginwidth="0"
					onload="this.height=window.frames['info'].document.body.scrollHeight"></iframe>
			</div>
		</div>

	</div>

	<%@ include file="/page/common/charisma-js.jsp"%>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/plugin/treetable/Treetable_files/jqtreetable.js"></script>
	<script
		src="${pageContext.request.contextPath}/scripts/jQueryCheckExt.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			var imgpath = '${pageContext.request.contextPath}'
					+ "/scripts/plugin/treetable/images/TreeTable";
			var $roleTree = $("#table-detailTree");
			var index = $.parseJSON('${INDEX}').indexes;
			var $objRoleTree = new jQueryCheckExt();
			$objRoleTree.makeCkeckBoxTreeTable($roleTree, index, imgpath);

			$('#tr_${s_consumablesType}').css('background-color', '#5CACEE');

			function replaceParam(href, value) {

				if (/navTab=(.*)&/.test(href)) {
					href = href
							.replace(/navTab=(.*)&/, 'navTab=' + value + '&');
					return href;
				}

				if (/navTab=(.*)$/.test(href)) {
					href = href.replace(/navTab=(.*)$/, 'navTab=' + value);
					return href;
				}

				return href + '&navTab=' + value;
			}

			$('a[data-toggle=tab]').click(function() {
				var value = $(this).attr('value');

				$('div.dataTables_paginate a').each(function() {
					var link = $(this);
					var href = link.attr('href');

					link.attr('href', replaceParam(href, value));
				});

				$('#table-detailTree a').each(function() {
					var link = $(this);
					var href = link.attr('href');

					link.attr('href', replaceParam(href, value));
				});
			});

			var navTab = '${param["navTab"]}';
			if (navTab == "profile") {
				$('#profileLink').tab('show');
			} else {
				$('#homeLink').tab('show');
			}
		});
	</script>
</body>
</html>