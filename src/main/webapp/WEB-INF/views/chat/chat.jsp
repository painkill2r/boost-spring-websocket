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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
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
        var json = {
            id: ${sessionScope.loginUser.id},
            name: ${sessionScope.loginUser.name},
            message: message
        }

        send(JSON.stringify(json));

        _message.value = '';
    });

    function connect() {
        console.log('INFO: Web socket(Use SockJS) Connection...');

        var sock = new SockJS('/stomp'); //EndPoint
        var client = Stomp.over(sock); //SockJS 위에서 동작

        socket = client;

        client.connect({}, function () {
            console.log('STOMP protocol connection');

            //토픽 구독
            client.subscribe('/topic/message', function (message) {
                console.log('Received: ' + message);

                var receivedData = JSON.parse(message);
                var chatList = document.getElementById('chatList');
                var p = document.createElement('p');

                p.append(receivedData.id + ": " + receivedData.message)

                chatList.appendChild(p);
            });
        }, function (error) {
            console.log('STOMP protocol error: ' + error);
        });
    }

    function send(data) {
        //Controller @MessageMapping, header, message(자유형식)
        socket.send('/send', {}, data);
    }
</script>
</body>
</html>