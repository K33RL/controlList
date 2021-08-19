package com.orangeteam.Control_List.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.orangeteam.Control_List.dao.ActivityDAOImpl;
import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.db.DBUtils;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class UserActivityReport implements PdfCreationSimpleInterface {
    private UserDAOImpl userDAO = new UserDAOImpl(DBUtils.connect().get());
    private ActivityDAOImpl activityDAO = new ActivityDAOImpl(DBUtils.connect().get());


    @Override
    public void createPdf(String pathToSave) {
        Document document = new Document();
        List actListText = new List();
        java.util.List<User> userList = getUsers();
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pathToSave));
            document.open();
            document.add(new Paragraph(new Date().toString()));
            for (User user :
                    userList) {
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(user.getName() + "" + user.getSurName()));
                java.util.List<Activity> activityList = getUserActivity(user);
                for (Activity activity :
                        activityList) {
                    actListText.add(new ListItem(activity.getDurationMin() + " : " + activity.getDescription()));
                    document.add(actListText);
                }
            }
            pdfWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

    }

    private java.util.List getUsers() {
        return userDAO.getAll();
    }

    private java.util.List getUserActivity(User user) {
        return activityDAO.getAllByUser(user);
    }

}
