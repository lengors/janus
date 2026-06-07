package io.github.lengors.janus.integrations.authentik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

/**
 * Client for interacting with Authentik's REST API.
 * <p>
 * This client provides methods to fetch paginated user data from Authentik.
 *
 * @author lengors
 */
@Component
public class AuthentikRestClient {
  private static final ParameterizedTypeReference<AuthentikPaginated<AuthentikUser>> PAGINATED_USER_TYPE_REFERENCE =
    new ParameterizedTypeReference<>() {
    };

  private static final Logger LOG = LoggerFactory.getLogger(AuthentikRestClient.class);

  private static final String USERS_PATH = "/api/v3/core/users/";

  private final RestClient restClient;

  AuthentikRestClient(
    final AuthentikClientConnectionDetails authentikClientConnectionDetails,
    final RestClient.Builder restClientBuilder
  ) {
    LOG.info("Setting {} up: {url={}}", getClass().getSimpleName(), authentikClientConnectionDetails.url());
    this.restClient = restClientBuilder
      .clone()
      .baseUrl(authentikClientConnectionDetails.url())
      .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .defaultHeader(
        HttpHeaders.AUTHORIZATION,
        "Bearer %s".formatted(authentikClientConnectionDetails.serviceAccountToken()))
      .build();
  }

  /**
   * Finds a user by their uuid in Authentik.
   *
   * @param uuid the uuid of the user to find
   * @return an optional of AuthentikUser object
   */
  public Optional<AuthentikUser> getUser(final UUID uuid) {
    int nextPage = 0;
    Optional<AuthentikUser> user;
    AuthentikPaginated<AuthentikUser> paginated;

    do {
      final var currentPage = nextPage;
      final var paginatedResponse = Optional.ofNullable(restClient
        .get()
        .uri(uriBuilder -> {
          uriBuilder = uriBuilder
            .path(USERS_PATH)
            .queryParam("uuid", uuid);
          if (currentPage != 0) {
            uriBuilder = uriBuilder.queryParam("page", currentPage);
          }
          return uriBuilder.build();
        })
        .retrieve()
        .body(PAGINATED_USER_TYPE_REFERENCE));

      if (paginatedResponse.isEmpty()) {
        return Optional.empty();
      }

      paginated = paginatedResponse.get();
      user = paginated
        .results()
        .stream()
        .findAny();
    } while (user.isEmpty() && (
      nextPage =
        paginated
          .pagination()
          .next()) != 0);


    return user;
  }
}
