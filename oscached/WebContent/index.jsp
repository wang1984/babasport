<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/oscache" prefix="oscache"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>只要url连接没有变就会用缓存，一旦url有变化就会重新请求</h1>
<h1>一个用户请求过一个地址，当这个地址被把别的用户请求时就会走页面缓存</h1>
现在时间<%=new Date() %>   <br/>
<oscache:cache scope="session" key="itcast" time="3">
缓存时间<%=new Date() %>
</oscache:cache>
</body>
</html>
