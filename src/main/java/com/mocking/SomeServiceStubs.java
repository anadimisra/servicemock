package com.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.tomakehurst.wiremock.client.WireMock;

@Component
public class SomeServiceStubs {

  // Since we can't use orders for all such customisations adding the Wiremock as
  // dependency to control init sequence
  @Autowired
  private MockingService mockingService;
  
  // Define stubs in constructor
  public SomeServiceStubs() {
    WireMock.configureFor("localhost", 8090);
    stubFor(
        get(urlPathMatching("/accounts/123456")).willReturn(aResponse().withStatus(200).withBodyFile("accounts-data/123456.json")));
  }

  public MockingService getMockingService() {
    return mockingService;
  }

}
