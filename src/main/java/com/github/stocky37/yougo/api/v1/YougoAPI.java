package com.github.stocky37.yougo.api.v1;

import javax.ws.rs.Path;

@Path("v1")
public interface YougoAPI {
	@Path("gos")
	GosAPI gos();

	@Path("go")
	GoAPI go();
}
