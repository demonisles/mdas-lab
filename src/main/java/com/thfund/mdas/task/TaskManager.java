package com.thfund.mdas.task;

import it.sauronsoftware.cron4j.Scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskManager {
  private final Scheduler scheduler = new Scheduler();
  private List<Task> tasks = new ArrayList<Task>();
  
  public void addTask(Task task) {
    
  }
  
  public void removeTask(Task task) {
    
  }
  
  public void startup() {
    System.out.println("taskManager start...");
    
    for(Task task : tasks) {
      String s1 = scheduler.schedule("* * * * *", new Runnable() {
        public void run() {
          System.out.println("schedule1 run..." + new Date());
        }
      });
      System.out.println("s1:" + s1);
      
      /*ProcessTask task = new ProcessTask("cmd.exe /c dir");
      String s2 = scheduler.schedule("* * * * *", task);
      System.out.println("s2:" + s2);*/
      
      String s3 = scheduler.schedule("* * * * *", new Runnable() {
        public void run() {
          try {
            System.out.println("schedule3 run..." + new Date());
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("cmd.exe /c dir");
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = bf.readLine()) != null)
            System.out.println(line);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
      System.out.println("s3:" + s3);
    }
    scheduler.start();
  }
  
  public void shutdown() {
    scheduler.stop();
  }
  
}
