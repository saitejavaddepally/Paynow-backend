package com.dbs.paynow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.paynow.Account.Account;
import com.dbs.paynow.Account.Receiver;


@Repository
public interface RecieverRepo extends JpaRepository<Receiver, String>{

	List<Receiver> findByBankContaining(String bank);

}