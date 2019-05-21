<%@ page contentType="text/html;charset=UTF-8"%>
<html>

<head>

</head>
<body>
	<div id="mydiv1">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr height="22">
					
					<td>
						需要导出的申报数据一共有 ${needUploadArchives}  条;
						需要导出的用户一共有 ${needUploadUsers}  位。
					</td>			
					
				</tr>
			</table>
	</div>
</body>


<script type="text/javascript">  

var xmlhttp1;  

function startrefresh(){  
	
xmlhttp1=new XMLHttpRequest();  

xmlhttp1.open("POST","<%=request.getContextPath()%>/dispatchdoc/dataChange.do?acType=auto",true);  

xmlhttp1.setRequestHeader("Content-type","application/x-www-form-urlencoded");  

xmlhttp1.send("method:view1=123");  //--需要传输参数时增加

xmlhttp1.onreadystatechange = function(){  

   if(xmlhttp1.readyState == 4)  

        if(xmlhttp1.status == 200)  
//         	alert("startrefresh :"+xmlhttp1.responseText);
            document.getElementById("mydiv1").innerHTML=xmlhttp1.responseText;  
   
 }  

 }  

setInterval('startrefresh()',3000);
</script>


</html>