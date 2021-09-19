package kr.or.connect.springwebsocket;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Database 관련 설정 파일
 */
@Configuration
@EnableTransactionManagement //트랙잭션과 관련된 설정을 자동으로 해줌
public class DBConfig implements TransactionManagementConfigurer {

    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://1.215.95.163:3306/developer?useUnicode=true&characterEncoding=utf8";
    private String username = "developer";
    private String password = "dev123~";

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return null; //트랜잭션을 처리할 PlatformTransactionManager를 반환
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}