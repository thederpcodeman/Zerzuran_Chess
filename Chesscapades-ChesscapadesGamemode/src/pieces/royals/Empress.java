package pieces.royals;

import Game.Board;
import Game.Tile;
import pieces.Moves;
import pieces.Piece;

import javax.swing.*;

public class Empress extends Piece {
    public Empress(int color) {
        super(color);
        value = 6;
        royal = true;
        name = "Empress";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bEmpress.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wEmpress.png"));
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
