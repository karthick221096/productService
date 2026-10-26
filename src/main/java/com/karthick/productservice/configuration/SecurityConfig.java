  package com.karthick.productservice.configuration;

  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
  import org.springframework.data.redis.core.RedisTemplate;
  import org.springframework.http.HttpMethod;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
  import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
  import org.springframework.security.web.SecurityFilterChain;

  @Configuration
  @EnableWebSecurity
  public class SecurityConfig {
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .authorizeHttpRequests(authorize -> authorize
                          .requestMatchers(HttpMethod.GET, "/product/**", "/products/**").permitAll()
                          .anyRequest().authenticated()
                  )
                  .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
          return http.build();
      }

      @Bean
      JedisConnectionFactory jedisConnectionFactory() {
          return new JedisConnectionFactory();
      }

      @Bean
      public RedisTemplate<String, Object> redisTemplate() {
          RedisTemplate<String, Object> template = new RedisTemplate<>();
          template.setConnectionFactory(jedisConnectionFactory());
          return template;
      }


  }