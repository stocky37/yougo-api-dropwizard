package com.github.stocky37.yougo.http.v1;

import com.github.stocky37.yougo.api.v1.GoAPI;
import com.github.stocky37.yougo.core.GosService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@ApplicationScoped
public class GoResource implements GoAPI {

	private final GosService service;

	@Inject
	public GoResource(GosService service) {
		this.service = service;
	}

	@Override
	public Response go(String alias) {
		return Response.seeOther(
				URI.create(service.getGoByName(alias).orElseThrow(NotFoundException::new).getHref())
		).build();
	}
}
