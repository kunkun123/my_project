<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<script src="../js/jquery-1.4.2.js"></script>
	
	<script type="text/javascript">
			function showReply(a){
				var buttonId = a.attr("id");
				$("#"+buttonId).parent().parent().siblings().show("slow");
				
			}
			function showComents(){
				var commentsNum = "<%=session.getAttribute("comments")%>";
				$("#h3").attr("style","");
				for(var i=1;i<=commentsNum.length;i++){
					//alert("replyDiv_"+i)
					$("#replyP_"+i).hide();
					jQuery("#replyDiv_"+i).show("slow");
				}
			}
			function submit(){
				var content = jQuery("#content").val();
				var bookid = jQuery("#bookid").val();
				var userId = jQuery("#userid").val();
				if(userId==null||""==userId){
					alert("您还没有登录呢！");
				}
				else if(content == null ||" "==content){
					alert("评论内容不能为空！");
				}else{
					$.ajax({
					url:'../servlet/CommentServlet',
					data:{content:content,bookid:bookid,userid:userId},
					success:function(){//服务器端如果成功的响应，则执行该函数
					alert("success");
					},
					error:function(){//如果服务器端出现异常，则执行该函数
						alert("error");
					},
					type:'POST'
					});
				}
			}
			function reply(a){
				var PId = a.attr("id");
				var content = jQuery("#"+PId).children(".text").val();
				var userId = jQuery("#userid").val();
				var commentId = PId.split("_")[1];
				if(userId==null||""==userId){
					alert("您还没有登录呢！");
				}else if(content == null ||""==content){
					alert("回复内容不能为空！");
				}else{
				//alert(111);
					$.ajax({
						url:'../servlet/ReplyServlet',
						data:{content:content,commentId:commentId,userId:userId},
						success:function(){//服务器端如果成功的响应，则执行该函数
						alert("success");
						},
						error:function(){//如果服务器端出现异常，则执行该函数
							alert("error");
						},
						type:'POST'
						});
					}
			}
			jQuery("document").ready(
				function(){
					//$("#replyContent").hide();
					jQuery("#category").mouseenter(function(){
					jQuery("#categoryDesc").show("slow")});
					
					jQuery("#category").mouseleave(function(){
					jQuery("#categoryDesc").hide()});
				}
			);
	</script>
	<style type="text/css">
	body {
		padding:0;
		margin:0;
	}
	#nav {
		width:100%;
		height:60px;
		background:#39f;
		color:#fff;
		line-height:60px;
		text-align:center;
		padding:0;
		margin:0;
		list-style:none;
	}
	#nav li {
		float:left;
		width:20%;
		height:60px;
	}
	.fix {
		position:fixed;
		top:0;
		left:0;
	}
	.div1{
		float:left;
		width:630px;
		text-align:right;
	}
	.div2{
		float:right;
		width:630px;
		color:#F00;
		font-size:18px;
		text-align:left;
		height:250px;
	}
	.itm{
		border-top: 1px dotted #ccc;
	    padding: 15px 0;
	    margin-left: 60px;
	    text-align: left; 
    }
    .u-hd4{
    	margin: 0;
    	padding: 0;
    	text-align: left; 
    }
    .que{
    	left:50px;
    	right:300px;
    	white-space: normal;
    	position: relative;
    	text-align: left;
    	background: #f4f4f4 none repeat scroll 0 0;
    	border: 1px solid #dedede;
    	line-height: 20px;
    	margin-top: 10px;
    	padding: 8px 19px;
    	-moz-text-size-adjust: none;
    	color: #333;
    	font-family: Arial,Helvetica,sans-serif;
    	font-size: 12px;
    }
    i{
    	font-size: inherit;
    	font-style: normal;
    	text-align: left;
    	left: 20px;
    	position: absolute;
    	color: #f4f4f4;
    	top: -10px;
    }
</style>
    <br/>
    <ul id="nav">
		<li> <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyBook&bookId=${book.id}">放入购物车</a></li>
		<li><a href="javascript:history.back()">继续购物</a></br></li>
		<li><a href ="javascript:showComents()">显示评论</a></li>
	</ul>
	<div class="div1" >
    <img height="250" width="170" src="${pageContext.request.contextPath}/images${book.path}/${book.photoFileName}"/><br/>
    </div>
    <div class="div2" >
    	书名：${book.name}<br/>
    	作者：${book.author}<br/>
    	价格：${book.price}<br/>
    	描述：${book.description}</br>
    	<p id="category">分类：${category.name}</p></br>
    	<span id="categoryDesc" style="display: none" >${category.description}</span>
    </div>
   
    </br></br></br><input id="content" type ="text" value="留下自己的感想吧。。。"/>
	<img src="../images/text-message.png" height="20" width="20" id="content" border="0" onClick="javascript:submit()">
    <h3 class="u-hd4" id="h3" style="visibility:hidden">精彩评论</h3>
    <c:forEach items="${comments}" var="c">
    <td>
    	</br>
    	<div style="display: none" id="replyDiv_${c.key}">
    	<div class="itm">
			<div class="">
				<div class="cnt f-brk">
					<a class="s-fc7">${c.value.username}</a>
						：${c.value.commentContent}
				</div>
				<img src="../images/comments_reply.png" height="20" width="20"id="replyButton_${c.key}" name="reply_button" border="0" onClick="javascript:showReply($(this))">	
			</div>
    	</div>
    	<c:forEach items="${replys}" var="r">
    	<c:if test="${r.value.id==c.key}">
    	<div class="que" id="reply_${r.key}">
			<span class="darr">
				<i class="bd">◆</i>
				<i class="bg">◆</i>
			</span>
			<a class="s-fc7">${ r.value.username}</a>
				：${ r.value.content}
		</div>
		</c:if>
		</c:forEach>
    	<p id="replyP_${c.key}">
    		</br id="1"><input type ="text" class="text" id="replyContent_${c.key}" value="想对TA说些什么呢？"/>
    		<input type="button" onclick="javascript:reply($(this).parent())" value="确定"/>
    	<p/>
    	</div></br>
    </td>
    </c:forEach>
    <input id="bookid" type="hidden" value="${book.id}" />
    <input id="userid" type="hidden" value="${sessionScope.customer.id}" />
  <a href="http://localhost:8080/WebReport/ReportServer?reportlet=WorkBook3.cpt">aa</a>
  </body>
  <script type="text/javascript">
var tit = document.getElementById("nav");

//占位符的位置
var rect = tit.getBoundingClientRect();//获得页面中导航条相对于浏览器视窗的位置
var inser = document.createElement("div");
tit.parentNode.replaceChild(inser,tit);
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
</script>
</html>
