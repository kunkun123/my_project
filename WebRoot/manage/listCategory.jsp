<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/manage/header.jsp" %>
<html>
  <body>
  </br></br>
  <table border="1" width="438"></table>
    <tr>
      <th>选择</th>
      <th>分类名称</th>
      <th>分类描述</th>
      <th>操作</th>
    </tr>
    <c:forEach items="${cs}" var="c" varStatus="vs">
     <tr class="${vs.index%2==0?'odd':'even'}">
      <td><input type="checkbox" name="ids" value="${c.id}"/></td>
      <td>${c.name}</td>
      <td>${c.description}</td>
      <td>
        <a herf="javascript:alert('略')">修改</a>
        <a herf="javascript:alert('略')">删除</a>
      </td>
    </tr>
    </c:forEach>
    </table>
  </body>
</html>
