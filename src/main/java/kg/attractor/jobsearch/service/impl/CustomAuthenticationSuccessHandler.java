package kg.attractor.jobsearch.service.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String redirectURL = "/";

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (role.equals("admin")) {
                redirectURL = "/admin/dashboard";
                break;
            } else if (role.equals("employer")) {
                redirectURL = "/resumes";
                break;
            } else if (role.equals("applicant")) {
                redirectURL = "/vacancies";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}
