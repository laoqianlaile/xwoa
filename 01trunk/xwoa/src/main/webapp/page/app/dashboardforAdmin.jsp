<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
 <%@page import="com.centit.sys.service.CodeRepositoryUtil" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/easyui/jquery.messager.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/Echart/esl.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<style>
<!--
.leftInfo table{table-layout: fixed}
.leftInfo ul{table-layout: fixed}
.itemTitle a{
    display: block;
    width: 80%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.number{width: 35px;}
.div_wrapper_table td{border-bottom: #e8e8e8 1px solid;border-right: #e8e8e8 1px solid; }
-->
</style>
<script type="text/javascript">

</script>
<body >
<div class="improveLeft" style="width: 100%">
	<div class="leftInfo">
		<div class="leftCont" >
			<div style="width: 40%">
				<i class="i-l"></i>
				<div class="childBlock" style="height: 390px !important;">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="jwbl-div"
								class="childBlockTile_left1 document-switch-hover"
								>孤儿节点监控</div>
							<i></i>
						</div>
						<div class="childBlockTile_right">
							<a id="grjd_more" class="chooseType" 
								title="孤儿节点监控" 
								onclick="openMenu(this,'grjd','<%=request.getContextPath()%>/sampleflow/sampleFlowManager!listNoOptNodes.do');return false;"
								href="javaScript:void(0);"> <span>更多</span>
							</a>
						</div>
					</div>

					<table style="width: 98%;margin-left: 15px;border-collapse: collapse;" id="grjd" border="0" cellpadding="0" cellspacing="0" >
						 <c:forEach items="${nodeList}" begin="0" end="5" var="node" varStatus='i'>
							<tr><td colspan="2" class='itemTitle'  style='text-align: left;width: 99%'>
							<a  href="javaScript:void(0);" 
							onclick="openMenu(this,'grjd','<%=request.getContextPath()%>/sampleflow/sampleFlowManager!listFlowInstNodes.do?flowInstId=${node.flowInstId}');return false;" >[${cp:MAPVALUE('optTypeName',fn:substringBefore(node.flowOptTag, '0' ))}]${node.flowOptName }</a></td>
							</tr>
							<tr style="border-bottom: 1px dashed #dedede;"  style="background-color: white;" >
							<td style='text-align:left;width:150px;color:#BCBCBC;padding-left: 30px;'  >当前步骤：${node.nodeName}</td>
							<td style='text-align:right;width:50px;padding-right:15px'>${fn:substring(node.createTime,0,10) }</td></tr>
						</c:forEach> 
						
					</table>
									
																
				</div>
				
				<i class="i-r"></i>
			</div>
			<div style="width: 60%">
				<i class="i-l r-l"></i>
				<div class="childBlock" style="height: 390px !important;">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
                             <div id="tongzhi-div" onmouseover="optBaseExceptionCount();"
								class="childBlockTile_left1 document-switch-hover">异常监控统计</div>
						</div>
					</div>
					<div style="float: left;width: 70%;height: 380px;padding-bottom: 5px;text-align: center;" id="myChart">
					
					</div>
					<div style="float: right;width: 28%;" >
					<table border="0" class="div_wrapper_table" cellspacing='0' style="width: 90%;height: 342px !important;border-left: #e8e8e8 1px solid;border-right: #e8e8e8 1px solid;" id="tzgg">
						<tr style="height: 41px;background-color:#4E81BD;color: white; ">
							<td style="width: 41px;">业务类别</td>
							<td style="width: 41px;">即将超期</td>
							<td style="width: 41px;">已超期</td>
							<td style="width: 51px;">长期未处理</td>
							</tr>
							<c:forEach var="row" items="${cp:DICTIONARY('optTypeName')}">
							<c:if test="${row.datacode ne 'HYSSQ'}">
							<tr style="height: 41px;">
							<td>${row.datavalue}</td>
							<td><a id="jjcq_${row.datacode}" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/powerruntime/VoptBaseException!list.do?s_itemType=${row.datacode}&s_type=jjcq');return false;">0</a></td>
							<td><a id="ycq_${row.datacode}" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/powerruntime/VoptBaseException!list.do?s_itemType=${row.datacode}&s_type=ycq');return false;">0</a></td>
							<td><a id="cqwb_${row.datacode}" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/powerruntime/VoptBaseException!list.do?s_itemType=${row.datacode}&s_type=cqwb');return false;">0</a></td>
							</tr>
							</c:if>
							</c:forEach>
					</table>
					</div>				
					
				</div>
				<i class="i-r r-r"></i>
			</div>
		</div>
		
		<div class="leftCont">
			<div style="width: 40%">
				<i class="i-l"></i>
				<div class="childBlock" style="height: 390px !important;">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="sjx-div"
								class="childBlockTile_left1 document-switch-hover"
								onmouseover="jrbjhj();">今日办件痕迹</div>
							<i></i>
						</div>

						<div  class="childBlockTile_right">
							<a  title="今日办件痕迹"
								 id="yj_more"
								onclick="openMenu(this,'','<%=request.getContextPath()%>/powerruntime/generalOperator!listOptProcInfoToday.do');return false;"
								href="javaScript:void(0);"> <span>更多</span>
							</a>
						</div>
					</div>

					<table style="width: 100%;border-collapse: collapse;" id="sjx">
					</table>
					
					
					
				</div>
				
				<i class="i-r"></i>
			</div>
			
			
			<div style="width: 60%">
				<i class="i-l r-l"></i>
				
				<div class="childBlock" style="height: 390px !important;">
					<div class="childBlockTile">
						
						<div class="childBlockTile_left">
							<div id="dwbl-div" name="dwbl-div"
								class="childBlockTile_left1 document-switch-hover"
								onmouseover="optBaseCountbyyear();">
								业务量统计</div>
							<i></i>
						</div>
					
					</div>

					<div style="float: right;width: 28%">
					<table border="0" cellspacing='0' class="div_wrapper_table" cellspacing='0' style="width: 90%;height: 342px !important;border-left: #e8e8e8 1px solid;border-right: #e8e8e8 1px solid" >
					<tr style="height: 41px;background-color:#4E81BD;color: white; ">
						<td style="width: 41px;">业务类别</td>
						<td style="width: 41px;">在办</td>
						<td style="width: 41px;">已办</td>
						<td style="width: 41px;">总计</td>
					</tr>
					<c:forEach var="row" items="${cp:DICTIONARY('optTypeName')}">
						<c:if test="${row.datacode ne 'HYSSQ'}">
							<tr style="height: 41px;">
								<td>${row.datavalue}</td>
								<c:choose>
									<c:when test="${row.datacode eq 'QB' or row.datacode eq 'SQ'}">
										<td><a id="${row.datacode}_NUM_T" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/oa/vOptBaseList!list.do?s_showAdminPage=${row.datacode}&&s_bizstate=W');return false;">0</a></td>
									</c:when>
									<c:otherwise>
										<td><a id="${row.datacode}_NUM_W" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/oa/vOptBaseList!list.do?s_showAdminPage=${row.datacode}&&s_bizstate=W');return false;">0</a></td>
									</c:otherwise>
								</c:choose>
								<td><a id="${row.datacode}_NUM_C" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/oa/vOptBaseList!list.do?s_showAdminPage=${row.datacode}&&s_bizstate=C');return false;">0</a></td>
								<td><a id="${row.datacode}_NUM" href="javaScript:void(0);" onclick="openMenu(this, '','<%=request.getContextPath()%>/oa/vOptBaseList!list.do?s_showAdminPage=${row.datacode}');return false;">0</a></td>
							</tr>
						</c:if>
					</c:forEach>
					</table>
					</div>
					<div style="float:left ;width: 70%;height: 340px;padding-bottom: 5px;" id="myChart2">
					
					</div>
					
				</div>
				
				
				<i class="i-r r-r"></i>
			</div>
		</div>
		
		
	</div>
</div>

<div id="improveFooter">
	<div>
	版权所有：新疆维吾尔自治区交通运输厅	
	</div>
	<div>技术支持：江苏南大先腾信息产业有限公司 </div>
</div>
</body>

<iframe id="downloadBox" height="0"></iframe>
    

<script type="text/javascript">



    
    function downloadFile(type,infocode){
    	
    	var href = "<%=request.getContextPath()%>/app/publicinfo!download.do";
    	$.post(href, {infocode: infocode, mode: type}, function(data){
    		downloadFileCallback(data, true);
		}, 'json');
    	
    }
    
    $(function(){
    	
    	//var xzjb='${userRank}';//根据行政级别显示对应模块
    	jrbjhj();//今日办件痕迹
    	optBaseExceptionCount();//办件异常统计
    	optBaseCountbyyear();//年度办件统计
    	//kuang();
    });
    function omitText(src,start,end){
    	if(src.length>end){
    		src=src.substring(start,end);
    	}
    	return src;
    }
    function changeTime(time){
    	var s=time.substr(5,5);
    	return s;
    }
    function emailreadstate(state){
    	if(state=="U")
    		return "未读";
    	else if(state=="R")
    		return "已读";
    }
    function jwblstate(state){
    	if(state=="W"||state=="T"){
    		return "办理中";
    	}
    	if(state=="C")
    		{
    		return "已办结";
    		}
    }
    
   
    //孤儿节点
   <%--  function grjd(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!showgrjd.do" ,
    		type:"post",
    		dataType:"json",
    		success:function(data){
    		var list=data;
    			if(list!=null){
    			 $.each(list,function(i,o){
    				 var item;
    					item="<tr><td  class='itemTitle_left'></td>"+
    					"<td class='itemTitle' >";
    					item+="<a  id='grjd"+i+"' target='navTab' external='true'>";
						item+="<span title='"+o.flowOptName+"'>"+o.flowOptName+"</span>";
						item+="</a>";
						/* +"<td class='itemTitle_right' style='width:20px'>"+o.readstate
						+"</td>" */
						item+="</td><td  style='text-align:right;width:50px;padding-right:15px'>"+
						changeTime(o.createTime)+
						"</td>"+
					"</tr>";
						if(i<=8){
						$("#grjd").append(item);
						var id="grjd"+i;
						var itemtype=omitText(o.flowOptTag,0,2);
						var itemtypeurl=<%=CodeRepositoryUtil.getValue("optType", o.getFlowOptTag().substring(0, 2))%>
						 $('#' + id).click(function(){
							openMenu(this, 'grjd', '<%=request.getContextPath()%>/'+itemtypeurl+'!generalOptView.do?djId='+o.flowOptTag+'&s_itemtype='+itemtype+
									'&nodeInstId=0');							
						});
						}
    			}) ;
    			}
    		}
    	});
    } --%>
    
  //年度办件统计
    function optBaseCountbyyear(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!showOptBaseNumByYear.do",
    		type:"post",
    		dataType:"json",
    		async:false, 
    		success:function(data){
    			for(var key in data){
    				if(data.hasOwnProperty(key)){
    					$("#"+key).html(data[key]);
    				}
    			}
    		}
    	});
    	$("#SW_NUM").html(parseInt($("#SW_NUM_W").html())+parseInt($("#SW_NUM_C").html()));
    	$("#FW_NUM").html(parseInt($("#FW_NUM_W").html())+parseInt($("#FW_NUM_C").html()));
    	$("#QB_NUM").html(parseInt($("#QB_NUM_T").html())+parseInt($("#QB_NUM_C").html()));
    	$("#SQ_NUM").html(parseInt($("#SQ_NUM_T").html())+parseInt($("#SQ_NUM_C").html()));
    	$("#HYSQ_NUM").html(parseInt($("#HYSQ_NUM_W").html())+parseInt($("#HYSQ_NUM_C").html()));
    	$("#CARSQ_NUM").html(parseInt($("#CARSQ_NUM_W").html())+parseInt($("#CARSQ_NUM_C").html()));
    	$("#DCDB_NUM").html(parseInt($("#DCDB_NUM_W").html())+parseInt($("#DCDB_NUM_C").html()));
    	var date=new Date();
    	var year=date.getFullYear();
    	var month=date.getMonth()+1;
    	var day=date.getDate();
    	/* var jjcqdata=$("#JJCQ_FW").html()+","+$("#JJCQ_SW").html()+","+$("#JJCQ_QB").html()+","+$("#JJCQ_SQ").html()+","+$("#JJCQ_HYSQ").html()+","+$("#JJCQ_CARSQ").html();
    	var ycqdata=$("#YCQ_FW").html()+","+$("#YCQ_SW").html()+","+$("#YCQ_QB").html()+","+$("#YCQ_SQ").html()+","+$("#YCQ_HYSQ").html()+","+$("#YCQ_CARSQ").html();
    	var cqwbdata=$("#CQWB_FW").html()+","+$("#CQWB_SW").html()+","+$("#CQWB_QB").html()+","+$("#CQWB_SQ").html()+","+$("#CQWB_HYSQ").html()+","+$("#CQWB_CARSQ").html(); */
    	//路径配置
    	require.config({
    	    paths:{ 
    	        'echarts' : '${pageContext.request.contextPath}/scripts/Echart/echarts',
    	        'echarts/chart/bar' : '${pageContext.request.contextPath}/scripts/Echart/echarts'
    	    }
    	});  
    	/* 
    	 *按需加载 
    	 */  
    	 require(  
    	     [  
    	         'echarts',  
    	         'echarts/chart/bar' 
    	     ],  
    	     //渲染ECharts图表  
    	     function (ec) {  
    	    	// alert(ec.init());
    	         //图表渲染的容器对象  
    	         var chartContainer2 = ec.init(document.getElementById("myChart2"));
    	         //加载图表  
    	          option = {
    		          		    title : {
    		          		    	text: year+'年度1月1日至'+month+'月'+day+'日办件量统计',
    		          		    	x:'center'
    		          		    },
    		          		    tooltip : {
    		          		        trigger: 'axis'
    		          		    },
    		          		    legend: {
    		          		    	padding: 300,                             // 图例内边距，单位px，默认上下左右内边距为5
    		          		        itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
    		          		        data: ['在办','已办']
    		          		    },
    		          		    toolbox: {
    		          		        
    		          		    },
    		          		    calculable : true,
    		          		    xAxis : [
    		          	                 {
    		          	                     type : 'category',
    		          	                     axisLabel:{
    		          	                      interval:0,
    		          	                         rotate:45,
    		          	                         margin:2,
    		          	                         textStyle:{
    		          	                             color:"#222"
    		          	                         }
    		          	                     },
    		          	                     data : ['收文','发文','签报','事权','会议申请','车辆申请','督办催办']
    		          	                 }
    		          	             ],
    		          	             grid: { // 控制图的大小，调整下面这些值就可以，
    		          	                 x: 40,
    		          	                 x2: 20,
    		          	                 y2: 100,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
    		          	             },
    		          		    yAxis : [
    		          		        {
    		          		        	title:{text:'办件数量'},
    		          		            type : 'value',
    		          		            boundaryGap: [0.1, 0.1],            // 坐标轴两端空白策略，数组内数值代表百分比
    		          		            splitNumber: 4                      // 数值轴用，分割段数，默认为5
    		          		        }
    		          		    ], 
    		          		    series : [
    		          		        {
    		          		            name:'在办',
    		          		            type:'bar',
    		          		            data:[$("#SW_NUM_W").html(),$("#FW_NUM_W").html(),$("#QB_NUM_T").html(),$("#SQ_NUM_T").html(),$("#HYSQ_NUM_W").html(),$("#CARSQ_NUM_W").html(),$("#DCDB_NUM_W").html()]
    		          		        },
    		          		      {
  		          		            name:'已办',
  		          		            type:'bar',
  		          		            data:[$("#SW_NUM_C").html(),$("#FW_NUM_C").html(),$("#QB_NUM_C").html(),$("#SQ_NUM_C").html(),$("#HYSQ_NUM_C").html(),$("#CARSQ_NUM_C").html(),$("#DCDB_NUM_C").html()]
  		          		        }
    		          		    ]
    		          		};
    	         chartContainer2.setOption(option);  
    	     }  
    	 );      
    }
    
    
    //异常统计
    function optBaseExceptionCount(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!showOptBaseExceptionNum.do",
    		type:"post",
    		dataType:"json",
    		async:false, 
    		success:function(data){
    			for(var key in data){
    				if(data.hasOwnProperty(key)){
    					$("#"+key).html(data[key]);
    				}
    			}
    		}
    	});
    	/* var jjcqdata=$("#JJCQ_FW").html()+","+$("#JJCQ_SW").html()+","+$("#JJCQ_QB").html()+","+$("#JJCQ_SQ").html()+","+$("#JJCQ_HYSQ").html()+","+$("#JJCQ_CARSQ").html();
    	var ycqdata=$("#YCQ_FW").html()+","+$("#YCQ_SW").html()+","+$("#YCQ_QB").html()+","+$("#YCQ_SQ").html()+","+$("#YCQ_HYSQ").html()+","+$("#YCQ_CARSQ").html();
    	var cqwbdata=$("#CQWB_FW").html()+","+$("#CQWB_SW").html()+","+$("#CQWB_QB").html()+","+$("#CQWB_SQ").html()+","+$("#CQWB_HYSQ").html()+","+$("#CQWB_CARSQ").html(); */
    	//路径配置
    	require.config({
    	    paths:{ 
    	        'echarts' : '${pageContext.request.contextPath}/scripts/Echart/echarts',
    	        'echarts/chart/bar' : '${pageContext.request.contextPath}/scripts/Echart/echarts'
    	    }
    	});  
    	/* 
    	 *按需加载 
    	 */  
    	 require(  
    	     [  
    	         'echarts',  
    	         'echarts/chart/bar' 
    	     ],  
    	     //渲染ECharts图表  
    	     function (ec) {  
    	    	// alert(ec.init());
    	         //图表渲染的容器对象  
    	         var chartContainer = ec.init(document.getElementById("myChart"));
    	         //加载图表  
    	          option = {
    		          		    title : {
    		          		    	text: 'OA运行数据异常监控统计',
    		          		    	x:'center'
    		          		    },
    		          		    tooltip : {
    		          		        trigger: 'axis'
    		          		    },
    		          		    legend: {
    		          		    	padding: 300,                             // 图例内边距，单位px，默认上下左右内边距为5
    		          		        itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
    		          		      	align: 'center', 
    		          		        x:140,
    		          		        y:300,
    		          		        data: ['即将超期', '已超期','长期未处理']
    		          		    },
    		          		    toolbox: {
    		          		        
    		          		    },
    		          		    calculable : true,
    		          		    xAxis : [
    		          	                 {
    		          	                     type : 'category',
    		          	                     axisLabel:{
    		          	                      interval:0,
    		          	                         rotate:45,
    		          	                         margin:2,
    		          	                         textStyle:{
    		          	                             color:"#222"
    		          	                         }
    		          	                     },
    		          	                     data : ['收文','发文','签报','事权','会议申请','车辆申请','督办催办']
    		          	                 }
    		          	             ],
    		          	             grid: { // 控制图的大小，调整下面这些值就可以，
    		          	                 x: 40,
    		          	                 x2: 20,
    		          	                 y2: 140,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
    		          	             },
    		          		    yAxis : [
    		          		        {
    		          		        	title:{text:'办件数量'},
    		          		            type : 'value',
    		          		            boundaryGap: [0.1, 0.1],            // 坐标轴两端空白策略，数组内数值代表百分比
    		          		            splitNumber: 4                      // 数值轴用，分割段数，默认为5
    		          		        }
    		          		    ], 
    		          		    series : [
    		          		        {
    		          		            name:'即将超期',
    		          		            type:'bar',
    		          		            data:[$("#jjcq_SW").html(),$("#jjcq_FW").html(),$("#jjcq_QB").html(),$("#jjcq_SQ").html(),$("#jjcq_HYSQ").html(),$("#jjcq_CARSQ").html(),$("#jjcq_DCDB").html()]
    		          		        },
    		          		      	{
    		          		            name:'已超期',
    		          		            type:'bar',
    		          		            data:[$("#ycq_SW").html(),$("#ycq_FW").html(),$("#ycq_QB").html(),$("#ycq_SQ").html(),$("#ycq_HYSQ").html(),$("#ycq_CARSQ").html(),$("#ycq_DCDB").html()]
    		          		        },
    		          		      	{
    		          		            name:'长期未处理',
    		          		            type:'bar',
    		          		            data:[$("#cqwb_SW").html(),$("#cqwb_FW").html(),$("#cqwb_QB").html(),$("#cqwb_SQ").html(),$("#cqwb_HYSQ").html(),$("#cqwb_CARSQ").html(),$("#cqwb_DCDB").html()]
    		          		        }
    		          		    ]
    		          		};
    	         chartContainer.setOption(option);  
    	     }  
    	 );      
    }
  //今日办件痕迹
    function jrbjhj(){
    	$.ajax({
    		url:"<%=request.getContextPath()%>/app/dashboard!showToDayOptProcinfo.do",
    		type:"post",
    		dataType:"json",
    		success:function(date){
    					$("#sjx").html("");
    					var list=date;
    					if(list!=null){
    						 $.each(list,function(i,o){
    				 			var item;
    							item="<tr>";
		    					item+="<td colspan='3' class='itemTitle'  >"+
		    					"<a style='height:16px!important;'";
		    					item+=" id='bjhj"+i+"' ";
		    					item+="target='navTab' external='true'"+
		    					" title='今日办件痕迹' >" ;
		    					item+="<span title='"+o.username+"'>["+o.itemTypeName+"]</span>"+
								"<span title='"+o.transaffairname+"'>";
								item+=o.transaffairname+"</span>"+
								"</a>"+"</td></tr>";
								item+="<tr style='border-bottom: 1px dashed #dedede;'><td style='text-align:left;width:150px;color:#BCBCBC;padding-left: 30px;' title='"+o.nodename+"'>当前步骤："+omitText2(o.nodename)+
								"</td><td style='text-align:right;width:150px;color:#BCBCBC;padding-right:15px' title='"+o.username+"'>办理人员："+omitText2(o.username)+
								"</td><td style='text-align:right;width:50px;padding-right:15px'>"+changeTime1(o.transdate)+"</td></tr>";
								 if(i<=5){
									 	var id="bjhj"+i;
					    				$("#sjx").append(item);
					    				$('#' + id).click(function(){
					    					showView('过程信息','<%=request.getContextPath()%>/powerruntime/generalOperator!listIdeaLogs.do?djId='+o.djId);
											<%-- openMenu(this,'',"<%=request.getContextPath()%>/oa/oaSchedule!view.do?schno="+o.no+"&dashboard=RCAP"); --%>
										});
								 }
    						}) ;
    			 		}
    		}
    	});
    }
 
    function changeTime1(time){
    	var s=time.substr(time.length-8,5);
    	return s;
    }
	function omitText1(src){
    	if(src.length>8){
    		src=src.substring(0,8);
    		src+="...";
    	}
    	return src;
    }function omitText2(src){
    	if(src.length>4){
    		src=src.substring(0,4);
    		src+="...";
    	}
    	return src;
    }
	function timestamp(){
		var timestamp = parseInt(new Date().getTime()/1000); 
		return timestamp;
	}
	function showView(title,link){
		DialogUtil.open(title,link,1200,700);
	}
</script>