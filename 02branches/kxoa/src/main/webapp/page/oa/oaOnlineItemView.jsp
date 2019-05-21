<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItem.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaOnlineItem.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaOnlineItem!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
<input type="hidden" id="s_djid" value="${s_djid }" name="s_djid">	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.chosetype" />
					</td>
					<td align="left">
						<s:property value="%{chosetype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.title" />
					</td>
					<td align="left">
						<s:property value="%{title}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.itemnames" />
					</td>
					<td align="left">
						<s:property value="%{itemnames}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.chosenum" />
					</td>
					<td align="left">
						<s:property value="%{chosenum}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.limitnum" />
					</td>
					<td align="left">
						<s:property value="%{limitnum}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaOnlineItem.thesign" />
					</td>
					<td align="left">
						<s:property value="%{thesign}" />
					</td>
				</tr>	

</table>


<p/>
<div class="eXtremeTable" >
	<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >

		<thead>
			<tr>
    
				<td class="tableHeader">
					<s:text name="oaOnlineItems.itemid" />
				</td>


  
				<td class="tableHeader">
					<s:text name="oaOnlineItems.title" />
				</td>
		
				<td class="tableHeader"><s:text name="opt.btn.collection" /></td>
			</tr>  
		</thead>
		
		<tbody class="tableBody" >
		<c:set value="odd" var="rownum" />
		
		<c:forEach var="oaOnlineItems" items="${object.oaOnlineItemss}">    
			<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
    
				<td><c:out value="${oaOnlineItems.itemid}"/></td>  

  
				<td><c:out value="${oaOnlineItems.title}"/></td>  
		
				<td>
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaOnlineItems!edit.do?no=${oaOnlineItem.no}&itemid=${oaOnlineItems.itemid}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaOnlineItems!delete.do?no=${oaOnlineItem.no}&itemid=${oaOnlineItems.itemid}' 
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
