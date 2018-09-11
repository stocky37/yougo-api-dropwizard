package com.github.stocky37.yougo;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.github.stocky37.yougo.config.CORSBundle;
import com.github.stocky37.yougo.config.YougoConfiguration;
import com.github.stocky37.yougo.core.GosService;
import com.github.stocky37.yougo.core.DAOGosService;
import com.github.stocky37.yougo.db.GoConverter;
import com.github.stocky37.yougo.db.GosDAO;
import com.github.stocky37.yougo.http.v1.GosResource;
import com.github.stocky37.yougo.http.v1.GoResource;
import com.github.stocky37.yougo.http.v1.YougoResource;

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
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
				new ResourceConfigurationSourceProvider(),
				new EnvironmentVariableSubstitutor(false)
		));
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new CORSBundle());
	}

	@Override
	public void run(YougoConfiguration configuration, Environment environment) {
		final GosDAO dao = new GosDAO(hibernateBundle.getSessionFactory());
		final GosService service = new DAOGosService(dao, new GoConverter());

		environment.jersey().register(
				new YougoResource(new GosResource(service), new GoResource(service))
		);
	}
}
