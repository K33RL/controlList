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
import java.util.List;

@WebServlet("/user_activities")
public class UserActivitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         Параметр
        int id = Integer.parseInt(req.getParameter("id"));
        List<Activity> activityList = ActivityDAOImpl.getAllByUser(new User(UserDAOImpl.getById(id)));
        req.setAttribute(activityList);

        getServletContext().getRequestDispatcher("/user_activities.jsp").forward(req, resp);
    }

}
