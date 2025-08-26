package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Buffalo extends Piece {
    public Buffalo(int color) {
        super(color);
        value = 7;
        name = "Buffalo";
        fen = "bf";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bBuffalo.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wBuffalo.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && (Moves.knightMove(dx, dy) || Moves.camelMove(dx, dy) || Moves.bullMove(dx, dy)));
    }
}
