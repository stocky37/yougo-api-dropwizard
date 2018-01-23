package com.github.stocky37.yougo.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "aliases")
@NamedQueries({
  @NamedQuery(name = "listAliases", query = "FROM aliases"),
  @NamedQuery(name = "getAlias", query = "FROM aliases a WHERE a.alias = :alias")
})
public class AliasEntity {
  @Id
  private String id;
  private String alias;
  private String href;
  private String description;

  public String getId() {
    return id;
  }

  public AliasEntity setId(String id) {
    this.id = id;
    return this;
  }

  public String getAlias() {
    return alias;
  }

  public AliasEntity setAlias(String alias) {
    this.alias = alias;
    return this;
  }

  public String getHref() {
    return href;
  }

  public AliasEntity setHref(String href) {
    this.href = href;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AliasEntity setDescription(String description) {
    this.description = description;
    return this;
  }
}
