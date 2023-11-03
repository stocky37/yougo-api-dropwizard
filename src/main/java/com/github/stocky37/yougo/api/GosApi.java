package com.github.stocky37.yougo.api;

import com.github.stocky37.yougo.dto.GoInput;
import com.github.stocky37.yougo.dto.GoOutput;
import com.github.stocky37.yougo.util.MoreMediaTypes;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("gos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GosApi {
	@GET
	List<GoOutput> findAll();

	@POST
	Response create(@Valid @NotNull GoInput go);

	@GET
	@Path("{idOrAlias}")
	GoOutput find(@PathParam String idOrAlias);

	@PATCH
	@Path("{idOrAlias}")
	@Consumes({ MoreMediaTypes.JSON_MERGE_PATCH, MediaType.APPLICATION_JSON })
	GoOutput update(@PathParam String idOrAlias, @NotNull JsonObject patch);

	@DELETE
	@Path("{idOrAlias}")
	GoOutput delete(@PathParam String idOrAlias);
}
