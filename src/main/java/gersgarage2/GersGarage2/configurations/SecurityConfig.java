package gersgarage2.GersGarage2.configurations;

import gersgarage2.GersGarage2.service.CustomSuccessHandler;
import gersgarage2.GersGarage2.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(c -> c.disable())

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/adminpage", "/profiles", "/deleteClient/**", "/edit-Client", "/editProfilesClients/**", "/listAll-admin", "/deleteAdmin/**", "/edit-Admin", "/editProfilesAdmin/**", "/edit-Staff", "/editProfilesStaff/**", "/deleteStaff/**", "/listAll-staff", "/listAll-clients", "/registration-user")
                        .hasAuthority("ADMIN")
                        .requestMatchers("/userpage")
                        .hasAuthority("CLIENT")
                        .requestMatchers("/staffpage")
                        .hasAuthority("STAFF")
                        .requestMatchers("/css/**", "/img/**", "/js/**", "/lib/**", "/scss/**", "/registration", "/", "/about", "/home", "/shop", "/service", "/wash", "/contact", "/staff", "/testimonial")
                        .permitAll())
                        /*.anyRequest().authenticated())*/

                .formLogin(form -> form.loginPage("/").loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler)
                        .permitAll())

                .logout(form -> form.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .permitAll());

        return http.build();

    }

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
