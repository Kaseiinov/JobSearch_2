package kg.attractor.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder encoder;
    private final DataSource dataSource;

    @Autowired
    public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "select email, password, enabled " +
                "from users " +
                "where email = ?;";

        String roleQuery = """
                select u.email ,
                r.role_name
                from users u
                join usr_roles ur on u.id = ur.user_id
                join roles r on r.id = ur.role_id
                where u.email = ?;
                """;

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(roleQuery);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        //Auth end points

                        // User end points
                        .requestMatchers("/users/**").fullyAuthenticated()

                        // Resume end points
                        .requestMatchers(HttpMethod.GET, "/resumes").hasAuthority("employer")
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasAuthority("admin")
                        .requestMatchers(HttpMethod.POST, "/resumes").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.POST, "/resumes/**").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.GET, "/resumes/**").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasAuthority("applicant")

                        // Vacancy end points
                        .requestMatchers(HttpMethod.POST, "/vacancies").hasAuthority("employer")
                        .requestMatchers(HttpMethod.POST, "/vacancies/**").hasAuthority("employer")
                        .requestMatchers(HttpMethod.GET, "/vacancies/create").hasAuthority("employer")
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasAuthority("admin")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasAuthority("employer")

                        // Images end points
                        .requestMatchers(HttpMethod.POST, "/images/**").fullyAuthenticated()


                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
