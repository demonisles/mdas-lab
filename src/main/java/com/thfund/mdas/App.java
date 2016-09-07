package com.thfund.mdas;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thfund.mdas.jetty.WebServer;
import com.thfund.mdas.task.TaskManager;
import com.thfund.mdas.task.impl.HiveTask;
import com.thfund.mdas.task.impl.ShellTask;
import com.thfund.mdas.task.impl.TestTask;


/**
 * Hello world!
 * 
 */
public class App {
  private static Logger logger = Logger.getLogger(App.class);
  private static ApplicationContext context;

  public static void main(String[] args) {
    PropertyConfigurator.configure("../conf/log4j.properties");
    context = new ClassPathXmlApplicationContext(new String[] {"classpath*:*.xml"});

    final TaskManager taskManager = context.getBean("taskManager", TaskManager.class);
    // taskManager.addTask(new ShellTask());
    taskManager.addTask(new TestTask("test dir"));

    // taskManager.addTask(new HiveTask());

    taskManager.startup();


    final WebServer webServer = context.getBean("webServer", WebServer.class);

    new Thread(new Runnable() {
      public void run() {
        try {
          webServer.startup();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
    logger.info("webServer started!");

    new Thread(new Runnable() {
      public void run() {
        new SocketServer().start();
      }

    }).start();

    logger.info("socket started!");

    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        taskManager.shutdown();
        webServer.shutdown();
        System.out.println("process shutdown!");
      }
    });
  }
}
