<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><meta name="decorator" content='${LAYOUT}'/>
    <meta name="decorator" content='${LAYOUT}'/>
      
    <title>操作业务定义 </title>
   <script src="${pageContext.request.contextPath}/scripts/optInfo.js"></script>
    <script type="text/javascript"> 
        //声明一个对象的引用变量可能子页面调用
        var module;
       $(function(){
            module = $.require("menuMgr");  
            module.init({ctx:"${pageContext.request.contextPath}",treeEleId:"menuTree",treeData:$.parseJSON('${menusJsonStr}')});
       });
    </script>
   <style type="text/css">
     ul.ztree{border:none;background:none}
   </style>
  </head>
  
  <body>
   <div id="menuOpt" style="height:30px;border:1px solid #ccc">
       <a href="javascript:void(0);" id="btnAddParentMenu">添加父类功能</a> 
       <a href="javascript:void(0);" id="btnAddChildMenu">添加下层功能</a>
       <a href="javascript:void(0);" id="btnDeleteMenu">删除功能</a>
   </div>
    <div id="menuContent"> 
     <!-- 左侧菜单列表 -->
     <div id="menuList" style="float:left;width:20%;height:100%;overflow-y:scroll;border:1px solid #ccc;border-top:none">
         <span style="display:inline-block;width:100%;border-bottom:1px solid #ccc;font-weight: bolder;padding:5px 0;color:#0c6da0;">&nbsp;&nbsp;&nbsp;&nbsp;系统所有业务</span>
        <ul id="menuTree" class="ztree"></ul>
     </div>
     <!-- 菜单项明细 -->
     <div id="menuItemDetail" style="float:left;width:79.5%;height:100%;">
       <iframe id="menuItemDetailIframe" src="" width="100%" height="100%"
			frameborder="0" scrolling="auto"></iframe>
     </div>
     </div> 
  </body>
</html>
