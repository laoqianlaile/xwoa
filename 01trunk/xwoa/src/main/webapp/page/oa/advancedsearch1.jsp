<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高级搜索</title>
<script	src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/newStatic/js/custom/common/dashboardV3.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/scripts/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>

<style type="text/css">
  .search_input_area{width:98%;margin:0 auto;background:#fff;padding:10px;position: relative;}
  .search_input_area > div.input_box{width:100%;background:#f3f3f3;overflow:hidden}
  .search_input_area > div.input_box > input{width:100%;background:#f3f3f3;height:30px;border:none;line-height:30px;padding-left:85px;padding-right:32px;}
  .search_input_area > div.select_box{position:absolute;width:80px;top:10px;height:30px;border-right:1px solid #E3E3E3;background:#f3f3f3}
  .search_input_area > div.select_box .selectorText{font-size:14px;display:block;width:100%;text-align: center;line-height:32px;cursor: pointer;}
  .search_input_area > div.select_box .selectorArrow{border:solid 5px transparent;border-top-color:#9A9A9A;position: absolute;display:block;right:5px;top:13px;cursor: pointer;}
  .search_input_area > div.select_box .selectorItems{position: absolute;width:78px;background:#fff;border:1px solid #E3E3E3}
  .search_input_area > div.select_box .selectorItems ul{padding:0;margin:5px 0;}
  .search_input_area > div.select_box .selectorItems ul li{list-style-type: none;margin:0; padding: 0px;}
  .search_input_area > div.select_box .selectorItems ul li a{display:block;height:22px;line-height:22px;width:100%;text-align: center;cursor: pointer;}
  .search_input_area > div.select_box .selectorItems ul li a:hover{background:#E7F2F9}
  .search_input_area > div.select_box .selectorItems ul li a.on{background:#E7F2F9}
  .search_input_area > a{position:absolute;width:32px;top:10px;height:32px;right:10px;background:url('${pageContext.request.contextPath}/xuwei/images/XWsearch_btn.png') no-repeat -1px -1px; }
  /*查询历史*/
  .search_input_area > div.input_box .searchHistoryItems{position: absolute;left:90px;width:500px;background:#fff;border:1px solid #E3E3E3}
  .search_input_area > div.input_box .searchHistoryItems ul{padding:0;margin:5px;}
  .search_input_area > div.input_box .searchHistoryItems ul li{list-style-type: none;margin:0; padding: 0px;overflow: hidden}
  .search_input_area > div.input_box .searchHistoryItems ul li:hover{background:#E7F2F9}
  .search_input_area > div.input_box .searchHistoryItems ul li span{font-size:12px;font-family:arial;color:#666;display:inline-block;height:22px;line-height:22px;float:left;width:90%;cursor: pointer;}
  .search_input_area > div.input_box .searchHistoryItems ul li a{font-size:12px;font-family:arial;color:#666;display: inline-block;float:right;height:22px;line-height:22px;cursor: pointer;}
  .search_input_area > div.input_box .searchHistoryItems ul li a:hover{text-decoration: underline;}
  /*查询结果*/
  .search_result_area{width:98%;margin:0 auto;background:#fff;padding:10px;margin-top:20px;}
  .search_result_area  ul{padding:0;margin:5px;}
  .search_result_area  ul li{list-style-type: none;margin:0; padding: 0px;overflow: hidden;border-bottom:1px dashed #ccc;height:30px}
  .search_result_area  ul li a{display:inline-block;height:30px;line-height:30px;font-size:14px;font-family:arial;cursor: pointer;}
  .search_result_area  ul li a:hover{color:#005580}
  .search_result_area  ul li a label{color:orange;margin-right:10px;}
  .search_result_area .pager a{display:inline-block;font-size:12px;padding:2px;color:#707070;margin-right:5px;cursor: pointer;}
  .search_result_area .pager a:hover{text-decoration: underline;}
  .search_result_area .pager span{font-size:12px;color:#707070}
</style>
 <script type="text/javascript">
 $(function(){
	 initSelector();
	 initSearchHistory();
	 $(".search_input_area>a").click(function(){
		 doSearch($("#keywords").val(),1); 
	 });
	 //父页面传输的查询条件是否存在，如果存在那么在页面展示时就进行数据检索
	 if(art.dialog.data("transferData") && art.dialog.data("transferData").length>0){
		var searchText =  art.dialog.data("transferData");
		$("#keywords").val(searchText);
		 doSearch(searchText,1); 
	 }
 });
  function initSelector(){
	  //触发下拉选择
	  $(".select_box").on("click",".selectorText,.selectorArrow",function(event){
		  //下拉显示
		  $(".selectorItems").show();
		  //点击下拉之外的区域隐藏下拉框
		  $(document).on("click",function(e){
			 if(!$(e.target).hasClass("selector-item")){
				 $(".selectorItems").hide();
				 $(document).off("click");
			 }
		  });
		  //阻止事件冒泡，不然会触发文档单击事件
		  event.stopPropagation();
	  });
	  //选中选项事件
	  $(".selectorItems").find("a").click(function(){
		$(".selectorText").text($(this).text()); 
		$(".selectorText").data("value",$(this).data("optval"));
		$(".selectorItems").hide();
	  });
  }
  
  //获取下拉选择框的值
  function getSelectorVal(){
	  return $(".select_box .selectorText").data("value")||'0';
  }
  //初始化历史查询
  function initSearchHistory(){
	var displayHistorySearchKeys = function(event){
		if($.trim($(this).val()).length==0){
			//获取历史记录
			$.ajax({
			   type:"get",
			   url:"${pageContext.request.contextPath}/oa/advsearch!getHistorySearchKeys.do",
			   dataType:"json",
			   success:function(respData){
			     if(respData.length!=0){
                      //渲染历史搜索关键字
			    	 $(".searchHistoryItems li").not(":last").remove();
			    	   $.each(respData,function(){
			    		   var span = $("<span>").text(this.mainKey);
			    		   //单击历史记录，进行查询
			    		   span.on("click",function(){
			    			   $(".searchHistoryItems").hide();
			    			  $("#keywords").val($(this).text());
			    			   doSearch(span.text(),1); 
			    		   });
			    		   //删除项
			    		   var a = $("<a>").data("no",this.no).text("删除");
			    		   a.click(function(){
			    			   removeOneHistory(span.text(),this);
			    		   });
			    		   var li = $("<li>");
			    		   li.append(span).append(a).insertBefore($(".searchHistoryItems li:first"));
			    	   });
			    	 //重新计算下拉框的宽度
						var historyItemsWidth = $(".input_box").width() - $(".select_box").width() -  $(".search_input_area > a").width();
						$(".searchHistoryItems").width(historyItemsWidth).show();
                         //点击文档其他区域时关闭下拉框
						 $(document).on("click",function(e){
							 if($(e.target).parents(".searchHistoryItems").length==0){
								 $(".searchHistoryItems").hide();
								 $(document).off("click");
							 }
						 });
						  //阻止事件冒泡，不然会触发文档单击事件
						 event.stopPropagation();
			     }
			   }
			});
		}else{
			 $(".searchHistoryItems").hide();
			 $(document).off("click");
		}
	};
	  //单击输入框弹出以前的搜索历史记录
	  $("#keywords").on("click keyup",displayHistorySearchKeys);
	  
	  setTimeout(function(){
		var resultAreaHeight = $(window).height() - 115;
		$(".search_result_area").height(resultAreaHeight);
	  },100);
	  
  }
  
  /**
   *进行检索
   */
  function doSearch(keywords,pn){
	  if($.trim(keywords).length == 0){
		  return;
	  }
	  var requestParam = {pageNo:pn};
	  var searchScope = getSelectorVal();
	  if(searchScope!="0"){
		  requestParam.searchScope = searchScope;
	  }
	  requestParam.keywords = keywords;
	  $("#loading_process").show();
	  $("#data_list").hide();
	  $.ajax({
		  type:"get",
		  url:"${pageContext.request.contextPath}/oa/advsearch!getSearchResult.do",
		  dataType:"json",
		  data:requestParam,
		  success:function(respData){
			  $("#loading_process").hide();
			  //如果没有查询到结果，那么无结果提示，否则显示数据列表
			  if(respData.objList.length==0){
				  $("#empty_tip").show();
				  $("#data_list").hide();
			  }else{
				  $("#data_list").find("ul").html("");
				  $.each(respData.objList,function(){
					  var li = $("<li>");
					  var a = $("<a>").text(this.title);
					  var label = $("<label>").text("【"+this.itemTypeText+"】");
					 a.prepend(label);
					  var _ = this;
					  a.click(function(){
						  forword(_.itemType,{djId:_.djId},this); 
					  });
					  li.append(a).appendTo($("#data_list").find("ul"));
				  });
				  //分页工具栏
				  var totalPage = Math.ceil(parseInt(respData.page.totalRows)/parseInt(respData.page.pageSize));
				  $("#data_list").find(".pager #firstPage").data("pageNo",1);
				  $("#data_list").find(".pager #prevPage").data("pageNo",respData.page.pageNo-1 <= 0 ? 1 : respData.page.pageNo-1);
				  $("#data_list").find(".pager #nextPage").data("pageNo",respData.page.pageNo+1 >= totalPage ? totalPage : respData.page.pageNo+1);
				  $("#data_list").find(".pager #lastPage").data("pageNo",totalPage);
				
				  var descText = "共检索到"+respData.page.totalRows+"条记录，每页显示"+respData.page.pageSize+"条，共"+totalPage+"页，当前在第"+respData.page.pageNo+"页。";
				  $("#data_list").find(".pager").find("span").text(descText);
				  $("#empty_tip").hide();
				  $("#data_list").show();
			  }
		  }
	  });
  }
  /**
  * 翻页
  */
  function goPage(ele){
	  var keywords = $("#keywords").val();
	  var pageNo = $(ele).data("pageNo");
	  doSearch(keywords,pageNo);
  }
  /**
  * 删除单条历史记录
  */
  function removeOneHistory(keywords,btnEle){
	  $.ajax({
	    type:"post",
	    url:"${pageContext.request.contextPath}/oa/oaSearchHistory!removeHistory.do",
	    dataType:"json",
	    data:{
	    	"keywords":keywords
	    },
	    success:function(){
	    	$(btnEle).closest("li").remove();
	    	if($(".searchHistoryItems").find("li").length==1){
	    		 $(".searchHistoryItems").hide();
				 $(document).off("click");
	    	}
	    }
	  })
  }
  /**
   * 删除历史记录
   */
  function removeAllHistory(){
	  $.ajax({
		    type:"post",
		    url:"${pageContext.request.contextPath}/oa/oaSearchHistory!removeAllHistory.do",
		    dataType:"json",
		    success:function(){
		    	 $(".searchHistoryItems").hide();
				 $(document).off("click");
		    }
		  });
  }
  //跳转
  function forword(itemType,data,eleObj){
	  if(itemType=='YJ'){ //邮件
		  parent.openMenu(eleObj,'sjx','${pageContext.request.contextPath}/oa/innermsg!recList.do?s_msgtype=P&s_mailtype=I');
	  }else if(itemType=='ZX'){//资讯
		  parent.openMenu(eleObj,'wdtz','${pageContext.request.contextPath}/oa/oaInformation!view.do?no='+data.djId+'&flag=GGZY');
	  }else{
		  var entry = {'CARSQ':'oa/oaCarApply','DCDB':'oa/oaSupervise','FW':'dispatchdoc/dispatchDoc','HYSQ':'oa/oaMeetApply','HYSSQ':'','QB':'powerruntime/optApply','SQ':'powerruntime/optApply','SW':'dispatchdoc/incomeDoc'};
		if(entry[itemType])
		  parent.openMenu(eleObj,'jwbl','${pageContext.request.contextPath}/'+entry[itemType]+'!generalOptView.do?djId='+data.djId+'&nodeInstId=0&applyItemType=&dashboard=T');
	  }
	 parent.DialogUtil.close();
  }
 </script>
</head>
<body style="background:#f3f3f3; ">
   <div class="search_input_area" style="width:50%">
      <div class="select_box" >
         <span class="selectorText">全部</span>
         <span class="selectorArrow"></span>
         <div class="selectorItems" style="display:none">
            <ul>
              <li><a class="selector-item"  data-optval="0">全部</a></li>
              <li><a class="selector-item"  data-optval="1">办件</a></li>
              <li><a class="selector-item"  data-optval="2">附件</a></li>
              <li><a class="selector-item"  data-optval="3">邮件</a></li>
              <li><a class="selector-item"  data-optval="4">资讯</a></li>
            </ul>
         </div>
      </div>
      <div class="input_box">
         <input type="text" id="keywords">
         <div class="searchHistoryItems" style="display:none">
           <ul>
             <li><a onclick="removeAllHistory()">清除历史</a></li>
           </ul>
         </div>
      </div>
      <a href="javascript:void(0);"></a>
   </div>
   <div class="search_result_area" >
     <div id="data_list" style="display:none">
      <ul>
      </ul>
      <div class="pager" style="padding-left:10px">
        <a id="firstPage" onclick="goPage(this)">首页</a><a id="prevPage" onclick="goPage(this)">上一页</a><a id="nextPage" onclick="goPage(this)">下一页</a><a id="lastPage" onclick="goPage(this)">尾页</a> <span>共检索到15条记录，每页显示15条，共2页，当前在第1页。</span>
      </div>
      </div>
      <div id="empty_tip" style="display:none">
         <p>抱歉，没有找到你想要的结果。</p>
      </div>
      <div id="loading_process" style="margin:0 auto;padding-top:20%;text-align:center;display:none">
         数据正在检索，请稍等.....
      </div>
   </div>
</body>
</html>