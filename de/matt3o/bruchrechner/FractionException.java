package de.matt3o.bruchrechner;

public class FractionException extends Exception {
	private static final long serialVersionUID = 0L;

	public FractionException() {
		super( "Math Error: Bruch enth�lt eine 0!" );
	}
}
