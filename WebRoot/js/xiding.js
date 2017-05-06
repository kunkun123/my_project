var tit = document.getElementById("nav");
alert(1111);
//占位符的位置
var rect = tit.getBoundingClientRect();//获得页面中导航条相对于浏览器视窗的位置
var inser = document.createElement("div");
inser.parentNode.replaceChild(inser,tit);
inser.appendChild(tit);
inser.style.height = rect.height + "px";
//获取距离页面顶端的距离
var titleTop = tit.offsetTop;
//滚动事件
document.onscroll = function(){
	//获取当前滚动的距离
	var btop = document.body.scrollTop||document.documentElement.scrollTop;
	//如果滚动距离大于导航条据顶部的距离
	if(btop>titleTop){
		//为导航条设置fix
		tit.className = "clearfix fix";
	}else{
		//移除fixed
		tit.className = "clearfix";
	}
}



