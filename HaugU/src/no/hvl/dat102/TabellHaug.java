package no.hvl.dat102;

public class TabellHaug<T extends Comparable<T>> {
	// Lager en minimumshaug

	private T[] data;
	private int antall;

	private static final int STDK = 100;

	public TabellHaug() {
		data = (T[]) new Comparable[STDK];
		antall = 0;
	}

	/**
	 * Legger til et element i haugen
	 * 
	 * @param el
	 */
	public void leggTilElement(T el) {
		if (antall == data.length)
			utvidTabell();
		data[antall] = el; // Plasser den nye helt sist
		antall++;
		if (antall > 1)
			reparerOpp(); // Bytt om oppover hvis nødvendig
	}

	/**
	 * Dobler tabellen ved behov for utviding
	 */
	private void utvidTabell() {
		int lengde = data.length;
		T[] ny = (T[]) new Comparable[2 * lengde];
		for (int i = 0; i < antall; i++)
			ny[i] = data[i];
		data = ny;
	}

	private void reparerOpp() {
		int node = antall - 1; // Start på det siste elementet (det som ble lagt til)
		T temp = data[node]; // Midlertidig variabel til bytting

		// Løkke som beveger til den enten kommer til roten, eller forelderen til den
		// aktuelle noden er større til den aktuelle.
		while ((node != 0) && temp.compareTo(data[(node - 1) / 2]) < 0) {
			data[node] = data[(node - 1) / 2];
			node = (node - 1) / 2;
		}
		data[node] = temp;
	}

	/**
	 * Fjerner minste elementet i haugen
	 * 
	 * @return Det minste elementet
	 */
	public T fjernMinste() {
		T svar = null;
		if (!erTom()) {
			svar = data[0];
			data[0] = data[antall - 1];
			reparerNed(); // Bytter om nedover hvis nødvendig
			antall--;
		}
		return svar;
	}

	/**
	 * Finner minste tabellen i haugen
	 * 
	 * @return Det minste elementet
	 */
	public T finnMinste() {
		T svar = null;
		if (!erTom()) {
			svar = data[0];
		}
		return svar;
	}

	private void reparerNed() {
		T hjelp;
		boolean ferdig = false;
		int forelder = 0; // Start i roten og sml med neste nivå
		int minbarn;
		int vbarn = forelder * 2 + 1;
		int hbarn = vbarn + 1;
		while ((vbarn < antall) && !ferdig) { // Har flere noder lenger nede
			minbarn = vbarn;

			if ((hbarn < antall) && ((data[hbarn]).compareTo(data[vbarn]) < 0))
				minbarn = hbarn;
			// Har funnet det "minste" av barna. Sml med forelder

			if ((data[forelder]).compareTo(data[minbarn]) <= 0)
				ferdig = true;
			else { // Bytt om og gå videre nedover hvis forelder er for stor
				hjelp = data[minbarn];
				data[minbarn] = data[forelder];
				data[forelder] = hjelp;
				forelder = minbarn;
				vbarn = forelder * 2 + 1;
				hbarn = vbarn + 1;
			}
		}
	}

	/**
	 * Metode som viser om haugen er tom eller ikke
	 * 
	 * @return Boolean true om haugen er tom, false hvis den ikke er tom.
	 */
	public boolean erTom() {
		return antall == 0;
	}

	/**
	 * Skriver ut tabellen
	 */
	public void skrivTab() {
		// Hjelpemetode til test
		for (int i = 0; i < antall; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}
}