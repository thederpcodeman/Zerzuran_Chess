package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.pieces.Assassins.Bladedancer;

import javax.swing.*;

public class Seraphim extends Bladedancer {
    public Seraphim(int color) {
        super(color);
        value = 10;
        name = "Seraphim";
        wall = true;
    }
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bSeraphim.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wSeraphim.png"));
        } else {
            return null;
        }
    }
}
