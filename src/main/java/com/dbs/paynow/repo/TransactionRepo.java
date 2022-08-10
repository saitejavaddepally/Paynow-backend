package com.dbs.paynow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.paynow.Account.Transaction;


public interface TransactionRepo extends JpaRepository<Transaction,Integer>{

    

}