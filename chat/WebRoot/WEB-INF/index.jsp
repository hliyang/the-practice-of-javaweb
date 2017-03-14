<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
           您好：${sessionScope.username }<br/><br/>
    <textarea rows="20" cols="40">
    	<c:forEach items="${chatroom }" var="c">
    		${c }<br/>
    	</c:forEach>
    </textarea><br/><br/>
	<form action="<c:url value="/talk" />" method="post">
		<input type="text" name="content"/><br/><br/>
		<input type="submit" value="发送消息" />
	</form>
  </body>
</html>
