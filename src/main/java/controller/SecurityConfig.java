package controller;

import java.security.AuthProvider;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import bll.AuthenticationService;
import bll.UserService;
import infrastructure.security.MarketsSimAuthEntryPoint;
import infrastructure.security.MarketsSimAuthProvider;
import infrastructure.security.filters.StatelessAuthenticationFilter;
import infrastructure.security.filters.StatelessLoginFilter;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserService _userService;
    private final AuthenticationService _authenticationService;
    private final MarketsSimAuthEntryPoint _authEntryPoint;
    private final MarketsSimAuthProvider _authProvider;

    public SecurityConfig() {
        super(true);
        // TODO apply dependency injection
        _userService = new UserService();
        
        // TODO proof of concept: get secret from config file
        _authenticationService = new AuthenticationService("marketsSimServerAuthSecret", _userService);
    
        _authEntryPoint = new MarketsSimAuthEntryPoint();
        
        _authProvider = new MarketsSimAuthProvider(_userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring()
         	//.anyRequest();
            .antMatchers("/whatever");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(_authEntryPoint)
            .and()
            .anonymous()
            .and()
            .authorizeRequests()
            //.anyRequest().permitAll()
            //.and().httpBasic().disable();
            // status endpoint needs authentication
            .antMatchers("/status").authenticated()
            // All other request allowed without authentication
            .anyRequest().permitAll()
            .and()

            // custom JSON based authentication by POST of 
            // {"username":"<name>","password":"<password>"} 
            // which sets the token header upon authentication
            .addFilterBefore(new StatelessLoginFilter("/user/login", _userService, _authenticationService, authenticationManagerBean()), 
            	UsernamePasswordAuthenticationFilter.class)
            	
            // Custom Token based authentication based on the header previously given to the client
            .addFilterBefore(new StatelessAuthenticationFilter(_authenticationService),
            	UsernamePasswordAuthenticationFilter.class);
    }
    
    //@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(_authProvider);
    }   

    @Bean
    @Override
    public UserService userDetailsService() {
    	//return null;
        return _userService;
    }

    @Bean
    public AuthenticationService authenticationService() {
        return _authenticationService;
    }
}
