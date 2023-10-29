package com.example.cbs.services;

import java.util.List;

import com.example.cbs.exceptions.pojo.SucessResponse;
import com.example.cbs.models.CabDetails;
import com.example.cbs.models.dto.CabBookDTO;
import com.example.cbs.models.dto.CabDetailsDTO;
import com.example.cbs.models.dto.CabRouteAddDTO;
import com.example.cbs.models.dto.CabRouteDTO;
import com.example.cbs.models.dto.CabSearchDTO;

public interface CabServices {

	public CabDetailsDTO cabRegister(CabDetailsDTO cabDetailsDTO);

	public List<CabRouteAddDTO> getCabDetails();

	public CabRouteDTO cabRouteRegister(int cabId, CabRouteDTO cabRouteDTO);

	public CabBookDTO cabBook(int cabId, CabBookDTO cabBookDTO);

	public SucessResponse cabBookDelete(int bookId);

	public List<CabSearchDTO> cabSearchSrcToDest(String src, String dest);

	public List<CabSearchDTO> cabSearchLogic(List<CabDetails> cabDetails);

	public List<CabSearchDTO> cabSearchByModel(String model);

}
