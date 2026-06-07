package io.github.lengors.janus.integrations.authentik;

import org.springframework.boot.docker.compose.core.RunningService;
import org.springframework.boot.docker.compose.service.connection.DockerComposeConnectionDetailsFactory;
import org.springframework.boot.docker.compose.service.connection.DockerComposeConnectionSource;

import java.util.Optional;

class AuthentikClientDockerComposeConnectionDetailsFactory
  extends DockerComposeConnectionDetailsFactory<AuthentikClientConnectionDetails> {
  private static final String[] AUTHENTIK_CLIENT_CONTAINER_NAMES = {"authentik-server"};
  private static final int CONTAINER_PORT = 9000;

  AuthentikClientDockerComposeConnectionDetailsFactory() {
    super(AUTHENTIK_CLIENT_CONTAINER_NAMES);
  }

  @Override
  protected AuthentikClientConnectionDetails getDockerComposeConnectionDetails(
    final DockerComposeConnectionSource source
  ) {
    return new AuthentikClientDockerComposeConnectionDetails(source.getRunningService());
  }

  private static final class AuthentikClientDockerComposeConnectionDetails extends DockerComposeConnectionDetails
    implements AuthentikClientConnectionDetails {
    private final String serviceAccountToken;
    private final String url;

    private AuthentikClientDockerComposeConnectionDetails(final RunningService runningService) {
      super(runningService);
      final var host = runningService.host();
      final var port = runningService
        .ports()
        .get(CONTAINER_PORT);
      this.url = "http://%s:%d".formatted(host, port);
      this.serviceAccountToken = Optional
        .ofNullable(runningService
          .env()
          .get("AUTHENTIK_BOOTSTRAP_TOKEN"))
        .orElseThrow();
    }

    @Override
    public String serviceAccountToken() {
      return serviceAccountToken;
    }

    @Override
    public String url() {
      return url;
    }
  }
}
