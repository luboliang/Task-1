<%@ taglib prefix="width" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <title>登陆</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/base.css">

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<%--placeholder="密 码" required="required"--%>
<body>
<form  method="post" action="/login" >
    <table border="" width="400"height="100">
        <tr>
            <td>*用户名：</td>
            <td><input type="text" name="user"/> </td>
        </tr>
        <tr>
            <td>*密码:</td>
            <td><input type="password" name="password"/> </td>
        </tr>
        <tr>
            <td><input type="submit"  value="登陆"/></td>
            <td></td>
        </tr>
    </table>
</form>

<form  method="post" action="/login" >
    <table border="" width="400"height="100">
        <tr>
            <td>*密码:</td>
            <td><input type="password" name="password"/> </td>
        </tr>
        <tr>
            <td>*手机号:</td>
            <td><input type="text" name="mobile"/> </td>
        </tr>
        <tr>
            <td><input type="submit"  value="登陆"/></td>
            <td></td>
        </tr>
    </table>
</form>
<form  method="post" action="/login" >
    <table border="" width="400"height="100">
        <tr>
            <td>*邮箱：</td>
            <td><input type="text" name="user"/> </td>
        </tr>
        <tr>
            <td>*密码:</td>
            <td><input type="password" name="password"/> </td>
        </tr>
        <tr>
            <td><input type="submit"  value="登陆"/></td>
            <td></td>
        </tr>
    </table>
</form>

</body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</html>


