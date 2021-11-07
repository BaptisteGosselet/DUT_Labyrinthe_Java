package tests;
import composants.Plateau ;
import composants.Piece ;
import grafix.interfaceGraphique.IG;

import java.util.ArrayList;
import java.util.Arrays;

public class TestPlateau {

    public static void main(String[] args) {

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
        IG.attendreClic();

        Plateau plateau = new Plateau() ;
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoirement();

        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
        IG.attendreClic();
        IG.miseAJourAffichage();

        IG.attendreClic();
        IG.attendreClic();

        int [][][] cheminsPossibles = new int[49][49][2];
        int nbChemin = 0 ;
        for(int i = 0 ; i< 7 ; i++) {
            for (int j = 0 ; j < 7 ; j++){
                if (plateau.calculeChemin(3,3,i,j)  != null && ( i != 3 || j != 3) ){
                    int[][] resultat = (plateau.calculeChemin(3, 3, i, j));
                    System.out.print("Chemin entre les cases (" + resultat[0][0] + "," + resultat[0][1] + ") et (" + resultat[resultat.length-1][0] + "," + resultat[resultat.length-1][1] + ") : ");
                    cheminsPossibles[nbChemin]= resultat ;
                    for (int k = 0 ; k < resultat.length ; k++ ) {
                        System.out.print("(" + resultat[k][0] + "," + resultat[k][1] + ")");
                    }
                    System.out.println("");
                    nbChemin++ ;
                }
            }
        }

        int [][] cheminLePlusLong = new int[49][2] ;
        int longueurChemin = 0 ;
        int longueurCheminMax = 0 ;
        for (int i=0 ; i< nbChemin ; i++) {
            if (i == 0) {
                cheminLePlusLong = cheminsPossibles[0] ;
            }
            longueurChemin = 0 ;
            for (int m = 0 ; m < cheminsPossibles[i].length ; m++) {
                if (cheminsPossibles[i][m] != null ) {
                    longueurChemin +=1 ;
                }
            }
            if (i == 0) {
                longueurCheminMax = longueurChemin ;
            }

            if (longueurCheminMax < longueurChemin) {
                cheminLePlusLong = cheminsPossibles[i] ;
                longueurCheminMax = longueurChemin ;
            }
        }

        for (int i = 0; i < longueurCheminMax ; i ++) {
            IG.placerBilleSurPlateau(cheminLePlusLong[i][0], cheminLePlusLong[i][1], 1 ,1, 2 );
        }
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
    }
}
