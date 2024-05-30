package Zerzuran_Chess.src.pieces.royals;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class Theocrat extends Piece {
    public Theocrat(int color) {
        super(color);
        value = 7;
        name = "Theocrat";
        royal = true;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bTheo.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wTheo.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return ((destination.getPiece() == null || destination.getPiece().royal) && ((Moves.knightMove(dx, dy) || (Moves.bishopMove(x, y, dx, dy,board)))));
    }
}
