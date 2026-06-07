package io.github.lengors.janus;

import io.github.lengors.janus.testing.authentik.AuthentikClientConnectionDetailsConfiguration;
import io.github.lengors.janus.testing.wiremock.WireMockTestContainerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

class JanusApplicationTests {

  @Test
  void givenApplicationContextWhenBootingThenShouldStartSuccessfully() {
    SpringApplication
      .from(JanusApplication::main)
      .with(
        AuthentikClientConnectionDetailsConfiguration.class,
        WireMockTestContainerConfiguration.class
      )
      .run();
  }
}
