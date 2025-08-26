package Zerzuran_Chess.src.pieces.Assassins;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Cloning;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class Chameleon extends Piece {
    private Piece s;
    public Chameleon(int color) {
        super(color);
        name = "Assassin";
        fen = "as";
        s = Cloning.Fear(color);
        value = s.value + 1;
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
        boolean b = s.isLegalMove(x, y, newX, newY, board, forReal);
        if (forReal){
            if (((int) (Math.random() * 20)) == 1){
                s = Cloning.Fear(color);
                value = s.value + 1;
                wall = s.wall;
                bomb = s.bomb;
                royal = s.royal;
            }
        }
        return b;
    }

}
