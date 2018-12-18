<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/24
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=0">
    <meta name="format-detection" content="telephone=no">
    <title>头像上传</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/base.css">

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form method="post" action="/img" enctype="multipart/form-data">
    <input type="file" name="multipartFile" >
    <img src="file" height="200" alt="Image preview area..." title="preview-img">
    <input type="submit" value="确定">
</form>

<script>
    var fileInput = document.querySelector('input[type=file]'),
        previewImg = document.querySelector('img');
    fileInput.addEventListener('change', function () {
        var file = this.files[0];
        var reader = new FileReader();
        // 监听reader对象的的onload事件，当图片加载完成时，把base64编码賦值给预览图片
        reader.addEventListener("load", function () {
            previewImg.src = reader.result;
        }, false);
        // 调用reader.readAsDataURL()方法，把图片转成base64
        reader.readAsDataURL(file);
    }, false);
</script>
</body>
</html>
