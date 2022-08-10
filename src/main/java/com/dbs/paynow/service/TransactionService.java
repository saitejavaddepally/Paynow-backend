package com.dbs.paynow.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.paynow.Account.Transaction;
import com.dbs.paynow.repo.TransactionRepo;


@Service
public class TransactionService {
    @Autowired
    TransactionRepo myrepo;
    public List<Transaction> getall() {
        return myrepo.findAll();

    }
    public String addNewTran(Transaction tran) {
         try{
                myrepo.save(tran);
            }catch(Exception e) {
                return "Problem Occured while inserting New Rec!!!!!!!!";
            }
            return " Transaction Added Successfully!!!!!!!!!";

    }
    public String updateTran(Transaction tran) {
          try{

               // Transaction exitingTransaction = myrepo.findById(tran.getId()).get();

                myrepo.save(tran);
            }catch(Exception e) {
                return "No Transaction Found!!!!!!!";
            }
                return " Transaction Updated Successfully!!!!!!!!!";
    }

    public String deleteTran(int UTR_NUM) {
        try{
            myrepo.deleteById(UTR_NUM);
        }catch(Exception e) {
            return "Problem Occured while deleting  Rec!!!!!!!!";
        }
        return " Transaction Deleted Successfully!!!!!!!!!";
    }

}