package com.example.cbs.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "cabDetails")
@Entity
public class CabDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cab_id")
	private Integer cabId;

	@NotNull(message = "Cab number should not be null")
	private String cabNumber;

	@NotNull(message = "Cab Driver name should not be null")
	private String cabDriverName;

	@NotNull(message = "Cab Model should not be null")
	private String cabModel;

	@NotNull(message = "Cab Driver Mobile Number should not be null")
	private long cabDriverMobileNumber;

	private boolean cabStatus;

	// Mapped cab_id to Cab Route Table or Entity
	@OneToMany(mappedBy = "cabDetails")
	private List<CabRoute> cabRoutes;

}
