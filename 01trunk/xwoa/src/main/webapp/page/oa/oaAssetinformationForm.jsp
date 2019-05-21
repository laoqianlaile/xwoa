<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformation.edit.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">编辑资产信息</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="oaAssetinformation" method="post" namespace="/oa"
			id="oaAssetinformationForm">
			<input type="hidden" name="datacode" value="${datacodeType}" />
            <input type="hidden" name="s_supEquipmentType" value="resourse"/>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">资产名称</td>
					<td align="left" colspan="5">${cp:MAPVALUE('equipment',
						datacodeType) }</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaAssetinformation.assetnum" />
					</td>
					<td align="left"><s:textfield name="assetnum" size="40" /></td>
					<td class="addTd"><s:text name="oaAssetinformation.assetunit" />
					</td>
					<td align="left"><s:textfield name="assetunit" size="40" /></td>
				</tr>

				<%-- <tr>
					<td class="addTd"><s:text name="oaAssetinformation.creater" />
					</td>
					<td align="left"><s:textfield name="creater" size="40" /></td>
					<td class="addTd"><s:text name="oaAssetinformation.createtime" />
					</td>
					<td align="left"><s:textfield name="createtime" size="40" /></td>
				</tr> --%>

				<tr>
					<td class="addTd"><s:text
							name="oaAssetinformation.assetleftalert" /></td>
					<td align="left"><s:textfield name="assetleftalert" size="40" />

					</td>
					<td class="addTd"><s:text name="oaAssetinformation.state" /></td>
					<td align="left">
					<select id="state" style="width:200px;height:25px;" name="state">
					        <c:forEach var="row" items="${cp:DICTIONARY('zcstate') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==state}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<%-- <td class="addTd"><s:text
							name="oaAssetinformation.createDepno" /></td>
					<td align="left"><s:textfield name="createDepno" size="40" /></td>--%>
				</tr> 


				<tr>
					<td class="addTd"><s:text
							name="oaAssetinformation.createRemark" /></td>
					<td align="left" colspan="5"><s:textarea name="createRemark"
							style="width:100%" /></td>
				</tr>

			</table>
			<div class="formButton">
				<input type="button" class="btn" id="save" value="保存"
					onclick="submitItemFrame('SAVE');" />

				<!-- <button type="submit" class="btn btn-primary">保存</button> -->
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
			</div>
		</s:form>
	</fieldset>

	<script type="text/javascript">
		function submitItemFrame(subType) {
			var srForm = document.getElementById("oaAssetinformationForm");
			if (subType == 'SAVE') {
				srForm.action = 'oaAssetinformation!save.do';
			}
			srForm.submit();
		}
		var t_oaAssetinformationOutlogRowCount; // 行数

		var t_oaAssetinformationInlogRowCount; // 行数

		$(function() {

			t_oaAssetinformationOutlogRowCount = $("table#t_oaAssetinformationOutlog tr").length - 1; // 除去标题�?  
			var oaAssetinformationOutlogColName = [ "djid", "applyuser",
					"applyUnitcode", "applydate", "assetnum", "assetunit",
					"creater", "createtime", "createRemark", "guard" ];

			t_oaAssetinformationInlogRowCount = $("table#t_oaAssetinformationInlog tr").length - 1; // 除去标题�?  
			var oaAssetinformationInlogColName = [ "djid", "assetnum",
					"assetunit", "creater", "createtime", "createRemark",
					"guard" ];

			$("input[name='method:save']")
					.bind(
							"click",
							function() {
								$("table#t_oaAssetinformationOutlog tr")
										.each(
												function(i) {
													$(this).attr(
															"id",
															"tr_oaAssetinformationOutlog"
																	+ i);
													$(
															"#tr_oaAssetinformationOutlog"
																	+ i
																	+ "  input[type='text']")
															.each(
																	function(j) {
																		$(this)
																				.attr(
																						"name",
																						"newOaAssetinformationOutlogs["
																								+ (i - 1)
																								+ "]."
																								+ oaAssetinformationOutlogColName[j]);
																	});
												});
								$("table#t_oaAssetinformationInlog tr")
										.each(
												function(i) {
													$(this).attr(
															"id",
															"tr_oaAssetinformationInlog"
																	+ i);
													$(
															"#tr_oaAssetinformationInlog"
																	+ i
																	+ "  input[type='text']")
															.each(
																	function(j) {
																		$(this)
																				.attr(
																						"name",
																						"newOaAssetinformationInlogs["
																								+ (i - 1)
																								+ "]."
																								+ oaAssetinformationInlogColName[j]);
																	});
												});

							});
		});

		function addOaAssetinformationOutlogItem() {
			var htmlItem = '<tr>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].djid" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].applyuser" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].applyUnitcode" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].applydate" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].assetnum" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].assetunit" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].creater" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].createtime" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationOutlogs['+t_oaAssetinformationOutlogRowCount+'].createRemark" /></td>';

			t_oaAssetinformationOutlogRowCount++;
			htmlItem += "<td> <a href='javascript:void(0)' onclick='delOaAssetinformationOutlogItem(this);'><s:text name='opt.btn.delete' /></a></td></tr>";
			$("table#t_oaAssetinformationOutlog").append(htmlItem);

			$('table#t_oaAssetinformationOutlog.tableRegion tr:odd').attr(
					'class', 'odd').hover(function() {
				$(this).addClass("highlight");
			}, function() {
				$(this).removeClass("highlight");
			});
			$('table#t_oaAssetinformationOutlog.tableRegion tr:even').attr(
					'class', 'even').hover(function() {
				$(this).addClass("highlight");
			}, function() {
				$(this).removeClass("highlight");
			});
		}

		function delOaAssetinformationOutlogItem(varBtn) {
			$(varBtn).parent().parent().remove();
			t_oaAssetinformationOutlogRowCount--;
			$('table#t_oaAssetinformationOutlog.tableRegion tr:odd').attr(
					'class', 'odd');
			$('table#t_oaAssetinformationOutlog.tableRegion tr:even').attr(
					'class', 'even');
		}
		function addOaAssetinformationInlogItem() {
			var htmlItem = '<tr>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationInlogs['+t_oaAssetinformationInlogRowCount+'].djid" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationInlogs['+t_oaAssetinformationInlogRowCount+'].assetnum" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationInlogs['+t_oaAssetinformationInlogRowCount+'].assetunit" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationInlogs['+t_oaAssetinformationInlogRowCount+'].creater" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationInlogs['+t_oaAssetinformationInlogRowCount+'].createtime" /></td>';

			htmlItem += '<td><input type="text" name="newOaAssetinformationInlogs['+t_oaAssetinformationInlogRowCount+'].createRemark" /></td>';

			t_oaAssetinformationInlogRowCount++;
			htmlItem += "<td> <a href='javascript:void(0)' onclick='delOaAssetinformationInlogItem(this);'><s:text name='opt.btn.delete' /></a></td></tr>";
			$("table#t_oaAssetinformationInlog").append(htmlItem);

			$('table#t_oaAssetinformationInlog.tableRegion tr:odd').attr(
					'class', 'odd').hover(function() {
				$(this).addClass("highlight");
			}, function() {
				$(this).removeClass("highlight");
			});
			$('table#t_oaAssetinformationInlog.tableRegion tr:even').attr(
					'class', 'even').hover(function() {
				$(this).addClass("highlight");
			}, function() {
				$(this).removeClass("highlight");
			});
		}

		function delOaAssetinformationInlogItem(varBtn) {
			$(varBtn).parent().parent().remove();
			t_oaAssetinformationInlogRowCount--;
			$('table#t_oaAssetinformationInlog.tableRegion tr:odd').attr(
					'class', 'odd');
			$('table#t_oaAssetinformationInlog.tableRegion tr:even').attr(
					'class', 'even');
		}
	</script>


</body>
</html>
