package com.thfund.mdas.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.thfund.mdas.task.Task;
import com.thfund.mdas.task.TaskManager;
import com.thfund.mdas.utils.SpringUtil;

public class TaskServlet extends HttpServlet {
  private static Logger logger = Logger.getLogger(TaskServlet.class);
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response
        .getWriter()
        .println(
            "<html> <title> Task List</title> <body> <table border='1' cellspacing='2%' width='80%'> <tr><th>Name</th><th>Pattern</th><th>Desc</th></tr>");
    StringBuffer sb = new StringBuffer();
    TaskManager taskManager = (TaskManager) SpringUtil.getBean("taskManager");
    logger.info("taskManager tasks:"+taskManager.getTasks());
    for (Task task : taskManager.getTasks()) {
      sb.append("<tr><td>").append(task.getName()).append("</td><td>")
          .append(task.getSchedulePattern()).append("</td><td>").append(task.getDesc())
          .append("</td></tr>");
    }
    response.getWriter().println(sb.toString());
    response.getWriter().println("</table> </body> </html>");
  }
}
