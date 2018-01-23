package com.github.stocky37.yougo.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.UUID;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableAlias.class)
@JsonDeserialize(as = ImmutableAlias.class)
public interface Alias {

  @Value.Default
  default String id() {
    return UUID.randomUUID().toString();
  }

  @NotBlank
  String alias();

  @URL
  String href();

  String description();
}
