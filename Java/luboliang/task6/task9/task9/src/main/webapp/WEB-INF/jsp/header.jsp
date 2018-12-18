<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/tags" prefix="date"%>

<!DOCTYPE html>

<html lang="en">

<body>
<!-- 头部 -->
<header>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <div class="top w">

        <div class="num">客服热线：010-594-78634
            当前在线人数:${CountUtil.getLoginCount()}

        </div>

        <c:choose>
            <c:when test="${cookie.name.value==null}">
                <a href="/login"  class="btn" >登录</a>
                <a href="/register" class="btn">注册</a>
            </c:when>
            <c:otherwise>
                <a href="/icon"> <img src =${cookie.img.value} alt="头像点击可更改" > </a>
                <a>${cookie.name.value}</a>
                <a href="/cookie" class="btn">退出</a>
            </c:otherwise>
        </c:choose>
        <div class="logos">
            <img src="/images/wx.png" alt="">
            <img src="/images/qq.png" alt="">
            <img src="/images/xl.jpg" alt="">
        </div>


    </div>

    <nav>
        <ul class=" nav1 w">
            <img src="/images/logo.png" alt="">
            <li><a href="/home">首页</a></li>
            <li><a href="/u/profession">职业</a></li>
            <li><a href="/partner">推荐</a></li>
            <li><a href="">关于</a></li>
        </ul>
        <div class="dropdown">
            <img  class="ji" src="/images/logo.png" alt="">
            <button class="btn dropdown-toggle clearfix" type="button" id="dropdownMenu1" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="true">
                    <span>
                        <img src="images/btn1.png" alt="">
                    </span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="/home">首页</a></li>
                <li><a href="task-93.jsp">职业</a></li>
                <li><a href="task-92.jsp">推荐</a></li>
                <li><a href="#">关于</a></li>
            </ul>
        </div>
    </nav>
</header>
</body>
</html>