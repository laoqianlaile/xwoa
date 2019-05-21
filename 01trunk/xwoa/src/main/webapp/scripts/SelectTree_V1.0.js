var getID = function(id) {
	return document.getElementById(id);
};

var Menu = {};
Menu.menuDate = {
	// 判断对象是否为空
	isEmptyObject : function(jsonList) {
		for ( var name in jsonList) {
			return false;
		}
		return true;
	},

	// 动态判断nodeName 是否存在
	haveNode : function(json, nodeName) {
		if (json[nodeName] === undefined) {
			return false;
		}
		return true;
	},

	// 固定的json格式，不管命名的变化
	getFirstNode : function(json) {
		var firstNode = new Array;
		for ( var name in json) {
			firstNode.push(name);
		}
		return firstNode;
	},

	// 获取菜单列表
	getList : function(json, nodeName) {
		return json[nodeName];
	},

	getNode : function(list, flag) {
		var v = new Array;
		for ( var i = 0, len = list.length; i < len; i++) {
			if (parseInt(list[i][flag]) === 1) {
				v.push(list[i]);
			}
		}
		return v;
	},

	sortList : function(list) {
		var s = new Array();
		for ( var i = 0, len = list.length; i < len; i++) {
			if (s[list[i]["ParentID"]] == undefined)
				s[list[i]["ParentID"]] = new Array();
			s[list[i]["ParentID"]].push(list[i]);
		}
		return s;
	},

	getLevel : function(list, headArr) {
		var len = list.length, key = new Array(), level = new Array(), index, h = 1, id, reg;
		level[0] = headArr;
		while (h) {
			for ( var i = 0; i < len; i++) {
				id = list[i]["ParentID"];
				reg = new RegExp("\\b(" + id + ")\\b");
				index = reg.test(headArr);
				if (index) {
					key.push(list[i]["MID"]);
				}
			}
			if (key.length == 0)
				break;
			headArr = key.join(",");
			level[h] = headArr;
			key.length = 0;
			h++;
		}
		level.pop();
//		for (var i in level) {
//			alert(level[i]);
//		}
		return level;
	},

	getIDArr : function(list) {
		var MTOP = new Array;
		for ( var i = 0, len = list.length; i < len; i++) {
			MTOP[list[i]["MID"]] = list[i]["ParentID"];
		}
//		for (var i in MTOP) {
//			alert(i + " : " + MTOP[i]);
//		}
		return MTOP;
	}
};

Menu.menuView = {
	// 生成菜单类
	headList : function() {
		var menuView = new Array;
		menuView
				.push("<div class='accordionContent' id='menu_a'><ul id='ul_a' class='tree treeFolder'></ul></div>");
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

function menuDisplay(menuList, levelID) {
	var mv = Menu.menuView, md = Menu.menuDate;

	if (menuList.length) {
		var c = mv.headList().join(""), 
			mdList = menuList, 
			sortList = md.sortList(mdList), 
			level = md.getLevel(mdList, levelID), 
			pid = md.getIDArr(mdList), 
			c = new Array, 
			f = new Array, 
			newLine = new Array, 
			line = "<div class='line'></div>", 
			expandable = "<div class='flag expandable'></div>", 
			last_expandable = "<div class='flag last_expandable'></div>", 
			indent = "<div class='indent'></div>", 
			node = "<div class='node'></div>";
//		alert(c);
		getID("lists").innerHTML = c;
		for ( var n = 0, nLen = level.length; n < nLen; n++) {
			var levelKey = level[n].split(","), m;
			for ( var h = 0, keyLen = levelKey.length; h < keyLen; h++) {
				if (sortList[levelKey[h]] != undefined) {
					newLine[n] = line;
					for ( var j = 0, jLen = sortList[levelKey[h]].length; j < jLen; j++) {
						var p = sortList[levelKey[h]][j]["ParentID"];
						m = n - 1;
						while (m >= 0) {
							if (pid[p]) {
								var oldP = p;
								p = pid[p];
								if (sortList[p]
										&& sortList[p][sortList[p].length - 1]["MID"] == oldP) {
									newLine[m] = indent;
								} else {
									newLine[m] = line;
								}
							} else {
								newLine[m] = line;
							}
							m--;
						}
						if (c[levelKey[h]] == undefined) {
							c[levelKey[h]] = new Array();
							c[levelKey[h]].push("<ul id='ul_" + levelKey[h]
									+ "' class='tree'>");
						}
						newLine.pop();
						if (sortList[sortList[levelKey[h]][j]["MID"]] == undefined) {
							newLine.push(node);
						} else if (j == jLen - 1
								&& sortList[sortList[levelKey[h]][j]["MID"]]) {
							newLine.push(last_expandable);
						} else {
							newLine.push(expandable);
						}
						c[levelKey[h]].push("<li id='menu_"
								+ sortList[levelKey[h]][j]["MID"]
								+ "' parentId='"
								+ sortList[levelKey[h]][j]["ParentID"]
								+ "'><div>" + newLine.join("") + "<span rel='"
								+ sortList[levelKey[h]][j]["MID"] + "' >"
								+ sortList[levelKey[h]][j]["MText"]
								+ "</span></div></li>");
						if (j == jLen - 1)
							c[levelKey[h]].push("</ul>");
					}
					newLine.length = 0;
					f.push(levelKey[h]);
				}
			}
		}

		if (getID("lists") != null) {
			getID("lists").innerHTML = c[levelID].join("");
//			getID("a123").innerHTML = getID("lists").innerHTML.replace(/</g,"&lt").replace(/>/g,"&gt");
		}
		for ( var d = 1, dLen = f.length; d < dLen; d++) {
			var k = c[f[d]].join("");
			var id = k.match(/(.*)ul_(.*)' cl(.*)/)[2];
			getID("menu_" + id).innerHTML += k;
		}

	}
	mv.addLevel();
	mv.addEvent();
};