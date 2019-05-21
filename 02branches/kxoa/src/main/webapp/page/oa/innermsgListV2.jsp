<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/newStatic/js/3rd/splitter.js"></script>
<title>我的消息</title>
<script type="text/javascript">
  if("rightDetail"==window.frameElement.id){
	  parent.location.href="${ctx}/oa/innermsg!list.do?s_msgtype=P&s_mailtype=D";
  }
</script>
<style type="text/css">

.vsplitbar {
	width: 3px;
	background: #DBE2E8 url("${ctxStatic}/image/vgrabber.gif") no-repeat center;
	border:1px solid #929AA2;
}
</style>
</head>
<body>
<fieldset  class="search">
		<legend><c:if test="${'D' eq s_mailtype }">草稿箱</c:if>
			<c:if test="${'O' eq s_mailtype }">发件箱</c:if><span class="headContent">（共&nbsp;${totalRows}&nbsp;封）&nbsp;</span></legend>

	<div class="searchDiv">
		<s:form id="innermsg_form" namespace="/oa"
			action="innermsg" style="margin-top:0;margin-bottom:5"
			method="post" data-changeSubmit="true">

			<input id="hid_msgcodes" type="hidden" name="msgcodes" /> 
			<input type="hidden" name="s_msgtype" id="s_msgtype" value="${s_msgtype }" />
			<input type="hidden" name="s_mailtype" value="${s_mailtype }" />
			<input type="hidden" name="s_msgstate" value="${s_msgstate }" />
			<input type="hidden" name="mailtype" value="${s_mailtype }" />
			<input type="hidden" name="isShow" value="${isShow}" />
			
			<div class="searchArea">
			
			    <table style="width:auto;">
				    <tr>
				    <td style="padding-left:2px;vertical-align: middle;">
				       <input type="checkbox" style="background:none;border:none;border-collapse: collapse;" onclick="checkAll(this)">
				    </td>
				    <td class="searchBtnArea" >
						<c:choose>
						<c:when test="${s_msgtype eq 'P' }">
						<s:submit method="editDraft" cssClass="whiteBtnWide" value='发送邮件' />
						</c:when>
						<c:when test="${s_msgtype eq 'F' }">
						<s:submit method="add" cssClass="whiteBtnWide" value='发送文件' />
						</c:when>
						</c:choose>
						<s:submit method="deleteMsg" onclick="return checkBeforeDelete('是否确定批量删除？')" id="a_list_mail_box_delete" 
						cssClass="whiteBtnWide" value="批量删除"></s:submit>
				    </td>
				    <td class="searchTitleArea">
					    <label class="searchCondTitle">标题:</label>
					    </td>
						<td class="searchCountArea">
					    <input type="text" class="searchCondContent"
							name="s_msgtitle" value="${s_msgtitle }" />
							</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea">
						<label class="searchCondTitle"><c:choose>
							<c:when test="${'O' eq param['s_mailtype'] }">
								时间:
							</c:when>
							<c:when test="${'I' eq param['s_mailtype'] }">
								接收时间:
							</c:when>
							<c:otherwise>
								发件时间:
							</c:otherwise>
						</c:choose>	</label>
						</td>
						<td class="searchCountArea">
						<input type="text" class="Wdate searchCondContent"  id="s_begsenddate" <c:if test="${not empty s_begsenddate }"> value="${s_begsenddate}" </c:if>
	                            <c:if test="${empty s_begsenddate  }">value="${param['s_begsenddate'] }"</c:if> name="s_begsenddate"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						                     <label class="searchCondTitle">至</label>
						        <input type="text" class="Wdate searchCondContent"  id="s_endsenddate" <c:if test="${not empty s_endsenddate }"> value="${s_endsenddate }" </c:if>
	                            <c:if test="${empty s_endsenddate  }">value="${param['s_endsenddate'] }"</c:if> name="s_endsenddate" 
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">	
				    </td>
				    <td class="searchCondArea"><div class="clickDiv" onclick="dosearch();"></div></td>
				    </tr>
			
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>
	 
   <div style="margin-top:10px;background:#DBE2E8;overflow:hidden;width:99.4%;border-bottom:1px solid #ccc;" id="layout">
       <div style="border:1px solid #929AA2;background:#fff;">
         <div id="navlist" style="overflow-y: auto;">
	        <table style="border-collapse:collapse">
	           <tbody>
	           <c:forEach var="innermsg" items="${objList}">
	             <tr style="cursor: pointer;" onclick="linkDetail(this,'${innermsg.msgcode}','${s_msgtype}','${s_mailtype}','${s_NP_senddate}')">
	              <td style="border-bottom:1px solid #ccc;text-align:center;width:30px;"><input type="checkbox" value="${innermsg.msgcode}" class="chk_item" onclick="checkOne()"></td>
	              <td style="border-bottom:1px solid #ccc;padding-left:5px;padding-right:5px;">
	                <div>
	                   <c:choose>
								<c:when
									test="${fn:length(cp:MAPVALUE('usercode',innermsg.receivename) ) > 20 }">${fn:substring(cp:MAPVALUE("usercode",innermsg.receivename), 0 , 20)}……</c:when>
								<c:otherwise>${cp:MAPVALUE("usercode",innermsg.receivename) }</c:otherwise>
							</c:choose>
	                </div>
	                <div style="color:#BCBCBC">
		               <c:if test="${fn:length(innermsg.msgtitle)>20}">
		                   ${fn:substring(innermsg.msgtitle,0,20)}....
		                </c:if>
		                <c:if test="${fn:length(innermsg.msgtitle)<=20}">
		                   ${innermsg.msgtitle}
		                </c:if>
	                </div>
	              </td>
	              <td align="right" style="border-bottom:1px solid #ccc;padding-right:10px;">
	                 <div style="height:20px;"> 
		                  <c:if test="${not empty innermsg.msgannexs}">
		                    <img  src="${ctxStatic}/image/fujian.gif" style="margin-top:3px;" title="附件">
		                  </c:if>
	                  </div>
	                 <div>
	                   <fmt:formatDate value="${innermsg.senddate}"
							pattern="MM-dd" />
					 </div>
	              </td>
	              </tr>
	             </c:forEach>
	           </tbody>
	        </table>
         </div>
         <div style="background:#F5F8FA;overflow:hidden" id="pagebar">
            <ul>
                 
             <li style="float:left;margin-left:35%">
                <span style="display:inline-block;width:30px;height:30px;background:url('${ctxStatic}/image/mail_prev_p.png') no-repeat center 3px;cursor:pointer" onclick="prePage()" title="上一页"></span>
              </li>
              <li style="float:left;padding-top:8px;">
                <c:set var="maxPage" value="${fns:roundupAfterDivide(pageDesc.totalRows,pageDesc.pageSize)}"/>
                <input type="text" name="_p" style="width:30px;" value="${pageDesc.pageNo}"/>/
                 ${maxPage}
                 </li>
                 <li style="float:left;">
                 <span style="display:inline-block;width:30px;height:30px;background:url('${ctxStatic}/image/mail_next_p.png') no-repeat center 3px;cursor:pointer" onclick="nextPage()" title="下一页"></span> 
              </li> 
            </ul>
         </div>
       </div>
       <div style="border:1px solid #929AA2;border-left-width: 0">
           <iframe style="width:99%;margin:0.5%;;height:740px;" id="rightDetail"></iframe>
       </div>
    </div>
	
	<SCRIPT type="text/javascript">
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
		var diffH = winH -$(".search").outerHeight(true)-$("#pagebar").outerHeight(true)-15;
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
     function linkDetail(ele,msgcode,msgtype,mailtype,senddate){
         var src = "${contextPath }/oa/innermsg!view.do?msgcode="+msgcode+"&s_msgtype="+msgtype+"&s_mailtype="+mailtype+"&s_NP_senddate="+senddate;
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
	function dosearch(pn){
			if(pn==undefined){
				pn=1;
			}
		    initPageParam(pn);
			$("#innermsg_form").attr("action","${pageContext.request.contextPath}/oa/innermsg!list.do");
			$("#innermsg_form").submit();
	}
	
	function initPageParam(pn){
		var sPageSize = $("#innermsg_form").find("#pageSize");
		var sPageNo = $("#innermsg_form").find("#pageNo");
		if(sPageSize.length==0){
		   $("<input/>",{id:"pageSize",name:"numPerPage",value:"20",type:"hidden"}).appendTo($("#innermsg_form"));
		}
		if(sPageNo.length==0){
			$("<input/>",{id:"pageNo",name:"pageNum",value:pn,type:"hidden"}).appendTo($("#innermsg_form"));
		}else{
			$("#pageNo").val($("#pagebar").find("input").val());
		}
	}
	
	function prePage(){
		var currPageNo = parseInt($("#pagebar").find("input").val());
		if(currPageNo<=1){
			return;
		}else{
			dosearch(--currPageNo);
		}
	}
	function nextPage(){
		var currPageNo = parseInt($("#pagebar").find("input").val());
		var maxPage = parseInt("${maxPage}");
		if(currPageNo>=maxPage){
			return;
		}else{
			dosearch(++currPageNo);
		}
	}
	function goPage(pageNo){
		var maxPage = parseInt("${maxPage}");
		if(pageNo<=1){
			pageNo = 1;
		}
		if(pageNo>=maxPage){
			pageNo = maxPage;
		}
		dosearch(pageNo);
	}
	
	function getSelectedRows(){
		var msgcodes = [];
		$("#navlist tbody").find(".chk_item").each(function(){
			 if($(this).is(':checked')){
				 msgcodes.push($(this).val());
			 }
		 });
		$('#hid_msgcodes').val(msgcodes.join(','));
	}
	
	function checkBeforeDelete(msg){
		 if($('#hid_msgcodes').val()==''){
			  alert("请选择要操作的记录");
		     return false;
		  }
		if(confirm(msg)){
		   return true;
		}
		return false;
	}
	//-->
	</SCRIPT>
</body>
</html>


