package com.github.stocky37.yougo.db;

import static java.util.Objects.requireNonNull;

import com.google.common.base.Converter;
import javax.annotation.ParametersAreNonnullByDefault;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.api.v1.ImmutableGo;

@ParametersAreNonnullByDefault
public class GoConverter extends Converter<Go, GoEntity> {

  @Override
  protected GoEntity doForward(Go go) {
    return new GoEntity()
        .setId(go.getId())
        .setGo(go.getGo())
        .setHref(go.getHref());
  }

  @Override
  protected Go doBackward(GoEntity goEntity) {
    return ImmutableGo.builder()
        .id(requireNonNull(goEntity.getId()))
        .go(requireNonNull(goEntity.getGo()))
        .href(requireNonNull(goEntity.getHref()))
        .build();
  }
}
