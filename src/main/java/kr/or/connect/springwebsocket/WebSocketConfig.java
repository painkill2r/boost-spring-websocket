package kr.or.connect.springwebsocket;

import kr.or.connect.springwebsocket.utils.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/chatting") //특정 URL에 웹소켓 핸들러를 매핑
                .setAllowedOrigins("*") //WebSocket 및 Sock JS 허용 오리진을 구성
                .addInterceptors(new HttpSessionHandshakeInterceptor()) //핸드셰이크 요청을 인터셉트할 인터셉터(HttpSession에 저장된 값을 사용하기 위해 설정)
                .withSockJS(); //SockJS를 사용하게 설정
    }

    @Bean
    public MyWebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        //WebSocket의 런타임 특성 제어
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);

        return container;
    }
}
