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
	
	<p class="ctitle">编辑个人待办事项</p>
	
	
	<form class="form-horizontal" id="taskListForm" action="${pageContext.request.contextPath}/app/taskList!save.do" method="post" validate="true">
		<table width="200" border="0" cellpadding="1" cellspacing="1">
			<input type="hidden" name="taskid" value="${object.taskid }"/>
			<input type="hidden" name="instance" value="${param['instance'] }"/>
			
			<tr>
				<td class="TDTITLE">
					待办标题:
				</td>
				<td align="left">
					<input type="text" name="tasktitle" class="focused required" value="${object.tasktitle }" />
				</td>
				<td class="TDTITLE">
					任务标记:
				</td>
				<td align="left">
					<select id="tasktag" name="tasktag">
						<c:forEach var="task" items="${cp:DICTIONARY('TASKTAG') }">
							<option value="${task.datacode }" <c:if test="${task.datacode eq object.tasktag }">selected="selected"</c:if> >${task.datavalue }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr id="tr_tasktype">
				<td class="TDTITLE">
					任务等级:
				</td>
				<td align="left">
					<select id="taskrank" name="taskrank">
						<c:forEach var="task" items="${cp:DICTIONARY('TASKRANK') }">
							<option value="${task.datacode }" <c:if test="${task.datacode eq object.taskrank }">selected="selected"</c:if> >${task.datavalue }</option>
						</c:forEach>
					</select>
				</td>
				<td class="TDTITLE">
					任务类型:
				</td>
				<td align="left">
					<select id="tasktype" name="tasktype">
						<c:forEach var="task" items="${cp:DICTIONARY('TASKTYPE') }">
							<option value="${task.datacode }" <c:if test="${task.datacode eq object.tasktype }">selected="selected"</c:if> >${task.datavalue }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="TDTITLE">
					任务状态:
				</td>
				<td align="left">
					<select id="taskstatus" name="taskstatus">
						<c:forEach var="task" items="${cp:DICTIONARY('TASKSTATUS') }">
							<option value="${task.datacode }" <c:if test="${task.datacode eq object.taskstatus }">selected="selected"</c:if> >${task.datavalue }</option>
						</c:forEach>
					</select>
				</td>
				<td class="TDTITLE">
					任务预期时间:
				</td>
				<td align="left">
					<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext'})" class="span2 Wdate" name="planbegintime" value='<fmt:formatDate value="${object.planbegintime }" pattern="yyyy-MM-dd"/>'/>
					<c:if test="${not isallday }">
						<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext'})" class="span2 Wdate" name="startt" value='<fmt:formatDate value="${object.planbegintime }" pattern="HH:mm:ss"/>'/>
					</c:if>
					<span class="help-inline" style="padding-right:5px">到</span>
						<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext'})" class="span2 Wdate"  name="planendtime" value='<fmt:formatDate value="${object.planendtime }" pattern="yyyy-MM-dd"/>'/>
					<c:if test="${not isallday }">
						<input type="text" class="span2 Wdate" data-option='{"dateFmt" : "HH:mm:ss"}'  name="endt" value='<fmt:formatDate value="${object.planendtime }" pattern="HH:mm:ss"/>'/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="TDTITLE">
					任务实际时间:
				</td>
				<td align="left">
					<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext', dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wtime" name="begintime" value='<fmt:formatDate value="${object.begintime }" pattern="yyyy-MM-dd"/>'/>
					到
					<input type="text" onclick="WdatePicker({readOnly:true, skin:'ext', dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wtime" name="endtime" value='<fmt:formatDate value="${object.endtime }" pattern="yyyy-MM-dd"/>'/>
				</td>
				<td class="TDTITLE">
					全天活动:
				</td>
				<td align="left">
					<label class="radio"><input type="radio" name="isallday" value="1" <c:if test="${isallday }">checked="checked"</c:if> > 是</label>
					<label class="radio"><input type="radio" name="isallday" value="0" <c:if test="${not isallday }">checked="checked"</c:if> > 否</label>
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
				<td class="TDTITLE">
					已保存附件:
				</td>
				<td align="left">
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
			<tr>
				<td class="TDTITLE">
					完成记录:
				</td>
				<td align="left">
					<textarea id="catalogdesc" name="finishmemo" class="span6 {rangelength:[4,1000] }" rows="5" cols="50">${object.finishmemo }</textarea>
				</td>
			</tr>
			<tr>
				<td class="TDTITLE">
					任务描述:
				</td>
				<td align="left">
					<textarea id="catalogdesc" name="taskmemo" class="span6 {rangelength:[4,1000] }" rows="5" cols="50">${object.taskmemo }</textarea>
				</td>
			</tr>
			<tr>
				<td height="40"></td>
				<td>
					<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
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
					<select id="sel_owner"></select>\
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
	});
	
	var D = function(obj) {console.debug(obj);};
	
	
</script>
</body>

</html>