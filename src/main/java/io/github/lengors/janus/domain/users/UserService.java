package io.github.lengors.janus.domain.users;

import java.util.UUID;

/**
 * Service for managing users.
 *
 * @author lengors
 */
public interface UserService {

  /**
   * Retrieves a user by their UUID.
   *
   * @param uuid the unique identifier of the user
   * @return the user with the specified UUID
   * @throws UserNotFoundException if the user with the given UUID is not found
   */
  User getUser(UUID uuid) throws UserNotFoundException;
}
