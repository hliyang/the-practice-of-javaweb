<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
<table width="80%" cellspacing="0" cellpadding="5" border="1" style="text-align:center">
	<tr>
		<td>订单编号</td><td>订单时间</td><td>订单金额</td><td>详情</td>
	</tr>
	<c:forEach items="${orderList }" var="order">
	<tr>
		<td>${order.oid }</td><td>${order.ordertime }</td><td>${order.total }</td><td><a href="<c:url value="/OrderServlet?method=load&oid=${order.oid }"/>">详情</a></td>
	</tr>
	</c:forEach>
</table>
  </body>
</html>
