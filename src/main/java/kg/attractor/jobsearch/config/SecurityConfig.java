package kg.attractor.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

        String roleQuery = "select email, role_name " +
                "from users u, roles r " +
                "where u.email = ? " +
                "and u.role_id = r.id;";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(roleQuery);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // User end points
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").fullyAuthenticated()

                        // Resume end points
                        .requestMatchers(HttpMethod.DELETE, "/api/resumes/**").hasAuthority("admin")
                        .requestMatchers(HttpMethod.POST, "/api/resumes").hasAuthority("applicant")
                        .requestMatchers("/api/resumes/**").hasAuthority("applicant")

                        // Vacancy end points
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
