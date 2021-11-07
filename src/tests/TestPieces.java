package tests;
import composants.Piece ;
import composants.Utils ;
import grafix.interfaceGraphique.IG;

public class TestPieces {
    public static void main(String[] args) {
        /*
        Tester les classes pièces du jeu
        Nous allons maintenant tester les classes précédentes.
        Dans le paquetage tests créez une nouvelle classe appelée TestPieces.
        La méthode main sera la seule méthode présente dans cette classe.
        Son exécution permettra les affichages/actions suivantes :
        [...]
        */


        // Saisie des différents paramètres
        Object parametres[];
        parametres = IG.saisirParametres();

        //Déclaration du message
        String message[] = {
                "",
                "",
                "Cliquer pour continuer ...",
                ""
        };

        // Création de la fenêtre de jeu et affichage de la fenêtre
        int nbJoueurs = ((Integer) parametres[0]).intValue(); // Récupération du nombre de joueurs
        IG.creerFenetreJeu("Démo Librairie IG version 1.9", nbJoueurs); // On crée la fenêtre
        IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu

        // Changement des nom des joueurs et le leurs skin
        int numImageJoueur0 = ((Integer) parametres[3]).intValue();
        String nomJoueur0 = (String) parametres[1];
        String categorieJoueur0 = (String) parametres[2];
        IG.changerNomJoueur(0, nomJoueur0 + " (" + categorieJoueur0 + ")");
        IG.changerImageJoueur(0, numImageJoueur0);

        int numImageJoueur1 = ((Integer) parametres[6]).intValue();
        String nomJoueur1 = (String) parametres[4];
        String categorieJoueur1 = (String) parametres[5];
        IG.changerNomJoueur(1, nomJoueur1 + " (" + categorieJoueur1 + ")");
        IG.changerImageJoueur(1, numImageJoueur1);

        if (nbJoueurs > 2) {
            int numImageJoueur2 = ((Integer) parametres[9]).intValue();
            String nomJoueur2 = (String) parametres[7];
            String categorieJoueur2 = (String) parametres[8];
            IG.changerNomJoueur(2, nomJoueur2 + " (" + categorieJoueur2 + ")");
            IG.changerImageJoueur(2, numImageJoueur2);
        }
        IG.afficherMessage(message);
        IG.miseAJourAffichage();


        Piece[] plateau = Piece.nouvellesPieces();


        IG.miseAJourAffichage();
        IG.attendreClic();


        int noPiece = 0 ;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                    IG.changerPiecePlateau(i, j, plateau[noPiece].getModelePiece(), plateau[noPiece].getOrientationPiece());
                    noPiece++ ;
            }
        }

        IG.changerPieceHorsPlateau(plateau[49].getModelePiece(), plateau[49].getOrientationPiece());
        IG.miseAJourAffichage();
        for(int l=0;l<4;l++) {
            noPiece=0 ;
            for(int p=0;p<plateau.length;p++){
                plateau[p].rotation();
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    IG.changerPiecePlateau(i, j, plateau[noPiece].getModelePiece(), plateau[noPiece].getOrientationPiece());
                    noPiece++ ;
                }
            }
            IG.changerPieceHorsPlateau(plateau[49].getModelePiece(), plateau[49].getOrientationPiece());

            System.out.println(plateau[49].toString());
            IG.miseAJourAffichage();
            IG.attendreClic();
        }

        IG.fermerFenetreJeu();
    }
}
