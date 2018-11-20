package com.github.stocky37.yougo.http.v1;

import io.dropwizard.hibernate.UnitOfWork;
import com.github.stocky37.yougo.api.v1.GoAPI;
import com.github.stocky37.yougo.core.GosService;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GoResource implements GoAPI {

	private final GosService service;

	public GoResource(GosService service) {
		this.service = service;
	}

	@UnitOfWork
	@Override
	public Response go(String alias) {
		return Response.seeOther(
				URI.create(service.getGoByName(alias).orElseThrow(NotFoundException::new).getHref())
		).build();
	}
}
