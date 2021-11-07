package joueurs;

import composants.Objet;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

/**
 * 
 * Cette classe permet de représenter un joueur humain.
 * 
 * @author Jean-François Condotta - 2021
 *
 */
public class JoueurHumain extends Joueur {

	/**
	 * Constructeur permettant de créer un joueur à partir de son nom, son type et 
	 * le numéro de l'image le représentant.
	 * @param numJoueur Le numéro du joueur.
	 * @param nomJoueur Le nom du joueur.
	 * @param numeroImagePersonnage Le numéro de l'image représentant le joueur.
	 * @param posLignePlateau La ligne du plateau sur laquelle est positionnée le joueur.
	 * @param posColonnePlateau La colonne du plateau sur laquelle est positionnés le joueur.

	 */
	public JoueurHumain(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "Humain";
	}

	
	/**
	 * Saisies de l'orientation de la pièce hors plateau et de l'entrée dans le plateau réalisées à l'aide de l'interface graphique.
	 */
	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
		int resultat[]=new int[2];
		resultat[1]=IG.attendreChoixEntree();
		resultat[0]=IG.recupererOrientationPieceHorsPlateau();
		return resultat;
	}

	
	/**
	 * Saisie de la case d'arrivée réalisée à l'aide de l'interface graphique.
	 */
	@Override
	public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
		return IG.attendreChoixPiece();
	}


	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurHumain(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}




}
