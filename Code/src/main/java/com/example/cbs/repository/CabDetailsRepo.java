package com.example.cbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cbs.models.CabDetails;

@Repository
public interface CabDetailsRepo extends JpaRepository<CabDetails, Integer> {

	// Select All Cab Details With Cab Routes
	@Query(value = "SELECT cab_details.*, cab_route.* FROM cab_details JOIN cab_route ON cab_details.cab_id = cab_route.fk_cab_id", nativeQuery = true)
	List<CabDetails> getAllCabs();

	// Get All Cabs By Route Source and Destination
	@Query(value = "SELECT cab_details.*, cab_route.* FROM cab_details JOIN cab_route ON cab_details.cab_id = cab_route.fk_cab_id WHERE cab_route.source = :src AND cab_route.destination = :dest AND cab_Details.cab_status=true", nativeQuery = true)
	List<CabDetails> cabSearchSrcToDest(String src, String dest);

	// Get All Cabs By Model No
	@Query(value = "SELECT cab_details.*, cab_route.* FROM cab_details JOIN cab_route ON cab_details.cab_id = cab_route.fk_cab_id WHERE cab_details.cab_model = :model AND cab_Details.cab_status=true", nativeQuery = true)
	List<CabDetails> cabSearchByModel(String model);

}
