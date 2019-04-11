package fr.isima.cours.jee.business;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {
    private Grille grille;

    public GameEntity(){
        grille = new Grille();
    }

    public Grille getGrille() {
        return grille;
    }

    public boolean isLegalPlay(int cell_x, int cell_y, int color){
        if (!inBounds(cell_x, cell_y) || grille.getCell(cell_x, cell_y).getValue() != 0) return false;

        for (int ii = 0; ii < DX.length; ii++) {
            boolean sawOther = false;
            int x = cell_x, y = cell_y;
            for (int dd = 0; dd < 8; dd++) {
                x += DX[ii];
                y += DY[ii];
                if (!inBounds(x, y)) break; // stop when we end up off the board
                int piece = grille.getCell(x, y).getValue();
                if (piece == 0) break;
                else if (piece != color) sawOther = true;
                else if (sawOther) return true;
                else break;
            }
        }

        return false;
    }

    public void applyPlay (int cell_x, int cell_y, int color) {
        List<int[]> toFlip = new ArrayList<int[]>();
        // place the piece
        grille.getCell(cell_x, cell_y).setValue(color);
        // determine where this piece captures other pieces
        for (int ii = 0; ii < DX.length; ii++) {
            // look in this direction for captured pieces
            int x = cell_x, y = cell_y;
            for (int dd = 0; dd < 8; dd++) {
                x += DX[ii];
                y += DY[ii];
                if (!inBounds(x, y)) break; // stop when we end up off the board

                int piece = grille.getCell(x, y).getValue();
                int[] coord = new int[2]; coord[0] = x; coord[1] = y;
                if (piece == 0) break;
                else if (piece != color) toFlip.add(coord);
                else { // piece == color
                    for (int[] coordinate : toFlip){
                        int xx = coordinate[0], yy = coordinate[1];
                        grille.getCell(xx, yy).setValue(color); //Flip
                    }
                    break;
                }
            }
            toFlip.clear();
        }
    }

    private final boolean inBounds (int x, int y) {
        return (x >= 0) && (x < 8) && (y >= 0) && (y < 8);
    }

    protected static final int[] DX = { -1,  0,  1, -1, 1, -1, 0, 1 };
    protected static final int[] DY = { -1, -1, -1,  0, 0,  1, 1, 1 };
}
