package com.github.stocky37.yougo.api.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/go")
public interface GoAPI {
	@GET
	@Path("{go}")
	Response go(@PathParam("go") String go);
}
