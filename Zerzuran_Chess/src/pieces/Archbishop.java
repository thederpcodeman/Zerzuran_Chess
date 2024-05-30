package Zerzuran_Chess.src.pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Archbishop extends Piece {
    public Archbishop(int color) {
        super(color);
        value = 7;
        name = "Archbishop";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bArchbishop.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wArchbishop.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && (Moves.knightMove(dx, dy) || (Moves.bishopMove(x, y, dx, dy,board))));
    }
}
