package Zerzuran_Chess.src.pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Pegasus extends Piece {
    public Pegasus(int color) {
        super(color);
        value = 5;
        name = "Pegasus";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bPsus.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wPsus.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && Moves.pegasusMove(x, y, dx, dy, board));
    }
}
