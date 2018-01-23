package com.github.stocky37.yougo;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import com.github.stocky37.yougo.config.YougoConfiguration;
import com.github.stocky37.yougo.core.AliasesService;
import com.github.stocky37.yougo.core.DAOAliasesService;
import com.github.stocky37.yougo.db.AliasConverter;
import com.github.stocky37.yougo.db.AliasesDAO;
import com.github.stocky37.yougo.http.v1.AliasesResource;
import com.github.stocky37.yougo.http.v1.GoResource;
import com.github.stocky37.yougo.http.v1.YougoResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class YougoApplication extends Application<YougoConfiguration> {

  private static final HibernateBundle<YougoConfiguration> hibernateBundle = new
      ScanningHibernateBundle<YougoConfiguration>("com.github.stocky37.yougo.db") {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(YougoConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };

  public static void main(String[] args) throws Exception {
    new YougoApplication().run("server", "config.yml");
  }

  @Override
  @SuppressWarnings("unchecked")
  public void initialize(Bootstrap bootstrap) {
    bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    bootstrap.addBundle(hibernateBundle);
  }

  @Override
  public void run(YougoConfiguration configuration, Environment environment) {
    final AliasesDAO dao = new AliasesDAO(hibernateBundle.getSessionFactory());
    final AliasesService service = new DAOAliasesService(dao, new AliasConverter());

    environment.jersey().register(
        new YougoResource(new AliasesResource(service), new GoResource(service))
    );

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
