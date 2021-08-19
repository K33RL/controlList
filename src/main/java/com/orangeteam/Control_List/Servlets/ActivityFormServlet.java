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

@WebServlet("/activity_form")
public class ActivityFormServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

       try {
           getServletContext().getRequestDispatcher("/activity_form.jsp").forward(req, resp);
       } catch (Exception e) {
           e.printStackTrace();
       }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //забираем введенные параметры
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int time = Integer.parseInt(req.getParameter("time"));
            String description = req.getParameter("description");
            User user = UserDAOImpl.getById(id);
            Activity activity = new Activity(user, time, description);
            // передаем в метод, который заносит эти данные в БД и PDF
            ActivityDAOImpl.addByUser(user, activity);

            resp.sendRedirect(req.getContextPath() + "/user_activity");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/activity_form.jsp");
        }
    }
}