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
	 * @return
	 */
	public int getDenominator(){
		return denominator;
	}
	
	/**
	 * Gibt das GGF zur�ck.
	 * 
	 * @author http://de.wikipedia.org/wiki/Euklidischer_Algorithmus#Rekursive_Variante
	 * @return
	 */
	private int getGGF( int a, int b ){
		if ( b == 0 )
			return a;
		else
			return getGGF( b, a % b );
	}
	
	public int getGGF(){
		return getGGF( getNumerator(), getDenominator() );
	}
	
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
	
	public int addition( Fraction summand2 ) throws FractionException{
		return getGGF( 3, 5);
	}
	
	@Override
	public String toString(){
		return "Fraction: [" + numerator + "/" + denominator + "]";
	}
}
