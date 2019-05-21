<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/page/common/taglibs.jsp" %>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head><meta name="decorator" content='${LAYOUT}'/>
		<title>人员列表</title>
		<sj:head locale="zh_CN" />
		<style type="text/css">
			.progressBar { display:block; width:148px; height:28px; position:fixed; top:50%; left:50%; margin-left:-74px; margin-top:-14px; padding:10px 10px 10px 50px; text-align:left; line-height:27px; font-weight:bold; position:absolute; z-index:2001;}
			.background { display:block; width:100%; height:100%; opacity:0.4; filter:alpha(opacity=40); background:#FFF; position:absolute; top:0; left:0; z-index:2000;}
		</style>
<link href="<s:url value="/scripts/autocomplete/autocomplete.css"/>" type="text/css" rel="stylesheet">
<script language="javascript" src="<s:url value="/scripts/autocomplete/autocomplete.js"/>"
        type="text/javascript"></script>
<script language="javascript" src="<s:url value="/scripts/selectUser.js"/>" type="text/javascript"></script>
<script type="text/javascript">
    var list = [];
    <c:forEach var="userinfo" varStatus="status" items="${cp:ALLUSER('T')}">
    list[${status.index}] = { username: '<c:out value="${userinfo.username}"/>', loginname: '<c:out value="${userinfo.loginname}"/>', usercode: '<c:out value="${userinfo.usercode}"/>'/* , pinyin: '<c:out value="${userinfo.usernamepinyin}"/>' */  };
    </c:forEach>
    function selectUser(obj) {
        userInfo.choose(obj, {dataList: list, userName: $('#userName')});
    }
</script>
</head>

<body>	

	<div class="background" id="background" style="display: none;"></div>
	<div class="progressBar" id="progressBar" style="display: none;">正在处理中......</div>


		<fieldset>
			<legend>同步所有数据</legend>
			<s:form id="frm_sync_all" action="dataSync!syncAll.do"  namespace="/sys" theme="simple">
				<s:hidden name="s_SyncLocation" value="WebService"/>
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<td width="300">
							同步策略:
							<select id="sel_dsmode" name="s_DSMode" class="combox">
		                        <option value="">全部</option>
		                        <c:forEach var="s" items="${cp:DICTIONARY('DSMode') }">
		                            <option value="${s.datacode }">${s.datavalue}</option>
		                        </c:forEach>
		                    </select>
						</td>
	
						<td width="350">
							最后更新时间
							<sj:datepicker id="birdate" name="s_lastModDate" yearRange="1900:2100" displayFormat="yy-mm-dd" changeYear="true" size="20" />
						</td>
						<td align="center">
							<button id="btn_sync_all" class="btn" >提交</button>
							
						</td>
					</tr>
						<tr>
						</tr>
				</table>
			</s:form>
		</fieldset>
		
		<fieldset>
			<legend>同步指定用户数据</legend>
			<s:form id="frm_sync_user" action="dataSync!syncUser.do"  namespace="/sys" theme="simple">		
				<table cellpadding="0" cellspacing="0" >
					<tr>
						
						<td width="300">
							同步用户:
							<s:textfield onclick="selectUser(this);" id="userCode" name="s_usercode" value="%{#parameters['s_usercode']}"/>
						</td>
	
						<td align="center">
							<input type="button" id="btn_sync_user" class="btn" value="提交" />
						</td>
					</tr>
						<tr>
						</tr>
				</table>
			</s:form>
		</fieldset>
	</body>
	
	<script>
		$(function(){
			$('#btn_sync_all').click(function(e){
				if('' == $('#sel_dsmode').val()) {
					alert('请选择同步策略');
					
					e.preventDefault();
					return;
				}
				
				var ajaxbg = $("#background,#progressBar");
				ajaxbg.show();
				$.post('${pageContext.request.contextPath}/sys/dataSync!syncAll.do', $('#frm_sync_all').serialize(), function(jsonData){
					ajaxbg.hide();
					if('0' == jsonData.result) {
						alert('已成功同步数据');
					} else {
						alert(jsonData.msg);
					}
				}, 'json');
				
				e.preventDefault();
			});
			$('#btn_sync_user').click(function(e){
				if('' == $('#userCode').val()) {
					alert('请选择同步用户');
					
					e.preventDefault();
					return;
				}
				
				var ajaxbg = $("#background,#progressBar");
				ajaxbg.show();
				$.post('${pageContext.request.contextPath}/sys/dataSync!syncUser.do', $('#frm_sync_user').serialize(), function(jsonData){
					ajaxbg.hide();
					if('0' == jsonData.result) {
						alert('已成功同步数据');
					} else {
						alert(jsonData.msg);
					}
				}, 'json');
				
				e.preventDefault();
			});
		});
	</script>
			
</html>

<%-- <div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/sys/dataSync!syncAll.do" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <label>同步所有数据</label>
                <li><label>同步方式:</label>
                    <select name="s_SyncLocation" class="combox">
                        <option value="">全部</option>
                        <c:forEach var="s" items="${cp:DICTIONARY('SyncLocation') }">
                            <option value="${s.datavalue }">${s.datavalue}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><label>同步策略:</label>
                    <select name="s_DSMode" class="combox">
                        <option value="">全部</option>
                        <c:forEach var="s" items="${cp:DICTIONARY('DSMode') }">
                            <option value="${s.datacode }">${s.datavalue}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>最后更新时间:</label>
                    <input type="text" name="s_lastModDate" readonly="true" class="date" format="yyyy-MM-dd HH:mm:ss"
                           yearstart="-20" yearend="5"/>
                    <a class="inputDateButton" style="float: right;" href="javascript:;">选择</a>
                </li>
            </ul>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">同步</button>
                            </div>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </form>


    <form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath }/sys/dataSync!syncUser.do"
          method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <label>同步人员数据</label>
                <li><label>同步用户:</label> <s:textfield onclick="selectUser(this);" id="userCode" name="s_usercode"
                                                      value="%{#parameters['s_usercode']}"/></li>
                <li><label>同步方式:</label>
                    <select name="s_SyncLocation" class="combox">
                        <option value="">全部</option>
                        <c:forEach var="s" items="${cp:DICTIONARY('SyncLocation') }">
                            <option value="${s.datavalue }">${s.datavalue}</option>
                        </c:forEach>
                    </select>
                </li>
            </ul>

            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">同步</button>
                            </div>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </form>
</div> --%>
    


