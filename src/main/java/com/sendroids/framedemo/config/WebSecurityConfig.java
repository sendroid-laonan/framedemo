package com.sendroids.framedemo.config;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.repository.UserRepository;
import com.sendroids.framedemo.service.BaseService;
import com.sendroids.framedemo.service.Impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration          // 配置文件
@EnableWebSecurity      // 开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)  //AOP
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String[] UNAUTHORIZED_RESOURCE_LIST = new String[] { "/resources/**", "/assets/**", "/css/**",
            "/webjars/**", "/img/**", "/dandelion/**", "/js/**", "/fonts/**" };
    @Bean
    UserDetailsService customUserService() {
        return  new UserDetailServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll() //未鉴权前允许访问login.html
                .antMatchers(UNAUTHORIZED_RESOURCE_LIST).permitAll().anyRequest().authenticated() //未鉴权前还可以访问前端部分文件
                .and().formLogin().loginPage("/login") //security登陆页面为指定的login.html
                .defaultSuccessUrl("/index").permitAll() //登陆成功后可访问所有，以后按需修改
                .and().logout().permitAll();
        http.csrf().disable();

    }

}
