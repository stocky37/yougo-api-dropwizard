package com.github.stocky37.yougo.http.v1;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.api.v1.GosAPI;
import com.github.stocky37.yougo.core.GosService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GosResource implements GosAPI {

	private final GosService service;

	@Inject
	public GosResource(GosService service) {
		this.service = service;
	}

	@Override
	public List<Go> listGos() {
		return service.listGos();
	}

	@Override
	public Go addGo(@Valid @NotNull Go go) {
		return service.createGo(go);
	}

	@Override
	public Optional<Go> udpateGo(String id, @NotNull JsonMergePatch patch) {
		return service.updateGo(id, patch);
	}

	@Override
	public Optional<Go> getGo(@NotNull String id) {
		return service.getGo(id);
	}

	@Override
	public Optional<Go> deleteGo(@NotNull String id) {
		return service.deleteGo(id);
	}
}
