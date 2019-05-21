<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>邮件明细</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/newStatic/js/3rd/splitter.js"></script>
    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;font-size:14px;}
       ul li { list-style-type: none; }
       .btn-group{background:#f3f6f9; padding:3px 10px;border-bottom:1px solid #ccc}
        .btn-group input{width:70px;height:25px;background:#fff;border:1px solid #ccc;cursor:pointer;}
       .btn-group input:hover{background:#F7F7F7}
       .content{padding:5px;}
       .desc{background:#F1F6F5;padding:5px;}
       .desc table td{font-size:12px;color:#798699;text-align:left;}
       .desc table td xmp{font-size:12px;}
       .desc table th{font-size:12px;text-align:left;line-height:25px;}
       .affix{overflow:hidden;}
       .affix li{float:left;padding:2px;}
       .affix li a{display:block;padding:5px;text-decoration: none}
       .affix li a:hover{background:#A2CEFA}
       .affix li a span{display:inline-block;padding-left:3px;font-size:12px;color:#798699}
    </style>
    <script type="text/javascript">
    $(function(){
    	$("#bodyContentIframe").contents().find("body").html($("#bodyContentIframeHiden").html()).css("font",'12px/1.5 "sans serif",tahoma,verdana,helvetica');
    	 setTimeout(function(){
    		 resizeIframeHeight($("#bodyContentIframe"));	 
    	 },200);
	});
    function resizeIframeHeight($iframe) {
    	var ifm = $iframe[0];
    	 ifm.height = $("#bodyContentIframe").contents().find("body")[0].scrollHeight;
    }   
    
	function goMailFrom(id,sendType){
		DialogUtil.open("写邮件","${ctx}/oa/mailinfo!mailForm.do?currentEmail=${object.email}&id="+id+"&sendType="+sendType,"80%","90%");
	}
	function disableEmail(id){
		DialogUtil.confirm("确定要将该邮件放进垃圾箱吗？",function(){
			$.ajax({
				type:"post",
				url:"${ctx}/oa/mailinfo!disableEmail.do",
				dataType:"json",
				data:{
					"ids":id
				},
				success:function(resp){
					if(resp){
						var mailListIframe = $(parent.parent.document).find("#rightDetail");
						mailListIframe.attr("src",mailListIframe.attr("src"));
					}else{
						DialogUtil.alert("操作失败");
					}
				}
			})	
		})
	}
	function removeEmail(id){
		DialogUtil.confirm("确定要永久删除该邮件吗？",function(){
			$.ajax({
				type:"post",
				url:"${ctx}/oa/mailinfo!removeEmail.do",
				dataType:"json",
				data:{
					"ids":id
				},
				success:function(resp){
					if(resp){
						var mailListIframe = $(parent.parent.document).find("#rightDetail");
						mailListIframe.attr("src",mailListIframe.attr("src"));
					}else{
						DialogUtil.alert("操作失败");
					}
				}
			})	
		})
	}
    </script>
  </head>
  <body>
    <div class="container">
       <div class="btn-group">
        <c:if test="${object.isvalid=='T'}">
       <!-- 如果是收件箱的邮件才可以回复 -->
         <c:if test="${object.location==2}">
            <input type="button" value="回复" onclick="goMailFrom(${object.id},'12')"/>
            <input type="button" value="回复全部" onclick="goMailFrom(${object.id},'14')"/>
          </c:if>
          <!-- 如果不是草稿箱的邮件都可以转发 -->
          <c:if test="${object.location!=3}">
            <input type="button" value="转发" onclick="goMailFrom(${object.id},'13')"/>
          </c:if>
          <!-- 如果是草稿箱的邮件可以编辑 -->
          <c:if test="${object.location==3}">
            <input type="button" value="编辑" onclick="goMailFrom(${object.id},'${object.sendType}')"/>
          </c:if>
          <input type="button" value="删除" onclick="disableEmail(${object.id})"/>
          </c:if>
           <input type="button" value="彻底删除" onclick="removeEmail(${object.id})"/>
       </div>
       <div class="content">
         <div class="desc">
	         <table>
	           <tr>
	             <th colspan="2">
	             ${object.subject}
	             </th>
	           </tr>
	           <tr>
	            <td>发件人：</td>
	            <td><xmp>${object.sender}</xmp></td>
	           </tr>
	           <tr>
	            <td>收件人：</td>
	            <td><xmp>${object.receiver}</xmp></td>
	           </tr>
	           <c:if test="${not empty object.copyer}">
	           <tr>
	            <td>抄送人：</td>
	            <td><xmp>${object.copyer}</xmp></td>
	           </tr>
	           </c:if>
	           <tr>
	            <td>时&nbsp;&nbsp;&nbsp;间：</td>
	            <td><fmt:formatDate value="${empty object.sendTime ? object.createTime : object.sendTime}"
							pattern="yyyy年MM月dd日 HH:mm" /></td>
	           </tr>
	         </table>
         </div>
         <c:if test="${not empty object.attachments}">
	         <div class="affix">
	            <ul>
	             <c:forEach var="affix" items="${object.attachments}">
	              <li><a href="${ctx}/oa/mailinfo!downloadAffix.do?attachmentId=${affix.id}">${affix.fileName}<span>(${affix.fmtFileSize})</span></a></li>
	             </c:forEach>
	            </ul>
	         </div>
         </c:if>
         
         <div class="body" style="border-top:1px #ccc solid">
           <iframe id="bodyContentIframe" 	width="100%" frameborder="0" 
				 scrolling="no" border="0"></iframe>
            <div id="bodyContentIframeHiden" style="display:none">${object.content}</div>
         </div>
       </div>
         
    </div>
  </body>
</html>