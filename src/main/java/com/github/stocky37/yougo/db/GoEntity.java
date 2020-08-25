package com.github.stocky37.yougo.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "gos")
public class GoEntity extends PanacheEntityBase {
	@Id
	@GeneratedValue
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	public UUID id;
	public String alias;
	public String href;
}
