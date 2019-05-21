<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationOutlog.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaAssetinformationOutlog.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaAssetinformationOutlog!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.applyuser" />
					</td>
					<td align="left">
						<s:property value="%{applyuser}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.applyUnitcode" />
					</td>
					<td align="left">
						<s:property value="%{applyUnitcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.applydate" />
					</td>
					<td align="left">
						<s:property value="%{applydate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.assetnum" />
					</td>
					<td align="left">
						<s:property value="%{assetnum}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.assetunit" />
					</td>
					<td align="left">
						<s:property value="%{assetunit}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationOutlog.createRemark" />
					</td>
					<td align="left">
						<s:property value="%{createRemark}" />
					</td>
				</tr>	

</table>


<p/>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>

    
				<td class="tableHeader">
					<s:text name="oaAssetinformationBond.no" />
				</td>

  
				<td class="tableHeader">
					<s:text name="oaAssetinformationBond.creater" />
				</td>
  
				<td class="tableHeader">
					<s:text name="oaAssetinformationBond.createtime" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="oaAssetinformationBond" items="${object.oaAssetinformationBonds}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${oaAssetinformationBond.no}"/></td>  

  
				<td><c:out value="${oaAssetinformationBond.creater}"/></td>  
  
				<td><c:out value="${oaAssetinformationBond.createtime}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaAssetinformationBond!edit.do?djid=${oaAssetinformationOutlog.djid}&djid=${oaAssetinformationBond.djid}&no=${oaAssetinformationBond.no}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaAssetinformationBond!delete.do?djid=${oaAssetinformationOutlog.djid}&djid=${oaAssetinformationBond.djid}&no=${oaAssetinformationBond.no}' 
							onclick='return confirm("${deletecofirm}oaAssetinformationBond?");'><s:text name="opt.btn.delete" /></a>
				</td>
			</tr>  
            <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		</c:forEach> 
		</tbody>        
	</table>
</div>


</body>
</html>
