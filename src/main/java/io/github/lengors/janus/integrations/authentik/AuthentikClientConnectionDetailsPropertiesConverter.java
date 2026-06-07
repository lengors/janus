package io.github.lengors.janus.integrations.authentik;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts {@link AuthentikClientProperties} to {@link AuthentikClientConnectionDetailsProperties}.
 * <p>
 * This converter is used to transform the client properties into connection details properties required for
 * establishing a connection with the Authentik service.
 *
 * @author lengors
 */
@Component
class AuthentikClientConnectionDetailsPropertiesConverter
  implements Converter<AuthentikClientProperties, AuthentikClientConnectionDetailsProperties> {
  @Override
  public AuthentikClientConnectionDetailsProperties convert(final AuthentikClientProperties source) {
    return new AuthentikClientConnectionDetailsProperties(source.serviceAccountToken(), source.url());
  }
}
