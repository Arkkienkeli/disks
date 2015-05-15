package config;

/**
 * Created by Arkkienkeli on 05.02.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

@Configuration
@EnableWebSecurity
@ComponentScan({"controller", "dao", "config"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    //@Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, 'true' as enabled from user where username = ?")
                .authoritiesByUsernameQuery("select username, authority from user where username = ?");

//                .jdbcAuthentication()
//                .usersByUsernameQuery(getUserQuery());
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");


    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//               .disable();
////                 .authorizeRequests()
//////                .antMatchers("/css/**", "/fonts/**", "/image/**", "/js/**").permitAll()
////                //.anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/index").permitAll()
////                .loginProcessingUrl("/index").permitAll();
//////                .usernameParameter("login")
//////                .passwordParameter("pass");
//    }

//    private String getUserQuery() {
//        return "SELECT username as username, password as password "
//                + "FROM User "
//                + "WHERE username = ?";
//    }
}