package security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        JwtWebSecurityConfigurer
                .forRS256("https://reemohealth.auth0.com/","http://52.173.187.125:8080")
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/config").permitAll()
                .antMatchers(HttpMethod.GET,"/health").permitAll()
                //.antMatchers(HttpMethod.GET,"/psap").permitAll()
                .antMatchers(HttpMethod.GET,"/psap/**").fullyAuthenticated()
                .anyRequest().fullyAuthenticated();
    }

}

