package com.github.stocky37.yougo.api;

import com.github.stocky37.yougo.dto.GoInputDTO;
import com.github.stocky37.yougo.dto.GoOutputDTO;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("gos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GosAPI {
	@GET
	List<GoOutputDTO> findAll();

	@POST
	Response create(@Valid @NotNull GoInputDTO go);

	@GET
	@Path("{alias}")
	GoOutputDTO find(@PathParam("alias") String alias);

	@PATCH
	@Path("{alias}")
	@Consumes({ MoreMediaTypes.JSON_MERGE_PATCH, MediaType.APPLICATION_JSON })
	GoOutputDTO update(@PathParam("alias") String alias, @NotNull JsonObject patch);

	@DELETE
	@Path("{alias}")
	GoOutputDTO delete(@PathParam("alias") String alias);
}
