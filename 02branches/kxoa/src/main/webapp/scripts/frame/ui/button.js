/**
 * 添加按钮事件
 * 
 * 提交表单
 * AJAX提交
 * 打开TAB页
 * 打开弹出窗口
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/17
 * @modify: 2014/4/17 by zk
 */

define(['jquery', 'Core', 'Tab', 'Dialog', 'Form', 'Msg'], function($, Core, Tab, Dialog, Form, Msg) {
	var Config = {
			SUBMIT_PROPERTIES: ['form', 'href', 'method']
	};
	
	/**
	 * 分页插件增强，为每一个按钮URL添加参数（包括FORM参数和动态参数）
	 * 
	 * @param option = {
				form: 引用form的选择器,
				params: 动态参数。格式为：[selector1, selector1, ... , selector8, ...]
			};
	 */
	pagingPlus = function(container) {
		
		$('div.pagination', container).each(function() {
			var $this = $(this);
			
			
			var form = $('form:last');
			
			
			// FORM参数
			var formParams = form.serialize();
			
			// 页码按钮的URL添加参数
			$('a', $this).each(function() {
				var url = $(this).attr('href');
				if (!url) return true;
				
				url = Core.addParams4URL(url, formParams);
				
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
		
	};

	function init(container) {
		
//		$('select', container).Core.interactSelect();
		
		
		
		//弹出验证
		$('button.delete,input.delete', container).click(function(event) {
			var warn = $(this).attr('warn');
			if (warn) {
				var result = confirm(warn);
				
				if (!result) {
					event.preventDefault();
					return false;
				}
			}
		});
		
		/*清空 refInput 需要清空的对象
		 * <span  class="add-on clearInput" refInput="#equipmentId,#equipmentName" title="点击清空">清空</span>
		 */
		$('.clearInput', container).click(function(event){
			var obj = $(this);
			obj.css({cursor:'pointer'});
			var ref = obj.attr('refInput');
			var inputs = [];
			
			if (ref) inputs = ref.split(',');
			for(var i=0; i<inputs.length; i++) {
				$(inputs[i]).val('');
			}
			});
		
		
		// 提交表单
		$('a[target=submit],input[target=submit],button[target=submit]', container).click(function(event) {
			var el = $(this), options = {}, form;
			el.readDatas(Config.SUBMIT_PROPERTIES, options);
			
			var warn = $(this).attr('warn');
			if (warn) {
				var result = confirm(warn);
				
				if (!result) {
					event.preventDefault();
					return false;
				}
			}
			
			form = Form.getForm(el, container);
			
			if (options.method) {
				form.attr('method', options.method);
			}
			
			if (options.href) {
				form.attr('action', options.href);
			}
			
			form.submit();

			event.preventDefault();
		});

        // 重置表单，清除验证提示
        $('input[type=reset],a[target=reset],input[target=reset],button[target=reset]', container).click(function(event) {
            var el = $(this), form = Form.getForm(el, container);
            
            if (form) {
                form.cleanupForm();
                
                if (form.resetForm) {
                	form.resetForm();
                }
            }
            
            event.preventDefault();
        });
        
        // 仅清除验证提示
        $('a[target=cleanup],input[target=cleanup],button[target=cleanup]', container).click(function(event) {
            var el = $(this), form = Form.getForm(el, container);
            
            if (form) {
                form.cleanupForm();
            }
            
            event.preventDefault();
        });
        
        // 下载新增
        $("a[target=download]", container).click(function(event) {
			var alink = $(this).css({
				cursor:'pointer'
			});
			var filecode = alink.data('filecode');

			// 下载回调函数
			var downloadFilesCallback = function(data) {
				if (!data || '0' != data.result) {
					Msg.warn(data.description);
					return false;
				}
				;

				var form = $('#downloadForm');

				if (!form[0]) {
					form = $("<form>"); // 定义一个form表单
					form.attr('id', 'downloadForm');
					form.attr('style', 'display:none'); // 在form表单中添加查询参数
					form.attr('target', '');
					form.attr('method', 'post');
					form.attr('action', Centit.contextPath
							+ '/app/fileinfoFs!downloadfile.do');

					var input = $('<input>');
					input.attr('type', 'hidden');
					input.attr('name', 'filecode');

					$('body').append(form); // 将表单放置在web中
					form.append(input); // 将查询参数控件提交到表单上
				}

				form.find('input[name=filecode]').val(data.filecode);
				form.submit();
			};

			if (filecode) {
				$.post(Centit.contextPath + '/app/fileinfoFs!toDownloadfile.do',
						{
							filecode : filecode
						}, downloadFilesCallback, 'json');
			} else {
				$.post(alink.attr('href'), downloadFilesCallback, 'json');
			}

			event.preventDefault();
			return false;
		});
        
        
        
        // 下载文件
	
		// 菜单标签栏
		Tab.init($('a[target=navTab],input[target=navTab],button[target=navTab]', container));
		
		// 弹出窗口
		Dialog.init($('a[target=dialog],input[target=dialog],button[target=dialog]', container));
		
		// TODO AJAX调用
		
		// 分页插件
		pagingPlus(container);
	}

	var B = {
         init: init
	};
	
	return B;
});