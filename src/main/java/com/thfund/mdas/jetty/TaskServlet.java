package com.thfund.mdas.jetty;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.thfund.mdas.task.TaskManager;
import com.thfund.mdas.utils.SpringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TaskServlet extends HttpServlet {
  private static Logger logger = Logger.getLogger(TaskServlet.class);
  private static final long serialVersionUID = 1L;

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
    cfg.setDirectoryForTemplateLoading(new File("../templates"));
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    
    Template temp = cfg.getTemplate("test.ftlh");
    TaskManager taskManager = (TaskManager) SpringUtil.getBean("taskManager");
    logger.info("taskManager tasks:"+taskManager.getTasks());
    Map root = new HashMap();
    try {
      root.put("tasks", taskManager.getTasks());
      temp.process(root, response.getWriter());
    } catch (TemplateException e) {
      e.printStackTrace();
    }
    //System.out.println(new File(".").getAbsolutePath());
   /* response
        .getWriter()
        .println(
            "<html> <title> Task List</title> <body> <table border='1' cellspacing='2%' width='80%'> <tr><th>Name</th><th>Pattern</th><th>Desc</th> <th>operate</th></tr>");
    StringBuffer sb = new StringBuffer();
    TaskManager taskManager = (TaskManager) SpringUtil.getBean("taskManager");
    logger.info("taskManager tasks:"+taskManager.getTasks());
    for (Task task : taskManager.getTasks()) {
      sb.append("<tr><td>").append(task.getName()).append("</td><td>")
          .append(task.getSchedulePattern()).append("</td><td>").append(task.getDesc())
          .append("</td><td></td></tr>");
    }
    response.getWriter().println(sb.toString());
    response.getWriter().println("</table> </body> </html>");*/
  }
}
