var UnitUser = function(param) {
	var $this = this;
	var tree = param.treeObj;
	//默认人员 user or unit
	var choosetype = param.choosetype || 'user';
	
	var callback = param.callback || {};
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick: callback.onClick || function(){}
		}
	};

	var settingCheckbox = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true
		}
	};
	this.e = {
		events : function() {
			// 绑定 this.e 下所有事件，统一调用
			$.each(this, function(index, fn) {
				if ('events' != fn && $.isFunction(fn)) {
					fn();
				}
			});
		},
		bindSave : function() {
			
			$('#' + param.btnAdd).click(function(e) {
				
				var $treeObj = $this.funs.getTree();

				var units = $this.funs.getCheckedName($treeObj);
				var codes = $this.funs.getCheckUserCode($treeObj, choosetype);
				//debugger;
				
				
				var  ddfs = top.document.getElementById("navTab");
				var id=$(ddfs).find("li.external.selected").attr("tabid");
				
				
				var $frame = $('#' + id).contents();
				if('user' == choosetype) {
					$('#' + param.username, $frame).text('');
					$('#' + param.username, $frame).text(units.join(','));
					$('#' + param.usercode, $frame).val('');
					$('#' + param.usercode, $frame).val(codes.join(','));
				} else if('unit' == choosetype) {
					$('#' + param.unitname, $frame).text('');
					$('#' + param.unitname, $frame).text(units.join(','));
					$('#' + param.unitcode, $frame).val('');
					$('#' + param.unitcode, $frame).val(codes.join(','));
				}
				

				param.close();
				e.preventDefault();
			});
		},

		// 已选择人员或部门，再次点击选择后，覆写状态
		bindChooseInit : function() {	
			var $frame = $('#'+param.frame).contents();
			//debugger;
			$this.funs.getChooseInit($.trim($('#' + param.usercode, $frame).val()), $this.funs.getTree());
			$this.funs.getChooseInit($.trim($('#' + param.unitcode, $frame).val()), $this.funs.getTree());
		}

	};

	this.funs = {
		init : function(zNodes) {
			if(oZTree){
				oZTree.init($('#' + tree), setting, zNodes);
				$this.funs.getSelectNode(oZTree.getZTreeObj(tree), param.selectNodeId);
			}
			else{
				$.fn.zTree.init($('#' + tree), setting, zNodes);				
				$this.funs.getSelectNode($.fn.zTree.getZTreeObj(tree), param.selectNodeId);
			}
			
		},

		initCheckbox : function(zNodes) {
			if(oZTree){
				oZTree.init($('#' + tree), settingCheckbox, zNodes);
			}
			else{
				$.fn.zTree.init($('#' + tree), settingCheckbox, zNodes);				
			}
			$this.e.events();
		},

		getTree : function() {
			if(oZTree){
				return oZTree.getZTreeObj(tree);
			}
			else{
				return $.fn.zTree.getZTreeObj(tree);				
			}
		},

		/**
		 * 获取选中中文名
		 * 
		 * @param $tree
		 * @returns {Array}
		 */
		getCheckedName : function($tree) {
			var $checkNodes = $tree.getCheckedNodes();
			var $units = [];
			$.each($checkNodes, function(index, node) {
				var pNode = node.getParentNode();
				if (null == pNode) {
					var obj = (node.getCheckStatus());
					if (obj.checked && !(obj.half)) {
						$units.push(node.name);
					}
				} else {
					var obj = (pNode.getCheckStatus());
					var o = node.getCheckStatus();
					// 父节点选中，选择父节点。
					// 自己选中，父半选，选择自己
					if (obj.checked && (obj.half) && o.checked && !(o.half)) {
						$units.push(node.name);
					}
				}
			});

			return $units;
		},

		getCheckUserCode : function($tree, type) {
			var $checkNodes = $tree.getCheckedNodes();

			var userCodes = [];
			
			
			var flag = '';
			if(type == choosetype) {
				flag = 'false';
			} else if(type == 'unit') {
				flag = 'true';
			}
			
			$.each($checkNodes, function(index, node) {
				// 不是父节点，并且不是root节点
				
				if (!(node.isParent) && flag == node.p) {
					userCodes.push(node.id);
				}
			});

			return userCodes;
		},
		
		getSelectNode : function($tree, selectNodeId) {
			var node = $tree.getNodesByParam('id', selectNodeId);
			if('' == node) {
				node = $tree.getNodeByTId(selectNodeId);
				$tree.selectNode(node);
			} else {
				$tree.selectNode(node[0]);
			}
		},		
		
		getChooseInit : function(codes, $tree) {
			if ('' == codes) {
				return;
			}

			var ucodeArr = codes.split(',');
			//debugger;
			$.each(ucodeArr, function(index, code) {
				var node = $tree.getNodesByParam('id', code);
				$tree.checkNode(node[0], true, true);
			});
		}
	};
};

function E(z) {
	console.debug(z);
}
