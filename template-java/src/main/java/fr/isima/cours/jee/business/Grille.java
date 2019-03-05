package fr.isima.cours.jee.business;

public class Grille {

    private int grille[][];

    public Grille() {
        grille = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grille[i][j] = 0;
            }
        }

        grille[3][3] = -1;
        grille[4][4] = -1;
        grille[3][4] = 1;
        grille[4][3] = 1;
    }

    public int[][] getGrille(){
        return grille;
    }
}
