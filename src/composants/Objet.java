package composants;

/**
 *
 * Cette classe permet de représenter chacun des objets du jeu.
 *
 */
public class Objet {

    private int numObjet; // Le numéro de l'objet (un entier entre 0 et 17).
    private int posLignePlateau; // La ligne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
    private int posColonnePlateau; // La colonne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
    private boolean surPlateau; // Indique si l'objet est sur le plateau ou non (true : sur le plateau, false : hors du plateau).

    /**
     * A Faire (05/05 Baptiste Fini)
     * Constructeur permettant de construire un objet qui est initialement hors du plateau.
     * @param numObjet Le numéro de l'objet.
     */
    public Objet(int numObjet) {
        this.numObjet = numObjet;
        this.posLignePlateau = -1;
        this.posColonnePlateau = -1;
        this.surPlateau = false;
    }

    /**
     * A Faire (05/05 Baptiste Fini)
     * Méthode permettant de générer un tableau contenant les 18 objets du jeu.
     * Les objets seront postionnés aléatoirement sur le plateau.  Deux objets ne pourront pas être sur une même case (même ligne et même colonne).
     * @return Un tableau de 18 objets initialisés pour une partie du jeu. Chaque objet a une position générée aléatoirement. Les positions sont différentes pour deux objets distincts.
     *
     */
    public static Objet[] nouveauxObjets(){
        Objet objets[] = new Objet[18];
        int[] tabAleatoireObjet = new int[18] ;
        int[] tabposLigne = new int[18];
        int[] tabposColonne = new int[18];

        //Tirer 18 coordonnées différentes
        int i = 0;
        while(i < objets.length){
            int x = Utils.genererEntier(6);
            int y = Utils.genererEntier(6);

            //Vérifier si la coordonnée n'existe pas déjà
            boolean doublon = false;
            for(int j=0; j < 18; j++){
                if ((x == tabposLigne[j]) && (y == tabposColonne[j])){
                    doublon = true;
                    break;
                }

            }
            if (!doublon){
                tabposLigne[i] = x;
                tabposColonne[i] = y;
                i++;
            }
        }

        tabAleatoireObjet = Utils.genereTabIntAleatoirement(18) ;

        for(int k=0; k < tabAleatoireObjet.length ; k ++) {
            objets[k] = new Objet(tabAleatoireObjet[k]);
            objets[k].positionneObjet(tabposLigne[k],tabposColonne[k]);
            objets[k].surPlateau = true;
        }
        return objets;
    }



    /**
     *
     * A Faire (05/05 Baptiste Fini)
     * Méthode retournant le numéro de l'objet.
     * @return Le numéro de l'objet.
     */
    public int getNumeroObjet() {
        return this.numObjet;
    }


    /**
     *
     * A Faire (05/05 Baptiste Fini)
     * MÃ©thode retournant le numÃ©ro de la ligne sur laquelle se trouve l'objet.
     * @return Le numéro de la ligne sur laquelle se trouve l'objet.
     */
    public int getPosLignePlateau() {
        return this.posLignePlateau;
    }

    /**
     *
     * A Faire (05/05 Baptiste Fini)
     * Méthode retournant le numéro de la colonne sur laquelle se trouve l'objet.
     * @return Le numéro de la colonne sur laquelle se trouve l'objet.
     */
    public int getPosColonnePlateau() {
        return this.posColonnePlateau;
    }


    /**
     *
     * A Faire (09/05/21 Adrien fini)
     *
     * MÃ©thode permettant de positionner l'objet sur une ligne et une colonne donnÃ©es en paramÃ¨tre.
     *
     * @param lignePlateau Un entier compris entre 0 et 6.
     * @param colonnePlateau Un entier compris entre 0 et 6.
     */
    public void positionneObjet(int lignePlateau, int colonnePlateau){
        if (lignePlateau >= 0 && lignePlateau <=6 && colonnePlateau >= 0 && colonnePlateau <=6 ) {
            this.posLignePlateau = lignePlateau;
            this.posColonnePlateau = colonnePlateau;
        } else {System.out.println("Les entier doivent être comptis entre 0 et 6");}
    }

    /**
     *
     * A Faire (09/05 Adrien fini)
     *
     * MÃ©thode permettant d'enlever l'objet du plateau.
     *
     */
    public void enleveDuPlateau(){
        this.surPlateau= false ;
    }

    /**
     *
     * A Faire (09/05 Adrien fini)
     *
     * MÃ©thode indiquant si l'objet est sur le plateau au non.
     *
     * @return true si l'objet est sur le plateau, false sinon.
     */
    public boolean surPlateau() {
        return this.surPlateau;
    }

    /**
     * MÃ©thode permettant d'obtenir une reprÃ©sentation d'un objet sous forme de chaÃ®ne de caractÃ¨res.
     */
    @Override
    public String toString() {
        return "Objet [numObjet=" + numObjet + ", posLignePlateau=" + posLignePlateau + ", posColonnePlateau="
                + posColonnePlateau + ", surPlateau=" + surPlateau + "]";
    }

    /**
     *
     * MÃ©thode permettant de copier l'objet.
     *
     * @return Une copie de l'objet.
     */
    public Objet copy(){
        Objet objet=new Objet(numObjet);
        objet.posLignePlateau=posLignePlateau;
        objet.posColonnePlateau=posColonnePlateau;
        objet.surPlateau=surPlateau;
        return objet;
    }

    /**
     * Programme testant quelques mÃ©thodes de la classe Objet.
     * @param args arguments du programme
     */
    public static void main(String[] args) {
        // Un petit test ...
        System.out.println("*** GÃ©nÃ©ration et affichage des 18 objets ... ***");
        Objet objetsJeu[]=nouveauxObjets();
        for (int i=0;i<objetsJeu.length;i++)
            System.out.println(objetsJeu[i]);
        System.out.println("*** On enlÃ¨ve les 18 objets du plateau ... ***");
        for (int i=0;i<objetsJeu.length;i++)
            objetsJeu[i].enleveDuPlateau();
        System.out.println("*** On affiche de nouveau les 18 objets ... ***");
        for (int i=0;i<objetsJeu.length;i++)
            System.out.println(objetsJeu[i]);
    }

}