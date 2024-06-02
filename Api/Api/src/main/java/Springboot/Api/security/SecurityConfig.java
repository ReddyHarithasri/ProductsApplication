package Springboot.Api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpHeaders;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    HttpSecurity httpSecurity;


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //authentication
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder)
    {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin@123"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("customer")
                .password(encoder.encode("customer@123"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);

    }
    //authorization
    @Bean
    public SecurityFilterChain securityFilterChain() throws Exception {

        httpSecurity.csrf().disable()
//                .addFilterBefore(customHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize->authorize
                        //.requestMatchers(HttpMethod.)
                        .requestMatchers("/products","/products{pid}","/product/{category}").permitAll()
                        .requestMatchers("/products","/products/{pid}","/products/{pid}").hasRole("ADMIN")
                        .anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
//    @Bean
//    public OncePerRequestFilter customHeaderFilter() {
//        return new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//                String organizationHeader = request.getHeader("organization");
//
//                if (organizationHeader == null || !organizationHeader.equals("ltts")) {
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.setContentType("application/json");
//                    PrintWriter writer = response.getWriter();
//                    writer.println("{\"error\": \"Header 'organisation' with value 'ltts' is required\"}");
//                    writer.flush();
//                    return;
//                }
//                response.addHeader("organization","ltts");
//                filterChain.doFilter(request, response);
//            }
//        };
    }

