package com.design4music.Service;

import com.design4music.DAO.ITradingDao;
import com.design4music.DAO.TradingDao;
import com.design4music.Domain.Account;
import com.design4music.Domain.Flippo;
import com.design4music.Domain.TradeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Nekkyou on 18-9-2017.
 */
@Service
public class TradingService {

	private ITradingDao tradingDAO;
	private AccountService accountService;

	@Autowired
	public TradingService(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostConstruct
	private void init() {
		tradingDAO = new TradingDao();
	}

	public TradeItem getTradeItem(long id) {
		return tradingDAO.getTradeItem(id);
	}

	public List<TradeItem> getAllTrades() {
		return tradingDAO.getAllTrades();
	}

	public TradeItem createTradeItem(long proposerId, long proposerFlippoId, long receiverId, long receiverFlippoId) {
		Account proposer = accountService.getAccount(proposerId);

		//TODO : Remove this code later.
		if (proposer == null) {
			proposer = accountService.createAccount(proposerId);
		}

		Flippo proposerFlippo = new Flippo(proposerFlippoId);
		Account receiver = accountService.getAccount(receiverId);

		//TODO : Remove this code later.
		if (receiver == null) {
			receiver = accountService.createAccount(receiverId);
		}

		Flippo receiverFlippo = new Flippo(receiverFlippoId);

		return tradingDAO.createTradeItem(proposer, proposerFlippo, receiver, receiverFlippo);
	}


	public List<TradeItem> getTradesFromAccount(long accountId) {
		return tradingDAO.getTradesFromAccount(accountId);
	}

	public List<TradeItem> getTradesForAccount(long accountId) {
		return tradingDAO.getTradesForAccount(accountId);
	}

	public List<TradeItem> getUnhandledAcceptedTrades(long accountId) {
		return tradingDAO.getUnhandledAcceptedTrades(accountId);
	}

	public List<TradeItem> getAllTradeResponses(long accountId) {
		return tradingDAO.getAllTradeResponses(accountId);
	}

	public List<TradeItem> getDeclinesTrades(long accountId) {
		return tradingDAO.getDeclinedTrades(accountId);
	}

	public void respondToTrade(long tradeId, boolean response) {
		tradingDAO.respondToTrade(tradeId, response);
	}

}
