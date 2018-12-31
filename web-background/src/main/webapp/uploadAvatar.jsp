<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传实例 </title>
</head>
<body>
<h1>文件上传实例 </h1>
<form method="post" action="/blog/user/uploadPortrait" enctype="multipart/form-data">

    选择一个文件:
    <input type="file" name="pic" accept="image/*" /><br/>
    手机号：<input type="text" name="identity"><br>
    token：<input type="text" name="token"><br>
    <input type="submit" value="上传"/>
</form>
</body>
</html>