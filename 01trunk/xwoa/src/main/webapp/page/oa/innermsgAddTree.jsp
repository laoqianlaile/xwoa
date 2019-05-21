<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="cn">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=8" />

<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/charisma-css.jsp"%> 


<title>我的消息</title>
</head>
<body>


<div class="container-fluid">
	
		<ul class="breadcrumb">
		  <li class="active">选择接收消息人员</li>
		</ul>
		
		
		<div class="row-fluid">
		
			<div class="span12 box">
			
				<div class="box-content">

					<form class="form-horizontal">
						<input id="txt_innermsg_receive_usercode" type="hidden"/>
						<input id="txa_innermsg_receive_name" type="hidden"/>
						
							
							<div class="control-group">
								<label class="control-label" for="msgtitle">
									<div>
										<div class="zTreeDemoBackground left">
											<ul id="innermsg_tree" class="ztree" style="width: 320px;height: 350px;"></ul>
										</div>
									</div>
								</label>
								
							</div>
							
							<div id="innermsg_btns" class="form-actions">
								
								<input id="btn_innermsg_add_tree" type="button" class="btn btn-primary" value="确定并返回" />
								<button id="btn_close" class="btn">关闭</button>
							</div>
					</form>

				</div>
			
			</div>
		
		</div>
				
	</div>




<%-- <%@ include file="/page/common/desktop.jsp"%> --%>
<%@ include file="/page/common/charisma-js.jsp"%> 









</body>
</html>