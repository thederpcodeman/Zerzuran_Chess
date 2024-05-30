package pieces.Unique;

import Game.Board;
import Game.Tile;
import pieces.Rook;

import javax.swing.*;

public class shortRook extends Rook {
    public shortRook(int color) {
        super(color);
        value = 4;
        name = "Short Rook";
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
