package mypackage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        //用户信息保存在内存中
        //在鉴定角色roler时，会默认加上ROLLER_前缀

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("student").password(passwordEncoder().encode("123456")).roles("STUDENT").and()
                .withUser("teacher").password(passwordEncoder().encode("123456")).roles("TEACHER");
    }

    @Bean
    public UserDetailsService userDetails(){
        return new MyUserDetailService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //登记界面，默认是permit All
                .and()
                .authorizeRequests().antMatchers("/", "/home").permitAll() //不用身份认证可以访问
                .and()
                .authorizeRequests().anyRequest().authenticated() //其它的请求要求必须有身份认证
                .and()
                .csrf() //防止CSRF（跨站请求伪造）配置
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
