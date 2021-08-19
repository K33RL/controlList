package com.orangeteam.Control_List.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = "FormServlet")
public class TypeFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        if (!req.getParameter("name").matches("[-+]?\\d+") && !req.getParameter("surname").matches("[-+]?\\d+")) {
            if (req.getParameter("time").matches("[-+]?\\d+")) {
                filterChain.doFilter(req, resp);
            } else {
                req.getServletContext().getRequestDispatcher("" + "").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
