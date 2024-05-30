package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Bow extends Piece {
    public Bow(int color) {
        super(color);
        value = 7;
        name = "Archer";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bBow.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wBow.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        if (Math.abs(dy ) <= 1 && Math.abs(dx ) <= 1){
            return false;
        }
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && ( (Moves.bishopMove(x, y, dx, dy,board)) || (Moves.rookMove(x, y, dx, dy, board))));
    }
}
