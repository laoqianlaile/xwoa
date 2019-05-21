<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="pcpunishItem.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="pcpunishItem.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='punish/pcpunishItem!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.punishclassid" />
					</td>
					<td align="left">
						<s:property value="%{punishclassid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.itemId" />
					</td>
					<td align="left">
						<s:property value="%{itemId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.version" />
					</td>
					<td align="left">
						<s:property value="%{version}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.punishclassname" />
					</td>
					<td align="left">
						<s:property value="%{punishclassname}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.depid" />
					</td>
					<td align="left">
						<s:property value="%{depid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.punishclasscode" />
					</td>
					<td align="left">
						<s:property value="%{punishclasscode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.punishobject" />
					</td>
					<td align="left">
						<s:property value="%{punishobject}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.isinuse" />
					</td>
					<td align="left">
						<s:property value="%{isinuse}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.punishbasiscontent" />
					</td>
					<td align="left">
						<s:property value="%{punishbasiscontent}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.punishbasis" />
					</td>
					<td align="left">
						<s:property value="%{punishbasis}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.remark" />
					</td>
					<td align="left">
						<s:property value="%{remark}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.islawsuit" />
					</td>
					<td align="left">
						<s:property value="%{islawsuit}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="pcpunishItem.isreconsider" />
					</td>
					<td align="left">
						<s:property value="%{isreconsider}" />
					</td>
				</tr>	

</table>


<p/>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>

    
				<td class="tableHeader">
					<s:text name="pcpunishStandard.punishtypeid" />
				</td>

  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.isinuse" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.punishtype" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.toplimit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.toplimitUnit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.lowlimit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.lowlimitUnit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.baseName" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.baseToplimit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.baseLowlimit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.baseUnit" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcpunishStandard.remark" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="pcpunishStandard" items="${object.pcpunishStandards}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${pcpunishStandard.punishtypeid}"/></td>  

  
				<td><c:out value="${pcpunishStandard.isinuse}"/></td>  
  
				<td><c:out value="${pcpunishStandard.punishtype}"/></td>  
  
				<td><c:out value="${pcpunishStandard.toplimit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.toplimitUnit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.lowlimit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.lowlimitUnit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.baseName}"/></td>  
  
				<td><c:out value="${pcpunishStandard.baseToplimit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.baseLowlimit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.baseUnit}"/></td>  
  
				<td><c:out value="${pcpunishStandard.remark}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='punish/pcpunishStandard!edit.do?punishclassid=${pcpunishItem.punishclassid}&punishclassid=${pcpunishStandard.punishclassid}&punishtypeid=${pcpunishStandard.punishtypeid}'><s:text name="opt.btn.edit" /></a>
					<a href='punish/pcpunishStandard!delete.do?punishclassid=${pcpunishItem.punishclassid}&punishclassid=${pcpunishStandard.punishclassid}&punishtypeid=${pcpunishStandard.punishtypeid}' 
							onclick='return confirm("${deletecofirm}pcpunishStandard?");'><s:text name="opt.btn.delete" /></a>
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
					<s:text name="pcfreeumpiredegree.degreeno" />
				</td>

  
				<td class="tableHeader">
					<s:text name="pcfreeumpiredegree.isinuse" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcfreeumpiredegree.punishfactgrade" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcfreeumpiredegree.punishbasis" />
				</td>
  
				<td class="tableHeader">
					<s:text name="pcfreeumpiredegree.standardNo" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="pcfreeumpiredegree" items="${object.pcfreeumpiredegrees}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${pcfreeumpiredegree.degreeno}"/></td>  

  
				<td><c:out value="${pcfreeumpiredegree.isinuse}"/></td>  
  
				<td><c:out value="${pcfreeumpiredegree.punishfactgrade}"/></td>  
  
				<td><c:out value="${pcfreeumpiredegree.punishbasis}"/></td>  
  
				<td><c:out value="${pcfreeumpiredegree.standardNo}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='punish/pcfreeumpiredegree!edit.do?punishclassid=${pcpunishItem.punishclassid}&punishclassid=${pcfreeumpiredegree.punishclassid}&degreeno=${pcfreeumpiredegree.degreeno}'><s:text name="opt.btn.edit" /></a>
					<a href='punish/pcfreeumpiredegree!delete.do?punishclassid=${pcpunishItem.punishclassid}&punishclassid=${pcfreeumpiredegree.punishclassid}&degreeno=${pcfreeumpiredegree.degreeno}' 
							onclick='return confirm("${deletecofirm}pcfreeumpiredegree?");'><s:text name="opt.btn.delete" /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</c:forEach> 
		</tbody>        
	</table>
</div>


</body>
</html>
