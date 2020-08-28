package com.github.stocky37.yougo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public class GoInputDTO {
	@NotNull
	@Pattern(regexp = "[\\w-]+")
	public String alias;

	@NotBlank
	@URL
	public String href;
}
