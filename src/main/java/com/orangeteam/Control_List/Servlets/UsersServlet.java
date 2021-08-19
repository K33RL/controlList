package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserDAOImpl> listUsers = UserDAOImpl.getAll();
        req.setAttribute("listUser", listUsers);

        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
