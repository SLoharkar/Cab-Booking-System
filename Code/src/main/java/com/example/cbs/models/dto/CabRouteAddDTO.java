package com.example.cbs.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CabRouteAddDTO {

	// Cab Details Fields

	private Integer cabId;

	private String cabNumber;

	private String cabDriverName;

	private String cabModel;

	private String cabStatus;

	private long cabDriverMobileNumber;

	// Cab Route Fields List

	private List<CabRouteDTO> cabRoutesList;

}
