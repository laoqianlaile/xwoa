<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ include file="/page/common/taglibs.jsp"%> --%>

          <!-- 办理信息 片段  示例-->
<!--           <table class="div_idea">
            <tr>
				<td class="addTd" style="height: 80px;"></td>
				<td class="info" colspan="5"><p class="idea"
						data-nodecodes="sw_czsh"></p>&nbsp;</td>
			</tr>
		  </table> -->
		
			<script type="text/javascript">
			$(function(){
				var _div=$(".div_idea");
				var _isIdeashow=$("#isVailViewPower").val();//是否需要显示办理意见
				//根据djid 获取需要展示办件信息
				$.ajax({type : "POST",url : "${contextPath}/powerruntime/generalOperator!showIdeaModuleList.do",
					async:false,  			    
					data: {djid:$("#djId").val()},
										dataType : "json",
										success : function(json) {
											var data=JSON.stringify(json);
											var dataObj=eval("("+data+")");//转换为json对象 
											$.each(dataObj.moduleList,function(i,o){
												//循环通用配置--配置为显示显示环节意见
												if("T"==o.isShowInNode){
												 var item;
													item="<tr>";
													item+="<td class='addTd'  >";
													item+=o.nodeLabel;
													item+="</td>";
													item+="<td class='info' colspan='5'><p class='ideaModuleCode' data-modulecode= '";
													item+=o.moduleCode;
													item+="'></p>&nbsp;</td> ";
													item+="</tr>";
												_div.append(item);
												}
											});
											if(!('F'==_isIdeashow)){//是否显示意见
												initIdeas();
												FrameUtils.adjustParentHeight(window);
											}
											
// 											init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);

										},
										error : function() {
											_div.append("数据获取失败，刷新后重试！");
										}

									});
				
				
			
			})
				function initIdeas(e) { 
				       $.each($('.ideaModuleCode'),
						function() {
		                  var _this=$(this);
							$
									.ajax({
										type : "POST",
										url : "${contextPath}/powerruntime/generalOperator!getAllIdeaInfoByModuleCode.do",
									    data: {djid:$("#djId").val(), moduleCode:_this.data("modulecode")},
									    async:false,  
									    dataType : "json",
										success : function(json) {
											var data=JSON.stringify(json);
											var dataObj=eval("("+data+")");//转换为json对象 
											_this.html(dataObj.idea);
											FrameUtils.adjustParentHeight(window);
// 											init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
										},
										error : function() {
											_this.html("数据获取失败，刷新后重试！");
										}

									})
						});
				}	
				</script>