<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<input type="hidden" name="modelType" id="modelType" value="3" />

<table class="reportTypeTable">
	<tr class="img-tr">
		<td rel="2" width="300">
			<img alt="普通报表" src="${contextPath }/images/report/normal.jpg" class="shadow-img" title="直接展示SQL查询出的数据" >
		</td>
		
		<td rel="3" width="300">
			<img alt="对比报表" src="${contextPath }/images/report/compare.jpg" class="shadow-img" title="按照年或月进行数据对比">
		</td>
		
		<td rel="5" width="300">
			<img alt="交叉报表" src="${contextPath }/images/report/cross.jpg" class="shadow-img" title="按照行、列分组进行数据对比">
		</td>
	</tr>
	<tr>
		<td class="text">普通报表</td>
		<td class="text">对比报表</td>
		<td class="text">交叉报表</td>
	</tr>
</table>

<script type="text/javascript">
// 选择模板类型
function changeModelType() {
	var modelType = $('#modelType');
	var table = $('.reportTypeTable');
	
	// 模板类型
	var type = modelType.val();
	var tds = $('tr.img-tr td');
	
	tds.hover(function() {
		$(this).addClass('hover-type');
	}, function() {
		$(this).removeClass('hover-type');
	}).click(function() {
		var $this = $(this);
		var index = $this.index();
		
		if ($this.hasClass('selected-type')) {
			return true;
		}
		
		$('tr>td', table).removeClass('selected-type');
		$('tr>td:nth-child('+ (index+1) + ')', table).addClass('selected-type');
		modelType.val($this.attr('rel'));
	});
	
	// 页面加载时选择
	if(type) {
		var td = $('tr.img-tr>td[rel=' + type + ']', table);
		var index = td.index();
		$('tr>td:nth-child('+ (index+1) + ')', table).addClass('selected-type');
	}
}
</script>