package com.github.stocky37.yougo.db;

import com.github.stocky37.yougo.GosResource;
import com.github.stocky37.yougo.api.GoInputDTO;
import com.github.stocky37.yougo.api.GoOutputDTO;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonMergePatch;
import javax.json.JsonObject;

@ApplicationScoped
public class GoRepository implements PanacheRepositoryBase<GoEntity, UUID> {
	private static final Logger log = Logger.getLogger(GosResource.class.getName());
	private static final Function<GoEntity, GoOutputDTO> toDTO = entity -> {
		GoOutputDTO dto = new GoOutputDTO();
		dto.id = entity.id.toString();
		dto.alias = entity.alias;
		dto.href = entity.href;
		return dto;
	};
	private static final Function<GoInputDTO, GoEntity> fromDTO = dto -> {
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

	public GoOutputDTO create(final GoInputDTO go) {
		final GoEntity entity = fromDTO.apply(go);
		entity.user = getUserId();
		persist(entity);
		return toDTO.apply(entity);
	}

	public List<GoOutputDTO> getAll() {
		return filterByUser(findAll(Sort.ascending("alias")))
			.stream()
			.map(toDTO)
			.collect(Collectors.toList());
	}

	public Optional<GoOutputDTO> findById(final String id) {
		return findEntityById(id).map(toDTO);
	}

	public Optional<GoOutputDTO> findByAlias(final String alias) {
		return filterByUser(find("#Go.findByAlias", alias)).singleResultOptional().map(toDTO);
	}

	public Optional<GoOutputDTO> deleteById(final String id) {
		final Optional<GoEntity> entity = findEntityById(id);
		entity.ifPresent(PanacheEntityBase::delete);
		return entity.map(toDTO);
	}

	public Optional<GoOutputDTO> updateGo(String id, JsonObject patch) {
		return findEntityById(id)
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

	private Optional<GoEntity> findEntityById(String id) {
		try {
			return filterByUser(find("#Go.findById", UUID.fromString(id))).singleResultOptional();
		} catch (IllegalArgumentException e) {
			// handle invalid UUIDs
			log.finer("Invalid id " + id);
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
