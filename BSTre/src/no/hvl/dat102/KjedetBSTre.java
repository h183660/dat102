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

	/**
	 * Oppretter et tomt binært søketre.
	 */
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/**
	 * Oppretter et binært søketre med en node..
	 * 
	 * @param element
	 */
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

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
		antall--;
		return svar;
	}

	/**
	 * Rekursiv metode for å fjerne et element fra det binaere treet
	 * 
	 * @param element
	 * @param p
	 * @return Det fjernete elementet
	 */
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

	@Override
	public T fjernMin() {
		T svar = null;
		BinaerTreNode<T> forrige = rot;
		BinaerTreNode<T> aktuell = rot;

		// Går helt til venstre
		while (aktuell.getVenstre() != null) {
			forrige = aktuell;
			aktuell = aktuell.getVenstre();
		}

		// Skjekker om noden har barn til høyre eller ikke
		if (aktuell.getHoyre() != null) {
			svar = aktuell.getElement();
			// Linker den minste sitt høyrebarn, som forgjengeren sitt venstrebarn
			forrige.setVenstre(aktuell.getHoyre());
		} else {
			svar = aktuell.getElement();
			// Fjerer venstrebarn referansen fra forgjengeren ettersom den aktuelle ikke har
			// barn til høyre eller venstre
			forrige.setVenstre(null);
		}

		svar = aktuell.getElement();
		aktuell = null;
		antall--;
		return svar;
	}//

	@Override
	public T fjernMaks() {
		T svar = null;
		BinaerTreNode<T> forrige = rot;
		BinaerTreNode<T> aktuell = rot;

		// Går helt til høyre
		while (aktuell.getHoyre() != null) {
			forrige = aktuell;
			aktuell = aktuell.getHoyre();
		}

		// Skjekker om noden har venstrebarn eller ikke
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

	@Override
	public T finnMin() {
		BinaerTreNode<T> aktuell = rot;

		while (aktuell.getVenstre() != null) {
			aktuell = aktuell.getVenstre();
		}
		return aktuell.getElement();

	}//

	@Override
	public T finnMaks() {
		BinaerTreNode<T> aktuell = rot;

		while (aktuell.getHoyre() != null) {
			aktuell = aktuell.getHoyre();
		}
		return aktuell.getElement();
	}//

	@Override
	public T finn(T element) {
		return finnRek(element, rot);
	}

	/**
	 * Rekursiv metode for å finne et element i et binaert tre
	 * 
	 * @param element
	 * @param p
	 * @return
	 */
	private T finnRek(T element, BinaerTreNode<T> p) {
		if (p != null) {
			int sml = element.compareTo(p.getElement());
			if (sml == 0) {
				return p.getElement();
			} else if (sml < 0) {
				return finnRek(element, p.getVenstre());
			} else {
				return finnRek(element, p.getHoyre());
			}
		}
		return null;
	}

	/**
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * BS-treet, null ellers. Uten bruk av rekursjon.
	 * 
	 * @param element
	 * @return Returnerer elementet hvis funnet, null ellers.
	 */
	public T finn2(T element) {
		// TODO
		return null;
	}

	/**
	 * Finner hoyden på det binaere treet
	 * 
	 * @return Hoyden til treet i int
	 */
	public int finnHoyde() {
		return finnHoydeRek(rot);
	}

	/**
	 * Rekursiv metode for å finne hoyden
	 * 
	 * @param BinaerTreNode p
	 * @return Returnerer hoyden på den delen som er hoyest.
	 */
	public int finnHoydeRek(BinaerTreNode<T> p) {
		if (p == null) {
			return -1;
		} else {
			return Math.max(finnHoydeRek(p.getVenstre()), finnHoydeRek(p.getHoyre())) + 1;
		}
	}

	/**
	 * Skriver ut verdier i BS-Treet mellom en nedre og øvre grense.
	 * 
	 * @param nedre grense
	 * @param øvre  grense
	 */
	public void skrivVerdier(T nedre, T ovre) {
		skrivVerdierRek(rot, nedre, ovre);
	}

	/**
	 * Privat rekursiv metode for å skrive ut verdier i BS-Treet mellom en nedre og
	 * øvre grense.
	 * 
	 * @param BinaerTreNode
	 * @param min
	 * @param maks
	 */
	private void skrivVerdierRek(BinaerTreNode<T> t, T min, T maks) {
		// Den originale metoden går gjennom alt rekursivt selv om det ikke er
		// nødvendig, f.eks den gjør rekursive kall til venstre og til høyre, selv om
		// den aktuelle noden er høyere enn max verdien allerede, i dette
		// tilfellet er det ikke nødvendig å gi et rekursivt kall til høyrebarnet.
		if (t != null) {
			int smlmin = t.getElement().compareTo(min);
			int smlmax = t.getElement().compareTo(maks);

			if (smlmin > 0) {
				skrivVerdierRek(t.getVenstre(), min, maks);
			}

			if ((smlmin >= 0) && (smlmax <= 0)) {
				System.out.print(t.getElement() + " ");
			}

			if (smlmax <= 0) {
				skrivVerdierRek(t.getHoyre(), min, maks);
			}
		}

	}

	/**
	 * Skriver ut hele treet med inorden-notasjon.
	 */
	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	/**
	 * Rekursiv metode for å vise inorden.
	 * 
	 * @param p
	 */
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
}