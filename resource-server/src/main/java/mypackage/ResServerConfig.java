package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableResourceServer
@Order(3)
public class ResServerConfig extends ResourceServerConfigurerAdapter {

    private static final String CHECK_URI = "http://localhost:8081/oauth/check_token";
    private static final String CLIENT_ID = "client";
    private static final String SECRET = "secret";

    @Autowired
    private TokenStore tokenStore;

    @Bean
    @Primary
    public RemoteTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(CHECK_URI);
        remoteTokenServices.setClientId(CLIENT_ID);
        remoteTokenServices.setClientSecret(SECRET);
        return remoteTokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore).resourceId("resourceId");
    }

//    @Override
//    public void configure(ResourceServ endpoints) throws Exception {
////        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
////        defaultUserAuthenticationConverter.setUserDetailsService(new MyUserDetailService());
////
////        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
////        defaultAccessTokenConverter.setUserTokenConverter(defaultUserAuthenticationConverter);
//        MyUserConvertService userConvertService = new MyUserConvertService();
//        endpoints.tokenStore(tokenStore)
//                .approvalStore(approvalStore)
//                .reuseRefreshTokens(false)
//                .accessTokenConverter(userConvertService)
//                .authenticationManager(authenticationManager);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().requestMatchers().antMatchers("/user/**")
                .and()
                .authorizeRequests().antMatchers("/user/**").authenticated()
                .and()
                .httpBasic().disable();

//        http.csrf().disable()
//                .requestMatchers().antMatchers("/user/**")
//                .and()
//                .authorizeRequests().antMatchers("/user/**").authenticated()
//                .and()
//                .httpBasic().disable();
    }

    @Bean
    public TokenStore tokenStore() {
        //token保存在内存中（也可以保存在数据库、Redis中）。
        //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
        //注意：如果不保存access_token，则没法通过access_token取得用户信息
        return new InMemoryTokenStore();
    }
}
