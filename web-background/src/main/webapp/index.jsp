<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>

<form action="/userRegist" method="post">
    邮箱：<input type="text" name="emailOrPhone"><br>
    验证码：<input type="text" name="emailOrPhoneCode"><a href="/code?emailOrPhone=986204478@qq.com">获取邮箱验证码</a><br>
    密码：<input type="password" name="password"><br>
    <input type="submit">
</form>

</body>
</html>
