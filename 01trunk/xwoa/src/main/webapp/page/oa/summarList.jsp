<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>请假加班出差列表</title>
<script type="text/javascript">
var beginDate="";
var endDate="";
var type="";
var unitcode="";
var iframe=1;//默认加载li
 $(function(){	
	 unitcode = $("#unit").attr("value");
		d=new Date();
		d.setDate(1);	
		d.setMonth(d.getMonth());  
		
		var compareDate = new Date();
		if(d.getMonth() == 11){
			compareDate.setFullYear(d.getFullYear() + 1);
			compareDate.setMonth(0, 1);
		}else{
			compareDate.setMonth(d.getMonth() + 1, 1);
		}
		var LastDay = new Date(compareDate.getTime()-1000*60*60*24).getDate();
		var month=String(d.getMonth()+1);
		if(month.length<2){
			month="0"+month;
		}
		
		$("#s_auditDate").attr("value",d.getFullYear()+"-"+(month));
		time=$("#s_auditDate").attr("value");
		beginDate=time+"-"+"1";
		endDate=time+"-"+LastDay;	
		//alert("beginDate:" + beginDate + "--endDate:" + endDate);
		var types=$(".htype");
		
		for(var i=0;i<types.length;i++){
			if(types[i].value =='N' ){
				types[i].checked=true;		
			}			
		}
		type="N";
		setFrame();
		$(".ul li").click(function(e){
			if(this.className=="selected"){
				return ;
			}else{
				var lis=$(".ul li");
				for(var i=0;i<lis.length;i++ ){
					if(lis[i].className=="selected"){
						lis[i].className="";
					}
		
					if(this==lis[i]){
						iframe=i+1;
					}
				}
				this.className="selected";
				setFrame();
				
				if(iframe==1){
					$("#tr2").show();
				}else{
					$("#tr2").hide();
				}
			}
		});
	
}); 
	function find(){
		 type=getTypes();
		var time=$("#s_auditDate").attr("value");
		if(time!=""){
			var t=time.split("-");
			var d= new Date();
			d.setFullYear(t[0]);
			d.setMonth(t[1]);
			d.setDate(1);
			var LastDay = new Date(d.getTime()-1000*60*60*24).getDate();
			beginDate=time+"-"+"1";
			endDate=time+"-"+LastDay;
		}else{
			alert("请选择查询时间");
		}
		setFrame();
	}	
	function getTypes(){
		var type="";
		var types=$(".htype");
	
		for(var i=0;i<types.length;i++){
			if(types[i].checked==true){
				if(type==""){
					type= type+types[i].value;	
				}					
				else if(type!=""){
					type= type+","+types[i].value;	
				}
			}
			
		}
		return type;
	}
	function setFrame(){	
		if(iframe==0)
			$("#li1frame").attr("src", "<%=request.getContextPath()%>/stat/twodimenform!doStat.do?modelName=KQHZ&s_beginDate="+beginDate+"&s_endDate="+endDate+"&s_unitcode="+unitcode+"&s_type="+type);
		if(iframe==1)	
			$("#li1frame").attr("src", "<%=request.getContextPath()%>/oa/oaLeaveOverTime!editDraft3.do?selUnit=" + $("#zbOrgCode").val() + "&ReasonType="+type+"&auditDate="+$("#s_auditDate").attr("value"));
		if(iframe==2) {
			var s_unitcode = unitcode;
			var s_unitcodeTemp = $("#zbOrgCode").val();
			if(typeof(s_unitcodeTemp) != "undefined" && s_unitcodeTemp != "") {
				s_unitcode = s_unitcodeTemp;
			}
			$("#li1frame").attr("src", "<%=request.getContextPath()%>/stat/twodimenform!doStatKQ.do?modelName=TYKQ&s_beginDate="+beginDate+"&s_endDate="+endDate+"&s_unitcode="+s_unitcode+"&auditDate="+$("#s_auditDate").attr("value") + "&selUnitcode="+s_unitcode);
		}
	}
	
</script>
<style type="text/css">
.menu{height:30px;font-size:14px;border-top:#ccc solid 1px;border-right:#ccc solid 1px;margin-left:25px;padding:5px}
.menu li {
    background: none repeat scroll 0 0 #e0e2eb;
    border: 1px solid #ccc;
    color: #666;
    cursor: pointer;
    float: left;
    height: 30px;
    line-height: 30px;
    overflow: hidden;
    text-align: center;
    padding:0px 10px 0px 10px;
    list-style-type:none;
}
.menu li.selected{background:#fff;color:#336699;font-weight:bold;}
</style>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search">
		<legend>考勤汇总</legend>
		<table>
			<tr>
				<td>统计年月:</td>
				<td align="left"><input type="text" class="Wdate" id="s_auditDate"
					 name="s_auditDate"
					onclick="WdatePicker({dateFmt:'yyyy-MM'})" placeholder="选择统计年月">
					<input type="hidden" id="unit" value="${unitcode}">
				</td>
				<c:if test="${not empty rskUnit}">
				<td>统计部门:</td>
			    <td align="left">
			     <select id="zbOrgCode" name="zbOrgCode">
						<option value="">---请选择---</option>
						<c:forEach items="${unitList}" var="unit">
							<option value="${unit.unitcode}" <c:if test="${param.zbOrgCode == unit.unitcode}" >selected = "selected"</c:if>>
								<c:out value="${unit.unitname}" />
							</option>
						</c:forEach>
				 </select>
				</td>
			    </c:if>
			</tr>
			
			
		
		   <tr id="tr2">
				<td>统计假期类型：</td>
				<td <c:if test="${not empty rskUnit }">colspan="3"</c:if> ><c:forEach var="row" items="${cp:LVB('LeaveOvertime')}">

						<input type="checkbox" class="htype" name="htype" value="${row.value}"
						 style="vertical-align:middle;">${row.label}
						&nbsp;&nbsp;
						
			</c:forEach>
			</tr> 
			<tr>
				<td  <c:if test="${empty rskUnit }">colspan="2"</c:if><c:if test="${not empty rskUnit }">colspan="4"</c:if> ><input type="button" onclick="find()" class="btn" value="查询" style="float: right;margin-right: 100px;"></td>
			</tr>
		</table>
			</fieldset>
		<div style="margin-top: 10px; ">
			<div id="menudiv" style="width: 100%;" >
				<div class="menu" >			
					<ul class="ul">
<!-- 						<li class="selected" id="li1">人事考勤汇总</li> -->
						<li class="selected" id="li2">人事考勤明细统计</li>
						<li id="li3">通用考勤汇总</li>
					</ul>
				</div>
			</div>
	</div> 
	<div id="content"  style="margin-top:1px;">
			<div>
				<iframe id="li1frame" style="width: 100%;height:500px; border: 0px;" 
					src=""></iframe>
			</div>
	</div>
	
</body>
</html>
