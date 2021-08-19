package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.ActivityDAOImpl;
import com.orangeteam.Control_List.dao.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteActivity")
public class DeleteActivityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
//            Добавить параметр
            ActivityDAOImpl.remove();
            resp.sendRedirect(req.getContextPath() + "/user_activity");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/user_activity.jsp").forward(req, resp);
        }
    }
}