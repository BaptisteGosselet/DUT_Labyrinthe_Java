package tests;
import composants.Plateau ;
import composants.Piece ;
import joueurs.Joueur ;
import grafix.interfaceGraphique.IG;
import joueurs.JoueurHumain;

public class TestJoueur {
       public static void main(String[] args) {

        // Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres();

        //Déclaration du message
        String[] message ={
                "",
                "",
                "Cliquer pour continuer ...",
                ""
        };

        // Création de la fenêtre de jeu et affichage de la fenêtre
        int nbJoueurs = ((Integer) parametres[0]).intValue(); // Récupération du nombre de joueurs
        IG.creerFenetreJeu("Test joueur", nbJoueurs); // On crée la fenêtre
        IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu

        // Changement des nom des joueurs et le leurs skin
        int numImageJoueur0 = ((Integer) parametres[3]).intValue();
        String nomJoueur0 = (String) parametres[1];
        String categorieJoueur0 = (String) parametres[2];
        IG.placerJoueurPrecis(0, 0 ,0 , 0 , 2);

        int numImageJoueur1 = ((Integer) parametres[6]).intValue();
        String nomJoueur1 = (String) parametres[4];
        String categorieJoueur1 = (String) parametres[5];
        IG.placerJoueurPrecis(1, 0 , 6 , 2 ,2 );

        if (nbJoueurs>2) {
            int numImageJoueur2 = ((Integer) parametres[9]).intValue();
            String nomJoueur2 = (String) parametres[7];
            String categorieJoueur2 = (String) parametres[8];
            IG.placerJoueurPrecis(2,6,6, 2 , 0);
        }

        IG.miseAJourAffichage();
        Plateau plateau=new Plateau() ;
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoirement() ;

        Joueur[] joueurs =Joueur.nouveauxJoueurs(parametres) ;
        for(int i = 0 ; i < nbJoueurs ; i++){
            IG.changerNomJoueur(joueurs[i].getNumJoueur(), joueurs[i].getNomJoueur());
            IG.changerImageJoueur(joueurs[i].getNumJoueur(), joueurs[i].getNumeroImagePersonnage());
        }
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        for (int i = 0 ; i < nbJoueurs ; i++){
            int[] choixArrivee ;
            int [][] chemin ;

            message = new String[]{
                    "",
                    "Au tour de :" + joueurs[i].getNomJoueur(),
                    "Selectionner une case ",
                    "",
            };
            IG.afficherMessage(message);
            IG.changerJoueurSelectionne(i);
            IG.miseAJourAffichage();
            choixArrivee = joueurs[i].choisirCaseArrivee(null);

            while(plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), choixArrivee[0], choixArrivee[1]) == null){
                choixArrivee = joueurs[i].choisirCaseArrivee(null);
            }
            joueurs[0].setPosition(choixArrivee[0],choixArrivee[1]);

            chemin = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), choixArrivee[0], choixArrivee[1]) ;
            for (int j = 0 ; j < chemin.length ; j ++){
                IG.placerBilleSurPlateau(chemin[j][0], chemin[j][1], 1 ,1, i );
                IG.miseAJourAffichage();
            }
            if (joueurs[i].getNumJoueur() == 0) IG.placerJoueurPrecis(i,choixArrivee[0],choixArrivee[1],0,2);
            else if (joueurs[i].getNumJoueur() == 1) IG.placerJoueurPrecis(i,choixArrivee[0],choixArrivee[1],2,2);
            else if (joueurs[i].getNumJoueur() == 2) IG.placerJoueurPrecis(i,choixArrivee[0],choixArrivee[1],2,0);

            IG.miseAJourAffichage();
        }

        message = new String[]{
               "",
               "C'est terminé !",
               "Cliquez pour fermer le programme",
               "",
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
    }
}