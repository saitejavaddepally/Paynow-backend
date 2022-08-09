package com.dbs.paynow.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.paynow.Account.Account;


@Repository
public interface AccountRepo extends JpaRepository<Account, String>{

//    @Query("SELECT t FROM accounts t WHERE t.id = ?1 AND t.bar = ?2")
//	Account findByAccountContaining(String accountNumber);
	

}
