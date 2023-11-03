package com.github.stocky37.yougo;

import com.github.stocky37.yougo.api.GosApi;
import com.github.stocky37.yougo.db.GoRepository;
import com.github.stocky37.yougo.dto.GoInput;
import com.github.stocky37.yougo.dto.GoOutput;
import io.quarkus.security.Authenticated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;

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
	public GoOutput find(String idOrAlias) {
		return repository.findByIdOrAlias(idOrAlias).orElseThrow(NotFoundException::new);
	}

	@Transactional
	@Override
	public GoOutput delete(String idOrAlias) {
		return repository.delete(idOrAlias).orElseThrow(NotFoundException::new);
	}

	@Transactional
	@Override
	public GoOutput update(String idOrAlias, @NotNull JsonObject patch) {
		return repository.update(idOrAlias, patch).orElseThrow(NotFoundException::new);
	}
}
