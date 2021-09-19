<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String url = request.getServerName();
    String port = String.valueOf(request.getServerPort());

    if (port != null) {
        url += ":" + port;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>채팅</title>
</head>
<body>
${sessionScope.loginUser.name}님 채팅방 접속을 환영합니다.
<button type="button" id="logoutBtn" onclick="location.href = '${pageContext.request.contextPath}/logout';">로그아웃
</button>
<hr/>
<form method="post" action="${pageContext.request.contextPath}/chat">
    메시지: <input type="text" name="message" id="message" placeholder="메시지 입력"/><br/>
    <button type="button" id="sendBtn">전송</button>
</form>
<div class="chat">
    <div class="chat_list" id="chatList"></div>
</div>
<script type="text/javascript">
    document.addEventListener('keydown', function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            document.getElementById('sendBtn').click();
        }
    }, true);

    var socket = null;

    connect();

    document.getElementById('sendBtn').addEventListener('click', function (e) {
        e.preventDefault();

        var _message = document.getElementById('message');
        var message = _message.value;

        if (socket.readyState !== 1)
            return;

        socket.send(message);

        _message.value = '';
    });

    function connect() {
        console.log('INFO: Web socket Connection...');

        var ws = new WebSocket('ws://<%= url %>/chatting'); //브라우저에서 지원하는 WebSocket(단, IE는 10이상 부터)

        socket = ws;

        ws.onopen = function () {
            console.log('INFO: Connection opened.');

            ws.onmessage = function (evt) {
                console.log('Received message: ' + evt.data);

                var chatList = document.getElementById('chatList');
                var p = document.createElement('p');

                p.append(evt.data)

                chatList.appendChild(p);
            };
        }

        ws.onclose = function (evt) {
            console.log('INFO: Connection closed.');

            //retry
            setTimeout(function () {
                connect();
            }, 1000);
        };

        ws.onerror = function (err) {
            console.log(err);
        };
    }
</script>
</body>
</html>