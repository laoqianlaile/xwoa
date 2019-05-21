<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>请设定这个页面的标题</title>
		
	</head>

	<body>
		<p class="ctitle">
			编辑通讯录
		</p>
		<s:form action="addressBook.do" namespace="/sys" >
			<s:submit method="save" cssClass="btn" value="保存" />
			<input type="button" value="返回" class="btn"
				onclick="window.history.back()"  />

			<table width="200" border="0" cellpadding="1" cellspacing="1">

				<tr>
					<td class="TDTITLE">
						通讯录ID(不用填写)
					</td>
					<td align="left">
					<c:if test="${ not empty object.addrbookid}">
						<s:textarea name=".addrbookid" rows="1"
							readonly="true"
							cols="40" />
							</c:if>
							
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<c:out value="bodytype" />
					</td>
					<td align="left">
						<s:textarea name="bodytype" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<c:out value="bodycode" />
					</td>
					<td align="left">
						<s:textarea name="bodycode" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						描述
					</td>
					<td align="left">
						<s:textarea name="representation" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位名
					</td>
					<td align="left">
						<s:textarea name="unitname" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						部门名
					</td>
					<td align="left">
						<s:textarea name="deptname" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						头衔名
					</td>
					<td align="left">
						<s:textarea name="rankname" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						电子邮件
					</td>
					<td align="left">
						<s:textarea name="email" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						电子邮件2
					</td>
					<td align="left">
						<s:textarea name="email2" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						电子邮件3
					</td>
					<td align="left">
						<s:textarea name="email3" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						主页
					</td>
					<td align="left">
						<s:textarea name="homepage" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						QQ
					</td>
					<td align="left">
						<s:textarea name="qq" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						MSN
					</td>
					<td align="left">
						<s:textarea name="msn" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						旺旺
					</td>
					<td align="left">
						<s:textarea name="wangwang" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						商务电话
					</td>
					<td align="left">
						<s:textarea name="buzphone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						商务电话2
					</td>
					<td align="left">
						<s:textarea name="buzphone2" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						商务传真
					</td>
					<td align="left">
						<s:textarea name="buzfax" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						助理电话
					</td>
					<td align="left">
						<s:textarea name="assiphone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						回复电话
					</td>
					<td align="left">
						<s:textarea name="callbacphone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						车载电话
					</td>
					<td align="left">
						<s:textarea name="carphone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位电话
					</td>
					<td align="left">
						<s:textarea name="unitphone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						家庭电话
					</td>
					<td align="left">
						<s:textarea name="homephone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						家庭电话2
					</td>
					<td align="left">
						<s:textarea name="homephone2" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						家庭电话3
					</td>
					<td align="left">
						<s:textarea name="homephone3" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						家庭传真
					</td>
					<td align="left">
						<s:textarea name="homefax" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						手机
					</td>
					<td align="left">
						<s:textarea name="mobilephone" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						手机2
					</td>
					<td align="left">
						<s:textarea name="mobilephone2" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						手机3
					</td>
					<td align="left">
						<s:textarea name="mobilephone3" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位邮编
					</td>
					<td align="left">
						<s:textarea name="unitzip" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位省
					</td>
					<td align="left">
						<s:textarea name="unitprovince" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位城市
					</td>
					<td align="left">
						<s:textarea name="unitcity" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位区
					</td>
					<td align="left">
						<s:textarea name="unitdistrict" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位街道
					</td>
					<td align="left">
						<s:textarea name="unitstreet" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						单位地址
					</td>
					<td align="left">
						<s:textarea name="unitaddress" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅邮编
					</td>
					<td align="left">
						<s:textarea name="homezip" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅省
					</td>
					<td align="left">
						<s:textarea name="homeprovince" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅城市
					</td>
					<td align="left">
						<s:textarea name="homecity" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅区
					</td>
					<td align="left">
						<s:textarea name="homedistrict" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅街道
					</td>
					<td align="left">
						<s:textarea name="homestreet" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅地址
					</td>
					<td align="left">
						<s:textarea name="homeaddress" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅2邮编
					</td>
					<td align="left">
						<s:textarea name="home2zip" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅2省
					</td>
					<td align="left">
						<s:textarea name="home2province" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅2城市
					</td>
					<td align="left">
						<s:textarea name="home2city" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅2区
					</td>
					<td align="left">
						<s:textarea name="home2district" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅2街道
					</td>
					<td align="left">
						<s:textarea name="home2street" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						住宅2地址
					</td>
					<td align="left">
						<s:textarea name="home2address" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						使用中的地址
					</td>
					<td align="left">
						<s:textarea name="inuseaddress" rows="1" cols="40" />
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						备注
					</td>
					<td align="left">
						<s:textarea name="memo" rows="1" cols="40" />
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
