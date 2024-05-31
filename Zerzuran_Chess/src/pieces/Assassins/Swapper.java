package Zerzuran_Chess.src.pieces.Assassins;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class Swapper extends Piece {
    public Swapper(int color) {
        super(color);
        value = 5;
        name = "Assassin";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bAssassin.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wAssassin.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if(dx <= 1 && dy <= 1){
            return !(dy == 0 && dx == 0);
        }
        return false;
    }
}
