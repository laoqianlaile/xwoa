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
             bindEvent();
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
          * 删除节点
          * @public
          * @author lay
          * @createDate 2015-10-30
          */
          this.removeTreeNode = function(node){
               //获取当前节点的下一个节点，让他选中,注意这里要先缓存上一个节点或下一个节点，
               //因为如果先删除节点的话，无法根据当前节点知道他相邻的节点了
              var willSeletedNode = node.getNextNode();
              if(willSeletedNode == null){
                  //如果下一个节点没有，获取前面一个节点
                 willSeletedNode = node.getPreNode();   
              }
              //如果找不到相邻的节点，我们看看是否有父节点，如果还没有，那就不管他了
              if(willSeletedNode == null){
                  willSeletedNode = node.getParentNode();
              }
              //先删子，再删父
              Global.menuTreeHandler.removeChildNodes(node);
              Global.menuTreeHandler.removeNode(node);
             
              if(willSeletedNode!=null){
                 Global.selectNode(willSeletedNode,true);
              }
          };
         
          
          /**
          * 删除菜单数据
          * @public
          * @author lay
          * @createDate 2015-10-30
          */
          this.deleteMenu = function(node){
             DialogUtil.confirm("确定要删除该菜单吗?",function(){
                 if(node.isParent){
                   DialogUtil.alert("当前菜单存在子菜单，请先删除子菜单！");
                   return false;
                 }
                 
                 $.ajax({
                     url:ctx+"/sys/optInfo!delete.do",
                     type:"post",
                     dataType:"json",
                     data:{optid:node.id},
                     success:function(data){
                        if(data.operFlag){
                          DialogUtil.alert("操作成功");
                          Global.removeTreeNode(node);
                        }else{
                          DialogUtil.alert("提示","操作失败",150,100);
                        }
                     }
                  });
              });
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
         * 绑定事件
         * @private
         * @author lay
         * @createDate 2015-10-30
         */
          var bindEvent = function(){
            //添加父菜单
            $("#btnAddParentMenu").click(function(){
                loadMenuDetail(CONSTANT_ROOT_ID,"add");
            });
            //添加下级菜单
            $("#btnAddChildMenu").click(function(){
                if(!Global.currMenuNode){
                   DialogUtil.alert("请选择一个菜单节点");
                   return;
                }
                loadMenuDetail(Global.currMenuNode.id,"add");
            });
            //删除菜单
            $("#btnDeleteMenu").click(function(){
                if(!Global.currMenuNode){
                   DialogUtil.alert("请选择一个菜单节点");
                   return;
                }
                Global.deleteMenu(Global.currMenuNode);
            });
            
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
             var url = "";
            if(type=="add"){
                url = ctx+"/sys/optInfo!built.do?preoptid="+menuId;
            }
            if(type=="view"){
                url = ctx+"/sys/optInfo!edit.do?optid="+menuId;
            }
            $("#menuItemDetail").find("iframe").attr("src",url);
         };
         
        
         
    }
    
    /***************************************************************************************************************
    *  菜单基本信息明细模块
    ****************************************************************************************************************/
    function ModuleDetailForm(){
        /**
        * 父页面的js模块引用对象
        */
        var superModuleRef;
        /**
        * 操作类型 add-添加，edit-修改
        */
        var operType;
        /**
          * 初始化对象
          * @public
          * @author lay
          * @createDate 2015-10-30
          * @param 参数对象包含saveBtnId-保存按钮id,formId-表单id,ctx-上下文路径pageContext.reqeust.contextPath,superModuleRef-父级页面模块js引用
          * @example module.init({ctx:"${pageContext.request.contextPath}",saveFormId:"form1",saveBtnId:"btnSave"})
          */
          this.init = function(param){
             Global = this; 
             ctx = param.ctx;
             superModuleRef = param.superModuleRef;
             //初始化操作类型
            
              if($("#"+param.saveFormId).find("input[name='optid']").length==0 || $("#"+param.saveFormId).find("input[name='optid']").val().length==0){
                 operType = "add";
              }else{
                  operType = "edit";
              }
            //绑定事件
            bindEvent(param);
          };
        /**
         * 绑定事件
         * @private
         * @author lay
         * @createDate 2015-10-30
         */
          var bindEvent = function(param){
              //绑定保存按钮单击事件
             $("#"+param.saveBtnId).click(function(){
                     DialogUtil.confirm("确定要保存吗?",function(){
                          Global.saveForm(param.saveFormId);
                     });
             });
              
            //业务类别click事件
             $("input[name='opttype']").click(function(){
                var opttype=$("#opttypeW").attr("checked");
				if(opttype=="checked"){
					$("#wfcodeTR").show();
					if($("#optVarListIframe").length>0){
						$("#optVarListIframe").show();
					}
				}else{
					$("#wfcodeTR").hide();
					if($("#optVarListIframe").length>0){
						$("#optVarListIframe").hide();
					}
				}
              });
          };
         /**
          * 根据业务类别会隐藏流程代码，当表单提交时如果不是流程业务类型，我们要清除流程代码
          * @private
          * @author lay
          * @createDate 2015-10-30
          */
         var clearProp = function(){
              var opttype=$("#opttypeW").attr("checked");
              if(opttype!="checked" && $("#wfcode").val().length > 0){
                  $("#wfcode").val("");
              }
         };
          /**
          *  基本信息表单保存
          *  @public
          *  @author lay
          *  @createDate 2015-10-30
          */
          this.saveForm = function(formId){
              clearProp();
              $.ajax({
                 url:ctx+"/sys/optInfo!save.do",
                 type:"post",
                 dataType:"json",
                 data:$("#"+formId).serialize(),
                 success:function(data){
                    if(data.operFlag){
                      //操作父页面的ztree节点
                      if(operType == "add"){
                         //调用父页面js添加节点名称
                         superModuleRef.addTreeNode({id:$("#optid").val(),name:$("#optname").val(),pId:$("#preoptid").val()});
                      }
                      if(operType == "edit"){
                         //调用父页面js更新节点名称
                         superModuleRef.updateTreeNode($("#optname").val());
                      }
                      DialogUtil.alert("操作成功");
                    }else{
                      DialogUtil.alert("提示","操作失败",150,100);
                    }
                 }
              });
          };
          
         
          /**
          *  iframe自适应高度
          *  @private
          *  @author lay
          *  @createDate 2015-10-30
          *  @param iframeId HTML元素id
          */
          var iFrameHeight = function(iframeId){
                var ifm= document.getElementById(iframeId);   
                var subWeb = document.frames ? document.frames[iframeId].document : ifm.contentDocument;   
                if(ifm != null && subWeb != null) {
                   ifm.height = subWeb.body.scrollHeight;
                   ifm.width = subWeb.body.scrollWidth;
                }  
          };
    }
    
    /*******************************************************************************************************
    * 菜单其他模块
    * 也是他的依赖模块，比如操作按钮，业务变量，这里将他们抽象成公用的，因为他们的操作很类似
    ********************************************************************************************************/
    function ModuleMenuDependies(){
        /**
          * 初始化对象
          * @public
          * @author lay
          * @createDate 2015-10-30
          * @param 参数对象包含saveBtnId-保存按钮id,formId-表单id,ctx-上下文路径pageContext.reqeust.contextPath
          * @example module.init({ctx:"${pageContext.request.contextPath}",saveFormId:"form1",saveBtnId:"btnSave"})
          */
         this.init = function(){
            Global = this; 
         }
       
         /**
         * 弹出编辑窗口，列表页弹出
         * @pubilc
         * @author lay
         *
         */
         this.popEditorWin = function(title,url){
             DialogUtil.open(title,url,500,300);
         };
        
        /**
        * 表单提交
        * @pubilc
        * @author lay
        * @param formId 表单id
        * @param url 表单提交路径
        * @param pIframeId 列表ifram的id
        */
        this.saveForm = function(formId,url,pIframeId){
             $.ajax({
                 url:url,
                 type:"post",
                 dataType:"json",
                 data:$("#"+formId).serialize(),
                 success:function(data){
                    if(data.operFlag){
                       var win = art.dialog.opener;//来源页面
                       //子页面刷新父页面的列表,有待寻找更好的方法
                       if (!!window.ActiveXObject || "ActiveXObject" in window) { 
                        	 var optDeflistIframe =  win.frames['external_OPTINFO']['menuItemDetailIframe'][pIframeId];
                        	 optDeflistIframe.location.reload(true);
                        }else{
                        	var optDeflistIframe =  win.frames['external_OPTINFO'].contentWindow.frames['menuItemDetailIframe'].contentWindow.frames[pIframeId];
                        	$(optDeflistIframe).attr("src",optDeflistIframe.src);
                        }
                        DialogUtil.close();
                    }else{
                      DialogUtil.alert("提示","操作失败",150,100);
                    }
                 }
              });
        }
        
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