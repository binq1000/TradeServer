package com.design4music.DAO;

import com.design4music.Domain.AcceptedTradeItem;
import com.design4music.Domain.Account;
import com.design4music.Domain.Flippo;
import com.design4music.Domain.TradeItem;

import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Created by Nekkyou on 18-9-2017.
 */
public class TradingDao implements ITradingDao{

	List<TradeItem> tradeItemList;
	HashMap<AcceptedTradeItem, Boolean> acceptedTrades;
	List<TradeItem> declinedTrades;

	public TradingDao() {
		tradeItemList = new ArrayList<>();
		acceptedTrades = new HashMap<>();
		declinedTrades = new ArrayList<>();
	}



	@Override
	public TradeItem getTradeItem(long id) {
		TradeItem item = null;
		for(TradeItem i: tradeItemList) {
			if (i.getId() == id) {
				item = i;
				break;
			}
		}
		return item;
	}

	@Override
	public List<TradeItem> getAllTrades() {
		return  tradeItemList;
	}

	@Override
	public TradeItem createTradeItem(TradeItem item) {
		item.setId(tradeItemList.size());
		tradeItemList.add(item);
		return item;
	}

	@Override
	public TradeItem createTradeItem(Account proposer, Flippo proposerFlippo, Flippo receiverFlippo) {
		TradeItem item = new TradeItem(tradeItemList.size(), proposer, proposerFlippo, receiverFlippo);
		tradeItemList.add(item);
		return item;
	}

	@Override
	public List<TradeItem> getTradesFromAccount(long accountId) {
		ArrayList<TradeItem> items = new ArrayList<>();
		for (TradeItem i: tradeItemList) {
			if (i.getProposer().getId() == accountId) {
				items.add(i);
			}
		}
		return items;
	}

	@Override
	public List<TradeItem> getTradesForAccount(long accountId) {
		ArrayList<TradeItem> items = new ArrayList<>();
		//TODO write logic.
		return items;
	}

	@Override
	public List<AcceptedTradeItem> getUnhandledAcceptedTrades(long accountId) {
		ArrayList<AcceptedTradeItem> items = new ArrayList<>();

		for(Map.Entry<AcceptedTradeItem, Boolean> entry: acceptedTrades.entrySet()) {
			TradeItem item = entry.getKey().getTradeItem();
			if (!entry.getValue() && entry.getKey() != null && item.getProposer() != null && item.getProposer().getId() == accountId) {
				items.add(entry.getKey());
				entry.setValue(true);
			}
		}

		return items;
	}

	@Override
	public Map<String,List<TradeItem>> getAllTradeResponses(long accountId) {
		List<TradeItem> items = new ArrayList<>();
		Map<String, List<TradeItem>> response = new HashMap<>();

		for(Map.Entry<AcceptedTradeItem, Boolean> entry: acceptedTrades.entrySet()) {
			TradeItem item = entry.getKey().getTradeItem();

			//All trade where you are the receiver
			if (entry.getKey().getReceiver().getId() == accountId) {
				items.add(new TradeItem(item.getId(), entry.getKey().getReceiver(), item.getReceiverFlippo(), item.getProposerFlippo()));
			}

			//All trades where you are the proposer.
			if (item.getProposer() != null && item.getProposer().getId() == accountId) {
				items.add(entry.getKey().getTradeItem());
			}
		}

		response.put("accepted", items);
		response.put("pending", getTradesFromAccount(accountId));
		return response;
	}


	@Override
	public List<TradeItem> getDeclinedTrades(long accountId) {
		ArrayList<TradeItem> items = new ArrayList<>();
		for(TradeItem i: declinedTrades) {
			if (i.getProposer().getId() == accountId) {
				items.add(i);
			}
		}

		return items;
	}


	@Override
	public void respondToTrade(long tradeItemId, Account receiver, boolean response) {
		TradeItem item = null;

		for(TradeItem i: tradeItemList) {
			if (i.getId() == tradeItemId) {
				item = i;
				break;
			}
		}

		tradeItemList.remove(item);
		AcceptedTradeItem acceptedTradeItem = new AcceptedTradeItem(item, receiver);

		if (response) {
			acceptedTrades.put(acceptedTradeItem, false);
		} else {
			declinedTrades.add(item);
		}
	}

	@Override
	public void removeFromList(long tradeItemId) {
		TradeItem item = null;

		for(TradeItem i: tradeItemList) {
			if (i.getId() == tradeItemId) {
				item = i;
				break;
			}
		}

		tradeItemList.remove(item);
	}
}
