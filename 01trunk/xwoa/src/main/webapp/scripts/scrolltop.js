function relocate() {
	var mydiv = document.getElementById("myDiv");
	var hh=document.getElementById("myDiv_fhdb");
	var divheight=mydiv.offsetHeight;
	var clientH=document.documentElement.clientHeight||document.body.clientHeight;
	var st=document.documentElement.scrollTop||document.body.scrollTop;
	var height = Math.abs(clientH + st
			- mydiv.offsetHeight)+"px";
	//window.status = document.body.clientHeight + " " + document.body.scrollTop;
	mydiv.style.top = height;
	if(st>0){
		hh.style.display="list-item";
		mydiv.style.height="80px";
	}else{
		hh.style.display="none";
		mydiv.style.height="40px";
	}
	/*st>0?hh.style.display="list-item":hh.style.display="none";*/

}
window.onscroll = relocate;
window.onresize = relocate;