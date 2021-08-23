package com.orangeteam.Control_List.Servlets;

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
import java.util.List;
import java.util.Optional;

import static com.orangeteam.Control_List.db.DatabaseContextListener.DB_ATTRIBUTE;

@WebServlet("/activities")
public class UserActivitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            UserDAO userDao = new UserDAOImpl(dbConn.get());
            ActivityDAOImpl activityDAO = new ActivityDAOImpl(dbConn.get());
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<User> user = userDao.getById(id);
            List<Activity> activityList = activityDAO.getAllByUser(user.get());
            req.setAttribute("activityList", activityList);
            req.setAttribute("user", user);

            getServletContext().getRequestDispatcher(req.getContextPath() + "/activities.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }

}
