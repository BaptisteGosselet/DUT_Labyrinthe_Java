package tests;

import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import partie.ElementsPartie;

public class TestElementsPartie {

    public static void main(String[] args) {

        Object parametresJeu[];
        parametresJeu=IG.saisirParametres();

        int  nbJoueurs=((Integer)parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- TestElementsPartie",nbJoueurs);
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);

        for(int i = 0 ; i < nbJoueurs ; i++){
            IG.changerNomJoueur(joueurs[i].getNumJoueur(), joueurs[i].getNomJoueur());
            IG.changerImageJoueur(joueurs[i].getNumJoueur(), joueurs[i].getNumeroImagePersonnage());
        }

        ElementsPartie elementsPartie=new ElementsPartie(joueurs);
        IG.rendreVisibleFenetreJeu();
        IG.miseAJourAffichage();
        String[] message ={
                "",
                "",
                "Cliquer pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.placerJoueurPrecis(0,0,0,0,2) ;
        IG.placerJoueurPrecis(1,0,6,2,2);
        IG.placerJoueurPrecis(2,6,6,2,0);
        IG.miseAJourAffichage();
        IG.attendreClic();


        for (int j = 0 ; j < 20 ; j++){
            int i = 0 ;
            message = new String[]{
                    "",
                    "",
                    "Choisissez une entrée ",
                    ""
            };
            IG.afficherMessage(message);
            IG.miseAJourAffichage();

            elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());
            /*
            message = new String[]{
                    "",
                    "Choissisez l'endroit",
                    "où vous déplacer ! ",
                    ""
            };
            IG.afficherMessage(message);
            IG.miseAJourAffichage();

            int[] choixArrivee ;
            choixArrivee = joueurs[i].choisirCaseArrivee(elementsPartie);
            elementsPartie.getPlateau().calculeChemin(joueurs[0].getPosLigne(), joueurs[0].getPosColonne(), choixArrivee[0], choixArrivee[1]) ;
            while(elementsPartie.getPlateau().calculeChemin(joueurs[0].getPosLigne(), joueurs[0].getPosColonne(), choixArrivee[0], choixArrivee[1]) == null){
                choixArrivee = joueurs[0].choisirCaseArrivee(null);
            }
            joueurs[0].setPosition(choixArrivee[0],choixArrivee[1]);

             IG.deselectionnerPiecePlateau();

            if (joueurs[i].getNumJoueur() == 0) IG.placerJoueurPrecis(0,choixArrivee[0],choixArrivee[1],0,2);

            IG.miseAJourAffichage();

             */
            IG.deselectionnerFleche();
            IG.miseAJourAffichage();
        }
        message = new String[]{
                "",
                "C'est terminé !",
                "Cliquer pour quitter ...",
                ""
        };

        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
    }

}
