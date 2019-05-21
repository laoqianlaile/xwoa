<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/arrow.js"
            type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/themes/css/arrow.css"
          rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
          rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/jquery.easyui.min.js"></script>
<title></title>
</head>
<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form action="oaArrangeweek" method="post" namespace="/oa" id="oaArrangeweekForm">
		<input type="hidden" id="popPage" name="popPage" value="${popPage}" />
		<input type="hidden" id="meetType" name="meetType" value="${meetType}" />
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="state" name="state" value="${object.state}" />
		<input type="hidden" id="begindate" name="begindate" />
		<input type="hidden" id="enddate" name="enddate" />
		<input type="hidden" id="office_djid" name="office_djid" value="${object.office_djid}" />
		<input type="hidden" id="office_applyItemType" name="office_applyItemType" value="${object.office_applyItemType}" />
		<input type="hidden" id="office_itemType" name="office_itemType" value="${object.office_itemType}" />
		<input type="hidden" id="office_optType" name="office_optType" value="${object.office_optType}" />


		<fieldset class="_new">
			<c:if test="${empty object.djId }">
				<legend>工作安排填报</legend>
			</c:if>
			<%-- <c:if test="${!(empty object.djId) }">
				<legend>编辑工作安排</legend>
			</c:if> --%>
		</fieldset>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
			<tr>
				<td class="addTd">时间<font color="#ff0000">*</font>
				</td>
				<td align="left" colspan="3"><input type="text" class="Wdate"
					readonly="readonly" style="width: 200px; height: 30px;"
					id="selectTime" name="selectTime"
					value='<fmt:formatDate value="${object.createtime}"  pattern="yyyy-MM-dd HH:mm"/>'
					onclick="Wdate()" placeholder="选择日期" /></td>
			</tr>
			<tr>
			<td class="addTd">活动类型<font color="#ff0000">*</font></td>
				<td align="left" colspan="2">
					<select  id="itemtype" name="itemtype" style="width: 200px;height:25px;">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('oaItemType')}">
							<option value="${row.datacode}"
								<c:if test="${row.datacode == object.itemtype}"> selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td class="addTd">关联办件</td>
				<td align="left" colspan="3">
					<input type="text" name="office_bjName" id="office_bjName" value="${object.office_bjName}" style="width:85%;height:30px;"/>
					<input type="button" class='btn' id="relativeOffice" name="relativeOffice" value="选择"
						   onclick="similarity('<%=request.getContextPath()%>/oa/vOptBaseList!listArrangeWeek.do',null,null)">
				</td>
			</tr>

			<c:if test="${'0' eq listType}">
				<tr>
					<td class="addTd">责任处室</td>
					<td align="left" colspan="3"><select name="depno" id="depno">
							<option value="">---请选择---</option>
							<c:forEach items="${unitlist}" var="u">
							<option value="${u.unitcode}"
								<c:if test="${u.unitcode == object.depno}"> selected="selected"</c:if>>
								<c:out value="${u.unitname}" />
							</option>
							</c:forEach>
					</select></td>
				</tr>
			</c:if>
			<c:if test="${('1' eq listType)}">
			<tr>
				<td class="addTd">参加领导</td>
				<td align="left" colspan="3">
					<input type="hidden" id="attendleaderscodes" name="attendleaderscodes" value="${object.attendleaderscodes}"/>
					<input type="text" name="attendleaders" id="attendleaders" value="${object.attendleaders}" style="width:85%;height:30px;" readonly="readonly"/>
					<input type="button" class='btn' id="attendingLeader" name="attendingLeader" value="选择">
				</td>
			</tr>
			</c:if>
			<c:if test="${('0' eq listType)}">
			<tr>
				<td class="addTd">处室负责人</td>
				<td align="left" colspan="3">
					<input type="hidden" id="attendleaderscodes" name="attendleaderscodes" value="${object.attendleaderscodes}"/>
					<input type="text" name="attendleaders" id="attendleaders" value="${object.attendleaders}" style="width:85%;height:30px;" readonly="readonly"/>
					<input type="button" class='btn' id="attendingLeader" name="attendingLeader" value="选择">
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="addTd">参加人员</td>
				<td align="left" colspan="3">
					<input type="hidden" id="attenduserscodes" name="attenduserscodes" value="${object.attenduserscodes}"/>
					<input type="text" name="attendusers" id="attendusers" value="${object.attendusers}" style="width:85%;height:30px;" readonly="readonly"/>
					<input type="button" class='btn' id="attendingperson" name="attendingperson" value="选择" >
				</td>
			</tr>
			<tr>
				<td class="addTd">地点<font color="#ff0000">*</font></td>
				<td align="left" colspan="3"><input type="text" id="address"
					name="address" maxlength="200" value="${object.address}"
					style="width: 100%; height: 30px;" /></td>
			</tr>
			<tr>
				<td class="addTd">工作内容<font color="#ff0000">*</font></td>
				<td align="left" colspan="3"><s:textarea name="remark"
						id="remark" cols="40" rows="6" style="width: 100%;height:150px" value="%{object.remark}">
					</s:textarea></td>
			</tr>
			<%-- <tr>
				<td class="addTd">登记人员<font color="#ff0000">*</font></td>
				<td align="left" colspan="3">
				<span>${cp:MAPVALUE('usercode', object.creater)}</span>
				</td>
			</tr>
			<tr>
				<td class="addTd">登记时间<font color="#ff0000">*</font>
				</td>
				<td align="left" colspan="3"><input type="text" class="Wdate"
					readonly="readonly" style="width: 200px; height: 30px;"
					id="createdate" name="createdate"
					value='<fmt:formatDate value="${object.createdate}"  pattern="yyyy-MM-dd HH:mm"/>'
					onclick="Wdate()" placeholder="选择日期" /></td>
			</tr> --%>
		<%--	<tr>
				<td class="addTd">备注</td>
				<td align="left" colspan="3"><s:textarea name="remarkInfo"
						id="remarkInfo" cols="20" rows="3" style="width: 100%;height:100px" value="%{object.remarkInfo}">
					</s:textarea></td>
			</tr>--%>
			<tr>
				<td align="left" colspan="4" style="text-align:center">
				<input type="button" name="back" Class="btn" onclick="closeDialog()" value="关 闭"/>
				<input type="button" class="btn" id="saveAndSubmit" name="submitBtn" onclick="doSubmitCheck('SUB');" value="提  交" />
				</td>
			</tr>
		</table>
	</s:form>
	<!-- 选择人员的模块Single -->
	<div id="attAlert" class="alert" style="width: 600px; height: 380px; position:absolute;bottom:160px;left:20%;overflow: hidden;">
		<h4 id="dialogHead">
			<span id="selectTT">选择</span><span id="close2"
											   style="float: right; margin-right: 8px;" class="close"
											   onclick="closeAlert('attAlert');">关闭</span>
		</h4>
		<div class="fix">
			<div class='fixTab'>
				<a class='selected' onclick="fixTab(this,'#leftSide')">常用</a>
				<a onclick="fixTab(this,'#leftSide2')">处室</a>
				<a onclick="fixTab(this,'#leftSide3')">角色</a>
			</div>
			<div class='leftSide' id="leftSide" style='width: 190px'></div>
			<div class='leftSide hide' id="leftSide2" style='width:190px;float:left'></div>
			<div class='leftSide hide' id="leftSide3" style='width:190px;float:left'></div>
			<div id="l-r-arrow">
				<div class="lb"></div>
				<div class="rb"></div>
			</div>
			<div id="rightSide">
				<ul></ul>
			</div>
			<div id="t-b-arrow">
				<div class="tb"></div>
				<div class="bb"></div>
				<b class="btns">
				<input id="save" class="btn" type="button"
						   value="保     存"/>
					<input id="clear" class="btn" type="button"
						   value="取     消" style="margin-top: 5px;"/>
					<input id="selectAll" class="btn" type="button"
						   value="短信全选"  style="margin-top: 5px;"/>
					<input id="cancelAll" class="btn" type="button"
						   value="短信取消" style="margin-top: 5px;"/>
					</b>
			</div>
			<script type="text/javascript">
                function fixTab(th,leftSide){
                    $(th).siblings('a').removeClass('selected');
                    $(th).addClass('selected');
                    $(leftSide).siblings('.leftSide').addClass('hide');
                    $(leftSide).removeClass('hide');
                }
			</script>
		</div>
	</div>
</body>
<script type="text/javascript">
/* var oOrgUser = new Object();
oOrgUser["meetingPersions"] = $("#meetingPersions");
$("#meetingPersions").click(function(){
	var d = '${userjson}';
	
	if(d.trim().length){
		selectPopWin(jQuery.parseJSON(d),$("#meetingPersions"),$("#userValue"));
	};
}); */
function selectPopWinTreeExtend(o1,o2,o3,box1,box2,box3){
    new treePersonExtend(o1,o2,o3,box1,box2,box3).init();
    setAlertStyle("attAlert");
}
function selectPopWin(json,o1,o2){
	new treePerson(json,o1,o2).init();
	setAlertStyle("attAlert");
}
function selectPopWinleader(json,o1,o2){
	new treePerson(json,o1,o2).init();
	setAlertStyle("attAlert");
}

$(document).ready(function() {
	<c:if test="${'0' eq listType}">
	$("#depno").val('${unit}');
	</c:if>
})
$("#attendingperson").click(function(){//选择参加人员
    var d = '${commonJson}';//常用
    var d2 = '${userjson}';//部门
    var d3 = '${rankjson}';//角色
    if(d.trim().length){
        // 人员选择树
        selectPopWinTreeExtend(jQuery.parseJSON(d),jQuery.parseJSON(d2),jQuery.parseJSON(d3),$("#attendusers"),$("#attenduserscodes"));
    };
});
$("#attendingLeader").click(function(){//选择参加领导
    var d = '${commonJson}';//常用
    var d2 = '${userjson}';//部门
    var d3 = '${rankjson}';//角色
    if(d.trim().length){
        // 人员选择树
        selectPopWinTreeExtend(jQuery.parseJSON(d),jQuery.parseJSON(d2),jQuery.parseJSON(d3),$("#attendleaders"),$("#attendleaderscodes"));
    };
});

	function Wdate() {
		WdatePicker({
			dateFmt : 'yyyy-MM-dd HH:mm',
			minDate : '#F{getminDate()}',
			onpicked : function(date) {
				if ("" != $("#djId").val()) {
					var weekDay = ("0" + new Date().getDay()).replace('00',
							'07');
					var today = dateFormat(newDate(), "yyyy-MM-dd");
					var today_begin = getBeginDate(today, weekDay);
					if ((newDate(date.cal.getDateStr())).getTime() < (newDate(today_begin))
							.getTime()) {
						top.art.dialog.confirm('您选择的日期为'
								+ date.cal.getDateStr() + '，已经过期，确认覆盖原来时间吗?',
								function() {
								}, function() {
									var t = '${object.createtime}';
									if ("" != t) {
										$("#selectTime")
												.val(t.substring(0, 16));
									} else {
										$("#selectTime").val("");
									}
								});
					}
				}
			}
		});
	}
	function getminDate() {
		if ("" == $("#djId").val()) {
			var weekDay = ("0" + newDate().getDay()).replace('00', '07');
			return getBeginDate(dateFormat(newDate(), 'yyyy-MM-dd'), weekDay);
		}
		return '2000-01-01';
	}
	function docheck(subType) {
		if ("" == $("#selectTime").val()) {
			return "时间不能为空！";
		}
		if (subType == 'SUB') {
			/* if ("" == $("#attendleaders").val()) {
				return "参加领导不能为空！";
			} */
			if ("" == $("#address").val()) {
				return "地点不能为空！";
			}
			if ("" == $("#remark").val()) {
				return "工作内容不能为空！";
			}if ("" == $("#itemtype").val()) {
				return "活动类型不能为空！";
			}
		}
		return "";
	}
	function doSubmitCheck(subType) {
	    debugger;
		var result = docheck(subType);
		if ("" != result) {
			DialogUtil.alert(result);
			return false;
		} else {
			var selectTime = newDate(($("#selectTime").val()).substring(0, 10));
			var weekDay = ("0" + selectTime.getDay()).replace('00', '07');
			$("#begindate")
					.val(
							getBeginDate(dateFormat(selectTime, 'yyyy-MM-dd'),
									weekDay));
			$("#enddate").val(
					getEndDate(dateFormat(selectTime, 'yyyy-MM-dd'), weekDay));
			if ("true" == $("#popPage").val()) {//对于从列表弹出的编辑页面
                if(""==$("#office_bjName").val()){
                    $("#office_djid").val("");
                    $("#office_applyItemType").val("");
                    $("#office_itemType").val("");
                    $("#office_optType").val("");
                }
				var saveParams = "selectTime:" + $("#selectTime").val()
						+ ";begindate:" + $("#begindate").val() + ";enddate:"
						+ $("#enddate").val() + ";attendusers:"
						+ $("#attendusers").val() + ";attendleaders:"
						+ $("#attendleaders").val() + ";address:"
						+ $("#address").val() + ";remark:" 
						+ $("#remark").val() + ";itemtype:"
                        + $("#itemtype").val()+ ";office_djid:"
						+ $("#office_djid").val()+";office_applyItemType:"
				        + $("#office_applyItemType").val()+";office_itemType:"
						+ $("#office_itemType").val()+";office_optType:"
					    + $("#office_optType").val()+";office_bjName:"
					    + $("#office_bjName").val();
				if (""!= $("#depno").val()) {
					saveParams = saveParams + ";depno:" + $("#depno").val();
				} 
				if(""!= $("#djId").val()) {
					saveParams = saveParams + ";djId:" + $("#djId").val();
				}
				if("" != $("#meetType").val()){
					saveParams = saveParams + ";meetType:" + $("#meetType").val();
				}

				if (subType == 'SUB') {
					DialogUtil.confirm("确定要提交相关工作安排吗？", function() {
						ajaxsubmitInfo(saveParams + ";state:1");
					});
				}
			} else {
				if (subType == 'SUB') {
					DialogUtil
							.confirm(
									"确定要提交相关工作安排吗？",
									function() {
										$("#state").val("1");
										$("#oaArrangeweekForm")
												.attr("action",
														"${pageContext.request.contextPath}/oa/oaArrangeweek!saveOaArrange.do");
										openLoadIng(true);
                                        $("#oaArrangeweekForm").submit(function(e){
                                            //保存成功后刷新
                                            var arr = $(parent.$.find('iframe'));
                                            arr.each(function(){
                                                if(this.id == "external_WORKPLANSUMMARY"||this.id == "external_WORKPLANSUMMARY2"||this.id == "external_GCSYZAP2" || this.id == "external_GCSYZAP"){
                                                    $(this).attr("src",this.src);
                                                }
                                            });
                                        });
									});
				}
				if ("SAVE" == subType) {
					DialogUtil
							.confirm(
									"确定要保存相关工作安排吗？",
									function() {
										$("#state").val("0");
										$("#oaArrangeweekForm")
												.attr("action",
														"${pageContext.request.contextPath}/oa/oaArrangeweek!saveOaArrange.do");
                                        openLoadIng(true);
                                        $("#oaArrangeweekForm").submit(function(e){
                                            //保存成功后刷新
                                            var arr = $(parent.$.find('iframe'));
                                            arr.each(function(){
                                            	if(this.id == "external_WORKPLANSUMMARY"||this.id == "external_WORKPLANSUMMARY2"||this.id == "external_GCSYZAP2" || this.id == "external_GCSYZAP"){
                                                    $(this).attr("src",this.src);
                                                }
                                            });
                                        });
                                    });
				}

			}
		}
	}
	function ajaxsubmitInfo(saveParams) {
		var urls = "${pageContext.request.contextPath}/oa/oaArrangeweek!saveOaArrange.do?saveParams="
				+ encodeURI(encodeURI(saveParams));
        openLoadIng(true);
		$.ajax({
			type : "post",
			url : urls,
			async : false,
			success : function(resp) {
				if (resp == "OK") {
				    //保存成功后刷新
				    var arr = $(parent.$.find('iframe'));
                    arr.each(function(){
                    	if(this.id == "external_WORKPLANSUMMARY"||this.id == "external_WORKPLANSUMMARY2"||this.id == "external_GCSYZAP2" || this.id == "external_GCSYZAP"){
                            $(this).attr("src",this.src);
                        }
                    });
					DialogUtil.close();
                } else {
					DialogUtil.alert("保存失败！", "", 150, 100);
				}

			}
		});
	}


	 function closeDialog() {
	        var list = top.art.dialog.list;
	        for (var i in list) {
	            list[i].close();
	        };
	    }
	$('#attAlert').draggable({
		handle:'#dialogHead'
	});

function similarity(winUrl, winName, winProps) {
    if (winProps == '' || winProps == null) {
        winProps = 'height=800px,width=1400px,directories=false,location=false,top=100,left=250,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
    }
    window.open(winUrl, winName, winProps);
}



</script>
</html>