package com.design4music.Rest;

import com.design4music.Domain.Account;
import com.design4music.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nekkyou on 19-9-2017.
 */
@RestController
@RequestMapping(value = "/account")
public class AccountRest {
	AccountService service;

	public AccountRest(AccountService service) {
		this.service = service;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> getAccounts() {
		List<Account> accounts = service.getAllAccounts();
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<Account> getAccount(@RequestParam("id") long id) {
		Account a = service.getAccount(id);
		return new ResponseEntity<Account>(a, HttpStatus.OK);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<Account> createAccount() {
		Account account = service.createAccount();
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

}
