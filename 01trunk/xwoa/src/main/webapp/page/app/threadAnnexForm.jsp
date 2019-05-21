<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>



<div class="pageContent">
	<s:form action="/app/threadAnnex!save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">

		<div class="pageFormContent" layoutH="56">
		
			
			<p>
				<label><c:out value="threadAnnex.threadid" />：</label>
				 
				 
				<input name="threadid" type="text" class="required" <c:if test="${!empty object.threadid }">readonly="readonly"</c:if> size="40" value="${object.threadid }" />
				
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
				<label><c:out value="threadAnnex.filecode" />：</label>
				 
				 
				<input name="filecode" type="text" class="required" <c:if test="${!empty object.filecode }">readonly="readonly"</c:if> size="40" value="${object.filecode }" />
				
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
