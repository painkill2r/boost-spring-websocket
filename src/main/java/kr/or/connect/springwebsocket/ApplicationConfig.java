package kr.or.connect.springwebsocket;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring 공통 설정 파일
 */
@Configuration
@ComponentScan(basePackages = {"kr.or.connect.springwebsocket.service", "kr.or.connect.springwebsocket.dao"})
@Import({WebSocketConfig.class, WebSocketMessageBrokerConfig.class})
public class ApplicationConfig {
}