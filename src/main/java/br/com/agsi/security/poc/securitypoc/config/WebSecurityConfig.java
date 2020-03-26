package br.com.agsi.security.poc.securitypoc.config;


import br.com.agsi.security.poc.securitypoc.filter.AgsiAuthenticationFilter;
import br.com.agsi.security.poc.securitypoc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    public WebSecurityConfig() {
        super();
        // https://docs.spring.io/spring-security/site/docs/3.1.x/reference/technical-overview.html
        //    6.2.1  SecurityContextHolder, SecurityContext and Authentication Objects
        // Caso a aplicação necessite que as demais threads geradas a partir da tread segura também assuma a mesma identidade de segurança
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers()
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new AgsiAuthenticationFilter(authenticationService),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("222.100.888-44")
                        .password("teste")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
