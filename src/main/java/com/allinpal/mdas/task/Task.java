package com.allinpal.mdas.task;

import org.apache.log4j.Logger;
/**
 * 定时任务接口
 * @author Shawn
 *
 */
public interface Task {
  
  Logger logger = Logger.getLogger(Task.class);

  String getName();
  
  String getSchedulePattern();
  
  void exec() throws Exception;
  
  String getDesc();
}
