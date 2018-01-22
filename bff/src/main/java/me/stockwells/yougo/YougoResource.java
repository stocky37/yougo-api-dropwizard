package com.github.stocky37.yougo;

import io.dropwizard.testing.FixtureHelpers;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class YougoResource {

  private static final Logger log = LoggerFactory.getLogger(YougoResource.class);

  @GET
  @Path("gos/{user}")
  public String listGos() {
    return FixtureHelpers.fixture("com.github.stocky37/yougo/fixtures/gos.json");
  }

  @POST
  @Path("gos")
  public String addGo(String json) {
    log.info(json);
    return json;
  }
}
