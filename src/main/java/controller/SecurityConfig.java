package controller;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http.cors();
        http.csrf().disable().cors();

        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/userInformation").permitAll();
        JwtWebSecurityConfigurer
                .forRS256(apiAudience,issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/psapTesting/**").permitAll()
                .antMatchers(HttpMethod.GET,"/health/**").permitAll()
                //.antMatchers(HttpMethod.GET,"/userInformation**").permitAll()
                .antMatchers(HttpMethod.GET,"/config/**").permitAll()
                .antMatchers(HttpMethod.GET, "/userInformationDev**").permitAll()
                //.anyRequest().anonymous();
                .anyRequest().authenticated();

                //.anyRequest().fullyAuthenticated();
    }
}

