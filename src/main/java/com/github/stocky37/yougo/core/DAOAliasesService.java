package com.github.stocky37.yougo.core;

import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import javax.annotation.ParametersAreNonnullByDefault;
import com.github.stocky37.yougo.api.v1.Alias;
import com.github.stocky37.yougo.db.AliasEntity;
import com.github.stocky37.yougo.db.AliasesDAO;

@ParametersAreNonnullByDefault
public class DAOAliasesService implements AliasesService {

  private final AliasesDAO dao;
  private final Converter<Alias, AliasEntity> converter;

  public DAOAliasesService(AliasesDAO dao,
      Converter<Alias, AliasEntity> converter) {
    this.dao = dao;
    this.converter = converter;
  }

  @Override
  public List<Alias> listAliases() {
    return ImmutableList.copyOf(converter.reverse().convertAll(dao.getAliases()));
  }

  @Override
  public Optional<Alias> getAlias(String alias) {
    return dao.getAlias(alias).map(a -> converter.reverse().convert(a));
  }

  @Override
  public Alias createAlias(Alias alias) {
    return converter.reverse().convert(dao.createAlias(converter.convert(alias)));
  }
}
