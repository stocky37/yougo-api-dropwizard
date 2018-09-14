package com.github.stocky37.yougo.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "gos")
@NamedQueries({
		@NamedQuery(name = "listGos", query = "FROM gos"),
		@NamedQuery(name = "getGoByName", query = "FROM gos g WHERE g.go = :go")
})
public class GoEntity {
	@Id
	private String id;
	private String go;
	private String href;

	public String getId() {
		return id;
	}

	public GoEntity setId(String id) {
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
}
