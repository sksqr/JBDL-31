package com.example.L15springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("shashi").password("$2a$10$kWh94q3Mdspgq.uIa32YEurezQdtVS0f5a.1kH0aW6sIeaWU/DSBu").authorities("user")
//                .and()
//                .withUser("ravi").password("$2a$10$A3W3mWPP2fCKXVvRmUpV3uuw3FCwJkkZOxg2iOHn8NpEwhjUB23qa").authorities("admin");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));
        //$2a$10$A3W3mWPP2fCKXVvRmUpV3uuw3FCwJkkZOxg2iOHn8NpEwhjUB23qa
        //$2a$10$kWh94q3Mdspgq.uIa32YEurezQdtVS0f5a.1kH0aW6sIeaWU/DSBu
    }



//    protected void configure(HttpSecurity http) throws Exception {
////        this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
//        http.authorizeRequests((requests) -> {
//            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
//        });
//        http.formLogin();
//        http.httpBasic();
//    }


    protected void configure(HttpSecurity http) throws Exception {
//        this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
        http.authorizeRequests().antMatchers("/api/user/hello","/admin/changePassword").hasAnyAuthority("user","admin")
                .antMatchers("/api/admin/hello").hasAnyAuthority("admin");
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
    }



}
