package io.github.lengors.janus.testing.authentik;

import io.github.lengors.janus.testing.wiremock.WireMockTestContainerConfigurer;
import org.springframework.stereotype.Component;
import org.wiremock.integrations.testcontainers.WireMockContainer;

@Component
class AuthentikWireMockTestContainerConfigurer implements WireMockTestContainerConfigurer {
  @Override
  public WireMockContainer configure(final WireMockContainer wireMockContainer) {
    return wireMockContainer.withMappingFromResource("mappings/authentik.json");
  }
}
