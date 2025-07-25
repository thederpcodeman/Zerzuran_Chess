package Zerzuran_Chess.src.pieces.Unique;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Rook;

import javax.swing.*;

public class shortRook extends Rook {
    public shortRook(int color) {
        super(color);
        value = 4;
        name = "Very Short Rook (so short you can't see it)";
    }
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bSRook.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wSRook.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dy = newY - y;
        int dx = newX - x;
        if (Math.abs(dy) > 4 || Math.abs(dx) > 4){
            return false;
        }
        return super.isLegalMove(x, y, newX, newY, board, forReal);
    }
}
