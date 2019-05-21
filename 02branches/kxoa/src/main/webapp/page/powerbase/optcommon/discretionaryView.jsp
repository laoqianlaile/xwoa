<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.centit.powerbase.po.*" %>

<html>
<head>
	<title>自由裁量查看</title>
	<base target="_self">
   <meta http-equiv="pragram" content="no-cache"> 
   <meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
   <meta http-equiv="expires" content="0"> 

<style type="text/css">
        .arrow { background:transparent url(arrows.png) no-repeat scroll 0px -16px; width:16px; height:16px; display:block;}
        .up { background-position:0px 0px;}
 
 </style>
 <style type="text/css">

              .byte { 
                   background-color:transparent; 
                    border-bottom:0px;
                    border-left:0px;
                    border-right:0px;
                    border-top:0px;
               }
</style>
 
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">  
        $(document).ready(function(){
            
            $("#arrow1").click(function(){
                $("#table_b1").find(".detailItemBody").toggle();
                $("#table_b1").find(".arrow").toggleClass("up");
            });
             $("#arrow2").click(function(){
                $("#table_b2").find(".detailItemBody").toggle();
                $("#table_b2").find(".arrow").toggleClass("up");
            });
            //$("#report").jExpand();
        });
    </script> 
</head>
<body style="overflow: hidden; ">
		<s:form action="supPower" method="post" namespace="/powerbase"
			id="suppowerForm" enctype="multipart/form-data">
		<input type="hidden" name="action" value="<%=request.getParameter("action") %>" >
		<input type="hidden" name="selectItem" value="">
		<input type="hidden" name="stepWorkID" value="">
		<input type="hidden" name="item_id" value="">
		<input type="hidden" name="lastModifyTime" value="">
		<input type="hidden" name="node_id" value="[Ljava.lang.String;@1171cd1">
		<br/>
<h2></h2> 
<%
			List list1  = (List)request.getAttribute("list1");
			if(list1.size() > 0){
				for(int i = 0; i < list1.size(); i++){
					Pcfreeumpiredegree zyc = (Pcfreeumpiredegree) list1.get(i);
			
		%>

  <table width="95%" id="table_b1" cellspacing="0" cellpadding="0"  class="table_b">
  <tr class="b_title" >
   <td colspan="2">自由裁量编号</td>
   <td colspan="2"><%=zyc.getStandardNo()%></td>
   <td colspan="2">自由裁量名称</td>
   <td colspan="4"><%=zyc.getPunishfactgrade()%></td>
  
  
    <tbody class="detailItemBody" id="detailItemBody1"> 
   <tr class="b_darkblue">
   
      <td width="20px">序号</td>
    <td width="90px">处罚种类</td>
    <td width="60px">裁量基准</td>
    <td width="90px">上限数值-单位</td>
    <td width="90px">下限数值-单位</td>
    <td width="70px">基数名称</td>
    <td width="80px">基数倍数上限</td>
    <td width="80px">基数倍数下限</td>
    <td width="60px">基数单位</td>
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
      <%
    	if(zyc.getStandardNo().equals(zycview.getStandardNo())){
    	%>
	<tr>
	<td><%=num++%></td>
	<td>
		<bean:define name="list" id="list" />
		<bean:define name="seqtypelist" id="seqtypelist" />
		<bean:define name="seqBaseUnitList" id="seqBaseUnitList" />
		<%
			List list  = (List)request.getAttribute("list");
			if(list.size() > 0){
				for(int m = 0; m < list.size(); m++){
					Pcfreeumpiredegree zyvie = (Pcfreeumpiredegree) list.get(m);
			
		%>
		
		<% if(zyvie.getStandardNo().equals(zycview.getPunishtypeid())){out.print(zyvie.getPunishfactgrade());}%>
		<%}} %>
    </td>
    <td>
    <%
			List seqtypelist  = (List)request.getAttribute("seqtypelist");
			if(seqtypelist.size() > 0){
				for(int m = 0; m < seqtypelist.size(); m++){
					Pcfreeumpiredegree seqtype = (Pcfreeumpiredegree) seqtypelist.get(m);
			
		%>
		
		<% if(seqtype.getStandardNo().equals(String.valueOf(zycview.getPunishtype()))){out.print(seqtype.getPunishfactgrade());}%>&nbsp;
		<%}} %>
	</td>
    <td><input type="text" value="<%=(zycview.getToplimit())==null?"":zycview.getToplimit()%>" size="5" name="EQ_TOPLIMIT" readonly class="byte">&nbsp;
    <input type="text" value="<%=(zycview.getToplimitUnit())==null?"":zycview.getToplimitUnit()%>" size="5" name="EQ_TOPLIMIT" readonly class="byte">
	</td>
    <td><input type="text" value="<%=(zycview.getLowlimit())==null?"":zycview.getLowlimit()%>" size="5" name = "SEQ_LOWLIMIT" class="byte">&nbsp;
    <input type="text" value="<%=(zycview.getLowlimitUnit())==null?"":zycview.getLowlimitUnit()%>" size="5" name = "SEQ_LOWLIMIT" class="byte">
	</td>
    <td><input type="text" value="<%=(zycview.getBaseName())==null?"":zycview.getBaseName()%>" size="10" name="SEQ_BASE_NAME"  class="byte" readonly></td>
    <td><input type="text" value="<%=(zycview.getBaseToplimit())==null?"":zycview.getBaseToplimit()%>" size="10" name="SEQ_BASE_TOPMUL"  class="byte" readonly></td>
    <td><input type="text" value="<%=(zycview.getBaseLowlimit())==null?"":zycview.getBaseLowlimit() %>" size="10" name="SEQ_BASE_LOWMUL"  class="byte" readonly></td>
    <td>
    	 <input type="text" value="<%=(zycview.getBaseUnit())==null?"":zycview.getBaseUnit()%>" size="5" name = "SEQ_LOWLIMIT" class="byte">
	</td>
	</tr>
	<%}%>
    <%}} %>
	</tbody>
</table> 
<br>
<%}
} %>
       <table align="center">
       		<tr>
       			<td><input type="button" class="btn" onclick="window.close();"
			value="关闭" />
		     </td>
       		</tr>
       </table>
	</s:form>
</body>
<script LANGUAGE="JavaScript" >
function addOrderRow(tab,rowNum,colNum,obj,addType) 
{ 
var detailbody=document.getElementById(tab); 
var row = document.createElement("tr"); 
var newrow=obj.parentNode.parentNode.innerHTML; 
if(addType=='add'){ 
var row = detailbody.insertRow(); 
for(var i=0;i<obj.parentNode.parentNode.childNodes.length;i++){ 
var cell=row.insertCell(); 
cell.innerHTML=obj.parentNode.parentNode.childNodes[i].innerHTML; 
//如果表单中有值就清空 
for(var k=0;k<cell.childNodes.length;k++){ 
if (cell.childNodes[k].type == 'text') { cell.childNodes[k].value = ''; } 
if (cell.childNodes[k].type == 'textarea') { cell.childNodes[k].value = ''; } 
if (cell.childNodes[k].type == 'checkbox') { cell.childNodes[k].checked = false; } 
if (cell.childNodes[k].type == 'radio') { cell.childNodes[k].checked = false; } 
if (cell.childNodes[k].type == 'select-multiple') { cell.childNodes[k].selectedIndex = -1; } 
if (cell.childNodes[k].type == 'select-one') { cell.childNodes[k].selectedIndex = -1; } 
} 
//cell.innerHTML=arr[i]; 
} 

}else if(addType=='copy'){ 
//copy 
//detailbody.insertRow().insertCell().innerHTML = newrow;   ok 
var row = detailbody.insertRow(); 
for(var i=0;i<obj.parentNode.parentNode.childNodes.length;i++){ 
var cell=row.insertCell(); 
cell.innerHTML=obj.parentNode.parentNode.childNodes[i].innerHTML; 
} 
}else{ 
//delete 
if(confirm("Are you sure to delete this record?")){ 
    obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode); 
}else{ 
return false; 
} 
} 

} 
function btn_doSave(){
  var item_id = window.dialogArguments.item_id;
   document.forms(0).item_id.value = item_id;
   document.forms(0).submit();
   window.close();
   window.dialogArguments.window.location="/jttsun/supPower.do?action=showInfo&item_id="+item_id; 
} 
function add(){ 
        var obj=document.getElementById("view"); 
        var value=document.getElementById("here").value; 

        alert(obj.options[obj.selectedIndex].text); 
        value=obj.options[obj.selectedIndex].text; 
    } 
    function closewin(){
    	if(document.forms[0].action.value!='viewzyc'){
    	   window.close();
    	}else{
    	   var DG = frameElement.lhgDG; 
           DG.cancel(); 
        }
    }
  </script>
</html>
