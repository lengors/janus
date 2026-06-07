package io.github.lengors.janus.domain.users;

import java.util.UUID;

/**
 * Exception indicating that a user with a specified UUID could not be found.
 * <p>
 * This exception is typically thrown when an operation attempts to retrieve a user from persistent storage or an
 * external system, and the user cannot be located.
 *
 * @author lengors
 */
public class UserNotFoundException extends Exception {
  private static final String MESSAGE = "User {uuid=%s} not found";

  /**
   * The unique identifier of the user that could not be found.
   */
  private final UUID uuid;

  /**
   * Constructs a new {@code UserNotFoundException} with the specified {@code UUID}. This exception indicates that a
   * user with the given unique identifier could not be found.
   *
   * @param uuid the unique identifier of the user that could not be found
   */
  public UserNotFoundException(final UUID uuid) {
    super(MESSAGE.formatted(uuid));
    this.uuid = uuid;
  }

  /**
   * Constructs a new {@code UserNotFoundException} with the specified {@code UUID} and a cause. This exception
   * indicates that a user with the given unique identifier could not be found, and provides an underlying cause for the
   * exception.
   *
   * @param uuid  the unique identifier of the user that could not be found
   * @param cause the cause of this exception
   */
  public UserNotFoundException(
    final UUID uuid,
    final Throwable cause
  ) {
    super(MESSAGE.formatted(uuid), cause);
    this.uuid = uuid;
  }

  /**
   * Returns the unique identifier of the user that could not be found.
   *
   * @return the UUID of the user
   */
  public UUID getUuid() {
    return uuid;
  }
}
