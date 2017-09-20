package com.design4music.Service;

import com.design4music.DAO.TradingDao;
import com.design4music.DAO.TradingDaoCrud;
import com.design4music.Domain.Account;
import com.design4music.Domain.Flippo;
import com.design4music.Domain.TradeItem;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Nekkyou on 20-9-2017.
 */
@Service
public class TradingServiceJpa {

	private TradingDaoCrud tradingDao;
	private AccountServiceJpa accountService;
	private FlippoService flippoService;


	@Autowired
	public TradingServiceJpa(TradingDaoCrud tradingDao, AccountServiceJpa accountService, FlippoService flippoService) {
		this.tradingDao = tradingDao;
		this.accountService = accountService;
		this.flippoService = flippoService;
	}

	public TradeItem getTradeItem(long id) {
		return tradingDao.findOne(id);
	}

	public List<TradeItem> getAllTrades() {
		return Lists.newArrayList(tradingDao.findAll());
	}

	public TradeItem createTradeItem(long proposerId, long proposerFlippoId, long receiverId, long receiverFlippoId) {
		Account proposer = accountService.getAccount(proposerId);
		Flippo proposerFlippo = flippoService.getFlippo(proposerFlippoId);
		Account receiver = accountService.getAccount(receiverId);
		Flippo receiverFlippo = flippoService.getFlippo(receiverFlippoId);

		TradeItem item = new TradeItem(proposer, proposerFlippo, receiver, receiverFlippo);

		return tradingDao.save(item);
	}


	public List<TradeItem> getTradesFromAccount(long accountId) {
		return tradingDao.findAllByProposerId(accountId);
	}

	public List<TradeItem> getTradesForAccount(long accountId) {
		return tradingDao.findAllByRecieverId(accountId);
	}

	public List<TradeItem> getUnhandledAcceptedTrades(long accountId) {
		List<TradeItem> items = Lists.newArrayList(tradingDao.findAllByResponseAndProposerIdAndHandled(1, accountId, false));
		for(TradeItem t: items) {
			t.setHandled(true);
			tradingDao.save(t);
		}
		return items;
		//return tradingDao.getUnhandledAcceptedTrades(accountId);
	}

	public List<TradeItem> getAllTradeResponses(long accountId) {
		return null; //return tradingDao.getAllTradeResponses(accountId);
	}

	public List<TradeItem> getDeclinesTrades(long accountId) {
		return null; //return tradingDao.getDeclinedTrades(accountId);
	}

	public void respondToTrade(long tradeId, boolean response) {
		TradeItem item = tradingDao.findOne(tradeId);
		int res = response ? 1 : 0;
		item.setResponse(res);
		tradingDao.save(item);
	}

}
