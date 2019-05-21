<script type="text/javascript">
/*
* Create time:2012-03-01
*/
$(function(){
	var cssStyle = $.cookie("CentitUI_theme") || "default";
	var surl = "${pageContext.request.contextPath}/themes/"+cssStyle;
	/*
	 * 按钮操作集 对应页面 a标签 eg <a href="#" styleopt="check"></a> styleopt的属性值check 对应optionList check
	 */
	var optionList = {
		"view":{"src":"/images/option/vcard.png","title":"<s:text name='opt.btn.view' />","alt":"<s:text name='opt.btn.view' />"},//浏览
		"viewDetail":{"src":"/images/option/vcard.png","title":"<s:text name='opt.btn.viewdetail' />","alt":"<s:text name='opt.btn.viewdetail' />"}, //浏览详细
		"viewInDialog":{"src":"/images/option/application.png","title":"<s:text name='opt.btn.viewindialog' />","alt":"<s:text name='opt.btn.viewindialog' />"}, //在弹出窗口浏览
		"edit":{"src":"/images/option/edit.png","title":"<s:text name='opt.btn.edit' />","alt":"<s:text name='opt.btn.edit' />"}, // 编辑
		"delete":{"src":"/images/option/cross.png","title":"<s:text name='opt.btn.delete' />","alt":"<s:text name='opt.btn.delete' />"}, //删除
		"disable":{}, //禁用
		"enabled":{}  //启用
	};
	var $pageOption = jQuery(".option-btn a");
	$pageOption.each(function(){
		var $this = jQuery(this);
		if(!!$this.attr("styleopt")&& typeof optionList[$this.attr("styleopt")] != "undefined" )
			$this.html("<img src='"+surl+optionList[$this.attr("styleopt")]["src"]+"' title='"+optionList[$this.attr("styleopt")]["title"]+"' alt='"+optionList[$this.attr("styleopt")]["alt"]+"' />");
	});
});

</script>