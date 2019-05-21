<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><meta name="decorator" content='${LAYOUT}'/>
    <meta name="decorator" content='${LAYOUT}'/>
      
    <title>操作业务定义 </title>
   <%-- <script src="${pageContext.request.contextPath}/scripts/optInfo.js"></script> --%>
    <script type="text/javascript"> 
        //声明一个对象的引用变量可能子页面调用
        var module;
       $(function(){
            module = $.require("menuMgr");  
            module.init({ctx:"${pageContext.request.contextPath}",treeEleId:"menuTree",treeData:$.parseJSON('${menusJsonStr}')});
       });
       function onLoadHeight(t){
			var _height=window.frames["menuItemDetailIframe"].document.body.scrollHeight+10;
			if(_height>460){t.height=_height;}
			else{t.height=460;}
		}
    </script>
   <style type="text/css">
     ul.ztree{border:none;background:none}
   </style>
  </head>
  
  <body>
    <div id="menuContent"> 
     <!-- 左侧菜单列表 -->
     <div id="menuList" style="float:left;width:20%;height:600px;overflow-y:scroll;border:1px solid #ccc;border-top:none">
         <span style="display:inline-block;width:100%;border-bottom:1px solid #ccc;font-weight: bolder;padding:5px 0;color:#0c6da0;">&nbsp;&nbsp;&nbsp;&nbsp;系统所有业务</span>
        <ul id="menuTree" class="ztree"></ul>
     </div>
     <!-- 帮助中心列表 -->
     
     <div id="itemFrame" style="float:left;width:79.5%;height:100%;">
       <iframe id="menuItemDetailIframe" src="" onload="onLoadHeight(this);" width="100%"  height="100%"
			frameborder="0" scrolling="auto"></iframe>
     </div>
     </div> 
  </body>
  <script type="text/javascript">
  /**
  * 系统菜单js
  * @author lay
  * @version 1.0
  * @createDate 2015-10-29
  */
  (function($){
       /**
         *  将当前对象指针用变量装起来，防止在用this的时候上下文已经变了
         * @private
         * @author lay
         * @createDate 2015-10-30
         */
      var Global;
      
       /**
         * 上下文路径
         * @private
         * @author lay
         * @createDate 2015-10-29
         */
      var ctx;
      /**
      *  跟节点id固定为0
      */
      var CONSTANT_ROOT_ID = 0;
      /***********************************************************************************************************************************
      *  系统菜单主页
      *  也是菜单的入口
      ************************************************************************************************************************************/
  	function ModuleMain(){
           
            /**
            * 当前操作的菜单节点
            * @public
            * @author lay
            * @createDate 2015-10-29
            */
            this.currMenuNode;

            /**
            *  ztree的句柄
            *  通过$.fn.zTree.init赋值
            * @public
            * @author lay
            * @createDate 2015-10-29
            */
            this.menuTreeHandler;

            /**
            * 初始化对象
            * @public
            * @author lay
            * @createDate 2015-10-29
            */
            this.init = function(param){
               Global = this; 
               ctx = param.ctx;
               resizeLayout();
               renderTree(param.treeEleId,param.treeData);
            };
            
            /**
            *  添加菜单节点
            * @public
            * @author lay
            * @createDate 2015-10-30
            */
            this.addTreeNode = function(node){
               if(!node){
                  return;
               }
               //如果是根节点
               if(node.pId == CONSTANT_ROOT_ID){
                  Global.menuTreeHandler.addNodes(null,node);
               }else{
                  Global.menuTreeHandler.addNodes(Global.currMenuNode,node);
               }
               var ztreeNode = Global.menuTreeHandler.getNodeByParam("id", node.id);
               Global.selectNode(ztreeNode,true);
            };
          
            /**
            * 更新菜单节点名称
            * @public
            * @author lay
            * @createDate 2015-10-30
            */
            this.updateTreeNode = function(nodeName){
               Global.currMenuNode.name = nodeName;
               Global.menuTreeHandler.updateNode(Global.currMenuNode);
            };
          
            /**
             * 选中树节点
             * @public
             * @author lay
             * @createDate 2015-10-29
             * @param node:ztree节点对象
             * @param bLoadRight:是否加载右侧
             */
            this.selectNode = function(node,bLoadRight){
                Global.currMenuNode = node;
               //选中节点
                Global.menuTreeHandler.selectNode(node, false);
                if(bLoadRight){
                    //加载当前节点的明细
                   loadMenuDetail(node.id,"view");
                }
               
            };
          
            /**
             * 布局重新计算，主要计算高度
             * @private
             * @author lay
             * @createDate 2015-10-29
             */
             var resizeLayout = function(){
                 var winHeight = $(window).height();
                 $("#menuContent").height(winHeight-$("#menuOpt").outerHeight(true)-2);
             };
             
            /**
            * 渲染菜单树
            * @private
            * @author lay
            * @createDate 2015-10-29
            * @param treeEleId ztree容器的id
            * @param treeData ztree的原始数据
            */
            var renderTree = function(treeEleId,treeData){
                  var setting = { 
                      data : { simpleData : { enable : true } }, 
                      callback : { onClick : onClickMenuItem} 
                  };
                 Global.menuTreeHandler = $.fn.zTree.init($("#"+treeEleId), setting, treeData);
               
                //初始化第一个节点选中
                if(treeData && treeData.length > 0){
                   var node = Global.menuTreeHandler.getNodeByParam("id", treeData[0].id);
                   Global.selectNode(node,true);
                }

            };
       
          /**
            * ztree 单击回调函数
            * @private
            * @author lay
            * @createDate 2015-10-29
            * @param event 标准的 js event 对象
            * @param treeId 对应 zTree 的 treeId，便于用户操控
            *  @param treeNode 被点击的节点 JSON 数据对象
            */
            var onClickMenuItem = function(event, treeId, treeNode){
                Global.selectNode(treeNode,true);
            };
          
          /**
           * 根据菜单id获取菜单明细
           * @private
           * @author lay
           * @createDate 2015-10-29
           * @param menuId 菜单id
           */
           var loadMenuDetail = function(menuId,type){
        	  var optid=menuId;
        	  if(optid=='YGJG'){
        		  optid='';
        	  }
                var url = "";
                  url = ctx+"/oa/oaHelpinfo!list4Manager.do?s_optid="+optid;
             	  $("#itemFrame").find("iframe").attr("src",url); 
           };
           
          
           
      }

      
      /*****************************************************************************************************
      * 将自定义对象扩展到jquery对象中去，发布给外界，供外界使用
      ********************************************************************************************************/
      $.extend({
         require:function(moduleName){
             if(moduleName === "menuMgr"){
                return new ModuleMain();
             }
             if(moduleName === "menuDetailForm"){
                return new ModuleDetailForm();
             }
             if(moduleName === "menuDependies"){
                return new ModuleMenuDependies();
             }
         }
      });
  })(jQuery);
  
  </script>
</html>
