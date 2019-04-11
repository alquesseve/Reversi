package fr.isima.cours.jee.business;

public class Line {
    public Cell ligne[];

    public Line(int n){
        ligne = new Cell[8];

        for (int i = 0; i < 8; i++) {
            ligne[i] = new Cell(n, i);
        }
    }

    public Cell getCell(int i){
        return ligne[i];
    }

    public void setCell(int i, int v){
        ligne[i].setValue(v);
    }

    public Cell[] getLine(){
        return ligne;
    }
}
