<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
	<script language="javascript">
		function Seal() 
		{
			if (pdfFile.value == "") {
				alert("请选择PDF文件");
				return;
			}
			var lID = 0;
			var nNum = 0;
			var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
			
			/*
			<!-- 应用系统识别码，公章系统分配 -->
			<AppCode>t8qR98kG5v</AppCode>
			<!-- 应用系统名称 -->
			<AppName>OA系统</AppName>
			<!-- 操作人 -->
			<ProcMan>张三</ProcMan>
			<!—公章所属单位，如果是个人章，填个人，服务器端只记录日志 -->
			<StampOwner>单位1</StampOwner>
			<!-- 文件名称 -->
			<FileName>XXXX.pdf</FileName>
			<!-- 标题 -->
			<Title>文件标题</Title>
			*/
			//设置操作者信息
			var lRet = obj.SetSealProcInfo("<AppCode>t8qR98kG5v</AppCode><AppName>OA系统</AppName><ProcMan>张三</ProcMan><StampOwner>单位1</StampOwner><FileName>XXXX.pdf</FileName><Title>文件标题</Title>");
			
			var lRet = obj.PDFVisualSeal(pdfFile.value, pdfFile.value, affixUrl.value, "<DeviceStyle>112</DeviceStyle>", Location.value, Reason.value, Contact.value);
			
			if (lRet != 0)
			{
				alert(obj.GetErrorMsg());
			}

		}
		
		function GetDocID() 
		{
			if (pdfFile.value == "") {
				alert("请选择PDF文件");
				return;
			}
			var lID = 0;
			var nNum = 0;
			var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
			
			var lRet = obj.GetDocID(pdfFile.value);
			if(lRet != "")
				alert(lRet);
			else
				alert(obj.GetErrorMsg());
			
		}
		
		function PrintPDF() 
		{
			if (pdfFile.value == "") {
				alert("请选择PDF文件");
				return;
			}
			var lID = 0;
			var nNum = 0;
			var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
			
			var lRet = obj.PrintPdf(pdfFile.value, UnitName.value, affixUrl.value + "/extend/interfaces/ReceivePrint.aspx");
			if(lRet == 0)
				alert("打印成功");
			else
				alert(obj.GetErrorMsg());
			
		}
		
		function SendPrintPDF() 
		{
			if (pdfFile.value == "") {
				alert("请选择PDF文件");
				return;
			}
			var lID = 0;
			var nNum = 0;
			var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
			
			var lRet = obj.SendPrintPDF(pdfFile.value, bstrUnitName.value, affixUrl.value + "/extend/interfaces/ReceivePrint.aspx");
			if(lRet == 0)
				alert("打印成功");
			else
				alert(obj.GetErrorMsg());
			
		}

function DetachPdf() 
		{
			if (pdfFile.value == "") {
				alert("请选择PDF文件");
				return;
			}
			var lID = 0;
			var nNum = 0;
			var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
			
			var lRet = obj.DetachPdf(pdfFile.value, 1, Color.value);
			if(lRet == 0)
				alert("脱密成功");
			else
				alert(obj.GetErrorMsg());
			
		}
		
		function DetachPdfEx() 
		{
			if (pdfFile.value == "") {
				alert("请选择PDF文件");
				return;
			}
			var lID = 0;
			var nNum = 0;
			var obj = new ActiveXObject("VisualSealStampCom.PDFSeal"); 	
			
			var lRet = obj.DetachPdfEx(pdfFile.value, 1, Color.value, "<NoShowMark>1</NoShowMark>");
			if(lRet == 0)
				alert("脱密成功");
			else
				alert(obj.GetErrorMsg());
			
		}
			</script>

</head>
<body>
	<table>
		<tr><td>请选择PDF文件<input type="file" id="pdfFile" style="width:400"/></td></tr>	
		<tr><td>公章服务器URL<input type="text" id="affixUrl" value="http://172.19.193.10/StampServer" style="width:400"/></td></tr>
		<tr><td>位置<input type="text" id="Location" value="地方" style="width:400"/></td></tr>	
		<tr><td>签章原因<input type="text" id="Reason" value="原因" style="width:400"/></td></tr>	
		<tr><td>联系方式<input type="text" id="Contact" value="联系方式" style="width:400"/></td></tr>		
		<tr>
		<td>
		<input type="button" value="盖章" onclick="Seal()"/>
		<input type="button" value="获取DocID" onclick="GetDocID()"/>
		</td>
		</tr>	
		<tr><td>打印单位<input type="text" id="UnitName" value="北京分公司" style="width:400"/></td></tr>		
		<tr><td><input type="button" value="打印" onclick="PrintPDF()"/><!-- <input type="button" value="发文打印" onclick="SendPrintPDF()"/> --></td></tr>	
		<!-- <tr><td>请选择要脱密成的颜色：
<SELECT id=select name="Color" style="WIDTH: 158px" > 
  <OPTION  value=1> 红色</OPTION>
  <OPTION  value=0> 黑色</OPTION>
</SELECT></td></tr>	
		<tr><td><input type="button" value="脱密" onclick="DetachPdf()"/></td></tr>	
		<tr><td><input type="button" value="没有水印脱密" onclick="DetachPdfEx()"/></td></tr>	 -->
	</table>
</body>
</html>