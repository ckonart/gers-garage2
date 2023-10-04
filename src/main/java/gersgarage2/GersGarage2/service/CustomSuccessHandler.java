package gersgarage2.GersGarage2.service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {

        var authourities = authentication.getAuthorities();
        var roles = authourities.stream().map(r -> r.getAuthority()).findFirst();

        if (roles.orElse("").equals("ADMIN")) {
            response.sendRedirect("/adminpage");
        } else if (roles.orElse("").equals("CLIENT")) {
            response.sendRedirect("/userpage");
        } else {
            response.sendRedirect("/404");
        }
    }
}
