<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>



<div class="pageContent">
	<s:form action="/app/forumStaff!save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">

		<div class="pageFormContent" layoutH="56">
		
			
			<p>
				<label><c:out value="forumStaff.forumid" />：</label>
				 
				 
				<input name="forumid" type="text" class="required" <c:if test="${!empty object.forumid }">readonly="readonly"</c:if> size="40" value="${object.forumid }" />
				
			</p>

			
			<p>
				<label><c:out value="forumStaff.userrole" />：</label>
				 
				 
				<input name="userrole" type="text" <c:if test="${!empty forumStaffForm.map.userrole }">readonly="readonly"</c:if> size="40" value="${object.userrole }"/>
				
			</p>
			
			<p>
				<label><c:out value="forumStaff.jointime" />：</label>
				 
				 
				<input name="jointime" type="text" <c:if test="${!empty forumStaffForm.map.jointime }">readonly="readonly"</c:if> size="40" value="${object.jointime }"/>
				
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

	<%-- </s:form> --%>
</div>

			<p>
				<label><c:out value="forumStaff.usercode" />：</label>
				 
				 
				<input name="usercode" type="text" class="required" <c:if test="${!empty object.usercode }">readonly="readonly"</c:if> size="40" value="${object.usercode }" />
				
			</p>

			
			<p>
				<label><c:out value="forumStaff.userrole" />：</label>
				 
				 
				<input name="userrole" type="text" <c:if test="${!empty forumStaffForm.map.userrole }">readonly="readonly"</c:if> size="40" value="${object.userrole }"/>
				
			</p>
			
			<p>
				<label><c:out value="forumStaff.jointime" />：</label>
				 
				 
				<input name="jointime" type="text" <c:if test="${!empty forumStaffForm.map.jointime }">readonly="readonly"</c:if> size="40" value="${object.jointime }"/>
				
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
