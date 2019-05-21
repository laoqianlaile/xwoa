<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/easyui/jquery.messager.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<style>
<!--
.leftInfo table{table-layout: fixed}
.leftInfo ul{table-layout: fixed}
.tzgg_custom td a,.nowrap {
    display: block;
    width: 80%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.tzgg_custom td{
border-bottom: 1px #ddd solid;
}
-->
</style>
<div class="left_custom">
	<c:if test="${not(username=='noname')}">
		<authz:authentication var="username" property="name" />
		<div><c:if test="${username!='noname'}">
								您好，<%-- ${cp:MAPVALUE("unitcode",unitcode) } --%> ${username}
								</c:if>
								<span class="icon  icon-carat-1-s">	</span></div>
		<c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ', 'showOnline')}">	
		<div><a style="color:white; font-size: 12px;" onmouseover="this.style.cursor='hand'" onclick="doShow();" >当前在线<span id="userCountOnlineDash" style="cursor:pointer;"></span>人</a></div>
		</c:if>
		<!-- <div>上次登录2016-04-27&nbsp;12:00</div> -->
		<div>
		   <a title="锁屏" href="javascript:void(0);" onclick="lockScreen(false);return false;"><img alt='锁屏' src='../newStatic/image/left_custom1.png'/></a>
		   <c:if test="${cp:MAPSTATE('SYSPARAM','CAS') eq 'T'}">
				<a title="注销" style="text-decoration: none" href="<s:url value='/j_spring_security_logout'/>"><img alt='关闭' src='../newStatic/image/left_custom3.png'/></a>
			</c:if>
			<c:if test="${not (cp:MAPSTATE('SYSPARAM','CAS') eq 'T')}">
				<a title="注销" style="text-decoration: none" href="<s:url value='/j_spring_security_logout'/>"><img alt='关闭' src='../newStatic/image/left_custom3.png'/></a>
			</c:if>
			<a  title="修改密码" href="<s:url value='/sys/userDef!modifyPwdPage.do' />" target="dialog" drawable="false" width="550" height="350"><img src='../newStatic/image/white_key.png'/></a>
		</div>
	</c:if>
	<div class="seg_line"></div>
	
	<ul style="margin-top: 14px;">
				<li class="shortcutKey"><img src='../themes/default/improve/fawenguidang.png'/>
				<a href='javaScript:void(0);'
					onclick="openMenu(this,'wjgfw','<%=request.getContextPath()%>/oa/optFileTransferSend!listReportToSysin.do');return false;">
					<span style="color: white;">&nbsp; &nbsp;&nbsp;文件上报</span>
				</a></li>

			   <li class="shortcutKey"><img src='../themes/default/improve/shouwenguidang.png'/><a href='javaScript:void(0);'
					onclick="openMenu(this,'wjgsw','<%=request.getContextPath()%>/oa/optFileTransferReceive!list.do');return false;">
					<span style="color: white;">&nbsp; &nbsp;&nbsp;文件接收</span>
				</a></li>
			
	</ul>
	

</div>
<div class="improveLeft" style="height: 98%; margin-top: 20px;" >
	<table>
		<tr>
			<td colspan="3" valign="top" style="padding: 0 10px 0 20px;">
							<div class="childBlockTile wjgfw_custom" >
									<div class="childBlockTile_left"  >
			                             <div id="tongzhi-div"
											class="childBlockTile_left1 document-switch-hover">文件上报</div>
									</div>
									<div class="childBlockTile_right" name="dwbl-div">
										<a class="chooseRCType" 
											title="文件上报" 
											onclick="openMenu(this,'wjgfw','<%=request.getContextPath()%>/oa/optFileTransferSend!listReportToSysin.do');return false;"
											href="javaScript:void(0);">
											<span class="childBlockMore"></span>
										</a>
									</div>				
								</div>
								
								<table  class="tzgg_custom">
								<tr><td style='width:60%' >标题</td><td  style='width:120px;float: right;text-align:left '>收件人</td><td style='width:108px;'>上报时间</td></tr>
								</table>
								<table id="wjgfw" class="tzgg_custom">
								
									
								</table>

				
			</td>	
			<td colspan="3" valign="top" style="padding: 0 10px 0 20px;" >
			<div class="childBlockTile wjgsw_custom">
									<div class="childBlockTile_left">
			                             <div id="tongzhi-div"
											class="childBlockTile_left1 document-switch-hover">文件接收</div>
									</div>
									<div class="childBlockTile_right" name="dwbl-div">
										<a class="chooseRCType" 
											title="文件接收" 
											onclick="openMenu(this,'wjgsw','<%=request.getContextPath()%>/oa/optFileTransferReceive!list.do');return false;"
											href="javaScript:void(0);">
											<span class="childBlockMore"></span>
										</a>
									</div>				
								</div>
								
								<table  class="tzgg_custom">
								<tr><td style='width:60%' >标题</td><td  style='width:120px;float: right;text-align:left '>来文单位</td><td style='width:108px;'>接收时间</td></tr>
								</table>
								<table id="wjgsw" class="tzgg_custom">
									
								</table>
			</td>	
		</tr>
	</table>
	
</div>

<div id="improveFooter">
	<div>
	</div>
<!-- 	<div>版权所有：南京市总工会 &nbsp;&nbsp;&nbsp;技术支持：江苏南大先腾信息产业有限公司 </div> -->
</div>


<script type="text/javascript">
$(function(){
	wjgfw();
	wjgsw();
});
    function changeTime(time){
    	var s=time.substr(5,5);
    	return s;
    }


	function formatTime(date){
		var times=date.split("-");
		var changetime=times[0]+"年"+times[1]+"月"+times[2].substring(0,2)+"日";
		return changetime;
	}
	function formatString(str){
		var reg=new RegExp("T","g"); //创建正则RegExp对象  
		var newstr=str.replace(reg," ");//装T替换成空格
		return newstr;
	}
	
	 function wjgfw(){
	    	$.ajax({
	    		url:"<%=request.getContextPath()%>/oa/optFileTransferSend!listReportToSysin_dashboard.do",
	    		type:"post",
	    		dataType:"json",
	    		success:function(date){
	    			
	    			var list=date;
	    			$("#wjgfw").html("");
	    			 $.each(list,function(i,o){
	    				 var item;
	    				
	    				item="<tr style='border-bottom: 1px dashed #dedede;'>";
	  					item+="<td ";
	  					item+="style='width:60%' ";
	  					item+=">"+
							"<a id='wjgfw"+i+"' href='javaScript:void(0);retrun false;'>"+
							"<span style='color:black;' title='"+o.subject+"'>"+o.subject+"</span></a></td>";
							/* 接收人 */
							item+="<td style='width:120px;float: right;text-align:left ' class='nowrap'> <span  title='"+o.receiverName+"'>"+o.receiverName+" </span></td>";
						
	    					/*时间  */
						item+="<td style='width:108px;'><span class='date' title='"+formatString(o.createTime)+"'>"+formatTime(o.createTime)+"</span></td>"+
						"</tr>";
						if(i<=10){
							$("#wjgfw").append(item);
							var id="wjgfw"+i;
							$('#' + id).click(function(){
									
								var url='${ctx}/oa/optFileTransferSend!view.do?id='+o.id+'&show_type=art';
								art.dialog.open(url, {title: '文件上报', width: 1050, height: 400});
// 								openMenu(this, 'wjgfw', '${ctx}/oa/optFileTransferSend!view.do?id='+o.id);	
								return false;
							});						
						};
	    			}) ;
	    		}
	    	});
	    }
	 
	 function wjgsw(){
	    	$.ajax({
	    		url:"<%=request.getContextPath()%>/oa/optFileTransferReceive!list_dashboard.do",
	    		type:"post",
	    		dataType:"json",
	    		success:function(date){
	    			var list=date;
	    			$("#wjgsw").html("");
	    			 $.each(list,function(i,o){
	    				 
	    				 var item;
	    				item="<tr style='border-bottom: 1px dashed #dedede;'>";
	  					item+="<td ";
	  				    item+="style='width:60%' ";
	  					item+=">"+
							"<a id='wjgsw"+i+"' href='javaScript:void(0);retrun false;'>"+
							"<span style='color:black;' title='"+o.subject+"'>"+o.subject+"</span></a></td>";
							/* 接收人 */
							item+="<td style='width:120px;float: right;text-align:left ' class='nowrap'>  <span >"+o.receiverName+" </span></td>";
						
	    					/*时间  */
						item+="<td style='width:108px;'><span class='date' title='"+formatString(o.createTime)+"'>"+formatTime(o.createTime)+"</span></td>"+
						"</tr>";
						if(i<=10){
							$("#wjgsw").append(item);
							var id="wjgsw"+i;
							$('#' + id).click(function(){
								var url='${ctx}/oa/optFileTransferReceive!view.do?id='+o.id+'&show_type=art';
								art.dialog.open(url, {title: '文件接收', width: 1050, height: 400});
								return false;
							});						
						};
	    			}) ;
	    		}
	    	});
	    }
</script>