package ua.student.coursetest.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html", "/js/**", "/css/**", "/logout")
                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                    .oauth2Login()
                    .loginPage("/login.html")
                    .successHandler(authenticationSuccessHandler)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
    }
}