package com.{projectName}.{modualName}.service.impl;

import com.github.hippo.annotation.HippoServiceImpl;
import com.{projectName}.{modualName}.service.PingService;

/**
@HippoServiceImpl 替代 @Service (必须使用@HippoServiceImpl)
**/
@HippoServiceImpl
public class PingServiceImpl implements PingService {

  @Override
  public String ping() {
    return "pong";
  }
}
