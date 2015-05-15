package config;

/**
 * Created by Arkkienkeli on 17.12.2014.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import java.util.Properties;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"controller", "dao"})
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds =
                    new SimpleDriverDataSource(org.h2.Driver.load(), "jdbc:h2:~/testdb", "sa", "sa");
        return ds;
    }
    @Bean
    @DependsOn("dataSource")
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }
    private Properties hibernateProperties() {
        Properties p = new Properties();
        p.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        p.setProperty("hibernate.hbm2ddl.auto", "update");
        p.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        p.setProperty("hibernate.show_sql", "true");
        p.setProperty("hibernate.format_sql", "true");
        return p;
    }
    @Bean
    @DependsOn("sessionFactoryBean")
    public PlatformTransactionManager transactionManager() {
        SessionFactory sessionFactory = sessionFactoryBean().getObject();
        HibernateTransactionManager tm = new HibernateTransactionManager(sessionFactory);
        return tm;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }
}