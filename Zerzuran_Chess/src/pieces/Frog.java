package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Frog extends Piece {
    public Frog(int color) {
        super(color);
        value = 3;
        name = "Frog";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bFrog.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wFrog.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dy = newY - y;
        int dx = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && Moves.frogMove(dx, dy));
    }
}
