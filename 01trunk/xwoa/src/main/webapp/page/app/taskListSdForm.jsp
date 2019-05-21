<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>

<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 


<title>用户待办事项</title>
</head>
<body>
	
	<c:choose>
		<c:when test="${isallday }"><c:set var="isalldayClass" value="Wdate"/> </c:when>
		<c:otherwise><c:set var="isalldayClass" value="Wtime"/> </c:otherwise>
	</c:choose>
	
	<p class="ctitle">日程安排编辑</p>
	
	
	<form class="form-horizontal" id="taskListForm" action="${pageContext.request.contextPath}/app/taskList!save.do" method="post" validate="true">
		<table width="200" border="0" cellpadding="1" cellspacing="1">
			<input type="hidden" name="taskid" value="${object.taskid }"/>
			<input type="hidden" name="instance" value="${param['instance'] }"/>
			<input type="hidden" name="tasktype" value="${param['tasktype'] }"/>
			<input type="hidden" name="day" value="${param['day'] }"/>
			
			<tr>
				<td class="TDTITLE">
					标题:
				</td>
				<td align="left">
					<input type="text" name="tasktitle" class="focused required" value="${object.tasktitle }" size="49" />
				</td>
				
			</tr>
			<%-- <tr id="tr_tasktype">
				<td class="TDTITLE">
					日程类型:
				</td>
				<td align="left">
					
						<c:forEach var="task" items="${cp:DICTIONARY('TASKTYPE') }">
							<c:if test="${task.datacode eq param.tasktype }">
								${task.datavalue }
							</c:if>
						</c:forEach>					
				</td>
			</tr> --%>
			<tr>
				<td class="TDTITLE">
					日程时间:
				</td>
				<td align="left">
					<c:choose>
						<c:when test="${not empty object.planbegintime }">
							<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext', dateFmt : 'yyyy-MM-dd HH:mm:ss'})" class="span2 Wdate" name="planbegintime" value='<fmt:formatDate value="${object.planbegintime }" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
						</c:when>
						<c:otherwise>
							<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext', dateFmt : 'yyyy-MM-dd HH:mm:ss'})" class="span2 Wdate" name="planbegintime" value='${param.start }'/>
						</c:otherwise>
					</c:choose>
				
					<span class="help-inline" style="padding-right:5px">到</span>
					<c:choose>
						<c:when test="${not empty object.planendtime }">
							<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext', dateFmt : 'yyyy-MM-dd HH:mm:ss'})" class="span2 Wdate" name="planendtime" value='<fmt:formatDate value="${object.planendtime }" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
						</c:when>
						<c:otherwise>
							<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext', dateFmt : 'yyyy-MM-dd HH:mm:ss'})" class="span2 Wdate" name="planendtime" value='${param.end }'/>
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr>
				<td class="TDTITLE">
					附件上传:
				</td>
				<td align="left">
					<input type="file" id="upload-fileinfo" optID="TASK" inputID="upload-data"/>
					<input type="hidden" id="upload-data" name="filecodes" />
				</td>
			</tr>
			<c:if test="${not empty object.taskListAnnexs }">
				<tr>
					<td class="TDTITLE">
						已保存附件:
					</td>
					<td id="td_annex" align="left">
						<c:forEach var="annex" items="${object.taskListAnnexs }">
	                        <div class="div_projecttasks_annexs" filecode="${annex.fileinfoFs.filecode }">
	                                ${annex.fileinfoFs.filename } <fmt:formatNumber
	                                value="${annex.fileinfoFs.filesize / 1024 / 1024 }"
	                                type="number" pattern="#.##"/> MB
	                            <a target="download" href="javasricpt:;"
	                               filecode="${annex.fileinfoFs.filecode }">下载</a> &nbsp;
	                            <a href="#" url="${contextPath }/app/taskList!deleteTasksAnnex.do?tlaid=${annex.tlaid }"
	                               class="a_projecttasks_annexs" title="是否删除附件">删除</a>
	                        </div>
	                    </c:forEach>
					</td>
				</tr>
			</c:if>
			</tr>
			<tr>
				<td class="TDTITLE">
					日程内容:
				</td>
				<td align="left">
					<textarea id="catalogdesc" name="taskmemo" class="span6 {rangelength:[4,1000] }" rows="5" cols="50">${object.taskmemo }</textarea>
				</td>
			</tr>
			<tr>
				<td height="40"></td>
				<td>
					<c:if test="${'1' ne param.view }">
						<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
					</c:if>
					<input type="button" class="btn" value="返回" onClick="window.history.go(-1);"/>
				</td>
			</tr>
		</table>
	</form>
	

<script>
	$(function(){
		$('#taskListForm :radio[name="isallday"]').change(function(){
			var $times = $('#taskListForm :text[name="startt"], :text[name="endt"]');
			if('1' == $(this).val()){
				$times.hide();
			}else if('0' == $(this).val()){
				$times.show();
			}
			
		});
		
		
		var selectowner = 
			'<tr id="tasklistowner">\
				<td class="TDTITLE">\
					选择人员:\
				</td>\
				<td align="left">\
					<select id="sel_owner" name="usercode"></select>\
				</td>\
			</tr>';
			
		var getResult = function(jsonResult){
			if($.isEmptyObject(jsonResult)) {
				$('#tasklistowner').remove();
				
				return;
			}
			
			var options = [];
			
			for(var o in jsonResult) {
				options.push('<option value="'+o+'">'+jsonResult[o]+'</option>');
			}
			
			$('#sel_owner').append(options.join(''));
			
			$("#sel_owner option[value='${object.taskowner}']").attr("selected", "selected");
		};
		
		var tasktypechange = function(){
			var val = $(this).val();
			
			var $tasklistowner = $('#tasklistowner');
			if (0 != $tasklistowner.length) {
				$tasklistowner.remove();
			} 
			
			$('#tr_tasktype').after(selectowner);
			
			$.post('${pageContext.request.contextPath}/app/calendar!getOwner.do', {
				instance : '${instance}',
				tasktype : val
			}, getResult, 'json');
			
		};
		
		
		$('#tasktype').change(tasktypechange);
		
		if ($.fn.uploadify) {
			$("input[type=file]").each(function() {
				$.myUpload.init.call(this);
			});
		}
		
		
		$('a.a_projecttasks_annexs').click(function(){
			var $this = $(this);
			if(confirm($this.attr('title'))) {
				$.post($this.attr('url'), {}, function(){
					$this.parent('.div_projecttasks_annexs').remove();
				});
			}
		});
		
		$('#td_annex a[target="download"]').click(function(e) {
			var alink = $(this);
			var filecode = alink.attr('filecode');
			var container = $('body');

			// 下载回调函数
			var downloadFilesCallback = function(data) {
				if (!data || '0' != data.result) {
					msgError(data.description);
					return false;
				}

				var form = $('#downloadForm', container);

				if (!form[0]) {
					form = $("<form>"); // 定义一个form表单
					form.attr('id', 'downloadForm');
					form.attr('style', 'display:none'); // 在form表单中添加查询参数
					form.attr('target', '');
					form.attr('method', 'post');
					form.attr('action', '${pageContext.request.contextPath}/app/fileinfoFs!downloadfile.do');

					var input = $('<input>');
					input.attr('type', 'hidden');
					input.attr('name', 'filecode');

					container.append(form); // 将表单放置在web中
					form.append(input); // 将查询参数控件提交到表单上
				}

				form.find('input[name=filecode]').val(data.filecode);
				form.submit();
			};

			if (filecode) {
				$.post('${pageContext.request.contextPath}/app/fileinfoFs!toDownloadfile.do',
						{
							filecode : filecode
						}, downloadFilesCallback, 'json');
			} else {
				$.post(alink.attr('href'), downloadFilesCallback, 'json');
			}
		});
		
		chooseTaskType();
		
	});
	
	
	var chooseTaskType = function(){
		var tasktype = '${param.tasktype}';
		if('P' == tasktype) {
			$("#tasktype option[value='Personal']").attr('selected', 'true');
			$("#tasktype").attr('disabled', 'disabled');
		} else if('L' == tasktype) {
			$("#tasktype option[value='Leadership']").attr('selected', 'true');
			$("#tasktype").change();
			$("#tasktype").attr('disabled', 'disabled');
		} else if('O' == tasktype) {
			$("#tasktype option[value='Offices']").attr('selected', 'true');
			$("#tasktype").change();
			$("#tasktype").attr('disabled', 'disabled');
		}
		
	};
	
	var D = function(obj) {console.debug(obj);};
	
	
</script>
</body>

</html>