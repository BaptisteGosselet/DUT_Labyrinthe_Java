package joueurs;

import composants.Objet;
import composants.Plateau;
import composants.Utils;
import partie.ElementsPartie;

import java.util.Arrays;

/**
 * 
 * Cette classe permet de représenter un joueur ordinateur de type T1.
 * 
 * @author Jean-François Condotta - 2021
 *
 */

public class JoueurOrdinateurT1 extends JoueurOrdinateur {

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
	public JoueurOrdinateurT1(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
				super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
	}

	@Override
	public String getCategorie() {
		return "OrdiType1";
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
				System.out.println(colonne) ;
				System.out.println(ligne) ;
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

	@Override
	public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
		//System.out.println(elementsPartie.getJoueurActuel()) ;
		if (avoirObjetUnCoup(elementsPartie, elementsPartie.getJoueurActuel()) != null) {
			return avoirObjetUnCoup(elementsPartie, elementsPartie.getJoueurActuel())[0];
		} else{
			System.out.println("Choix random");
			int[] tab = {Utils.genererEntier(27), Utils.genererEntier(3)};
		return tab;
		}
	}

	@Override
	public int[] choisirCaseArrivee(ElementsPartie elementsPartie){
		int numJoueur = elementsPartie.getJoueurActuel() ;
		int[][] chemin = elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[numJoueur].getPosLigne(), elementsPartie.getJoueurs()[numJoueur].getPosColonne(), elementsPartie.getJoueurs()[numJoueur].getProchainObjet().getPosLignePlateau(), elementsPartie.getJoueurs()[numJoueur].getProchainObjet().getPosColonnePlateau());
		if (chemin != null ){
			int[] tab = {chemin[chemin.length-1][0], chemin[chemin.length-1][1]};
			return tab ;
		}
		int[] tab = {Utils.genererEntier(6), Utils.genererEntier(6)} ;
		return tab ;
	}

	@Override
	public Joueur copy(Objet objets[]){
		Joueur nouveauJoueur=new JoueurOrdinateurT1(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
		nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
		while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
			nouveauJoueur.recupererObjet();
		return nouveauJoueur;
	}


}
