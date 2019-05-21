/**
 * Created by zk on 14-4-8.
 * 
 * 老框架的main控制脚本
 */

require.config({
    baseUrl: '../scripts/frame',
    
    paths: {
		'jquery': '../jquery-1.7.2.min',
		
		'css':	"css",
		
        'normalize': "normalize",
		
        'angular': 'components/angular/angular.min',
        
        'Uploadify': 'components/jquery/jquery.uploadify/jquery.uploadify',

        'WdatePicker': 'components/My97DatePicker/WdatePicker',
        
        'Mustache': 'components/mustache/mustache',
        
        'validator': 'components/jquery/jquery.validation/jquery-validation-1.12.0/jquery.validate',
        
        'validatorMethod': 'components/jquery/jquery.validation/jquery-validation-1.12.0/additional-methods',
        
        'Cleditor': 'components/jquery/jquery.cleditor/jquery.cleditor.min',
        
        'TokenInput': 'components/jquery/jquery.tokeninput/jquery.tokeninput',
        
        'Core': 'ui/core',
        
        'WDate': 'ui/date',
        
        'AutoComplete': 'ui/autocomplete', 
        
        'Form': 'ui/form',
        
        'Select': 'ui/select',
        
        'Tab': 'ui/tab',
        
        'Dialog': 'ui/dialog',
        
        'Button': 'ui/button',
        
        'Msg': 'ui/msg',
        
        'Validate': 'ui/validate',
        
        'UI': 'ui/ui_old',
        
        'Publicinfo': 'ui/publicinfo/publicinfo',
        
        'EventManager': 'ui/event',
        
        'Table': 'ui/table/table',
        
        'Upload': 'ui/upload/upload'
    },

    shim: {
        angular: {
            exports: 'angular'
        },
        
        Cleditor: {
        	deps: ['jquery'],
            exports: 'Cleditor'
        },
        
        TokenInput: {
        	deps: ['jquery'],
        	exports: 'TokenInput'
        },
        
        Uploadify: {
        	exports: 'Uploadify',
        	deps: ['jquery']
        },
        
        WdatePicker: {
        	exports: 'WdatePicker'
        },
        
        Mustache: {
        	exports: 'Mustache'	
        },
        
        validator: {
        	deps: ['jquery'],
        	exports: 'validator'
        },
        
        validatorMethod: {
        	deps: ['validator'],
        	exports: 'validatorMethod'
        }
    },
    
    map: {
      '*': {
        'css': 'css'
      } 
    }
});

// 父页面jquery
define('pJquery', function() {
	return parent.$;
});

// 菜单标签
define('pTab', function() {
	return parent.navTab;
});

// 弹出窗口
define('pDialog', ['pJquery'], function($$) {
	return $$.pdialog;
});

// 消息窗口
define('pMsg', function() {
	return parent.alertMsg;
});

require(['jquery', 'UI'], function($, UI) {

	$(document).ready(function(){
		  UI.init($(document));
		  
		  // 自定义控制器
		  var controller = $('body').data('controller');
		  var deps = $('body').data('deps');
		  
		  if (controller) {
			  
			  try {
				  controller = eval(controller);
				  
				  if (deps) {
					  require(deps, controller);
				  }
				  else {
					  require(controller);
				  }

			  }
			  catch(e) {
				  return false;
			  }
			  
		  }
	});
});