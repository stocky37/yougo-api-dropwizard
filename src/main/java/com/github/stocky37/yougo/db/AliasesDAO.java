package com.github.stocky37.yougo.db;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;


public class AliasesDAO extends AbstractDAO<AliasEntity> {

  public AliasesDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @SuppressWarnings("unchecked")
  public List<AliasEntity> getAliases() {
    return list(namedQuery("listAliases"));
  }

  @SuppressWarnings("unchecked")
  public Optional<AliasEntity> getAlias(String alias) {
    return Optional.ofNullable(uniqueResult(namedQuery("getAlias").setParameter("alias", alias)));
  }

  public AliasEntity createAlias(AliasEntity alias) {
    this.persist(alias);
    return alias;
  }
}
