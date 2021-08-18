package com.orangeteam.Control_List.db;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.util.Optional;

@WebListener
public class DatabaseContextListener implements ServletContextListener {
    public static final String DB_ATTRIBUTE = "dbConnection";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Optional<Connection> dbConn = DBUtils.connect();
        context.setAttribute(DB_ATTRIBUTE, dbConn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ((Optional<Connection>) context.getAttribute(DB_ATTRIBUTE)).ifPresent(DBUtils::disconnect);
    }
}
