package com.orangeteam.Control_List.scheduler;

import com.orangeteam.Control_List.utils.MailSender;
import com.orangeteam.Control_List.utils.UserActivityReport;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ReportSchedulerInitializer implements ServletContextListener {

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable job = new ReportSchedulerJob(sce.getServletContext());
        long initialDelay = getInitialDelay();
        scheduledExecutorService.scheduleAtFixedRate(job, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }

    // Delay for first execution in seconds
    private long getInitialDelay() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        ZonedDateTime period = now.withHour(17).withMinute(42).withSecond(0);
        if(now.compareTo(period) > 0)
            period = period.plusDays(1);
        Duration duration = Duration.between(now, period);
        return duration.getSeconds();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

 class ReportSchedulerJob implements Runnable {

    private ServletContext context;

    public ReportSchedulerJob(ServletContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            new UserActivityReport().createPdf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File pdfReport = new File("OrangeTeamReport.pdf");
        new MailSender("lykinevgen@gmail.com", "tvgqlwdkvmhjpvvx").sendMailWithAttachment("Testing mail sender", new Date().toString(), "lykinevgen@gmail.com", pdfReport);
    }

}
