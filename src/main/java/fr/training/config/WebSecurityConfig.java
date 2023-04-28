package fr.training.config;

import fr.training.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // turn off csrf (Cross-Site Request Forgery)
        http.csrf()
                .disable();
        // all requests must be authenticated
        http.authorizeRequests().anyRequest().authenticated();
        // use authenticationEntryPoint to check username/ password
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
//            auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
