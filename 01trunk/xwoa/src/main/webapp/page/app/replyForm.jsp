<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>



<div class="pageContent">
	<s:form action="/app/reply!save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">

		<div class="pageFormContent" layoutH="56">
		
			
			<p>
				<label><c:out value="reply.replyid" />：</label>
				 
				 
				<input name="replyid" type="text" class="required" <c:if test="${!empty object.replyid }">readonly="readonly"</c:if> size="40" value="${object.replyid }" />
				
			</p>

			
			<p>
				<label><c:out value="reply.threadid" />：</label>
				 
				 
				<input name="threadid" type="text" <c:if test="${!empty replyForm.map.threadid }">readonly="readonly"</c:if> size="40" value="${object.threadid }"/>
				
			</p>
			
			<p>
				<label><c:out value="reply.reply" />：</label>
				 
				 
				<input name="reply" type="text" <c:if test="${!empty replyForm.map.reply }">readonly="readonly"</c:if> size="40" value="${object.reply }"/>
				
			</p>
			
			<p>
				<label><c:out value="reply.replytime" />：</label>
				 
				 
				<input name="replytime" type="text" <c:if test="${!empty replyForm.map.replytime }">readonly="readonly"</c:if> size="40" value="${object.replytime }"/>
				
			</p>
			
			<p>
				<label><c:out value="reply.userid" />：</label>
				 
				 
				<input name="userid" type="text" <c:if test="${!empty replyForm.map.userid }">readonly="readonly"</c:if> size="40" value="${object.userid }"/>
				
			</p>
			
			<p>
				<label><c:out value="reply.username" />：</label>
				 
				 
				<input name="username" type="text" <c:if test="${!empty replyForm.map.username }">readonly="readonly"</c:if> size="40" value="${object.username }"/>
				
			</p>
			
			<p>
				<label><c:out value="reply.annexnum" />：</label>
				 
				 
				<input name="annexnum" type="text" <c:if test="${!empty replyForm.map.annexnum }">readonly="readonly"</c:if> size="40" value="${object.annexnum }"/>
				
			</p>
			
			
			
			
			<div class="divider"></div>
			<div>
				<table class="list nowrap itemDetail" addButton="新建从表1条目" width="100%">
					<thead>
						<tr>
						
						
							
								
							
								 
									<th type="text" name="replyAnnex.filecode" fieldClass="required"> <c:out value="replyAnnex.filecode" /> </th>
								
							
							
						
							<th type="del" width="60">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			
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

