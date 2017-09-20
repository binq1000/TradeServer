package com.design4music.Rest;

import com.design4music.Domain.Account;
import com.design4music.Service.AccountService;
import com.design4music.Service.AccountServiceJpa;
import io.swagger.annotations.ApiOperation;
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
	AccountServiceJpa service;

	public AccountRest(AccountServiceJpa service) {
		this.service = service;
	}

	@ApiOperation(value = "getAccounts", notes = "Get all the accounts")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> getAccounts() {
		List<Account> accounts = service.getAllAccounts();
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}

	@ApiOperation(value = "getAccount", notes = "Get a single Account by id")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<Account> getAccount(@RequestParam("id") long id) {
		Account a = service.getAccount(id);
		return new ResponseEntity<Account>(a, HttpStatus.OK);
	}

	@ApiOperation(value = "createAccount", notes = "Create an Account")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<Account> createAccount() {
		Account account = service.createAccount();
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

}
