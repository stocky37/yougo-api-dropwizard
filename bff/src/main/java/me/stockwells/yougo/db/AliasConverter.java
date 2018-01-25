package com.github.stocky37.yougo.db;

import static java.util.Objects.requireNonNull;

import com.google.common.base.Converter;
import javax.annotation.ParametersAreNonnullByDefault;
import com.github.stocky37.yougo.api.v1.Alias;
import com.github.stocky37.yougo.api.v1.ImmutableAlias;

@ParametersAreNonnullByDefault
public class AliasConverter extends Converter<Alias, AliasEntity> {

  @Override
  protected AliasEntity doForward(Alias alias) {
    return new AliasEntity()
        .setId(alias.getId())
        .setAlias(alias.getAlias())
        .setHref(alias.getHref())
        .setDescription(alias.getDescription());
  }

  @Override
  protected Alias doBackward(AliasEntity aliasEntity) {
    return ImmutableAlias.builder()
        .id(requireNonNull(aliasEntity.getId()))
        .alias(requireNonNull(aliasEntity.getAlias()))
        .href(requireNonNull(aliasEntity.getHref()))
        .description(requireNonNull(aliasEntity.getDescription()))
        .build();
  }
}
