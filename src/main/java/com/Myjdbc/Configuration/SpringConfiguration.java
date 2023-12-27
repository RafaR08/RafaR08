package com.Myjdbc.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "jdbcPerson")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource firstDataSource() { return DataSourceBuilder.create().build();}


    @Bean(name = "PersonaIne")
    @Autowired
    public JdbcTemplate jdbcTemplateOne(@Qualifier("jdbcPerson") DataSource jdbcPerson) {
        return new JdbcTemplate(jdbcPerson);
    }

}
