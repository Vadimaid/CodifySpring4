package kg.codify.codifyspring4.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyFirstInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        System.out.println("PreHandle в MyFirstInterceptor");
        String uri = request.getRequestURI();
        if (uri.contains("/class")) {
            if (!uri.contains("/all") && !uri.contains("/create") && !uri.contains("/by-name")) {
                try {
                    String id = uri.substring(uri.lastIndexOf("/") + 1);
                    long longId = Long.parseLong(id);
                    if (longId <= 0) {
                        System.out.println("ID не может быть отрицательным");
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неправильный формат ID");
                        return false;
                    }
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println("ID не может быть строкой");
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неправильный формат ID");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
        System.out.println("PostHandle в MyFirstInterceptor");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception {
        System.out.println("AfterCompletion в MyFirstInterceptor");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
