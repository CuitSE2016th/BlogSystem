<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<form action="/blog/register" method="post">
    邮箱：<input type="text" name="emailOrPhone"><br>
    验证码：<input type="text" name="emailOrPhoneCode"><a name="code" href="/blog/code?emailOrPhone=1007610491@qq.com">获取邮箱验证码</a><br>
    密码：<input type="password" name="password"><br>
    <input type="submit">
</form>



</body>
<script language="JavaScript" type="text/javascript" src="static/jquery-3.2.1.min.js">

    window.onload = function () {
        alert("test");
    }

</script>
</html>
