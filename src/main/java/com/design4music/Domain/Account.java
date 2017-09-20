package com.design4music.Domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nekkyou on 18-9-2017.
 */
@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PlayerFlippo> playerFlippos;


	//region Getters and setters
	protected Account() {
		// no-args constructor required by JPA spec
		// this one is protected since it shouldn't be used directly
	}

	public Account(long id) {
		this.id = id;
	}

	public Account(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<PlayerFlippo> getPlayerFlippos() {
		return playerFlippos;
	}

	public void setPlayerFlippos(Set<PlayerFlippo> playerFlippos) {
		this.playerFlippos = playerFlippos;
	}
	//endregion
}
