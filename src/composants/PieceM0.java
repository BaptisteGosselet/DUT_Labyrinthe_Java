package composants;

/**
 * 
 * Cette classe permet de représenter les pièces du jeu de modèle 0.
 *
 */
public class PieceM0 extends Piece {

	/**
	 * A Faire (29/04 Adrien Fini)
	 * 
	 * Constructeur permettant de construire une pièce de modèle 0 et d'orientation 0.
	 */
	public PieceM0() {
		super(0,false,true,true,false);
	}

	/**
	 * A Faire (30/04 Adrien Fini)
	 * 
	 * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
	 * @return Une copie de la pièce.
	 */
	public Piece copy(){
		Piece piece=null;
		piece=new PieceM0() ;
		return piece;
	}
}
