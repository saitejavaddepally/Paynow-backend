package com.dbs.paynow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.paynow.Account.Account;
import com.dbs.paynow.Account.Receiver;
import com.dbs.paynow.service.AccountService;

@RestController
public class AccountController {
	

	
	@Autowired
	AccountService service;
	

	
	
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
	
	
//	Bank to Bank transfer
	
//	Get the receiver
	@PostMapping("/searchBank")
	List<Receiver> searchReceiverBank(@RequestBody Map<String, String> details) {
		return service.findByBankName(details.get("details"));
	}
	
	
//  Send the Account and Receiver to the API
//	Get success or failure as return
	
	@PostMapping("/sendMoney")
	Object sendMoneyToAnotherBank(@RequestBody Map<String, Object> mapData) {
		
		
//		get the correct logic from the service and return it.
		
		Object response = service.sendMoney(mapData);
		
		return response;
	}
	
	

}
