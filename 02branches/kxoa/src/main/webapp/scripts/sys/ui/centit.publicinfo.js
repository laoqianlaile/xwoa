$(function() {
	/**
	 * 获取文件类型 ：公共文件夹、共享等
	 * @param {Object} file
	 */
	function getFolderType(file) {
		if (!file) return "";
		
		if ('1' == file.foldertype) return "public";
		
		if ('3' == file.foldertype) return "share";
		
		return "";
	}
	
	/**
	 * 获取文件类型
	 */
	function getFileType(file) {
		if (!file) return "";
		
		// 文件夹
		if (file.isfolder=='1') return "folder";
		var type = $.publicinfo.FILE_TYPES[file.fileextension.toLowerCase()];
		return type ? type : "";
	}
	
	/**
	 * 获取文件url
	 */
	function getFileURL(file) {
		// 文件夹
		if (file.isfolder=='1') {
			return $.publicinfo.FILE_OPTION.public_url+'?path='+file.infocode;
		}
		else if ($.publicinfo.CAN_VIEW[getFileType(file)]) {
			return $.publicinfo.FILE_OPTION.view_url+'?infocode='+file.infocode+'&type='+getFileType(file);
		}
		// 查看文件
		else {
			return 'javascript:;';
		}
	}
	
	/**
	 * 获取链接类型
	 */
	function getLinkType(file) {
		// 文件夹
		if (file.isfolder=='1') {
			return 'dir';
		}
		else if ($.publicinfo.CAN_VIEW[getFileType(file)]) {
			return 'view';
		}
		// 查看文件
		else {
			return 'none';
		}
	}
	
	/**
	 * 获取文件大小
	 */
	function getFileSize(file) {
		// 文件夹
		if (file.isfolder=='1') {
			return '-';
		}
		// 查看文件
		else {
			var size = parseFloat(file.filesize);
			
			if (size < 1024) return size + "B";
			
			size >>= 10;
			if (size < 1024) return size + "KB";
			
			size >>= 10;
			if (size < 1024) return size + "MB";
			
			size >>= 10;
			if (size < 1024) return size + "GB";
			
			size >>= 10;
			if (size < 1024) return size + "TB";
			
		}
	}
	
	/**
	 * 获取文件全名
	 */
	function getFullFilename(file) {
		return file.fileextension ? file.filename+'.'+file.fileextension : file.filename;
	}
	
	/**
	 * 获取文件上传时间
	 */
	function getFileUploadtime(file) {
		return file.uploadtime.replaceAll('T', ' ');
	}
	
	/**
	 * 保存数据
	 * 
	 * @param line 文件对象行
	 * @param file 文件JSON对象
	 */
	 function _saveData(line, file) {
		line.data('authority', file.authority)
			.data('dounloadcount', file.downloadcount)
			.data('filedescription', file.filedescription)
			.data('fileextension', file.fileextension)
			.data('filename', file.filename)
			.data('filesize', file.filesize)
			.data('infocode', file.infocode)
			.data('isfolder', file.isfolder)
			.data('modifytime', file.modifytime)
			.data('ownercode', file.ownercode)
			.data('uploader', file.uploader)
			.data('readcount', file.readcount)
			.data('status', file.status)
			.data('type', file.foldertype)
			.data('unitcode', file.unitcode)
			.data('uploadtime', file.uploadtime);
			
	}
	 
	 /**
	  * 按文件名升序排序
	  */
	 function ArraysSortByNameAsc(file1, file2) {
			var name1 = file1.data('filename');
			var name2 = file2.data('filename');
			
			if (name1 > name2) return 1;
			if (name2 > name1) return -1;
			
			return 0;
		}
	 
	 /**
	  * 按文件名降序排序
	  */
	function ArraysSortByNameDesc(file1, file2) {
		var name1 = file1.data('filename');
		var name2 = file2.data('filename');
		
		if (name1 > name2) return -1;
		if (name2 > name1) return 1;
		
		return 0;
	}
	
	/**
	 * 按文件大小升序排序
	 */
	function ArraysSortBySizeAsc(file1, file2) {
		var size1 = file1.data('filesize');
		var size2 = file2.data('filesize');
		
		if (size1 > size2) return 1;
		if (size2 > size1) return -1;
		
		return 0;
	}

	/**
	 * 按文件大小降序排序
	 */
	function ArraysSortBySizeDesc(file1, file2) {
		var size1 = file1.data('filesize');
		var size2 = file2.data('filesize');
		
		if (size1 > size2) return -1;
		if (size2 > size1) return 1;
		
		return 0;
	}

	/**
	 * 按上传者名称升序排序
	 */
	function ArraysSortByUploaderAsc(file1, file2) {
		var uploader1 = file1.data('uploader');
		var uploader2 = file2.data('uploader');
		
		if (uploader1 > uploader2) return 1;
		if (uploader2 > uploader1) return -1;
		
		return 0;
	}
	
	/**
	 * 按上传者降序排序
	 */
	function ArraysSortByUploaderDesc(file1, file2) {
		var uploader1 = file1.data('uploader');
		var uploader2 = file2.data('uploader');
		
		if (uploader1 > uploader2) return -1;
		if (uploader2 > uploader1) return 1;
		
		return 0;
	}

	/**
	 * 按上传时间升序排序
	 */
	function ArraysSortByUploadTimeAsc(file1, file2) {
		var uploadtime1 = file1.data('uploadtime');
		var uploadtime2 = file2.data('uploadtime');
		
		if (uploadtime1 > uploadtime2) return 1;
		if (uploadtime2 > uploadtime1) return -1;
		
		return 0;
	}

	/**
	 * 按上传时间降序排序
	 */
	function ArraysSortByUploadTimeDesc(file1, file2) {
		var uploadtime1 = file1.data('uploadtime');
		var uploadtime2 = file2.data('uploadtime');
		
		if (uploadtime1 > uploadtime2) return -1;
		if (uploadtime2 > uploadtime1) return 1;
		
		return 0;
	}
	
	/**
	 * 获取排序顺序
	 */
	function getSort(info) {
		var btn = info.find('span.sort');
		
		// 正序
		var sort = 1;
		
		// 倒序
		if (btn.hasClass('asc')) {
			sort = 0;
		}
		
		return sort;
	}
	 
	 /**
	  * 创建路径栏
	  */
	 function createPath(data) {
	 	$('.line.path span.ready').show();
	 	$('.line.path span.refresh').hide();
	 	
	 	var line = $('.file-container div.line.path');
	 	var unitroot = data.unitroot;
	 	var parentcode = data.parentcode;
	 	var infocode = data.infocode;
	 	
	 	// 返回上一级
	 	if (parentcode && parentcode != '0') {
	 		$('a.uplevel', line).attr('href', $.publicinfo.FILE_OPTION.public_url+'?path='+parentcode).show();
	 	}
	 	else {
	 		$('a.uplevel', line).hide();
	 	}
	 	
	 	// 部门主页
	 	if (infocode == unitroot) {
	 		$('a.home', line).hide();
	 	}
	 	else {
	 		$('a.home', line).attr('href', $.publicinfo.FILE_OPTION.public_url+'?path='+unitroot).show();
	 	}
	 	
	 	// 路径
	 	$('div.ed', line).html('');
	 	
	 	if (data.path) {
	 		var path = data.path;
	 		var length = data.path.length;
	 		
	 		for (var i=0; i<length; i++) {
	 			if (i == length - 1) {
	 				$('div.ed', line).append('&nbsp;&nbsp;<a class="patha text">'+ path[i].FILENAME +'</a>&nbsp;&nbsp;');
	 			}
	 			else {
	 				$('div.ed', line).append('&nbsp;&nbsp;<a class="patha" type="dir" href="' + $.publicinfo.FILE_OPTION.public_url+'?path='+path[i].INFOCODE+'">'+ path[i].FILENAME +'</a>&nbsp;&nbsp;&gt;');
	 			}
	 		}
	 	}
	 	
	 	// 加载总数
	 	$('i', line).html(data.files.length);
	 	
	 }
	 
	 /**
	  * 选择文件
	  */
	 function selectHead() {
		var container = $('div.file-container');
		
		if ($.publicinfo.SELECTED_FILES.length == 0) {
			$('div.line.operation', container).hide();
			$('div.header', container).show().removeClass('selected');
		}
		else {
			$('div.line.operation', container).show().find('i').html($.publicinfo.SELECTED_FILES.length);
			$('div.header', container).hide();
		}
	}
	 
	/**
	 * 校验文件、文件夹名
	 * 
	 * @returns
	 */
	function validateAddFolder() {
		var container = $('#public-window');
		var div = $('div.line.new', container);
		var input = $('input', div).select();
		
		if (!input.val()) {
			return "请填写文件夹名";
		}
		
		if (/\.|\*|\?|\%|\_/.test(input.val())) {
			return "文件夹名不能包含.*?%_等特殊字符，请重新输入。";
		}
		
		return true;
	}
	
	/**
	 * 校验重命名
	 */
	function validateRename() {
		
		var rename = $('#rename-container');
		var newName = rename.find('input').val();
		
		if (/\.|\*|\?|\%|\_/.test(newName)) {
			rename.find('input').select();
			return '文件名不能包含.*?%_等特殊字符，请重新输入。';
		}
		
		return true;
	}
	
	$.publicinfo = {
			/*公共文件设置*/
			FILE_OPTION : {
				public_url: Config.contextPath+'/app/publicinfo!dirPublicFolder.do',		// 查询公共文件夹
				personal_url: Config.contextPath+'/app/publicinfo!dirPersonalFolder.do',	// 查询个人文件夹
				view_url: Config.contextPath+'/app/publicinfo!view.do',						// 查看文件
				add_url: Config.contextPath+'/app/publicinfo!addFolder.do',					// 添加文件夹
				download_url: Config.contextPath+'/app/publicinfo!download.do',		// 下载文件（公共文件夹处理）
				toDownload_file_url: Config.contextPath+'/app/fileinfo!toDownloadfile.do',	// 准备下载文件（文件处理）
				download_file_url: Config.contextPath+'/app/fileinfo!downloadfile.do',		// 下载文件（文件处理）
				delete_url: Config.contextPath+'/app/publicinfo!delete.do',					// 删除文件
				rename_url: Config.contextPath+'/app/publicinfo!rename.do',					// 重命名文件
				copy_url: Config.contextPath+'/app/publicinfo!copy.do',						// 复制文件
				move_url: Config.contextPath+'/app/publicinfo!move.do',						// 移动文件
				upload_url: Config.contextPath+'/app/publicinfo!upload.do',					// 上传文件
				search_url: Config.contextPath+'/app/searcher!retrieve.do',
				
				AUTH_VIEW:0,				// 查看权限位置
				AUTH_ADD:1,					// 添加权限位置
				AUTH_MODIFY:2,				// 修改权限位置
				
				TYPE_PUBLIC_CUSTOM:0,		// 公共自定义类型
				TYPE_PUBLIC_DEFAULT:1,		// 公共默认类型
				TYPE_PERSONAL_CUSTOM:2,		// 个人自定义类型
				TYPE_PERSONAL_DEFAULT:3,	// 个人默认类型
				
				RENAME_CONTAINER: $('#rename-container') // 重命名控件

			},
			
			STATUS:[0],					// 当前状态栈
			MODEL:0,					// 0 公共文件夹；1 个人文件夹
			
			FILES: [],					// 文件
			FOLDERS: [],				// 文件夹
			SELECTED_FILES: [],			// 选中的文件、文件夹
			
			CAN_VIEW : {
					'img':1,'pdf':1,'word':1,'txt':1,'ppt':1,'excel':1
			},
			
			/*文件类型*/
			FILE_TYPES : {
					'bmp':'img',
					'gif':'img',
					'jpeg':'img',
					'jpg':'img',
					'png':'img',
					'vsd':'visio',
					'pdf':'pdf',
					'doc':'word',
					'docx':'word',
					'xls':'excel',
					'xlsx':'excel',
					'txt':'txt',
					'wav':'music',
					'mp3':'music',
					'mov':'movie',
					'rm':'movie',
					'rmvb':'movie',
					'avi':'movie',
					'mkv':'movie',
					'ppt':'ppt',
					'pptx':'ppt',
					'app':'apple',
					'exe':'exe',
					'zip':'zip',
					'rar':'zip',
					'7z':'zip',
					'apk':'apk'
			},
			
			AUTHENTICATE_MAP : {
				"addFolder": {pos: 1, msg: "您没有权限在此目录新建文件夹。"},
				"rename": {pos: 2, msg: "您没有权限修改文件（文件夹）名"}
			},
			
			VALIDATE_MAP : {
				"addFolder": validateAddFolder,
				"rename": validateRename
			},
			
			/**
			 * 操作状态的3个函数
			 * @returns
			 */
			getStatus : function() {
				return $.publicinfo.STATUS[$.publicinfo.STATUS.length - 1];
			},
			
			popStatus : function() {
				$.publicinfo.STATUS.pop();
			},
			
			pushStatus : function(status) {
				$.publicinfo.STATUS.push(status);
			},
			
			clearStatus: function() {
				$.publicinfo.STATUS.length = 0;
				$.publicinfo.STATUS.push(0);
			},
			
			/**
			 * 鉴权中心
			 * @param name
			 */
			authenticate: function() {
				// 若只有一个参数，则从MAP里直接计算权限
				if (1 == arguments.length) {
					var name = arguments[0];
					
					var info = this.AUTHENTICATE_MAP[name];
					var auth = this.FILE_OPTION.authority;
					var pos = info.pos;
					
					return ((auth >> pos) % 2) == 1 ? true : info.msg;
				}
				var auth = arguments[0];
				var pos = arguments[1];
				
				return ((auth >> pos) % 2) == 1;
			},
			
			/**
			 * 校验中心
			 * @param name
			 */
			validate: function() {
				var params = [];
				for (var i=0; i<arguments.length; i++) {
					params.push(arguments[i]);
				}
				var name = params[0];
				params.shift();
				
				var func = this.VALIDATE_MAP[name];
				
				if (func) {
					return func.apply(this, params);
				}
			},
			
			/**
			 * 初始化
			 */
			init: function(mode) {
				$.publicinfo.MODE = mode;
				
				// 加载文件夹
				this.dirPublicFolder();
				
				var container = $('#public-window');
				
				
				// 更多按钮
				popMenuHandle = null;
				$('.line.operation a.more').add('ul.pull-down-menu.header-menu').hover(function(event) {
					$.publicinfo.openPopMenu(this);
				}, function(event) {
					popMenuHandle = setTimeout("$.publicinfo.closePopMenu()", 50);
				});
				
				// 事件初始化
				var publicinfoManager = new EventManager("publicinfo");
				publicinfoManager.debug(false);
				
				// 鉴权函数后置
				publicinfoManager.addAfterInterceptor("authenticate", function(result) {
					if (result === true) {
						return true;
					};
					
					Centit.msgError(result);
					return false;
				});
				
				// 校验函数后置
				publicinfoManager.addAfterInterceptor("validate", function(result) {
					if (result === true) {
						return true;
					};
					
					if (result === false) {
						return false;
					}
					
					Centit.msgError(result);
					return false;
				});
				
				// 回调函数前置
				publicinfoManager.addBeforeInterceptor("callback", function(data) {
					if (!data || '0' != data.result) {
						Centit.msgError(data.description);
						return false;
					};
					return true;
				});
				
				// 查看文件夹
				publicinfoManager.addEvent("dirFolder")
					.addEventLitsen("excute", "a[type=dir]", "live", "click")
					.setEventAction("excute", function(event, obj) {
						var eventObject = this;
						$.getJSON(obj.href, {mode: $.publicinfo.MODE}, function(data) {
							eventObject.excuteAction("callback", data);
						});
						event.preventDefault();
					})
					.setEventAction("callback", $.publicinfo.dirPublicFolderCallback);
				
				// 新增文件夹
				publicinfoManager.addEvent("addFolder")
					// 鉴权是否有权限新增文件夹
					.setEventAction("authenticate", function() {
						return $.publicinfo.authenticate("addFolder");
					})
					
					// 打开新增文件夹控件
					.addEventLitsen("prepare", "div.line.navigation a.add", "click")
					.setEventAction("prepare", function(event, obj, result) {
						if (result === true && $.publicinfo.getStatus() == 0) {
							$.publicinfo.toAddFolder();
						}
						event.preventDefault();
					})
					
					// 关闭新增文件夹控件
					.addEventLitsen("cancel", $('div.line.new a.cancel', container), "live", "click")
					.setEventAction("cancel", $.publicinfo.cancelAddFolder)
					
					// 校验新增文件夹
					.setEventAction("validate", function() {
						return $.publicinfo.validate("addFolder");
					})
					
					// 新增文件夹
					.addEventLitsen("excute", $('div.line.new a.sure', container), "live", "click")
					.setEventAction("excute", $.publicinfo.addFolder)
					
					// 新增文件夹回调
					.setEventAction("callback", $.publicinfo.addFolderCallback);
				
				// 重命名文件、文件夹
				publicinfoManager.addEvent("rename")
				
					// 鉴权是否具有权限修改文件名
					.setEventAction("authenticate", function() {
						var file = $.publicinfo.SELECTED_FILES[0];
						return $.publicinfo.authenticate(file.data('authority'), $.publicinfo.FILE_OPTION.AUTH_MODIFY);
					})
					
					// 打开重命名控件
					.addEventLitsen("prepare", "ul.pull-down-menu.header-menu a.rename", "click")
					.setEventAction("prepare", function(event, obj, result) {
						if (result === true && $.publicinfo.getStatus() == 0 && $.publicinfo.SELECTED_FILES.length == 1) {
							$.publicinfo.toRename();
						}
					})
					
					// 关闭重命名控件
					.addEventLitsen("cancel", "#rename-container a.cancel", "click")
					.setEventAction("cancel", $.publicinfo.cancelRename)
					
					// 校验重命名
					.setEventAction("validate", function() {
						return $.publicinfo.validate("rename");
					})
					
					// 重命名
					.addEventLitsen("excute", "#rename-container a.sure", "click")
					.setEventAction("excute", $.publicinfo.rename)
					
					// 重命名回调
					.setEventAction("callback", $.publicinfo.renameCallback);
				
				// 选择文件、文件夹
				publicinfoManager.addEvent("selectFile")
					.addEventLitsen("excute", $('div.line', container), "live", "click")
					.setEventAction("excute", $.publicinfo.selectFile);
				
				// 全选文件、文件夹
				publicinfoManager.addEvent("selectAllFile")
					.addEventLitsen("excute", $('div.line.operation span.check').add('div.header span.check'), "click")
					.setEventAction("excute", $.publicinfo.selectAllFile);
				
				// 下载文件
				publicinfoManager.addEvent("download")
					.addEventLitsen("excute", ".line.operation a.download", "click")
					.setEventAction("excute", $.publicinfo.downloadFile)
					.setEventAction("callback", $.publicinfo.downloadFileCallback);
				
				// 临时下载文件
				publicinfoManager.addEvent("downloadTemp")
					.addEventLitsen("excute", "a[type=view]", "live", "click")
					.setEventAction("excute", function(event, obj) {
						var code = $(obj).closest('div.line').data('infocode');
						var eventObject = this;
						$.post($.publicinfo.FILE_OPTION.download_url, {infocode: code, mode: $.publicinfo.MODE}, function(data){
							eventObject.excuteAction("callback", data);
						}, 'json');
						
						event.preventDefault();
					})
					.setEventAction("callback", $.publicinfo.downloadFileCallback);
				
				// 删除文件
				publicinfoManager.addEvent("delete")
					.addEventLitsen("excute", ".line.operation a.delete", "click")
					.setEventAction("excute", $.publicinfo.deleteFile)
					.setEventAction("callback", $.publicinfo.deleteFileCallback);
				
				// 排序-名称
				publicinfoManager.addEvent("sort-name")
					.addEventLitsen("excute", ".line.header div.name", "click")
					.setEventAction("excute", $.publicinfo.sortByName);
				
				// 排序-大小
				publicinfoManager.addEvent("sort-size")
					.addEventLitsen("excute", ".line.header div.size", "click")
					.setEventAction("excute", $.publicinfo.sortBySize);
				
				// 排序-上传人
				publicinfoManager.addEvent("sort-uploader")
					.addEventLitsen("excute", ".line.header div.owner", "click")
					.setEventAction("excute", $.publicinfo.sortByUploader);
				
				// 排序-上传时间
				publicinfoManager.addEvent("sort-time")
					.addEventLitsen("excute", ".line.header div.time", "click")
					.setEventAction("excute", $.publicinfo.sortByUploadTime);
				
				// 弹出窗口
				$.publicinfo.COPY_MOVE_WINDOW = $('#file_window');
				
				// 复制、移动文件
				publicinfoManager.addEvent("copymoveFile")
					// 校验选择的目标文件夹
					.setEventAction("validate", function() {
						var type = $.publicinfo.COPY_MOVE_WINDOW.find('input').val();
						
						if (type != 'copy' && type != 'move') {
							return "窗口类型不正确";
						}
						
						var nodes = $.publicinfo.ZTREE_FOLDER.getSelectedNodes();
						return $.publicinfo.validateCopyMoveFile(nodes,type);
					})
					// 提交复制、移动文件请求
					.addEventLitsen("excute", "#file_window button.sure", "click")
					.setEventAction("excute", $.publicinfo.copyMoveFile)
					// 复制、移动文件回调
					.setEventAction("callback", $.publicinfo.copyMoveFileCallback);
					
				// 搜索
				publicinfoManager.addEvent('search')
					.setEventAction('validate', function(event, obj) {
						if (!$('#searchInput').val()) {
							return false;
						}
						
						return true;
					})
					.addEventLitsen("excute", "#search-text span", "click")
					.addEventLitsen("excute", "#searchInput", "keyup")
					.setEventAction('excute', function(event, obj, result) {
						if (result === false) return;
						
						if ($(obj).is('input')) {
							if (event.keyCode != Centit.keyCode.ENTER) {
								return false;
							}
						}
						
						var value = $('#searchInput').val().trim();
						window.parent.HROS.window.createTemp({title:"文件搜索",url:$.publicinfo.FILE_OPTION.search_url+"?keywords="+value+"&mode="+$.publicinfo.MODE,width:800,height:400,isresize:false,isopenmax:true,isflash:false})
					});
				
				publicinfoManager.init();
			},
			
			/**
			 * 校验复制、移动文件
			 */
			validateCopyMoveFile: function(nodes, type) {
				if (0 == nodes.length) {
					return "请选择一个文件夹";
				}
				
				var node = nodes[0];
				var auth = node.authority;
				if (!$.publicinfo.authenticate(auth, $.publicinfo.FILE_OPTION.AUTH_ADD)) {
					return "没有权限向目标文件夹复制或项目";
				}
				
				var flag = false;
				if ('move' == type) {
					for (var i=0; i<$.publicinfo.SELECTED_FILES.length; i++) {
						var file = $.publicinfo.SELECTED_FILES[i];
						if (!$.publicinfo.authenticate(file.data('authority'), $.publicinfo.FILE_OPTION.AUTH_MODIFY)) {
							file.trigger('click');
							flag = true;
						}
					}
				}
				
				if (0 != $.publicinfo.SELECTED_FILES.length && flag) {
					Centit.msgAlert("有部分选择项目无权限移动，仅会操作可移动项目。");
				}
				
				else if (0 == $.publicinfo.SELECTED_FILES.length) {
					$.publicinfo.COPY_MOVE_WINDOW.modal('hide');
					return "没有权限移动源文件。";
				}
				
				return true;
			},
			
			/**
			 * 执行复制、移动文件
			 */
			copyMoveFile: function(event, obj, result) {
				if (result !== true) {
					return;
				}
				
				var node = $.publicinfo.ZTREE_FOLDER.getSelectedNodes()[0];
				var type = $.publicinfo.COPY_MOVE_WINDOW.find('input').val();
				var files = [];
				var eventObject = this;
				
				for (var i=0; i<$.publicinfo.SELECTED_FILES.length; i++) {
					var line = $.publicinfo.SELECTED_FILES[i];
					files.push(line.data('infocode'));
				}
				
				if (type == 'copy') {
					$.post($.publicinfo.FILE_OPTION.copy_url, {infocode: files.join(','), toInfocode: node.id, mode: $.publicinfo.MODE}, function(data) {
						eventObject.excuteAction("callback", data);
					}, 'json');
				}
				else if (type == 'move') {
					$.post($.publicinfo.FILE_OPTION.move_url, {infocode: files.join(','), toInfocode: node.id, mode: $.publicinfo.MODE}, function(data) {
						eventObject.excuteAction("callback", data);
					}, 'json');
				}
				
			},
			
			/**
			 * 复制、移动文件回调
			 * @param data
			 */
			copyMoveFileCallback: function(data, result) {
				if (result !== true) {
					return;
				}
				
				var file = data.file;
				var type = $.publicinfo.COPY_MOVE_WINDOW.find('input').val();
				var typeName = (type == 'copy') ? "复制" : "移动";
				
				if (file.infocode == $.publicinfo.FILE_OPTION.infocode) {
					Centit.msgAlert(typeName + '文件成功');
					$.publicinfo.dirPublicFolder();
					$.publicinfo.COPY_MOVE_WINDOW.modal('hide');
					return;
				}
				
				Centit.msgConfirm(typeName + '文件成功，是否跳转到 '+file.filename+' 去查看？', {
					retryAction: function() {
						$.publicinfo.dirPublicFolder(file.infocode);
					}
				});
				
				$.publicinfo.COPY_MOVE_WINDOW.modal('hide');
			},
			
			/**
			 * 按上传者排序
			 */
			sortByUploader: function(event, info) {
				info = $(info);
				var sort = getSort(info);
				
				if (sort) {
					$.publicinfo.FOLDERS.sort(ArraysSortByUploaderAsc);
					$.publicinfo.FILES.sort(ArraysSortByUploaderAsc);
				}
				else {
					$.publicinfo.FOLDERS.sort(ArraysSortByUploaderDesc);
					$.publicinfo.FILES.sort(ArraysSortByUploaderDesc);
				}
				
				$.publicinfo.sortFileIcon(info, sort);
			},

			/**
			 * 按上传时间排序
			 */
			sortByUploadTime: function(event, info) {
				info = $(info);
				var sort = getSort(info);
				
				if (sort) {
					$.publicinfo.FOLDERS.sort(ArraysSortByUploadTimeAsc);
					$.publicinfo.FILES.sort(ArraysSortByUploadTimeAsc);
				}
				else {
					$.publicinfo.FOLDERS.sort(ArraysSortByUploadTimeDesc);
					$.publicinfo.FILES.sort(ArraysSortByUploadTimeDesc);
				}
				
				$.publicinfo.sortFileIcon(info, sort);
			},
			
			/**
			 * 按大小排序
			 */
			sortBySize: function(event, info) {
				info = $(info);
				var sort = getSort(info);
				
				if (sort) {
					$.publicinfo.FOLDERS.sort(ArraysSortBySizeAsc);
					$.publicinfo.FILES.sort(ArraysSortBySizeAsc);
				}
				else {
					$.publicinfo.FOLDERS.sort(ArraysSortBySizeDesc);
					$.publicinfo.FILES.sort(ArraysSortBySizeDesc);
				}
				
				$.publicinfo.sortFileIcon(info, sort);
			},
			
			/**
			 * 按名称排序
			 */
			sortByName: function(event, info) {
				info = $(info);
				var sort = getSort(info);
				
				if (sort) {
					$.publicinfo.FOLDERS.sort(ArraysSortByNameAsc);
					$.publicinfo.FILES.sort(ArraysSortByNameAsc);
				}
				else {
					$.publicinfo.FOLDERS.sort(ArraysSortByNameDesc);
					$.publicinfo.FILES.sort(ArraysSortByNameDesc);
				}
				
				$.publicinfo.sortFileIcon(info, sort);
			},
			
			/**
			 * 排序文件图标
			 * @param info
			 * @param sort
			 */
			sortFileIcon: function(info, sort) {
				var container = $('#public-window');
				
				// 去除原来的排序图标
				info.closest('div.line').find('span.sort').removeClass('asc').removeClass('desc');
				
				if (sort) {
					$('span.sort', info).addClass('asc');
				}
				else {
					$('span.sort', info).addClass('desc');
				}
				
				// 去除原来最后.last样式
				$('div.last.line', container).removeClass('last');
				
				// 隐藏，避免在排序时渲染屏幕
				container.hide();
				container.detach('div.line');
				
				for (var i=0; i<$.publicinfo.FOLDERS.length; i++) {
					container.append($.publicinfo.FOLDERS[i]);
				}	
				for (var i=0; i<$.publicinfo.FILES.length; i++) {
					container.append($.publicinfo.FILES[i]);
				}	
				
				container.show().find('div.line:last').addClass('last');
			},
			
			/**
			 * 删除文件
			 */
			deleteFile: function() {
				var files = [];
				var eventObject = this;
				var noAuthorityFileCount = 0;
				
				// 取可以删除的文件
				for (var i=0; i<$.publicinfo.SELECTED_FILES.length; i++) {
					var line = $.publicinfo.SELECTED_FILES[i];
					var authority = line.data("authority");
					
					// 无权限删除
					if (!$.publicinfo.authenticate(authority, $.publicinfo.FILE_OPTION.AUTH_MODIFY)) {
						noAuthorityFileCount++;
						line.trigger("click");
					}
					else {
						files.push(line.data('infocode'));
					}
				}
				
				// 没有可以删除的文件
				if (0 == files) {
					Centit.msgError('没有权限删除所选文件。');
					return false;
				}
				
				var alertMsg = "文件将被删除不可恢复，是否继续？";
				if (noAuthorityFileCount > 0) {
					alertMsg = '所选文件有部分无权限删除，剩下的文件将被删除不可恢复，是否继续？';
				}
				
				Centit.msgConfirm(alertMsg, {
					retryAction: function() {
						$.post($.publicinfo.FILE_OPTION.delete_url, {infocode: files.join(','), mode: $.publicinfo.MODE}, function(data) {
							eventObject.excuteAction("callback", data);
						}, 'json');
					}
				});
				
			},
			
			/**
			 * 删除文件回调
			 * @param data
			 */
			deleteFileCallback: function(data) {
				Centit.msgAlert('删除文件成功。');
				$.publicinfo.dirPublicFolder();
			},
			
			/**
			 * 下载文件
			 */
			downloadFile: function(event) {
				var files = [];
				var eventObject = this;
				for (var i=0; i<$.publicinfo.SELECTED_FILES.length; i++) {
					var line = $.publicinfo.SELECTED_FILES[i];
					files.push(line.data('infocode'));
				}
				
				$.post($.publicinfo.FILE_OPTION.download_url, {infocode: files.join(','), mode: $.publicinfo.MODE}, function(data){
					eventObject.excuteAction("callback", data);
				}, 'json');
				
				event.preventDefault();
			},
			
			/**
			 * 下载文件回调
			 * 
			 * @param data
			 */
			downloadFileCallback: function(data, result) {
				if (result !== true) return false;
				
				var callback = function(data) {
					if (!data || '0' != data.result) {
						Centit.msgError(data.description);
						return false;
					}
					
					// 下载文件
					var downloadForm = $('#downloadForm');
					
					if (!downloadForm[0]) {
						var form = $("<form>");   //定义一个form表单
						form.attr('id', 'downloadForm');
						form.attr('style','display:none');   //在form表单中添加查询参数
						form.attr('target','');
						form.attr('method','post');
						form.attr('action',$.publicinfo.FILE_OPTION.download_file_url);
						
						var input = $('<input>'); 
						input.attr('type','hidden'); 
						input.attr('name','filecode'); 
						
						$('body').append(form);  //将表单放置在web中
						form.append(input);   //将查询参数控件提交到表单上
						downloadForm = form;
					}
					
					downloadForm.find('input[name=filecode]').val(data.filecode);
					downloadForm.submit(); 
				};
				
				$.post($.publicinfo.FILE_OPTION.toDownload_file_url, {
					filecode : data.filecode
				}, callback, 'json');
				
			},
			
			/**
			 * 全选所有文件、文件夹
			 * @param e
			 * @param button
			 * @returns {Boolean}
			 */
			selectAllFile: function(e, button) {
				// 新建文件夹中或重命名时不可选择文件
				if ($.publicinfo.getStatus() == 1) {
					return false;
				}
				
				var $this = $(button).closest('div.line');
				$this.toggleClass('selected');
				
				var container = $('#public-window');
				if ($this.hasClass('selected')) {
					
					$('div.line', container).addClass('selected');
					$('div.line.operation').addClass('selected');
					$('div.header').addClass('selected');
					
					$.publicinfo.SELECTED_FILES.length = 0;
					$('div.line.selected', container).each(function() {
						$.publicinfo.SELECTED_FILES.push($(this));
					});
					
				}else {
					
					$('div.line', container).removeClass('selected');
					$('.line.operation').removeClass('selected');
					$('.header').removeClass('selected');
					$.publicinfo.SELECTED_FILES.length = 0;
				}
				
				selectHead();
			},
			
			/**
			 * 选择文件、文件夹
			 */
			selectFile: function(e, line) {
				// 新建文件夹中或重命名时不可选择文件
				if ($.publicinfo.getStatus() == 1) {
					return false;
				}
				
				if ($(e.target).is('a')) {
					return false;
				}
				
				$(line).toggleClass('selected');
				var container = $('#public-window');
				
				// 清空并重新设定已选择文件
				$.publicinfo.SELECTED_FILES.length = 0;
				$('div.line.selected', container).each(function() {
					$.publicinfo.SELECTED_FILES.push($(this));
				});
				
				// 判断是否全选
				if ($.publicinfo.FILES.length + $.publicinfo.FOLDERS.length == $.publicinfo.SELECTED_FILES.length) {
					$('div.line.operation span.check').add('div.header span.check').addClass('selected');
					$('div.line.operation').addClass('selected');
					$('div.header').addClass('selected');
				}
				else {
					$('div.line.operation span.check').add('div.header span.check').removeClass('selected');
					$('.line.operation').removeClass('selected');
					$('.header').removeClass('selected');
				}
				
				selectHead();
			},
			
			/**
			 * 重命名回调
			 * 
			 * @param data
			 * @param result
			 */
			renameCallback: function(data, result) {
				
				if (result === true) {
					var file = data.file;
					var infocode = file.infocode;
					var filename = file.filename;
					
					var line = $('div.line[_data_infocode='+infocode+']');
					line.data('filename', filename);
					line.find('div.info.name').find('a').first().html(filename);
					
					Centit.msgAlert('重命名文件成功。');
					
					this.excuteAction("cancel");
				}
				
			},
			
			/**
			 * 重命名文件、文件夹
			 * @returns {Boolean}
			 */
			rename: function() {
				
				var file = $.publicinfo.SELECTED_FILES[0];
				var rename = $('#rename-container');
				var newName = rename.find('input').val();
				var eventObject = this;
				
				$.post($.publicinfo.FILE_OPTION.rename_url, {mode:$.publicinfo.MODE, name:newName, infocode:file.data('infocode'), root:$.publicinfo.FILE_OPTION.infocode}, function(data){
					eventObject.excuteAction("callback", data);
				}, 'json');
			},
			
			/**
			 * 关闭重命名控件
			 */
			cancelRename: function() {
				// 显示原来文件名
				var line = $.publicinfo.SELECTED_FILES[0];
				line.find('div.info.name a').show();
				
				// 退回状态
				$.publicinfo.popStatus();
				
				// 隐藏重命名控件
				$.publicinfo.FILE_OPTION.RENAME_CONTAINER.detach();
			},
			
			/**
			 * 打开重命名控件
			 */
			toRename: function() {
				
				// 设置状态
				$.publicinfo.pushStatus(1);
				
				// 隐藏“更多”菜单
				$('ul.pull-down-menu.header-menu').hide();
				
				var line = $.publicinfo.SELECTED_FILES[0];
				var info = line.find('div.info.name');
				
				// 隐藏原始文件名
				info.find('a').hide();
				
				var rename = $.publicinfo.FILE_OPTION.RENAME_CONTAINER.show();
				rename.find('input').select().val(line.data('filename'));
				
				// 替换成重命名控件
				info.append(rename);
			},
			
			/**
			 * 打开“更多”菜单
			 */
			openPopMenu: function(btn) {
				var container = $('#file-container');
				var menu = $('ul.pull-down-menu.header-menu', container);
				clearTimeout(popMenuHandle);
				
				btn = $(btn);
				if (btn.hasClass('pull-down-menu')) return;
				
				var left = btn.offset().left -25;
				var top = btn.offset().top + btn.height();
				
				if ($.publicinfo.SELECTED_FILES.length != 1) {
					$('a.rename', menu).addClass('disable');
				}
				else {
					$('a.rename', menu).removeClass('disable');
				}
				
				menu.css({
					left:left,
					top:top
				}).show();
			},
			
			/**
			 * 关闭“更多”菜单
			 */
			closePopMenu: function () {
				var container = $('#file-container');
				var menu = $('ul.pull-down-menu.header-menu', container);
				
				menu.hide();
			},
			
			/**
			 * 新建文件夹回调
			 * @param data
			 * @returns {Boolean}
			 */
			addFolderCallback: function (data) {
				this.excuteAction("cancel");
				Centit.msgAlert('新建文件夹成功。');
				$.publicinfo.dirPublicFolder();
			},
			
			/**
			 * 提交新建文件夹
			 * @returns {Boolean}
			 */
			addFolder: function(event, obj, result) {
				if (result === true) {
					var container = $('#public-window');
					var div = $('.line.new', container);
					var input = $('input', div).select();
					var eventObject = this;
					
					$.post($.publicinfo.FILE_OPTION.add_url, {filename:input.val(), root:$.publicinfo.FILE_OPTION.infocode, mode: $.publicinfo.MODE}, 
							function(data) {
						eventObject.excuteAction("callback", data);
					}, 'json');
				};
			},
			
			/**
			 * 关闭新建文件夹
			 * @returns
			 */
			cancelAddFolder: function() {
				if ($.publicinfo.getStatus() == 1) {
					var container = $('#public-window');
					var div = $('div.line.new', container).remove();
					return $.publicinfo.popStatus();
				}
			},
			
			/**
			 * 打开新建文件夹控件
			 */
			toAddFolder: function() {
				
				var html = Centit.frag["PUBLIC_NEW_FOLDER"]
				// 替换上传者
				.replaceAll('{username}', $.publicinfo.FILE_OPTION.username);
				
				var container = $('#public-window');
				var firstChild = container.find('div.line:first');
				
				// 插入
				if (firstChild[0]) {
					firstChild.before(html);
				}else {
					container.append(html);
				}
				
				var div = $('.line.new', container);
				var input = $('input', div).select();
				
				$.publicinfo.pushStatus(1);
			},
			
			/**
			 * 加载公共文件夹
			 * 
			 * @param toPath
			 */
			dirPublicFolder: function(toPath) {
				var path = toPath || $.publicinfo.FILE_OPTION.infocode;
				
				if (path) {
					$.getJSON($.publicinfo.FILE_OPTION.public_url, {path:path, mode: $.publicinfo.MODE}, $.publicinfo.dirPublicFolderCallback);
				}
				else {
					$.getJSON($.publicinfo.FILE_OPTION.public_url, {mode: $.publicinfo.MODE}, $.publicinfo.dirPublicFolderCallback);
				}
			},
			
			/**
			 * 加载公共文件夹回调
			 * 
			 * @param data
			 * @returns {Boolean}
			 */
			dirPublicFolderCallback: function(data, result){
				
				if (result === false) {
					return false;
				};
				
				var files = data.files.reverse();
				
				// 保存信息
				$.publicinfo.FILE_OPTION.username = data.username;
				$.publicinfo.FILE_OPTION.usercode = data.usercode;
				$.publicinfo.FILE_OPTION.userunit = data.userunit;
				$.publicinfo.FILE_OPTION.fileunit = data.fileunit;
				$.publicinfo.FILE_OPTION.infocode = data.infocode;
				$.publicinfo.FILE_OPTION.authority = data.authority;
				
				// 创建路径栏
				createPath(data);
				
				// 清空
				$.publicinfo.FOLDERS = [];
				$.publicinfo.FILES = [];
				$.publicinfo.SELECTED_FILES = [];
				$.publicinfo.clearStatus();
				$('div#public-window div.line').remove();
				
				// 加载文件
				for (var i=0; i<files.length; i++) {
					$.publicinfo.addFile(files[i]);
				}
				
				// 最后一行
				$('div#public-window div.line:last').addClass('last');
				
				selectHead();
			},
			
			/**
			 * 添加一个文件对象到容器
			 * 
			 * @param file 文件JSON数据
			 * @param last 是否排在最后
			 * @param container 容器
			 */
			addFile: function(file, last, container) {
				if (!container) {
					container = $('div.public-window');
				}
				
				var html = Centit.frag["PUBLIC_FILE_LINE"]
				.replaceAll('{infocode}', file.infocode)
				// 替换类型
				.replaceAll('{type}', getFileType(file))
				.replaceAll('{fullFilename}', getFullFilename(file))
				// 替换文件名
				.replaceAll('{filename}', file.filename)
				// 替换类型
				.replaceAll('{atype}', getLinkType(file))
				// 替换URL
				.replaceAll('{url}', getFileURL(file))
				// 替换文件大小
				.replaceAll('{size}', getFileSize(file))
				// 替换上传者
				.replaceAll('{ownercode}', file.uploader)
				// 替换上传时间
				.replaceAll('{uploadtime}', getFileUploadtime(file))
				.replaceAll('{folderType}', getFolderType(file));
				
				var line;
				// 排在最末尾
				if (last) {
					line = $(html).appendTo(container);
				}
				// 排在前端
				else {
					var first = container.find('div.line:first');
					if (first[0]) {
						line = $(html).insertBefore(first);
					}
					// 一个文件、文件夹也没有
					else {
						line = $(html).appendTo(container);
					}
				}
				
				if (file.isfolder=='1') {
					$.publicinfo.FOLDERS.push(line);
				}
				else {
					$.publicinfo.FILES.push(line);
				}
				
				_saveData(line, file);
			}
			
	};
});