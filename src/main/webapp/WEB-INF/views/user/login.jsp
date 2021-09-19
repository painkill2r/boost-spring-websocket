<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>로그인</title>
</head>
<body>
<form name="loginForm" id="loginForm" method="post" action="${pageContext.request.contextPath}/login" onsubmit="return loginFormCheck(this);">
    ID: <input type="text" name="id" id="id"/><br/>
    닉네임:<input type="text" name="name" id="name"/><br/>
    <input type="submit" value="로그인"/>
</form>
<script>
    function loginFormCheck(f) {
        var _id = f.id.value;
        var _name = f.name.value;

        if (_id === '') {
            alert('아이디를 입력해 주세요.');
            return false;
        }

        if (_name === '') {
            alert('닉네임을 입력해 주세요.');
            return false;
        }

        return true;
    }
</script>
</body>
</html>