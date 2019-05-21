define('UI', [
              'jquery', 
              'Core', 
              'Button', 
              'WDate', 
              'Form', 
              'Select', 
              'Table',
              'Upload'], 
              
function($, Core, Button, WDate, Form, Select, Table, Upload) {
	
	var U = {
		/**
		 * 初始化UI
		 */
		init: function(container) {
			// 时间控件
			WDate.init(container);
			
			// 表单增强
			Form.init(container);
			
			// 下拉框、多选框、单选框自动选值
			Select.init(container);
			
			// 表单提交、TAB页、弹出窗口、AJAX等
			Button.init(container);
			
			// 可编辑表格、动态表格
			Table.init(container);
			
			// 上传插件
			Upload.init(container);
			
			return container;
		}
	}
	
	return U;
});