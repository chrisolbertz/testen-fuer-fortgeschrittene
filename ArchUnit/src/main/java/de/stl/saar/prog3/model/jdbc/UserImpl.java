package de.stl.saar.prog3.model.jdbc;

import de.stl.saar.prog3.model.interfaces.User;

/**
 * Eine einfache Klasse fuer die Benutzer der Webseite.
 * @author Christopher
 *
 */
public class UserImpl implements User {
	private long userId;
	private String username;
	
	public UserImpl() {
		super();
	}
	
	public UserImpl(String username) {
		super();
		this.username = username;
	}

	@Override
	public long getUserId() {
		return userId;
	}
	
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
}
