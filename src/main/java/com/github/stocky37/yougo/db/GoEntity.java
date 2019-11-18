package com.github.stocky37.yougo.db;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "gos")
public class GoEntity extends PanacheEntityBase {
	@Id
	public UUID id;
	public String go;
	public String href;

	@SuppressWarnings("ConstantConditions")
	@Nullable
	public static GoEntity findByName(String name) {
		return find("go", name).singleResult();
	}

	public UUID getId() {
		return id;
	}

	public GoEntity setId(UUID id) {
		this.id = id;
		return this;
	}

	public String getGo() {
		return go;
	}

	public GoEntity setGo(String go) {
		this.go = go;
		return this;
	}

	public String getHref() {
		return href;
	}

	public GoEntity setHref(String href) {
		this.href = href;
		return this;
	}

	public void merge(GoEntity entity) {
		this.go = entity.go;
		this.href = entity.href;
	}
}
