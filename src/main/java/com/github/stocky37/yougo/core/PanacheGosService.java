package com.github.stocky37.yougo.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.db.GoEntity;
import com.google.common.base.Converter;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
@ParametersAreNonnullByDefault
public class PanacheGosService implements GosService {

	private final Converter<Go, GoEntity> converter;
	private final ObjectMapper om;

	@Inject
	public PanacheGosService(Converter<Go, GoEntity> converter, ObjectMapper om) {
		this.converter = converter;
		this.om = om;
	}

	@Override
	@Transactional
	public List<Go> listGos() {
		return ImmutableList.copyOf(
			GoEntity.<GoEntity>streamAll()
				.map(converter.reverse())
				.collect(Collectors.toList())
		);
	}

	@Override
	@Transactional
	public Go createGo(Go go) {
		final GoEntity entity = Objects.requireNonNull(converter.convert(go));
		entity.persistAndFlush();
		return converter.reverse().convert(entity);
	}

	@Override
	@Transactional
	public Optional<Go> getGo(String id) {
		return findGoById(id).map(converter.reverse());
	}

	@Override
	@Transactional
	public Optional<Go> getGoByName(String go) {
		return Optional.ofNullable(GoEntity.findByName(go)).map(converter.reverse());
	}

	@Override
	@Transactional
	public Optional<Go> updateGo(String id, JsonMergePatch patch) {
		final Optional<GoEntity> existingEntity = this.findGoById(id);
		if(existingEntity.isPresent()) {
			final GoEntity entity = existingEntity.get();
			final Go existing = converter.reverse().convert(entity);
			final Go patched = patch(Objects.requireNonNull(existing), patch);
			entity.merge(Objects.requireNonNull(converter.convert(patched)));
			entity.persist();
			return Optional.ofNullable(converter.reverse().convert(entity));
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public Optional<Go> deleteGo(String id) {
		final Optional<GoEntity> entity = findGoById(id);
		entity.ifPresent(PanacheEntityBase::delete);
		return entity.map(converter.reverse());
	}

	private Go patch(Go existing, JsonMergePatch patch) {
		return fromNode(applyPatch(patch, om.valueToTree(existing)));
	}

	private Optional<GoEntity> findGoById(String id) {
		return Optional.ofNullable(GoEntity.findById(UUID.fromString(id)));
	}

	private JsonNode applyPatch(JsonMergePatch patch, JsonNode node) {
		try {
			return patch.apply(node);
		} catch (JsonPatchException e) {
			throw Throwables.propagate(e);
		}
	}

	private Go fromNode(JsonNode node) {
		try {
			return om.treeToValue(node, Go.class);
		} catch (JsonProcessingException e) {
			throw Throwables.propagate(e);
		}
	}
}
