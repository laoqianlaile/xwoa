<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><meta name="decorator" content='${LAYOUT}'/>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
    <title>操作方法定义，要和业务中的Action中的方法对应</title>
   <script src="${pageContext.request.contextPath}/scripts/optInfo.js"></script>
        <script>
          var module;//发布出去供全局对象使用
          $(function(){
             module= $.require("menuDependies");
             module.init();
         });
        </script>
  </head>
  
  <body> 
    <fieldset  class="form">
        <legend style="border-bottom-width:1px">
			操作按钮列表  <a style="float:right;margin-right:10" href="javascript:void(0);" onclick = "module.popEditorWin('按钮信息新增','${pageContext.request.contextPath}/sys/optDef!built.do?optid=${optid}')">新增</a>
        </legend>
        <div style="max-height:250px;overflow-y:auto">
            <ec:table items="optdefs" action="sys/optDef!list.do" retrieveRowsCallback="limit"
                sortable="false"  var="optdef" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" rowsDisplayed="5">
                    <ec:row>
                        <ec:column property="optcode" title="操作代码" style="text-align:left"  />
                        <ec:column property="optmethod" title="操作方法" sortable="false" style="text-align:left" />
                        <ec:column property="optname" title="方法名称" sortable="false" style="text-align:left" />
                        <ec:column property="optdesc" title="方法说明" sortable="false"	style="text-align:left" />
                        <ec:column property="optdefineopt" title="操作" sortable="false" >
                            <a href="javascript:void(0);" onclick="module.popEditorWin('按钮信息编辑','${pageContext.request.contextPath}/sys/optDef!edit.do?optcode=${optdef.optcode}')">
                                编辑
                            </a>
                            <a href='${pageContext.request.contextPath}/sys/optDef!delete.do?optcode=${optdef.optcode}'
                                onclick='return confirm("是否删除操作：${optdef.optname}?");' >
                                删除
                            </a>
                        </ec:column>
                    </ec:row>
                </ec:table>	
        </div>
      </fieldset>	
		
  </body>
</html>
