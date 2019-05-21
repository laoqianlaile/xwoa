var checkFileFlag = false; // 文件是否已经检查的标志

function uploadFile(button,id,callback) {

	var $button=$(button);
	
	if (checkFileFlag == false) {
		checkFileFlag = true;
		var flag = checkFilePath();
		if (flag) {
			alert("上传文件不能为空");
			checkFileFlag = false;
			return;
		}
	}
	alert($(button).attr("action")); 
	alert(id);
	$.ajaxFileUpload(
        {
            url:$(button).attr("action"),//用于文件上传的服务器端请求地址
            secureuri:false,//一般设置为false
            fileElementId: id,//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json',//返回值类型 一般设置为json
            success: callback,
            error: function() {
				alert("上传失败");
			}
        });
    
    return false;

}

function checkFilePath() {
	var flag = false;
	$("input[type*='file']").each(function(i) {
		if ($(this).val() == null || $(this).val() == "") {
			flag = true;
			return flag;
		}
	});
	return flag;
}

function NextOp(tag){
	var $tag=$(tag);
	var id2=$(tag).attr("id")+"_2";	
	var defaultvalue=$(tag).attr("defaultvalue");
	$("#"+id2+"").empty();
	//if(defaultvalue==null)
	$("#"+id2+"").append("<option  value='' >--请选择--</option>");
	$.ajax({
		   type: "POST",
		   url: $(tag).attr("action"),
		   data: "selectvalue="+$(tag).attr("value"),
		   async:false,
		   success: function(data){
			   
			     for(k in data)
			    	 {			    	
			    	 if(defaultvalue==k)
			    	     $("#"+id2+"").append("<option  value='"+k+"' selected='selected'>"+data[k]+"</option>"); 		    	 
			    	 else
			    		 $("#"+id2+"").append("<option  value='"+k+"'>"+data[k]+"</option>");  
			    	 }	
			   
		   }
			
		 });

}


function doajaxSubmit(form,button){
	 var $form=$(form);
	 var $button=$(button);
	 alert(path+$form.attr("action"));
	// alert($form.attr("action")+"!"+$button.attr("method") +".do");
	 
	$.ajax({
		   type: "POST",
		   url: $form.attr("action"),         //+"!"+$button.attr("method") +".do",
		   data: $form.serializeArray(),
		   cache: false,
		   async:false,
		   success: function(){
			   alert("xxxx");
			   return true;		      
		   },
		  error:function(){
			  return false;
		  }
			
		 });
}
