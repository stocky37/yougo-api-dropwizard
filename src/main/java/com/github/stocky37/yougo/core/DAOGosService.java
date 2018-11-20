package com.github.stocky37.yougo.core;

import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.db.GoEntity;
import com.github.stocky37.yougo.db.GosDAO;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class DAOGosService implements GosService {

	private final GosDAO dao;
	private final Converter<Go, GoEntity> toEntity;
	private final Converter<GoEntity, Go> fromEntity;

	public DAOGosService(GosDAO dao, Converter<Go, GoEntity> toEntity) {
		this.dao = dao;
		this.toEntity = toEntity;
		this.fromEntity = toEntity.reverse();
	}

	@Override
	public List<Go> listGos() {
		return ImmutableList.copyOf(fromEntity.convertAll(dao.getGos()));
	}

	@Override
	public Go createGo(Go go) {
		return fromEntity.convert(dao.createAlias(toEntity.convert(go)));
	}

	@Override
	public Optional<Go> getGo(String id) {
		return dao.getGo(id).map(fromEntity);
	}

	@Override
	public Optional<Go> getGoByName(String go) {
		return dao.getGoByName(go).map(fromEntity);
	}

	@Override
	public Optional<Go> deleteGo(String id) {
		return dao.deleteGo(id).map(fromEntity);
	}
}
