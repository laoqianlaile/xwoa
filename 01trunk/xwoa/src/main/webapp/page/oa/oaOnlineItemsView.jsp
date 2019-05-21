<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItems.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaOnlineItems.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaOnlineItems!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
<input type="hidden" id="s_djid" value="${s_djid }" name="s_djid">
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItems.itemid" />
					</td>
					<td align="left">
						<s:property value="%{itemid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItems.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItems.title" />
					</td>
					<td align="left">
						<s:property value="%{title}" />
					</td>
				</tr>	

</table>


<p/>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>

    
				<td class="tableHeader">
					<s:text name="oaSurveydetail.creater" />
				</td>

  
				<td class="tableHeader">
					<s:text name="oaSurveydetail.no" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaSurveydetail.title" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaSurveydetail.djid" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaSurveydetail.createtime" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="oaSurveydetail" items="${object.oaSurveydetails}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${oaSurveydetail.creater}"/></td>  

  
				<td><c:out value="${oaSurveydetail.no}"/></td>  
  
				<td><c:out value="${oaSurveydetail.title}"/></td>  
  
				<td><c:out value="${oaSurveydetail.djid}"/></td>  
  
				<td><c:out value="${oaSurveydetail.createtime}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaSurveydetail!edit.do?itemid=${oaOnlineItems.itemid}&itemid=${oaSurveydetail.itemid}&creater=${oaSurveydetail.creater}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaSurveydetail!delete.do?itemid=${oaOnlineItems.itemid}&itemid=${oaSurveydetail.itemid}&creater=${oaSurveydetail.creater}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</c:forEach> 
		</tbody>        
	</table>
</div>


</body>
</html>
