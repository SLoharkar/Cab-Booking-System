package com.example.cbs.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CabDetailsDTO {

	private Integer cabId;

	private String cabNumber;

	private String cabDriverName;

	private long cabDriverMobileNumber;

	private String cabModel;

	private String cabStatus;

	/*
	 * {
	 * "cabNumber":"1234789",
	 * "cabDriverName":"Suresh",
	 * "cabDriverMobileNumber":9832742352
	 * "cabModel":"Sedan",
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 */

}
