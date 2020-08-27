package com.github.stocky37.yougo;

import com.github.stocky37.yougo.api.GoAPI;
import com.github.stocky37.yougo.api.GoOutputDTO;
import com.github.stocky37.yougo.db.GoRepository;
import io.quarkus.security.Authenticated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@Authenticated
@ApplicationScoped
public class GoResource implements GoAPI {
	private final GoRepository repository;

	@Inject
	public GoResource(GoRepository repository) {
		this.repository = repository;
	}

	@Override
	public GoOutputDTO go(String alias) {
		return this.repository.findByAlias(alias).orElseThrow(NotFoundException::new);
	}
}
