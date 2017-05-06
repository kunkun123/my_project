<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>
<form action="${pageContxt.request.contextPath}/servlet/ControlServlet?op="addCatagory" method="post">
   <table border="1" width="438">
      <tr>
        <td>分类名称：</td>
        <td><input type="name" name="name"/></td>
      </tr> 
      <tr>
        <td>分类描述：</td>
        <td><testarea row="3" cols="38 name="description"></testarea></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" name="保存"/></td>
      </tr> 
   </table>
</form>
</body>
</html>
