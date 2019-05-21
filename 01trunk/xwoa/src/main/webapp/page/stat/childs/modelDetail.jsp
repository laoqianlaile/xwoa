<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<div class="container_12 form-page">
	<input type="hidden" name="rowDrawChart" value="F">
	<input type="hidden" name="additionRow" value="0">
	<input type="hidden" name="drawChartBeginCol" value="1">
	<input type="hidden" name="drawChartEndCol" value="1">

	<div class="grid_2 form-title">* 模板编码：</div>
	<div class="grid_10 form-content"><s:textfield name="modelName" /></div>
	<div class="clear"></div>
	
	<div class="grid_2 form-title">* 模板名称：</div>
	<div class="grid_10 form-content"><s:textfield name="formNameFormat" /></div>
	<div class="clear"></div>
	
	<div class="grid_2 form-title">查询描述：</div>
	<div class="grid_10 form-content"><s:textfield name="queryDesc" /></div>
	<div class="clear"></div>
	
	<div class="grid_2 form-title">* 返回页面：</div>
	<div class="grid_10 form-content"><s:textfield name="resultName" /></div>
	<div class="clear"></div>
	
	<div class="grid_2 form-title">* 查询语句：</div>
	<div class="grid_10 form-content"><s:textarea name="querySql" cols="90" rows="20" /></div>
	<div class="clear"></div>
	
</div>
   