package de.matt3o.bruchrechner;

public class Fraction {
	/** Deutsch: Z�hler */
	private int numerator;
	
	/** Deutsch: Nenner */
	private int denominator;
	
	/**
	 * Erzeugt eine neue Fraction Instance
	 * 
	 * @param numerator Der Z�hler
	 * @param denominator Der Nenner
	 * @throws FractionException Wird ausgel�st, wenn der Nenner oder Z�hler eine 0 enth�lt.
	 */
	public Fraction( int numerator, int denominator ) throws FractionException {
		if ( numerator == 0 || denominator == 0 ){
			throw new FractionException();
		}
		
		//Test
		
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * Gibt den numerator (Z�hler) zur�ck.
	 * 
	 * @return numerator (Z�hler)
	 */
	public int getNumerator(){
		return numerator;
	}
	
	/**
	 * Gibt den denominator (Nenner) zur�ck.
	 * @return denominator (Nenner)
	 */
	public int getDenominator(){
		return denominator;
	}
	
	/**
	 * Gibt das GGF zur�ck.
	 * 
	 * @author http://de.wikipedia.org/wiki/Euklidischer_Algorithmus#Rekursive_Variante
	 * @return Den GGF
	 */
	private int getGGF( int a, int b ){
		if ( b == 0 )
			return a;
		else
			return getGGF( b, a % b );
	}
	
	/**
	 * Gibt das GGF zur�ck
	 * @return Den GGF
	 */
	public int getGGF(){
		return getGGF( getNumerator(), getDenominator() );
	}
	
	/**
	 * K�rtzt den Aktuellen bruch.
	 * 
	 * @return Ein neuer - aber gek�rztz Bruch.
	 */
	public Fraction trim(){
		int ggf = getGGF();
		Fraction resul;
		try {
			resul = new Fraction( getNumerator()/ggf , getDenominator()/ggf );
		} catch (FractionException e) {
			e.printStackTrace();
			return null;
		}
		
		return resul;
	}
	
	
	/**
	 * Addiert 2 Br�che miteinander
	 * Es k�nnen nur Br�che miteinander Multipliziert werden, wenn die Z�hler gleich sein!
	 * 
	 * @param summand2 Den Wert, mit dem der Bruch Addiert werden soll.
	 * @return Ein neuen Bruch, wo beide Br�che Addiert werden.
	 * @throws FractionException Wird geworfen, wenn der neue Bruch eine 0 enth�lt.
	 * @throws MathException Wird geworfen, wenn nenner und z�hler ungleich sind.
	 */
	public Fraction addition( Fraction summand2 ) throws FractionException, MathException{
		int numerator;
		int denominator;
		
		if ( summand2.getDenominator() == this.getDenominator() ){
			numerator = summand2.getNumerator() + this.getNumerator();
			denominator = this.getDenominator();
			return new Fraction( numerator, denominator ).trim();
		}
		
		throw new MathException( "Die beiden Nenner m�ssen gleich sein!" );
	}
	
	/**
	 * Subtrahiert 2 Br�che miteinander.
	 * 
	 * @param subtrahend Der Subtrahend (2. Zahl, mit der der Bruch Subtrahiert wird).
	 * @return Ein neuen Bruch, wo beide Br�che Addiert werden.
	 * @throws FractionException Wird geworfen, wenn der neue Bruch eine 0 enth�lt.
	 * @throws MathException Wird geworfen, wenn nenner und z�hler ungleich sind.
	 */
	public Fraction subtract( Fraction subtrahend ) throws FractionException, MathException{
		if ( subtrahend.getDenominator() == this.getDenominator() ){
			numerator = this.getNumerator() - subtrahend.getNumerator();
			denominator = this.getDenominator();
			
			return new Fraction( numerator, denominator );
		}
		
		throw new MathException( "Die beiden Nenner m�ssen gleich sein!" );
	}
	
	/**
	 * Multipliziert die beiden Br�che miteinander.
	 * 
	 * @param multiplier Der Multiplikator (2. Zahl, mit der der Bruch Multipliziert wird.)
	 * @return Ein neuer Burch, wo beide Br�che Multipliziert werden.
	 * @throws FractionException Wird geworfen, wenn der neue Bruch eine 0 enth�lt.
	 */
	public Fraction multiplicat( Fraction multiplier ) throws FractionException{
		int numerator = multiplier.getNumerator() * this.getNumerator();
		int denominator = multiplier.getDenominator() * this.getDenominator();
		
		return new Fraction( numerator, denominator ).trim();
	}
	
	/**
	 * Dividiert die beiden Br�che miteinander.
	 * @param divisor Der Divisor (2. Zahl, mit der der Bruch Division wird.)
	 * @return Ein neuer Bruch, wo beide Br�che Multipliziert werden.
	 * @throws FractionException Wird geworfen, wenn ein neuer Bruch eine 0 enth�lt.
	 */
	public Fraction division( Fraction divisor ) throws FractionException{
		Fraction reciprocalValue = new Fraction( divisor.getDenominator(), divisor.getNumerator() );
		
		return multiplicat( reciprocalValue ).trim();
	}
		
	@Override
	public String toString(){
		return "Fraction: [" + numerator + "/" + denominator + "]";
	}
}
