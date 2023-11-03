package com.github.stocky37.yougo.db;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.sql.Types;
import java.util.UUID;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ParamDef;

@Table(name = "gos")
@Entity(name = "Go")
@NamedQuery(name = "Go.findById", query = "from Go where id = ?1")
@NamedQuery(name = "Go.findByAlias", query = "from Go where alias = ?1")
@FilterDef(name = "user", parameters = @ParamDef(name = "user", type = String.class))
@Filter(name = "user", condition = "userid = :user")
@SuppressFBWarnings("UrF")
public class GoEntity extends PanacheEntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public UUID id;

	public String alias;

	@JdbcTypeCode(Types.LONGVARCHAR)
	public String href;

	@Column(name = "userid")
	public String user;
}
