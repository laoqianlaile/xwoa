 
/*
 * 基于jquery的验证插件
 * 目前只支持inputValidator（针对input、textarea、select控件的字符长度、值范围、选择个数的控制）
 * 			regexValidator（提供可扩展的正则表达式库）
 * 			有想法和需求可以继续提供(ajax验证待做)
*/


$(document).ready(function(){
  		for(var i=0;i<document.forms.length;i++){
    			var f=document.forms[i];   			
    			if($(f).attr('validator')=="true"){
    				f.onsubmit=function(){
    					return validatorform(f.id);
    					
    				};
    			}
    		}

    	}); 



function validatorform(id){
	//alert(inputValidator(id));
	if(!inputValidator(id))
		return false;
	if(!regexValidator(id))
		return false;
	return true;

	
}
  
/*
 * 对象解析
 * */
function initconfig(input){
	this.min=0;				//最小长度
	this.max=999999999;		//最大长度
	this.regexEnum="";		//正则表达式
	this.regex="";			//直接是正则表达式
	this.errorshow="不符合格式";
	this.errorshow2="";
	this.ajaxaction="";
	if(input.getAttribute('min')){
		this.min=input.getAttribute('min');
	}
	if(input.getAttribute('max')){
		this.max=input.getAttribute('max');
	}
	if(input.getAttribute('errorshow')){
		this.errorshow=input.getAttribute('errorshow');
	}
	if(input.getAttribute('errorshow2')){
		this.errorshow2=input.getAttribute('errorshow2');
	}
	if(input.getAttribute('regex')){
		this.regex=input.getAttribute('regex');
	}
	if(input.getAttribute('regexEnum')){
		this.regexEnum=input.getAttribute('regexEnum');
	}
	if(input.getAttribute('ajaxaction')){
		this.regexEnum=input.getAttribute('ajaxaction');
	}
		
}

/*
 * inputValidator（针对text、radio、textarea、select控件的字符长度、值范围、选择个数的控制）
 * 
 * */
function inputValidator(formid){
	try{
	
		
	//text
	var inputs=$("#"+formid+" :input[validator=input][type=text]");

	for (var i =0;i<inputs.length;i++){	
		if(!textValidator(inputs.get(i)))
			return false;		
	};
	//textarea 
	var inputs=$("#"+formid+" :input[validator=input][type=textarea]");

	for (var i =0;i<inputs.length;i++){	
		if(!textValidator(inputs.get(i)))
			return false;		
	};
	
	
	
	
	//type=radio
	
	var rad=$("#"+formid+" :input[validator][type=radio]");

	var rdnames=getnamesByrc(rad);
	for(var i=0;i<rdnames.length;i++){
		if(!rcValidator(formid,rdnames[i],"radio"))
			return false;
	};
	//checkbox;
	var che=$("#"+formid+" :input[validator][type=checkbox]");	
	var chenames=getnamesByrc(che);
	for(var i=0;i<chenames.length;i++){
		if(!rcValidator(formid,chenames[i],"checkbox"))
			return false;
	};
	//select
	var sel=$("#"+formid+" select[validator]");	
	for(var i=0;i<sel.length;i++){
		if(!selectValidator(sel.get(i)))
			return false;
	}
	
	//ajax
	var ajaxinputs=$("#"+formid+" :input[validator=ajax][type=text]");
	for (var i =0;i<ajaxinputs.length;i++){	
		if(!ajaxValidator(ajaxinputs.get(i)))
			return false;		
	};
	return true;
	}catch(ex){
		alert(ex);
		return false;
	}
}

/*
 * regexValidator（正则表达式验证）
 * 
 * */

function regexValidator(formid){
	
	
	
	var inputs=$("#"+formid+" :input[validator=regex][type=text]");
	
	for (var i =0;i<inputs.length;i++){	
		if(!textValidator(inputs.get(i)))
			return false;
		if(!textregexValidator(inputs.get(i)))
			return false;
	};
	return true;
	
}


function textregexValidator(input){
	var init= new initconfig(input);
	
	if(init.regexEnum==""){	//如果直接输入正则表达式的话
		r=init.regex;
		var isvalid= (new RegExp(r)).test(input.value);
		if(!isvalid){
			input.style.borderColor="#FF0000";
			alert(init.errorshow);
		}else{
			input.style.borderColor="#CCC";
		}		
		return isvalid;
	}
	else{	
		var r = eval("regexEnum."+init.regexEnum);
		if(r==undefined || r=="") 
		  {
		        return true;
		  	}
		var isvalid= (new RegExp(r)).test(input.value);
		if(!isvalid){
			input.style.borderColor="#FF0000";			
			alert(init.errorshow2);
			input.title=init.errorshow2;
		}else{
			input.style.borderColor="#CCC";
			input.title="";
		}		
		return isvalid;
	}

}






//radio和checkbox 只取1个name做完数组
function getnamesByrc(rc){
	var rcnames=new Array();
	
	if(rc.length>0){
		rcnames.push(rc.get(0).name);
	for(var i=0; i<rc.length;i++){
		var flag=true;
		var item=rc.get(i);
		
		for (var j=0; j<rcnames.length;j++)
			{
				if(item.name==rcnames[j]){
					flag=false;
				};				
			};
		if(flag)
			rcnames.push(item.name);
	};
	}
	return rcnames;
}




//对text
function textValidator(input){
	var init= new initconfig(input);
	var len=getByteLen(input.value);
	if(len<init.min){
		input.style.borderColor="#FF0000";
		
		
		alert(init.errorshow);
		input.focus();
		input.title=init.errorshow;
		return false;
		
	}else{
		input.title="";
		input.style.borderColor="#CCC";
	}
	if(len>init.max){
		input.style.borderColor="#FF0000";
		input.focus();
		alert(init.errorshow);		
		input.title=init.errorshow;
		return false;
	}else{
		input.title="";
		input.style.borderColor="#CCC";
	};	
	return true;
}

//对radio checkbox
function rcValidator(formid,name,type){
	
	var radios=$("#"+formid+" :"+type+"[name="+name+"]");
	var flag=false;
	var init=new initconfig(radios.get(0));	
	for(var i = 0;i < radios.length;i++){			
		   if(radios.get(i).checked){
			   flag=true;
		   };
	}
	if(!flag){
		radios.focus();
		alert(init.errorshow);
		radios.get(0).title=init.errorshow;
		return false;
	}else{
		radios.get(0).title="";
		return true;
	}
}

//
function selectValidator(select){
	var init=new initconfig(select);
	if(select.value==""||select.value==" "){
		select.focus();
		select.title=init.errorshow;
		alert(init.errorshow);	
		return false;
	}else{
		select.title="";
		return true;
	}
	
	
}


//ajax 验证     返回的json数据  后台需要返回ajaxresult的json字符串。

function ajaxValidator(input){
	
	var init=new initconfig(input);	
	 $.ajax({
			type:"POST",
			url:init.ajaxaction+input.name+"="+input.value,			
			dataType:"json",
			async: false,
			success:function(data){	
					if(data.ajaxresult=="T"){
						input.title="";
						input.style.borderColor="#CCC";	
						return true;
					}		
				},
			error:function(){
				input.title=init.errorshow;
				input.style.borderColor="#FF0000";	
				alert(init.errorshow);
				return false;
			}
		});
	
}

/*
 *获取长度 
 * 
 * */

function getByteLen(str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};




//正则表达式
var regexEnum = 
{
	twonum:"^\\d+\\.\\d{2}$ ",			//精确到2位的有效数字		
	intege:"^-?[1-9]\\d*$",					//整数
	intege1:"^[1-9]\\d*$",					//正整数
	intege3 :"^((0\\d{2,3})-)?(\\d{1,11})$",
	intege4:"^[1-9]\\d{1,15}$",				//正整数精确到1-15位
	intege2:"^-[1-9]\\d*$",					//负整数
	num:"^([+-]?)\\d*\\.?\\d+$",			//数字
	num1:"^[1-9]\\d*|0$",					//正数（正整数 + 0）
	num2:"^-[1-9]\\d*|0$",					//负数（负整数 + 0）
	decmal:"^([+-]?)\\d*\\.\\d+$",			//浮点数
	
	email:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
	color:"^[a-fA-F0-9]{6}$",				//颜色
	url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",	//url
	chinese:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",					//仅中文
	ascii:"^[\\x00-\\xFF]+$",				//仅ACSII字符
	zipcode:"^\\d{6}$",						//邮编
	phone:"^((0\\d{2,3})([-]?))?(\\d{1,11})$",     //手机或者固定电话
	mobile:"^(13|15)[0-9]{9}$",				//手机
	ip4:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",	//ip地址
	notempty:"^(.*)\\S+(.*)$",						//非空
	//notempty:"/^\s*$/", //非空
	picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",	//图片
	rar:"(.*)\\.(rar|zip|7zip|tgz)$",								//压缩文件
	date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",					//日期
	qq:"^[1-9]*[1-9][0-9]*$",				//QQ号码
	tel:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",	//电话号码的函数(包括验证国内区号,国际区号,分机号)
	username:"^\\w+$",						//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
	letter:"^[A-Za-z]+$",					//字母
	letter_u:"^[A-Z]+$",					//大写字母
	letter_l:"^[a-z]+$",					//小写字母
	idcard:"^[1-9]([0-9]{14}|[0-9]{17})$"//身份证
};