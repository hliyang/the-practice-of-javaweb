<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">

</head>

<body>
	<div
		style="width: 30%;margin: 10% auto;background-color: #FFF;padding: 20px 0 30px 40px">
		<form action="<c:url value='/UserServlet'/>" method="post">
		<input type="hidden" name="method" value="login" />
			<table align="center" cellspacing="0" cellpadding="5"
				style="margin: 20px">
				<tr>
					<td colspan="2" align="center"><p
							style="color: red; font-weight: 900">${error }</p></td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input style="width:99%" type="text" name="username"
						value="${form.username }" /></td>
				</tr>
				<tr>
					<td align="right">密　码：</td>
					<td><input style="width:99%" type="password" name="password"
						value="${form.password }"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input style="width:50%" type="submit" value="登录" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
