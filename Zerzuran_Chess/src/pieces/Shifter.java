package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Shifter extends Piece {
    public int form;
    public Shifter(int color) {
        super(color);
        value = 7;
        name = "Shifter";
        fen = "sh";
        form = (int) (Math.random() * 3);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (form == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bKnight.png"));
            } else if (form == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bBishop.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/bRook.png"));
            }
        } else if(color == 1) {
            if (form == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wKnight.png"));
            } else if (form == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wBishop.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/wRook.png"));
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
            if (Moves.knightMove(dx, dy)){
                if (form == 0){
                    return true;
                }else{
                    if (forReal){
                        form = 0;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.bishopMove(x, y, dx, dy, board)){
                if (form == 1){
                    return true;
                }else{
                    if (forReal){
                        form = 1;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.rookMove(x, y, dx, dy, board)){
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
