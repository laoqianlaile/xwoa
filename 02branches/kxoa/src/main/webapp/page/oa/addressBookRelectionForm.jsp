<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>



<div class="pageContent">
	<s:form action="/oa/addressBookRelection!save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">

		<div class="pageFormContent" layoutH="56">
		
			
			<p>
				<label><c:out value="addressBookRelection.abno" />�?/label>
				 
				 
				<input name="abno" type="text" class="required" <c:if test="${!empty object.abno }">readonly="readonly"</c:if> size="40" value="${object.abno }" />
				
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
				<label><c:out value="addressBookRelection.shareman" />�?/label>
				 
				 
				<input name="shareman" type="text" class="required" <c:if test="${!empty object.shareman }">readonly="readonly"</c:if> size="40" value="${object.shareman }" />
				
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
