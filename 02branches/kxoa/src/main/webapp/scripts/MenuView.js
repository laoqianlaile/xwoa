Menu.menuView = {
	// 生成菜单类
	headList : function() {
		var menuView = new Array;
		menuView.push("<div class='accordionContent' id='menu_a'><ul id='ul_a' class='tree treeFolder'></ul></div>");
		return menuView;
	},
	// 子菜单 二级/三级
	menuList : function(list, node) {
		var menuView = new Array;
		for ( var i = 0, len = list.length; i < len; i++) {
			menuView.push("<li id='menu_" + list[i]["MID"] + "' parentId='"
					+ list[i]["ParentID"] + "'><div><span rel='"
					+ list[i]["MID"] + "' >" + list[i]["MText"]
					+ "</span></div></li>");
		}
		return menuView;
	},

	addUL : function(id, liView) {
		var ulDom = getID("ul_" + id), menuDom = getID("menu_" + id);
		if (ulDom === null) {
			menuDom.innerHTML += "<ul id='ul_" + id + "' class='tree'>"
					+ liView + "</ul>";
		} else {
			ulDom.innerHTML += liView;
		}
	},

	addLevel : function() {
		var $tree = $("ul.tree");
		$tree.each(function(i) {
			$('li:last:not(ul)', $(this)).addClass('last');
		});
	},

	addEvent : function(o1, o2) {
		$(".flag").live(
				'click',
				function(e) {
					var $this = $(this).parent().parent();
					if ($(this).hasClass("expandable")) {
						$(">ul", $this).show();
						$(this).removeClass("expandable").addClass(
								"collapsable");
					} else if ($(this).hasClass("collapsable")) {
						$(">ul", $this).hide();
						$(this).removeClass("collapsable").addClass(
								"expandable");
					} else if ($(this).hasClass("last_expandable")) {
						$(">ul", $this).show();
						$(this).removeClass("last_expandable").addClass(
								"last_collapsable");
					} else {
						$(">ul", $this).hide();
						$(this).removeClass("last_collapsable").addClass(
								"last_expandable");
					}
					e.preventDefault();
				});

		$("#close").click(function() {
			if (getID("shadow")) {
				$("#shadow").hide();
				$("#boxContent").hide();
			}
		});
	}
};