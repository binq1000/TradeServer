package com.design4music.Domain;

/**
 * Created by Nekkyou on 18-9-2017.
 */
public class Account {
	private long id;

	public Account(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
