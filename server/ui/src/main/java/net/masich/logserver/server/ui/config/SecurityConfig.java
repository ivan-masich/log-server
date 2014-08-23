package net.masich.logserver.server.ui.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.masich.logserver.server.ui.web.security.model.AccessDeniedResponse;
import net.masich.logserver.server.ui.web.security.model.SignInResponse;
import net.masich.logserver.server.ui.web.security.model.SignOutResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@DependsOn("mvcConfig")
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //TODO Find solution to use csrf with ajax.
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/", "/sign-in", "/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/sign-in")
                .usernameParameter("email")
                .successHandler((request, response, authentication) -> {
                    SignInResponse responseObject = new SignInResponse(true);
                    mapper.writer().writeValue(response.getWriter(), responseObject);
                })
                .failureHandler((request, response, exception) -> {
                    //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    SignInResponse responseObject = new SignInResponse(false, exception.getMessage());
                    mapper.writer().writeValue(response.getWriter(), responseObject);
                })
                .and()
            .logout()
                .logoutUrl("/sign-out")
                .addLogoutHandler((request, response, authentication) -> {
                    try {
                        SignOutResponse signOutResponse = new SignOutResponse(true);
                        mapper.writer().writeValue(response.getWriter(), signOutResponse);
                    } catch (IOException e) {
                        LOG.error("Can't create and write sign out response.");
                    }
                })
                .and()
            .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    AccessDeniedResponse responseObject = new AccessDeniedResponse(accessDeniedException.getMessage());
                    mapper.writer().writeValue(response.getWriter(), responseObject);
                })
                .and()
            .httpBasic();
    }

}
