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

@Table(name = "cabBook")
@Entity
public class CabBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer cabBookId;

	private String customerName;

	private String customerNumber;

	private String source;

	private String destination;

	// Foreign Key From Cab Details
	@Column(name = "fk_cab_id")
	private Integer cab_id;

	// Foreign Key cab_id
	@ManyToOne
	@JoinColumn(name = "fk_cab_id", insertable = false, updatable = false)
	private CabDetails cabDetails;

}
