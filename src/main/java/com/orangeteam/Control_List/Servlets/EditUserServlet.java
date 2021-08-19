package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.ActivityDAOImpl;
import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            int user_id = Integer.parseInt(req.getParameter("id"));
            User user = UserDAOImpl.getById(user_id);
            if (user != null) {
                req.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            User user = new User(id, name, surname);
            UserDAOImpl.update(user);
            resp.sendRedirect(req.getContextPath() + "/users");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
        }
    }
}