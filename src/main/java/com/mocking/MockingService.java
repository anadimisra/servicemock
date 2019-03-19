package com.mocking;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.tomakehurst.wiremock.WireMockServer;

@Component
@Order(1)
public class MockingService {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(MockingService.class);

  @Value("${wiremock.server.host:localhost}")
  private String bindingHost;
  
  @Value("${wiremock.server.port:8090}")
  private int bindingPort;
  
  private WireMockServer server;

  public MockingService() {
    server = new WireMockServer(options().port(8090).bindAddress("localhost"));
    LOGGER.info("Starting Wiremock Server bound to address {} at port {}", bindingHost, bindingPort);
    server.start();
  }
  
  @PreDestroy
  public void shutdown() {
    LOGGER.info("Shutting Wiremock Server");
    server.shutdown();
  }
}
