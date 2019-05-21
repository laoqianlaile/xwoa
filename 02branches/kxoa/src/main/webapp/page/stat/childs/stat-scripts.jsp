<%@ page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript">
	$(function() {
    <%-- 日期控件 --%>
    if ($.fn.datepicker){
    	
		$('input.date').each(function(){
			var $this = $(this);
			var opts = {};
			if ($this.attr("format")) opts.pattern = $this.attr("format");
			if ($this.attr("yearstart")) opts.yearstart = $this.attr("yearstart");
			if ($this.attr("yearend")) opts.yearend = $this.attr("yearend");
			$this.datepicker(opts);
		});
	}
    
	    $.myChart._initSearchArea();
    function addOddTRColor() {
    	$('table.stat tbody tr:visible:odd').css({
        	'background-color': '#EFEFEF'
        });
    	
    	$('table.stat tbody tr:visible:even').css({
        	'background-color': '#fff'
        });
    }
    
    addOddTRColor();
    
    $('table.stat tbody tr').hover(function() {
    	addOddTRColor();
    	$(this).css({
        	'background-color': '#FEF9E6'
        });
    }, addOddTRColor);
	});
</script>