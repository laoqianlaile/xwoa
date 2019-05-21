/**
* 先腾公文流转页面控制
* 1.数据来源 通用运行模块 modecode  --控制页面有哪些元素（这个还沿用原先在jsp中处理）
*         数据字典   ideacode  --控制页面初始显示元素及切换意见时根据DataDesc（这个是transinfo。js 的主要功能）
* 2.规则    idearule  {"bj":"T","en":"F"}  办件角色显示  权限引擎隐藏    
* 
*/
(function($){
    /***********************************************************************************************************************************
    *  意见切换模板
    ************************************************************************************************************************************/
	function TransinfoTemplate(){
		
		  var _self;

          /**
          * 初始化对象
          * context--办理表单
          */
          this.init = function(){
        	  _self = this; 
        	  
        	  _self.initCommon();

              // 办理意见与显示内容联动
				$('input:radio[name="ideacode"]').bind("click",
						function(e) {
							_self.initCommon();
						});
          };
          
          
          
          var idea_module = {// 枚举页面需要控制的逻辑块--默认不显示
					// 人员信息
					"assignteamrole" : {
						isshow : "F",
						element : "#tr_assignTeamRole",
						target : "bj"
					},// 1办件角色
					"assignenginerole" : {
						isshow : "F",
						element : "#tr_assignEngineRole",
						target : "en"
					},// 2权限引擎角色

					// 在使用过程中约定优先使用 1,2 存在需要初始多种类型人员数据时再使用3,4
					"haszbuser" : {
						isshow : "F",
						element : "#tr_haszbuser",
						target : "zbu"
					},// 3 主办角色
					"hasxbuser" : {
						isshow : "F",
						element : "#tr_hasxbuser",
						target : "xbu"
					},// 4 协办角色

					// 部门信息-批分
					"hasorgrole" : {
						isshow : "F",
						element : ".tr_hasorgrole",
						target : "org"
					}// 主办协办

				};
            /**
             * 获取关键数据：页面显示rule
             * idearule  {"bj":"T","en":"F"}
             */
            this.initCommon = function() {
				var ideacode = $.trim($(
						'input:radio[name="ideacode"]:checked')
						.val()); // 取radio
				var idearule = "" == $(
						'input:radio[name="ideacode"]:checked')
						.data("rule") ? null : $(
						'input:radio[name="ideacode"]:checked')
						.data("rule");
				//出示快捷意见--如页面有人员部门选择将被重新改写
				$("#transidea").val(
						$.trim($('input:radio[name="ideacode"]:checked').attr("lable")));
				_self.initIdealinfo( idearule);
			};
          
			/**
			 * 判断页面元素是否存在
			 */
			var isshow= function (_element){
				if ( _element.length > 0 ) {
					return  "T";
				}else{
					return "F";
				}
			};
          /**
           * 页面元素控制
           * initIdealinfo 办理意见控制 通用模块配置优先--数据字典描述次之（判断页面元素是否存在，存在根据数据字典配置显示）
           * 
           */
			
			this.initIdealinfo = function( idearule) {

				$
						.each(
								idea_module,
								function(name, value) {
									var _element = $(
											idea_module[name]["element"]);
									var showTag = isshow(_element);
									
									var target = idea_module[name]["target"];
									if (_element) {
										if ("T" == showTag) {
											if (null == idearule) {// 通用模块配置
												_element.css("display",
														"table-row");
												
											} else if (null != idearule
													&& idearule[target]
													&& "T" == (idearule[target]
															.toUpperCase())) {// 大小写不敏感 // T t
												
																		
												_element.css("display",
														"table-row");
											} else if (null != idearule) {// 办理意见只需写需要显示的元素
												_element.css("display",
														"none");
											}

										} else {
											_element.css("display", "none"); 
										}
									}
								});
			};
			
			
    }
	
    
    /*****************************************************************************************************
    * 将自定义对象扩展到jquery对象中去，发布给外界，供外界使用
    ********************************************************************************************************/
    $.extend({
       require:function(moduleName){
           if(moduleName === "transinfoIdea"){
              return new TransinfoTemplate();
           }
           
       }
    });
})(jQuery);