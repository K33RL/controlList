package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.UserDAO;
import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import static com.orangeteam.Control_List.db.DatabaseContextListener.DB_ATTRIBUTE;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            UserDAO userDao = new UserDAOImpl(dbConn.get());
            try {
                int user_id = Integer.parseInt(req.getParameter("id"));
                Optional<User> user = userDao.getById(user_id);
                req.setAttribute("user", user);
                getServletContext().getRequestDispatcher(req.getContextPath() + "/edit.jsp").forward(req, resp);
            } catch (Exception e) {
                getServletContext().getRequestDispatcher("/404.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            UserDAO userDao = new UserDAOImpl(dbConn.get());
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                User user = new User(id, name, surname);
                userDao.update(user);
                resp.sendRedirect("/users.jsp");
            } catch (Exception e) {
                getServletContext().getRequestDispatcher("/404.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }
}