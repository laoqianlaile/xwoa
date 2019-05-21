/*  *//*  *//*  *//*  *//*  *//*  *//*  *//*  *//*  *//*  */

/*  */
$(document).ready(function() {
	
});



function PageUtils() {}
PageUtils.genJSelector = function(pos) {
	return ("#" == pos.charAt(0)) ? pos : ("[name='" + pos + "']");
};
PageUtils.getTagName = function(pos, selector) {
	return selector ? ($(selector).prop("tagName")) : ($(PageUtils.genJSelector(pos)).prop("tagName"));
};
PageUtils.getTagType = function (pos, selector, tagName) {
	if (!tagName) {
		tagName = PageUtils.getTagName(pos, selector);
	}
	if ("INPUT" == tagName.toUpperCase()) {
		return selector ? ($(selector)).attr("type").toUpperCase() :  $(PageUtils.genJSelector(pos)).attr("type").toUpperCase();
	}
	return tagName;
};
PageUtils.genJObject = function(pos) {
	var selector = PageUtils.genJSelector(pos);
	var tagName = PageUtils.getTagName(pos, selector);
	var tagType = PageUtils.getTagType(pos, selector, tagName);
	if ("CHECKBOX" == tagType || "RADIO" == tagType) {
		return $("INPUT" + selector + ":checked");
	} else {
		return $(selector);
	}
};
/*  */