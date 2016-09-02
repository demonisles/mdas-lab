package com.thfund.mdas;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thfund.mdas.task.TaskManager;


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
    
    final TaskManager taskManager = context.getBean("taskManager",TaskManager.class);
    taskManager.startup();
    
    new Thread(new Runnable() {
      public void run() {
        new SocketServer().start();
      }

    }).start();

    logger.info("socket started!");
    
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        taskManager.shutdown();
        System.out.println("process shutdown!");
      }
    });
  }
}
