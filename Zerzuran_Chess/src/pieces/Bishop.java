package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
        value = 3;
        name = "Bishop";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bBishop.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wBishop.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dy = newY - y;
        int dx = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return(Moves.allClear(getColor(), destination) && Moves.bishopMove(x, y, dx, dy, board));
    }
}
