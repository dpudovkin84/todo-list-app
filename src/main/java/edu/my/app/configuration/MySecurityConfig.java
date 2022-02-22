package edu.my.app.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/index.html","/user/add","/user/save").permitAll()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/users").hasAnyRole("USER","ADMIN")
                .and()
                .formLogin();
//                .csrf()
//                .disable()
//                .authorizeRequests()
////                .antMatchers("/","/index").permitAll()
//                .antMatchers("/","/users").hasAnyRole("USER","ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//                .authorizeRequests()
//                .antMatchers("/users").hasAnyRole("USER")
//                .antMatchers("/hr_info").hasRole("HR")
//                .antMatchers("/manager_info").hasRole("MANAGER")
//                .and().formLogin().permitAll();
    }

}
