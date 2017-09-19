package com.design4music.DAO;

import com.design4music.Domain.Account;
import com.design4music.Domain.Flippo;
import com.design4music.Domain.TradeItem;

import java.util.List;

/**
 * Created by Nekkyou on 18-9-2017.
 */
public interface ITradingDao {
	/**
	 * Get a trade item by id
	 * @param id The id of the trade item
	 * @return The trade item with the specified id.
	 */
	TradeItem getTradeItem(long id);

	List<TradeItem> getAllTrades();

	/**
	 * Create a tradeitem
	 * @param item the tradeitem that needs to be created.
	 * @return The item created
	 */
	TradeItem createTradeItem(TradeItem item);

	/**
	 * Create a tradeitem with the base values.
	 * @param proposer		 : The account that started the trade
	 * @param proposerFlippo : The flippo that the proposer is offering
	 * @param receiver		 : The one that is receiving the trade offer.
	 * @param receiverFlippo : The flippo that the proposer want.
	 * @return The trade item.
	 */
	TradeItem createTradeItem(Account proposer, Flippo proposerFlippo, Account receiver, Flippo receiverFlippo);

	/**
	 * Get all the trades that this account has made.
	 * @param accountId The id of the account that you want to get the data from.
	 * @return A list of all trades the account proposed.
	 */
	List<TradeItem> getTradesFromAccount(long accountId);

	/**
	 * Get the trades that the account is the receiver of.
	 * @param accountId The id of the receiving account.
	 * @return a list of trades that have been made to this account.
	 */
	List<TradeItem> getTradesForAccount(long accountId);

	/**
	 * Get all the trades that the receiver has accepted.
	 * @param accountId The id of the proposer of the trade
	 * @return a list of all the trades that the proposer made that have been accepted and not yet handles.
	 */
	List<TradeItem> getUnhandledAcceptedTrades(long accountId);

	List<TradeItem> getAllTradeResponses(long accountId);

	/**
	 * Get all declined trades from an account.
	 * @param accountId The proposer.
	 * @return
	 */
	List<TradeItem> getDeclinedTrades(long accountId);

	/**
	 * Respond to a trade.
	 * @param response If the user wishes to accept or reject the trade.
	 */
	void respondToTrade(long tradeItemId, boolean response);
}
