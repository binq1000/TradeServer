package com.design4music.Domain;

import javax.persistence.*;

/**
 * Created by Nekkyou on 18-9-2017.
 */
@Entity
public class TradeItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private Account proposer;

	@OneToOne
	private Flippo proposerFlippo;

	@OneToOne
	private Account reciever;

	@OneToOne
	private Flippo receiverFlippo;

	protected TradeItem() {
		//For JPA
	}

	public TradeItem(long id, Account proposer, Flippo proposerFlippo, Account reciever, Flippo receiverFlippo) {
		this.id = id;
		this.proposer = proposer;
		this.proposerFlippo = proposerFlippo;
		this.reciever = reciever;
		this.receiverFlippo = receiverFlippo;
	}

	public TradeItem(Account proposer, Flippo proposerFlippo, Account reciever, Flippo receiverFlippo) {
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
