<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.centit.powerbase.po.*" %>

<html>
<head>
	<title>处罚标准查看</title>
	
	<link rel="stylesheet" type="text/css" href="./styles/default/css/index.css">
  <script type="text/javascript" src="./js/jquery.min.js"></script>
  <script type="text/javascript" src="./js/jquery.treetable.js"></script>
  <script language="JavaScript" src="./lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
  <script language="JavaScript" src="./lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
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
<body>
	<form name="PcStepWorkDefForm'" method="post" action="/zyclview.do" id="SupPowerForm">
		<input type="hidden" name="action" value="<%=request.getParameter("action") %>" >
		<input type="hidden" name="selectItem" value="">
		<input type="hidden" name="stepWorkID" value="">
		<input type="hidden" name="item_id" value="">
		<input type="hidden" name="lastModifyTime" value="">
		<input type="hidden" name="node_id" value="[Ljava.lang.String;@1171cd1">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tablegray">
			<tr>
				<td valign="middle" width="30%">
					
					当前位置：
					<span class="fontred">处罚标准查看</span>
				</td>
				
			</tr>
		</table>
<h2></h2> 
<br/>
  <table width="95%" id="table_b1" cellspacing="0" cellpadding="0"  class="table_b">
    <tbody class="detailItemBody" id="detailItemBody1"> 
   <tr class="b_darkblue">
   
    <td>序号</td>
    <td width="60">处罚种类</td>
    <td width="50">裁量基准</td>
    <td>上限数值-单位</td>
    <td>下限数值-单位</td>
    <td >基数名称</td>
    <td>基数倍数上限</td>
    <td>基数倍数下限</td>
    <td>基数单位</td>
    <td>备&nbsp;注</td>
   </tr>
   <%
   		int num =1;
    %>
  <%
			List list2  = (List)request.getAttribute("list2");
			if(list2.size() > 0){
				for(int j = 0; j < list2.size(); j++){
					PcpunishStandard zycview = (PcpunishStandard) list2.get(j);
			
		%>
	<tr>
	<td><%=num++%></td>
	<td>
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
					System.out.println(seqtype.getStandardNo().equals(zycview.getPunishtype())+seqtype.getStandardNo() +"------"+ zycview.getPunishtype()+"===="+seqtype.getPunishfactgrade());
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
	 <td>
	 	<input type="text" value="<%=(zycview.getRemark())==null?"":zycview.getRemark()%>" size="5" name = "SEQ_REMARK" class="byte" readonly="readonly">
	 </td>
	</tr>
    <%}} %>
	</tbody>
</table> 
       <table align="center">
       		<tr>
       			<td><input type="button" class="btn" onclick="window.close();"
			value="关闭" />
		     </td>
       		</tr>
       </table>
	</form>
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
        
    	//window.close();
    	
    	
    	if(document.forms[0].action.value!='viewzyc'){
    	   window.close();
    	}else{
    	   var DG = frameElement.lhgDG; 
           DG.cancel(); 
        }
    }
  </script>
</html>
