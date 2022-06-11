package io.bootify.l16_visitor_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
        http.authorizeRequests().antMatchers("/api/user-panel/**").hasAnyAuthority("RESIDENT")
                .antMatchers("/api/admin-panel/**").hasAuthority("ADMIN")
                .antMatchers("/api/gatekeeper-panel/**").hasAuthority("GATEKEEPER");
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();




    }


}
