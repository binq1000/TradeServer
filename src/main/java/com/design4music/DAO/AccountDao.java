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
		try {
			for(Account a: accounts) {
				if (a.getId() == id) {
					acc = a;
				}
			}
		}
		catch (NullPointerException e) {
			System.out.println("NullPointer encountered: " + e.getMessage());
		}

		return acc;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accounts;
	}

	@Override
	public Account createAccount() {
		Account account = new Account(getNextAccountId());
		if (account != null)
		{
			accounts.add(account);
		}
		return account;
	}

	@Override
	public Account createAccount(long id) {
		//Check if account already exists.
		for(Account a: accounts) {
			if (a.getId() == id) {
				throw new IllegalArgumentException("id already exists");
			}
		}

		//Create the account
		Account account = new Account(id);
		accounts.add(account);
		return account;
	}


	/**
	 * Get a new ID for a new account
	 * @return The highest id + 1.
	 */
	private long getNextAccountId() {
		long highest = 0;
		for(Account a: accounts) {
			if (a != null && a.getId() >= highest) {
				highest = a.getId() + 1;
			}
		}
		return highest;
	}
}
