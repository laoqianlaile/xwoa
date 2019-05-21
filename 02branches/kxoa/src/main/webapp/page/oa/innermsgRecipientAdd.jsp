<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>



<div class="pageContent">
	<s:form action="/oa/innermsg!saveMsg.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">

        <input type="hidden" name="receiveUserCode" value="${object.innermsg.sender }"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>主题：</dt>
				<dd>
					<input name="msgtitle" type="text" maxlength="128" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>收件人：</dt>
				<dd>
				    <label>${cp:MAPVALUE('usercode', object.innermsg.sender) }</label>
					<%-- <textarea rows="3" cols="50" class="required" disabled="disabled">${cp:MAPVALUE('usercode', object.innermsg.sender) }</textarea> --%>
					<input name="receiveUserCode" type="hidden" class="required" value=""/>
				</dd>
			</dl>
			<dl>
				<dt>内容：</dt>
				<dd>
					<textarea name="msgcontent" rows="3" cols="50" class="required"></textarea>
				</dd>
			</dl>
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
