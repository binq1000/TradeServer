package com.design4music.DAO;

import com.design4music.Domain.Account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 19-9-2017.
 */
public class AccountDao implements IAccountDao {

	List<Account> accounts;

	public AccountDao() {
		accounts = new ArrayList<>();
		readId();
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
		Account account = new Account(getNextAccountId());
		accounts.add(account);
		writeId();
		return account;
	}

	@Override
	public Account createAccount(long id) {
		//Check if account already exists.
		for(Account a: accounts) {
			if (a != null && a.getId() == id) {
				throw new IllegalArgumentException("id already exists");
			}
		}

		//Create the account
		Account account = new Account(id);
		accounts.add(account);
		writeId();
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



	private void writeId() {
		try {
			Writer writer = new FileWriter("accountid.txt");
			writer.write(String.valueOf(accounts.size()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readId() {
		try {
			Reader reader = new FileReader("accountid.txt");
			BufferedReader bufferedReader =
					new BufferedReader(reader);
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				int i = Integer.parseInt(line);
				createAccount(i);
			}

			// Always close files.
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
