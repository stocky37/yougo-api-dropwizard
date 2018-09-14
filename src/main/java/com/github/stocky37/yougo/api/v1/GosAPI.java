package com.github.stocky37.yougo.api.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GosAPI {

	@GET
	List<Go> listGos();

	@POST
	Go addGo(@Valid @NotNull Go go);

	@DELETE
	@Path("{id}")
	Optional<Go> deleteGo(@NotNull @PathParam("id") String id);
}
