var tit = document.getElementById("nav");
alert(1111);
//ռλ����λ��
var rect = tit.getBoundingClientRect();//���ҳ���е����������������Ӵ���λ��
var inser = document.createElement("div");
inser.parentNode.replaceChild(inser,tit);
inser.appendChild(tit);
inser.style.height = rect.height + "px";
//��ȡ����ҳ�涥�˵ľ���
var titleTop = tit.offsetTop;
//�����¼�
document.onscroll = function(){
	//��ȡ��ǰ�����ľ���
	var btop = document.body.scrollTop||document.documentElement.scrollTop;
	//�������������ڵ������ݶ����ľ���
	if(btop>titleTop){
		//Ϊ����������fix
		tit.className = "clearfix fix";
	}else{
		//�Ƴ�fixed
		tit.className = "clearfix";
	}
}



