package io.github.lengors.janus.api.users;

import io.github.lengors.janus.domain.users.User;
import io.github.lengors.janus.domain.users.UserNotFoundException;
import io.github.lengors.janus.domain.users.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.checkerframework.framework.qual.TypeUseLocation;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.UUID;
import java.util.regex.Pattern;

@Validated
@DefaultQualifier(value = Nullable.class, locations = TypeUseLocation.PARAMETER)
@RestController
@RequestMapping("/users")
class UserController {
  private static final Pattern DATA_BLOB_PATTERN = Pattern.compile("^data:image/[^;]+;base64,.*$");

  private final UserService userService;

  UserController(final @NotNull UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{uuid}/avatar")
  ResponseEntity<String> getAvatar(final @PathVariable("uuid") @Valid @NotNull UUID uuid) {
    final User user;
    try {
      user = userService.getUser(uuid);
    } catch (final UserNotFoundException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    final var userAvatar = user.avatar();
    final var dataBlobMatcher = DATA_BLOB_PATTERN.matcher(userAvatar);

    if (dataBlobMatcher.matches()) {
      return ResponseEntity.ok(userAvatar);
    }

    return ResponseEntity
      .status(HttpStatus.FOUND)
      .location(URI.create(userAvatar))
      .build();
  }
}
