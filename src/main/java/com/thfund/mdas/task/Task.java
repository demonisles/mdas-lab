package com.thfund.mdas.task;

public interface Task {
  
  String getName();
  
  String getSchedulePattern();
  
  void exec() throws Exception;
}
