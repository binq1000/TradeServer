package com.design4music.DAO;

import com.design4music.Domain.Account;
import com.design4music.Domain.Flippo;
import com.design4music.Domain.TradeItem;

import java.util.*;

/**
 * Created by Nekkyou on 18-9-2017.
 */
public class TradingDao implements ITradingDao{

	List<TradeItem> tradeItemList;
	HashMap<TradeItem, Boolean> acceptedTrades;
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
	public TradeItem createTradeItem(Account proposer, Flippo proposerFlippo, Account receiver, Flippo receiverFlippo) {
		TradeItem item = new TradeItem(tradeItemList.size(), proposer, proposerFlippo, receiver, receiverFlippo);
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
		for (TradeItem i: tradeItemList) {
			if (i.getReciever().getId() == accountId) {
				items.add(i);
			}
		}
		return items;
	}

	@Override
	public List<TradeItem> getUnhandledAcceptedTrades(long accountId) {
		ArrayList<TradeItem> items = new ArrayList<>();
		for(Map.Entry<TradeItem, Boolean> entry: acceptedTrades.entrySet()) {
			if (!entry.getValue() && entry.getKey().getProposer().getId() == accountId) {
				items.add(entry.getKey());
				entry.setValue(true);
			}
		}

		return items;
	}

	@Override
	public List<TradeItem> getAllTradeResponses(long accountId) {
		List<TradeItem> items = new ArrayList<>();
		for(TradeItem t: declinedTrades) {
			if (t.getProposer().getId() == accountId || t.getReciever().getId() == accountId) {
				items.add(t);
			}
		}
		for(TradeItem t: acceptedTrades.keySet()) {
			if (t.getProposer().getId() == accountId || t.getReciever().getId() == accountId) {
				items.add(t);
			}
		}
		return items;
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
	public void respondToTrade(long tradeItemId, boolean response) {
		TradeItem item = null;
		for(TradeItem i: tradeItemList) {
			if (i.getId() == tradeItemId) {
				item = i;
				break;
			}
		}

		tradeItemList.remove(item);

		if (response) {
			acceptedTrades.put(item, false);
		} else {
			declinedTrades.add(item);
		}
	}
}
