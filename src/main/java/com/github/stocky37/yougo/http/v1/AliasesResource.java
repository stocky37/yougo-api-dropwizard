package com.github.stocky37.yougo.http.v1;

import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.github.stocky37.yougo.api.v1.Alias;
import com.github.stocky37.yougo.api.v1.AliasesAPI;
import com.github.stocky37.yougo.core.AliasesService;

public class AliasesResource implements AliasesAPI {

  private final AliasesService service;

  public AliasesResource(AliasesService service) {
    this.service = service;
  }

  @UnitOfWork
  @Override
  public List<Alias> listAliases() {
    return service.listAliases();
  }

  @UnitOfWork
  @Override
  public Alias addGo(@Valid @NotNull Alias alias) {
    return service.createAlias(alias);
  }
}
