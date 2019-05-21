$(function() {
	/**
	 * 一进入页面显示信息
	 */
	function showInfo() {
		var errorInfo = $('#errorInfo').hide().html();
		var successInfo = $('#successInfo').hide().html();
		
		if (errorInfo) {
			msgError(errorInfo);
		}
		else if (successInfo) {
			msgAlert(successInfo);
		}
	}
	
	/**
	 * 拼接参数到URL后面
	 * 
	 * @param url URL
	 * 
	 * @param params {String} 参数，形如：key1=value1&key2=value2
	 * 
	 * @returns {String} 返回拼接好后加参数的URL
	 */
	function addParams4URL(url, params) {
		if (!params) return url;
		
		if (url.indexOf('?') == -1) url += '?';
		if (!url.endsWith('?')) url += '&';
		
		// 添加自带参数
		url += params;
		
		return url;
	}
	
	/**
	 * 将选择器数组转化为url参数字符串
	 * 
	 * @param params 动态参数。格式为：[selector1, selector1, ... , selector8, ...]
	 * 
	 * @returns {String} url参数。例如：key1=value1&key2=value2&...&key8=value8...
	 */
	function getParamsString(params) {
		if (!params) return "";
		
		var ps = "";
		
		// 拼接参数
		for (var i=0; i<params.length; i++) {
			var selector = params[i];
			var obj = $(selector);
			
			if (obj[0]) {
				ps += obj.serialize();
				ps += '&';
			}
			
		}
		
		// 去掉最后的'&'字符
		ps.removeLastChar();
		
		return ps;
	}
	
	/**
	 * 将选择器数组转化为参数数组
	 * 
	 * @param params
	 *            动态参数。格式为：[selector1, selector1, ... , selector8, ...]
	 * 
	 * @returns {Array} 符合JQuery Ajax提交data参数的格式。例如：[ { name: "a", value: "1" }, {
	 *          name: "b", value: "2" }, { name: "c", value: "3" }, { name: "d",
	 *          value: "4" }, { name: "e", value: "5" } ]
	 */
	function getParamsArray(params) {
		if (!params) return [];
		
		var pa = [];
		
		// 拼接参数
		for (var i=0; i<params.length; i++) {
			var selector = param[i];
			var obj = $(selector);
			
			$.merge(pa, obj.serializeArray());
		}
		
		return pa;
	}
	
	/***
	 * Ajax请求系统默认回调函数
	 */
	function ajaxDone(json) {
		var status = json.statusCode;
		var msg = json.message;
		
		// 成功
		if ('200' == status) {
			msgAlert(msg);
		}
		// 错误
		else {
			msgError(msg);
		}
	}
	
	function msgAlert(msg) {
		$.globalMessenger().post({
		  message: msg,
		  hideAfter: 2,
		  showCloseButton: true
		});
	}
	
	function msgError(msg) {
		$.globalMessenger().post({
		  message: msg,
		  type: 'error',
		  hideAfter: 10,
		  showCloseButton: true
		});
	}
	
	function msgConfirm(msg, option) {
		option = $.extend({
			retryLabel: '提交',
			cancelLabel: '取消',
			cancelAction: function() {
				this.cancel();
			}
		}, option);
		
		var msg = $.globalMessenger().post({
		  message: msg,
		  showCloseButton: true,
		  hideAfter:null,
		  actions: {
			
			retry: {
		      label: option.retryLabel,
		      action: function() {
		    	  option.retryAction.call();
		    	  this.cancel();
		      }
		    },
		    cancel: {
		      label: option.cancelLabel,
		      action: option.cancelAction
		    }
		  }
		});
	}
	
	function init() {
	
		jQuery.ajax({
			type:'GET',
			url:Config.contextPath + "/page/frame/centitui.frag.xml",
			dataType:'xml',
			timeout: 50000,
			cache: false,
			error: function(xhr){
				alert('Error loading XML document: ' + pageFrag + "\nHttp status: " + xhr.status + " " + xhr.statusText);
			}, 
			success: function(xml){
				$(xml).find("_PAGE_").each(function(){
					var pageId = $(this).attr("id");
					if (pageId) Centit.frag[pageId] = $(this).text();
				});
				
				$(xml).find("_MSG_").each(function(){
					var id = $(this).attr("id");
					if (id) Centit._msg[id] = $(this).text();
				});
				
				$(window).wresize(function() {
					$('[layoutH]').layoutH();
				});
				
				// 初始化页面
				$(document).initUI();
			}
		});
	}
	
	var Centit = {
			getParamsString: getParamsString,
			getParamsArray: getParamsArray,
			ajaxDone: ajaxDone,
			addParams4URL: addParams4URL,
			showInfo:showInfo,
			msgAlert:msgAlert,
			msgError:msgError,
			msgConfirm:msgConfirm,
			init:init,
			frag:{},
			_msg:{},
			
			keyCode: {
				ENTER: 13, ESC: 27, END: 35, HOME: 36,
				SHIFT: 16, TAB: 9,
				LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
				DELETE: 46, BACKSPACE:8
			},
	};
	Centit.init();
	window.Centit = Centit;
	
	
	// Ajax遮盖层
//	var ajaxbg = $("#background,#progressBar");
//	ajaxbg.hide();
//	$(document).ajaxStart(function() {
//		ajaxbg.show();
//	}).ajaxStop(function() {
//		ajaxbg.hide();
//	});
	
	
	$.fn.extend({
		/**
		 * 初始化UI、添加事件等……
		 */
		initUI: function() {
			// 显示信息
			Centit.showInfo();
			
			var $this = $(this);
			
			var selectors = $('select,input:checkbox,input:radio', $this);
			
			// 级联下拉框
			selectors.filter('select').interactSelect();
			
			// Select Checkbox Radio 根据回填值自动选择
			selectors.autoSelect(function () {
				// 分页插件增强
				$('div.pagination').pagingPlus();
			});
			
			// 表格排序
			$('table[form]', $this).sortTable();
			
			// 优化表单
			$('form', $this).formPlus();
			
			var buttons = $('a,button', $this);
			
			// 自动提交表单
			buttons.filter('[target=submit]').click(function(e) {
				
				$(this).autoSubmit();
				
				e.preventDefault();
			});
			
			// Ajax请求
			buttons.filter('[target=ajax]').click(function(e) {
				$(this).ajaxTodo();
				
				e.preventDefault();
			});
			
			// 文件下载
			buttons.filter('[target=download]').download();
			
			// 文本框加强
			$('textarea', $this).textareaPlus();
			
			// 自动完成
			$('input.autoComplete', $this).autoComplete();
			
			// 下拉框选择用户
			$('input.users', $this).selectUsers();
			
			// 清空下拉框
			$('.clearInput', $this).clearInput();
			
			// 日期
			$('.Wdate', $this).each(function() {
				var options = $(this).data('option');
				options = $.extend({readOnly:true, skin:'ext'}, options);
				
				$(this).click(function() {
					WdatePicker(options);
				});
			});
			
			$('.Wtime', $this).each(function() {
				var options = $(this).data('option');
				options = $.extend({readOnly:true, skin:'ext', dateFmt:"yyyy-MM-dd HH:mm:ss"}, options);
				
				$(this).click(function() {
					WdatePicker(options);
				});
			});
			
			// 文件
			if ($.fn.uploadify) {
				$("input:file", $this).each(function() {
					$.myUpload.init.call(this);
				});
			}
			
			setTimeout(function(){
				$('[layoutH]', $this).layoutH();
			}, 10);
			
			return $(this);
		},
		
		/**
		 * adjust component inner reference box height
		 * @param {Object} refBox: reference box jQuery Obj
		 */
		layoutH: function($refBox){
			return this.each(function(){
				var $this = $(this);
				if (!$refBox) $refBox = $(window);
				var parent = $refBox;
				
				var iRefH = $refBox.height();
				var layoutH = $this.attr("layoutH");
				if (!layoutH) return true;
				var iLayoutH = parseInt(layoutH); 
				
				// 根据对象高度自适应
				if (!iLayoutH) {
					var temp = layoutH.split(' ');
					
					var selector = layoutH.split(' ')[0];
					var offset = temp.length > 1 ? layoutH.split(' ')[1] : 0;
					
					iLayoutH = parent.find(selector).outerHeight(true) + parseInt(offset);
				}
				
				var iH = iRefH - iLayoutH > 50 ? iRefH - iLayoutH : 50;
				
				if ($this.is("table")) {
					$this.removeAttr("layoutH").wrap('<div layoutH="'+layoutH+'" style="overflow:auto;height:'+iH+'px"></div>');
				} else {
					$this.height(iH).css("overflow","auto");
				}
			});
		},
		
		download: function() {
			$(this).css({
				cursor:'pointer'
			}).click(function(e) {
				var alink = $(this);
				var filecode = alink.attr('filecode');
				var container = $('body');

				// 下载回调函数
				var downloadFilesCallback = function(data) {
					if (!data || '0' != data.result) {
						msgError(data.description);
						return false;
					}

					var form = $('#downloadForm', container);

					if (!form[0]) {
						form = $("<form>"); // 定义一个form表单
						form.attr('id', 'downloadForm');
						form.attr('style', 'display:none'); // 在form表单中添加查询参数
						form.attr('target', '');
						form.attr('method', 'post');
						form.attr('action', Config.contextPath
								+ '/app/fileinfo!downloadfile.do');

						var input = $('<input>');
						input.attr('type', 'hidden');
						input.attr('name', 'filecode');

						container.append(form); // 将表单放置在web中
						form.append(input); // 将查询参数控件提交到表单上
					}

					form.find('input[name=filecode]').val(data.filecode);
					form.submit();
				};

				if (filecode) {
					$.post(Config.contextPath + '/app/fileinfo!toDownloadfile.do',
							{
								filecode : filecode
							}, downloadFilesCallback, 'json');
				} else {
					$.post(alink.attr('href'), downloadFilesCallback, 'json');
				}

				e.preventDefault();
				return false;
			});
			
			return $(this);
		},
		
		clearInput: function() {
			$(this).click(function() {
				var obj = $(this);
				obj.css({cursor:'pointer'});
				var ref = obj.attr('refInput');
				var inputs = [];
				
				if (ref) inputs = ref.split(',');
				for(var i=0; i<inputs.length; i++) {
					$(inputs[i]).val('');
				}
			});
			
			return $(this);
		},
		autoComplete: function(options) {
			$(this).each(function() {
				var obj = $(this);
				
				if (obj.parents('head')[0]) return true;
				
				if (!options) {
					options = {
						href: obj.attr('href'),
						showInfo: obj.attr('showInfo'),
						dataValue: obj.attr('dataValue'),
						realValue: obj.attr('realValue'),
						refTo: obj.attr('refTo')
					};
				}
				
				obj.autocomplete({
					source:function(query,process){
						var matchCount = this.options.items;
						$.post(options.href, {"matchInfo":query,"matchCount":matchCount},function(respData){
							return process(respData);
						});
					},
					formatItem:function(item){
						var showInfo = options.showInfo;
						var properties = showInfo.match(/\{\w*?\}/ig);
						for (var i=0; i<properties.length; i++) {
							showInfo = showInfo.replace(properties[i], item[properties[i].replace('{','').replace('}','')]);
						}
						
						return showInfo;
					},
					afterSelect:options.afterSelect || function(val, realVal) {
						var refTo = this.attr('refTo');
						if (!refTo) return;
					  
						var input = $('input[name=' + refTo + ']', this.parent());
						if (!input[0]) {
						  this.before('<input type="hidden" name="'+ refTo +'" value="' + realVal + '" />');
						}
						else {
						  input.val(realVal);
						}
					},
					setValue:function(item){
						return {'data-value':item[options.dataValue],'real-value':item[options.realValue]};
					}
				});
				
			});
			
			return $(this);
		},
		
		/***
		 * 下拉框选择用户
		 * 
		 * @returns
		 */
		selectUsers: function() {
			$(this).each(function() {
				var $this = $(this);
				
				$this.autocomplete({
					source:function(query,process){
						var matchCount = this.options.items;
						
						$.post(Config.contextPath + "/sys/userDef!selectUser.do",{"matchInfo":query,"matchCount":matchCount},function(respData){
							return process(respData);
						});
					},
					formatItem:function(item){
						return item["username"]+"("+item["nameFisrtLetter"]+", "+item["loginname"]+") - "+item["usercode"];
					},
					afterSelect:function(val, realVal){
						var usercodeRef = this.attr('usercodeRef');
						if (!usercodeRef) return;
					  
						var input = $('input[name=' + usercodeRef + ']', this.parent());
						if (!input[0]) {
						  this.before('<input type="hidden" name="'+ usercodeRef +'" value="' + realVal + '" />');
						}
						else {
						  input.val(realVal);
						}
					},
					setValue:function(item){
						return {'data-value':item["username"],'real-value':item["usercode"]};
					}
				});
				
				
			});
			
			return $(this);
			
		},
		
		/**
		 * 表格排序
		 * 使用方法：在TH上加入orderField，值为需要排序的字段名。table上加属性form值为对应提交FORM的选择器
		 * @returns
		 */
		sortTable: function() {
			$(this).each(function() {
				var $this = $(this);
				var form = $this.attr('form');
				
				// 原form中排序属性
				var fOrderField = $(form).find('input[name=orderField]').val();
				var fOrderDirection = $(form).find('input[name=orderDirection]').val();
				
				$this.find('th[orderField]').each(function() {
					var th = $(this);
					th.addClass('sorting').attr('form', form);
					
					if (fOrderField.trim() == th.attr('orderField')) {
						th.removeClass('sorting').addClass('sorting_' + fOrderDirection);
					}
					
					// 点击表头排序
					th.click(function() {

						var orderField = th.attr('orderField');
						
						// 为FORM添加排序属性
						if (!$(form).find('input[name=orderField]')[0]) {
							$(form+'>fieldset').append('<input type="hidden" name="orderField" value="" />');
						}
						if (!$(form).find('input[name=orderDirection]')[0]) {
							$(form+'>fieldset').append('<input type="hidden" name="orderDirection" value="" />');
						}
						
						$(form).find('input[name=orderField]').val(orderField);
						var direction = $(form).find('input[name=orderDirection]').val().trim() == "asc" ? "desc" : "asc";
						$(form).find('input[name=orderDirection]').val(direction);
						
						$(this).autoSubmit();
					});
					
				});
			});
			
			return $(this);
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
					
					$.post(refUrl, function(json) {
						refSelect.find('option').remove();
						
						for(var i=0; i<json.length; i++) {
							var key = json[i].key;
							var value = json[i].value;
							
							refSelect.append('<option value="'+key+'">'+value+'</option>');
						}
						
						refSelect.autoSelect();
						
						// 如果是chosen下拉框：
						if ('chosen' == refSelect.data('rel')) {
							refSelect.trigger('liszt:updated');
						}
						
					}, "json");
				}
					
			});
			
			return $(this);
			
		},
		
		/**
		 * 根据回填值（data-value），自动为下拉框、checkbox、radio选择值
		 * 
		 */
		autoSelect: function(callback) {
			
			$(this).each(function() {
				var $this = $(this);
				var value = $this.attr('data-value');
				
				if (!value) return $this;
				
				// 下拉框
				if ($this.is('select')) {
					var text = "";
					
					$this.find('option').each(function() {
						
						// 选中下拉框
						if (value == $(this).val()) {
							$(this).prop('selected', true);
							text = $(this).text();
							
							// 如果是chosen下拉框：
							if ('chosen' == $this.data('rel')) {
								$this.trigger('liszt:updated');
							}
							
							return false;
						}
						
					});
					
					$this.trigger('change');
					
				}
				// 多选框
				else if ($this.is(':checkbox')) {
					
					// TOFIX 现在后台返回会自动忽略重复，只取数组第一个值，需要修改
					var array = $.parseJSON(value);
					
					if ($.inArray($this.val(), array) > -1) {
						$this[0].click();
					}
				}
				
				// Radio
				else if ($this.is(':radio')) {
					
					if (value == $this.val() || 
							(value == 'true' && $this.val() == '1') || 
							(value == 'false' && $this.val() == '0')) {
						$this[0].click();
					}
				}
			});
			
			// 执行callback
			if ($.isFunction(callback)) {
				callback.call();
			}
			
			return $(this);
		},
		
		/**
		 * 文本框增强，添加已输入多少字信息
		 * @param options
		 */
		textareaPlus : function(options) {
			var update = function(textarea, countInfo) {
				var length = textarea.val().length;
				var html = "已输入<b>" + length + "</b>字";
				countInfo.html(html);
				
				var left = (textarea.width() - countInfo.width() - 5) + 'px';
				countInfo.css({
					left: left
				});
			};
			
			$(this).each(function() {
				var textarea = $(this);
				
				if (textarea.hasClass('cleditor') || textarea.hasClass('noPlus')) return true;
				
				textarea.focus(function() {
					var textarea = $(this);
					textarea.closest('div.controls').css({
						position: 'relative'
					});
					var countInfo = textarea.data('countInfo');
					
					// 第一次，新建countInfo
					if (!countInfo) {
						countInfo = $('<div class="countInfo"></div>').insertAfter(textarea);
						textarea.data('countInfo', countInfo);
					}
					
					countInfo.show();
					update(textarea, countInfo);
					
				}).blur(function() {
					var textarea = $(this);
					var countInfo = textarea.data('countInfo');
					
					countInfo.hide();
				}).keyup(function() {
					var textarea = $(this);
					var countInfo = textarea.data('countInfo');
					
					update(textarea, countInfo);
				}).resize(function() {
					var textarea = $(this);
					var countInfo = textarea.data('countInfo');
					
					update(textarea, countInfo);
				});
			});
			
			return $(this);
		},
		
		/**
		 * 表单增强：添加分页属性、输入框按回车提交、是否自动改变下拉框时提交表单等……
		 *
		 */
		formPlus : function(options) {
			$(this).each(function() {
				var form = $(this);
				var submit = form.find('a[target=submit],button[target=submit]');
				
				if (!options) {
					options = {changesubmit: form.data('changesubmit'),
							entersubmit: form.data('entersubmit')};
				}
				
				options = $.extend({
					/*改变下拉框时自动提交*/
					changesubmit: false
				}, options);
				
				if (form.attr('validate') == 'true') {
					form.validate();
				}
				
				// 分页标签
//				var paging = $(options.paging);
//				if (paging[0]) {
//					var numPerPage = paging.find('input[name=numPerPage]').val();
//					var pageNum =paging.find('input[name=pageNum]').val();
//					
//					// 修改分页值
//					if (!$('input[name=numPerPage]', form)[0]) {
//						form.find('fieldset').append('<input name="numPerPage" type="hidden" value="' + numPerPage + '" />');
//					}
//					else {
//						$('input[name=numPerPage]', form).val(numPerPage);
//					}
//					
//					if (!$('input[name=pageNum]', form)[0]) {
//						form.find('fieldset').append('<input name="pageNum" type="hidden" value="' + pageNum + '" />');
//					}
//					else {
//						$('input[name=pageNum]', form).val(pageNum);
//					}
//				}
				
				if (options.entersubmit) {
					// 输入框按回车自动提交
					form.find('input[type=text]').bind('keyup', function(event) {
						if (event.keyCode == Centit.keyCode.ENTER) {
							// 如果有提交按钮，点击提交按钮
							if (submit[0]) {
								submit.trigger('click');
							}
							// 没有提交按钮直接提交表单
							else {
								form[0].submit();
							}
						}
					});
				}
				
				
				// 判断是否改变下拉框值后提交
				if (!options.changesubmit) return true;
				
				form.find('input[type=radio],select').bind('change', function(event) {
					// 如果有提交按钮，点击提交按钮
					if (submit[0]) {
						submit.trigger('click');
					}
					// 没有提交按钮直接提交表单
					else {
						form[0].submit();
					}
				});
				
			});
			
			return $(this);
		},
		
		/**
		 * 分页插件增强，为每一个按钮URL添加参数（包括FORM参数和动态参数）
		 * 
		 * @param option = {
					form: 引用form的选择器,
					params: 动态参数。格式为：[selector1, selector1, ... , selector8, ...]
				};
		 */
		pagingPlus : function(option) {
			
			$(this).each(function() {
				var $this = $(this);
				
				if (!option) {
					var form = $this.attr('form');
					var params = $.parseJSON($this.attr('params'));
					
					option = {
						form: form,
						params: params
					};
				}
				
				// 如果没有FORM 取页面上第一个FORM
				if (!option.form) {
					option.form = $('form').eq(0);
				}
				
				// 动态参数
				var paramsString = getParamsString(option.params);
				
				// FORM参数
				var formParams = $(option.form).serialize();
				
				// 页码按钮的URL添加参数
				$('a', $this).each(function() {
					var url = $(this).attr('href');
					if (!url) return true;
					
					url = addParams4URL(url, params);
					url = addParams4URL(url, formParams);
					
					$(this).attr('href', url);
				});
				
				// 添加当前页和每页条数两个隐藏属性，为后续表单提交做准备
				var numPerPage = $this.attr('numPerPage');
				var pageNum = $this.find('a.current').text();
				
				if (!$('input[name=numPerPage]', $this)[0]) {
					$this.append('<input name="numPerPage" type="hidden" value="' + numPerPage + '" />');
				}
				
				if (!$('input[name=pageNum]', $this)[0]) {
					$this.append('<input name="pageNum" type="hidden" value="' + pageNum + '" />');
				}
			
			});
			
			return $(this);
		},
		
		/**
		 * 提交ajax请求
		 * 
		 * @param option = {
					href: 请求链接,
					action: 请求链接,
					params: 动态参数。格式为：[selector1, selector1, ... , selector8, ...]
				};
		 */
		ajaxTodo: function(option) {
			var $this = $(this);
			if (!option) {
				var href = $this.attr('href');
				var action = $this.attr('action');
				var params = $.parseJSON($this.attr('params'));
				var warn = $this.attr('warn');
				var callback = $this.attr('callback');
				
				option = {
					href: href,
					action: action,
					params: params,
					warn: warn,
					callback: callback || ajaxDone
				};
			}
			
			if (! $.isFunction(option.callback)) option.callback = eval('(' + option.callback + ')');
			
			// 有action的话优先使用action，其次href
			var url = action || href;
			var params = getParamsArray(option.params);
			
			// 提示信息
			if (warn) {
				msgConfirm(option.warn, {
					retryAction: function() {
						$.post(url, params, option.callback);
					}
				});
				
			}
			else {
				// Ajax请求
				$.post(url, params, ajaxDone);
			}
			
			return $this;
		},
		
		/**
		 * 自动提交form表单
		 * 
		 * @param option {
		 * 		form: 引用form的选择器,
		 * 		action: 如果有值，将改变form默认的action
		 * 		params: 动态参数。格式为：[selector1, selector1, ... , selector8, ...]
		 * }
		 * @returns {JQuery Object}
		 */
		autoSubmit: function(option) {
			
			var $this = $(this);
			if ($this.hasClass('disabled')) return;
			if (!option) {
				var form = $this.attr('form');
				var action = $this.attr('action');
				var params = $.parseJSON($this.attr('params'));
				var warn = $this.attr('warn');
				
				option = {
					form: form,
					action: action,
					params: params,
					warn: warn
				};
			}
			
			// 找FORM条件：1、参数自带	2、父级FORM		3、页面第一个FORM
			var form = $(option.form);
			if (!form[0]) {
				form = $this.closest('form');
			}
			if (!form[0]) {
				form = $('form').eq(0);
			}
			if (!form[0]) return false;
			
			var action = option.action;
			
			// 将原来字符串 ：[selector1, selector1, ... , selector8, ...]转换成符合url传参的形式：key1=value1&key2=value2...
			var params = getParamsString(option.params);
			
			// 如果按钮有action属性，则替换原来form的action
			var url = action ? action : form.attr('action');
			
			// 添加自带参数
			url = addParams4URL(url, params);
			
			// 序列化表格参数
//			url += form.serialize();
			
			var oldAction = form.attr('action');
			form.attr('action', url);
			
			// 提示信息
			if (warn) {
				msgConfirm(option.warn, {
					retryAction: function() {
						form[0].submit();
				    	form.trigger('submit');
				    	form.attr('action', oldAction);
					},
					cancelAction: function() {
						form.attr('action', oldAction);
						this.cancel();
					}
				});
			}
			else {
				form[0].submit();
				form.trigger('submit');
				form.attr('action', oldAction);
			}
			
			
			return $this;
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
			return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
		},
		isUrl:function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isExternalUrl:function(){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		},
		removeLastChar:function() {
			return this.substring(0, this.length-1);
		}
	});
	
});