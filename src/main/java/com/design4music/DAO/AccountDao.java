package com.design4music.DAO;

import com.design4music.Domain.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 19-9-2017.
 */
public class AccountDao implements IAccountDao {

	List<Account> accounts;

	public AccountDao() {
		accounts = new ArrayList<>();
	}

	@Override
	public Account getAccount(long id) {
		Account acc = null;
		for(Account a: accounts) {
			if (a.getId() == id) {
				acc = a;
			}
		}
		return acc;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accounts;
	}

	@Override
	public Account createAccount() {
		Account account = new Account(accounts.size());
		accounts.add(account);
		return account;
	}
}
