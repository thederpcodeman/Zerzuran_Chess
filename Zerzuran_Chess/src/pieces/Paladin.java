package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Paladin extends Piece {
    public Paladin(int color) {
        super(color);
        value = 6;
        name = "Paladin";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bPaladin.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wPaladin.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dy = newY - y;
        int dx = newX - x;
        if (dy == 0){
            return true;
        }else if (Math.abs(dy) <= 2){
            System.out.println("o");
            Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
            return (Moves.allClear(getColor(), destination) && Moves.rookMove(x, y, dx, dy, board));
        }
        return false;
    }
}
