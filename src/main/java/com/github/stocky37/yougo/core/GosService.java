package com.github.stocky37.yougo.core;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.github.stocky37.yougo.api.v1.Go;

import java.util.List;
import java.util.Optional;

public interface GosService {
	List<Go> listGos();
	Go createGo(Go go);
	Optional<Go> getGo(String id);
	Optional<Go> getGoByName(String go);
	Optional<Go> updateGo(String id, JsonMergePatch patch);
	Optional<Go> deleteGo(String id);
}
