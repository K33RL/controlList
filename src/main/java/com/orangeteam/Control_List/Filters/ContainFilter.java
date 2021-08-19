package com.orangeteam.Control_List.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = "FormServlet")
public class ContainFilter implements Filter {

    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int time = Integer.parseInt(req.getParameter("time"));
        if (name.length() < 20 && surname.length() < 20 && time < 24) {
            filterChain.doFilter(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("" + "").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
