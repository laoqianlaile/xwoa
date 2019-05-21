define([
        'jquery', 
        'text!ui/upload/template/upload-dialog.xml',
        'text!ui/upload/template/upload-file.xml', 
        'Uploadify'], 
        function($, TEMPLATE_UPLOAD_DIALOG, TEMPLATE_UPLOAD_FILE) {

    // 自定义选择器
    SELECTORS = {
        FILE: 'input[type=file]',

        UNITBOX:	'div.unitBox',
        DIALOG:		'div.dialog',					// 系统对话框

        FILE_INFO:	'.file-info',					// 上传完成后显示内容
        TITLE_NUM:	'.info-num',					// 标题文件个数
        TITLE_SPEED:'.info-speed',					// 标题速度
        UPLOAD_DAILOG: '.upload-dialog',			// 上传文件窗口
        FILE_PROGRESS: '.title .inline-mask',		// 文件上传进度条
        CONTENT: 	'.content',						// 上传文件内容
        FILE_CONTENT:	'.upload-content',			// 上传文件内容
        BTN_CLOSE: 	'a.close',						// 关闭按钮
        BTN_MIN:	'a.min',						// 最小化按钮
        BTN_RESTORE:'a.restore'						// 恢复按钮
    }

    // 绘制上传窗口
    drawUploadDialog = function(settings) {
        var container = $('body');

        if (settings.queueContainer) {
            container = $('#'+settings.queueContainer, container);
        }

        var dialog = $(TEMPLATE_UPLOAD_DIALOG).appendTo(container);
        settings.dialog = dialog;

        return dialog;
    };
    
    // 附加事件
	addEvent = function(dialog) {
		$('.upload-dialog .close').die('click');
		$('.upload-dialog .close').live('click', function (event) {
			closeUploadDialog(this);
		});
		
		$('.upload-dialog .min').die('click');
		$('.upload-dialog .min').live('click', function (event) {
			minUploadDialog(this);
		});
		
		$('.upload-dialog .restore').die('click');
		$('.upload-dialog .restore').live('click', function (event) {
			restoreUploadDialog(this);
		});
		
		$('.file-info a.delete').die('click');
		$('.file-info a.delete').live('click', deleteFileValue);
	};
    
	// 打开文件上传窗口
	openUploadDialog = function(dialog) {
		dialog.show();
		
		restoreUploadDialog(dialog);
	};
	
	// 关闭文件上传窗口
	closeUploadDialog = function(obj) {
		var dialog = $(obj);
		
		// 点击按钮事件触发
		if ($(obj).is('a')) {
			dialog = $(obj).closest(SELECTORS.UPLOAD_DAILOG);
		}
		
		// TODO 清空上传文件队列
		
		var content = dialog.find(SELECTORS.FILE_CONTENT);
		
		// 清空
		content.html('');
		dialog.hide();
	};
	
	// 最小化文件上传窗口
	minUploadDialog = function(obj) {
		var dialog = obj;
		var btn = obj;
		
		// 点击按钮事件触发
		if ($(obj).is('a')) {
			dialog = $(btn).closest(SELECTORS.UPLOAD_DAILOG);
		}
		// 传入对象为上传文件对话框
		else {
			btn = dialog.find(SELECTORS.BTN_MIN);
		}
		
		$(btn).removeClass('min').addClass('restore').attr('title', '还原');
		var content = dialog.find(SELECTORS.CONTENT);
		content.slideUp();
	};
	
	// 恢复文件上传窗口
	restoreUploadDialog = function(obj) {
		var dialog = obj;
		var btn = obj;
		
		// 点击按钮事件触发
		if ($(obj).is('a')) {
			dialog = $(btn).closest(SELECTORS.UPLOAD_DAILOG);
		}
		// 传入对象为上传文件对话框
		else {
			btn = dialog.find(SELECTORS.BTN_RESTORE);
		}
		
		// 进度条清空
		this.setUploadFileProgress(dialog, 0);
		
		// 速度清空
		this.setTitleSpeed(dialog, '');
		
		$(btn).removeClass('restore').addClass('min').attr('title', '最小化');
		var content = dialog.find(SELECTORS.CONTENT);
		content.slideDown();
	};
	
	// 文件成功上传后将返回的文件ID写入到INPUT值中
	afterUploadSuccess = function(data) {
		
		if (data.result != '0') return;
		
		var input = $('#'+this.settings.inputId);
		var parent = $('#'+this.original[0].id).parent();
		
		if (!parent.find(SELECTORS.FILE_INFO)[0]) {
			parent.append('<table class="file-info"></table>');
		}
		var container = parent.find(SELECTORS.FILE_INFO);
		
		var file = data.file;
		var filename = file.fileext ? file.filename + '.' + file.fileext : file.filename;
		var filesize = (parseFloat(file.filesize) / (1024*1024)).toFixed(2) + ' MB';
		var filecode = file.filecode;
		
		container.append('<tr id="file_'+filecode+'" > <td>'+filename+'</td> <td>'+filesize+'</td> <td><a href="'+window.Centit.contextPath+'/app/fileinfoFs!deletefile.do?filecode='+filecode+'" class="delete" inputId="'+this.settings.inputId+'" filecode="'+filecode+'">删除</a></td><tr>');
	
		addFileValue(input, filecode);
	};
	
	addFileValue = function(input, filecode) {
		var values = input.val() ? input.val().split(',') : [];
		values.push(filecode);
		
		input.val(values.join(','));
	};
	
	deleteFileValue = function(event) {
		var $this = $(this);
		var input = $('#'+$this.attr('inputId'));
		var filecode = $this.attr('filecode');
		
		var values = input.val() ? input.val().split(',') : [];
		
		for (var i=0; i<values.length; i++) {
			if (values[i] == filecode) {
				values.splice(i, 1);
			} 
		}
		event.preventDefault();
		
		// 发送ajax删除文件
		
		$.getJSON($this.attr('href'), function(data) {
			if (data.result != '0') {
				alertMsg.warn(data.description);
				return false;
			}
			
			$this.closest('tr').fadeOut(function() {
				$(this).remove();
				input.val(values.join(','));
			});
		});
		
		return false;
	};
	
	// 设置文件上传进度条百分比，progress百分比整数
	setUploadFileProgress = function(dialog, progress) {
		dialog.find(SELECTORS.FILE_PROGRESS).css({
			width: progress+'%'
		});
	};
	
	// 设置标题
	setTitle = function(dialog, value) {
		var info = dialog.find(SELECTORS.TITLE_NUM);
		info.html(value);
	};
	
	// 设置文件个数标题
	setTitleNumber = function(dialog, value) {
		var info = dialog.find(SELECTORS.TITLE_NUM);
		
		if (value) {
			info.html('文件个数：' + value);
		}
		else {
			info.html('');
		}
	};
	
	// 设置速度标题
	setTitleSpeed = function(dialog, value) {
		var info = dialog.find(SELECTORS.TITLE_SPEED);
		
		if (value) {
			info.html('速度：' + value);
		}
		else {
			info.html('');
		}
	};
	
	// 获取已经上传文件个数
	getUploadDialogFileSize = function(files) {
		var length = 0;
		for (var n in files) {
			if (typeof files[n] === 'object') length++;
		}
		
		return length;
	};
	
	// 初始化函数
	onInit = function(instance) {
		var dialog = instance.settings.dialog;
		
		$(instance).data('_data', {
			all_num:0,		// 总共上传文件数
			now_num:0		// 正在上传的文件序号
		}).data('dialog', dialog);
	};
	
	// 选择文件后事件
	onSelect = function(file) {
		var dialog = $(this).data('dialog');
		
		openUploadDialog(dialog);
	};
	
	// 开始上传文件事件
	onUploadStart = function(file) {
		var callback = this.settings.dynamicFormData;
		
		if (callback) {
			$('#'+this.original[0].id).uploadify("settings", "formData", callback());
		}
		
		var data = $(this).data('_data');
		var dialog = $(this).data('dialog');
		
    	var all_num = getUploadDialogFileSize(this.queueData.files);
    	var now_num = data.now_num+1;
    	
    	data.all_num = all_num;
    	data.now_num = now_num;
    	
    	// 重新设置上传文件个数
    	setTitleNumber(dialog, now_num + '/' + all_num);
	};
	
	// 正在上传文件事件
	onUploadProgress = function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
		var data = $(this).data('_data');
		var dialog = $(this).data('dialog');
		
		var all_num = getUploadDialogFileSize(this.queueData.files);
    	var now_num = data.now_num;
    	
    	data.all_num = all_num;
    	
    	// 重新设置上传文件个数
    	setTitleNumber(dialog, now_num + '/' + all_num);
    	
    	// 上传窗口最小化时才显示速度和进度
    	if (!dialog.find(SELECTORS.CONTENT).is(':visible')) {
    		setTitleSpeed(dialog, this.queueData.averageSpeed + this.queueData.suffix);
    		setUploadFileProgress(dialog, this.queueData.percentage);
    	}
	};
	
	// 文件成功上传后事件
	onUploadSuccess = function (file, data, response) {
		var callback = this.settings.uploadCallback;
		
		if (callback) {
			callback.call(this, $.parseJSON(data));
		}
		else {
			afterUploadSuccess.call(this, $.parseJSON(data));
		}
		
	};
	
	// 全部文件上传完成
	onQueueComplete = function(queueData) {
		var dialog = $(this).data('dialog');
		setTitle(dialog, '上传完成');
		
		// 进度、速度清空
		setTitleSpeed(dialog, '');
		setUploadFileProgress(dialog, 0);
		
		// 最小化窗口
		setTimeout(function() {
			minUploadDialog(dialog);
			
			
		}, 1000);
		
		//上传完关闭上传窗口
		setTimeout(function() {
			closeUploadDialog(dialog);
			
		}, 2000);
		
	};
	
	// 定义一些常量
    Config = {
        PORPERTIES: [
            'optId',
            'inputId',
            'queueContainer',
            'uploader',
            'buttonText',
            'fileTypeDesc',
            'fileTypeExts',
            'queueSizeLimit',
            'uploadLimit',
            'multi',
            'uploadCallback',
            'dynamicFormData'],

        DEFAULT_OPTIONS: {
            // 可选选项
            buttonText: '上传文件',
            removeCompleted: false,
            queueID:'upload-content',

            onInit: onInit,
            onSelect: onSelect,
            onUploadStart: onUploadStart,
            onUploadProgress: onUploadProgress,
            onUploadSuccess: onUploadSuccess,
            onQueueComplete: onQueueComplete,

            // 必选设置
            swf:window.Centit.contextPath+'/scripts/frame/components/jquery/jquery.uploadify/uploadify.swf',
            uploader: window.Centit.contextPath+'/app/fileinfoFs!uploadfile.do'

        }
    };
    var U = {
        init: function(container, options) {
        	
            $(SELECTORS.FILE, container).each(function() {
                
                var settings = $.extend({}, Config.DEFAULT_OPTIONS, options) , input = $(this);
                
                // 设置选项			   	
                input.readDatas(Config.PORPERTIES, settings);
                settings.formData = {optId:settings.optId};
                settings.swf = settings.swf+'?var='+(new Date().getTime());
               
                // 绘制窗口
                var dialog = drawUploadDialog(settings);

                // 附加事件
                addEvent(dialog);

                // 上传插件
                input.uploadify(settings);
            });
        }
    };

    return U;
});