package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.ActivityDAO;
import com.orangeteam.Control_List.dao.ActivityDAOImpl;
import com.orangeteam.Control_List.dao.UserDAO;
import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.model.Activity;
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

@WebServlet("/create")
public class AddActivityServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            UserDAO userDao = new UserDAOImpl(dbConn.get());
            try {
                int user_id = Integer.parseInt(req.getParameter("id"));
                Optional<User> user = userDao.getById(user_id);
                req.setAttribute("user", user);
                req.setAttribute("id", user_id);
                getServletContext().getRequestDispatcher(req.getContextPath() + "/create.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                getServletContext().getRequestDispatcher("/404.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            UserDAO userDao = new UserDAOImpl(dbConn.get());
            ActivityDAO activityDAO = new ActivityDAOImpl(dbConn.get());
            try {
                int time = Integer.parseInt(req.getParameter("time"));
                String description = req.getParameter("description");
                Optional<User> user = (Optional<User>) req.getAttribute("user");
                Activity activity = new Activity(user.get(), time, description);
                activityDAO.addByUser(user.get(), activity);

                resp.sendRedirect(req.getContextPath() + "/activities");
            } catch (Exception e) {
                e.printStackTrace();
                getServletContext().getRequestDispatcher("/404.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}