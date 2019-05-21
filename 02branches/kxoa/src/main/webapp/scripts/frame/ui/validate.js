/**
 * 封装jquery.validate验证框架
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/23
 * @modify: 2014/4/23 by zk
 */

define(['jquery', 'i18n!nls/locale', 'validator', 'validatorMethod'], function($, Locale) {
	var V = $.validator;
	
	$.fn.extend({
		/**
         * 重置表单
         */
		cleanupForm: function() {
	        if (!this.is('form')) {
                return;
            }

            var validator = this.data('validator');

            if (validator) {
                validator.resetForm();
            }
            
		}
	});	

	// 修复在IE7以及在IE怪异模式下的错误，不支持 String[0]
	$.extend(V.prototype, {
		customDataMessage: function( element, method ) {
//			return $( element ).data( "msg" + method[0].toUpperCase() + method.substring( 1 ).toLowerCase() ) || $( element ).data("msg");
			return $( element ).data( "msg" + method.charAt(0).toUpperCase() + method.substring( 1 ).toLowerCase() ) || $( element ).data("msg");
		}
	});
	
	// 修复在IE7以及在IE怪异模式下的错误，不支持 String[0]
	$.extend(V, {	
		dataRules: function( element ) {
			var method, value,
				rules = {}, $element = $( element );
			for ( method in $.validator.methods ) {
//				value = $element.data( "rule" + method[0].toUpperCase() + method.substring( 1 ).toLowerCase() );
				value = $element.data( "rule" + method.charAt(0).toUpperCase() + method.substring( 1 ).toLowerCase() );
				if ( value !== undefined ) {
					rules[ method ] = value;
				}
			}
			return rules;
		}
	});
	
	
	// 和可编辑表格配合，特殊处理现实错误标示
	function isEditable(element) {
		return $(element).closest('td').hasClass('editable');
	}
	
	highlight_prototype = V.defaults.highlight;
	unhighlight_prototype = V.defaults.unhighlight;
	
	// 设置默认配置
	V.setDefaults({
		// 调试
		debug: false,
		
		// 忽略元素
		ignore: ".ignore,.btn",
		
		// 错误提示标签
		errorElement: "span",
		
		// 聚焦元素时显示错误提示
		focusInvalid: false,
		
		// 验证成功给错误提示标签添加class
		success: 'valid',
		
		// 修改 radio 和 checkbox错误提示位置
		errorPlacement: function(error, element) {
			// 可编辑表格
			if (isEditable(element)) {
				var msg = error.text(), td = element.closest('td');
				td.attr('title', msg);
				
				return;
			}
		
			if (element.is(':radio') || element.is(':checkbox')) {
				element = element.parent();
			}
			
			error.insertAfter(element);
	    },
	    
	    highlight: function(element, errorClass, validClass) {
	    	// 可编辑表格
	    	if (isEditable(element)) {
	    		var td = $(element).closest('td');
	    		td.addClass(errorClass).removeClass(validClass);
	    		td.attr('title', td.data('errorTitle'));
	    	}
	    	else {
	    		highlight_prototype(element, errorClass, validClass);
	    	}
	    },
	    
	    unhighlight: function(element, errorClass, validClass) {
	    	// 可编辑表格
	    	if (isEditable(element)) {
	    		var td = $(element).closest('td');
	    		td.addClass(validClass).removeClass(errorClass);
	    		td.attr('title', td.data('originalTitle'));
	    	}
	    	else {
	    		unhighlight_prototype(element, errorClass, validClass);
	    	}
	    }
	});
	
	// 设置错误提示
	$.extend($.validator.messages, {
		required: 	Locale.validator.required,
		remote: 	Locale.validator.remote,
		email: 		Locale.validator.email,
		url: 		Locale.validator.url,
		date: 		Locale.validator.date,
		dateISO: 	Locale.validator.dateISO,
		number: 	Locale.validator.number,
		digits: 	Locale.validator.digits,
		creditcard: Locale.validator.creditcard,
		equalTo: 	Locale.validator.equalTo,
		maxlength: 	V.format(Locale.validator.maxlength),
		minlength: 	V.format(Locale.validator.minlength),
		rangelength: V.format(Locale.validator.rangelength),
		range: 		V.format(Locale.validator.range),
		max: 		V.format(Locale.validator.max),
		min: 		V.format(Locale.validator.min)
	});
	
	// 添加新校验方法
	
	
	// 手机号码验证
	V.addMethod("isphone", function(value, element) {
		return this.optional(element) || value.isPhone();
	}, V.format(Locale.validator.phone));
	
	// 固定电话码验证
	V.addMethod("isTelphone", function(value, element) {
		return this.optional(element) || value.isTelPhone();
	}, V.format(Locale.validator.telphone));
	
	// 开始时间不能大于结束时间
	V.addMethod("ltDate", function(value, element, param) {
		var other = $(param);
		
		if ( this.settings.onfocusout ) {
			other.unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
				$(element).valid();
			});
		}

		if (!value || !other.val()) return true;
		return this.optional(element) || (value <= other.val());
	}, V.format(Locale.validator.ltDate));
	
	// 结束时间不能小于开始时间
	V.addMethod("gtDate", function(value, element, param) {
		var other = $(param);
		
		if ( this.settings.onfocusout ) {
			other.unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
				$(element).valid();
			});
		}

		if (!value || !other.val()) return true;
		return this.optional(element) || (value >= other.val());
	}, V.format(Locale.validator.gtDate));
	
	// 修改remote方法，可以通过 data-remote-data属性携带参数
	V.addMethod("remote", function(value, element, param) {
		if ( this.optional(element) ) {
			return "dependency-mismatch";
		}

		var previous = this.previousValue(element),
			validator, data;

		if (!this.settings.messages[element.name] ) {
			this.settings.messages[element.name] = {};
		}
		previous.originalMessage = this.settings.messages[element.name].remote;
		this.settings.messages[element.name].remote = previous.message;

		param = typeof param === "string" && { url: param } || param;

		if ( previous.old === value ) {
			return previous.valid;
		}

		previous.old = value;
		validator = this;
		this.startRequest(element);
		data = {};
		
		// 元素中添加 data-remote-params='{"key":"selector",...}' 获取请求参数 
		var paramOption = $(element).data('remoteParam');
		
		if (paramOption && typeof paramOption === "object") {
			for (var name in paramOption) {
				data[name] = this.elementValue($(paramOption[name]));
			}
		}
		else if (paramOption && typeof paramOption === "string") {
			data = eval(paramOption)();
		}
		else {
			data[element.name] = value;
		}

		$.ajax($.extend(true, {
			url: param,
			mode: "abort",
			port: "validate" + element.name,
			dataType: "json",
			data: data,
			context: validator.currentForm,
			success: function( response ) {
				var valid = response === true || response === "true",
					errors, message, submitted;

				validator.settings.messages[element.name].remote = previous.originalMessage;
				if ( valid ) {
					submitted = validator.formSubmitted;
					validator.prepareElement(element);
					validator.formSubmitted = submitted;
					validator.successList.push(element);
					delete validator.invalid[element.name];
					validator.showErrors();
				} else {
					errors = {};
					message = response || validator.defaultMessage( element, "remote" );
					errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
					validator.invalid[element.name] = true;
					validator.showErrors(errors);
				}
				previous.valid = valid;
				validator.stopRequest(element, valid);
			}
		}, param));
		return "pending";
	});
	
	return V;
});