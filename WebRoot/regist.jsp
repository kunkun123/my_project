<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<script src="/netStore/js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
	jQuery("document").ready(function(){
		//alert(111);
		jQuery("input").blur(function(){
				var username = jQuery("#username").val();
				//alert(username);
				$.ajax({
				url:'/netStore/servlet/CustomerServlet',
				data:{username:username},
				success:function(data){//服务器端如果成功的响应，则执行该函数
				//alert(data);
				jQuery("#usernameIsExit").html(data);
				},
				error:function(){//如果服务器端出现异常，则执行该函数
					alert("error");
				},
				type:'POST'
				});
			});
	});
			
			
	</script>
    <br/>
    <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=registCustomer" method="post">
    	<table border="1" width="438">
    		<tr>
    			<td>用户名：</td>
    			<td>
    				<input id="username" type="text" name="username" />
    				<div id="usernameIsExit" style="color:#ff0000"></div>
    			</td>
    		</tr>
    		<tr>
    			<td>密码：</td>
    			<td>
    				<input id="password" type="password" name="password"/>
    			</td>
    		</tr>
    		<tr>
    			<td>电话：</td>
    			<td>
    				<input type="text" name="phone"/>
    			</td>
    		</tr>
    		<tr>
    			<td>地址：</td>
    			<td>
    				<input type="text" name="address"/>
    			</td>
    		</tr>
    		<tr>
    			<td>邮箱：</td>
    			<td>
    				<input type="text" name="email"/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="注册"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
 