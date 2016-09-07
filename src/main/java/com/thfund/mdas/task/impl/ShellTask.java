package com.thfund.mdas.task.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.thfund.mdas.task.Task;

public class ShellTask implements Task{

  public String getName() {
    return "shellTask";
  }

  public String getSchedulePattern() {
    return "* * * * *";
  }

  public void exec() throws Exception {
    Runtime r = Runtime.getRuntime();
    Process p = r.exec("../scripts/test.sh");
    BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line = "";
    while ((line = bf.readLine()) != null)
      System.out.println(line);
  }

  public String getDesc() {
    // TODO Auto-generated method stub
    return null;
  }
}
