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
	public List<GoEntity> getAliases() {
		return list(namedQuery("listGos"));
	}

	@SuppressWarnings("unchecked")
	public Optional<GoEntity> getAlias(String go) {
		return Optional.ofNullable(uniqueResult(namedQuery("getGo").setParameter("go", go)));
	}

	public GoEntity createAlias(GoEntity alias) {
		this.persist(alias);
		return alias;
	}
}
