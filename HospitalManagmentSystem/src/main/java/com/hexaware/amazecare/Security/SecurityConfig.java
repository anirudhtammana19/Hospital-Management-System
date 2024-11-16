package com.hexaware.amazecare.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import com.hexaware.amazecare.Model.Users.Role;
import com.hexaware.amazecare.Service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserInfoUserDetailsService userInfoUserDetailsService;
	
	@Autowired
	JWTAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				 .authorizeHttpRequests(auth -> auth
			                .requestMatchers("/api/register","/api/login").permitAll()
			                .requestMatchers("/api/admin/**").hasRole("ADMIN")
			                .requestMatchers("/api/doctor/viewProfile").hasRole("DOCTOR")
			                .requestMatchers("/api/patient/viewProfile").hasRole("PATIENT")
			                
			                .requestMatchers("/api/patient/**").hasAnyRole("PATIENT", "ADMIN")
			                .requestMatchers("/api/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
			                .requestMatchers("/api/medicalRecord/**").hasAnyRole("DOCTOR", "ADMIN")
			                .requestMatchers("/api/getDoctorSpecialties").hasAnyRole("PATIENT", "ADMIN")
			                .anyRequest().authenticated()
			            )
								//.httpBasic(Customizer.withDefaults())
				.sessionManagement(sessionManage->sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				//.formLogin(formLogin->formLogin.permitAll())
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails
	 * normalUser=User.builder() .username("ajay")
	 * .password(passwordEncoder().encode("ajay")) .roles("PATIENT") .build();
	 * return new InMemoryUserDetailsManager(normalUser); }
	 */

	@Bean
	public UserDetailsService usersDetailsService() {
		return userInfoUserDetailsService;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userInfoUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
