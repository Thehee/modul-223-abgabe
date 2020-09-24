package com.example.webDemo.security;

import com.example.webDemo.filter.JWTAuthenticationFilter;
import com.example.webDemo.filter.JWTAuthorizationFilter;
import com.example.webDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class DemoWebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private UserService userService;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable().authorizeRequests()
          .antMatchers(HttpMethod.POST, "/user").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginProcessingUrl("/login")
          .and()
          .addFilter(new JWTAuthenticationFilter(authenticationManager()))
          .addFilter(new JWTAuthorizationFilter(authenticationManager()))
          // this disables session creation on Spring Security
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.headers().frameOptions().disable();
   }

   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
   }

   @Bean
   public static BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(11);
   }

   @Override
   public void configure(WebSecurity web) {
      web.ignoring()
          .antMatchers("/actuator/**");
   }

   @Bean
   CorsConfigurationSource corsConfigurationSource() {
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

      String[] headers = {
          "Access-Control-Allow-Headers",
          "Access-Control-Allow-Origin",
          "Access-Control-Expose-Headers",
          "Authorization",
          "Cache-Control",
          "Content-Type",
          "Origin"
      };

      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.applyPermitDefaultValues();

      for (String header :
          headers) {
         corsConfiguration.addExposedHeader(header);
      }

      corsConfiguration.addAllowedMethod("DELETE");
      corsConfiguration.addAllowedMethod("PUT");
      corsConfiguration.addAllowedMethod("OPTIONS");
      corsConfiguration.addAllowedMethod("POST");

      source.registerCorsConfiguration("/**", corsConfiguration);

      return source;
   }
}
