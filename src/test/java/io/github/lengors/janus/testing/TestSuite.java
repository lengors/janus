package io.github.lengors.janus.testing;

import io.github.lengors.janus.testing.authentik.AuthentikClientConnectionDetailsConfiguration;
import io.github.lengors.janus.testing.wiremock.WireMockTestContainerConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This interface is used as the base for all test classes in the project.
 * <p>
 * It provides a set of default annotations to simplify the testing process.
 *
 * @author lengors
 */
public interface TestSuite {

  /**
   * This annotation is used to configure the test class with the default settings.
   */
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @Import({
    AuthentikClientConnectionDetailsConfiguration.class,
    WireMockTestContainerConfiguration.class
  })
  @SpringBootTest
  @ActiveProfiles("test")
  @AutoConfigureMockMvc
  @interface Defaults {

  }
}
