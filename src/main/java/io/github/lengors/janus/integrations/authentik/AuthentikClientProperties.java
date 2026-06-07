package io.github.lengors.janus.integrations.authentik;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Properties for configuring the Authentik client.
 *
 * @param serviceAccountToken the service account token for authentication
 * @param url                 the base URL of the Authentik server
 * @author lengors
 */
@ConfigurationProperties(prefix = "authentik.client")
public record AuthentikClientProperties(
  @NotNull
  String serviceAccountToken,

  @NotNull
  @DefaultValue("http://localhost:9000")
  String url
) {
  /**
   * Default constructor for AuthentikClientProperties.
   */
  @ConstructorBinding
  public AuthentikClientProperties {
    // Empty constructor for Spring Boot configuration binding
  }
}
