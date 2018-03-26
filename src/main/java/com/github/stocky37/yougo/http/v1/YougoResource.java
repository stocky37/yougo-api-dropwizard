package com.github.stocky37.yougo.http.v1;

import com.github.stocky37.yougo.api.v1.AliasesAPI;
import com.github.stocky37.yougo.api.v1.GoAPI;
import com.github.stocky37.yougo.api.v1.YougoAPI;

public class YougoResource implements YougoAPI {
  private final AliasesAPI aliases;
  private final GoAPI go;

  public YougoResource(AliasesAPI aliases, GoAPI go) {
    this.aliases = aliases;
    this.go = go;
  }

  @Override
  public AliasesAPI aliases() {
    return aliases;
  }

  @Override
  public GoAPI go() {
    return go;
  }
}
