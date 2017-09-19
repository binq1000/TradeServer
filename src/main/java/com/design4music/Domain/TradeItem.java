package com.design4music.Domain;

/**
 * Created by Nekkyou on 18-9-2017.
 */
public class TradeItem {
	private long id;
	private Account proposer;
	private Flippo proposerFlippo;
	private Account reciever;
	private Flippo receiverFlippo;

	public TradeItem(long id, Account proposer, Flippo proposerFlippo, Account reciever, Flippo receiverFlippo) {
		this.id = id;
		this.proposer = proposer;
		this.proposerFlippo = proposerFlippo;
		this.reciever = reciever;
		this.receiverFlippo = receiverFlippo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Account getProposer() {
		return proposer;
	}

	public void setProposer(Account proposer) {
		this.proposer = proposer;
	}

	public Flippo getProposerFlippo() {
		return proposerFlippo;
	}

	public void setProposerFlippo(Flippo proposerFlippo) {
		this.proposerFlippo = proposerFlippo;
	}

	public Account getReciever() {
		return reciever;
	}

	public void setReciever(Account reciever) {
		this.reciever = reciever;
	}

	public Flippo getReceiverFlippo() {
		return receiverFlippo;
	}

	public void setReceiverFlippo(Flippo receiverFlippo) {
		this.receiverFlippo = receiverFlippo;
	}
}
