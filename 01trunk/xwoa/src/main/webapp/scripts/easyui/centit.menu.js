(function($){
	this.searchText = null, this.searchIndex = 0;
	
	function walkTree(tree, callback, subProperty) {
		subProperty = subProperty || 'children';
		
		var _walk = function(tree) {
			for (var i=0; i<tree.length; i++) {
				var node = tree[i];
				
				if (node[subProperty]) {
					_walk(node[subProperty]);
				}
				
				callback(node);
				
			}
		};
		
		_walk(tree);
	}
	
	function setSize(container) {
		container.accordion('resize', {
			height: container.closest('div.layout-body').height() - container.prev('div.panel').height()
		});
	}
	
	/*收缩所有菜单*/
	function collapseAll(container) {
		var panels = $.data(container[0], 'centitMenu').panels;
		
		if (!panels) return;
		
		for (var i=0; i<panels.length; i++) {
			// 一级菜单
//			container.accordion('unselect', i);
			
			// 二级及以下菜单
			panels[i].tree('collapseAll');
		}
	}
	
	/*展开所有菜单*/
	function expandAll(container) {
		var panels = $.data(container[0], 'centitMenu').panels;
		
		if (!panels) return;
		
		for (var i=0; i<panels.length; i++) {
			// 一级菜单
//			container.accordion('select', i);
			
			// 二级及以下菜单
			panels[i].tree('expandAll');
		}
	}
	
	/*展开指定菜单*/
	function expandTo(container, id, mark) {
		var panels = $.data(container[0], 'centitMenu').panels;
		
		if (!panels) return;
		
		for (var i=0; i<panels.length; i++) {
			
			var treeObj = panels[i];
			var node = treeObj.tree('find', id);
			
			if (node) {
				container.accordion('select', i);
				treeObj.tree('expandTo', node.target);
				treeObj.tree('scrollTo', node.target);
				
				return node;
			}
		}
	}
	
	/*标记指定菜单*/
	function mark(container, id) {
		var node = expandTo(container, id);
		
		if (node) {
			$(node.target).addClass('found');
		}
	}
	
	function clean(container) {
		container.find('div.tree-node').removeClass('found tree-node-selected');
	}
	
	/*搜索菜单名称*/
	function search(container, text) {
		var panels = $.data(container[0], 'centitMenu').panels;
		if (!panels) return;
		
		if (text == this.searchText) {
			this.searchIndex++;
		}
		else {
			this.searchText = text;
			this.searchIndex = 0;
		}
		
		var foundMenu = [];
		for (var i=0; i<panels.length; i++) {
			var treeObj = panels[i];
			
			var temp = [];
			walkTree(treeObj.data('menuData'), function(node) {
				if (node.text.indexOf(text) > -1) {
					temp.push(node.id)
				}
			});
			
			temp.length && foundMenu.push(temp);
		}
		
		clean(container);
		if (!foundMenu.length) {
			return;
		}
		
		//collapseAll(container);
		var indexes = foundMenu[this.searchIndex % foundMenu.length];
		for (var i=0; i<indexes.length; i++) {
			var id = indexes[i];
			
			mark(container, id);
		}
	}
	
	function init(container) {
		var state = $.data(container, 'centitMenu');
		var options = state.options;
		container = $(container);
		
		$.getJSON(options.base+'/'+options.url, function(menus) {
			menus = menus[0].children;
			var panels = [];
			
			for (var i=0; i<menus.length; i++) {
				var menu = menus[i];
				var treeId = "menu_"+menu.id;
				
				// 一级菜单
				container.accordion('add', {
					title: menu.text,
					iconCls: ['icon-save', 'icon-add', 'icon-edit', 'icon-clear', 'icon-cut', 'icon-ok', 'icon-no', 'icon-reload', 'icon-cancel', 'icon-print'][parseInt(Math.random()*10)],
					content: Mustache.render(Template["TEMPLATE_TREE"], {
						treeId: treeId
					}),
					selected: 0==i
				});
				
				// 二级及以后菜单
				$('#'+treeId).tree({
					onClick: function(node){
						// 叶子节点是菜单
						if (!node.children || !node.children.length) {
							$('#menu_tab').menuTab('open', node);
						}
					},
					formatter: function(node) {
						return Mustache.render(Template["TEMPLATE_LINK"], node);
					},
					data: menu.children
				});
				
				panels.push($('#'+treeId).data('menuData', [menu]));
			}
			
			state.panels = panels;
			state.menuData = menus;
			
			setSize(container);
			
			if (options.callback) {
				options.callback.call(container);
			}
			
			container.bind('_resize', function(){
				setSize(container);
			});
		});
	};
	
	$.fn.centitMenu = function(options, param){
		if (typeof options == 'string'){
			return $.fn.centitMenu.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'centitMenu');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'centitMenu', {
					options: $.extend({}, $.fn.centitMenu.defaults, $.fn.centitMenu.parseOptions(this), options),
					centitMenu: $(this).addClass('centit-menu'),
					panels: [],
					menuData: []
				});
				
				init(this);
			}
			
//			setProperties(this);
//			setSize(this);
//			doFirstSelect(this);
		});
	};
	
	$.fn.centitMenu.methods = {
		options: function(jq){
			return $.data(jq[0], 'centitMenu').options;
		},
		panels: function(jq){
			return $.data(jq[0], 'centitMenu').panels;
		},
		collapseAll: function(jq) {
			collapseAll(jq);
		},
		expandAll: function(jq) {
			expandAll(jq);
		},
		expandTo: function(jq, id) {
			expandTo(jq, id);
		},
		mark: function(jq, id) {
			mark(jq, id);
		},
		clean: function(jq) {
			clean(jq);
		},
		search: function(jq, text) {
			search(jq, text);
		}
	};
	
	$.fn.centitMenu.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.parser.parseOptions(target, [
			'width','height','base','url','callback',
			{fit:'boolean',border:'boolean',animate:'boolean',multiple:'boolean',selected:'number'}
		]));
	};
	
	$.fn.centitMenu.defaults = {
		base: './',	
		width: 'auto',
		height: 'auto',
		fit: true,
		border: false,
		animate: true,
		multiple: false,
		selected: 0,
		
		onSelect: function(title, index){},
		onUnselect: function(title, index){},
		onAdd: function(title, index){},
		onBeforeRemove: function(title, index){},
		onRemove: function(title, index){}
	};
})(jQuery)