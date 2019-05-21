<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>

<head>

</head>
<body>
	<div id="mydiv2" >
			<table id="ec_t_expdata" border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
				<thead>
					<tr>
					<td width="50%" class="tableHeader">导出时间</td>   
					<td width="20%" class="tableHeader">导出人</td>  
					<td width="30%" class="tableHeader">下载</td>  
					</tr>  
				</thead>
				
				<tbody class="tableBody" >
				<c:set value="odd" var="rownum" />
				
				<c:forEach var="expfile" items="${expfiles}">    
		
					<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
						<td align="center"><fmt:formatDate value ='${expfile.recordDate}' type="both" dateStyle="long" timeStyle="long" /></td>   
						<td align="center"><c:out value="${cp:MAPVALUE('userCode',expfile.recorder)}"/></td>   
						<td align="center">
							<c:if test="${expfile.inDb eq '0'}" >
								正在导出......
							</c:if>
							<c:if test="${expfile.inDb ne '0'}" >
								<a href='dataChange!downloadexp.do?fileCode=${expfile.fileCode}'>数据</a>
								<a href='dataChange!downloadexplog.do?fileCode=${expfile.fileCode}'>日志</a>
							</c:if>
						</td>  
					</tr>  
		
		          <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		          
				</c:forEach> 
				</tbody>        
			</table>
		</div>		
</body>


<script type="text/javascript">  

var xmlhttp2;  

function startrefresh2(){  
	
xmlhttp2=new XMLHttpRequest();  

xmlhttp2.open("POST","<%=request.getContextPath()%>/dispatchdoc/dataChange.do?acType=auto",true);  

xmlhttp2.setRequestHeader("Content-type","application/x-www-form-urlencoded");  

xmlhttp2.send("method:view2=123");  //--需要传输参数时增加

xmlhttp2.onreadystatechange = function(){  

   if(xmlhttp2.readyState == 4)  

        if(xmlhttp2.status == 200)  
        	
            document.getElementById("mydiv2").innerHTML=xmlhttp2.responseText;  
   
 }  

 }  

setInterval('startrefresh2()',3000);
</script>


</html>