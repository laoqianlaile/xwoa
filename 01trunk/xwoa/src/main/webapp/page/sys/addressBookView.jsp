<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>请设定这个页面的标题</title>

<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	function onBack()
	{
		if (window.history.length > 0)
			window.history.back();
		else
      		window.close();
  	}
</script>
</head>

<body>
<p class="ctitle">请设定这个页面的标题</p>


<input type="button" value="返回" class="btn" onclick="onBack()" />
<p>	
	
<table  border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="addTd">
						通讯录ID
					</td>
					<td align="left">
					${object.addrbookid}
					</td>
				</tr>


				<tr>
					<td class="addTd">
						<c:out value="bodytype" />
					</td>
					<td align="left">
						${object.bodytype}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<c:out value="bodycode" />
					</td>
					<td align="left">
						${object.bodycode}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						描述
					</td>
					<td align="left">
						${object.representation}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位名
					</td>
					<td align="left">
						${object.unitname}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						部门名
					</td>
					<td align="left">
						${object.deptname}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						头衔名
					</td>
					<td align="left">
						${object.rankname}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						电子邮件
					</td>
					<td align="left">
						${object.email}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						电子邮件2
					</td>
					<td align="left">
						${object.email2}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						电子邮件3
					</td>
					<td align="left">
						${object.email3}
				</tr>	

				<tr>
					<td class="addTd">
						主页
					</td>
					<td align="left">
						${object.homepage}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						qq
					</td>
					<td align="left">
						${object.qq}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						msn
					</td>
					<td align="left">
						${object.msn}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						旺旺
					</td>
					<td align="left">
						${object.wangwang}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						商务电话
					</td>
					<td align="left">
						${object.buzphone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						商务电话2
					</td>
					<td align="left">
						${object.buzphone2}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						商务传真
					</td>
					<td align="left">
						${object.buzfax}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						助理电话
					</td>
					<td align="left">
						${object.assiphone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						回复电话
					</td>
					<td align="left">
						${object.callbacphone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						车载电话
					</td>
					<td align="left">
						${object.carphone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位电话
					</td>
					<td align="left">
						${object.unitphone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭电话
					</td>
					<td align="left">
						${object.homephone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭电话2
					</td>
					<td align="left">
						${object.homephone2}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭电话3
					</td>
					<td align="left">
						${object.homephone3}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭传真
					</td>
					<td align="left">
						${object.homefax}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						移动电话
					</td>
					<td align="left">
						${object.mobilephone}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						移动电话2
					</td>
					<td align="left">
					${object.mobilephone2}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						移动电话3
					</td>
					<td align="left">
						${object.mobilephone3}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位邮编
					</td>
					<td align="left">
						${object.unitzip}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位省
					</td>
					<td align="left">
						${object.unitprovince}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位城市
					</td>
					<td align="left">
						${object.unitcity}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位区
					</td>
					<td align="left">
						${object.unitdistrict}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						单位街道
					</td>
					<td align="left">
						${object.unitstreet}
				</tr>	

				<tr>
					<td class="addTd">
						单位地址
					</td>
					<td align="left">
						${object.unitaddress}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭邮编
					</td>
					<td align="left">
						${object.homezip}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭省
					</td>
					<td align="left">
						<c:out value="${object.homeprovince}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭城市
					</td>
					<td align="left">
						<c:out value="${object.homecity}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭区
					</td>
					<td align="left">
						<c:out value="${object.homedistrict}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭街道
					</td>
					<td align="left">
						<c:out value="${object.homestreet}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭地址
					</td>
					<td align="left">
						<c:out value="${object.homeaddress}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭2邮编
					</td>
					<td align="left">
						<c:out value="${object.home2zip}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭2省
					</td>
					<td align="left">
						<c:out value="${object.home2province}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭2市
					</td>
					<td align="left">
						<c:out value="${object.home2city}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭2区
					</td>
					<td align="left">
						<c:out value="${object.home2district}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭2街道
					</td>
					<td align="left">
						<c:out value="${object.home2street}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						家庭2地址
					</td>
					<td align="left">
						<c:out value="${object.home2address}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						常用地址
					</td>
					<td align="left">
						<c:out value="${object.inuseaddress}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left">
						<c:out value="${object.memo}" />
					</td>
				</tr>	

</table>



</body>
</html>
