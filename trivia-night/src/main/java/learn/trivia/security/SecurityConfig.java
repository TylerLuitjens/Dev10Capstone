package learn.trivia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    SecurityConfig (JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/user/create").permitAll()
                .antMatchers(HttpMethod.GET, "user/leaderboard").permitAll()
                .antMatchers(HttpMethod.GET, "/user/*").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/user/").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/game/*").hasAnyRole("USER")
                .antMatchers(HttpMethod.POST, "/game/user/*/*").hasAnyRole("USER")
                .antMatchers(HttpMethod.POST, "/game/*").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/game/*").hasAnyRole("USER")
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        var userBuilder = User.withUsername("user")
//                .password("password").passwordEncoder(password -> encoder.encode(password))
//                .roles("USER");
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder);
//    }

//    @Autowired
//    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }


}
