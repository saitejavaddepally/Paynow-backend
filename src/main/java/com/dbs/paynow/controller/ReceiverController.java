package com.dbs.paynow.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.paynow.Account.Receiver;
import com.dbs.paynow.service.AccountService;

@CrossOrigin("*")
@RestController
public class ReceiverController {

	

	@Autowired
	AccountService service;
	
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
