package com.github.stocky37.yougo;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.logging.LoggingFeature;

public class YougoApplication extends Application<YougoConfiguration> {

  public static void main(String[] args) throws Exception {
    new YougoApplication().run("server", "config.yml");
  }

  @Override
  public void initialize(Bootstrap bootstrap) {
    bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
  }

  @Override
  public void run(YougoConfiguration configuration, Environment environment) {
    environment.jersey().register(new YougoResource());
    environment.jersey().register(new LoggingFeature());
    final FilterRegistration.Dynamic cors =
        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    // Configure CORS parameters
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    // Add URL mapping
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
  }
}
