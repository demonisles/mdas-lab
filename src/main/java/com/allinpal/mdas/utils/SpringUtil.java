package com.allinpal.mdas.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {

  private static ApplicationContext context;
  
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
  
  public static Object getBean(String name) {
    return context.getBean(name);
  }

}
