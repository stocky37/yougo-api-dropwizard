package com.github.stocky37.yougo.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.URL;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value.Immutable
//@Value.Style(passAnnotations = {NotBlank.class, URL.class, NotNull.class})
@JsonSerialize(as = ImmutableGo.class)
@JsonDeserialize(as = ImmutableGo.class)
public interface Go {

	@Value.Default
	default String getId() {
		return UUID.randomUUID().toString();
	}

	@NotBlank
	String getGo();

	@URL
	@NotNull
	String getHref();
}
