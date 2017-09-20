package com.design4music.Service;

import com.design4music.DAO.AccountDao;
import com.design4music.DAO.AccountDaoCrud;
import com.design4music.DAO.IAccountDao;
import com.design4music.Domain.Account;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nekkyou on 20-9-2017.
 */
@Service
public class AccountServiceJpa {

	private AccountDaoCrud accountDao;

	@Autowired
	public AccountServiceJpa(AccountDaoCrud crud) {
		this.accountDao = crud;
	}

	public Account getAccount(long id) {
		return accountDao.findOne(id);
	}

	public List<Account> getAllAccounts() {
		return Lists.newArrayList(accountDao.findAll());
	}

	public Account createAccount() {
		Account account = new Account(UUID.randomUUID().toString());
		account = accountDao.save(account);
		return account;
	}
}
