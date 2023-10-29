package com.example.cbs.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CabRouteDTO {

	private Integer cabRouteId;

	private Integer cabId;

	private String source;

	private String destination;

	private int distance;

	private int duration;

}
