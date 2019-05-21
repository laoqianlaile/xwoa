<script src="${pageContext.request.contextPath}/newStatic/js/3rd/lodop/LodopFuncs.js" type="text/javascript"></script>
<script>
var ctx3rdJS = "${ctx3rdJS}";

var LODOP; //声明为全局变量 
function CheckIsInstall() {	 
	try{ 
	    LODOP=getLodop(); 
		if (LODOP.VERSION) {
			 if (!LODOP.CVERSION)
				 alert("本机已成功安装了Lodop控件！\n 版本号:"+LODOP.VERSION); 
		};
	 }catch(err){ 
  } 
};
/**
 * 打印预览
 */
function printView(rendCallBack){
	if(rendCallBack){
		LODOP=getLodop(); 
		rendCallBack(LODOP);
		LODOP.PREVIEW();
	}
}
</script>


