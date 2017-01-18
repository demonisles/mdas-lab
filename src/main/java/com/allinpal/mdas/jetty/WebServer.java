package com.allinpal.mdas.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import com.allinpal.mdas.task.TaskManager;

public class WebServer {
  private Server server;
  private TaskManager taskManager;

  public TaskManager getTaskManager() {
    return taskManager;
  }

  public void setTaskManager(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

  public void startup() throws Exception {
    server = new Server();
    ServerConnector http = new ServerConnector(server);
    http.setHost("localhost");
    http.setPort(9527);
//    http.setIdleTimeout(30000);
    // Set the connector
    server.addConnector(http);
    ServletHandler handler = new ServletHandler();
    server.setHandler(handler);
    handler.addServletWithMapping(TaskServlet.class, "/*");
    server.start();
    server.join();
  }

  public void shutdown() {
    if (server != null) {
      try {
        server.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
