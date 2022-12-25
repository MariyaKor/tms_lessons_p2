package com.tms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Создать фильтр, который будет отклонять все запросы,
 * если в реквест хидере нет спец параметра (но разрешать при этом get запросы).
 */
@WebFilter(urlPatterns = {"/*"})
public class HeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isExceptionNeeded((HttpServletRequest) request)) {
            throw (new ServletException("Please fill in Client-Login"));
        }
        chain.doFilter(request, response);
    }

    private boolean isExceptionNeeded(HttpServletRequest request) {
        return (request.getHeader("Client-Login") == null
                || request.getHeader("Client-Login").isBlank())
                && !"GET".equalsIgnoreCase(request.getMethod());
    }

    @Override
    public void destroy() {
    }
}
