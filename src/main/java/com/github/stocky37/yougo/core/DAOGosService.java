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
	private final Converter<Go, GoEntity> converter;

	public DAOGosService(GosDAO dao,
											 Converter<Go, GoEntity> converter) {
		this.dao = dao;
		this.converter = converter;
	}

	@Override
	public List<Go> listGos() {
		return ImmutableList.copyOf(converter.reverse().convertAll(dao.getGos()));
	}

	@Override
	public Optional<Go> getGo(String go) {
		return dao.getGoByName(go).map(a -> converter.reverse().convert(a));
	}

	@Override
	public Go createGo(Go go) {
		return converter.reverse().convert(dao.createAlias(converter.convert(go)));
	}
}
