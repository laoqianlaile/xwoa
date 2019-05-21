$(document).ready(function(){
			$("button.ui-datepicker-trigger").click(
				function(){
					var obj=$("#ui-datepicker-div");
					var btnTop=$(this).offset().top;
					var top=btnTop-obj.height()-5;
					if((btnTop+obj.height())>550)
					{
					obj.css("top",top);
					}	
			});
			$(".hasDatepicker").click(
				function(){
					var obj=$("#ui-datepicker-div");
					var btnTop=$(this).offset().top;
					var top=btnTop-obj.height()-5;
					if((btnTop+obj.height())>550)
					{
					obj.css("top",top);
					}	
			});
		});