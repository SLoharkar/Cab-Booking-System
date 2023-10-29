package com.example.cbs.models.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CabBookDTO {

	private Integer cabBookId;

	@NotNull(message = "customerNumber should not be null")
	private String customerNumber;

	@NotNull(message = "customerName should not be null")
	private String customerName;

	@NotNull(message = "source should not be null")
	private String source;

	@NotNull(message = "destination should not be null")
	private String destination;

}
