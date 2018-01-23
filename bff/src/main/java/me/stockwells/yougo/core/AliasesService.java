package com.github.stocky37.yougo.core;

import java.util.List;
import java.util.Optional;
import com.github.stocky37.yougo.api.v1.Alias;

public interface AliasesService {

  List<Alias> listAliases();

  Optional<Alias> getAlias(String alias);

  Alias createAlias(Alias alias);
}
