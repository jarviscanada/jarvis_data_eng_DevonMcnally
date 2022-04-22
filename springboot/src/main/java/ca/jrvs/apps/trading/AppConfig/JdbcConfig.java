package ca.jrvs.apps.trading.AppConfig;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {


    private String jdbcUrl;
    private String user;
    private String password;

    @Bean
    @Primary
    public DataSource dataSource(){
        //jdbc:postgresql://localhost:5432/postgres
        jdbcUrl =
                "jdbc:postgresql://" +
                        System.getenv("PSQL_HOST") + ":" +
                        System.getenv("PSQL_PORT") +
                        "/" +
                        System.getenv("PSQL_DB");



        user = System.getenv("PSQL_USER");
        password = System.getenv("PSQL_PASSWORD");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;

    }
}
