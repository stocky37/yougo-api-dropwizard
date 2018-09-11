package com.github.stocky37.yougo.http.v1;

import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.api.v1.GosAPI;
import com.github.stocky37.yougo.core.GosService;

public class GosResource implements GosAPI {

  private final GosService service;

  public GosResource(GosService service) {
    this.service = service;
  }

  @UnitOfWork
  @Override
  public List<Go> listGos() {
    return service.listGos();
  }

  @UnitOfWork
  @Override
  public Go addGo(@Valid @NotNull Go go) {
    return service.createGo(go);
  }
}
