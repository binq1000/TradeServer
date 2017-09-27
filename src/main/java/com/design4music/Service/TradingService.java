package com.design4music.Service;

import com.design4music.DAO.ITradingDao;
import com.design4music.DAO.TradingDao;
import com.design4music.DataViz.AccountTrades;
import com.design4music.Domain.AcceptedTradeItem;
import com.design4music.Domain.Account;
import com.design4music.Domain.Flippo;
import com.design4music.Domain.TradeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

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

	public TradeItem createTradeItem(long proposerId, long proposerFlippoId, long receiverFlippoId) {
		Account proposer = accountService.getAccount(proposerId);

		//TODO : Remove this code later.
		if (proposer == null) {
			proposer = accountService.createAccount(proposerId);
		}

		Flippo proposerFlippo = new Flippo(proposerFlippoId);
		Flippo receiverFlippo = new Flippo(receiverFlippoId);

		return tradingDAO.createTradeItem(proposer, proposerFlippo, receiverFlippo);
	}

	public TradeItem checkAlt(TradeItem item) {
		System.out.println("New PfId: " + item.getProposerFlippo().getId() + "; New RfId: " + item.getReceiverFlippo().getId());
		TradeItem tradeItem = null;

		List<TradeItem> items = tradingDAO.getAllTrades();
		for(TradeItem t: items) {
			System.out.println("PfId: " + t.getProposerFlippo().getId() + "; RfId: " + t.getReceiverFlippo().getId());
			if (t.getProposerFlippo().getId() == item.getReceiverFlippo().getId() && t.getReceiverFlippo().getId() == item.getProposerFlippo().getId()) {
				tradeItem = t;
				tradingDAO.respondToTrade(t.getId(), item.getProposer(), true);
				tradingDAO.removeFromList(item.getId());
				break;
			}
		}

		return tradeItem;
	}


	public List<TradeItem> getTradesFromAccount(long accountId) {
		return tradingDAO.getTradesFromAccount(accountId);
	}

	public List<TradeItem> getTradesForAccount(long accountId) {
		return tradingDAO.getTradesForAccount(accountId);
	}

	public List<AcceptedTradeItem> getUnhandledAcceptedTrades(long accountId) {
		return tradingDAO.getUnhandledAcceptedTrades(accountId);
	}

	public Map<String,List<TradeItem>> getAllTradeResponses(long accountId) {
		return tradingDAO.getAllTradeResponses(accountId);
	}

	public List<TradeItem> getDeclinesTrades(long accountId) {
		return tradingDAO.getDeclinedTrades(accountId);
	}

	public void respondToTrade(long tradeId, boolean response) {
//		tradingDAO.respondToTrade(tradeId, response);
	}

	public List<AccountTrades> getTradesPerAccount() {
		List<AccountTrades> items = new ArrayList<>();

		for(Account a: accountService.getAllAccounts()) {
			List<TradeItem> tradedItems = new ArrayList<>();
			Map<String, List<TradeItem>> trades = getAllTradeResponses(a.getId());
			for(Map.Entry<String, List<TradeItem>> trade : trades.entrySet()) {
				tradedItems.addAll(trade.getValue());
			}
			items.add(new AccountTrades(a, tradedItems));
		}

		return items;
	}


	public void createTestData() {
		int minFlippoId = 1;
		int maxFlippoId = 30 + 1;

		if (tradingDAO.getAllTrades().size() < 5000) {
			for(int i=0; i <30; i++) {
				Random random = new Random();
				int accountId = random.ints(0, 301).findFirst().getAsInt();
				int flippo1 = random.ints(minFlippoId, maxFlippoId).findFirst().getAsInt();
				int flippo2 = random.ints(minFlippoId, maxFlippoId).findFirst().getAsInt();
				createTradeItem(accountId, flippo1, flippo2);
			}
		}

	}

}
