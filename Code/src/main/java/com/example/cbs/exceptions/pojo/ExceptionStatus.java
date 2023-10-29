package com.example.cbs.exceptions.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionStatus {

	Date timestamp;
	int status;
	String error;
	String description;

}
