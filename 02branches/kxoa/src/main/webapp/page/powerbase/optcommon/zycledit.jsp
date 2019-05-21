<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.centit.powerbase.po.*" %>
<html>

<head>
	<title>自由裁量编缉</title>

<link rel="stylesheet" type="text/css" href="./styles/default/css/index.css">
  <link rel="stylesheet" href="./css/uniform.default.css" type="text/css" media="screen">
  <script type="text/javascript" src="./js/jquery.min.js"></script>
  <script type="text/javascript" src="./js/jquery.treetable.js"></script>
  <script language="JavaScript" src="./lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
  <script language="JavaScript" src="./lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
  
<style type="text/css">
        .arrow { background:transparent url(./images/del.gif) no-repeat scroll 0px 0px; float:right; width:2%; height:18px; border:0px solid #0000FF;margin:0 auto; line-height:23px}
        .up { background-position:0px 0px;}
 
 </style>
 <style type="text/css">
  .byte { 
       background-color:transparent;  
   }
   
           /*input*/
.input_on{
padding:2px 8px 0pt 3px;
height:22px;
border:1px solid #999;
background-color:#FFFFCC;
}
.input_off{
padding:2px 8px 0pt 3px;
height:22px;
border:1px solid #CCC;
background-color:#FFF;
}
.input_move{
padding:2px 8px 0pt 3px;
height:22px;
border:1px solid #999;
background-color:#FFFFCC;
}
.input_out{
/*height:16px;默认高度*/
padding:2px 8px 0pt 3px;
height:22px;
border:1px solid #CCC;
background-color:#FFF;
}
/*form*/
ul.input_test{
margin:20px auto 0 auto;
width:500px;
list-style-type:none;
}
ul.input_test li{
width:500px;
height:22px;
margin-bottom:10px;
}
.input_test label{
float:left;
padding-right:10px;
width:100px;
line-height:22px;
text-align:right;
font-size:1.4em;
}
.input_test p{
float:left;
_margin-top:-1px;
}
.input_test span{
float:left;
padding-left:10px;
line-height:22px;
text-align:left;
font-size:1.2em;
color:#999;
}
var item_id = window.parent.document.getElementById("item_id");
var version = window.parent.document.getElementById("version");
var dis_detail = window.parent.document.getElementById("dis_detail");
document.getElementById("item_id").value=item_id.value;
document.getElementById("version").value=version.value;
document.getElementById("dis_detail").value=dis_detail.value;
 window.onload=function()
	{
		openflow();
	};
	function openflow(){
		var form=document.getElementById("suppowerForm");
	     form.action="supPower!editzycl.do";     
	     form.submit();
	}
</style>

  
</head>
<body>
	<s:form action="supPower" method="post" namespace="/powerbase"
			id="suppowerForm" enctype="multipart/form-data">
		<input type="hidden" name="action" value="supshowDepAllStepWorkInfo" >
		<input type="hidden" name="selectItem" value="">
		<input type="hidden" name="stepWorkID" value="">
		<input type="hidden" name="item_id">
		<input type="hidden" name="version">
		<input type="hidden" name="dis_detail">
		<input type="hidden" name="lastModifyTime" value="">
	    <input type="hidden" name="punishClass"/>
		
<h2></h2> 

 <input type="button" name=""  class="btn" onclick="CopyTable()" value="添加">
<p>
 <div id= "dispay">
<table width="95%" id="oTable" cellspacing="0" cellpadding="0"  class="table_b" style="DISPLAY:none" >
  <tr class="b_title" >
  <td colspan="10">
  <div style="float:left; width:5%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF; margin:0 auto; line-height:23px" >
 		<input type="button" onclick="addOrderRow('detailItemBody',2,11,this,'add')" value="增加" class="btn" id="btnAdd1" name="btnAdd"/>  </div>
  <div style="float:left; width:25%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF; margin:0 auto; line-height:23px" >
  自由裁量编号：<input type="text" type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" name="stand_id" size="15">
  <input type="hidden" value="1" name="isNewStand">
  <input type="hidden" name="standIdbtn" value="detailItemBody">
  </div>
  <div style="float:left; width:68%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF;">
  自由裁量名称：<input type="text" type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" size="55" value="" name="stand_code">
  </div>
<div class="arrow" onclick="deleteElement(this)"> </div>
  </td>
  </tr>

   <tbody id="detailItemBody"> 
   <tr class="b_darkblue" align="center">
   
    <td width="40px">序号</td>
    <td width="90px">处罚种类</td>
    <td width="60px">裁量基准</td>
    <td width="90px">上限数值-单位</td>
    <td width="90px">下限数值-单位</td>
    <td width="70px">基数名称</td>
    <td width="80px">基数倍数上限</td>
    <td width="80px">基数倍数下限</td>
    <td width="60px">基数单位</td>
    <td width="40px">操作</td>
   </tr>
	<tr>
	<td>1</td>
	<td>
		<select name="seq_id" >
		<option value="" selected="selected">-处罚种类-</option>
		<option value="1" >警告</option>
		<option value="2">通报批评</option>
		<option value="3">罚款</option>
		<option value="4">没收财物</option>
		<option value="5">暂扣或者吊销许可证和营业执照</option>
		<option value="6">责令停产、停业</option>
		<option value="7">行政拘留</option>
		<option value="8">劳动教养</option>
		<option value="9">其他</option>
		</select>
		
    </td>
    <td>
       <select name="seq_type" >
		<option value="" selected="selected"></option>
		<option value="1" >上下限</option>
		<option value="2">基数</option>
		<option value="3" >其他</option>
		</select>
	</td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="eq_toplimit">&nbsp;
    <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="seq_toplimit_unit">
	</td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name = "seq_lowlimit">&nbsp;
    <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="seq_lowlimit_unit"></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"  size="8" name="seq_base_name" value=""></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"  size="10" name="seq_base_topmul" value=""></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"  size="10" name="seq_base_lowmul" value=""></td>
    <td>
        <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="seq_base_unit">
	</td>
		<td><input type="button" onclick="addOrderRow('detailItemBody',2,11,this,'delete')" value="删除" class="btn" id="btnDelete" name="btnDelete"/>
		<input type="hidden" name="buttable" value="detailItemBody">
		<input type="hidden" name="butadd" value="">
		<input type="hidden" name="zycNo" value="">
	
		</td>
	</tr>
	</tbody>
</table> 
</div>
<% 
	String flagcyz=request.getAttribute("flagcyz").toString();
	String flagcyzdis=request.getAttribute("flagcyzdis").toString();
	if(flagcyz.equals("1")){
		%>
			
<%
			List list1  = (List)request.getAttribute("list1");
			if(list1.size() > 0){
				for(int i = 0; i < list1.size(); i++){
					Pcfreeumpiredegree zyc = (Pcfreeumpiredegree) list1.get(i);
			
		%>
  <table width="95%" id="table_b1" cellspacing="0" cellpadding="0"  class="table_b">
  <tbody>
  <tr class="b_title" valign="middle" >
  <td colspan="10" width="100%" valign="middle" >
  <div style="float:left; width:5%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF; margin:0 auto; line-height:23px" >
  <input type="button" onclick="addOrderRow('detailItemBody<%=i+1 %>',2,11,this,'add')" value="增加" class="btn" id="btnAdd1" name="btnAdd"/>
  </div>
  <div style="float:left; width:25%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF; margin:0 auto; line-height:23px" >
   自由裁量编号：<%=zyc.getStandardNo()%><input type="hidden" value="<%=zyc.getStandardNo()%>" name="stand_id"  readonly="readonly" class="byte">
  <input type="hidden" value="0" name="isNewStand">
  </div>
  <div style="float:left; width:68%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF;">
  自由裁量名称：<input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" size="55" value="<%=zyc.getPunishfactgrade()%>" name="stand_code"><input type="hidden" name="standIdbtn" value="detailItemBody<%=i+1%>">
  </div>
<div class="arrow" onclick="deleteElement(this)"> </div>

</td>
</tr>
  </tbody>
  
    <tbody class="detailItemBody" id="detailItemBody<%=i+1%>"> 
   <tr class="b_darkblue" align="center">
   
    <td width="40px">序号</td>
    <td width="90px">处罚种类</td>
    <td width="60px">裁量基准</td>
    <td width="90px">上限数值-单位</td>
    <td width="90px">下限数值-单位</td>
    <td width="70px">基数名称</td>
    <td width="80px">基数倍数上限</td>
    <td width="80px">基数倍数下限</td>
    <td width="60px">基数单位</td>
    <td width="40px">操作</td>
   </tr>
   	<%
   		int num =1;
    %>
    <%
			List list2  = (List)request.getAttribute("list2");
			if(list2.size() > 0){
				for(int j = 0; j < list2.size(); j++){
					Pcfreeumpiretype zycview = (Pcfreeumpiretype) list2.get(j);
			
		%>
      <%if(zyc.getStandardNo().equals(zycview.getStandardNo())){ 
        %>
	<tr>
	<td><%=num++%></td>
	<td>
		<select name="seq_id" >
		<option value="" >--处罚种类--</option>
			<%
			List list  = (List)request.getAttribute("list");
			if(list.size() > 0){
				for(int m = 0; m < list.size(); m++){
					Pcfreeumpiredegree zyvie = (Pcfreeumpiredegree) list.get(m);
			
		%>
		<option value="<%=zyvie.getStandardNo() %>"  <%if(zyvie.getStandardNo().equals(zycview.getPunishtypeid())){%>selected="selected"<%}%>><%=zyvie.getPunishfactgrade() %></option>
		<%}} %>
		</select>
		
    </td>
    <td>
       <select name="seq_type" >
		<option value=""></option>
		   <%
			List seqtypelist  = (List)request.getAttribute("seqtypelist");
			if(seqtypelist.size() > 0){
				for(int m = 0; m < seqtypelist.size(); m++){
					Pcfreeumpiredegree seqtype = (Pcfreeumpiredegree) seqtypelist.get(m);
			
		%>
		<option value="<%=seqtype.getStandardNo() %>" <%if(seqtype.getStandardNo().equals(String.valueOf(zycview.getPunishtype()))){%>selected="selected"<%}%>><%=seqtype.getPunishfactgrade() %></option>
		<%}} %>
		</select>
	</td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=(zycview.getToplimit())==null?"":zycview.getToplimit()%>" size="5" name="eq_toplimit">&nbsp;
    <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=zycview.getToplimitUnit()==null?"":zycview.getToplimitUnit() %>" size="5" name="seq_toplimit_unit">
	</td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=(zycview.getLowlimit())==null?"":zycview.getLowlimit()%>" size="5" name = "seq_lowlimit">&nbsp;
    <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=zycview.getLowlimitUnit()==null?"":zycview.getLowlimitUnit() %>" size="5" name="seq_lowlimit_unit"></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=(zycview.getBaseName())==null?"":zycview.getBaseName()%>" size="8" name="seq_base_name"></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=(zycview.getBaseToplimit())==null?"":zycview.getBaseToplimit()%>" size="10" name="seq_base_topmul"></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=(zycview.getBaseLowlimit())==null?"":zycview.getBaseLowlimit() %>" size="10" name="seq_base_lowmul"></td>
    <td>
       <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="<%=zycview.getBaseUnit()==null?"":zycview.getBaseUnit() %>" size="5" name="seq_base_unit">
	</td>
		<td><input type="button" onclick="addOrderRow('detailItemBody<%=i+1 %>',2,11,this,'delete')" value="删除" class="btn" id="btnDelete" name="btnDelete" />
		<input type="hidden" name="zycNo" value="<%=zycview.getStandardNo()%>">
		<input type="hidden" name="buttable" value="detailItemBody<%=i+1%>">
		</td>
	</tr>
	<%}%>
     <%}} %>
	</tbody>
</table>
<br> 
<%}} %>
<% }else{
%>

<table width="95%" id="oTable" cellspacing="0" cellpadding="0"  class="table_b"  >
  <tr class="b_title" >
  <td colspan="10">
  <div style="float:left; width:5%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF; margin:0 auto; line-height:23px" >
 		<input type="button" onclick="addOrderRow('detailItemBody1',2,11,this,'add')" value="增加" class="btn" id="btnAdd1" name="btnAdd"/>  </div>
  <div style="float:left; width:25%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF; margin:0 auto; line-height:23px" >
  自由裁量编号：<input type="text" type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" name="stand_id" size="15">
  <input type="hidden" value="1" name="isNewStand">
  <input type="hidden" name="standIdbtn" value="detailItemBody1">
  </div>
  <div style="float:left; width:68%; height:18px; border-right:0px solid #0000FF; border-top:0px solid #0000FF; border-bottom:0px solid #0000FF;">
  自由裁量名称：<input type="text" type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" size="55" value="" name="stand_code">
  </div>
<div class="arrow" onclick="deleteElement(this)"> </div>
  </td>
  </tr>

   <tbody id="detailItemBody1"> 
   <tr class="b_darkblue" align="center">
   
    <td width="40px">序号</td>
    <td width="90px">处罚种类</td>
    <td width="60px">裁量基准</td>
    <td width="90px">上限数值-单位</td>
    <td width="90px">下限数值-单位</td>
    <td width="70px">基数名称</td>
    <td width="80px">基数倍数上限</td>
    <td width="80px">基数倍数下限</td>
    <td width="60px">基数单位</td>
    <td width="40px">操作</td>
   </tr>
	<tr>
	<td>1</td>
	<td>
		<select name="seq_id" >
		<option value="" selected="selected">-处罚种类-</option>
		<option value="1" >警告</option>
		<option value="2">通报批评</option>
		<option value="3">罚款</option>
		<option value="4">没收财物</option>
		<option value="5">暂扣或者吊销许可证和营业执照</option>
		<option value="6">责令停产、停业</option>
		<option value="7">行政拘留</option>
		<option value="8">劳动教养</option>
		<option value="9">其他</option>
		</select>
		
    </td>
    <td>
       <select name="seq_type" >
		<option value="" selected="selected"></option>
		<option value="1" >上下限</option>
		<option value="2">基数</option>
		<option value="3" >其他</option>
		</select>
	</td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="eq_toplimit">&nbsp;
    <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="seq_toplimit_unit">
	</td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name = "seq_lowlimit">&nbsp;
    <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="seq_lowlimit_unit"></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"  size="8" name="seq_base_name" value=""></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"  size="10" name="seq_base_topmul" value=""></td>
    <td><input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'"  size="10" name="seq_base_lowmul" value=""></td>
    <td>
        <input type="text" class="input_out" onfocus="this.className='input_on';this.onmouseout=''" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" value="" size="5" name="seq_base_unit">
	</td>
		<td><input type="button" onclick="addOrderRow('detailItemBody1',2,11,this,'delete')" value="删除" class="btn" id="btnDelete" name="btnDelete"/>
		<input type="hidden" name="buttable" value="detailItemBody1">
		<input type="hidden" name="butadd" value="">
		<input type="hidden" name="zycNo" value="">
	
		</td>
	</tr>
	</tbody>
</table> 

<%}%>

<div id="parseTable"></div>
<br>
		<div align="center">
			<input type="button" name="" onclick="btn_doSave();" value="保存" class="btn">
		</div>
		
</s:form>
</body>
<script LANGUAGE="JavaScript" >
function addOrderRow(tab,rowNum,colNum,obj,addType) 
{


var detailbody=document.getElementById(tab); 
var currRow=obj.parentNode.parentNode.rowIndex;
var row = document.createElement("tr"); 
var newrow=obj.parentNode.parentNode.innerHTML; 
if(addType=='add'){
var row = detailbody.insertRow(); 

for(var i=0;i<detailbody.getElementsByTagName("TR")[1].childNodes.length;i++){ 
var cell=row.insertCell();

if(i==0)
cell.innerHTML=parseInt(detailbody.getElementsByTagName("TR").length-2)+1;
else
cell.innerHTML=detailbody.getElementsByTagName("TR")[1].childNodes[i].innerHTML;

//如果表单中有值就清空 
for(var k=0;k<cell.childNodes.length;k++){ 
if (cell.childNodes[k].type == 'text') { cell.childNodes[k].value = ''; } 
if (cell.childNodes[k].type == 'textarea') { cell.childNodes[k].value = ''; } 
if (cell.childNodes[k].type == 'checkbox') { cell.childNodes[k].checked = false; } 
if (cell.childNodes[k].type == 'radio') { cell.childNodes[k].checked = false; } 
if (cell.childNodes[k].type == 'select-multiple') { cell.childNodes[k].selectedIndex = -1; } 
if (cell.childNodes[k].type == 'select-one') { cell.childNodes[k].selectedIndex = ''; } 
} 
//cell.innerHTML=arr[i]; 
} 

}else if(addType=='copy'){ 
//copy 
//detailbody.insertRow().insertCell().innerHTML = newrow;   ok 
var row = detailbody.insertRow(); 
for(var i=0;i<obj.parentNode.parentNode.childNodes.length;i++){ 
var cell=row.insertCell(); 
if(i==0)
cell.innerHTML=parseInt(obj.parentNode.parentNode.childNodes[i].innerHTML)+1;
else
cell.innerHTML=obj.parentNode.parentNode.nextSibling.childNodes[i].innerHTML;
} 
}else{ 
//delete 
if(confirm("是否确定删除此处罚决定?")){ 
    if(currRow-1==1) {
      alert("至少保留一行数据")
      return;
    }
    obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode); 
}else{ 
   return false; 
} 
} 


} 

function btn_doSave(){
	var parentDocument = window.opener.document;
	var eq_toplimit=document.getElementsByName("eq_toplimit");
	var seq_lowlimit=document.getElementsByName("seq_lowlimit");
	var seq_base_topmul=document.getElementsByName("seq_base_topmul");
	var seq_base_lowmul=document.getElementsByName("seq_base_lowmul");
	var stand_id=document.getElementsByName("stand_id");
	var seq_id=document.getElementsByName("seq_id");
	for(var i=0;i<seq_id.length;i++){
		if(seq_id[i].value!=''){
			if(eq_toplimit[i].value!='' && seq_lowlimit[i].value!=''){
				if(parseInt(eq_toplimit[i].value) < parseInt(seq_lowlimit[i].value)){
					alert("上限数值不能小于下限数值！");
					return ;
				}
			}
			if(seq_base_topmul[i].value!='' && seq_base_lowmul[i].value!=''){
				if(parseInt(seq_base_topmul[i].value) < parseInt(seq_base_lowmul[i].value)){
					alert("上限基数倍数不能小于下限基数倍数！");
					return ;
				}
			}
		}
	}
	for(var i=1;i<stand_id.length;i++){
		if(stand_id[i].value!=''){
			//if(isNaN(stand_id[i].value)){
				//alert("自由裁量编号只能为数字！");
				//return;
			//}
		}else{
		   alert("自由裁量编号不能为空")
		   return;
		}
	}
	for(var i=0;i<eq_toplimit.length;i++){
		if(eq_toplimit[i].value!=''){
			if(isNaN(eq_toplimit[i].value)){
				alert("上限基数只能输入数字！");
				return;
			}
		}
	}
	for(var i=0;i<seq_lowlimit.length;i++){
		if(seq_lowlimit[i].value!=''){
			if(isNaN(seq_lowlimit[i].value)){
				alert("下限基数只能输入数字！");
				return;
			}
		}
	}
	for(var i=0;i<seq_base_topmul.length;i++){
		if(seq_base_topmul[i].value!=''){
			if(isNaN(seq_base_topmul[i].value)){
				alert("基数倍数上限只能输入数字！");
				return;
			}
		}
	}
	for(var i=0;i<seq_base_lowmul.length;i++){
		if(seq_base_lowmul[i].value!=''){
			if(isNaN(seq_base_lowmul[i].value)){
				alert("基数倍数下限只能输入数字！");
				return;
			}
		}
	}
 var  stands=document.getElementsByName("standIdbtn");
 var  sels=document.getElementsByName("buttable");
 var  seq_ids=document.getElementsByName("seq_id");
 
 
 for(var i=1;i<stands.length;i++){
      var inbeg=0;
      var  temparray=new Array();
	  for(var j=1;j <sels.length;j++) {
		  if(stands[i].value==sels[j].value){
		       temparray[++inbeg]=seq_ids[j].value;
		  }
	  }
	  for(k=0;k <temparray.length;k++) {
		  for(q=k+1;q <temparray.length;q++) {
			  if(temparray[k]==temparray[q]){
				  alert("同一自由裁量不能选择相同的处罚种类");
				  return;
		     }
	      }
	   }
	
 }
 var item_id = document.forms[0].item_id.value;
 var dis_standard=document.forms[0].punishClass.value;
 var eq_toplimitleng = document.getElementsByName("eq_toplimit").length;
 var seq_lowlimitleng = document.getElementsByName("seq_lowlimit").length;
 var seq_id=conventToString('seq_id');
 var seq_type=conventToString('seq_type'); 
 var eq_toplimit=conventToString('eq_toplimit');
 var seq_lowlimit=conventToString('seq_lowlimit');
 var seq_toplimit_unit=conventToString('seq_toplimit_unit');
 var seq_lowlimit_unit=conventToString('seq_lowlimit_unit');
 var seq_base_topmul=conventToString('seq_base_topmul');
 var seq_base_lowmul=conventToString('seq_base_lowmul');
 var seq_base_unit=conventToString('seq_base_unit');
 var stand_id=conventToString('stand_id');
 var stand_code=conventToString('stand_code');
 var buttable=conventToString('buttable');
 var standIdbtn=conventToString('standIdbtn');
 var seq_base_name=conventToString('seq_base_name');
 var isNewStand=conventToString('isNewStand');
var ret = '1';
 var url = "<%=request.getContextPath()%>/powerbase/supPower!zyclView.do?seq_id="+seq_id+"&isNewStand="+isNewStand+"&stand_id="+stand_id+"&seq_type="+seq_type+"&eq_toplimit="+eq_toplimit+"&seq_lowlimit="+seq_lowlimit+"&seq_toplimit_unit="+seq_toplimit_unit+"&seq_lowlimit_unit="+seq_lowlimit_unit+"&seq_base_topmul="+seq_base_topmul+"&seq_base_lowmul="+seq_base_lowmul+"&seq_base_unit="+seq_base_unit+"&item_id="+item_id+"&punishClass="+dis_standard+"&num="+Math.random()+new Date();
 url=url.replaceAll('%','%25');
 if (window.XMLHttpRequest){ 
 	req = new XMLHttpRequest(); 
 }else if (window.ActiveXObject) { 
	req = new ActiveXObject("Microsoft.XMLHTTP"); 
 } 
 if(req){ 
  		 req.open("GET",url,false); 
  		 req.onreadystatechange = function(){
   		 var str=""; 
  		 if (req.readyState == 4){ 
   			 if (req.status == 200){ 
	   			 var type = req.responseText;
	   				 if(type == '0'){
	  					 ret = '0';
	  					 alert('处罚决定与标准不符');
	    		 }else if(type == '2'){
	  					 ret = '0';
	  					 alert('自由裁量编号已经存在');
	    		 } 
   			 } 
  		 }
  	 	}; 
   		req.send(null); 
 } 
  if(ret == '0'){
    return;
 }
 

  var retStr="";
  var url = "<%=request.getContextPath()%>/powerbase/supPower!genDiscretionaryXml.do?seq_id="+seq_id+"&seq_base_name="+seq_base_name+"&standIdbtn="+standIdbtn+"&buttable="+buttable+"&stand_code="+stand_code+"&stand_id="+stand_id+"&seq_type="+seq_type+"&eq_toplimit="+eq_toplimit+"&seq_lowlimit="+seq_lowlimit+"&seq_toplimit_unit="+seq_toplimit_unit+"&seq_lowlimit_unit="+seq_lowlimit_unit+"&seq_base_topmul="+seq_base_topmul+"&seq_base_lowmul="+seq_base_lowmul+"&seq_base_unit="+seq_base_unit+"&item_id="+item_id+"&num="+Math.random()+new Date();
  url=url.replaceAll('%','%25');	
	if (window.XMLHttpRequest){ 
		req = new XMLHttpRequest(); 
	}else if (window.ActiveXObject) { 
		req = new ActiveXObject("Microsoft.XMLHTTP"); 
	} 
	if(req){ 
		req.open("GET",url,false); 
		req.onreadystatechange = function(){
		if (req.readyState == 4){ 
 			if (req.status == 200){ 
  			  retStr = req.responseText;
 			} 
		}
		}; 
		req.send(null); 
	}
	//alert(retStr);
	var wzsm = retStr.split("wzsm??");
	parentDocument.getElementById('freeJudge').value = wzsm[1];
	parentDocument.getElementById('dis_detail').value = wzsm[0];
	window.close();
	//var DG = frameElement.lhgDG; 
   // J('#dis_detail',DG.curDoc).val( retStr ); 
   // DG.cancel(); 
	//window.returnValue=retStr; 
	
  // document.forms(0).submit();
   //window.close();
   //window.dialogArguments.window.location="SupPowerLog.do?action=changeEdit&item_id="+item_id; 
} 

function conventToString(parm){
 var  array=new Array()
 var obj=document.getElementsByName(parm);
 for(var i=0;i<obj.length;i++){
    var objValue=" ";
    if(obj[i].value=="" || obj[i].value==null)
       objValue=" ";
    else
       objValue=obj[i].value;
    array[i]=objValue;
 }
 return array.join();
}

function add(){ 
        var obj=document.getElementById("view"); 
        var value=document.getElementById("here").value; 
        value=obj.options[obj.selectedIndex].text; 
    } 
    
    
 function CopyTable(){
     var obj=document.getElementById("oTable"); 
    CopyHtmlElement(obj)
    //var odiv = document.getElementById('oTable');
	//odiv.style.display ="block";
    //var table = document.createElement("table");
    //document.body.appendChild(table);
    //var detailbody=
	//var row = detailbody.insertRow(); 
	//for(var i=0;i<obj.parentNode.parentNode.childNodes.length;i++){ 
	//var cell=row.insertCell(); 
	//cell.innerHTML=obj.parentNode.parentNode.childNodes[i].innerHTML; 
	//}
}
function CopyHtmlElement(obj)
{
  var id='detailItemBody';
  var begstr=obj.outerHTML.substring(0,obj.outerHTML.indexOf(id));
  var endstr=obj.outerHTML.substr(obj.outerHTML.indexOf(id)+id.length);
  var sorNum=document.getElementsByTagName("tbody").length/2-0.5;
  var strHtml=obj.outerHTML;
  str=strHtml.replaceAll('detailItemBody',"detailItemBody"+sorNum);
  str=str.replaceAll('none',"");
  document.getElementById('parseTable').innerHTML=document.getElementById('parseTable').innerHTML+str;
}

String.prototype.replaceAll = function(s1,s2) {

    return this.replace(new RegExp(s1,"gm"),s2);
}
   
function sle(){
  var   sels=document.getElementsByName("seq_id"); 
  for(i=0;i <sels.length-1;i++) 
  for(j=i+1;j <sels.length;j++) 
  if(sels[i].value==sels[j].value){
	  alert("选择的值不能相同");
	  return;
  }
}

  function   deleteElement(obj)   
  {   
  var   d   =   obj.parentNode.parentNode.parentNode.parentNode;   
  if   (d   &&   confirm("您要删除此自由裁量?"))   
  {   
  var   p   
  if (p  = d.parentNode)   
  {   
   p.removeChild(d);   
  }   
  }   
  }   

  </script>
</html>
