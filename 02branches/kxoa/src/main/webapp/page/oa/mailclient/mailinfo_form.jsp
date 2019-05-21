<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>发件箱</title>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
    <script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>

    <style type="text/css">
       * { margin:0; padding:0; font-family:Microsoft YaHei!important;font-size:14px;}
       ul li { list-style-type: none; }
      .btn-group{height:40px;background:#f5f8fa;border-bottom:1px #ccc solid;}
       .btn-group input{width:80px;height:30px;background:#fff;border:1px solid #ccc;cursor:pointer;margin-top:5px;margin-left:5px;}
       .btn-group input:hover{background:#F7F7F7}
        .btn-group span{float:right;display:inline-block;margin-top:8px;color:#5EB5EF;font-weight:bolder;margin-right:10px;}
        .row{padding-left:5px;margin-top:5px;overflow: hidden}
         .row label{display:inline-block; min-width:7%;text-align:right;}
         .row .txt{width:91%;border:solid 1px #ccc;padding:2px;}
         .attachments{overflow:hidden}
         .attachments li{float:left;margin-right:10px;margin-bottom:10px;}
         .attachments li span{display:block;height:40px;line-height:40px;width:140px;padding-left:5px;padding-right:5px;}
         .attachments li span.selected{background:#D7EAFF;border:1px solid #9CB4D0;}
         .attachments li span a{display:inline-block;padding-left:5px;text-decoration: underline;color:#D2AD8F}
         /*kindEditor样式修改*/
       .container,.ke-toolbar,.ke-statusbar{background-color:#E3E8ED!important}
       .ke-statusbar{height:0!important;}
       .ke-toolbar span{border-color:#E3E8ED!important}
    </style>
    <script type="text/javascript">
    var editor;
    $(function(){
    	resizeLayout();
    	kindEditorInit();
    	createInputFileEle();
    	inputBtnBindMousemoveEvent($(".btn-group").find("input[type='file']"));
    	assignmentAfterLoad();
    });
    //文档加载完毕后，赋值改变
    function assignmentAfterLoad(){
    	var sendType = $("#sendType").val();
    	var location = $("#location").val();
    	if(location == "3"){return;}
    	if(sendType == "12"){//回复发送只要保留收件人
    		$("#copyer").val("");
    		$("#secreter").val("");
    		$("#mailInfoId").val("");
    		$("#subject").val("回复："+$("#subject").val());
    	}
        if(sendType == "13"){//转发
        	$("#secreter").val("");
        	$("#copyer").val("");
        	$("#receiver").val("");
        	$("#mailInfoId").val("");
    		$("#subject").val("转发："+$("#subject").val());
    	}
        if(sendType == "14"){//回复全部
        	$("#secreter").val("");
        	$("#mailInfoId").val("");
    		$("#subject").val("回复："+$("#subject").val());
    	}
    }
    function kindEditorInit(){
    	KindEditor.ready(function(K) {
            editor = K.create(
							'textarea[id="msgcontent"]',
							{
								resizeType : 0,
								uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
								items : [
											'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
											'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
											'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
							    afterBlur: function(){
												this.sync();
								}
							
							});
		});
    }
    function resizeLayout(){
    	var winHeight = $(top).height()*0.9;
    	var btnGroupHeight = $(".btn-group").outerHeight(true);
    	var rowHeight = 42;
    	var msgrowHeight = winHeight - btnGroupHeight - rowHeight*4;
    	if($(".attachments").find("li").length!=0){
    		msgrowHeight = msgrowHeight - 60;
    		$(".attachments").height(60);
    	}
        $("#msgcontent").height(msgrowHeight);	
        
    }
    
    function showAttachmentsArea(){
    	if($(".attachments").find("li").length==0){
    		var oldHeight = $(".ke-container").height();
        	var newHeight = oldHeight - 60;
        	$(".ke-container").height(newHeight);
        	$(".attachments").height(60);
    	}
    }
    function hideAttachmentsArea(){
    	if($(".attachments").find("li").length==0){
    		var oldHeight = $(".ke-container").height();
    		var newHeight = oldHeight + 60;
    		$(".ke-container").height(newHeight);
        	$(".attachments").height(0);
    	}
    }
    function removeAttachmentItem(ele){
    	if($(ele).data("affixid")){
    		$.ajax({
        		type:"post",
        		url:"${ctx}/oa/mailinfo!removeAttachment.do",
        		dataType:"json",
        		data:{
        			"attachmentId":$(ele).data("affixid")
        		},
        		success:function(resp){
        			if(resp){
        				$(ele).parents("li").remove();
        		    	hideAttachmentsArea();
        			}else{
        				DialogUtil.alert("操作失败");
        			}
        		}
        	});
    	}else{
    		$(ele).parents("li").remove();
	    	hideAttachmentsArea();
    	}
    	
    	
    }
    /**
    * 我们知道input file控件是无法改变其形式的，也不能通过委托触发其事件，所以我们只能将其隐藏，让他跟随鼠标，这样单击的时候就能触发到文件选择
    */
    function createInputFileEle(){
    	var $fileInput=$("<input/>",{name:"attachment",type:"file"}).css({
    			"position":"absolute",
    			"filter":"alpha(opacity=0)",
    			"opacity":"0", 
    			"width":"30px",
    			"top":"0px",
    			"cursor":"pointer"
    		}).appendTo($(".btn-group"));
    	//绑定change事件
    	fileInputBindChangeEvent($fileInput);
    	return $fileInput;
    }
    function inputBtnBindMousemoveEvent(fileInput){
    	$("#btnAttach").unbind("mousemove").bind("mousemove",function(e){
    		inputfileAttach(e,fileInput);
    	});
    }
    function fileInputBindChangeEvent(fileInput){
    	fileInput.unbind("change").bind("change",function(){
			var arr=$(this).val().split('\\');//注split可以用字符或字符串分割 
			var filename=arr[arr.length-1];//这就是要取得的文件名称
			/*这里input file控件是刷新后才清空，这里处理是克隆一个再添加进去，不然change事件不会触发的*/
			var copyFile = $(this).clone().appendTo($(".btn-group"));
			fileInputBindChangeEvent(copyFile);
			inputBtnBindMousemoveEvent(copyFile);
			showAttachmentsArea();
			addFileItem(filename,$(this).css("position","static"));
       });	
    }
    function addFileItem(filename,inputfile){
        var omitFilename = filename;
        if(filename.length>6){
        	omitFilename = filename.substring(0,6)+"...";
        }
    	var html = '<span title="{0}">{1}<a href="javascript:void(0);" onclick="removeAttachmentItem(this)">删除</a></span>';
    	var li = $("<li>").html(html.replace("{0}",filename).replace("{1}",omitFilename)).append(inputfile);
    	$(".attachments").append(li);
    }
    //input file 跟随
    function inputfileAttach(e,o){
    	var x, y;
        if (e) { x = e.clientX; y = e.clientY; }
        else { x = event.x; y = event.y; }
		 x = (x - 10) + 'px';
		 y = (y - 10) + 'px';
		 o.css("left",x);
		 o.css("top",y);
    }
    function keyupEvent(evt,ele){
    	var e = event || window.event;
    	
    	if(e && (e.keyCode ==188 || e.keyCode ==186)){
    		var temp0 = $(ele).val().substring(0,$(ele).val().length-1);//去除最后的，或；
    		var temp1 = temp0.substring(0,temp0.lastIndexOf(";")+1);//获取正在输入的地址之前的所有地址
    		var temp2 = temp0.substring(temp0.lastIndexOf(";")+1);
    		var temp3 = temp2.substring(0,temp2.indexOf("@"));
    		$(ele).val(temp1+temp3+"<"+temp2+">;");
    	}
    }
    
    function prepareParam(invokeMethod){
    	if(invokeMethod=="save"){
    		$("#location").val("3");
    	}
    	if(invokeMethod=="sendEmail"){
    		$("#location").val("1");
    	}
    	var temp = $("select").val().split(",");
    	$("#sender").val(temp[0]+"<"+temp[1]+">");
    	$("#email").val(temp[1]);
    	$("#profileId").val(temp[2]);
    	$("form").submit();
    }
    //不能关闭窗口，否则上传连接会断开
    function save(){
    	if(!checkForm()){
    		return;
    	}
    	$("form").attr("action","${ctx}/oa/mailinfo!save.do");
    	prepareParam("save");
    }
  //不能关闭窗口，否则上传连接会断开
    function sendEmail(){
    	if(!checkForm()){
    		return;
    	}
    	$("form").attr("action","${ctx}/oa/mailinfo!sendEmail.do");
    	prepareParam("sendEmail");
    }
    function checkForm(){
    	if($.trim($("#receiver").val())==''&&$.trim($("#copyer").val())==''&&$.trim($("#secreter").val())==''){
    		DialogUtil.alert("必须有一个收件方");
    		return false;
    	}
    	return true;
    }
    </script>
  </head>
  <body style="overflow:hidden">
    <div class="container">
      <div class="btn-group">
         <input type="button" value="发送" onclick="sendEmail()"/>
          <input type="button" value="保存" onclick="save()"/>
          <input type="button" id="btnAttach" value="附件"/>
          <span id="currrentEmail" style="border:0;">当前使用邮箱：
          <select style="background:#f5f8fa;">
            <c:forEach var="profile" items="${profileList}">
              <option <c:if test="${profile.email==param.currentEmail}">selected="selected"</c:if> value="${profile.senderName},${profile.email},${profile.id}">${profile.displayName}（${profile.email}）</option>
            </c:forEach>
          </select></span>
       </div>
       <div class="form-area">
          <form action="${ctx}/oa/mailinfo!save.do" method="post" enctype="multipart/form-data">
             <input name="sendType" id="sendType" type="hidden" value="${param.sendType}"/>
             <input name="location" id="location" type="hidden" value="${object.location}"/>
             <input name="email" id="email"  type="hidden"/>
             <input name="sender" id="sender" type="hidden"/>
             <input name="profileId" id="profileId" type="hidden"/>
             <input name="id" id="mailInfoId" type="hidden" value="${object.id}"/>
             <input name="removeAttachmentIds" id="removeAttachmentIds" type="hidden"/>
             <div class="row">
	               <label>收件人：</label>
	               <input class="txt" name="receiver" id="receiver" type="text" onkeyup="keyupEvent(event,this)" value="${object.receiver}"/>
	         </div>
	          <div class="row">
	               <label>抄送：</label>
	               <input class="txt" name="copyer" id="copyer" type="text" onkeyup="keyupEvent(event,this)" value="${object.copyer}"/>
	         </div>
	          <div class="row">
	               <label>密送：</label>
	               <input class="txt" name="secreter" id="secreter" type="text" onkeyup="keyupEvent(event,this)" value="${object.secreter}"/>
	         </div>
	         <div class="row">
	               <label>主题：</label>
	               <input class="txt" name="subject" id="subject" type="text" value="${object.subject}" maxlength="100"/>
	         </div>
	         <div  class="row" style="padding:0">
	            <textarea name="content" id="msgcontent" style="width: 100%;height:100%">
	              <c:if test="${not empty object.location && object.location ne '3'}">
	                <br/><br/><br/>
	                <br>----------------------------------------------------------------------------------------------------------------------------</br>
					 <table style="font-size:12px">
			           <tr>
			            <td style="font-weight: bold">发件人：</td>
			            <td>${fns:convertHtmlTo(object.sender)}</td>
			           </tr>
			           <tr>
			            <td style="font-weight: bold">发送时间：</td>
			            <td><fmt:formatDate value="${object.sendTime}"
									pattern="yyyy年MM月dd日 HH:mm" /></td>
			           </tr>
			           <tr>
			            <td style="font-weight: bold">收件人：</td>
			            <td>${fns:convertHtmlTo(object.receiver)}</td>
			           </tr>
			           <c:if test="${not empty object.copyer}">
			           <tr>
			            <td style="font-weight: bold">抄送人：</td>
			            <td>${fns:convertHtmlTo(object.copyer)}</td>
			           </tr>
			           </c:if>
			           <tr>
			            <td style="font-weight: bold">主题：</td>
			            <td>${object.subject}</td>
			           </tr>
			         </table>
			         <br/>
			         </c:if>
					${object.content}
				</textarea>
	         </div>
	         <div class="row" style="overflow-y:auto">
	           <ul class="attachments"><!-- 草稿箱编辑的时候有附件就将附件显示出来 -->
	             <c:if test="${not empty object.attachments && object.location eq '3'}">
	               <c:forEach var="affix" items="${object.attachments}">
	                   <li>
	                     <span title="${affix.fileName}">
	                       <c:if test="${fn:length(affix.fileName)>6}">${fn:substring(affix.fileName,0,6)}...</c:if>
	                       <c:if test="${fn:length(affix.fileName)<=6}">${affix.fileName}</c:if>
	                       <a href="javascript:void(0);" data-affixid="${affix.id}" onclick="removeAttachmentItem(this)">删除</a>
	                     </span>
	                   </li>
	               </c:forEach>
	             </c:if>
	           </ul>
	         </div>
          </form>
       </div>
    </div>
  </body>
</html>