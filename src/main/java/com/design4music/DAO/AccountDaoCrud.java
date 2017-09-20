package com.design4music.DAO;

import com.design4music.Domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nekkyou on 20-9-2017.
 */
public interface AccountDaoCrud extends CrudRepository<Account, Long>{
}
