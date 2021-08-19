package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
//            Добавить параметр
            UserDAOImpl.remove();
            resp.sendRedirect(req.getContextPath() + "/users");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
        }
    }
}