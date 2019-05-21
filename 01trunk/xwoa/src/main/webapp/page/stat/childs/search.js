alert(1);

$(function() {
	$.myChart.init('#ec_table', '${formName}');

	var nowYear = new Date();
	nowYear = nowYear.getFullYear();

	$("select[id^='year']").each(function(index, el) {
		for ( var i = nowYear; i >= nowYear - 5; i--) {
			el.add(new Option(i + '年', i));
		}
	});

	$('select').css({
		width : '150px'
	});
	$('input[class!="btn"]').css({
		width : '150px'
	});
	
	CentitUI.init(
			"${pageContext.request.contextPath}/page/frame/centitui.frag.xml",
			{
				callback : function() {
					if ($.fn.datepicker) {
						$('input.date').each(function() {
							var $this = $(this);
							var opts = {};
							if ($this.attr("format"))
								opts.pattern = $this.attr("format");
							if ($this.attr("yearstart"))
								opts.yearstart = $this.attr("yearstart");
							if ($this.attr("yearend"))
								opts.yearend = $this.attr("yearend");
							$this.datepicker(opts);
						});
					}
				}
			});

});

function locateMe(parent, child) {
	parent = $(parent);
	child = $(child);

	var offset = parent.offset();
	var iTop = offset.top + parent.outerHeight() + 2;

	child.css({
		top : iTop + 'px',
		left : offset.left + 'px',
		position : 'absolute'
	});
}

function showUnitSearch(el, flag) {
	el = $(el);
	var unit = $('#unitbox');

	if (!flag) {
		unit.hide();
		return;
	}

	locateMe(el, unit);
	unit.data('source', el);
	unit.show();
}

function choseUnit(tree) {
	var unit = $('#unitbox');
	var source = unit.data('source');
	var value = tree.currentNode.sourceIndex.match(/_(\w+)/)[1];
	source.val(tree.currentNode.text);
	source.prevAll('input:first').val(value);
	unit.hide();
}