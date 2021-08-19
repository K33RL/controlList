package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_form")
public class UserFormServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //забираем введенные параметры
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            UserDAOImpl.add(new User(name, surname));

            resp.sendRedirect(req.getContextPath() + "/users");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/user_form.jsp");
        }
    }
}