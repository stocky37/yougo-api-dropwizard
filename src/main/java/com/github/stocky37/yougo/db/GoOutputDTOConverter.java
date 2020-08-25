package com.github.stocky37.yougo.db;

import com.github.stocky37.yougo.api.v1.json.GoOutputDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class GoOutputDTOConverter implements Function<GoEntity, GoOutputDTO> {

	@Override
	public GoOutputDTO apply(GoEntity entity) {
		GoOutputDTO dto = new GoOutputDTO();
		dto.id = entity.id.toString();
		dto.alias = entity.alias;
		dto.href = entity.href;
		return dto;
	}
}
