package com.vincent.todolist;

import com.vincent.todolist.interceptor.MessageResourceInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
@Configuration
//@EnableWebSecurity
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
//        slr.setDefaultLocale(Locale.TRADITIONAL_CHINESE);
//        slr.setCookieMaxAge(3600);
//        slr.setCookieName("Language");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 參數名
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
                registry.addInterceptor(new MessageResourceInterceptor()).addPathPatterns("/**");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //映射static路径的请求到static目录下
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/");
            }
        };
    }

    //@Bean
//    public WebSecurityConfigurerAdapter WebSecurityConfigurer() {
//        return new WebSecurityConfigurerAdapter() {
//            //            @Override
////            protected void configure(HttpSecurity http) throws Exception {
////                http
////                        .authorizeRequests() // 定義哪些url需要被保護
////                        .antMatchers("/").permitAll()  // 定義匹配到"/" 不需要驗證
////                        .antMatchers("/swagger-ui.html").permitAll() // 匹配到"/swagger-ui.html", 不需要身份驗證
////                        .anyRequest().authenticated() // 其他尚未匹配到的url都需要身份驗證
////                        .and()
////                        .formLogin()
////                        .and()
////                        .httpBasic();
////            }
//            @Override
//            protected void configure(AuthenticationManagerBuilder auth)
//                    throws Exception {
//                // 設置角色定義
//                auth.inMemoryAuthentication()
//                        .withUser("admin")
//                        .password("123456")
//                        .roles("ADMIN", "USER") // 擁有ADMIN 與 USER角色
//                        .and()
//                        .withUser("user")
//                        .password("123")
//                        .roles("USER");// 擁有USER角色
//            }
//
//            @Override
//            protected void configure(HttpSecurity http) throws Exception {
//                http
//                        .authorizeRequests() // 定義哪些url需要被保護
//                        .antMatchers("/api/todos").hasRole("USER")// 定義匹配到"/user"底下的需要有USER的這個角色才能進去
//                        .antMatchers("/admin").hasRole("ADMIN") // 定義匹配到"/admin"底下的需要有ADMIN的這個角色才能進去
//                        .anyRequest().authenticated() // 其他尚未匹配到的url都需要身份驗證
//                        .and()
//                        .formLogin()
//                        .and()
//                        .httpBasic();
//            }
//        };
//    }
}
