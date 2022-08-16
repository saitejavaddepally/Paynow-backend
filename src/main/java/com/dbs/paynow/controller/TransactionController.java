package com.dbs.paynow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.paynow.Account.Transaction;
import com.dbs.paynow.service.TransactionService;


@CrossOrigin("*")
@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
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
