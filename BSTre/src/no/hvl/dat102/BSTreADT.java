package no.hvl.dat102;

public interface BSTreADT<T extends Comparable<T>> {
	// Burde hatt javadoc her
	// anbefaler at du fyller ut med javadoc her

	/**
	 * @return Returnerer antall elementer i treet
	 */
	public int antall();

	/**
	 * 
	 * @return Returnerer sann hvis dette binære treet er tom og usann ellers.
	 */
	public boolean erTom();

	/**
	 * Legger det spesifiserte elementet på passende plass i dette binære søketreet.
	 * Like elementer blir lagt til høyre.
	 * 
	 * @param element
	 */
	public void leggTil(T element);

	/**
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * binære treet ellers returneres null.
	 * 
	 * @param element
	 * @return elementet
	 */
	public T finn(T element);

	/**
	 * Returnerer en referanse til minste elementet, null viss tre er tomt.
	 * 
	 * @return Det minste elementet, null ellers
	 */
	public T finnMin();

	/**
	 * Returnerer en referanse til største elementet, null viss tre er tomt.
	 * 
	 * @return Det største elementet, eller null
	 */
	public T finnMaks();

	/**
	 * Fjerner et element fra dette treet hvis det fins, ellers returneres null
	 * 
	 * @param element
	 * @return Returnerer det fjernete ellementet, null ellers
	 */
	public T fjern(T element);

	/**
	 * Fjerner minste elementet fra dette treet hvis det fins, ellers returneres
	 * null
	 * 
	 * @return Returnerer det minste elementet, null ellers
	 */
	public T fjernMin();

	/**
	 * Fjerner største elementet fra dette treet hvis det fins, ellers returneres
	 * null
	 * 
	 * @return Returnerer det største elementet, null ellers
	 */
	public T fjernMaks();
}
