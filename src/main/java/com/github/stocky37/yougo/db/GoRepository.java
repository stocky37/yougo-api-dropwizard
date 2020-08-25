package com.github.stocky37.yougo.db;

import com.github.stocky37.yougo.api.v1.json.GoInputDTO;
import com.github.stocky37.yougo.api.v1.json.GoOutputDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
@ParametersAreNonnullByDefault
public class GoRepository implements PanacheRepositoryBase<GoEntity, UUID> {

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

	public GoOutputDTO create(final GoInputDTO go) {
		final GoEntity entity = fromDTO.apply(go);
		persist(entity);
		return toDTO.apply(entity);
	}

	public List<GoOutputDTO> getAll() {
		return findAll(Sort.ascending("alias")).stream().map(toDTO).collect(Collectors.toList());
	}

	public Optional<GoOutputDTO> findById(final String id) {
		return findEntityById(id).map(toDTO);
	}

	public Optional<GoOutputDTO> deleteById(final String id) {
		Optional<GoEntity> entity = findEntityById(id);
		if(entity.isPresent()) {
			final boolean deleted = deleteById(entity.get().id);
			if(!deleted) {
				return Optional.empty();
			}
		}
		return entity.map(toDTO);

	}

	private Optional<GoEntity> findEntityById(String id) {
		try {
			return findByIdOptional(UUID.fromString(id));
		} catch (IllegalArgumentException e) {
			// handle invalid UUIDs
			return Optional.empty();
		}
	}

	public Optional<GoOutputDTO> updateGo(String id, JsonObject patch) {
		return this.findEntityById(id).map(e -> {
			if(patch.containsKey("alias")) {
				e.alias = patch.getString("alias");
			}
			if(patch.containsKey("href")) {
				e.href = patch.getString("href");
			}
			return e;
		}).map(toDTO);
	}


}
