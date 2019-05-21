(function($) {
function isTdElement(element) {
	return $(element).closest('td')[0];
}	

$.extend($.validator.prototype, {
    showLabel: function(element, message) {
    	if (isTdElement(element)) {
    		var td = $(element).closest('td');
    		
    		if (td.attr('title')) {
    			td.attr('title', message);
    			td.tooltip('fixTitle');
    		}
    		else {
    			td.attr('title', message);
    			td.tooltip();
    		}
    		return;
    	}
    	
        var label = this.errorsFor( element );
        if (label.length == 0) {
            var railsGenerated = $(element).next('span.help-inline');
            if (railsGenerated.length) {
                railsGenerated.attr('for', this.idOrName(element))
                railsGenerated.attr('generated', 'true');
                label = railsGenerated;
            }
        }
        if (label.length) {
            label.removeClass(this.settings.validClass).addClass(this.settings.errorClass);
            label.attr('generated') && label.html(message);
        } else {
            label = $('<' + this.settings.errorElement + '/>')
                  .attr({'for':  this.idOrName(element), generated: true})
                  .addClass(this.settings.errorClass)
                  .addClass('help-inline')
                  .html(message || '');
            if (this.settings.wrapper) {
                label = label.hide().show().wrap('<' + this.settings.wrapper + '/>').parent();
            }
            if (!this.labelContainer.append(label).length) {
                this.settings.errorPlacement
                    ? this.settings.errorPlacement(label, $(element))
                    : label.insertAfter(element);
            }
        }
        if (!message && this.settings.success) {
            label.text('');
            typeof this.settings.success == 'string'
                ? label.addClass(this.settings.success)
                : this.settings.success(label);
        }
        this.toShow = this.toShow.add(label);
    }
});
$.extend($.validator.defaults, {
	ignore: function() {
		return $(this).closest('thead').length != 0 || $(this).hasClass('notValidate');
	},
    errorClass: 'error',
    validClass: 'success',
    errorElement: 'span',
    highlight: function (element, errorClass, validClass) {
      if (isTdElement(element)) {
    	  $(element).closest('td').addClass(errorClass);
      }
      else {
    	  $(element).closest('div.control-group').removeClass(validClass).addClass(errorClass);
      }
    },
    unhighlight: function (element, errorClass, validClass) {
	 if (isTdElement(element)) {
		 var td = $(element).closest('td');
   	  	 td.removeClass(errorClass).attr('title', '').attr('data-original-title', '');
     }
     else {
    	 $(element).closest('div.control-group').removeClass(errorClass).addClass(validClass);
         $(element).next('span.help-inline').text('');
     }
    },
    errorPlacement: function(error, element) {
      $(element).parents('div.controls').append(error);
    },
	success: function(element) {
		if (isTdElement(element)) {
			
	     }
	     else {
	    	 element
	 		.text('OK!').addClass('success')
	 		.closest('.control-group').removeClass('error').addClass('success');
	     }
	}
});

jQuery.extend(jQuery.validator.messages, {
  required: "必选字段",
  remote: "请修正该字段",
  email: "请输入正确格式的电子邮件",
  url: "请输入合法的网址",
  date: "请输入合法的日期",
  dateISO: "请输入合法的日期 (ISO).",
  number: "请输入合法的数字",
  digits: "只能输入整数",
  creditcard: "请输入合法的信用卡号",
  equalTo: "请再次输入相同的值",
  accept: "请输入拥有合法后缀名的字符串",
  maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
  minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
  rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
  min: jQuery.validator.format("请输入一个最小为{0} 的值")
});
}(jQuery));