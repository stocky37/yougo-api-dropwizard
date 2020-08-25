package com.github.stocky37.yougo.api;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class GoInputDTO {

	@NotNull
	@Pattern(regexp = "[\\w-]+")
	public String alias;

	@NotBlank
	@URL
	public String href;
}
