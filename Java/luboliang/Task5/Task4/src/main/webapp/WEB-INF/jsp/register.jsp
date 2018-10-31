<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/23
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=0">
        <meta name="format-detection" content="telephone=no">
        <title>注册</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/css/base.css">

        <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
</head>
<body>
<form method="post" action="/register" >
    <table border="1" width="600">
        <tr>
            <td>*用户名：</td>
            <td><input type="text" name="user"/> </td>
        </tr>
        <tr>
            <td>*密码:</td>
            <td><input type="password" name="password"/> </td>
        </tr>
        <tr>
            <td><input type="submit"  value="提交注册信息"/></td>
            <td></td>
        </tr>
    </table>
</form>

</body>
</html>
