package no.hvl.dat102;

import java.util.Iterator;

import no.hvl.dat102.adt.BSTreADT;

//********************************************************************
// KjedetBinærSøkeTre.java        
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>, Iterable<T> {

	private int antall;
	private BinaerTreNode<T> rot;

	/**
	 * @return the rot
	 */
	public BinaerTreNode<T> getRot() {
		return rot;
	}

	/**
	 * @param rot the rot to set
	 */
	public void setRot(BinaerTreNode<T> rot) {
		this.rot = rot;
	}

	/******************************************************************
	 * Oppretter et tomt binært søketre.
	 ******************************************************************/
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/******************************************************************
	 * Oppretter et binært søketre med en node..
	 ******************************************************************/
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære trett er tomt og usann ellers.
	 *****************************************************************/
	@Override
	public int antall() {
		return antall;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære treet er tom og usann ellers.
	 *****************************************************************/
	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	/**********************************************************************
	 * Legger det spesifiserte elementet på passende plass i BS-treet. Like
	 * elementer blir lagt til høyre. Bruk av rekursiv hjelpemetode.
	 ********************************************************************/
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {
		if (p == null) {
			return new BinaerTreNode<T>(element);
		} else if (element.compareTo(p.getElement()) < 0) {
			p.setVenstre(leggTilRek(p.getVenstre(), element));
		} else {
			p.setHoyre(leggTilRek(p.getHoyre(), element));
		}
		return p;
	}

	/******************************************************************
	 * Legger det spesifiserte elementet på passende plass i dette binære søketreet.
	 * Like elementer blir lagt til høyre.
	 ******************************************************************/

	public void leggTil2(T element) {
		//
	}

	@Override
	public T fjern(T element) {
		// Søk med rekursiv hjelpemetode
		T svar = null;
		if (rot.getElement().equals(element)) {
			svar = rot.getElement();
			rot = null;
		} else {
			svar = fjernRek(element, rot);
		}

		return svar;
	}

	private T fjernRek(T element, BinaerTreNode<T> p) {
		T svar = null;
		if (p != null) {
			int sml = element.compareTo(p.getElement());
			if (sml == 0) {
				svar = p.getElement();
			} else if (sml < 0) {
				svar = finnRek(element, p.getVenstre());
			} else {
				svar = finnRek(element, p.getHoyre());
			}
		}
		return svar;
	}

	/******************************************************************
	 * Fjerner noden med minste verdi fra dette binære søketreet.
	 *********************************************************************/
	@Override
	public T fjernMin() {
		T svar = null;
		BinaerTreNode<T> forrige = rot;
		BinaerTreNode<T> aktuell = rot;

		while (aktuell.getVenstre() != null) {
			forrige = aktuell;
			aktuell = aktuell.getVenstre();
		}

		if (aktuell.getHoyre() != null) {
			svar = aktuell.getElement();
			forrige.setVenstre(aktuell.getHoyre());
		} else {
			svar = aktuell.getElement();
			forrige.setVenstre(null);
		}

		svar = aktuell.getElement();
		aktuell = null;
		antall--;
		return svar;
	}//

	/******************************************************************
	 * Fjerner noden med største verdi fra dette binære søketreet.
	 ******************************************************************/
	@Override
	public T fjernMaks() {
		T svar = null;
		BinaerTreNode<T> forrige = rot;
		BinaerTreNode<T> aktuell = rot;

		while (aktuell.getHoyre() != null) {
			forrige = aktuell;
			aktuell = aktuell.getHoyre();
		}

		if (aktuell.getHoyre() != null) {
			svar = aktuell.getElement();
			forrige.setHoyre(aktuell.getHoyre());
		} else {
			svar = aktuell.getElement();
			forrige.setHoyre(null);
		}

		svar = aktuell.getElement();
		aktuell = null;
		antall--;
		return svar;
	}//

	/******************************************************************
	 * Returnerer det minste elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMin() {
		BinaerTreNode<T> aktuell = rot;

		while (aktuell.getVenstre() != null) {
			aktuell = aktuell.getVenstre();
		}
		return aktuell.getElement();

	}//

	/******************************************************************
	 * Returnerer det største elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMaks() {
		BinaerTreNode<T> aktuell = rot;

		while (aktuell.getHoyre() != null) {
			aktuell = aktuell.getHoyre();
		}
		return aktuell.getElement();
	}//

	/*******************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det finst i dette
	 * BS-treet, null ellers. Bruk av rekursjon /
	 ******************************************************************************/
	@Override
	public T finn(T element) {
		// Søk med rekursiv hjelpemetode
		return finnRek(element, rot);
	}

	private T finnRek(T element, BinaerTreNode<T> p) {
		T svar = null;
		if (p != null) {
			int sml = element.compareTo(p.getElement());
			if (sml == 0) {
				svar = p.getElement();
			} else if (sml < 0) {
				svar = finnRek(element, p.getVenstre());
			} else {
				svar = finnRek(element, p.getHoyre());
			}
		}
		return svar;
	}

	/************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * BS-treet, null ellers. Uten bruk av rekursjon. /
	 ************************************************************************/
	public T finn2(T element) {
		// TODO
		return null;
	}

	public int finnHoyde() {
		return finnHoydeRek(rot);
	}

	public int finnHoydeRek(BinaerTreNode<T> p) {
		if (p == null) {
			return -1;
		} else {
			int venstreHoyde = finnHoydeRek(p.getVenstre());
			int hoyreHoyde = finnHoydeRek(p.getHoyre());
			return Math.max(finnHoydeRek(p.getVenstre()), finnHoydeRek(p.getHoyre())) + 1;
		}
	}

	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	private void visInorden(BinaerTreNode<T> p) {
		if (p != null) {
			visInorden(p.getVenstre());
			System.out.print(" " + p.getElement());
			visInorden(p.getHoyre());
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new InordenIterator<T>(rot);

	}
}// class
