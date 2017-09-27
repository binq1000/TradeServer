package com.design4music.DataViz;

import com.design4music.Domain.AcceptedTradeItem;
import com.design4music.Domain.Account;
import com.design4music.Domain.TradeItem;

import java.util.List;

public class AccountTrades {
	private Account account;
	private List<TradeItem> trades;

	public AccountTrades(Account account, List<TradeItem> trades) {
		this.account = account;
		this.trades = trades;
	}

	//region Getters and setters
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<TradeItem> getTrades() {
		return trades;
	}

	public void setTrades(List<TradeItem> trades) {
		this.trades = trades;
	}
	//endregion
}
