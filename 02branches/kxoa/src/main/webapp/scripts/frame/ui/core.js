define([ 'jquery'], function($) {
	var C = {
		DATE_FORMAT : 'yyyy-MM-dd',
		TIME_FORMAT : 'yyyy-MM-dd HH:mm:ss',

		KEYCODS : {
			ENTER : 13,
			ESC : 27,
			END : 35,
			HOME : 36,
			SHIFT : 16,
			TAB : 9,
			LEFT : 37,
			RIGHT : 39,
			UP : 38,
			DOWN : 40,
			DELETE : 46,
			BACKSPACE : 8
		},
		
		/**
		 * 拼接参数到URL后面
		 * 
		 * @param url URL
		 * 
		 * @param params {String} 参数，形如：key1=value1&key2=value2
		 * 
		 * @returns {String} 返回拼接好后加参数的URL
		 */
		addParams4URL: function (url, params) {
			if (!params) return url;
			
			if (url.indexOf('?') == -1) url += '?';
			if (!url.endsWith('?')) url += '&';
			
			// 添加自带参数
			url += params;
			
			return url;
		},
		
		/**
		 * 读参数
		 * 
		 * [{"key":"selectedUserCode", "value":"#txt_innermsg_share_usercode"}, ...] 或者
		 * {"key":"selectedUserCode", "value":"#txt_innermsg_share_usercode"}
		 * @param params
		 * @returns
		 */
		readTagParams: function(params) {
			if (!params) return null;
			
			if (!$.isArray(params)) {
				params = [params];
			}
			
			var temp = [];
			for (var i=0; i<params.length; i++) {
				var key = params[i].key;
				var value = $(params[i].value).val();
				
				if (value) {
					temp.push(key + "=" + value);
				}
			}
			
			return temp.join("&");
		}
		
		
	};

	$.fn.extend({
		/**
		 * 从jQuery对象中读取指定属性值，保存至设置变量
		 * 
		 * 元素里通过 data-name 添加属性，
		 * 若name为驼峰命名法，如："userName" 则在元素里用 "-" 分割：data-user-name
		 * 
		 * 若设置的值为函数名，需要在 data- 后面加 fn：data-fn-callback
		 */
		readData: function(name, options) {
			if (!options) {
				options = {};
			}
			var data = this.attr(name) || this.data(name);
			
			if(data=="false"){
				data= false;
			}else if(data=="true"){
				data= true;
			}
			if (data != undefined) {
				var temp = {};
				temp[name] = data;
				$.extend(options, temp)
			}
			else {
				newName = "fn" + name.charAt(0).toUpperCase() + name.substring(1);
				data = this.attr(newName) || this.data(newName);
				
				if (data != undefined) {
					data = eval(data);
					
					var temp = {};
					temp[name] = data;
					$.extend(options, temp)
				}
			}
			
			return this;
		},
		
		/**
		 * 联动下拉框
		 * 
		 */
		interactSelect: function() {
		
			$(this).change(function() {
				
				var select = $(this);
				
				var ref = select.attr('ref');
				var url = select.attr('refUrl');
				
				if (ref && url) {
					var refSelect = $(ref);
					var refUrl = url.replaceAll('{value}', select.val());
					
					$.get(refUrl, function(json) {
						refSelect.find('option').remove();
						
						for(var i=0; i<json.length; i++) {
							var key = json[i].key;
							var value = json[i].value;
							
							refSelect.append('<option value="'+key+'">'+value+'</option>');
						}
					
						refSelect.autoSelect();
						
					}, "json");
				}
			});
			
			return $(this);
			
		},
		
		/**
		 * 从jQuery对象中批量读取指定属性值，保存至设置变量
		 */
		readDatas: function(names, options) {
			
			// 必须是属性名数组
			if (!$.isArray(names)) {
				return this;
			}
			
			for (var i=0; i<names.length; i++) {
				this.readData(names[i], options);
			}
			
			return this;
		}

	});
	
	/**
	 * 扩展String方法
	 */
	$.extend(String.prototype, {
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		isInteger:function(){
			return (new RegExp(/^\d+$/).test(this));
		},
		isNumber: function(value, element) {
			return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
		},
		trim:function(){
			return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
		},
		startsWith:function (pattern){
			return this.indexOf(pattern) === 0;
		},
		endsWith:function(pattern) {
			var d = this.length - pattern.length;
			return d >= 0 && this.lastIndexOf(pattern) === d;
		},
		replaceSuffix:function(index){
			return this.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index);
		},
		trans:function(){
			return this.replace(/&lt;/g, '<').replace(/&gt;/g,'>').replace(/&quot;/g, '"');
		},
		replaceAll:function(os, ns){
			return this.replace(new RegExp(os,"gm"),ns);
		},
		replaceTm:function($data){
			if (!$data) return this;
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				return $data[$1.replace(/[{}]+/g, "")];
			});
		},
		replaceTmById:function(_box){
			var $parent = _box || $(document);
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				var $input = $parent.find("#"+$1.replace(/[{}]+/g, ""));
				return $input.val() ? $input.val() : $1;
			});
		},
		isFinishedTm:function(){
			return !(new RegExp("{[A-Za-z_]+[A-Za-z0-9_]*}").test(this)); 
		},
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		},
		isValidPwd:function() {
			return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this)); 
		},
		isValidMail:function(){
			return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
		},
		isSpaces:function() {
			for(var i=0; i<this.length; i+=1) {
				var ch = this.charAt(i);
				if (ch!=' '&& ch!="\n" && ch!="\t" && ch!="\r") {return false;}
			}
			return true;
		},
		isPhone:function() {
			return (new RegExp(/^(1(([35][0-9])|(47)|[8][01236789]))\d{8}$/).test(this));
		},
		isTelPhone:function() {
			return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
		},
		isUrl:function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isExternalUrl:function(){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		}
	});
	
	return C;
});