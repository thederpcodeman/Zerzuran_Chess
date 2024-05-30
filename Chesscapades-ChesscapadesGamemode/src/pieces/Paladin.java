package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Paladin extends Piece {
    public Paladin(int color) {
        super(color);
        value = 7;
        name = "Paladin";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bPaladin.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wPaladin.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (dy == 0){
            return false;
        }
        return (Moves.allClear(getColor(), destination) && ( (Moves.bishopMove(x, y, dx, dy,board)) || (Moves.rookMove(x, y, dx, dy, board))));
    }
}
