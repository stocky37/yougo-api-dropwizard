package com.github.stocky37.yougo.http.v1;

import com.github.stocky37.yougo.api.v1.GoAPI;
import com.github.stocky37.yougo.api.v1.GosAPI;
import com.github.stocky37.yougo.api.v1.YougoAPI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class YougoResource implements YougoAPI {
	private final GosAPI gos;
	private final GoAPI go;

	@Inject
	public YougoResource(GosAPI gos, GoAPI go) {
		this.gos = gos;
		this.go = go;
	}

	@Override
	public GosAPI gos() {
		return gos;
	}

	@Override
	public GoAPI go() {
		return go;
	}
}
