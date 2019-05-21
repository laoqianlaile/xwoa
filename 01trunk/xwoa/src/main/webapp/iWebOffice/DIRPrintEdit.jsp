<%@page import="DBstep.iMsgServer2000"%>
<%@page import="com.goldgrid.weboffice.iDBManager2000"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,java.util.Date,javax.servlet.*,javax.servlet.http.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印目录</title>
<%
  ResultSet result=null;
  String mSubject=null;
  String mStatus=null;
  String mAuthor=null;
  String mFileName=null;
  String mFileDate=null;
  String mHTMLPath="";

  String mDisabled="";
  String mDisabledSave="";
  String mWord="";
  String mExcel="";

  //流程步骤、或者模块缩写
  String flowStep = request.getParameter("flowStep");
  flowStep = (flowStep == null ? "" : flowStep);
  
  String printType = request.getParameter("printType");
  printType = (printType == null ? "" : printType);
  
  String rowIndex = request.getParameter("rowIndex");
  
  //String usercode=request.getParameter("usercode");
  //String mUserName=new String(request.getParameter("UserName").getBytes("8859_1"));
  
  //自动获取OfficeServer和OCX文件完整URL路径
  String mHttpUrlName=request.getRequestURI();
  String mScriptName=request.getServletPath();
  String mServerName="/OfficeServer?flowStep="+flowStep;//+"&usercode="+usercode;

  String mServerUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/"+mServerName;//取得OfficeServer文件的完整URL
  String mHttpUrl="http://"+request.getServerName()+":"+request.getServerPort()+mHttpUrlName.substring(0,mHttpUrlName.lastIndexOf(mScriptName))+"/";
  String mRecordID=request.getParameter("RecordID");
  String mTemplate=request.getParameter("Template");
  //强行指定.doc类型String mFileType=request.getParameter("FileType");
  String mFileType=".doc";
  String mEditType=request.getParameter("EditType");
  String mShowType=request.getParameter("ShowType");

  //设置编号初始值
  if (mRecordID==null){
     mRecordID="";
  }

  //设置编辑状态初始值
  if (mEditType==null || mEditType==""){
    mEditType="1,1";
  }

  //设置显示模式初始值
  if (mShowType==null || mShowType==""){
    mShowType="1";
  }

  //设置文档类型初始值
  if (mFileType==null || mFileType==""){
    mFileType=".doc";
  }

  //设置模板初始值
  if (mTemplate==null){
    mTemplate="";
  }

  String fileName = "";
  //打开数据库
  iDBManager2000 DbaObj=new iDBManager2000();
  if (DbaObj.OpenConnection()){
    try{
      String TemplateSql = "SELECT FILETYPE,DESCRIPT FROM Template_File WHERE RECORDID='" + mTemplate + "'";
      result = DbaObj.ExecuteQuery(TemplateSql);
      if (result.next()) {
      		fileName = result.getString("DESCRIPT") + result.getString("FILETYPE");
      }
     
      result.close();
    }
    catch(SQLException e){
      System.out.println(e.toString());
    }
    DbaObj.CloseConnection() ;
  }

  if (mEditType=="0,0"){
    mDisabled="disabled";
    mDisabledSave="disabled";
  }
  else{
    mDisabled="";
  }

  mFileName=mRecordID + mFileType;  //取得完整的文档名称
  if (mFileType.equalsIgnoreCase(".doc") || mFileType.equalsIgnoreCase(".wps")){
    mWord="";
    mExcel="disabled";
  }
  else if (mFileType==".xls"){
    mWord="disabled";
    mExcel="";
  }
  else{
    mDisabled="disabled";
  }
  
%>
<script type="text/javascript">
	var step = '<%=flowStep%>';
	var printType='<%=printType%>';
	
	//父页面文档对象
	var parentDocument = window.opener.document;
	
	//作用：保存文档
	function SaveDocument(){
	  //webform.DIRWebOffice.WebSetMsgByName("MyDefine1","自定义变量值1");  //设置变量MyDefine1="自定义变量值1"，变量可以设置多个  在WebSave()时，一起提交到OfficeServer中
	  if (!webform.DIRWebOffice.WebSaveLocal()){    //交互OfficeServer的OPTION="SAVEFILE"  注：WebSave()是保存复合格式文件，包括OFFICE内容和手写批注文档；如只保存成OFFICE文档格式，那么就设WebSave(true)
	     StatusMsg(webform.DIRWebOffice.Status);
	     return false;
	  }else{
	     StatusMsg(webform.DIRWebOffice.Status);
	     return true;
	  }
	}

	//作用：载入iWebOffice
	function Load(){
		var userName = '${SPRING_SECURITY_CONTEXT.authentication.principal.username}';
		var userid = '${SPRING_SECURITY_CONTEXT.authentication.principal.usercode}';
		var webURL = "<%=mServerUrl%>" + "&usercode="+userid;
		var webObj = webform.DIRWebOffice;
		try{
		  	//以下属性必须设置，实始化iWebOffice
		    webObj.WebUrl=webURL;             //WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
		    webObj.RecordID="<%=mRecordID%>";            //RecordID:本文档记录编号
		    webObj.Template="<%=mTemplate%>";            //Template:模板编号
		    webObj.FileName="<%=fileName%>";            //FileName:文档名称
		    webObj.FileType="<%=mFileType%>";            //FileType:文档类型  .doc  .xls  .wps
		    webObj.UserName=userName;            //UserName:操作用户名，痕迹保留需要
		    webObj.EditType="<%=mEditType%>";            //EditType:编辑类型  方式一、方式二  <参考技术文档>
		                                                          //第一位可以为0,1,2,3 其中:0不可编辑;1可以编辑,无痕迹;2可以编辑,有痕迹,不能修订;3可以编辑,有痕迹,能修订；
		                                                          //第二位可以为0,1 其中:0不可批注,1可以批注。可以参考iWebOffice2009的EditType属性，详细参考技术白皮书
			webObj.MaxFileSize = 4 * 1024;               //最大的文档大小控制，默认是8M，现在设置成4M。
			webObj.Language="CH";                        //Language:多语言支持显示选择   CH简体 TW繁体 EN英文
			//webObj.ShowWindow = true;                  //控制显示打开或保存文档的进度窗口，默认不显示
			webObj.PenColor="#FF0000";                   //PenColor:默认批注颜色
			webObj.PenWidth="1";                         //PenWidth:默认批注笔宽
			webObj.Print="1";                            //Print:默认是否可以打印:1可以打印批注,0不可以打印批注
			webObj.ShowToolBar="1";                      //ShowToolBar:是否显示工具栏:1显示,0不显示
			webObj.ShowMenu="1";                         //控制整体菜单显示
			webObj.DisableMenu("宏(&M);选项(&O)...");    //禁止某个（些）菜单项
			
			webObj.WebOpen();                            //打开该文档    交互OfficeServer  调出文档OPTION="LOADFILE"    调出模板OPTION="LOADTEMPLATE"     <参考技术文档>
			  
			//穆欣 2012-7-8 设置拟文单界面所需要的控制
			//webObj.ShowToolBar="0";//不显示自定义工具栏
			
			if(step == "FW_DIR" && printType=='FW' ){//发文目录
				FWDIRPrint(webObj);
			}else if(step == "FW_DIR" && printType=='GK' ){//公开查询目录
				GKDIRPrint(webObj);
			}else if(step == "SW_DIR" ){//收文目录
				SWDIRPrint(webObj);
			}else if(step == "SP_DIR" ){//收文目录
				SPDIRPrint(webObj);
			}else if(step == "BJ_DIR" ){//收文目录
				BJDIRPrint(webObj);
			}else if(step == "BWD_BAT" ){//批量打印
				BWDBatPrint(webObj);
			}else if(step == "BWD" ){//单行打印
				BWDPrint(webObj,<%=rowIndex%>);
			}
			
			webObj.VisibleTools("重新批注,新建文件,打开文件,保存文件,文字批注,手写批注,文档清稿",false);
			webObj.WebObject.ShowRevisions=0;
			webObj.AppendTools("94","打印文档",99);
			webObj.AppendTools("95","退出编辑",99);
			//webObj.AppendTools("96","本地保存",99);
			
			if(step != null && step != "VIEW"){
				webObj.AppendTools("97","保存文档",99);
			}
			if($.browser.msie && $.browser.version==10){
				   $("#WebOffice").height(window.innerHeight);				
				}
			  
		}catch(e){
		  alert(e.description);                                   //显示出错误信息
		}
	}

	function _getParentElementValue(id){
		if(parentDocument.getElementById(id) == undefined){
			return '';
		}
		
		return parentDocument.getElementById(id).value;
	}
	
	function _getCellValue(rowObj,cellIndex){
		return rowObj.cells[cellIndex].innerText;
	}
	
	function _getRowCellValue(tableObj,rowIndex,cellIndex){
		return tableObj.rows[rowIndex].cells[cellIndex].innerText;
	}
	
	//收文目录打印
	function SWDIRPrint(webObj){
		var tableObj = parentDocument.getElementById("ec_table");//EC表格对象
		var rowObj = tableObj.rows;//行
		try{
			if(webObj.WebObject.Tables.Count>0 && rowObj.length > 3 ){ 
				//alert(rowObj.length);
				for(var i=2; i< rowObj.length - 1 ; i++){ 
					webObj.WebObject.Tables.Item(1).Cell(i,1).Range.Text=_getRowCellValue(tableObj,i,0);//收文日期 
					webObj.WebObject.Tables.Item(1).Cell(i,2).Range.Text=_getRowCellValue(tableObj,i,1);//来文单位 
					webObj.WebObject.Tables.Item(1).Cell(i,3).Range.Text=_getRowCellValue(tableObj,i,2);//来文字号 
					webObj.WebObject.Tables.Item(1).Cell(i,4).Range.Text=_getRowCellValue(tableObj,i,3);// 文件标题
					webObj.WebObject.Tables.Item(1).Cell(i,5).Range.Text=_getRowCellValue(tableObj,i,4);//分办意见
					webObj.WebObject.Tables.Item(1).Cell(i,6).Range.Text=_getRowCellValue(tableObj,i,5);//编号
					//webObj.WebObject.Tables.Item(1).Cell(i,7).Range.Text=_getRowCellValue(tableObj,i,6);; 
					//webObj.WebObject.Tables.Item(1).Cell(i,8).Range.Text=_getRowCellValue(tableObj,i,7);//阅示主任 
					if(i < rowObj.length - 2){ 
						webObj.WebObject.Tables.Item(1).Rows.Add(); 
					}
				}
				webObj.WebObject.Tables.Item(1).Borders.OutsideLineStyle=1; 
				webObj.WebObject.Tables.Item(1).Borders.InsideLineStyle=1; 
				
				alert("完成"); 
			} 
			
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
	}
	
	function BJDIRPrint(webObj){
		var tableObj = parentDocument.getElementById("ec_table");//EC表格对象
		var rowObj = tableObj.rows;//行
		try{
			if(webObj.WebObject.Tables.Count>0 && rowObj.length > 3 ){ 
				//alert(rowObj.length);
				for(var i=2; i< rowObj.length - 1 ; i++){ 
					webObj.WebObject.Tables.Item(1).Cell(i,1).Range.Text=_getRowCellValue(tableObj,i,0);//收文日期 
					webObj.WebObject.Tables.Item(1).Cell(i,2).Range.Text=_getRowCellValue(tableObj,i,1);//来文单位 
					webObj.WebObject.Tables.Item(1).Cell(i,3).Range.Text=_getRowCellValue(tableObj,i,2);//来文字号 
					webObj.WebObject.Tables.Item(1).Cell(i,4).Range.Text=_getRowCellValue(tableObj,i,3);// 文件标题
					webObj.WebObject.Tables.Item(1).Cell(i,5).Range.Text=_getRowCellValue(tableObj,i,4);//分办意见
					webObj.WebObject.Tables.Item(1).Cell(i,6).Range.Text=_getRowCellValue(tableObj,i,5);//编号
					webObj.WebObject.Tables.Item(1).Cell(i,7).Range.Text=_getRowCellValue(tableObj,i,6);; 
					//webObj.WebObject.Tables.Item(1).Cell(i,8).Range.Text=_getRowCellValue(tableObj,i,7);//阅示主任 
					if(i < rowObj.length - 2){ 
						webObj.WebObject.Tables.Item(1).Rows.Add(); 
					}
				}
				webObj.WebObject.Tables.Item(1).Borders.OutsideLineStyle=1; 
				webObj.WebObject.Tables.Item(1).Borders.InsideLineStyle=1; 
				
				alert("完成"); 
			} 
			
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
	}
	
	function SPDIRPrint(webObj){
		var tableObj = parentDocument.getElementById("ec_table");//EC表格对象
		var rowObj = tableObj.rows;//行
		try{
			if(webObj.WebObject.Tables.Count>0 && rowObj.length > 3 ){ 
				//alert(rowObj.length);
				for(var i=2; i< rowObj.length - 1 ; i++){ 
					webObj.WebObject.Tables.Item(1).Cell(i,1).Range.Text=_getRowCellValue(tableObj,i,0);//收文日期 
					webObj.WebObject.Tables.Item(1).Cell(i,2).Range.Text=_getRowCellValue(tableObj,i,1);//来文单位 
					webObj.WebObject.Tables.Item(1).Cell(i,3).Range.Text=_getRowCellValue(tableObj,i,2);//来文字号 
					webObj.WebObject.Tables.Item(1).Cell(i,4).Range.Text=_getRowCellValue(tableObj,i,3);// 文件标题
					webObj.WebObject.Tables.Item(1).Cell(i,5).Range.Text=_getRowCellValue(tableObj,i,4);//分办意见
					webObj.WebObject.Tables.Item(1).Cell(i,6).Range.Text=_getRowCellValue(tableObj,i,5);//编号
					webObj.WebObject.Tables.Item(1).Cell(i,7).Range.Text=_getRowCellValue(tableObj,i,6);; 
					webObj.WebObject.Tables.Item(1).Cell(i,8).Range.Text=_getRowCellValue(tableObj,i,7);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,9).Range.Text=_getRowCellValue(tableObj,i,8);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,10).Range.Text=_getRowCellValue(tableObj,i,9);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,11).Range.Text=_getRowCellValue(tableObj,i,10);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,12).Range.Text=_getRowCellValue(tableObj,i,11);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,13).Range.Text=_getRowCellValue(tableObj,i,12);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,14).Range.Text=_getRowCellValue(tableObj,i,13);//阅示主任 
					webObj.WebObject.Tables.Item(1).Cell(i,15).Range.Text=_getRowCellValue(tableObj,i,14);//阅示主任 
					if(i < rowObj.length - 2){ 
						webObj.WebObject.Tables.Item(1).Rows.Add(); 
					}
				}
				webObj.WebObject.Tables.Item(1).Borders.OutsideLineStyle=1; 
				webObj.WebObject.Tables.Item(1).Borders.InsideLineStyle=1; 
				
				alert("完成"); 
			} 
			
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
	}
	
	//发文目录打印
	function FWDIRPrint(webObj){
		var tableObj = parentDocument.getElementById("ec_table");//EC表格对象
		var rowObj = tableObj.rows;//行
		try{
			if(webObj.WebObject.Tables.Count>0 && rowObj.length > 3){ 
				for(var i=2; i< rowObj.length - 1 ; i++){ 
					webObj.WebObject.Tables.Item(1).Cell(i,1).Range.Text=_getRowCellValue(tableObj,i,0);//拟文日期（拟文步骤的提交时间） 
					webObj.WebObject.Tables.Item(1).Cell(i,2).Range.Text=_getRowCellValue(tableObj,i,1);//发文号（在置文号步骤完成后才填写） 
					webObj.WebObject.Tables.Item(1).Cell(i,3).Range.Text=_getRowCellValue(tableObj,i,2);//文件标题 
					webObj.WebObject.Tables.Item(1).Cell(i,4).Range.Text=_getRowCellValue(tableObj,i,3);//承办处室（完整流程的主办处室，主动发文的发文处室） 
					webObj.WebObject.Tables.Item(1).Cell(i,5).Range.Text=_getRowCellValue(tableObj,i,6);//签发人（在字号问好步骤完成后，填写最后一个签发的主任） 
					webObj.WebObject.Tables.Item(1).Cell(i,6).Range.Text=_getRowCellValue(tableObj,i,9);//主送单位 
					if(i < rowObj.length - 2){ 
						webObj.WebObject.Tables.Item(1).Rows.Add(); 
					} 
				} 
				webObj.WebObject.Tables.Item(1).Borders.OutsideLineStyle=1; 
				webObj.WebObject.Tables.Item(1).Borders.InsideLineStyle=1; 
				alert("完成"); 
			} 
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
	}
	
	//公开查询目录打印
	function GKDIRPrint(webObj){
		var tableObj = parentDocument.getElementById("ec_table");//EC表格对象
		var rowObj = tableObj.rows;//行
		try{
			if(webObj.WebObject.Tables.Count>0 && rowObj.length > 3){ 
				for(var i=2; i< rowObj.length - 1 ; i++){ 
					webObj.WebObject.Tables.Item(1).Cell(i,1).Range.Text=_getRowCellValue(tableObj,i,21);//拟文日期（拟文步骤的提交时间） 
					webObj.WebObject.Tables.Item(1).Cell(i,2).Range.Text=_getRowCellValue(tableObj,i,1);//发文号（在置文号步骤完成后才填写） 
					webObj.WebObject.Tables.Item(1).Cell(i,3).Range.Text=_getRowCellValue(tableObj,i,2);//文件标题 
					webObj.WebObject.Tables.Item(1).Cell(i,4).Range.Text=_getRowCellValue(tableObj,i,11);//承办处室（完整流程的主办处室，主动发文的发文处室） 
					webObj.WebObject.Tables.Item(1).Cell(i,5).Range.Text=_getRowCellValue(tableObj,i,15);//签发人（在字号问好步骤完成后，填写最后一个签发的主任） 
					webObj.WebObject.Tables.Item(1).Cell(i,6).Range.Text=_getRowCellValue(tableObj,i,16);//主送单位 
					if(i < rowObj.length - 2){ 
						webObj.WebObject.Tables.Item(1).Rows.Add(); 
					} 
				} 
				webObj.WebObject.Tables.Item(1).Borders.OutsideLineStyle=1; 
				webObj.WebObject.Tables.Item(1).Borders.InsideLineStyle=1; 
				alert("完成"); 
			} 
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
	}
	
	//打印单个办文单
	function BWDPrint(webObj,rowIndex){
		
		if(rowIndex == null || Number(rowIndex) < 1){
			alert("Row Index error!");
			return;
		}
		
		var tableObj = parentDocument.getElementById("ec_table");//EC表格对象
		var rowObj  = tableObj.rows[rowIndex];
		try{
			if(webObj.WebObject.Bookmarks.Exists("AllContent")){ 
				webObj.WebObject.Bookmarks.Item("AllContent").Range.Copy();//将一份办文单拷贝到剪贴板 
				webObj.WebObject.Tables.Item(1).Cell(1,1).Range.Text=rowObj.cells[1].innerText;//收文号 
				webObj.WebObject.Tables.Item(1).Cell(2,2).Range.Text=rowObj.cells[2].innerText;//原文号 
				webObj.WebObject.Tables.Item(1).Cell(2,4).Range.Text=rowObj.cells[3].innerText;//原文日期 
				webObj.WebObject.Tables.Item(1).Cell(3,2).Range.Text=rowObj.cells[4].innerText;//来文单位 
				webObj.WebObject.Tables.Item(1).Cell(3,4).Range.Text=rowObj.cells[5].innerText;//收文日期 
				//word2000下对表格中合并单元格之后的坐标引用和2007不太一样
				try{
					webObj.WebObject.Tables.Item(1).Cell(4,4).Range.Text=rowObj.cells[6].innerText.replace("省能源局综合规划处","省能源局");//主办部门
				}catch(e){
					webObj.WebObject.Tables.Item(1).Cell(4,2).Range.Text=rowObj.cells[6].innerText.replace("省能源局综合规划处","省能源局");//主办部门，2003以前版本的引用方式
				}
				webObj.WebObject.Tables.Item(1).Cell(5,2).Range.Text=rowObj.cells[7].innerText;//文件标题 
				webObj.WebObject.Tables.Item(1).Cell(6,2).Range.Text='\n'+rowObj.cells[8].innerText.replace("省能源局综合规划处","省能源局");//批分意见 
				try{
					webObj.WebObject.Tables.Item(1).Cell(7,2).Range.Text=rowObj.cells[9].innerText+' '+rowObj.cells[10].innerText;//批分人+批分时间 
				}catch(e){
					webObj.WebObject.Tables.Item(1).Cell(7,1).Range.Text=rowObj.cells[9].innerText+' '+rowObj.cells[10].innerText;//批分人+批分时间，2003以前版本的引用方式
				}
				webObj.WebObject.Tables.Item(1).Cell(10,2).Range.Text=rowObj.cells[12].innerText;//备注 
			}
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
		parentDocument.getElementById("hidDiv").innerHTML += '<input type="hidden" name="djId" value="'+rowObj.cells[11].innerText+'">';
		parentDocument.updtForm.submit();
	}
	
	//批量打印办文单
	function BWDBatPrint(webObj){
		var parentDocument = window.opener.document;
		var checkedCount = 0;
		try{
			
			var chks = parentDocument.getElementsByName("chk");
			
			
			for(var j = 0; j < chks.length; j ++){
				if(chks[j].checked){
					checkedCount++;
				}
			}
			
			if(webObj.WebObject.Bookmarks.Exists("AllContent")){ 
				webObj.WebObject.Bookmarks.Item("AllContent").Range.Copy();//将一份办文单拷贝到剪贴板 
				
				//批量打印的办文单数量 
				var n = Number(1);
				
				for(var i = 0; i < chks.length; i ++){
					if(chks[i].checked){
						parentDocument.getElementById("hidDiv").innerHTML += '<input type="hidden" name="djId" value="'+chks[i].value+'">';
						var rowObj = chks[i].parentNode.parentNode;
						
						webObj.WebObject.Tables.Item(n).Cell(1,1).Range.Text=rowObj.cells[1].innerText;//收文号 
						webObj.WebObject.Tables.Item(n).Cell(2,2).Range.Text=rowObj.cells[2].innerText;//原文号 
						webObj.WebObject.Tables.Item(n).Cell(2,4).Range.Text=rowObj.cells[3].innerText;//原文日期 
						webObj.WebObject.Tables.Item(n).Cell(3,2).Range.Text=rowObj.cells[4].innerText;//来文单位 
						webObj.WebObject.Tables.Item(n).Cell(3,4).Range.Text=rowObj.cells[5].innerText;//收文日期 
						//word2000下对表格中合并单元格之后的坐标引用和2007不太一样
						try{
							webObj.WebObject.Tables.Item(n).Cell(4,4).Range.Text=rowObj.cells[6].innerText.replace("省能源局综合规划处","省能源局");//主办部门
						}catch(e){
							webObj.WebObject.Tables.Item(n).Cell(4,2).Range.Text=rowObj.cells[6].innerText.replace("省能源局综合规划处","省能源局");//主办部门，2003以前版本的引用方式
						}
						webObj.WebObject.Tables.Item(n).Cell(5,2).Range.Text=rowObj.cells[7].innerText;//文件标题
						webObj.WebObject.Tables.Item(n).Cell(6,2).Range.Text='\n'+rowObj.cells[8].innerText.replace("省能源局综合规划处","省能源局");//批分意见 
						try{
							webObj.WebObject.Tables.Item(n).Cell(7,2).Range.Text=rowObj.cells[9].innerText+' '+rowObj.cells[10].innerText;//批分人+批分时间 
						}catch(e){
							webObj.WebObject.Tables.Item(n).Cell(7,1).Range.Text=rowObj.cells[9].innerText+' '+rowObj.cells[10].innerText;//批分人+批分时间，2003以前版本的引用方式
						} 
						webObj.WebObject.Tables.Item(n).Cell(10,2).Range.Text=rowObj.cells[12].innerText;//备注 
						if(n < checkedCount){ 
							webObj.WebObject.Application.Selection.EndKey(6,0);//光标移动到最后 
							webObj.WebObject.Application.Selection.InsertBreak(7);//插入分页符 
							webObj.WebObject.Application.Selection.Paste();//粘贴剪贴板中的空白办文单 
						} 
						n++ ;
					}
				} 
			} 
		}catch(e){ 
			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description); 
		} 
		//alert(document.getElementById("hidDiv").innerHTML);
		//parentDocument.updtForm.target="frame_save";
		if(checkedCount >= 1){
			parentDocument.updtForm.submit();
		}
	}

//作用：显示操作状态
function StatusMsg(mString){
	//alert(mString);
  //webform.StatusBar.value=mString;
}

//作用：退出iWebOffice
function UnLoad(){
	try{
	  webform.DIRWebOffice.WebClose();
	  }catch(e){
	  alert(e.description);
	}
}
</script>
<script language=javascript for="DIRWebOffice" event=OnToolsClick(vIndex,vCaption)>
		var webObj = webform.DIRWebOffice;
		 if (vIndex==95){  
				window.close();     //退出文档
			}else if (vIndex==94){  
				webObj.WebOpenPrint();       //打印文档
			}else if (vIndex==97){ //保存提交
				if(SaveDocument()){
					document.webform.submit();   
				}
			}
</script>
</head>
<body bgcolor="#ffffff" onload="Load()" onunload="UnLoad()">
<form name="webform" method="post" action="javascript:void(0);" target="_self">
    <input type="hidden" name="RecordID" value="<%=mRecordID%>">
    <input type="hidden" name="Template" value="<%=mTemplate%>">
    <input type="hidden" name="FileType" value="<%=mFileType%>">
    <input type="hidden" name="EditType" value="<%=mEditType%>">
    <input type="hidden" name="Subject" value="">
    <input type="hidden" name="Author" value="${SPRING_SECURITY_CONTEXT.authentication.principal.usercode}">
    <input type="hidden" name="FileDate" value="<%=mFileDate%>">
<table border=0 cellspacing='0' cellpadding='0' width=100% height="1536px;" align=center class=TBStyle>
			<!--<input type=button onClick="SaveDocument();" value="保存文档">
	&nbsp;&nbsp;<input type=button onClick="window.close();" value="退出文档">
      --><td height="100%" colspan="2" rowspan="12" align="right" valign="top" class="TDStyle" hegith="90%">
          <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
            <tr>
              <td bgcolor="menu" height="98%" valign="top">
	              <OBJECT name="DIRWebOffice" id="WebOffice"
					classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499"
					codebase="iWebOffice2009.cab#version=10,4,6,0"
					  width="100%"  height="100%">
				</OBJECT>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </table>
</form>
<!--
<form id="updtForm" name="updtForm" action="<%=request.getContextPath()%>/yxxk/vSwSearch!savePrintSign.do" target="frame_save">
	<div id="hidDiv">
	</div>
</form>
     <iframe width="0px" height="0px" frameborder="no" scrolling="no" src="" name="frame_save"></iframe>
-->
</body>
</html>