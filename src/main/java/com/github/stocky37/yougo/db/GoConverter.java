package com.github.stocky37.yougo.db;

import com.google.common.base.Converter;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.api.v1.ImmutableGo;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.requireNonNull;

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
