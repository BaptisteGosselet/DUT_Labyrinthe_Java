package composants;
import grafix.interfaceGraphique.IG;

import java.util.Arrays;

import static composants.Utils.genereTabIntAleatoirement;
import static composants.Utils.genererEntier;
import static composants.Piece.nouvellesPieces;




/**
 * Cette classe permet de gÃ©rer un plateau de jeu constituÃ© d'une grille de piÃ¨ces (grille de 7 lignes sur 7 colonnes).
 *
 */
public class Plateau {

    private Piece plateau[][]; // La grille des piÃ¨ces.

    /**
     * A Faire (07/05 Rayane Fini)
     * <p>
     * Constructeur permettant de construire un plateau vide (sans piÃ¨ces) et d'une taille de 7 lignes sur 7 colonnes.
     */
    public Plateau() {
        plateau = new Piece[7][7];
    }

    /**
     * A Faire (07/05 Rayane Fini)
     * <p>
     * MÃ©thode permettant de placer une piÃ¨ce sur le plateau.
     *
     * @param piece          La piÃ¨ce Ã  placer.
     * @param lignePlateau   La ligne du plateau sur laquelle sera placÃ©e la piÃ¨ce (un entier entre 0 et 6).
     * @param colonnePlateau La colonne du plateau sur laquelle sera placÃ©e la piÃ¨ce (une entier entre 0 et 6).
     */
    public void positionnePiece(Piece piece, int lignePlateau, int colonnePlateau) {
        this.plateau[lignePlateau][colonnePlateau] = piece;
    }

    /**
     * A Faire (07/05 Rayane Fini)
     * <p>
     * MÃ©thode retournant une piÃ¨ce se trouvant sur le plateau Ã  un emplacement spÃ©cifique.
     *
     * @param lignePlateau   La ligne du plateau  (un entier entre 0 et 6).
     * @param colonnePlateau La colonne du plateau (un entier entre 0 et 6).
     * @return La piÃ¨ce se trouvant sur la ligne lignePlateau et la colonne colonnePlateau. Si il n'y a pas de piÃ¨ce, null est retournÃ©.
     */
    public Piece getPiece(int lignePlateau, int colonnePlateau) {
        return this.plateau[lignePlateau][colonnePlateau];
    }


    /**
     * A Faire (07/05 Baptiste Fini)
     * <p>
     * MÃ©thode permettant de placer alÃ©atoirment 49 piÃ¨ces du jeu sur le plateau.
     * L'orientation des piÃ¨ces est alÃ©atoire. Les piÃ¨ces utilisÃ©es doivent Ãªtre des nouvelles piÃ¨ces gÃ©nÃ©rÃ©es Ã  partir de la mÃ©thode Piece.nouvellesPieces.
     * Parmi les 50 piÃ¨ces du jeu, la piÃ¨ce qui n'a pas Ã©tÃ© placÃ©e sur le plateau est retournÃ©e par la mÃ©thode.
     *
     * @return La seule piÃ¨ce qui n'a pas Ã©tÃ© placÃ©e sur le plateau
     */
    public Piece placerPiecesAleatoirement() {
        Piece[] pieces = Piece.nouvellesPieces();
        int[] coordonneesX = new int[49];
        int[] coordonneesY = new int[49];
        int[] placements = Utils.genereTabIntAleatoirement(50);
        int[] placementsCoord = Utils.genereTabIntAleatoirement(49);

        int valeur = 0;
        int nbTours = 0;
        for(int i = 0; i<coordonneesX.length; i++){
            coordonneesX[i] = valeur;
            nbTours += 1;
            if(nbTours > 6){
                nbTours = 0;
                valeur += 1;
            }
        }

        valeur = 0;
        for(int i = 0; i<coordonneesY.length; i++){
            coordonneesY[i] = valeur;
            valeur += 1;
            if(valeur > 6) valeur = 0;
        }


        for(int i=0; i< pieces.length-1;i++){
            positionnePiece(pieces[placements[i]], coordonneesX[placementsCoord[i]],coordonneesY[placementsCoord[i]]);
        }

        for(int i=0; i< 49;i++) {
            IG.changerPiecePlateau(coordonneesX[placementsCoord[i]], coordonneesY[placementsCoord[i]], pieces[placements[i]].getModelePiece(), pieces[placements[i]].getOrientationPiece());
        }
        IG.changerPieceHorsPlateau(pieces[placements[49]].getModelePiece(), pieces[placements[49]].getOrientationPiece());
        IG.miseAJourAffichage();

        return pieces[placements[49]];
    }

    /**
         *
         * MÃ©thode utilitaire permettant de tester si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes d'une grille de 7 lignes sur 7 colonnes.
         *
         * @param posLigCase1 Un entier quelconque.
         * @param posColCase1 Un entier quelconque.
         * @param posLigCase2 Un entier quelconque.
         * @param posColCase2 Un entier quelconque.
         * @return true si les les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes d'une grille de 7 lignes sur 7 colonnes, false sinon.
         */
        private static boolean casesAdjacentes ( int posLigCase1, int posColCase1, int posLigCase2, int posColCase2){
            if ((posLigCase1 < 0) || (posLigCase2 < 0) || (posLigCase1 > 6) || (posLigCase2 > 6)) return false;
            if ((posColCase1 < 0) || (posColCase2 < 0) || (posColCase1 > 6) || (posColCase2 > 6)) return false;
            int distLigne = posLigCase1 - posLigCase2;
            if (distLigne < 0) distLigne = -distLigne;
            int distColonne = posColCase1 - posColCase2;
            if (distColonne < 0) distColonne = -distColonne;
            if ((distLigne > 1) || (distColonne > 1) || ((distColonne + distLigne) != 1))
                return false;
            return true;
        }

        /**
         *
         * A Faire (04/05 Pierre fait)
         *
         * MÃ©thode permettant de tester si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes
         * de la grille de jeu et qu'il est possible de passer d'une cas Ã  l'autre compte tenu des deux piÃ¨ces posÃ©es sur les deux cases du plateau.
         *
         * @param posLigCase1 Un entier quelconque.
         * @param posColCase1 Un entier quelconque.
         * @param posLigCase2 Un entier quelconque.
         * @param posColCase2 Un entier quelconque.
         * @return true si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes de la grille de jeu et qu'il est possible de passer d'une cas Ã  l'autre compte tenu des deux piÃ¨ces posÃ©es sur les deux cases du plateau, false sinon.
         */
        private boolean passageEntreCases ( int posLigCase1, int posColCase1, int posLigCase2, int posColCase2){
            boolean adj = false;
            if (posColCase1 != posColCase2 || posLigCase1 != posLigCase2) { //verification si les cases sont differentes
                if (casesAdjacentes(posLigCase1, posColCase1, posLigCase2, posColCase2)) { //verification si les cases sont adjacentes
                    adj = true;
                } else {
                    return false ;
                }
                if (adj) {
                    if ((posColCase1 == posColCase2) && (posLigCase2 == (posLigCase1 - 1))) { //si la case 2 est au dessus de la case 1
                        if (plateau[posLigCase1][posColCase1].getPointEntree(0) && plateau[posLigCase2][posColCase2].getPointEntree(2)) {
                            return true;
                        }
                    }
                    if ((posLigCase1 == posLigCase2) && (posColCase2 == (posColCase1 + 1))) { //si la case 2 est à droite de la case 1
                        if (plateau[posLigCase1][posColCase1].getPointEntree(1) && plateau[posLigCase2][posColCase2].getPointEntree(3)) {
                            return true;
                        }
                    }
                    if ((posColCase1 == posColCase2) && (posLigCase2 == (posLigCase1 + 1))) { //si la case 2 est en dessous de la case 1
                        if (plateau[posLigCase1][posColCase1].getPointEntree(2) && plateau[posLigCase2][posColCase2].getPointEntree(0)) {
                            return true;
                        }
                    }
                    if ((posLigCase1 == posLigCase2) && (posColCase2 == (posColCase1 - 1 ))) { //si la case 2 est à gauche de la case 1
                        if (plateau[posLigCase1][posColCase1].getPointEntree(3) && plateau[posLigCase2][posColCase2].getPointEntree(1)) {
                            return true;
                        }
                    }
                }
            }
            return false ;
        }


        /**
         *
         * A Faire (Quand Pierre Statut)
         *
         * MÃ©thode permettant de retourner un Ã©ventuel chemin entre deux cases du plateau compte tenu des piÃ¨ces posÃ©es sur le plateau.
         * Dans le cas oÃ¹ il n'y a pas de chemin entre les deux cases, la valeur null est retournÃ©e.
         * Dans le cas oÃ¹ il existe un chemin, un chemin possible est retournÃ© sous forme d'un tableau d'entiers Ã  deux dimensions.
         * La premiÃ¨re dimension correspond aux cases du plateau Ã  emprunter pour aller de la case de dÃ©part Ã  la case d'arrivÃ©e.
         * Dans ce tableau, chaque case est un tableau de deux entiers avec le premier entier qui correspond Ã  la ligne de la case et
         * le second entier qui correspond Ã  la colonne de la case. La premiÃ¨re case d'un chemin retournÃ© correspond toujours
         * Ã  la case (posLigCaseDep,posColCaseDep) et la derniÃ¨re case correspond toujours Ã  la case (posLigCaseArr,posColCaseArr).
         *
         * @param posLigCaseDep La ligne de la case de dÃ©part (un entier compris entre 0 et 6).
         * @param posColCaseDep La colonne de la case de dÃ©part (un entier compris entre 0 et 6).
         * @param posLigCaseArr La ligne de la case d'arrivÃ©e (un entier compris entre 0 et 6).
         * @param posColCaseArr La colonne de la case d'arrivÃ©e (un entier compris entre 0 et 6).
         * @return null si il n'existe pas de chemin entre les deux case, un chemin sinon.
         */
        public int[][] calculeChemin ( int posLigCaseDep, int posColCaseDep, int posLigCaseArr, int posColCaseArr) {

            int[][] Plateau = new int[7][7];

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    Plateau[i][j] = -1;
                }
            }

            Plateau[posLigCaseDep][posColCaseDep] = 0;
            int nbTours = 0;


            while (Plateau[posLigCaseArr][posColCaseArr] == -1) {

                boolean cheminExiste = false;
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (Plateau[i][j] == nbTours) {
                            cheminExiste = true;
                            break;
                        }
                    }
                }

                if (cheminExiste == false) return null;

                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {

                        if (Plateau[i][j] == nbTours) {

                            //en dessous (i+1)
                            if(i <= 5){
                                if(passageEntreCases(i,j,i+1,j)){
                                    if(Plateau[i+1][j] == -1){
                                        Plateau[i+1][j] = nbTours + 1;
                                    }
                                }
                            }

                            //au dessus (i-1)
                            if(i >= 1){
                                if(passageEntreCases(i,j,i-1,j)){
                                    if(Plateau[i-1][j] == -1){
                                        Plateau[i-1][j] = nbTours + 1;
                                    }
                                }
                            }

                            //à droite (j+1)
                            if(j <= 5){
                                if(passageEntreCases(i,j,i,j+1)){
                                    if(Plateau[i][j+1] == -1){
                                        Plateau[i][j+1] = nbTours + 1;
                                    }
                                }
                            }

                            //à gauche (j-1)
                            if(j >= 1){
                                if(passageEntreCases(i,j,i,j-1)){
                                    if(Plateau[i][j-1] == -1){
                                        Plateau[i][j-1] = nbTours + 1;
                                    }
                                }
                            }


                        }

                    }
                }

                nbTours += 1 ;
            }

            int[][] cheminFinal = new int[nbTours+1][2];
            cheminFinal[nbTours][0] = posLigCaseArr;
            cheminFinal[nbTours][1] = posColCaseArr;
            cheminFinal[0][0] = posLigCaseDep;
            cheminFinal[0][1] = posColCaseDep;

            int[] caseActuelle = new int[2];
            caseActuelle[0] = posLigCaseArr;
            caseActuelle[1] = posColCaseArr;

            int[] caseProche = new int[2];
            int minimum = nbTours;

            for(int h=1; h<nbTours; h++){
                int i = caseActuelle[0];
                int j = caseActuelle[1];

                //en dessous (i+1)
                if(i <= 5){
                    if(passageEntreCases(i,j,i+1,j)){
                        if((Plateau[i+1][j] != -1) && (Plateau[i+1][j] < minimum)){
                            caseProche[0] = i+1;
                            caseProche[1] = j;
                            minimum = Plateau[i+1][j];
                        }
                    }
                }

                //au dessus (i-1)
                if(i >= 1){
                    if(passageEntreCases(i,j,i-1,j)){
                        if((Plateau[i-1][j] != -1) && (Plateau[i-1][j] < minimum)){
                            caseProche[0] = i-1;
                            caseProche[1] = j;
                            minimum = Plateau[i-1][j];
                        }
                    }
                }

                //A droite (j+1)
                if(j <= 5){
                    if(passageEntreCases(i,j,i,j+1)){
                        if((Plateau[i][j+1] != -1) && (Plateau[i][j+1] < minimum)){
                            caseProche[0] = i;
                            caseProche[1] = j+1;
                            minimum = Plateau[i][j+1];
                        }
                    }
                }

                //A gauche (j-1)
                if(j >= 1){
                    if(passageEntreCases(i,j,i,j-1)){
                        if((Plateau[i][j-1] != -1) && (Plateau[i][j-1] < minimum)){
                            caseProche[0] = i;
                            caseProche[1] = j-1;
                            minimum = Plateau[i][j-1];
                        }
                    }
                }

                caseActuelle[0] = caseProche[0];
                caseActuelle[1] = caseProche[1];

                cheminFinal[nbTours-h][0] = caseActuelle[0];
                cheminFinal[nbTours-h][1] = caseActuelle[1];
            }

            return cheminFinal;

        }


            /**
             *
             * MÃ©thode permettant de calculer un chemin dÃ©taillÃ© (chemin entre sous-cases) Ã  partir d'un chemin entre cases.
             *
             * @param chemin Un tableau reprÃ©sentant un chemin de cases.
             * @param numJoueur Le numÃ©ro du joueur pour lequel nous souaitons construire un chemin dÃ©taillÃ©.
             *
             * @return Le chemin dÃ©taillÃ© correspondant au chemin de positions de piÃ¨ces donnÃ©es en paramÃ¨tre et pour le numÃ©ro de joueur donnÃ©.
             */
            public int[][] calculeCheminDetaille ( int[][] chemin, int numJoueur){
                if (chemin.length == 1)
                    return new int[0][0];
                int[][] cheminDetaille = new int[chemin.length * 5][4];
                int pos = 0;
                int col, lig, colS, ligS;
                for (int i = 0; i < chemin.length - 1; i++) {
                    lig = chemin[i][0];
                    col = chemin[i][1];
                    ligS = chemin[i + 1][0];
                    colS = chemin[i + 1][1];
                    if (ligS < lig) {
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 1;
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 0;
                        cheminDetaille[pos++][3] = 1;
                        cheminDetaille[pos][0] = ligS;
                        cheminDetaille[pos][1] = colS;
                        cheminDetaille[pos][2] = 2;
                        cheminDetaille[pos++][3] = 1;
                    } else if (ligS > lig) {
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 1;
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 2;
                        cheminDetaille[pos++][3] = 1;
                        cheminDetaille[pos][0] = ligS;
                        cheminDetaille[pos][1] = colS;
                        cheminDetaille[pos][2] = 0;
                        cheminDetaille[pos++][3] = 1;
                    } else if (colS < col) {
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 1;
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 0;
                        cheminDetaille[pos][0] = ligS;
                        cheminDetaille[pos][1] = colS;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 2;
                    } else if (colS > col) {
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 1;
                        cheminDetaille[pos][0] = lig;
                        cheminDetaille[pos][1] = col;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 2;
                        cheminDetaille[pos][0] = ligS;
                        cheminDetaille[pos][1] = colS;
                        cheminDetaille[pos][2] = 1;
                        cheminDetaille[pos++][3] = 0;
                    }
                }
                cheminDetaille[pos][0] = chemin[chemin.length - 1][0];
                cheminDetaille[pos][1] = chemin[chemin.length - 1][1];
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 1;

                int debut = 0;
                if ((numJoueur == 0) && ((cheminDetaille[pos - 2][2] == 0) || (cheminDetaille[pos - 2][3] == 2))) pos--;
                if ((numJoueur == 1) && ((cheminDetaille[pos - 2][2] == 2) || (cheminDetaille[pos - 2][3] == 2))) pos--;
                if ((numJoueur == 2) && ((cheminDetaille[pos - 2][2] == 2) || (cheminDetaille[pos - 2][3] == 0))) pos--;
                if ((numJoueur == 0) && ((cheminDetaille[1][2] == 0) || (cheminDetaille[0][3] == 2))) debut++;
                if ((numJoueur == 1) && ((cheminDetaille[1][2] == 2) || (cheminDetaille[0][3] == 2))) debut++;
                if ((numJoueur == 2) && ((cheminDetaille[1][2] == 2) || (cheminDetaille[0][3] == 0))) debut++;

                int[][] resultat = new int[pos - debut][4];
                for (int i = debut; i < pos; i++)
                    for (int j = 0; j < 4; j++)
                        resultat[i - debut][j] = cheminDetaille[i][j];
                return resultat;
            }

            /**
             *
             * MÃ©thode retournant une copie du plateau avec des copies de ses piÃ¨ces.
             *
             * @return Une copie du plateau avec une copie de toutes ses piÃ¨ces.
             */
            public Plateau copy () {
                Plateau plateau = new Plateau();
                for (int ligne = 0; ligne < 7; ligne++)
                    for (int colonne = 0; colonne < 7; colonne++)
                        plateau.positionnePiece((this.plateau[ligne][colonne]).copy(), ligne, colonne);
                return plateau;
            }
}