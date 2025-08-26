package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Metamorph extends Shifter {
    public Metamorph(int color) {
        super(color);
        value = 13;
        name = "Metamorph";
        fen = "me";
        form = (int) (Math.random() * 3);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (form == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bQueen.png"));
            } else if (form == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bManticore.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/bGryphon.png"));
            }
        } else if(color == 1) {
            if (form == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wQueen.png"));
            } else if (form == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wManticore.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/wGryphon.png"));
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (Moves.allClear(getColor(), destination)){
            if (Moves.bishopMove(x, y, dx, dy, board) || Moves.rookMove(x, y, dx, dy, board)){
                if (form == 0){
                    return true;
                }else{
                    if (forReal){
                        form = 0;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.wyvernMove(x, y, dx, dy, board)){
                if (form == 1){
                    return true;
                }else{
                    if (forReal){
                        form = 1;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.gryphonMove(x, y, dx, dy, board)){
                if (form == 2){
                    return true;
                }else{
                    if (forReal){
                        form = 2;
                        return false;
                    }
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }
}
