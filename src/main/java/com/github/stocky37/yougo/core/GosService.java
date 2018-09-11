package com.github.stocky37.yougo.core;

import java.util.List;
import java.util.Optional;
import com.github.stocky37.yougo.api.v1.Go;

public interface GosService {

  List<Go> listGos();

  Optional<Go> getGo(String go);

  Go createGo(Go go);
}
