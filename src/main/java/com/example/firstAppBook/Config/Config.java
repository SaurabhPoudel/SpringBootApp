package com.example.firstAppBook.Config;

import com.example.firstAppBook.Repository.BookRepository;
import com.example.firstAppBook.Service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
     {
         http
                 .authorizeHttpRequests(authorize -> authorize
                 .requestMatchers("/api/books/**").authenticated()
                 .anyRequest().permitAll()
                 ).httpBasic()
                 .and()
                 .csrf().disable()
                 .cors();
         return http.build();

     }
     @Bean
    public MongoTemplate mongoTemplate() {
         return MongoDBDataSource.getInstance().getMongoTemplate();
     }
    @Bean
    public BookService bookService(BookRepository bookRepository) {
         return book -> bookRepository.save(book);
    }
}
