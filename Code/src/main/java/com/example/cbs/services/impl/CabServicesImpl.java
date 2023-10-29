package com.example.cbs.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cbs.exceptions.DataNotFoundException;
import com.example.cbs.exceptions.pojo.SucessResponse;
import com.example.cbs.models.CabBook;
import com.example.cbs.models.CabDetails;
import com.example.cbs.models.CabRoute;
import com.example.cbs.models.dto.CabBookDTO;
import com.example.cbs.models.dto.CabDetailsDTO;
import com.example.cbs.models.dto.CabRouteAddDTO;
import com.example.cbs.models.dto.CabRouteDTO;
import com.example.cbs.models.dto.CabSearchDTO;
import com.example.cbs.repository.CabBookRepo;
import com.example.cbs.repository.CabDetailsRepo;
import com.example.cbs.repository.CabRouteRepo;
import com.example.cbs.services.CabServices;

@Service
public class CabServicesImpl implements CabServices {

	@Autowired
	CabDetailsRepo cabDetailsRepo;

	@Autowired
	CabRouteRepo cabRouteRepo;

	@Autowired
	CabBookRepo cabBookRepo;
	
	private static final Logger log = LoggerFactory.getLogger(CabServicesImpl.class);

	// Cab Register Service
	public CabDetailsDTO cabRegister(CabDetailsDTO cabDetailsDTO) {

		CabDetails cabDetails = new CabDetails();

		// Mapped CabDetailsDTO to CabDetails
		cabDetails.setCabDriverMobileNumber(cabDetailsDTO.getCabDriverMobileNumber());
		cabDetails.setCabDriverName(cabDetailsDTO.getCabDriverName());
		cabDetails.setCabModel(cabDetailsDTO.getCabModel());
		cabDetails.setCabNumber(cabDetailsDTO.getCabNumber());
		cabDetails.setCabStatus(true);

		// Save Data in Cab Details Table
		cabDetailsRepo.save(cabDetails);

		cabDetailsDTO.setCabId(cabDetails.getCabId());
		cabDetailsDTO.setCabStatus("Available");

		return cabDetailsDTO;
	}

	// Get All Cab and Route Details Service
	public List<CabRouteAddDTO> getCabDetails() {
		// Entity List and Repository Data

		List<CabDetails> cabDetails = cabDetailsRepo.getAllCabs().stream().distinct().collect(Collectors.toList());

		if (cabDetails.isEmpty()) {
			throw new DataNotFoundException("Data not Available on Database");
		}

		else {

			List<CabRouteAddDTO> cabRouteAddDTO = new ArrayList<>();

			for (CabDetails cd : cabDetails) {

				CabRouteAddDTO crdto = new CabRouteAddDTO();

				// CabDetail
				crdto.setCabId(cd.getCabId());
				crdto.setCabNumber(cd.getCabNumber());
				crdto.setCabDriverName(cd.getCabDriverName());
				crdto.setCabModel(cd.getCabModel());
				crdto.setCabDriverMobileNumber(cd.getCabDriverMobileNumber());
				crdto.setCabStatus(cd.isCabStatus() ? "Avaiable" : "Booked");

				// Mapped Cab Routes to Cab Route DTO
				List<CabRoute> list1 = cd.getCabRoutes();
				List<CabRouteDTO> list2 = new ArrayList<>();
				for (CabRoute cr : list1) {
					CabRouteDTO cr1 = new CabRouteDTO();
					cr1.setCabRouteId(cr.getCabRouteId());
					cr1.setCabId(cr.getCabId());
					cr1.setSource(cr.getSource());
					cr1.setDestination(cr.getDestination());
					cr1.setDistance(cr.getDistance());
					cr1.setDuration(cr.getDuration());
					list2.add(cr1);
				}

				crdto.setCabRoutesList(list2);

				cabRouteAddDTO.add(crdto);

			}

			return cabRouteAddDTO;
		}

	}

	// Cab Route Register Service
	public CabRouteDTO cabRouteRegister(int cabId, CabRouteDTO cabRouteDTO) {

		// Find Cab by Id
		CabDetails cab = cabDetailsRepo.findById(cabId)
				.orElseThrow(() -> new DataNotFoundException("Cab not available"));

		// Mapped CabRouteDTO to CabRoute
		CabRoute cabRoute = new CabRoute();
		cabRoute.setCabId(cabId);
		cabRoute.setDestination(cabRouteDTO.getDestination());
		cabRoute.setSource(cabRouteDTO.getSource());
		cabRoute.setDistance(cabRouteDTO.getDistance());
		cabRoute.setDuration(cabRouteDTO.getDuration());

		cabRouteRepo.save(cabRoute);

		cabRouteDTO.setCabRouteId(cabRoute.getCabRouteId());
		cabRouteDTO.setCabId(cab.getCabId());

		List<CabRoute> list = new ArrayList<>();

		list.add(cabRoute);

		cab.setCabRoutes(list);

		cabDetailsRepo.save(cab);

		return cabRouteDTO;

	}

	// Cab Book Register Service
	public CabBookDTO cabBook(int cabId, CabBookDTO cabBookDTO) {
		CabDetails cab = cabDetailsRepo.findById(cabId)
				.orElseThrow(() -> new DataNotFoundException("Cab not available"));

		if (cab.isCabStatus()) {
			// CabBookDTO Data to CabBook
			CabBook cabBook = new CabBook();
			cabBook.setCab_id(cab.getCabId());
			cabBook.setCustomerName(cabBookDTO.getCustomerName());
			cabBook.setCustomerNumber(cabBookDTO.getCustomerNumber());
			cabBook.setDestination(cabBookDTO.getDestination());
			cabBook.setSource(cabBookDTO.getSource());

			// Save Data in CabBook Table
			cabBookRepo.save(cabBook);

			// After CabBook Return ID
			cabBookDTO.setCabBookId(cabBook.getCabBookId());

			// Update Status in CabDetails Table
			cab.setCabStatus(false);
			cabDetailsRepo.save(cab);
		} else {
			throw new DataNotFoundException("Cab already book or Cab not Available");
		}

		return cabBookDTO;
	}

	// Cab Book Delete Service
	public SucessResponse cabBookDelete(int bookId) {
		CabBook cabBook = cabBookRepo.findById(bookId)
				.orElseThrow(() -> new DataNotFoundException("Cab Booking not Available"));

		try {
			CabDetails cabDetails = cabDetailsRepo.findById(cabBook.getCab_id())
					.orElseThrow(() -> new Exception("Error in CabBook Delete"));
			cabDetails.setCabStatus(true);
			cabDetailsRepo.save(cabDetails);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			log.warn(e.getMessage());
		}

		cabBookRepo.delete(cabBook);

		return new SucessResponse(201, "Cab Deleted Sucessfully");
	}

	// Cab Search By Source and Destination Service
	public List<CabSearchDTO> cabSearchSrcToDest(String src, String dest) {
		List<CabDetails> cabDetails = cabDetailsRepo.cabSearchSrcToDest(src, dest).stream().distinct()
				.collect(Collectors.toList());

		if (cabDetails.isEmpty()) {
			throw new DataNotFoundException("Data not Available on Database");
		} else {
			return cabSearchLogic(cabDetails);
		}
	}

	// Cab Search By Cab Model Service
	public List<CabSearchDTO> cabSearchByModel(String model) {
		List<CabDetails> cabDetails = cabDetailsRepo.cabSearchByModel(model).stream().distinct()
				.collect(Collectors.toList());

		if (cabDetails.isEmpty()) {
			throw new DataNotFoundException("Data not Available on Database");
		} else {
			return cabSearchLogic(cabDetails);
		}

	}

	// Cab Search Logic
	public List<CabSearchDTO> cabSearchLogic(List<CabDetails> cabDetails) {
		List<CabSearchDTO> cabSearchDTOList = new ArrayList<>();

		for (CabDetails cab : cabDetails) {

			CabSearchDTO cabSearch = new CabSearchDTO();

			// Cab Details
			cabSearch.setCabId(cab.getCabId());
			cabSearch.setCabNumber(cab.getCabNumber());
			cabSearch.setCabDriverName(cab.getCabDriverName());
			cabSearch.setCabDriverMobileNumber(cab.getCabDriverMobileNumber());
			cabSearch.setCabModel(cab.getCabModel());
			cabSearch.setCabStatus(cab.isCabStatus() ? "Available" : "Booked");

			// Cab Route Details

			// Mapped Cab Routes to Cab Route DTO
			CabRoute cr = cab.getCabRoutes().stream().findFirst().get();

			cabSearch.setCabRouteId(cr.getCabRouteId());
			cabSearch.setSource(cr.getSource());
			cabSearch.setDestination(cr.getDestination());
			cabSearch.setDistance(cr.getDistance());
			cabSearch.setDuration(cr.getDuration());

			cabSearchDTOList.add(cabSearch);

		}

		return new ArrayList<>(cabSearchDTOList);
	}

}
