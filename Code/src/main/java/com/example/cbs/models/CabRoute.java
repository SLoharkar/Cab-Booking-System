package com.example.cbs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "cabRoute")
@Entity
public class CabRoute {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabRouteId;

	// Cab Details Table Foreign Key cab_id
	@Column(name = "fk_cab_id")
	private Integer cabId;

	private String source;

	private String destination;

	private int distance;

	private int duration;

	// Cab Details Table cab_id mapped Cab Route Table or Entity
	@ManyToOne
	@JoinColumn(name = "fk_cab_id", insertable = false, updatable = false)
	private CabDetails cabDetails;

}
