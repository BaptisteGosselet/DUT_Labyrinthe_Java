package joueurs;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import partie.ElementsPartie;

/**
 * 
 * Cette classe permet de représenter un joueur ordinateur de type T2.
 * 
 * @author Jean-François Condotta - 2021
 *
 */

public class JoueurOrdinateurT2 extends JoueurOrdinateur {

	/**
	 * Constructeur permettant de créer un joueur.
	 * 
	 * @param numJoueur Le numéro du joueur.
	 * @param nomJoueur Le nom du joueur.
	 * @param numeroImagePersonnage Le numéro de l'image représentant le joueur.
	 * @param posLignePlateau La ligne du plateau sur laquelle est positionnée le joueur.
	 * @param posColonnePlateau La colonne du plateau sur laquelle est positionnée le joueur.

	 */
	public JoueurOrdinateurT2(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "OrdiType2";
	}


	/**
	 * A Faire (date Adrien à faire)
	 * Méthode permettant de trouver les solutions pour aller à un objets sans insérer la pièce libre
	 *
	 * Renvoyer une insertion qui ne bloque pas le passage déjà disponible
	 *
	 * @return tableau des adversaires
	 */

	private int[] tabAdversaire(ElementsPartie elementsPartie) {
		int[] tabAdversaire = null ;
		if (elementsPartie.getNombreJoueurs() == 3) {
			tabAdversaire = new int[2];
			if (elementsPartie.getJoueurActuel() == 0) {
				tabAdversaire[0] = 1;
				tabAdversaire[1] = 2;
			} else if (elementsPartie.getJoueurActuel() == 1) {
				tabAdversaire[0] = 0;
				tabAdversaire[1] = 2;
			} else if (elementsPartie.getJoueurActuel() == 2) {
				tabAdversaire[0] = 0;
				tabAdversaire[1] = 1;
			}
		}else {
			tabAdversaire = new int[1];
			if(elementsPartie.getJoueurActuel() == 1){
				tabAdversaire[0]= 0 ;
			} else if (elementsPartie.getJoueurActuel() == 0){
				tabAdversaire[0] = 1 ;
			}
		}
		return tabAdversaire ;
	}


	/**
	 * A Faire (05/06 Pierre en FINI)
	 * Méthode permettant de trouver les solutions pour aller à un objet en 1 coups
	 *
	 * Essaye chaque entrée avec chaque orientation de la piece libre
	 *
	 * @return tableau de toutes les solutions en 1 coups si pas de solution renvoit null
	 */

	private int[][] avoirObjetUnCoup(ElementsPartie elementsPartie, int numJoueur){
		int[][] tabSolution = new int[112][2] ;
		int nbSolutions = 0 ;
		ElementsPartie copyElementPartie ;
		System.out.println(numJoueur);

		for(int entree=0;entree<28;entree++) { //on parcours toutes les entrees possibles
			for (int orien = 0; orien < 4; orien++) {    //on parcours les differentes orientations
				copyElementPartie = elementsPartie.copy() ; //copie du plateau
				copyElementPartie.getPieceLibre().setOrientation(orien); //definition de l'orientation de la piece libre
				copyElementPartie.insertionPieceLibre(entree, numJoueur); //insertion de la piece libre

				int colonne = 0 ;
				int ligne = 0 ;

				for (int i = 0 ; i < copyElementPartie.getObjets().length ; i ++) {
					if(copyElementPartie.getJoueurs()[numJoueur].getProchainObjet().getNumeroObjet() == copyElementPartie.getObjets()[i].getNumeroObjet()){
						colonne = copyElementPartie.getObjets()[i].getPosColonnePlateau(); //recupere la colone de l'objet
						ligne = copyElementPartie.getObjets()[i].getPosLignePlateau();  //recupere la ligne de l'objet
						break ;
					}
				}

				//System.out.println(copyElementPartie.getJoueurs()[numJoueur].getPosLigne()) ;
				//System.out.println(copyElementPartie.getJoueurs()[numJoueur].getPosColonne()) ;
				//System.out.println(colonne) ;
				//System.out.println(ligne) ;
				//System.out.println(copyElementPartie.getObjets()[copyElementPartie.getJoueurs()[numJoueur].getProchainObjet().getNumeroObjet()].getPosColonnePlateau());
				//System.out.println(copyElementPartie.getObjets()[copyElementPartie.getJoueurs()[numJoueur].getProchainObjet().getNumeroObjet()].getPosLignePlateau());


				if(copyElementPartie.getPlateau().calculeChemin(copyElementPartie.getJoueurs()[numJoueur].getPosLigne(),copyElementPartie.getJoueurs()[numJoueur].getPosColonne(),ligne,colonne)!=null){ //verifie si il existe un chemin entre le joueur et l'objet à recuperer
					tabSolution[nbSolutions][1] = entree ;
					tabSolution[nbSolutions][0] = orien ;
					nbSolutions ++ ;
					System.out.println("Vrai" + entree + " orien :" + orien) ;
				}
			}
		}
		//System.out.println(Arrays.toString(tabSolution[0]));
		if(nbSolutions > 0) return tabSolution ;
		else return null ;
	}

	/**
	 * A Faire (06/06/2021 Adrien FINI)
	 * Méthode permettant de trouver la meilleur solutions pour aller à un objet en 1 coups
	 *
	 * Compare le tableau renvoyé de avoirObjetUnCoup avec en priorité celui de bloquerDeuxAdversaire
	 * Ensuite avec bloquer un adversaire ou finalement revoit juste un couple du tableau de avoirObjetUnCoup
	 * Pour limier les calculs regarde d'abord si unAdversaireEnUnCoup revoit vrai ou faux
	 * Pareil pour deuxAdversaireEnUnCoup tu regardes juste avant d'appeler les méthodes de bloquage
	 *
	 * @return la meilleur insertion de piece pour aller objet 1 coup si tout est null renvoit null
	 */

	private int[] meilleurInsertionUnCoup(ElementsPartie elementsPartie) {
		int[] tabSolution = new int[2];
		int nbSolution = 0;
		int[] tabAdversaire = tabAdversaire(elementsPartie);
		int numJoueur = elementsPartie.getJoueurActuel();

		int[][] avoirObjetUnCoup = avoirObjetUnCoup(elementsPartie, numJoueur);
		int[][] adv1 = bloquerAdversaire(elementsPartie, tabAdversaire[0]);
		int[][] adv2 = bloquerAdversaire(elementsPartie, tabAdversaire[1]);

		if (elementsPartie.getJoueurs()[tabAdversaire[0]].getNombreObjetsRecuperes() >= elementsPartie.getJoueurs()[tabAdversaire[1]].getNombreObjetsRecuperes()) {
			if (avoirObjetUnCoup != null && adv1 != null) {
				int i = 0;
				while (avoirObjetUnCoup[i] != null) {
					int j = 0;
					i++;
					while (adv1[j] != null) {
						if (avoirObjetUnCoup[i][0] == adv1[j][0] && avoirObjetUnCoup[i][1] == adv1[j][1]) {
							tabSolution[0] = avoirObjetUnCoup[i][0];
							tabSolution[1] = avoirObjetUnCoup[i][1];
							return tabSolution;
						}
						j++;
					}
				}
			} else if (avoirObjetUnCoup != null && adv2 != null) {
				int i = 0;
				while (avoirObjetUnCoup[i] != null) {
					int j = 0;
					i++;
					while (adv2[j] != null) {
						if (avoirObjetUnCoup[i][0] == adv2[j][0] && avoirObjetUnCoup[i][1] == adv2[j][1]) {
							tabSolution[0] = avoirObjetUnCoup[i][0];
							tabSolution[1] = avoirObjetUnCoup[i][1];
							return tabSolution;
						}
						j++;
					}
				}
			}
		} else {
			if (avoirObjetUnCoup != null && adv2 != null) {
				int i = 0;
				while (avoirObjetUnCoup[i] != null) {
					int j = 0;
					i++;
					while (adv2[j] != null) {
						if (avoirObjetUnCoup[i][0] == adv2[j][0] && avoirObjetUnCoup[i][1] == adv2[j][1]) {
							tabSolution[0] = avoirObjetUnCoup[i][0];
							tabSolution[1] = avoirObjetUnCoup[i][1];
							return tabSolution;
						}
						j++;
					}
				}
			} else if (avoirObjetUnCoup != null && adv2 != null){
				int i = 0;
				while (avoirObjetUnCoup[i] != null) {
					int j = 0;
					i++;
					while (adv1[j] != null) {
						if (avoirObjetUnCoup[i][0] == adv1[j][0] && avoirObjetUnCoup[i][1] == adv1[j][1]) {
							tabSolution[0] = avoirObjetUnCoup[i][0];
							tabSolution[1] = avoirObjetUnCoup[i][1];
							return tabSolution;
						}
						j++;
					}
				}
			}
		}
		return null;
	}

	/**
	 * A Faire (06/06 Baptiste FINI)
	 * Méthode permettant de savoir si un adversaire peut avoir un objet en 1 coup
	 * Si l'adversaire en paramètre peut avoir un objet en un coup renvoit true
	 * sinon false
	 *
	 * @return boolean
	 */


	private boolean unAdversaireObjetUnCoup(ElementsPartie elementsPartie, int numJoueur){
		if (avoirObjetUnCoup(elementsPartie, numJoueur) != null) return true;
		return false;
	}



	/**
	 * A Faire (06/06 Baptiste FINI)
	 * Méthode permettant de savoir si deux adversaire peuvent avoir un objet en 1 coup
	 * Si les deux adversaires peuvent avoir un objet en un coup renvoit true
	 * sinon false
	 *
	 * @return boolean
	 */

	private boolean deuxAdversairesObjetUnCoup(ElementsPartie elementsPartie){
		int[] adv = tabAdversaire(elementsPartie);
		if(unAdversaireObjetUnCoup(elementsPartie, adv[0]) && unAdversaireObjetUnCoup(elementsPartie, adv[1]))  return true;

		return false;
	}


	/**
	 * A Faire (06/06 Rayane FINI)
	 * Méthode permettant de jouer si les adversaires sont à plus de 1 coup de leur objet
	 *
	 * appeller les méthodes pour faire les actions dans l'ordre :
	 *		on se déplace à notre objet en 1 coup si possible
	 * 		on se prépare à aller à notre objet en 2 coups si possible
	 * 		on se rapproche au maximun de notre objet
	 * 		Il faut penser que notre perso peut sauter d'un côté à l'autre et de même pour les objets
	 *
	 */

	private int[][] adversaireObjetTropCoup(ElementsPartie elementsPartie) {
		int[][] tabUnCoup=avoirObjetUnCoup(elementsPartie, elementsPartie.getJoueurActuel()) ;

		if (tabUnCoup!= null) {
			return tabUnCoup;
		}

		int[][] tabPlusDeux = new int[1][2] ;
		tabPlusDeux[0] = objetPlusDeDeux(elementsPartie);

		return tabPlusDeux ;

	}

	/**
	 * A Faire (05/06/2021 Adrien FINI)
	 * Méthode permettant de bloquer un adversaire qui peut avoir un objet en 1 coup
	 * faisant passer son potentiel objet à deux coups
	 *
	 * @return tableau possibilité de bloquage d'un  joueur si pas de possibilité --> null
	 */

	private int[][] bloquerAdversaire(ElementsPartie elementsPartie, int numJoueur){
		int[][] tabSolution = new int[150][2] ;
		int nbSolution = 0 ;
		ElementsPartie copyElementPartie ;

		if (unAdversaireObjetUnCoup(elementsPartie, numJoueur) == true){
			int[][] tabObjetUn = avoirObjetUnCoup(elementsPartie, numJoueur) ;
			for (int i = 0 ; i < 28 ; i++ ){
				for(int j = 0 ; j < 4 ; j++ ){
					copyElementPartie = elementsPartie.copy() ;
					copyElementPartie.getPieceLibre().setOrientation(j);
					copyElementPartie.insertionPieceLibre(i, 1);
					if (avoirObjetUnCoup(elementsPartie, numJoueur)== null) {
						tabSolution[nbSolution][1] = i ;
						tabSolution[nbSolution][0] = j ;
						nbSolution ++ ;
					}
				}
			}
		}
		if(nbSolution> 0) return tabSolution ;
		else return null ;
	}

	/**
	 * A Faire (05/06/2021 Adrien FINI)
	 * Méthode permettant de bloquer un adversaire qui peut avoir un objet en 1 coup
	 * faisant passer son potentiel objet à deux coups
	 * Appeller cette fonction si deuxAdversairesObjetunCoup == true
	 *
	 * @return tab possibilité de bloquage des deux joueurs si pas de possibilité --> null
	 */


	/**
	 * A Faire (date Adrien à faire)
	 * Méthode permettant de faire son déplacement si on
	 * est à plus de 2 coups de son objet
	 *
	 * @return choix pour entrée/orientation pour la pièce
	 */
	private int[] objetPlusDeDeux(ElementsPartie elementsPartie){
		int[] tabSolution = new int[2] ;
		int numJoueur = elementsPartie.getJoueurActuel() ;
		int calcul1, calcul2,calcul3, calcul4, somme1, somme2 ;
		ElementsPartie copyElementPartie ;

		int[] posObjet = {elementsPartie.getJoueurs()[numJoueur].getProchainObjet().getPosLignePlateau(), elementsPartie.getJoueurs()[numJoueur].getProchainObjet().getPosColonnePlateau()} ;
		int[] posJoueur = {elementsPartie.getJoueurs()[numJoueur].getPosLigne(), elementsPartie.getJoueurs()[numJoueur].getPosColonne()} ;

		tabSolution[0] = Math.abs(posObjet[0]) - Math.abs(posJoueur[0]) ;
		tabSolution[1] = Math.abs(posObjet[1]) - Math.abs(posJoueur[1]) ;
		for(int i = 0 ; i < 28 ; i++ ){
			for(int j = 0 ; j < 4 ; j++ ){
				copyElementPartie = elementsPartie.copy() ;
				copyElementPartie.getPieceLibre().setOrientation(j);
				copyElementPartie.insertionPieceLibre(i, 1);

				calcul1 = posObjet[0] - posJoueur[0] ;
				calcul2 = posObjet[1] - posJoueur[1] ;
				somme1 = Math.abs(calcul1) + Math.abs(calcul2) ;

				calcul3 = copyElementPartie.getJoueurs()[numJoueur].getProchainObjet().getPosLignePlateau() - copyElementPartie.getJoueurs()[numJoueur].getPosLigne() ;
				calcul4 = copyElementPartie.getJoueurs()[numJoueur].getProchainObjet().getPosColonnePlateau() - copyElementPartie.getJoueurs()[numJoueur].getPosColonne() ;
				somme2 = Math.abs(calcul3) + Math.abs(calcul4) ;

				if (somme2 < somme1){
					tabSolution[0] = Math.abs(calcul3) ;
					tabSolution[1] = Math.abs(calcul4) ;
				}
			}
		}
		return tabSolution ;
	}

	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
		int joueurActuel = elementsPartie.getJoueurActuel();

		int[] tabAdversaire = tabAdversaire(elementsPartie) ;
		int nbObjetRecupAdv1 = elementsPartie.getJoueurs()[tabAdversaire[0]].getNombreObjetsRecuperes() ;

		if (elementsPartie.getNombreJoueurs() == 3){
			int nbObjetRecupAdv2 = elementsPartie.getJoueurs()[tabAdversaire[1]].getNombreObjetsRecuperes() ;

			int[][] bloquer1 = bloquerAdversaire(elementsPartie, tabAdversaire[0]) ;
			int[][] bloquer2 = bloquerAdversaire(elementsPartie, tabAdversaire[1]) ;

			System.out.println("Premier cas") ;
			int[] meilleurInsert = meilleurInsertionUnCoup(elementsPartie) ;
			if (meilleurInsert != null) return meilleurInsert ;
			/*
			System.out.println("deuxieme cas") ;
			if (nbObjetRecupAdv1 >= 3 && nbObjetRecupAdv1 >= nbObjetRecupAdv2 && bloquer1 != null){
				return bloquer1[0] ;
			}else if (bloquer2 != null){
				return bloquer2[0] ;
			}

			System.out.println("troisième cas") ;
			int[][] objetEnUn = avoirObjetUnCoup(elementsPartie, joueurActuel) ;
			if (objetEnUn != null){
				return objetEnUn[0] ;
			}
			*/
			System.out.println("quatrième cas") ;
			return objetPlusDeDeux(elementsPartie) ;
		}
		return null ;
	}


	@Override
	public int[] choisirCaseArrivee(ElementsPartie elementsPartie){
		int numJoueur = elementsPartie.getJoueurActuel() ;
		int[][] chemin = elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[numJoueur].getPosLigne(), elementsPartie.getJoueurs()[numJoueur].getPosColonne(), elementsPartie.getJoueurs()[numJoueur].getProchainObjet().getPosLignePlateau(), elementsPartie.getJoueurs()[numJoueur].getProchainObjet().getPosColonnePlateau());
		if (chemin != null){
			int[] tab = {chemin[chemin.length-1][0], chemin[chemin.length-1][1]};
			return tab ;
		}
		int[] tab = {Utils.genererEntier(6), Utils.genererEntier(6)} ;
		return tab ;
	}

	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurOrdinateurT2(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}

}
