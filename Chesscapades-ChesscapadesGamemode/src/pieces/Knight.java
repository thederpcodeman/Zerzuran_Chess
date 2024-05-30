package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Knight extends Piece {
    public Knight(int color) {
        super(color);
        value = 3;
        name = "Knight";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKnight.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKnight.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && Moves.knightMove(dx, dy));
    }
}
