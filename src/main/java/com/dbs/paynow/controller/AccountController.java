package com.dbs.paynow.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.paynow.Account.Account;
import com.dbs.paynow.Account.Receiver;
import com.dbs.paynow.service.AccountService;

//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin("*")
@RestController
public class AccountController {
	

	
	@Autowired
	AccountService service;
	



	@RequestMapping("/checkForDates")
	
	Map<String, Object> checkDates() {
		Map<String, Object> response = new HashMap<>(); 
		

		DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
		
	
		response.put("services", dayOfWeek);
		return response;
	}
	
	
	@RequestMapping("/find")
	List<Account> getAccountDetails() {
		
		return service.findAllAccountHolderNames();
		
	}
	
	@RequestMapping("/findById")
	Map<String, Object> findById(@RequestParam String id) {
		Account accountObject =  service.findById(id);
		Map<String, Object> response = new HashMap<>(); 

		
		if(accountObject.getId() == null) {
			response.put("isFound", null); 
			response.put("status", 440);
			return response;
		}
		
		
		response.put("isFound", accountObject);
		response.put("status", 200);
		
		return response; 
		
	}
	
	@RequestMapping("/findByBic")
	Map<String, Object> findByBic(@RequestParam String id) {
		Receiver accountObject =  service.findByBic(id);
		Map<String, Object> response = new HashMap<>(); 

		
		if(accountObject.getBIC() == null) {
			response.put("isFound", null); 
			response.put("status", 440);
			return response;
		}
		
		
		response.put("isFound", accountObject);
		response.put("status", 200);
		
		return response; 
		
	}
	
	
	
//	Bank to Bank transfer
	


}
