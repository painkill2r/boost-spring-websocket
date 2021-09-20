package kr.or.connect.springwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp") //EndPoint 설정
                .setAllowedOrigins("*") //WebSocket 및 Sock JS 허용 오리진을 구성
                .withSockJS(); //SockJS를 사용하게 설정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/"); //Send URL에 대한 전역 prefix 설정
        registry.enableSimpleBroker("/topic"); //Subscribe에 대한 전역 prefix를 설정
    }
}
