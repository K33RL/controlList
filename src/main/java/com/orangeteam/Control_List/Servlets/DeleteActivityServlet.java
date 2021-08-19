package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.ActivityDAO;
import com.orangeteam.Control_List.dao.ActivityDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import static com.orangeteam.Control_List.db.DatabaseContextListener.DB_ATTRIBUTE;

@WebServlet("/deleteActivity")
public class DeleteActivityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            ActivityDAO activityDAO = new ActivityDAOImpl(dbConn.get());
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                activityDAO.remove(id);
                resp.sendRedirect(req.getContextPath() + "/user_activity");
            } catch (Exception e) {
                getServletContext().getRequestDispatcher("/404.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }
}