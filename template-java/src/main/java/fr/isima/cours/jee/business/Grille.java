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
    }

    public int[][] getGrille(){
        return grille;
    }
}
