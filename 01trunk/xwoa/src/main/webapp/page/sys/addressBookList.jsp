<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ include file="/page/common/option.jsp"%>

<title>通用的通讯录</title>

<script language="javascript" type="text/javascript" src="<s:url value="/scripts/colorbox/jquery.colorbox.js"/>" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>

<script type="text/javascript">
    function showAddressBook(id){
        var options ={};
      //  options.displayNull = true;
     //   options.nullReplace = '---';
        addressBook.showDetail(id,options);
    }
    
</script>
</head>
<body>
	<fieldset>
		<legend>查询条件</legend>
		<s:form action="addressBook">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr height="30">
					<td width="100" align="right">智能搜索:</td>
					<td><s:textfield name="s_searchstring" /></td>
				</tr>
				<tr height="30">
					<td align="right">备注:</td>
					<td><s:textfield name="s_memo" /></td>
				</tr>
				<tr>
					<td></td>
					<td><s:submit type="submit" method="list" cssClass="btn"
							value="查询" /><s:submit type="button" cssClass="btn"
							onclick="newAddressBook()" value="在对话框中新增" /></td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="addressBook!list.do" items="objList"
		var="addressBook" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:exportXls fileName="out.xls"></ec:exportXls>
		<ec:exportPdf fileName="out.pdf" headerColor="blue"
			headerBackgroundColor="white"></ec:exportPdf>
		<ec:row>
			<ec:column property="addrbookid" title="addrbookid"
				style="text-align:center" viewsDenied="html" />
			<ec:column property="representation" title="representation"
				style="text-align:center" />
			<ec:column property="unitname" title="unitname"
				style="text-align:center" viewsDenied="html" />
			<ec:column property="deptname" title="deptname"
				style="text-align:center" viewsDenied="html" />
			<ec:column property="rankname" title="rankname"
				style="text-align:center" viewsDenied="html" />
			<ec:column property="qq" title="qq" style="text-align:center" />
			<ec:column property="buzphone" title="buzphone"
				style="text-align:center" viewsDenied="html" />
			<ec:column property="mobilephone" title="mobilephone"
				style="text-align:center" />
			<ec:column property="email" title="email" style="text-align:center" />
			<ec:column property="homepage" title="homepage"
				style="text-align:center" viewsDenied="html" />
			<ec:column property="memo" title="memo" style="text-align:center" />
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center;">
				<div class="option-btn">
					<a class="showDetail" rel="check" styleopt="viewDetail"
						href='addressBook!view.do?addrbookid=${addressBook.addrbookid}'></a>
					<a href='#' class="showDetail" styleopt="viewInDialog"
						onclick="showAddressBook('${addressBook.addrbookid}')"></a> <a
						href='addressBook!edit.do?addrbookid=${addressBook.addrbookid}'
						styleopt="edit"></a> <a
						href='addressBook!delete.do?addrbookid=${addressBook.addrbookid}'
						onclick='return confirm("是否删除 ${addressBook.representation} 通讯录?");'
						styleopt="delete"></a>
				</div>
			</ec:column>

		</ec:row>
	</ec:table>
</body>
</html>
