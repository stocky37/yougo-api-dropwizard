package com.github.stocky37.yougo.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.google.common.base.Converter;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import io.dropwizard.jackson.Jackson;
import com.github.stocky37.yougo.api.v1.Go;
import com.github.stocky37.yougo.db.GoEntity;
import com.github.stocky37.yougo.db.GosDAO;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class DAOGosService implements GosService {

	private final GosDAO dao;
	private final Converter<Go, GoEntity> toEntity;
	private final Converter<GoEntity, Go> fromEntity;
	private final ObjectMapper om;

	public DAOGosService(GosDAO dao, Converter<Go, GoEntity> entityConverter) {
		this(dao, entityConverter, Jackson.newObjectMapper());
	}

	public DAOGosService(GosDAO dao, Converter<Go, GoEntity> entityConverter, ObjectMapper om) {
		this.dao = dao;
		this.toEntity = entityConverter;
		this.fromEntity = entityConverter.reverse();
		this.om = om;
	}

	@Override
	public List<Go> listGos() {
		return ImmutableList.copyOf(fromEntity.convertAll(dao.getGos()));
	}

	@Override
	public Go createGo(Go go) {
		return fromEntity.convert(dao.createAlias(toEntity.convert(go)));
	}

	@Override
	public Optional<Go> getGo(String id) {
		return dao.getGo(id).map(fromEntity);
	}

	@Override
	public Optional<Go> getGoByName(String go) {
		return dao.getGoByName(go).map(fromEntity);
	}

	@Override
	public Optional<Go> updateGo(String id, JsonMergePatch patch) {
		final Optional<GoEntity> existingEntity = dao.getGo(id);
		if(existingEntity.isPresent()) {
			final GoEntity entity = existingEntity.get();
			final Go existing = fromEntity.convert(entity);
			final Go patched = patch(Objects.requireNonNull(existing), patch);
			entity.merge(toEntity.convert(patched));
			return dao.updateGo(entity).map(fromEntity);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Go> deleteGo(String id) {
		return dao.deleteGo(id).map(fromEntity);
	}

	protected Go patch(Go existing, JsonMergePatch patch) {
		return fromNode(applyPatch(patch, om.valueToTree(existing)));
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
