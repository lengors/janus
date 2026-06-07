package io.github.lengors.janus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
class JanusApplication {
  static void main(final String[] args) {
    SpringApplication.run(JanusApplication.class, args);
  }
}
