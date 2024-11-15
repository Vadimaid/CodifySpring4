package kg.codify.codifyspring4.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class MyFirstFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        System.out.println("Вызов doFilter в MyFirstFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (uri.contains("/class")) {
            if (!uri.contains("/all") && !uri.contains("/create") && !uri.contains("/by-name")) {
                try {
                    String id = uri.substring(uri.lastIndexOf("/") + 1);
                    long longId = Long.parseLong(id);
                    if (longId <= 0) {
                        System.out.println("ID не может быть отрицательным");
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неправильный формат ID");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID не может быть строкой");
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неправильный формат ID");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
