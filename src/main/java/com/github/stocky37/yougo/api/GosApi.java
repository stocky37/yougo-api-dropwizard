package com.github.stocky37.yougo.api;

import com.github.stocky37.yougo.dto.GoInput;
import com.github.stocky37.yougo.dto.GoOutput;
import com.github.stocky37.yougo.util.MoreMediaTypes;
import java.util.List;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
