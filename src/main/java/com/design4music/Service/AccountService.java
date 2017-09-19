package com.design4music.Service;

import com.design4music.DAO.AccountDao;
import com.design4music.DAO.IAccountDao;
import com.design4music.Domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Nekkyou on 19-9-2017.
 */
@Service
public class AccountService {

	private IAccountDao accountDao;

	@Autowired
	public AccountService() {

	}

	@PostConstruct
	private void init() {
		accountDao = new AccountDao();
	}

	public Account getAccount(long id) {
		return accountDao.getAccount(id);
	}

	public List<Account> getAllAccounts() {
		return accountDao.getAllAccounts();
	}

	public Account createAccount() {
		return accountDao.createAccount();
	}

	public Account createAccount(long id) {
		return accountDao.createAccount(id);
	}
}
