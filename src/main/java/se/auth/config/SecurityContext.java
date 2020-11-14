package se.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.auth.repository.UserRepository;
import se.auth.service.SecurityService;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(
					"/",
					"/auth/logout",
					"/auth/signup/**",
					"/auth/login",
					"/api/ws/**"
			).permitAll()
			//The rest of the our application is protected.
			.antMatchers("/**").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			//Configures the login function
			.formLogin()
			.loginPage("/auth/login")
			//Configures the logout function
			.and()
			.logout()
			.deleteCookies("JSESSIONID")
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/auth/login")
			//Configures url based authorization
			.and();
			//Anyone can access the urls
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityService(userRepository);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}