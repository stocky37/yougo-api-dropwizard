package com.github.stocky37.yougo.db;

import com.github.stocky37.yougo.api.v1.json.GoInputDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class GoInputDTOConverter implements Function<GoInputDTO, GoEntity> {
	@Override
	public GoEntity apply(GoInputDTO json) {
		final GoEntity entity = new GoEntity();
		entity.alias = json.alias;
		entity.href = json.href;
		return entity;
	}
}
