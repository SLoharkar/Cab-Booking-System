package com.example.cbs.controller.cab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cbs.models.dto.CabSearchDTO;
import com.example.cbs.services.CabServices;

@RestController
@RequestMapping("/cab/search")
public class CabSearchController {

	@Autowired
	CabServices cabService;

	@GetMapping("/routes/{source}/{destination}")
	@ResponseStatus(HttpStatus.OK)
	public List<CabSearchDTO> cabSearchSrcToDest(@PathVariable("source") String src,
			@PathVariable("destination") String dest) {
		return cabService.cabSearchSrcToDest(src, dest);
	}

	@GetMapping("/model/{model}")
	@ResponseStatus(HttpStatus.OK)
	public List<CabSearchDTO> cabSearchByModel(@PathVariable("model") String model) {
		return cabService.cabSearchByModel(model);
	}

}
