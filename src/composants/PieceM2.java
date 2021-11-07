package composants;

/**
 * 
 * Cette classe permet de représenter les pièces du jeu de modèle 2.
 *
 */
public class PieceM2 extends Piece {

	/**
	 * A Faire (29/04 Adrien Fini)
	 * 
	 * Constructeur permettant de construire une pièce de modèle 2 et d'orientation 0.
	 */
	public PieceM2() {
		super(2,true,true,false,true);
	}
	/**
	 * A Faire (30/04 Adrien Fini)
	 * 
	 * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
	 * @return Une copie de la pièce.
	 */
	public Piece copy(){
		Piece piece=null;
		piece = new PieceM2();
		return piece;
	}
}
