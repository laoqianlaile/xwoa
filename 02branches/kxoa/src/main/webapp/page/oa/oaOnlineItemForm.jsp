<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItem.edit.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaOnlineItem.edit.title" />
			</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaOnlineItem" method="post" namespace="/oa"
			id="oaOnlineItemForm" onsubmit="return checkForm();">

			<input type="hidden" id="s_djid" value="${s_djid }" name="s_djid">
			<input type="hidden" id="no" value="${no }" name="no">
			<input type="hidden" id="djid" value="${djid }" name="djid">
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<%-- <tr>
					<td class="addTd">
						<s:text name="oaOnlineItem.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="addTd">
						<s:text name="oaOnlineItem.djid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="djid"  size="40"/>
	
					</td>
				</tr>
 --%>
				<tr>
					<td class="addTd"><s:text name="oaOnlineItem.chosetype" /></td>
					<td align="left"><select id="chosetype" name="chosetype"
						 id="chosetype" style="width: 150px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('OAChoseType')}">
								<option value="${row.datacode}"
									<c:if test="${chosetype eq row.datacode }">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaOnlineItem.thesign" /></td>
					<td align="left"><input type="checkbox" id=thesign
						name="thesign" value="T"
						<c:if test="${thesign=='T'}"> checked="checked" </c:if>>必答

					</td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaOnlineItem.title" /></td>
					<td align="left"><s:textarea name="title" cols="40" rows="2"
							style="width: 100%;height: 27px;" /></td>
				</tr>

				<%-- 	<tr>
					<td class="addTd">
						<s:text name="oaOnlineItem.itemnames" />
					</td>
					<td align="left" >
						<s:textarea name="itemnames" cols="40" rows="6"  style="width: 100%;" />
	                    <br>
	                 <span style="color: red">*每行代表一个选项(自动换行不算)</span> 
					</td>
				</tr> --%>

				<tr id="tr_chosenum">
					<td class="addTd">每行<s:text name="oaOnlineItem.chosenum" />
					</td>
					<td align="left"><s:textfield name="chosenum" size="40" /></td>
				</tr>

				<tr id="tr_limitnum">
					<td class="addTd"><s:text name="oaOnlineItem.limitnum" /></td>
					<td align="left"><s:textfield name="limitnum" size="40" /> <br>
						<span style="color: red">*0代表不控制 </span></td>
				</tr>

			</table>

			<div class="formButton">
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
				<s:submit name="save" method="save" cssClass="btn"
					key="opt.btn.save" />
				
			</div>

			<div class="eXtremeTable">
				<span class="addTd"> <s:text name="oaOnlineItem.itemnames" /></span>
				<table id="t_oaOnlineItems" border="0" cellspacing="0"
					cellpadding="0" class="tableRegion" width="100%">

					<thead>
						<tr>

							<!-- 				<td class="tableHeader"> -->
							<%-- 					<s:text name="oaOnlineItems.itemid" /> --%>
							<!-- 				</td>	 -->



							<td class="tableHeader"><s:text name="oaOnlineItems.title" />
							</td>

							<td class="tableHeader"><a href='javascript:void(0)'
								onclick='addOaOnlineItemsItem(this);'><s:text
										name="opt.btn.new" /></a></td>
						</tr>
					</thead>

					<tbody class="tableBody">
						<c:set value="odd" var="rownum" />
						<s:iterator value="oaOnlineItemss" status="status" var="items">
							<tr class="${rownum}" onmouseover="this.className='highlight'"
								onmouseout="this.className='${rownum}'">
								<input type="hidden" name="itemid" value="${items.itemid }" />
								<input type="hidden" name="no" value="${items.no }" />
								<td><s:textfield name="title"
										style="width: 80%;height: 27px;" /></td>

								<td><a href='javascript:void(0)'
									onclick='addOaOnlineItemsItem(this);'><s:text
											name="opt.btn.new" /></a> <a href='javascript:void(0)'
									onclick='delOaOnlineItemsItem(this);'><s:text
											name='opt.btn.delete' /></a></td>
							</tr>
							<c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
						</s:iterator>
					</tbody>
				</table>
			</div>

		</s:form>
	</fieldset>
	<script type="text/javascript">
		var t_oaOnlineItemsRowCount; // 行数

		$(function() {

			t_oaOnlineItemsRowCount = $("table#t_oaOnlineItems tr").length - 1; // 除去标题行   
			var oaOnlineItemsColName = [ "itemid", "no", "title", "guard" ];

			$("input[name='method:save']")
					.bind(
							"click",
							function() {
								$("table#t_oaOnlineItems tr")
										.each(
												function(i) {
													$(this).attr(
															"id",
															"tr_oaOnlineItems"
																	+ i);
													$(
															"#tr_oaOnlineItems"
																	+ i
																	+ "  input")
															.each(
																	function(j) {
																		$(this)
																				.attr(
																						"name",
																						"newOaOnlineItemss["
																								+ (i - 1)
																								+ "]."
																								+ oaOnlineItemsColName[j]);
																	});
												});

							});

			// 	        choseType();
		});

		//题目类型  OAChoseType  1:多选 2:单选 3:问答

		function choseType() {
			var v = $('#chosetype').find('option:selected').val();

			if (v == '' || v == '3') {
				$('#tr_chosenum').hide();
				$('#tr_limitnum').hide();
				$(".eXtremeTable").hide();
			}

			if (v == '1') {
				$('#tr_chosenum').show();//选项个数
				$('#tr_limitnum').show();//最多可选个数
				$(".eXtremeTable").show();

			}
			if (v == '2') {
				$('#tr_chosenum').show();
				$('#tr_limitnum').hide();
				$(".eXtremeTable").show();
			}

		}

		$('#chosetype').change(function() {
			choseType();
		});

		choseType();

		function checkForm() {

			if ($('#chosetype').val() == '') {
				alert("题目类型不可为空！");
				$('#chosetype').focus();
				return false;
			}
		}

		function addOaOnlineItemsItem() {
			var htmlItem = '<tr>';
			htmlItem += '<input type="hidden" name="newOaOnlineItemss['+t_oaOnlineItemsRowCount+'].itemid" />';
			htmlItem += '<input type="hidden" name="newOaOnlineItemss['+t_oaOnlineItemsRowCount+'].no" value="${no}" />';
			htmlItem += '<td><input type="text" name="newOaOnlineItemss['
					+ t_oaOnlineItemsRowCount
					+ '].title" style="width: 80%;height: 27px;" /></td>';

			t_oaOnlineItemsRowCount++;
			htmlItem += "<td><a href='javascript:void(0)' onclick='addOaOnlineItemsItem(this);'><s:text name="opt.btn.new" /></a>"
					+ "<a href='javascript:void(0)' onclick='delOaOnlineItemsItem(this);'><s:text name='opt.btn.delete' /></a> </td></tr>";
			$("table#t_oaOnlineItems").append(htmlItem);

			$('table#t_oaOnlineItems.tableRegion tr:odd').attr('class', 'odd')
					.hover(function() {
						$(this).addClass("highlight");
					}, function() {
						$(this).removeClass("highlight");
					});
			$('table#t_oaOnlineItems.tableRegion tr:even')
					.attr('class', 'even').hover(function() {
						$(this).addClass("highlight");
					}, function() {
						$(this).removeClass("highlight");
					});
		}

		function delOaOnlineItemsItem(varBtn) {
			$(varBtn).parent().parent().remove();
			t_oaOnlineItemsRowCount--;
			$('table#t_oaOnlineItems.tableRegion tr:odd').attr('class', 'odd');
			$('table#t_oaOnlineItems.tableRegion tr:even')
					.attr('class', 'even');
		}
	</script>


</body>
</html>
