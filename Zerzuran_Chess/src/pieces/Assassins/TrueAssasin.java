package Zerzuran_Chess.src.pieces.Assassins;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Cloning;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class TrueAssasin extends Piece {
    private Piece s;
    public TrueAssasin(int color) {
        super(color);
        name = "Assassin";
        s = Cloning.Fear(color);
        value = s.value;
        wall = s.wall;
        bomb = s.bomb;
        royal = s.royal;
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
        return s.isLegalMove(x, y, newX, newY, board, forReal);
    }

}
