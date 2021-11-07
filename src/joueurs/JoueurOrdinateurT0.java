package joueurs;

import composants.Objet;
import composants.Plateau;
import composants.Utils;
import jdk.jshell.execution.Util;
import partie.ElementsPartie;


/**
 * 
 * Cette classe permet de représenter un joueur ordinateur de type T0.
 * 
 * @author Jean-François Condotta - 2021
 *
 */

public class JoueurOrdinateurT0 extends JoueurOrdinateur {

	/**
	 * 
	 * Constructeur permettant de créer un joueur.
	 * 
	 * @param numJoueur Le numéro du joueur.
	 * @param nomJoueur Le nom du joueur.
	 * @param numeroImagePersonnage Le numéro de l'image représentant le joueur.
	 * @param posLignePlateau La ligne du plateau sur laquelle est positionnée le joueur.
	 * @param posColonnePlateau La colonne du plateau sur laquelle est positionnée le joueur.

	 */
	public JoueurOrdinateurT0(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "OrdiType0";
	}


	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
		int[] tab = {Utils.genererEntier(27), Utils.genererEntier(3)} ;
		return tab ;
	}

	@Override
	public int[] choisirCaseArrivee(ElementsPartie elementsPartie){
		int[] tab = {Utils.genererEntier(6), Utils.genererEntier(6)} ;
		return tab;
	}

	
	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurOrdinateurT0(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}
	

}
