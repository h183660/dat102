package no.hvl.dat102.klient;

import java.util.Random;
import no.hvl.dat102.KjedetBSTre;

public class KlientOppgave3b {

	public static void main(String[] args) {

		KjedetBSTre[] tre = new KjedetBSTre[100];
		Random terning = new Random();

		for (int j = 0; j < 100; j++) {
			tre[j] = new KjedetBSTre();
			for (int i = 0; i < 1023; i++) {
				int tall = terning.nextInt();
				tre[j].leggTil(tall);
			}
		}

		System.out.println("Den minste teoretiske høyden er: " + minTeoretiskHoyde(1023));
		System.out.println("Den største teoretiske høyden er: " + maksTeoretiskHoyde(1023));

		System.out.println("Finner den minste høyden....");
		int min = tre[0].finnHoyde();
		for (int i = 0; i < 100; i++) {
			int ny = tre[i].finnHoyde();
			if (min > ny) {
				min = ny;
			}
		}

		System.out.println("Minste høyden er: " + min);
		// Får 17 på en kjøring
		// Får 18 på en annen

		System.out.println("Finner den største høyden....");
		int maks = tre[0].finnHoyde();
		for (int i = 0; i < 100; i++) {
			int ny = tre[i].finnHoyde();
			if (maks < ny) {
				maks = ny;
			}
		}

		System.out.println("Den største høyden er: " + maks);
		// Får 29 på en kjøring
		// Får 28 på en annen
		// Får 27 på en annen...

		System.out.println("Finner den gjennomsnittelige høyden.....");
		double sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += tre[i].finnHoyde();
		}
		double gjennomsnitt = sum / 100;
		System.out.println("Gjennomsnittelig høyde er: " + gjennomsnitt);
		// Får en gjennomsnittelig høyde på 21.17 på en kjøring
		// Får 21.00 på en annen...

		System.out.println("Skriver ut verdier i et tre mellom 0 og 10000000: ");
		tre[0].skrivVerdier(0, 10000000);
		System.out.println();

		System.out.println("Skriver ut verdier i et tre mellom -10000000 og 0: ");
		tre[0].skrivVerdier(-10000000, 0);
	}

	private static int minTeoretiskHoyde(int n) {
		double svar = 2;
		for (int i = 0; i < 69; i++) {
			svar = Math.pow(2, i);
			if (svar > n) {
				return i;
			}
		}
		return -1;
	}

	private static int maksTeoretiskHoyde(int n) {
		return n - 1;
	}

}