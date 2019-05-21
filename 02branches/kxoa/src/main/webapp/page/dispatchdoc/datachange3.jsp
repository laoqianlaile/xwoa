<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>

<head>

</head>
<body>
	<div id="mydiv3" >
			<table id="ec_t_impdata" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
		
				<thead>
					<tr>
					<td width="50%" class="tableHeader">上传时间</td>   
					<td width="20%" class="tableHeader">上传人员</td>  
					<td width="30%" class="tableHeader">操作</td>  
					</tr>  
				</thead>
				
				<tbody class="tableBody" >
				<c:set value="odd" var="rownum" />
				
				<c:forEach var="impfile" items="${impfiles}">    
					<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
						<td align="center"><fmt:formatDate value ='${impfile.recordDate}' type="both" dateStyle="long" timeStyle="long"/></td>   
						<td align="center"><c:out value="${cp:MAPVALUE('userCode',impfile.recorder)}"/></td>   
						<td align="center">
							<c:if test="${expfile.inDb eq '0'}" >
								正在导入......
							</c:if>
							<c:if test="${expfile.inDb ne '0'}" >
								<a href='dataChange!downloadexp.do?fileCode=${impfile.fileCode}'>数据</a>
								<a href='dataChange!downloadexplog.do?fileCode=${impfile.fileCode}'>日志</a>
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

var xmlhttp3;  

function startrefresh3(){  
	
xmlhttp3=new XMLHttpRequest();  

xmlhttp3.open("POST","<%=request.getContextPath()%>/dispatchdoc/dataChange.do?acType=auto",true);  

xmlhttp3.setRequestHeader("Content-type","application/x-www-form-urlencoded");  

xmlhttp3.send("method:view3=123");  //--需要传输参数时增加

xmlhttp3.onreadystatechange = function(){  

   if(xmlhttp3.readyState == 4)  

        if(xmlhttp3.status == 200)  
//         	alert("startrefresh :"+xmlhttp3.responseText);
            document.getElementById("mydiv3").innerHTML=xmlhttp3.responseText;  
   
 }  

 }  

setInterval('startrefresh3()',3000);
</script>


</html>