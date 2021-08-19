package com.orangeteam.Control_List.Servlets;

import com.orangeteam.Control_List.dao.UserDAO;
import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Optional;

import static com.orangeteam.Control_List.db.DatabaseContextListener.DB_ATTRIBUTE;


@WebServlet("/user_form")
public class UserFormServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp){

        try {
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

        Optional<Connection> dbConn = (Optional<Connection>) req.getServletContext().getAttribute(DB_ATTRIBUTE);
        if (dbConn.isPresent()) {
            UserDAO userDao = new UserDAOImpl(dbConn.get());
            try {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                userDao.add(new User(name, surname));

                resp.sendRedirect(req.getContextPath() + "/users");
            } catch (Exception e) {
                getServletContext().getRequestDispatcher("/user_form.jsp");
            }
        } else {
            // обработка отсутствия коннекта к бд, страничка какая то мб
        }
    }
}