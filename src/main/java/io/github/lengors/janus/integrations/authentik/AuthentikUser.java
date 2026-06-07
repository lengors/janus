package io.github.lengors.janus.integrations.authentik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lengors.janus.domain.users.User;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jspecify.annotations.Nullable;

/**
 * Represents an Authentik user.
 * <p>
 * This record encapsulates the details of a user in Authentik, including their avatar URL. It is used to transfer user
 * information between the application and Authentik.
 *
 * @param avatar the URL of the user's avatar image, cannot be null
 * @author lengors
 */
@DefaultQualifier(Nullable.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthentikUser(
  @JsonProperty("avatar")
  @NotNull
  String avatar
) implements User {

}
