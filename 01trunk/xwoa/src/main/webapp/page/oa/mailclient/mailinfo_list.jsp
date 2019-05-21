<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>邮箱列表</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/newStatic/js/3rd/splitter.js"></script>
    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;font-size:14px;}
       ul li { list-style-type: none; }
       .container{overflow:hidden;background:#DBE2E8;height:100%}
      .vsplitbar {
		width: 3px;
		background: #DBE2E8 url("${ctxStatic}/image/vgrabber.gif") no-repeat center;
		border:1px solid #929AA2;
	  }
    </style>
    <script type="text/javascript">
    $(function(){
		relayout();
		//初始化选中行事件
		$("#navlist tbody").find("tr").eq(0).click();
		$("#pagebar").find("input").bind('keypress',function(event){
	            if(event.keyCode == "13")    
	            {
	            	var v = $("#pagebar").find("input").val();
	    			if(!isNaN(v)){
	    				goPage(v);				
	    			}
	            }
	        });
	});
	/**
	 * 重新布局
	 */
	function relayout(){
		var winH = $(window).height();
		var diffH = winH -$("#pagebar").outerHeight(true)-2;
		$("#navlist").height(diffH);
		$("#rightDetail").height(diffH+16);
		$("#layout").height(diffH+35);
		//网页分割线
		$("#layout").splitter({
			type: "v",
			outline:true,
			minLeft: 400, sizeLeft: 150, minRight: 100,
			//自定义2个回调事件，源码被改了
		    onMouseDown:function(){
		    	$("#rightDetail").hide();
		    },
		    onMouseUp:function(){
		    	$("#rightDetail").show();
		    }
		});
	}
	 
	/**
	 * 关联明细
	 */
     function linkDetail(ele,id,location,readState){
		if(location=='2' && readState=='F'){
			changeReadState(ele,id);
		}
         var src = "${contextPath }/oa/mailinfo!view.do?id="+id;
         $("#rightDetail").attr("src",src);
         $("#navlist tbody").find("tr").css("background","#fff");
         $(ele).css("background","#F5F8FA");
     }
	/**
	* 全选或全不选
	*/
	 function checkAll(ele){
		 var chkall = $(ele).is(':checked');
		 $("#navlist tbody").find(".chk_item").each(function(){
			 $(this).prop('checked',chkall);
		 });
		 getSelectedRows();
	 }
		
     function checkOne(){
    	 getSelectedRows();
    	 window.event? window.event.cancelBubble = true : evt.stopPropagation();
     }
     function getSelectedRows(){
 		var ids = [];
 		$("#navlist tbody").find(".chk_item").each(function(){
 			 if($(this).is(':checked')){
 				 ids.push($(this).val());
 			 }
 		 });
 		$('#hid_ids').val(ids.join(','));
  }
     function disableEmail(){
     	var ids=$('#hid_ids').val();
     	 if(ids==''){
 			  DialogUtil.alert("请选择要操作的记录");
 		     return;
 		  }
  		DialogUtil.confirm("确定要将选中的邮件放进垃圾箱吗？",function(){
  			$.ajax({
  				type:"post",
  				url:"${ctx}/oa/mailinfo!disableEmail.do",
  				dataType:"json",
  				data:{
  					"ids":ids
  				},
  				success:function(resp){
  					if(resp){
  						var mailListIframe = $(parent.document).find("#rightDetail");
  						mailListIframe.attr("src",mailListIframe.attr("src"));
  					}else{
  						DialogUtil.alert("操作失败");
  					}
  				}
  			});	
  		});
  	}
 	function removeEmail(){
 		var ids=$('#hid_ids').val();
   	     if(ids==''){
			  DialogUtil.alert("请选择要操作的记录");
		     return;
		  }
 		DialogUtil.confirm("确定要永久删除选中的邮件吗？",function(){
 			$.ajax({
 				type:"post",
 				url:"${ctx}/oa/mailinfo!removeEmail.do",
 				dataType:"json",
 				data:{
 					"ids":ids
 				},
 				success:function(resp){
 					if(resp){
 						var mailListIframe = $(parent.document).find("#rightDetail");
 						mailListIframe.attr("src",mailListIframe.attr("src"));
 					}else{
 						DialogUtil.alert("操作失败");
 					}
 				}
 			});	
 		});
 	}
 	function changeReadState(ele,id){
 		$.ajax({
 			type:"post",
 			url:"${ctx}/oa/mailinfo!changeReadState.do",
 			data:{
 				"id":id
 			},
 			dataType:"json",
 			success:function(resp){
 				if(resp.result){
 					$(ele).find(".readState-ico").attr("src","${ctxStatic}/image/yjo.png");
 				}else{
 					DialogUtil.alert("操作失败");
 				}
 			}
 		});
 	}
    </script>
  </head>
  <body>
    <div class="container" id="layout">
         <div style="border-rigth:1px solid #929AA2;background:#fff;">
         <div id="navlist" style="overflow-y: auto;overflow-x:hidden">
            <div id="statusbar" style="height:25px;line-height:25px;border-bottom:1px #ccc solid;background:#F3F6F9">
               <span style="padding-left:9px;"><input type="checkbox"  onclick="checkAll(this)"></span>
               <span style="float:right;margin-right:10px;">共${pageDesc.totalRows}封</span>
            </div>
             <input id="hid_ids" type="hidden" name="ids" /> 
             <c:if test="${empty objList}">
             <div style="position:relative;left:45%;top:40%;font-size:16px;font-weight:bolder;color:#ccc">无内容</div>
             </c:if>
	        <table style="border-collapse:collapse;width:100%">
	           <tbody>
	           <c:forEach var="mailInfo" items="${objList}">
	             <tr style="cursor: pointer;" onclick="linkDetail(this,${mailInfo.id},'${mailInfo.location}','${mailInfo.readState}')">
	              <td style="border-bottom:1px solid #ccc;text-align:center;width:30px;"><input type="checkbox" value="${mailInfo.id}" class="chk_item" onclick="checkOne()"></td>
	              <td style="border-bottom:1px solid #ccc;padding-left:5px;padding-right:5px;">
	                <div>
	                   <c:choose>
								<c:when
									test="${fn:length(mailInfo.senderNickname) > 20 }">${fn:substring(mailInfo.senderNickname, 0 , 20)}……</c:when>
								<c:otherwise>${mailInfo.senderNickname}</c:otherwise>
							</c:choose>
	                </div>
	                <div style="color:#BCBCBC">
		               <c:if test="${fn:length(mailInfo.subject)>20}">
		                   ${fn:substring(mailInfo.subject,0,20)}....
		                </c:if>
		                <c:if test="${fn:length(mailInfo.subject)<=20}">
		                   ${mailInfo.subject}
		                </c:if>
	                </div>
	              </td>
	              <td align="right" style="border-bottom:1px solid #ccc;padding-right:10px;">
	                 <div style="height:20px;"> 
		                  <c:if test="${mailInfo.hasAttachment eq 'T'}">
		                    <img  src="${ctxStatic}/image/fujian.gif" style="margin-top:3px;" title="附件">
		                  </c:if>
		                   <c:if test="${2 eq mailInfo.location}">
			                  <c:if test="${'F' eq mailInfo.readState}">
		                         <img class="readState-ico" src="${ctxStatic}/image/yjc.png" style="margin-top:3px;" title="未读">
		                      </c:if>
		                      <c:if test="${'T' eq mailInfo.readState}">
		                         <img class="readState-ico" src="${ctxStatic}/image/yjo.png" style="margin-top:3px;" title="已读" >
		                      </c:if>
	                      </c:if>
	                  </div>
	                 <div>
	                   <fmt:formatDate value="${empty mailInfo.sendTime ? mailInfo.createTime : mailInfo.sendTime}"
							pattern="MM-dd" />
					 </div>
	              </td>
	              </tr>
	             </c:forEach>
	           </tbody>
	        </table>
         </div>
         <div style="background:#F5F8FA;overflow:hidden;border-top:1px #929AA2 dotted;" id="pagebar">
            <ul>
                 
             <li style="float:left;margin-left:35%">
                <span style="display:inline-block;width:30px;height:30px;background:url('${ctxStatic}/image/mail_prev_p.png') no-repeat center 4px;cursor:pointer" onclick="prePage()" title="上一页"></span>
              </li>
              <li style="float:left;padding-top:8px;">
                <c:set var="maxPage" value="${fns:roundupAfterDivide(pageDesc.totalRows,pageDesc.pageSize)}"/>
                <input type="text" name="_p" style="width:30px;" value="${pageDesc.pageNo}"/>/
                 ${maxPage}
                 </li>
                 <li style="float:left;">
                 <span style="display:inline-block;width:30px;height:30px;background:url('${ctxStatic}/image/mail_next_p.png') no-repeat center 4px;cursor:pointer" onclick="nextPage()" title="下一页"></span> 
              </li> 
            </ul>
         </div>
       </div>
       <div style="border:1px solid #929AA2;border-left-width: 0;border-top-width:0;">
           <iframe style="width:99%;margin:0.5%;;height:740px;" id="rightDetail"></iframe>
       </div>
    </div>
  </body>
</html>