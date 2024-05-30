package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Quetzacoatl extends Piece {
    public Quetzacoatl(int color) {
        super(color);
        value = 9;
        name = "Quetzacoatl";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bQuetz.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wQuetz.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && Moves.quetzMove(x, y, dx, dy, board));
    }
}
