package com.github.stocky37.yougo.api.v1;

import com.github.stocky37.yougo.api.v1.json.GoInputDTO;
import com.github.stocky37.yougo.api.v1.json.GoOutputDTO;
import com.github.stocky37.yougo.util.MoreMediaTypes;

import javax.json.JsonMergePatch;
import javax.json.JsonObject;
import javax.json.JsonValue;
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
import java.util.List;

@Path("/v1/gos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GosAPI {

	@GET
	List<GoOutputDTO> findAll();

	@POST
	Response create(@Valid @NotNull GoInputDTO go);

	@GET
	@Path("{id}")
	GoOutputDTO findById(@PathParam("id") String id);

	@PATCH
	@Path("{id}")
	@Consumes({MoreMediaTypes.JSON_MERGE_PATCH, MediaType.APPLICATION_JSON})
	GoOutputDTO update(@PathParam("id") String id, @NotNull JsonObject patch);

	@DELETE
	@Path("{id}")
	GoOutputDTO delete(@PathParam("id") String id);
}
