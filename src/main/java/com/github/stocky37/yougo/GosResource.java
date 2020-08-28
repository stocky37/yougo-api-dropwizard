package com.github.stocky37.yougo;

import com.github.stocky37.yougo.api.GosApi;
import com.github.stocky37.yougo.db.GoRepository;
import com.github.stocky37.yougo.dto.GoInput;
import com.github.stocky37.yougo.dto.GoOutput;
import io.quarkus.security.Authenticated;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Authenticated
@ApplicationScoped
public class GosResource implements GosApi {
	@Context
	UriInfo uriInfo;

	private final GoRepository repository;

	@Inject
	public GosResource(GoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public List<GoOutput> findAll() {
		return repository.findAllFiltered();
	}

	@Transactional
	@Override
	public Response create(@Valid @NotNull GoInput go) {
		final GoOutput created = repository.create(go);
		return Response
			.created(uriInfo.getAbsolutePathBuilder().path(GosApi.class, "find").build(created.alias))
			.entity(created)
			.build();
	}

	@Transactional
	@Override
	public GoOutput find(String alias) {
		return repository.findByAlias(alias).orElseThrow(NotFoundException::new);
	}

	@Transactional
	@Override
	public GoOutput delete(String alias) {
		return repository.delete(alias).orElseThrow(NotFoundException::new);
	}

	@Transactional
	@Override
	public GoOutput update(String alias, @NotNull JsonObject patch) {
		return repository.update(alias, patch).orElseThrow(NotFoundException::new);
	}
}
