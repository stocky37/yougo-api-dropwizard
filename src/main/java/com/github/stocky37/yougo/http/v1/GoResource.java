package com.github.stocky37.yougo.http.v1;

import io.dropwizard.hibernate.UnitOfWork;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.github.stocky37.yougo.api.v1.GoAPI;
import com.github.stocky37.yougo.core.AliasesService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GoResource implements GoAPI {

  private final AliasesService service;

  public GoResource(AliasesService service) {
    this.service = service;
  }

  @UnitOfWork
  @Override
  public Response go(String alias) {
    return Response.seeOther(
        URI.create(service.getAlias(alias).orElseThrow(NotFoundException::new).getHref())
    ).build();
  }
}
