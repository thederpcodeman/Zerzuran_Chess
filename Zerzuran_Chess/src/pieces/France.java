package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class France extends Piece {
    public France(int color) {
        super(color);
        value = 7;
        name = "France";
        fen = "fr";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bFrance.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wFrance.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && (Moves.wyvernMove(x, y, dx, dy, board) || Moves.bishopMove(x, y, dx, dy, board)));
    }
}
