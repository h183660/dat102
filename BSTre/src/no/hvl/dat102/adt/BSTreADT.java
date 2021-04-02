package no.hvl.dat102.adt;

import java.util.*;

public interface BSTreADT<T extends Comparable<T>> {

	/**
	 * Viser hvor mange elementer det er i treet
	 * 
	 * @return antall
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
	 * 
	 * @param element
	 * @return Returnerer en referanse til det spesifiserte elementet hvis det fins
	 *         i dette binære treet ellers returneres null.
	 */
	public T finn(T element);

	/**
	 * 
	 * @return Returnerer en referanse til minste elementet, null viss tre er tomt.
	 */
	public T finnMin();

	/**
	 * Returnerer en referanse til største elementet, null viss tre er tomt.
	 * 
	 * @return
	 */
	public T finnMaks();

	/**
	 * Fjerner de element fra dette treet hvis det fins, ellers returneres null
	 * 
	 * @param element
	 * @return Returnerer det fjernete elementet, eller null hvis det ikke fins
	 */
	public T fjern(T element);

	/**
	 * Fjerner minste elementet fra dette treet hvis det fins, ellers returneres
	 * null
	 * 
	 * @return Det minste elementet som ble fjernet, eller null
	 */
	public T fjernMin();

	/**
	 * Fjerner største elementet fra dette treet hvis det fins, ellers returneres
	 * null
	 * 
	 * @return Returnerer det største elementet, eller null
	 */
	public T fjernMaks();
}
