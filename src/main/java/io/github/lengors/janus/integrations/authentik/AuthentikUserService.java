package io.github.lengors.janus.integrations.authentik;

import io.github.lengors.janus.domain.users.UserNotFoundException;
import io.github.lengors.janus.domain.users.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class AuthentikUserService implements UserService {
  private final AuthentikRestClient authentikRestClient;

  AuthentikUserService(final AuthentikRestClient authentikRestClient) {
    this.authentikRestClient = authentikRestClient;
  }

  @Override
  public AuthentikUser getUser(final UUID uuid) throws UserNotFoundException {
    return authentikRestClient
      .getUser(uuid)
      .orElseThrow(() -> new UserNotFoundException(uuid));
  }
}
