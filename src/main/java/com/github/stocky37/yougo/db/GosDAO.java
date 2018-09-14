package com.github.stocky37.yougo.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


public class GosDAO extends AbstractDAO<GoEntity> {

	public GosDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<GoEntity> getGos() {
		return list(namedQuery(GoEntity.Queries.LIST_GOS));
	}

	@SuppressWarnings("unchecked")
	public Optional<GoEntity> getGoByName(String go) {
		return Optional.ofNullable(uniqueResult(namedQuery(GoEntity.Queries.BY_NAME).setParameter("go", go)));
	}

	public Optional<GoEntity> deleteGo(String id) {
		final Optional<GoEntity> go = Optional.ofNullable(get(id));
		go.ifPresent(entity -> currentSession().delete(entity));
		return go;
	}

	public GoEntity createAlias(GoEntity alias) {
		this.persist(alias);
		return alias;
	}
}
