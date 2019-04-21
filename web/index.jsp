<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--查询到cookie里保存有登录用户信息，直接调用UserServlet里的login方法登录此用户--%>
<C:if test="${not empty  cookie.username.value}">
  <C:redirect url="/UserServlet?method=login"></C:redirect>
</C:if>


<html>
  <head>
    <title>用户登录</title>
    <style>
      body{
        text-align: center;
      }
      #logindiv{
        position: absolute;
        width:300px;
        height: 250px;
        top:40%;
        left:40%;
        background-color: aquamarine;
        text-align: center;

      }
    </style>
  </head>
  <body>

  <h1>通讯录管理系统</h1>
  <div id="logindiv">
    <h2>用户登录</h2><br/>
    <% request.setCharacterEncoding("utf-8"); %>
    <b style="color: red;">${requestScope.errorMessage}</b>
  <form action="UserServlet?method=login" method="post">
    账户名：<input id="username" type="text" name="username" value="${cookie.username.value}"><br/>
    密&nbsp&nbsp&nbsp码：<input id="password" type="password" name="password" value="${cookie.password.value}"><br/>


    <input type="checkbox" name="rememberMe" >一周内免密登录<br><br>


    <input type="submit" value="登录">&nbsp&nbsp&nbsp&nbsp<input type="button" value="注册">

  </form>
  </div>

  </body>
</html>
