<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>



<div class="pageContent">
	<s:form action="/dispatchdoc/VSubprocessProjectTaskList!save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">

		<div class="pageFormContent" layoutH="56">
		
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.taskId" />：</label>
				 
				 
				<input name="taskId" type="text" class="required" <c:if test="${!empty object.taskId }">readonly="readonly"</c:if> size="40" value="${object.taskId }" />
				
			</p>

			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.nodeInstId" />：</label>
				 
				 
				<input name="nodeInstId" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.nodeInstId }">readonly="readonly"</c:if> size="40" value="${object.nodeInstId }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.unitCode" />：</label>
				 
				 
				<input name="unitCode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.unitCode }">readonly="readonly"</c:if> size="40" value="${object.unitCode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.userCode" />：</label>
				 
				 
				<input name="userCode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.userCode }">readonly="readonly"</c:if> size="40" value="${object.userCode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.roleType" />：</label>
				 
				 
				<input name="roleType" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.roleType }">readonly="readonly"</c:if> size="40" value="${object.roleType }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.roleCode" />：</label>
				 
				 
				<input name="roleCode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.roleCode }">readonly="readonly"</c:if> size="40" value="${object.roleCode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optId" />：</label>
				 
				 
				<input name="optId" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optId }">readonly="readonly"</c:if> size="40" value="${object.optId }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.flowInstId" />：</label>
				 
				 
				<input name="flowInstId" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.flowInstId }">readonly="readonly"</c:if> size="40" value="${object.flowInstId }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.flowOptName" />：</label>
				 
				 
				<input name="flowOptName" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.flowOptName }">readonly="readonly"</c:if> size="40" value="${object.flowOptName }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.flowOptTag" />：</label>
				 
				 
				<input name="flowOptTag" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.flowOptTag }">readonly="readonly"</c:if> size="40" value="${object.flowOptTag }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.authDesc" />：</label>
				 
				 
				<input name="authDesc" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.authDesc }">readonly="readonly"</c:if> size="40" value="${object.authDesc }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.nodeName" />：</label>
				 
				 
				<input name="nodeName" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.nodeName }">readonly="readonly"</c:if> size="40" value="${object.nodeName }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.nodeType" />：</label>
				 
				 
				<input name="nodeType" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.nodeType }">readonly="readonly"</c:if> size="40" value="${object.nodeType }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.nodeOptType" />：</label>
				 
				 
				<input name="nodeOptType" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.nodeOptType }">readonly="readonly"</c:if> size="40" value="${object.nodeOptType }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optName" />：</label>
				 
				 
				<input name="optName" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optName }">readonly="readonly"</c:if> size="40" value="${object.optName }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.methodName" />：</label>
				 
				 
				<input name="methodName" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.methodName }">readonly="readonly"</c:if> size="40" value="${object.methodName }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optUrl" />：</label>
				 
				 
				<input name="optUrl" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optUrl }">readonly="readonly"</c:if> size="40" value="${object.optUrl }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optMethod" />：</label>
				 
				 
				<input name="optMethod" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optMethod }">readonly="readonly"</c:if> size="40" value="${object.optMethod }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optDesc" />：</label>
				 
				 
				<input name="optDesc" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optDesc }">readonly="readonly"</c:if> size="40" value="${object.optDesc }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optCode" />：</label>
				 
				 
				<input name="optCode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optCode }">readonly="readonly"</c:if> size="40" value="${object.optCode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.optParam" />：</label>
				 
				 
				<input name="optParam" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.optParam }">readonly="readonly"</c:if> size="40" value="${object.optParam }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.inststate" />：</label>
				 
				 
				<input name="inststate" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.inststate }">readonly="readonly"</c:if> size="40" value="${object.inststate }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.nodeCreateTime" />：</label>
				 
				 
				<input name="nodeCreateTime" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.nodeCreateTime }">readonly="readonly"</c:if> size="40" value="${object.nodeCreateTime }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.expireOptSign" />：</label>
				 
				 
				<input name="expireOptSign" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.expireOptSign }">readonly="readonly"</c:if> size="40" value="${object.expireOptSign }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.expireOpt" />：</label>
				 
				 
				<input name="expireOpt" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.expireOpt }">readonly="readonly"</c:if> size="40" value="${object.expireOpt }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.grantor" />：</label>
				 
				 
				<input name="grantor" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.grantor }">readonly="readonly"</c:if> size="40" value="${object.grantor }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.timeLimit" />：</label>
				 
				 
				<input name="timeLimit" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.timeLimit }">readonly="readonly"</c:if> size="40" value="${object.timeLimit }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.lastUpdateUser" />：</label>
				 
				 
				<input name="lastUpdateUser" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.lastUpdateUser }">readonly="readonly"</c:if> size="40" value="${object.lastUpdateUser }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.lastUpdateTime" />：</label>
				 
				 
				<input name="lastUpdateTime" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.lastUpdateTime }">readonly="readonly"</c:if> size="40" value="${object.lastUpdateTime }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.flowPhase" />：</label>
				 
				 
				<input name="flowPhase" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.flowPhase }">readonly="readonly"</c:if> size="40" value="${object.flowPhase }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.djId" />：</label>
				 
				 
				<input name="djId" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.djId }">readonly="readonly"</c:if> size="40" value="${object.djId }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.transaffairname" />：</label>
				 
				 
				<input name="transaffairname" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.transaffairname }">readonly="readonly"</c:if> size="40" value="${object.transaffairname }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.bizstate" />：</label>
				 
				 
				<input name="bizstate" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.bizstate }">readonly="readonly"</c:if> size="40" value="${object.bizstate }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.bizstate" />：</label>
				 
				 
				<input name="bizstate" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.bizstate }">readonly="readonly"</c:if> size="40" value="${object.bizstate }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.orgcode" />：</label>
				 
				 
				<input name="orgcode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.orgcode }">readonly="readonly"</c:if> size="40" value="${object.orgcode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.orgname" />：</label>
				 
				 
				<input name="orgname" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.orgname }">readonly="readonly"</c:if> size="40" value="${object.orgname }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.headunitcode" />：</label>
				 
				 
				<input name="headunitcode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.headunitcode }">readonly="readonly"</c:if> size="40" value="${object.headunitcode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.headusercode" />：</label>
				 
				 
				<input name="headusercode" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.headusercode }">readonly="readonly"</c:if> size="40" value="${object.headusercode }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.content" />：</label>
				 
				 
				<input name="content" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.content }">readonly="readonly"</c:if> size="40" value="${object.content }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.powerid" />：</label>
				 
				 
				<input name="powerid" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.powerid }">readonly="readonly"</c:if> size="40" value="${object.powerid }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.powername" />：</label>
				 
				 
				<input name="powername" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.powername }">readonly="readonly"</c:if> size="40" value="${object.powername }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.solvestatus" />：</label>
				 
				 
				<input name="solvestatus" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.solvestatus }">readonly="readonly"</c:if> size="40" value="${object.solvestatus }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.solvetime" />：</label>
				 
				 
				<input name="solvetime" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.solvetime }">readonly="readonly"</c:if> size="40" value="${object.solvetime }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.solvenote" />：</label>
				 
				 
				<input name="solvenote" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.solvenote }">readonly="readonly"</c:if> size="40" value="${object.solvenote }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.createuser" />：</label>
				 
				 
				<input name="createuser" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.createuser }">readonly="readonly"</c:if> size="40" value="${object.createuser }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.createdate" />：</label>
				 
				 
				<input name="createdate" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.createdate }">readonly="readonly"</c:if> size="40" value="${object.createdate }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.riskType" />：</label>
				 
				 
				<input name="riskType" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.riskType }">readonly="readonly"</c:if> size="40" value="${object.riskType }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.sendArchiveNo" />：</label>
				 
				 
				<input name="sendArchiveNo" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.sendArchiveNo }">readonly="readonly"</c:if> size="40" value="${object.sendArchiveNo }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.acceptArchiveNo" />：</label>
				 
				 
				<input name="acceptArchiveNo" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.acceptArchiveNo }">readonly="readonly"</c:if> size="40" value="${object.acceptArchiveNo }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.riskDesc" />：</label>
				 
				 
				<input name="riskDesc" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.riskDesc }">readonly="readonly"</c:if> size="40" value="${object.riskDesc }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.riskResult" />：</label>
				 
				 
				<input name="riskResult" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.riskResult }">readonly="readonly"</c:if> size="40" value="${object.riskResult }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.transAffairNo" />：</label>
				 
				 
				<input name="transAffairNo" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.transAffairNo }">readonly="readonly"</c:if> size="40" value="${object.transAffairNo }"/>
				
			</p>
			
			<p>
				<label><c:out value="VSubprocessProjectTaskList.transAffairQueryKey" />：</label>
				 
				 
				<input name="transAffairQueryKey" type="text" <c:if test="${!empty VSubprocessProjectTaskListForm.map.transAffairQueryKey }">readonly="readonly"</c:if> size="40" value="${object.transAffairQueryKey }"/>
				
			</p>
			
			
			
			
		</div>


		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>

	</s:form>
</div>
