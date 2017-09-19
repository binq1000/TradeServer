package com.design4music.DAO;

import com.design4music.Domain.Account;

import java.util.List;

/**
 * Created by Nekkyou on 19-9-2017.
 */
public interface IAccountDao {

	Account getAccount(long id);

	List<Account> getAllAccounts();

	Account createAccount();
}
