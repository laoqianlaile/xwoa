<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSurvey.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaSurvey.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaSurvey!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
   <s:hidden id="s_type" name="s_type" value="%{s_type}"></s:hidden>
				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>


				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.title" />
					</td>
					<td align="left">
						<s:property value="%{title}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.reType" />
					</td>
					<td align="left">
						<s:property value="%{reType}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.remark" />
					</td>
					<td align="left">
						<s:property value="%{remark}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.begtime" />
					</td>
					<td align="left">
						<s:property value="%{begtime}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.endtime" />
					</td>
					<td align="left">
						<s:property value="%{endtime}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.createRemark" />
					</td>
					<td align="left">
						<s:property value="%{createRemark}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.createDepno" />
					</td>
					<td align="left">
						<s:property value="%{createDepno}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.thesign" />
					</td>
					<td align="left">
						<s:property value="%{thesign}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.sendusers" />
					</td>
					<td align="left">
						<s:property value="%{sendusers}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.isautoend" />
					</td>
					<td align="left">
						<s:property value="%{isautoend}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.isviewresult" />
					</td>
					<td align="left">
						<s:property value="%{isviewresult}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaSurvey.isbookn" />
					</td>
					<td align="left">
						<s:property value="%{isbookn}" />
					</td>
				</tr>	

</table>


<p/>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
    
				<td class="tableHeader">
					<s:text name="oaOnlineItem.no" />
				</td>


  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.chosetype" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.title" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.itemnames" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.chosenum" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.limitnum" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaOnlineItem.thesign" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="oaOnlineItem" items="${object.oaOnlineItems}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${oaOnlineItem.no}"/></td>  

  
				<td><c:out value="${oaOnlineItem.chosetype}"/></td>  
  
				<td><c:out value="${oaOnlineItem.title}"/></td>  
  
				<td><c:out value="${oaOnlineItem.itemnames}"/></td>  
  
				<td><c:out value="${oaOnlineItem.chosenum}"/></td>  
  
				<td><c:out value="${oaOnlineItem.limitnum}"/></td>  
  
				<td><c:out value="${oaOnlineItem.thesign}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaOnlineItem!edit.do?djid=${oaSurvey.djid}&no=${oaOnlineItem.no}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaOnlineItem!delete.do?djid=${oaSurvey.djid}&no=${oaOnlineItem.no}' 
							onclick='return confirm("${deletecofirm}该记录?");'><s:text name="opt.btn.delete" /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</c:forEach> 
		</tbody>        
	</table>
</div>

<p/>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
    
				<td class="tableHeader">
					<s:text name="oaSurveydetail.itemid" />
				</td>
    
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
					<s:text name="oaSurveydetail.createtime" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="oaSurveydetail" items="${object.oaSurveydetails}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${oaSurveydetail.itemid}"/></td>  
    
				<td><c:out value="${oaSurveydetail.creater}"/></td>  

  
				<td><c:out value="${oaSurveydetail.no}"/></td>  
  
				<td><c:out value="${oaSurveydetail.title}"/></td>  
  
				<td><c:out value="${oaSurveydetail.createtime}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaSurveydetail!edit.do?djid=${oaSurvey.djid}&itemid=${oaSurveydetail.itemid}&creater=${oaSurveydetail.creater}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaSurveydetail!delete.do?djid=${oaSurvey.djid}&itemid=${oaSurveydetail.itemid}&creater=${oaSurveydetail.creater}' 
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
