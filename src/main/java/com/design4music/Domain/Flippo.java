package com.design4music.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Nekkyou on 18-9-2017.
 */
@Entity
public class Flippo {

	@Id
	private long id;

	protected Flippo() {
		//For JPA
	}

	public Flippo(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
