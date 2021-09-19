package kr.or.connect.springwebsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * DispatcherServlet이 읽어 들이는 설정 파일
 */
@Configuration
@EnableWebMvc //Web에 필요한 설정을 자동으로 해줌.
//@ComponentScan(basePackages = {"kr.or.connect.springwebsocket."}) //레이어드 아키텍처가 아닌 학습을 할 경우 주석 해제
@ComponentScan(basePackages = {"kr.or.connect.springwebsocket.controller"}) //레이어드 아키텍처를 학습할 경우 주석 해제
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 특정 URL 요청에 대해서는 별도로 처리할 수 있게 설정
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }

    /**
     * 매핑 정보가 없는 URL에 대해서는 DefaultServletHandlerConfigurer가 처리하도록 설정
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 특정 URL에 대한 처리를 Controller를 거치지 않고 매핑하여 처리하도록 설정함.
     * 여기서는 "/"에 대해서는 "main"이라는 View를 보여지도록 설정하였음.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        System.out.println("addViewControllers() 호출...");
        registry.addViewController("/").setViewName("index");
    }

    /**
     * ViewResolver 설정
     * View 파일 경로 설정
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
}