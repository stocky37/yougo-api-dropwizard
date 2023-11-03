package com.github.stocky37.yougo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public class GoInput {
	@NotNull
	@Pattern(regexp = "[\\w-]+")
	public String alias;

	@NotBlank
	@URL
	public String href;
}
