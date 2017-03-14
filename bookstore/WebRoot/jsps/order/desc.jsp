<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/desc.css'/>">
  </head>
  
<body>
<table border="1" width="80%">
	<tr>
		<td>商品图片</td>
		<td>商品名称</td>
		<td>商品单价</td>
		<td>商品数量</td>
		<td>商品总价</td>
	</tr>
	<c:forEach items="${orderItemList }" var="orderItem">
		<tr>
			<td>
				<img src="<c:url value='${orderItem.book.image }' />"/>
			</td>
			<td>${orderItem.book.bname }</td>
			<td>${orderItem.book.price }</td>
			<td>${orderItem.count }</td>
			<td>${orderItem.subtotal }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>

