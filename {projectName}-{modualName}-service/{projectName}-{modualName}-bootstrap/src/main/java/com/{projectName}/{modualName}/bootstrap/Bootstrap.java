package com.{projectName}.{modualName}.bootstrap;

import java.io.IOException;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Bootstrap {

  @SuppressWarnings("resource")
  public static void main(String[] args) throws IOException {
    new FileSystemXmlApplicationContext("classpath:application-context.xml");
  }
}
