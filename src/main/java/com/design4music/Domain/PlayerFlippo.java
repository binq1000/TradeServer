package com.design4music.Domain;

import javax.persistence.*;

/**
 * Created by Nekkyou on 20-9-2017.
 */
@Entity
public class PlayerFlippo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account player;

	@OneToOne
	private Flippo flippo;

	private int amount;

	protected PlayerFlippo() {
		//For JPA
	}

	public PlayerFlippo(Flippo flippo, int amount) {
		this.flippo = flippo;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Flippo getFlippo() {
		return flippo;
	}

	public void setFlippo(Flippo flippo) {
		this.flippo = flippo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
