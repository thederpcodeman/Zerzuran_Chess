package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Boat extends Piece {
    public Boat(int color) {
        super(color);
        value = 5;
        name = "Ship";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bBoat.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wBoat.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Math.abs(dx) <= 1 && Moves.allClear(getColor(), destination) && Moves.gryphonMove(x, y, dx, dy, board));
    }
}
