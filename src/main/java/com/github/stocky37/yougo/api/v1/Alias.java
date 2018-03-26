package com.github.stocky37.yougo.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(passAnnotations = {NotBlank.class, URL.class, NotNull.class})
@JsonSerialize(as = ImmutableAlias.class)
@JsonDeserialize(as = ImmutableAlias.class)
public interface Alias {

  @Value.Default
  default String getId() {
    return UUID.randomUUID().toString();
  }

  @NotBlank
  String getAlias();

  @URL
  @NotNull
  String getHref();

  String getDescription();
}
