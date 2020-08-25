package com.github.stocky37.yougo;

import com.github.stocky37.yougo.api.GosAPI;
import com.github.stocky37.yougo.api.GoInputDTO;
import com.github.stocky37.yougo.api.GoOutputDTO;
import com.github.stocky37.yougo.db.GoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
public class GosResource implements GosAPI {

	@Context UriInfo uriInfo;

	private final GoRepository repository;

	@Inject
	public GosResource(GoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public List<GoOutputDTO> findAll() {
		return repository.getAll();
	}

	@Transactional
	@Override
	public Response create(@Valid @NotNull GoInputDTO go) {
		final GoOutputDTO created = repository.create(go);
		return Response.created(uriInfo.getAbsolutePathBuilder()
			.path(GosAPI.class, "findById")
			.build(created.id)).entity(created).build();
	}

	@Transactional
	@Override
	public GoOutputDTO findById(String id) {
		return repository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Transactional
	@Override
	public GoOutputDTO delete(String id) {
		return repository.deleteById(id).orElseThrow(NotFoundException::new);
	}

	@Transactional
	@Override
	public GoOutputDTO update(String id, @NotNull JsonObject patch) {
		return repository.updateGo(id, patch).orElseThrow(NotFoundException::new);
	}

}
