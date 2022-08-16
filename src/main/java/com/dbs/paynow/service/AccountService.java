package com.dbs.paynow.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dbs.paynow.Account.Account;
import com.dbs.paynow.Account.Receiver;
import com.dbs.paynow.Account.Transaction;
import com.dbs.paynow.repo.AccountRepo;
import com.dbs.paynow.repo.RecieverRepo;

// Task 1: sender account balance check
// Task 2: if OD is true, send money anyway

//1. send money failure
//	1. insufficient balance
//	2. unknown receiver
//	3. same bank.
//	4. terror group.
//	5. trigger transaction API as failure
//	6. Blocked banks.

//2. send money success
//	1. deduct balance after sending.
//	3. trigger transaction API as success

@Controller
public class AccountService {

	@Autowired
	AccountRepo repo;

	@Autowired
	RecieverRepo receiverRepo;

	@Autowired
	TransactionService transactionService;

	public List<Account> findAllAccountHolderNames() {

		return repo.findAll();

	}

	public Account findById(String id) {
		return repo.findById(id).orElse(new Account());
	}
	
	public Receiver findByBic(String id) {
		return receiverRepo.findById(id).orElse(new Receiver());
	}


	public List<Receiver> findByBankName(String bankName) {
		return receiverRepo.findByBankContaining(bankName);
	}

	public boolean findInTerrorList(String name) {
		String[] words; 
		BufferedReader br;
		String inputFileName = "src/main/resources/db/data.txt"; 
		try {
			try (FileReader fr = new FileReader(inputFileName)) {
				br = new BufferedReader(fr);
				
				String line;
				while((line = br.readLine())!= null) {
					words = line.split(" ");
					for(String word: words) {
						System.out.println(word);
						if(word.equals(name)) {
							return true;
						}
					}
				}
				
				fr.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return false;
		
	}
	
	public Account updateBalance(String id, double balance, String type) {

		Account accountDetails = findById(id);
		accountDetails.setBalance(balance);
		accountDetails.setType(type);
		repo.save(accountDetails);

		return findById(id);

	}

	public HashMap<String, Object> bankTransfer(Map<String, Object> senderAccount, Map<String, Object> receiverAccount,
			int moneyToSend, boolean bankToBank, String code) {

		HashMap<String, Object> statusResponse = new HashMap<>();

		// check for same bank or not.
		String id = senderAccount.get("id").toString();
		String receiverBic = receiverAccount.get("bic").toString();


//		Generate random urn number
		Random r = new Random();
		int urnNumber = 1000000000 + (int) (r.nextDouble() * 999999999);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

//	    initiate transaction
		Transaction transaction = new Transaction();
		transaction.setAmount(Double.toString(moneyToSend));
		transaction.setBENEFICIARY_ACNUM(receiverBic);
		transaction.setDOT(dtf.format(now).toString());
		transaction.setUTR_NUMBER(urnNumber);
		transaction.setSENDER_ACNUM(id);
		transaction.setCode(code);

		if (bankToBank) {
			String senderBank = senderAccount.get("name").toString();
			String[] keywords = senderBank.split(" ");
			String exactBankName = keywords[0];
			
	

			System.out.print(exactBankName);

			if (!receiverAccount.get("bank").toString().contains(exactBankName)) {
				statusResponse.put("status", "success");
				statusResponse.put("message", "cannot initiate transactions with other banks");
				statusResponse.put("code", "201");

				return statusResponse;
			}
		}

		boolean checkedBalance = false;

		double currentBalance = Double.parseDouble(senderAccount.get("balance").toString());

//		check if OD is true

		if (senderAccount.get("od").toString().toUpperCase().equals("YES")) {

			currentBalance -= moneyToSend;
			if (currentBalance < 0)
				currentBalance = 0;

			checkedBalance = true;
		}

//		check balance first.

		else if (moneyToSend <= currentBalance) {

			currentBalance -= moneyToSend + moneyToSend*0.0025;
			senderAccount.put("balance", String.format("%.2f", currentBalance));

			checkedBalance = true;
		}

//		send the response

		if (checkedBalance) {
			senderAccount.put("balance", currentBalance);
			senderAccount.put("type", "bank transfer");

//			update the balance

			updateBalance(id, currentBalance, (bankToBank) ? "bank transfer" : "customer transfer");

//			return the response
			statusResponse.put("status", "success");
			statusResponse.put("balanceRemaining", currentBalance);
			statusResponse.put("code", "200");

			transaction.setSTATUS(true);

		}

		else {
			statusResponse.put("status", "failure");
			statusResponse.put("code", "400");
			transaction.setSTATUS(false);
		}

		transactionService.addNewTran(transaction);

		return statusResponse;
	}

	@SuppressWarnings("unchecked")
	public Object sendMoney(Map<String, Object> details) {

		Map<String, Object> senderAccount = (Map<String, Object>) details.get("senderDetails");
		Map<String, Object> receiverAccount = (Map<String, Object>) details.get("receiverDetails");
		HashMap<String, Object> statusResponse = new HashMap<>();
		String receiverName = details.get("receiverName").toString();
		
		if(findInTerrorList(receiverName)) {
			statusResponse.put("status", "failure");
			statusResponse.put("message", "Name is in the terrorList");
			statusResponse.put("code", "201");
			return statusResponse;
		}
		String senderBank = senderAccount.get("name").toString();
		String[] keywords = senderBank.split(" ");
		String exactBankName = keywords[0];

		String typeOfTransaction = details.get("transactionType").toString();
		int moneyToSend = (int) details.get("moneyToSend");

		if (typeOfTransaction.equals("bank")) {

			if (!"HDFC".equals(exactBankName.toUpperCase())) {
				statusResponse.put("status", "success");
				statusResponse.put("message", "Account doesn't correspond to bank to bank transfer");
				statusResponse.put("code", "201");

				return statusResponse;
			}
			statusResponse = bankTransfer(senderAccount, receiverAccount, moneyToSend, true, details.get("code").toString());
		} else {

			if ("HDFC".equals(exactBankName.toUpperCase())) {
				statusResponse.put("status", "success");
				statusResponse.put("message", "Account doesn't correspond to customer to bank transfer");
				statusResponse.put("code", "201");

				return statusResponse;
			}

			statusResponse = bankTransfer(senderAccount, receiverAccount, moneyToSend, false, details.get("code").toString());

		}

		return statusResponse;
	}
}
