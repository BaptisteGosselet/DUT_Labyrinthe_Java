package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

public class Partie {
	static double version=0.0;


	private ElementsPartie elementsPartie; // Les éléments de la partie.

	/**
	 * 
	 * A Faire (Quand Baptiste Statut)
	 * 
	 * Constructeur permettant de créer et d'initialiser une nouvelle partie.
	 */
	public Partie(){
		// Initialisation de la partie
		parametrerEtInitialiser();

		for(int i = 0 ; i < elementsPartie.getNombreJoueurs() ; i++){
			IG.changerNomJoueur(elementsPartie.getJoueurs()[i].getNumJoueur(), elementsPartie.getJoueurs()[i].getNomJoueur()) ;
			IG.changerImageJoueur(elementsPartie.getJoueurs()[i].getNumJoueur(), elementsPartie.getJoueurs()[i].getNumeroImagePersonnage());
		}
		IG.placerJoueurPrecis(0, 0 ,0 , 0 , 2);
		IG.placerJoueurPrecis(1, 0 , 6 , 2 ,2 );
		if(elementsPartie.getNombreJoueurs() > 2) IG.placerJoueurPrecis(2,6,6, 2 , 0);
		elementsPartie.getJoueurs()[0].setPosition(0,0);
		elementsPartie.getJoueurs()[1].setPosition(0,6);
		if(elementsPartie.getNombreJoueurs() > 2) elementsPartie.getJoueurs()[2].setPosition(6,6);

		IG.rendreVisibleFenetreJeu();

		IG.rendreVisibleFenetreJeu();
		IG.miseAJourAffichage();
	}

	/**
	 * Méthode permettant de paramètrer et initialiser les éléments de la partie.
	 */
	private void parametrerEtInitialiser(){
		// Saisie des différents paramètres
		Object parametresJeu[];
		parametresJeu=IG.saisirParametres();
		int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
		IG.creerFenetreJeu("- Version "+version, nombreJoueurs);

		// Création des joueurs
		Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);

		// Création des éléments de la partie
		elementsPartie=new ElementsPartie(joueurs);

	}


	/**
	 * 
	 * A Faire (02/06 Baptiste FINI)
	 * 
	 * Méthode permettant de lancer une partie.
	 */
	public void lancer() {
		String[] message = {
				"",
				"Début de la partie",
				"",
				""
		};
		IG.afficherMessage(message);
		IG.miseAJourAffichage();

		boolean continuer = true;
		while (continuer) {
			for (int i = 0; i < this.elementsPartie.getNombreJoueurs(); i++) {
				IG.changerJoueurSelectionne(i);
				this.elementsPartie.setJoueurActuel(i);

				message = new String[]{
						"",
						this.elementsPartie.getJoueurs()[i].getNomJoueur(),
						"Pivotez la pièce en cliquant dessus",
						"et choissisez une entrée."
				};
				IG.afficherMessage(message);
				IG.miseAJourAffichage();

				int[] choixInsertion ;
				choixInsertion = this.elementsPartie.getJoueurs()[i].choisirOrientationEntree(this.elementsPartie);
				//System.out.println(choixInsertion[0]);
				//System.out.println(choixInsertion[1]);

				this.elementsPartie.insertionPieceLibre(choixInsertion[1]);
				IG.miseAJourAffichage();

				message = new String[]{
						"",
						this.elementsPartie.getJoueurs()[i].getNomJoueur(),
						"Choissisez une case",
						"où vous déplacer."

				};
				IG.afficherMessage(message);
				IG.deselectionnerFleche();
				IG.miseAJourAffichage();

				int[] choixArrivee;
				choixArrivee = this.elementsPartie.getJoueurs()[i].choisirCaseArrivee(elementsPartie);
				while(this.elementsPartie.getPlateau().calculeChemin(this.elementsPartie.getJoueurs()[i].getPosLigne(), this.elementsPartie.getJoueurs()[i].getPosColonne(), choixArrivee[0], choixArrivee[1]) == null){ //Tant qu'une case disponible n'a pas été sélectionnée
					IG.miseAJourAffichage();
					choixArrivee = this.elementsPartie.getJoueurs()[i].choisirCaseArrivee(elementsPartie); //Choisir une case
				}

				//Supprimer toutes les billes
				for(int a=0; a<7; a++){
					for(int b=0; b<7; b++){
						for(int c=0; c<3; c++){
							for(int d=0; d<3; d++){
								IG.supprimerBilleSurPlateau(a,b,c,d);
							}
						}
					}
				}

				//Faire le déplacement
				int[][] deplacement = this.elementsPartie.getPlateau().calculeChemin(this.elementsPartie.getJoueurs()[i].getPosLigne(), this.elementsPartie.getJoueurs()[i].getPosColonne(), choixArrivee[0], choixArrivee[1]);
				for(int d=1; d<deplacement.length; d++){
					IG.placerBilleSurPlateau(this.elementsPartie.getJoueurs()[i].getPosLigne(), this.elementsPartie.getJoueurs()[i].getPosColonne(), 1, 1, i);
					this.elementsPartie.getJoueurs()[i].setPosition(deplacement[d][0], deplacement[d][1]);
					if (this.elementsPartie.getJoueurs()[i].getNumJoueur() == 0) IG.placerJoueurPrecis(i,deplacement[d][0],deplacement[d][1],0,2);
					else if (this.elementsPartie.getJoueurs()[i].getNumJoueur() == 1) IG.placerJoueurPrecis(i,deplacement[d][0],deplacement[d][1],2,2);
					else if (this.elementsPartie.getJoueurs()[i].getNumJoueur() == 2) IG.placerJoueurPrecis(i,deplacement[d][0],deplacement[d][1],2,0);
					IG.pause(200);
					IG.miseAJourAffichage();
				}


				//Si le joueur est sur la case d'un objet
				if(this.elementsPartie.getJoueurs()[i].getPosLigne() == this.elementsPartie.getJoueurs()[i].getProchainObjet().getPosLignePlateau() && this.elementsPartie.getJoueurs()[i].getPosColonne() == this.elementsPartie.getJoueurs()[i].getProchainObjet().getPosColonnePlateau()){
					IG.changerObjetJoueurAvecTransparence(i, this.elementsPartie.getJoueurs()[i].getProchainObjet().getNumeroObjet() ,this.elementsPartie.getJoueurs()[i].getNombreObjetsRecuperes());
					IG.enleverObjetPlateau(this.elementsPartie.getJoueurs()[i].getPosLigne(), this.elementsPartie.getJoueurs()[i].getPosColonne());
					this.elementsPartie.getJoueurs()[i].getProchainObjet().enleveDuPlateau();
					this.elementsPartie.getJoueurs()[i].recupererObjet();



					//Dire que l'objet a été récupéré
					message = new String[]{
							"",
							"Vous avez récupéré",
							"un objet !",
							""
					};
					IG.afficherMessage(message);
					IG.miseAJourAffichage();
				}

				IG.miseAJourAffichage();


				//Fin de tour du joueur


				//Vérifier si la partie est terminée
				if ((this.elementsPartie.getJoueurs()[i].getNombreObjetsRecuperes() == 6 && this.elementsPartie.getNombreJoueurs() == 3) || (this.elementsPartie.getJoueurs()[i].getNombreObjetsRecuperes() == 9 && this.elementsPartie.getNombreJoueurs() == 2)) {
						continuer = false;

						message = new String[]{
								"",
								"C'est terminé !",
								"Cliquer pour quitter ...",
								""
						};

						IG.afficherMessage(message);
						IG.afficherGagnant(i);
						IG.miseAJourAffichage();
						IG.attendreClic();
						IG.fermerFenetreJeu();
					}
				IG.pause(5) ;
			}
		}
	}

	/**
	 * 
	 * Programme principal permettant de lancer le jeu.
	 * 
	 * @param args Les arguments du programmes.
	 */
	public static void main(String[] args) {
		//while(true){
		Partie partie=new Partie();
		partie.lancer();
		//}
	}

}
