package ma.octo.aop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "ma.octo",
    excludeFilters = @Filter(type = FilterType.REGEX, pattern = "ma.octo.aop.entity.*"))
@PropertySource("classpath:application.properties")
@Import({DevConfigProperties.class, ProdConfigProperties.class})
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate applicationDataConnection(DataSource dataSource)  {
        return new JdbcTemplate(dataSource);
    }


}
