package com.example.cbs.exceptions.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucessResponse {

	int status;
	String message;
}
