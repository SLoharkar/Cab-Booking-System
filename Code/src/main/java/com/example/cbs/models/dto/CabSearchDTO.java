package com.example.cbs.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CabSearchDTO {

	// Cab Details Fields

	private Integer cabId;

	private String cabNumber;

	private String cabDriverName;

	private String cabModel;

	private String cabStatus;

	private long cabDriverMobileNumber;

	// Cab Route Fields

	private Integer cabRouteId;

	private String source;

	private String destination;

	private int distance;

	private int duration;

}
