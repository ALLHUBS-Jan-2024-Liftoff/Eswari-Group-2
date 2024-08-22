package org.launchcode.git_artsy_backend.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.launchcode.git_artsy_backend.controllers.UserController;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter  implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    private static final List<String> whitelist = Arrays.asList("/user/login", "/api/user/newUser", "/user/logout"
            ,"/gitartsy/api/artworks/**", "/uploads/**", "/gitartsy/api/tags/**", "/gitartsy/api/profiles/**",
            "/gitartsy/api/profiles/profileid/**", "/gitartsy/api/**", "/api/commissions/**", "/api/profile/**", "/api/follow/**",
            "/gitartsy/api/search/**");



    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        if (isWhitelisted(request.getRequestURI())) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = userController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            return true;
        }

        // The user is NOT logged in
        if (request.getMethod().equals("OPTIONS")) {
            // For preflight requests, respond with the necessary CORS headers
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else {
            // For other requests, respond with a CORS error
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
