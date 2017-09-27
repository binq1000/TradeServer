package com.design4music.Domain;

/**
 * Created by Nekkyou on 25-9-2017.
 */
public class AcceptedTradeItem {
	private TradeItem tradeItem;
	private Account receiver;

	public AcceptedTradeItem(TradeItem tradeItem, Account receiver) {
		this.tradeItem = tradeItem;
		this.receiver = receiver;
	}

	//region Getters and Setters
	public TradeItem getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(TradeItem tradeItem) {
		this.tradeItem = tradeItem;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}
	//endregion
}
