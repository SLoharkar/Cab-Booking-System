package com.example.cbs.controller.cab;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cbs.models.dto.CabDetailsDTO;
import com.example.cbs.models.dto.CabRouteAddDTO;
import com.example.cbs.services.CabServices;

@RestController
@RequestMapping("/cab")
public class CabController {

	@Autowired
	CabServices cabService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<CabRouteAddDTO> getCabDetails() {
		return cabService.getCabDetails();
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.OK)
	public CabDetailsDTO cabRegister(@Valid @RequestBody CabDetailsDTO cabDetailsDTO) {
		return cabService.cabRegister(cabDetailsDTO);
	}

}
