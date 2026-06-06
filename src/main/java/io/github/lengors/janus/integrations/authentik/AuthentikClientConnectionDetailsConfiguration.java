package io.github.lengors.janus.integrations.authentik;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

@AutoConfiguration
class AuthentikClientConnectionDetailsConfiguration {
  @Bean
  @ConditionalOnBean(AuthentikClientProperties.class)
  @ConditionalOnMissingBean(AuthentikClientConnectionDetails.class)
  AuthentikClientConnectionDetails authentikClientConnectionDetails(
    final AuthentikClientProperties authentikClientProperties,
    final ConversionService conversionService
  ) {
    return Optional
      .ofNullable(
        conversionService.convert(authentikClientProperties, AuthentikClientConnectionDetailsProperties.class))
      .orElseThrow();
  }
}
