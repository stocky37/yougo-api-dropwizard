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
