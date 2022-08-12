package com.dbs.paynow.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.paynow.PaynowApplication;
import com.dbs.paynow.Account.Account;
import com.dbs.paynow.Account.Receiver;
import com.dbs.paynow.Account.Transaction;
import com.dbs.paynow.service.AccountService;
import com.dbs.paynow.service.TransactionService;

//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin("*")
@RestController
public class AccountController {
	

	
	@Autowired
	AccountService service;
	
	@Autowired
	TransactionService transactionService;


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
	
	@RequestMapping("/all")
	public List<Transaction> GetAll(){
    List<Transaction> Tlist=transactionService.getall();
        return Tlist;
    }

    @PostMapping("/addNewTran")
    public String addNewEmpRecord(@RequestBody Transaction tran) {

        return transactionService.addNewTran(tran);
    }

    @PutMapping("/updateTran")
    public String updateEmpRec(@RequestBody Transaction tran) {
        return transactionService.addNewTran(tran);

    }

    @DeleteMapping("/deleteTran")
    public String deleteEmp(@RequestParam int UTR_NUM) {
        return transactionService.deleteTran(UTR_NUM);
    }

}
