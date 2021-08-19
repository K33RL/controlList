package com.orangeteam.Control_List.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(servletNames = "FormServlet")
public class EmptyFilter implements Filter {

    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig fConfig){
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        if (!req.getParameter("name").isEmpty() && !req.getParameter("surname").isEmpty() && !req.getParameter("time").isEmpty()) {
                filterChain.doFilter(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("" + "").forward(req, resp);
        }
    }

    @Override
    public void destroy() {}
}
