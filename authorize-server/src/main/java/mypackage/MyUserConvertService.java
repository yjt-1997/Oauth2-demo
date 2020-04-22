package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MyUserConvertService extends DefaultAccessTokenConverter {

//    private UserAuthenticationConverter userTokenConverter;

//    @Autowired
//    public MyUserConvertService(){
//        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        defaultUserAuthenticationConverter.setUserDetailsService(new MyUserDetailService());
//        super.setUserTokenConverter(defaultUserAuthenticationConverter);
//        System.out.println("my init");
//    }

//    @Override
//    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
//        Map<String, Object> response = new LinkedHashMap();
//        response.put("user_name", authentication.getName());
//        response.put("details", authentication.getDetails());
//        return response;
//    }
}
//public class MyUserConvertService extends DefaultUserAuthenticationConverter{
//
//    @Override
//    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
//        Map<String, Object> response = new LinkedHashMap();
//        response.put("user_name", authentication.getName());
//        response.put("details", authentication.getDetails());
//        return response;
//    }
//}
