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
		
		
		return level;
	},

	getIDArr : function(list) {
		var MTOP = new Array;
		for ( var i = 0, len = list.length; i < len; i++) {
			MTOP[list[i]["MID"]] = list[i]["ParentID"];
		}
		return MTOP;
	}
};