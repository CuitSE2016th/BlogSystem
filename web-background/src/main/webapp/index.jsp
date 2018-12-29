<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--<form action="/blog/register" method="post">--%>
    <%--邮箱：<input type="text" name="emailOrPhone"><br>--%>
    <%--验证码：<input type="text" name="emailOrPhoneCode"><a name="code" href="/blog/code?emailOrPhone=1007610491@qq.com">获取邮箱验证码</a><br>--%>
    <%--密码：<input type="password" name="password"><br>--%>
    <%--<input type="submit">--%>
<%--</form>--%>



<%--</body>--%>
<%--<script language="JavaScript" type="text/javascript" src="static/jquery-3.2.1.min.js">--%>

    <%--window.onload = function () {--%>
        <%--alert("test");--%>
    <%--}--%>

<%--</script>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--pageEncoding="UTF-8"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>upload</title>
</head>
<body>
<fieldset>
    <legend>上传文件</legend>
    <form action="${pageContext.request.contextPath}/user/article/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="image"/>
        <input type="file" name="image"/>
        <input type="submit" value="上传"/>
        <input type="hidden" name="token" value="1e27c5eddd8d4fc2bbd46edab1be914b"/>
    </form>
</fieldset>
</body>
</html>
