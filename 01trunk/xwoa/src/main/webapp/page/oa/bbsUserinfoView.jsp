<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<script type="text/javascript">
	function onBack()
	{
		if (window.history.length > 0)
			window.history.back();
		else
      		window.close();
  	}	
</script>
<style>
 .vertical_ul{list-style:none;width:150px;}
 .vertical_ul li{height:40px;line-height:40px;text-align:center;background: rgb(99, 150, 194);border:1px solid #ccc;cursor:pointer;}
 .vertical_ul li.select{background:white;font-weight: bold;}
 .r-legend{margin-bottom:0px;}

 fieldset td{
 border: 1px solid rgb(219, 218, 218);
 padding: 5px;
color: rgb(80, 80, 80);
}
fieldset table {
table-layout: fixed;
}
</style>
<title><s:text name="oaUserinfo.view.title" /></title>
</head>

<body>

    <fieldset class="_new">
		<legend >	
		个人信息查看
		<c:if test="${object.usercode == session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}">
		<a style="float:right; cursor:pointer;color: rgb(12, 109, 160); text-decoration: none; font-size: 16px;" onclick="window.location.href='${contextPath }/oa/oaUserinfo!userinfoEdit.do?s_usercode=${object.usercode }&flag=${flag}'">
		<img alt="" style="width:30px;height:30px;" src="${pageContext.request.contextPath}/styles/default/css/tvimage/edit.png"><font style="margin-left:5px;">编辑</font>
		</a>
		</c:if>
		</legend>
		
		<s:form action="oaUserinfo" method="post" namespace="/oa"
		id="oaUserinfoForm">

			<!-- <div style="width: 20%; float: left; margin-top: 10px;">
				<ul class="vertical_ul">
					<li class="select">基本资料</li>
					<li>联系方式</li>
					<li>教育情况</li>
					<li>工作情况</li>
					<li>个人信息</li>
				</ul>
			</div> -->
			<div id="rightDiv" style="width: 100%; float: left; margin-top: 10px;">
				<table id="table0" class="active">
					<tr>
						<td align="right" >用户名</td>
						<td colspan="5">${cp:MAPVALUE("usercode",object.usercode)} &nbsp;</td>
					</tr>

					<tr>
						<td align="right">头像</td>
						<td colspan="5">
									<img id="appicon"
										src='${pageContext.request.contextPath }/oa/oaUserinfo!showImage.do?usercode=${object.usercode}&flag=0'
										alt="头像" style="width: 200px; height: 150px;" align="left" />
						</td>
					</tr>

					<tr>
						<td align="right">职务</td>
						<td colspan="5">${cp:MAPVALUE("RankType",userrank)} &nbsp;</td>
					</tr>

					<c:if test="${'1' eq isshowsex }">
						<tr>
							<td align="right">性别</td>
							<td colspan="5">${cp:MAPVALUE('sex',sex)} &nbsp;</td>						
						</tr>
					</c:if>

					<tr>
						<td align="right" >出生年月</td>
						<td colspan="5"><fmt:formatDate value='${birthday}'
								pattern='yyyy-MM-dd' /> &nbsp;</td>
					</tr>

					<c:if test="${'1' eq isshowplaceofbirth }">
						<tr>
							<td align="right">出生地</td>
							<td colspan="5">${object.placeofbirth} &nbsp;</td>
						</tr>
					</c:if>
					
					<c:if test="${'1' eq isshowtolive }">
					<tr>
						<td align="right">居住地</td>
						<td colspan="5">${object.tolive } &nbsp;</td>
					</tr>
				    </c:if>

				</table>
		</fieldset>
		<fieldset class="_new">
				<legend >	
				联系方式
				</legend>
				<table id="table1">
					<c:if test="${'1' eq isshowtelephone }">
						<tr>
							<td align="right">固定电话</td>
							<td colspan="5">${object.telephone } &nbsp;</td>
						</tr>
					</c:if>
					
					<c:if test="${'1' eq isshowmobilephone }">
						<tr>
							<td align="right">手机</td>
							<td colspan="5">${object.mobilephone } &nbsp;</td>
						</tr>
					</c:if>
					
					<c:if test="${'1' eq isshowqq }">
						<tr>
							<td align="right">QQ</td>
							<td colspan="5">${object.qq } &nbsp;</td>
						</tr>
					</c:if>
					
					<c:if test="${'1' eq isshowmsn }">
						<tr>
							<td align="right">MSN</td>
							<td colspan="5">${object.msn } &nbsp;</td>
						</tr>
					</c:if>
					
					<c:if test="${'1' eq isshowemail }">
						<tr>
							<td align="right">Email</td>
							<td colspan="5">${object.email } &nbsp;</td>
						</tr>
					</c:if>

					<tr>
						<td align="right">邮寄地址</td>
						<td colspan="5">${object.mailingaddress} &nbsp;</td>
					</tr>
					<tr>
						<td align="right">其它联系方式</td>
						<td colspan="5">${object.otherlinks } &nbsp;</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="_new">
				<legend >	
				教育情况
				</legend>
				<table id="table2">
					<c:if test="${'1' eq isshowrecord }">
						<tr>
							<td align="right" >学历</td>
							<td colspan="5">${object.record } &nbsp;</td>
						</tr>
					</c:if>
					
					<tr>
						<td align="right" >毕业院校</td>
						<td colspan="5">${object.school } &nbsp;</td>
					</tr>
			   </table>
		</fieldset>
		<fieldset class="_new">
				<legend >	
				个人信息
				</legend>
				<table id="table3">
					<tr>
						<td align="right">证件类型</td>
					    <td colspan="5">${cp:MAPVALUE('DocumentType',object.documenttype)} &nbsp;</td>
					</tr>

					<c:if test="${'1' eq isshowidno }">
						<tr>
							<td align="right">证件号</td>
							<td colspan="5">${object.idno } &nbsp;</td>
						</tr>
					</c:if>

					<tr>
						<td align="right">用户等级</td>
						<td colspan="5">${object.mailingaddress} &nbsp;</td>
					</tr>

					<tr>
						<td align="right">等级分数</td>
						<td colspan="5">${object.levelnum } &nbsp;</td>
					</tr>

					<tr>
						<td align="right">个人签名</td>
						<td colspan="5">${object.personalsignature} &nbsp;</td>
					</tr>

					<c:if test="${'1' eq isshowintroduce }">
						<tr>
							<td align="right">自我介绍</td>
							<td colspan="5">${object.introduce} &nbsp;</td>
						</tr>
					</c:if>

					<c:if test="${'1' eq isshowhobbies }">
						<tr>
							<td align="right">兴趣爱好</td>
							<td colspan="5">${object.hobbies} &nbsp;</td>
						</tr>
					</c:if>
				</table>
			</fieldset>
			</div>
		</s:form>

		<%-- <div class="formButton">
			<input type="button" name="save" class="btn" value="编辑" 
			      onclick="window.location.href='${contextPath }/oa/oaUserinfo!userinfoEdit.do?s_usercode=${object.usercode }&flag=${flag}'"/>
		</div> --%>
	</fieldset>

</body>
<script type="text/javascript">
$(document).ready(
			function() {
				init = setInterval("FrameUtils.initialize(window, init)",
						MyConstant.initTimeForAdjustHeight);
			});
	/* $(".vertical_ul li").each(function(index) {
		$(this).click(function() {
			$(".vertical_ul li").removeClass("select");
			$(this).addClass("select");
			$("#rightDiv table").removeClass("active");
			$("#table" + index).addClass("active");
		});
	}); */
	
	$(function(){
		
		/* 改变打开图片链接的参数，以保证在IE下不会出现相同链接，防止不读取图片 */
		var imgSrc = $('#appicon').attr('src');
		if(imgSrc!=undefined){
			var params = imgSrc.split('&');
			if(params.length == 1){
				imgSrc = params[0] + "&flag=0";
			}else if(params.length == 2){
				// 分离第二个参数flag
				var flagParam = params[1].split('=');
				var flagValue = flagParam[1];
				
				flagValue ++;
				
				imgSrc = params[0] + '&flag=' + flagValue;
			}
			
			$('#appicon').attr('src', imgSrc);
		}
		
	});
</script>
</html>
