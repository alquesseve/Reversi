package fr.isima.cours.jee.business;

public class Grille {

    private Line grille[];

    public Grille() {
        grille = new Line[8];

        for (int i = 0; i < 8; i++) {
            grille[i] = new Line(i);
        }

        grille[3].setCell(3,-1);
        grille[4].setCell(4,-1);
        grille[3].setCell(4,1);
        grille[4].setCell(3, 1);
    }

    public Line[] getGrille(){
        return grille;
    }

    public Cell getCell(int x, int y){
        return grille[x].getCell(y);
    }
}
