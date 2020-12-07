package de.stl.saar.prog3.exceptions;

public class StringIsEmptyException extends Exception {
	private static final long serialVersionUID = 4638602649610043684L;
	private static final String MESSAGE = "Der String darf nicht leer sein!";
	
	public StringIsEmptyException() {
		super(MESSAGE);
	}
}
