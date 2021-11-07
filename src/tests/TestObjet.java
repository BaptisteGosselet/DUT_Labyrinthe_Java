package tests;
import composants.Objet;
import grafix.interfaceGraphique.IG;

public class TestObjet {

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


        Objet[] objets = Objet.nouveauxObjets() ;

        for (int i=0;i<objets.length;i++)
            System.out.println(objets[i]);

        System.out.println(objets[0].getNumeroObjet());
        System.out.println(objets[0].getPosLignePlateau());
        System.out.println(objets[0].getPosColonnePlateau());
        for (int i = 0 ; i < 18 ; i++){
            IG.placerObjetPlateau(i, objets[i].getPosLignePlateau(), objets[i].getPosColonnePlateau()) ;
        }

        IG.miseAJourAffichage();

    }
}
