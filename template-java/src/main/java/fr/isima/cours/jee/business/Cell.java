package fr.isima.cours.jee.business;

public class Cell {
    public int value;
    public int line;
    public int column;

    public Cell(int l, int c){
        value = 0;
        line = l;
        column = c;
    }

    public Cell(int n, int l, int c){
        value = n;
        line = l;
        column = c;
    }

    public void setValue(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }
}
