package partie;

import java.util.Random;
import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

/**
 * 
 * Cette classe permet de représenter un ensemble d'élements composant une partie de jeu.
 * 
 */
public class ElementsPartie {

	private Joueur[] joueurs; 	//Les joueurs de la partie.
	private Objet[] objets; 	//Les 18 objets de la partie dans l'ordre de leurs numéros.
	private Plateau plateau; 	//Le plateau des pièces.
	private Piece pieceLibre; 	//La pièce libre.
	private int nombreJoueurs; 	//Le nombre de joueurs.
	private int joueurActuel ;


	/**
	 * 
	 * A Faire (31/05/2021 Pierre FINI)
	 *  
	 * Constructeur permettant de générer et d'initialiser l'ensemble des éléments d'une partie (sauf les joueurs qui sont donnés en paramètres).
	 * 
	 * Un plateau est créé en placant 49 pièces de manière aléatoire (utilisation de la méthode placerPiecesAleatoierment de la classe Plateau).
	 * La pièce restante (celle non présente sur le plateau) est affectée à la pièce libre.
	 * Les 18 objets sont créés avec des positions aléatoires sur le plateau (utilisation de la méthode Objet.nouveauxObjets)
	 * et distribuées aux différents joueurs (utilisation de la méthode attribuerObjetsAuxJoueurs).
	 * 
	 * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribués (c'est au constructeur de le faire).
	 */
	public ElementsPartie(Joueur[] joueurs) {
		this.nombreJoueurs = joueurs.length;
		this.joueurs = new Joueur[nombreJoueurs];
		this.joueurs = joueurs ;
		plateau = new Plateau();
		this.pieceLibre = plateau.placerPiecesAleatoirement();
		this.joueurActuel = 0 ;

		this.objets = Objet.nouveauxObjets();
		for (int i = 0 ; i < 18 ; i++){
			IG.placerObjetPlateau(objets[i].getNumeroObjet(), objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau()) ;
		}
		attribuerObjetsAuxJoueurs();


	}

	/**
	 * Un simple constructeur.
	 * 
	 * @param joueurs Les joueurs de la partie.
	 * @param objets Les 18 objets de la partie.
	 * @param plateau Le plateau de jeu.
	 * @param pieceLibre La pièce libre (la pièce hors plateau).
	 */
	public ElementsPartie(Joueur[] joueurs,Objet[] objets,Plateau plateau,Piece pieceLibre) {
		this.joueurs=joueurs;
		nombreJoueurs=joueurs.length;
		this.objets=objets;
		this.plateau=plateau;
		this.pieceLibre=pieceLibre;
	}

	/**
	 * A Faire (31/05/2021 Pierre FINI)
	 * 
	 * Méthode permettant d'attribuer les objets aux différents joueurs de manière aléatoire.
	 */
	private void attribuerObjetsAuxJoueurs(){
		int k = 0 ;

		for (int i = 0 ; i < this.nombreJoueurs ; i ++){
			if (this.nombreJoueurs == 3) {
				Objet[] tabObjetJoueur = new Objet[6];
				for (int j = 0; j < 6; j++) {
					IG.changerObjetJoueur(i, this.objets[k].getNumeroObjet(), j);
					tabObjetJoueur[j] = this.objets[k];
					k++;
				}
				joueurs[i].setObjetsJoueur(tabObjetJoueur);
			} else {
				Objet[] tabObjetJoueur = new Objet[9];
				for (int j = 0; j < 9; j++) {
					IG.changerObjetJoueur(i, this.objets[k].getNumeroObjet(), j);
					tabObjetJoueur[j] = this.objets[k];
					k++;
				}
				joueurs[i].setObjetsJoueur(tabObjetJoueur);
			}
		}
	}

	/**
	 * A Faire (29/05/2021 Adrien Fini)
	 * 
	 * Méthode permettant de récupérer les joueurs de la partie.
	 * @return Les joueurs de la partie.
	 */
	public Joueur[] getJoueurs() {
		return joueurs ;
	}


	/**
	 * A Faire (29/05/2021 Adrien Fini)
	 * 
	 * Méthode permettant de récupérer les objets d'un joueur.
	 * @return Les objets de la partie.
	 */
	public Objet[] getObjets() {
		return objets ;
	}


	/**
	 * A Faire (29/05/2021 Adrien Fini)
	 * 
2	 * Méthode permettant de récupérer le plateau de pièces de la partie.
	 * @return Le plateau de pièces.
	 */
	public Plateau getPlateau() {
		return plateau ;
	}


	/**
	 * A Faire (29/05/2021 Adrien Fini)
	 * 
	 * Méthode permettant de récupérer la pièce libre de la partie.
	 * @return La pièce libre.
	 */
	public Piece getPieceLibre() {
		return pieceLibre ;
	}


	/**
	 * A Faire (29/05/2021 Adrien Fini)
	 * 
	 * Méthode permettant de récupérer le nombre de joueurs de la partie.
	 * @return Le nombre de joueurs.
	 */
	public int getNombreJoueurs() {
		return nombreJoueurs ;
	}

	public int getJoueurActuel() {
		return joueurActuel;
	}

	public void setJoueurActuel(int joueurActuel) {
		this.joueurActuel = joueurActuel;
	}



	/**
	 * A Faire (01/06/2021 Rayane/Adrien Fini)
	 * 
	 * Méthode modifiant les différents éléments de la partie suite à l'insertion de la pièce libre dans le plateau.
	 * 
	 * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
	 */
	public void insertionPieceLibre(int choixEntree){
		Piece pieceLibreTemp ;

		//Recherche des cases d'entrées et insertion piece dans les différents cas
			pieceLibreTemp = getPieceLibre() ;
			pieceLibreTemp.setOrientation(IG.recupererOrientationPieceHorsPlateau()) ;

			if (choixEntree <= 6) { //Cases d'entrées en haut
				this.pieceLibre = this.plateau.getPiece(6, choixEntree) ;
				IG.changerPieceHorsPlateau(this.pieceLibre.getModelePiece(), this.pieceLibre.getOrientationPiece());

				for(int i = 5 ; i > -1 ; i--) {
					this.plateau.positionnePiece(this.plateau.getPiece(i,choixEntree), i+1, choixEntree );
					IG.changerPiecePlateau(i+1, choixEntree,this.plateau.getPiece(i,choixEntree).getModelePiece(), this.plateau.getPiece(i,choixEntree).getOrientationPiece());
				}

				this.plateau.positionnePiece(pieceLibreTemp,0, choixEntree);
				IG.changerPiecePlateau(0,choixEntree,pieceLibreTemp.getModelePiece(),pieceLibreTemp.getOrientationPiece());

				//si objet présent
				int jTemp = -10;
				for (int j = 0 ; j < this.objets.length ; j ++){
					if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == 6 && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(0, choixEntree);
						jTemp = j ;
						break ;
					}
				}
				//si objet
				for (int i = 5 ; i > -1 ; i--) {
					for (int j = 0; j < this.objets.length; j++) {
						if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == i && j != jTemp && this.objets[j].surPlateau()==true) {
							this.objets[j].positionneObjet(i+1, choixEntree);
						}
					}
				}
				//affichage qui doit se refaire en cas de superposition temporaire d'objet
				if(jTemp >= 0 && jTemp <= 17) {
					IG.placerObjetPlateau(jTemp,0,choixEntree);
				}

				//si joueur
				jTemp = -10 ;
				for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
					if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == 6 ) {
						this.joueurs[j].setPosition(0, choixEntree);
						if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,0,choixEntree,0,2);
						else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,0,choixEntree,2,2);
						else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,0,choixEntree,2,0);
						jTemp = j ;
						break ;
					}
				}
				//si joueur
				for (int i = 5 ; i > -1 ; i--) {
					for (int j = 0; j < this.getNombreJoueurs(); j++) {
						if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == i && jTemp != j) {
							this.joueurs[j].setPosition(i+1, choixEntree);
							if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,i+1,choixEntree,0,2);
							else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,i+1,choixEntree,2,2);
							else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,i+1,choixEntree,2,0);
						}
					}
				}
			}

			else if (choixEntree <= 13) {//Cases d'entrées à droite
				this.pieceLibre = this.plateau.getPiece(choixEntree-7, 0) ;
				IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());

				for(int i = 0 ; i < 6 ; i++) {
					this.plateau.positionnePiece(this.plateau.getPiece(choixEntree-7,i+1),  choixEntree-7, i);
					IG.changerPiecePlateau( choixEntree-7, i,this.plateau.getPiece(choixEntree-7, i).getModelePiece(), this.plateau.getPiece(choixEntree-7, i).getOrientationPiece());
				}
				this.plateau.positionnePiece(pieceLibreTemp,choixEntree-7, 6);
				IG.changerPiecePlateau(choixEntree-7, 6,pieceLibreTemp.getModelePiece(), pieceLibreTemp.getOrientationPiece());

				//si objet présent
				int jTemp = -10;
				for (int j = 0 ; j < this.objets.length ; j ++){
					if (this.objets[j].getPosColonnePlateau() == 0 && this.objets[j].getPosLignePlateau() == choixEntree-7 && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(choixEntree-7, 6);
						jTemp = j ;
						break ;
					}
				}

				//si objet
				for (int i = 1 ; i < 7 ; i++) {
					for (int j = 0; j < this.objets.length; j++) {
						if (this.objets[j].getPosColonnePlateau() == i && this.objets[j].getPosLignePlateau() == choixEntree-7 && j != jTemp && this.objets[j].surPlateau()==true) {
							this.objets[j].positionneObjet(choixEntree-7, i-1);
						}
					}
				}
				//affichage qui doit se refaire en cas de superposition temporaire d'objet
				if(jTemp >= 0 && jTemp <= 17) {
					IG.placerObjetPlateau(jTemp,choixEntree-7,6);
				}

				//si joueur
				jTemp = -10;
				for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
					if (this.joueurs[j].getPosColonne() == 0 && this.joueurs[j].getPosLigne() == choixEntree-7 ) {
						this.joueurs[j].setPosition(choixEntree-7, 6);
						if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,choixEntree-7,6,0,2);
						else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,choixEntree-7,6,2,2);
						else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,choixEntree-7,6,2,0);
						jTemp = j ;
						break ;
					}
				}

				//si joueur
				for (int i = 1 ; i < 7 ; i++) {
					for (int j = 0; j < this.getNombreJoueurs() ; j++) {
						if (this.joueurs[j].getPosColonne() == i && this.joueurs[j].getPosLigne() == choixEntree-7 && j != jTemp) {
							this.joueurs[j].setPosition(choixEntree-7, i-1);
							if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,choixEntree-7,i-1,0,2);
							else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,choixEntree-7,i-1,2,2);
							else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,choixEntree-7,i-1,2,0);
						}
					}
				}
			}

			else if (choixEntree <= 20) { //Cases d'entrées en bas
				if (choixEntree == 20 ) choixEntree = 0 ;
				if (choixEntree == 19 ) choixEntree = 1 ;
				if (choixEntree == 18 ) choixEntree = 2 ;
				if (choixEntree == 17 ) choixEntree = 3 ;
				if (choixEntree == 16 ) choixEntree = 4 ;
				if (choixEntree == 15 ) choixEntree = 5 ;
				if (choixEntree == 14 ) choixEntree = 6 ;

				this.pieceLibre = this.plateau.getPiece(0, choixEntree) ;
				IG.changerPieceHorsPlateau(this.pieceLibre.getModelePiece(), this.pieceLibre.getOrientationPiece());

				for(int i = 0 ; i < 6 ; i++) {
					this.plateau.positionnePiece(this.plateau.getPiece(i+1,choixEntree), i, choixEntree );
					IG.changerPiecePlateau(i, choixEntree,this.plateau.getPiece(i+1,choixEntree).getModelePiece(), this.plateau.getPiece(i+1,choixEntree).getOrientationPiece());
				}
				this.plateau.positionnePiece(pieceLibreTemp,6, choixEntree);
				IG.changerPiecePlateau(6,choixEntree,pieceLibreTemp.getModelePiece(),pieceLibreTemp.getOrientationPiece());

				//si objet présent
				int jTemp = -10;
				for (int j = 0 ; j < this.objets.length ; j ++){
					if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == 0 && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(6, choixEntree);
						jTemp = j ;
						break ;
					}
				}
				//si objet
				for (int i = 1 ; i < 7 ; i++) {
					for (int j = 0; j < this.objets.length; j++) {
						if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == i && j != jTemp && this.objets[j].surPlateau()==true) {
							this.objets[j].positionneObjet(i-1, choixEntree);
						}
					}
				}
				//affichage qui doit se refaire en cas de superposition temporaire d'objet
				if(jTemp >= 0 && jTemp <= 17) {
					IG.placerObjetPlateau(jTemp,6,choixEntree);
				}

				//si joueur présent
				jTemp = -10;
				for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
					if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == 0 ) {
						this.joueurs[j].setPosition(6, choixEntree);
						if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,6,choixEntree,0,2);
						else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,6,choixEntree,2,2);
						else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,6,choixEntree,2,0);
						jTemp = j ;
						break ;
					}
				}
				//si joueur
				for (int i = 1 ; i < 7 ; i++) {
					for (int j = 0; j < this.getNombreJoueurs() ; j++) {
						if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == i && j != jTemp) {
							this.joueurs[j].setPosition(i-1, choixEntree);
							if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,i-1,choixEntree,0,2);
							else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,i-1,choixEntree,2,2);
							else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,i-1,choixEntree,2,0);

						}
					}
				}

			}

			else if (choixEntree <= 27) { //Cases d'entrées à gauche
				if (choixEntree == 27 ) choixEntree = 0 ;
				if (choixEntree == 26 ) choixEntree = 1 ;
				if (choixEntree == 25 ) choixEntree = 2 ;
				if (choixEntree == 24 ) choixEntree = 3 ;
				if (choixEntree == 23 ) choixEntree = 4 ;
				if (choixEntree == 22 ) choixEntree = 5 ;
				if (choixEntree == 21 ) choixEntree = 6 ;

				this.pieceLibre = this.plateau.getPiece(choixEntree, 6) ;
				IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
				for(int i = 5 ; i > -1 ; i--) {
					this.plateau.positionnePiece(this.plateau.getPiece(choixEntree,i),  choixEntree, i+1);
					IG.changerPiecePlateau( choixEntree, i+1,this.plateau.getPiece(choixEntree, i).getModelePiece(), this.plateau.getPiece(choixEntree, i).getOrientationPiece());
				}
				this.plateau.positionnePiece(pieceLibreTemp,choixEntree, 0);
				IG.changerPiecePlateau(choixEntree, 0,pieceLibreTemp.getModelePiece(), pieceLibreTemp.getOrientationPiece());


				//si objet présent
				int jTemp = -10;
				for (int j = 0 ; j < this.objets.length ; j ++){
					if (this.objets[j].getPosColonnePlateau() == 6 && this.objets[j].getPosLignePlateau() == choixEntree && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(choixEntree, 0);
						jTemp = j ;
						break ;
					}
				}
				//si objet
				for (int i = 5 ; i > -1 ; i--) {
					for (int j = 0; j < this.objets.length; j++) {
						if (this.objets[j].getPosColonnePlateau() == i && this.objets[j].getPosLignePlateau() == choixEntree && j != jTemp && this.objets[j].surPlateau()==true) {
							this.objets[j].positionneObjet(choixEntree, i + 1);
						}
					}
				}
				//affichage qui doit se refaire en cas de superposition temporaire d'objet
				if(jTemp >= 0 && jTemp <= 17) {
					IG.placerObjetPlateau(jTemp,choixEntree,0);
				}

				//si joueur présent
				jTemp = -10;
				for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
					if (this.joueurs[j].getPosColonne() == 6 && this.joueurs[j].getPosLigne() == choixEntree ) {
						this.joueurs[j].setPosition(choixEntree, 0);
						if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,choixEntree,0,0,2);
						else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,choixEntree,0,2,2);
						else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,choixEntree,0,2,0);
						jTemp = j ;
						break ;
					}
				}
				//si joueur
				for (int i = 5 ; i > -1 ; i--) {
					for (int j = 0; j < this.getNombreJoueurs() ; j++) {
						if (this.joueurs[j].getPosColonne() == i && this.joueurs[j].getPosLigne() == choixEntree && j != jTemp) {
							this.joueurs[j].setPosition(choixEntree, i+1);
							if (joueurs[j].getNumJoueur() == 0) IG.placerJoueurPrecis(0,choixEntree,i+1,0,2);
							else if (joueurs[j].getNumJoueur() == 1) IG.placerJoueurPrecis(1,choixEntree,i+1,2,2);
							else if (joueurs[j].getNumJoueur() == 2) IG.placerJoueurPrecis(2,choixEntree,i+1,2,0);
						}
					}
				}
			}

		for (int i = 0 ; i < 7 ; i++)	{
			for (int j = 0 ; j < 7 ; j ++){
					IG.enleverObjetPlateau(i , j) ;
			}
		}
		for (int i = 0 ; i < 18 ; i++){
			if(this.objets[i].surPlateau()==true) IG.placerObjetPlateau(this.objets[i].getNumeroObjet(), this.objets[i].getPosLignePlateau(), this.objets[i].getPosColonnePlateau()) ;
		}

			IG.miseAJourAffichage();
		}


	/**
	 * A Faire (01/06/2021 Rayane/Adrien Fini)
	 *
	 * Méthode modifiant les différents éléments de la partie suite à l'insertion de la pièce libre dans le plateau.
	 * Avec une surcharge
	 *
	 * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
	 */
	public void insertionPieceLibre(int choixEntree, int numJoueur){
		Piece pieceLibreTemp ;

		//Recherche des cases d'entrées et insertion piece dans les différents cas
		pieceLibreTemp = getPieceLibre() ;
		pieceLibreTemp.setOrientation(getPieceLibre().getOrientationPiece()) ;

		if (choixEntree <= 6) { //Cases d'entrées en haut n
			this.pieceLibre = this.plateau.getPiece(6, choixEntree);

			for (int i = 5; i > -1; i--) {
				this.plateau.positionnePiece(this.plateau.getPiece(i, choixEntree), i + 1, choixEntree);
			}

			this.plateau.positionnePiece(pieceLibreTemp, 0, choixEntree);

			//si objet présent
			int jTemp = -10;
			for (int j = 0 ; j < this.objets.length ; j ++){
				if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == 6 && this.objets[j].surPlateau()==true) {
					this.objets[j].positionneObjet(0, choixEntree);
					jTemp = j ;
					break ;
				}
			}
			//si objet
			for (int i = 5; i > -1; i--) {
				for (int j = 0; j < this.objets.length; j++) {
					if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == i && j != jTemp && this.objets[j].surPlateau() == true) {
						this.objets[j].positionneObjet(i + 1, choixEntree);
					}
				}
			}

			//si joueur
			jTemp = -10;
			for (int j = 0; j < this.getNombreJoueurs(); j++) {
				if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == 6) {
					this.joueurs[j].setPosition(0, choixEntree);
					jTemp = j;
					break;
				}
			}
			//si joueur
			for (int i = 5; i > -1; i--) {
				for (int j = 0; j < this.getNombreJoueurs(); j++) {
					if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == i && jTemp != j) {
						this.joueurs[j].setPosition(i + 1, choixEntree);
					}
				}
			}
		}else if (choixEntree <= 13) {//Cases d'entrées à droite
			this.pieceLibre = this.plateau.getPiece(choixEntree-7, 0) ;

			for(int i = 0 ; i < 6 ; i++) {
				this.plateau.positionnePiece(this.plateau.getPiece(choixEntree-7,i+1),  choixEntree-7, i);
			}
			this.plateau.positionnePiece(pieceLibreTemp,choixEntree-7, 6);

			//si objet présent
			int jTemp = -10;
			for (int j = 0 ; j < this.objets.length ; j ++){
				if (this.objets[j].getPosColonnePlateau() == 0 && this.objets[j].getPosLignePlateau() == choixEntree-7 && this.objets[j].surPlateau()==true) {
					this.objets[j].positionneObjet(choixEntree-7, 6);
					jTemp = j ;
					break ;
				}
			}

			//si objet
			for (int i = 1 ; i < 7 ; i++) {
				for (int j = 0; j < this.objets.length; j++) {
					if (this.objets[j].getPosColonnePlateau() == i && this.objets[j].getPosLignePlateau() == choixEntree-7 && j != jTemp && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(choixEntree-7, i-1);
					}
				}
			}

			//si joueur
			jTemp = -10;
			for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
				if (this.joueurs[j].getPosColonne() == 0 && this.joueurs[j].getPosLigne() == choixEntree-7 ) {
					this.joueurs[j].setPosition(choixEntree-7, 6);
					jTemp = j ;
					break ;
				}
			}

			//si joueur
			for (int i = 1 ; i < 7 ; i++) {
				for (int j = 0; j < this.getNombreJoueurs() ; j++) {
					if (this.joueurs[j].getPosColonne() == i && this.joueurs[j].getPosLigne() == choixEntree-7 && j != jTemp) {
						this.joueurs[j].setPosition(choixEntree-7, i-1);
					}
				}
			}
		}

		else if (choixEntree <= 20) { //Cases d'entrées en bas
			if (choixEntree == 20 ) choixEntree = 0 ;
			if (choixEntree == 19 ) choixEntree = 1 ;
			if (choixEntree == 18 ) choixEntree = 2 ;
			if (choixEntree == 17 ) choixEntree = 3 ;
			if (choixEntree == 16 ) choixEntree = 4 ;
			if (choixEntree == 15 ) choixEntree = 5 ;
			if (choixEntree == 14 ) choixEntree = 6 ;

			this.pieceLibre = this.plateau.getPiece(0, choixEntree) ;

			for(int i = 0 ; i < 6 ; i++) {
				this.plateau.positionnePiece(this.plateau.getPiece(i+1,choixEntree), i, choixEntree );
			}
			this.plateau.positionnePiece(pieceLibreTemp,6, choixEntree);

			//si objet présent
			int jTemp = -10;
			for (int j = 0 ; j < this.objets.length ; j ++){
				if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == 0 && this.objets[j].surPlateau()==true) {
					this.objets[j].positionneObjet(6, choixEntree);
					jTemp = j ;
					break ;
				}
			}
			//si objet
			for (int i = 1 ; i < 7 ; i++) {
				for (int j = 0; j < this.objets.length; j++) {
					if (this.objets[j].getPosColonnePlateau() == choixEntree && this.objets[j].getPosLignePlateau() == i && j != jTemp && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(i-1, choixEntree);
					}
				}
			}

			//si joueur présent
			jTemp = -10;
			for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
				if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == 0 ) {
					this.joueurs[j].setPosition(6, choixEntree);
					jTemp = j ;
					break ;
				}
			}
			//si joueur
			for (int i = 1 ; i < 7 ; i++) {
				for (int j = 0; j < this.getNombreJoueurs() ; j++) {
					if (this.joueurs[j].getPosColonne() == choixEntree && this.joueurs[j].getPosLigne() == i && j != jTemp) {
						this.joueurs[j].setPosition(i-1, choixEntree);
					}
				}
			}

		}

		else if (choixEntree <= 27) { //Cases d'entrées à gauche
			if (choixEntree == 27 ) choixEntree = 0 ;
			if (choixEntree == 26 ) choixEntree = 1 ;
			if (choixEntree == 25 ) choixEntree = 2 ;
			if (choixEntree == 24 ) choixEntree = 3 ;
			if (choixEntree == 23 ) choixEntree = 4 ;
			if (choixEntree == 22 ) choixEntree = 5 ;
			if (choixEntree == 21 ) choixEntree = 6 ;

			this.pieceLibre = this.plateau.getPiece(choixEntree, 6) ;
			for(int i = 5 ; i > -1 ; i--) {
				this.plateau.positionnePiece(this.plateau.getPiece(choixEntree,i),  choixEntree, i+1);
			}
			this.plateau.positionnePiece(pieceLibreTemp,choixEntree, 0);


			//si objet présent
			int jTemp = -10;
			for (int j = 0 ; j < this.objets.length ; j ++){
				if (this.objets[j].getPosColonnePlateau() == 6 && this.objets[j].getPosLignePlateau() == choixEntree && this.objets[j].surPlateau()==true) {
					this.objets[j].positionneObjet(choixEntree, 0);
					jTemp = j ;
					break ;
				}
			}
			//si objet
			for (int i = 5 ; i > -1 ; i--) {
				for (int j = 0; j < this.objets.length; j++) {
					if (this.objets[j].getPosColonnePlateau() == i && this.objets[j].getPosLignePlateau() == choixEntree && j != jTemp && this.objets[j].surPlateau()==true) {
						this.objets[j].positionneObjet(choixEntree, i + 1);
					}
				}
			}

			//si joueur présent
			jTemp = -10;
			for (int j = 0 ; j < this.getNombreJoueurs() ; j ++){
				if (this.joueurs[j].getPosColonne() == 6 && this.joueurs[j].getPosLigne() == choixEntree ) {
					this.joueurs[j].setPosition(choixEntree, 0);
					jTemp = j ;
					break ;
				}
			}
			//si joueur
			for (int i = 5 ; i > -1 ; i--) {
				for (int j = 0; j < this.getNombreJoueurs() ; j++) {
					if (this.joueurs[j].getPosColonne() == i && this.joueurs[j].getPosLigne() == choixEntree && j != jTemp) {
						this.joueurs[j].setPosition(choixEntree, i+1);
					}
				}
			}
		}

	}

	/**
	 * Méthode retournant une copie.
	 * 
	 * @return Une copie des éléments.
	 */
	public ElementsPartie copy(){
		Objet[] nouveauxObjets=new Objet[(this.objets).length];
		for (int i=0;i<objets.length;i++)
			nouveauxObjets[i]=(this.objets[i]).copy();
		Joueur[] nouveauxJoueurs=new Joueur[nombreJoueurs];
		for (int i=0;i<nombreJoueurs;i++)
			nouveauxJoueurs[i]=(this.joueurs[i]).copy(objets);
		Plateau nouveauPlateau=(this.plateau).copy();
		Piece nouvellePieceLibre=(this.pieceLibre).copy();
		ElementsPartie nouveauxElements=new  ElementsPartie(nouveauxJoueurs,nouveauxObjets,nouveauPlateau,nouvellePieceLibre); 
		return nouveauxElements;
	}


}
