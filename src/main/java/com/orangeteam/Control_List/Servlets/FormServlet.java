package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.ActivityDAO;
import com.orangeteam.Control_List.dao.ActivityDAOImpl;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;

@WebServlet("/form")
public class FormServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

       try {
           getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
       } catch (Exception e) {
           e.printStackTrace();
       }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //забираем введенные параметры
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
//            OffsetDateTime start_time = req.getParameter("start_time");
//            OffsetDateTime end_time = req.getParameter("end_time");
            String description = req.getParameter("description");
            User user = new User(id, name, surname);
            Activity activity = new Activity(user, start_time, end_time, description);
            // передаем в метод, который заносит эти данные в БД и PDF
            ActivityDAOImpl.addByUser(user, activity);

            resp.sendRedirect(req.getContentPath() + "/user_activity");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/form.jsp");
        }
    }
}