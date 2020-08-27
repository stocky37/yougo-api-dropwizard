package com.github.stocky37.yougo.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

@Table(name = "gos")
@Entity(name = "Go")
@NamedQuery(name = "Go.findById", query = "from Go where id = ?1")
@NamedQuery(name = "Go.findByAlias", query = "from Go where alias = ?1")
@FilterDef(name = "user", parameters = @ParamDef(name = "user", type = "string"))
@Filter(name = "user", condition = "user = :user")
public class GoEntity extends PanacheEntityBase {
	@Id
	@GeneratedValue
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	public UUID id;

	public String alias;
	public String href;
	public String user;
}
