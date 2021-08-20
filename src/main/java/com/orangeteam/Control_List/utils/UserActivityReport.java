package com.orangeteam.Control_List.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import com.orangeteam.Control_List.dao.ActivityDAOImpl;
import com.orangeteam.Control_List.dao.UserDAOImpl;
import com.orangeteam.Control_List.db.DBUtils;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Date;

public class UserActivityReport  {
    private UserDAOImpl userDAO = new UserDAOImpl(DBUtils.connect().get());
    private ActivityDAOImpl activityDAO = new ActivityDAOImpl(DBUtils.connect().get());



    public void createPdf() throws IOException {

        List actListText = new List();
        java.util.List<User> userList = getUsers();
        PdfWriter writer;
        try {
            Document document = new Document(PageSize.A4);
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 24, Font.BOLD, new CMYKColor(255, 255, 255, 2));
            Font fontUser = FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD, new CMYKColor(255, 255, 255, 2));
            Font fontActivity = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, new CMYKColor(255, 255, 255, 2));
            writer = PdfWriter.getInstance(document, new FileOutputStream(""));
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = Image.getInstance("IMG SOURCE HERE");
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState state = new PdfGState();
            state.setFillOpacity(0.6f);
            canvas.setGState(state);
            canvas.addImage(image);
            canvas.restoreState();
            Paragraph paragraph = new Paragraph("Orange Team Report", fontTitle);
            paragraph.setIndentationLeft(150);
            document.add(paragraph);
            document.add(new Paragraph(new Date().toString(), fontActivity));
            for (User user :
                    userList) {
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(user.getName() + "" + user.getSurName(), fontUser));
                java.util.List<Activity> activityList = getUserActivity(user);
                for (Activity activity :
                        activityList) {
                    actListText.add(new ListItem(activity.getDurationMin() + " : " + activity.getDescription(), fontActivity));
                    document.add(actListText);
                }
            }
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private java.util.List getUsers() {
        return userDAO.getAll();
    }

    private java.util.List getUserActivity(User user) {
        return activityDAO.getAllByUser(user);
    }

}
