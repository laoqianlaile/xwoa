<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>查看工作安排</title>
</head>
<style type="text/css">
	.viewTable tr {
		height: 40px;
	}

	#office_bjName u:hover {
		color: red;

	}
</style>
<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="_new">
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<input type="hidden" id="office_djid" name="office_djid" value="${object.office_djid}" />
			<input type="hidden" id="office_applyItemType" name="office_applyItemType" value="${object.office_applyItemType}" />
			<input type="hidden" id="office_itemType" name="office_itemType" value="${object.office_itemType}" />
			<input type="hidden" id="office_optType" name="office_optType" value="${object.office_optType}" />
			<tr>
				<td class="addTd">时间</td>
				<td align="left" colspan="3"><fmt:formatDate
						value="${object.createtime}" pattern="yyyy年M月d日 H时mm分" /></td>
			</tr>
			<tr>
				<td class="addTd">活动类型</td>
				<td align="left" colspan="3">${cp:MAPVALUE('oaItemType',object.itemtype)}</td>
				<%-- ${cp:MAPVALUE('oaItemType',itemtype)} --%>
			</tr>
			<c:if test="${not empty object.office_bjName}">
			<tr>
				<td class="addTd">关联办件</td>
				<td align="left" colspan="3" onclick="viewDetail(null,null,null)" id="office_bjName"><u>${object.office_bjName}</u></td>
				<%-- ${cp:MAPVALUE('oaItemType',itemtype)} --%>
			</tr>
			</c:if>
			<c:if test="${'B' eq meetType}">
				<tr>
					<td class="addTd">责任处室</td>
					<td align="left" colspan="3">${cp:MAPVALUE("unitcode",object.depno)}</td>
				</tr>
			</c:if>
			<c:if test="${'B' eq meetType}">
			<tr>
				<td class="addTd">处室负责人</td>
				<td align="left" colspan="3">${object.attendleaders}</td>
			</tr>
			</c:if>
			<c:if test="${'A' eq meetType}">
			<tr>
				<td class="addTd">参加领导</td>
				<td align="left" colspan="3">${object.attendleaders}</td>
			</tr>
			</c:if>
			<tr>
				<td class="addTd">参加人员</td>
				<td align="left" colspan="3">${object.attendusers}</td>
			</tr>

			<tr>
				<td class="addTd">地点</td>
				<td align="left" colspan="3">${object.address}</td>
			</tr>
			<tr>
				<td class="addTd">工作内容</td>
				<td align="left" colspan="3">${object.remark}</td>
			</tr>
			<tr>
				<td class="addTd">登记人员</td>
				<td align="left" colspan="3">
				${cp:MAPVALUE('usercode', object.creater)}</td>
			</tr>
			<tr>
				<td class="addTd">登记时间</td>
				<td align="left" colspan="3"><fmt:formatDate
						value="${object.createdate}" pattern="yyyy年M月d日 H时mm分" /></td>
			</tr>
			
		<%--	<tr>
				<td class="addTd">备注</td>
				<td align="left" colspan="3">${object.remarkInfo}</td>
			</tr>--%>
		</table>
		<div align="center" style="margin-right: 20px;margin-top: 20px;">
			<object id="WebBrowser" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0"></object>
			<input id="printBtn" name="printBtn" type="button" value="打印" Class="btn" onclick="printPage()" />
			<input id="closeDg" name="closeDg" type="button" name="back" Class="btn"
				   onclick="closeDialog()" value="关闭"/>
		</div>
	</fieldset>
</body>
<script type="text/javascript">
    function printPage() {
        if(!-[1,]){//IE
            document.all.WebBrowser.ExecWB(6,1);
        }else{//chrome firefox
            window.print();
        }
    }

    function closeDialog() {
//        alert(1);
//        var list = top.art.dialog.list;
//        for (var i in list) {
//            list[i].close();
//        };
		DialogUtil.close();
    }

    function viewDetail(winUrl, winName, winProps) {
        debugger;
        var optType=$('#office_optType').val();
        var djId=$('#office_djid').val();
        var applyItemType=$('#office_applyItemType').val();
		winUrl = "${pageContext.request.contextPath}/"+optType+"!generalOptView.do?djId="+djId+"&nodeInstId=0"+"&applyItemType="+applyItemType;
        if (winProps == '' || winProps == null) {
            winProps = 'height=800px,width=1400px,directories=false,location=false,top=100,left=250,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
        }
        window.open(winUrl, winName, winProps);
    }
</script>

</html>