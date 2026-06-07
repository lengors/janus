package io.github.lengors.janus.domain.spring;

import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration(proxyBeanMethods = false)
class ClientCustomizerConfiguration {
  @Bean
  RestClientCustomizer clientCustomizer() {
    return builder -> builder.requestFactory(new SimpleClientHttpRequestFactory());
  }
}
