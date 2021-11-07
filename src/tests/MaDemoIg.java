package tests;
import grafix.interfaceGraphique.IG;
import partie.Partie;

public class MaDemoIg {

    public static void main(String[] args) {


        // Saisie des différents paramètres
        Object parametres[];
        parametres = IG.saisirParametres();

        //Déclaration du message
        String message[]={
                "",
                "Démo Librairie Graphique 1.9 ...	",
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

        if (nbJoueurs>2) {
            int numImageJoueur2 = ((Integer) parametres[9]).intValue();
            String nomJoueur2 = (String) parametres[7];
            String categorieJoueur2 = (String) parametres[8];
            IG.changerNomJoueur(2, nomJoueur2 + " (" + categorieJoueur2 + ")");
            IG.changerImageJoueur(2, numImageJoueur2);
            IG.miseAJourAffichage();
        }

        // Changement des objets des joueurs
        for (int i = 0; i < 6; i++) {
            IG.changerObjetJoueur(0, i, i);
            IG.changerObjetJoueur(1, i + 6, i);
            if (nbJoueurs>2) {
                IG.changerObjetJoueur(2, i + 12, i);
            }
        }
        IG.miseAJourAffichage();

        // Changement des pièces sur le plateau
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, 2, 0);
            }
        }
        IG.miseAJourAffichage();

        //Changement de la pièce hors plateau
        IG.changerPieceHorsPlateau(1, 0);
        IG.miseAJourAffichage();


        // Place les objets sur le plateau
        int numObjet = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.placerObjetPlateau((numObjet++) % 18, i, j);
                if (numObjet==18) break ;
            }
            if (numObjet==18) break ;
        }
        IG.miseAJourAffichage();

        // Place les deux joueurs sur le plateau

        IG.placerJoueurPrecis(0,3,0, 1, 0);
        IG.placerJoueurPrecis(1,3,6, 1 ,2);
        IG.miseAJourAffichage();


        // Texte Bonjour, cliquez sur avancer

        message[0]="";
        message[1]="Bonjour !";
        message[2]="Cliquer pour continuer ...";
        message[3]="";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();


        //Rotation de quatre du plateau et de la piece hors plateau
        int orientation=0 ;
        int sousColonneJ1=0;
        int sousColonneJ2=2;
        int colonneJ1=0;
        int colonneJ2=6;
        int sousColonneBilleJ1=0;
        int sousColonneBilleJ2=0;
        int colonneBilleJ1=0;
        int colonneBilleJ2=6;

        for(int k = 0 ; k<4 ; k++) {

            message[0]="";
            message[1]="Après le clic " + (k+1) ;
            message[2]="Cliquer pour continuer ...";
            message[3]="";
            IG.afficherMessage(message);
            IG.miseAJourAffichage();

            IG.attendreClic();
            orientation++ ;
            if (orientation>3){
                orientation=0 ;
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    IG.changerPiecePlateau(i, j, 2, orientation);
                }
            }
            IG.changerPieceHorsPlateau(1, orientation);

            //Déplacement des joueurs
            colonneBilleJ1 = colonneJ1;
            sousColonneBilleJ1 = sousColonneJ1;
            colonneBilleJ2 = colonneJ2;
            sousColonneBilleJ2 = sousColonneJ2;

            sousColonneJ1++;
            sousColonneJ2--;


            if (sousColonneJ1>2){
                colonneJ1+=1;
                sousColonneJ1=0;
            }
            if (sousColonneJ2<0) {
                colonneJ2-=1;
                sousColonneJ2 = 2;
            }

            System.out.println(colonneJ1 + " " + sousColonneJ1);
            System.out.println(colonneBilleJ1 + " " + sousColonneBilleJ1);
            IG.placerJoueurPrecis(0,3,colonneJ1, 1, sousColonneJ1);
            IG.placerJoueurPrecis(1,3,colonneJ2, 1 ,sousColonneJ2);
            IG.placerBilleSurPlateau(3, colonneBilleJ1, 1 ,sousColonneBilleJ1, 0);
            IG.placerBilleSurPlateau(3, colonneBilleJ2, 1 ,sousColonneBilleJ2, 0);

            IG.enleverObjetPlateau(0,k);
            IG.changerObjetJoueurAvecTransparence(0,k,k);

            IG.miseAJourAffichage();
        }

        IG.attendreClic();

        IG.afficherGagnant(0);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.selectionnerFleche(2);
        IG.miseAJourAffichage();
        IG.pause(2000);
        IG.fermerFenetreJeu();


    }
}
