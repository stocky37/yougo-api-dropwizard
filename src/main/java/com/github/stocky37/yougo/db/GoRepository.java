package com.github.stocky37.yougo.db;

import com.github.stocky37.yougo.GosResource;
import com.github.stocky37.yougo.dto.GoInput;
import com.github.stocky37.yougo.dto.GoOutput;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.security.identity.SecurityIdentity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GoRepository implements PanacheRepositoryBase<GoEntity, UUID> {
	private static final Logger log = LoggerFactory.getLogger(GoRepository.class);
	private static final Function<GoEntity, GoOutput> toDTO = entity -> {
		GoOutput dto = new GoOutput();
		dto.id = entity.id.toString();
		dto.alias = entity.alias;
		dto.href = entity.href;
		return dto;
	};
	private static final Function<GoInput, GoEntity> fromDTO = dto -> {
		final GoEntity entity = new GoEntity();
		entity.alias = dto.alias;
		entity.href = dto.href;
		return entity;
	};

	private final SecurityIdentity identity;

	@Inject
	public GoRepository(SecurityIdentity identity) {
		this.identity = identity;
	}

	public GoOutput create(final GoInput go) {
		final GoEntity entity = fromDTO.apply(go);
		entity.user = getUserId();
		persist(entity);
		return toDTO.apply(entity);
	}

	public List<GoOutput> findAllFiltered() {
		return filterByUser(findAll(Sort.ascending("alias")))
			.stream()
			.map(toDTO)
			.collect(Collectors.toList());
	}

	public Optional<GoOutput> findById(final String id) {
		return findEntityById(id).map(toDTO);
	}

	public Optional<GoOutput> findByAlias(final String alias) {
		return findEntityByAlias(alias).map(toDTO);
	}

	public Optional<GoOutput> delete(final String alias) {
		final Optional<GoEntity> entity = findEntityByAlias(alias);
		entity.ifPresent(PanacheEntityBase::delete);
		return entity.map(toDTO);
	}

	public Optional<GoOutput> update(String id, JsonObject patch) {
		return findEntityByAlias(id)
			.map(
				e -> {
					if (patch.containsKey("alias")) {
						e.alias = patch.getString("alias");
					}
					if (patch.containsKey("href")) {
						e.href = patch.getString("href");
					}
					return e;
				}
			)
			.map(toDTO);
	}

	private Optional<GoEntity> findEntityByAlias(String alias) {
		return filterByUser(find("#Go.findByAlias", alias)).singleResultOptional();
	}

	private Optional<GoEntity> findEntityById(String id) {
		try {
			return filterByUser(find("#Go.findById", UUID.fromString(id))).singleResultOptional();
		} catch (IllegalArgumentException e) {
			// handle invalid UUIDs
			log.debug("Invalid id " + id);
			return Optional.empty();
		}
	}

	private <T> PanacheQuery<T> filterByUser(PanacheQuery<T> query) {
		return query.filter("user", Parameters.with("user", getUserId()));
	}

	private String getUserId() {
		return identity.getPrincipal().getName();
	}
}
