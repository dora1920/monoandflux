package com.acoder.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	
	private String name;
	
	private Date date;

	public Customer(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}
	
	
	
}
