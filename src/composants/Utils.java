package composants;

import java.util.Date;
import java.util.Random;

/**
 * 
 * Classe contenant quelques fonctions utilitaires.
 * 
 */
public class Utils {
	
	private static Random generateur=new Random((new Date().getTime()));

	/**
	 * A Faire (28/04 Baptiste Fini)
	 * 
	 * Méthode permettant de générer aléatoirement un nombre entier.
	 * 
	 * @param max Le nombre entier maximal pouvant être retourné.
	 * @return Un nombre entier compris entre 0 et max (inclus).
	 */
	public static int genererEntier(int max){
		Random entierRandom = new Random();
		return entierRandom.nextInt(max+1);

	}
	/**
	 * A Faire (30/04 Baptiste Fini)
	 * 
	 * Méthode permettant de générer un tableau d'entiers dont la longueur longTab est donnée en paramètre.
	 * Le tableau généré doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
	 * dans le tableau doit être aléatoire.
	 * 
	 * @param longTab La longueur du tableau.
	 * @return Un tableau contenant les entiers 0,...,longTab-1 placés aléatoirement dans le tableau.
	 */
	public static int[] genereTabIntAleatoirement(int longTab) {
		int tab[] = new int[longTab];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = 0;
		}
		int j = 1;
		while (j < tab.length) {
			int placement = genererEntier(longTab - 1);
			if (tab[placement] == 0) {
				tab[placement] = j;
				j += 1;
			}
		}
		return tab;
	}
	/**
	 * Programme testant la méthode genereTabIntAleatoirement.
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {
		// Un petit test ...
		int tab[]=genereTabIntAleatoirement(18);
		for (int i=0;i<tab.length;i++)
			System.out.print(tab[i]+" ");
	}

}
