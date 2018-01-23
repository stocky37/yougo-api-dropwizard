package com.github.stocky37.yougo.api.v1;

import javax.ws.rs.Path;

@Path("v1")
public interface YougoAPI {
  @Path("aliases")
  AliasesAPI aliases();

  @Path("go")
  GoAPI go();
}
