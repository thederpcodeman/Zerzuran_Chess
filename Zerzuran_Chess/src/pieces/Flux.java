package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Flux extends Shifter {
    public int duck_power;
    public Flux(int color) {
        super(color);
        value = 8;
        name = "Flux";
        form = (int) (Math.random() * 5);
        duck_power = 0;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (form == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bKnight.png"));
            } else if (form == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bCamel.png"));
            } else if (form == 2) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bBull.png"));
            } else if (form == 3) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bElephant.png"));
            } else if (form == 4) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bFrog.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/bDuck.png"));
            }
        } else if(color == 1) {
            if (form == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wKnight.png"));
            } else if (form == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wCamel.png"));
            } else if (form == 2) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wBull.png"));
            } else if (form == 3) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wElephant.png"));
            } else if (form == 4) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wFrog.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/wDuck.png"));
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
                        wall = false;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.camelMove(dx, dy)){
                if (form == 1){
                    return true;
                }else{
                    if (forReal){
                        form = 1;
                        wall = false;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.bullMove(dx, dy)){
                if (form == 2){
                    return true;
                }else{
                    if (forReal){
                        form = 2;
                        wall = false;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.elephantMove(dx, dy)){
                if (form == 3){
                    return true;
                }else{
                    if (forReal){
                        form = 3;
                        wall = false;
                        return false;
                    }
                    return true;
                }
            }else if (Moves.frogMove(dx, dy)){
                if (form == 4){
                    return true;
                }else{
                    if (forReal){
                        form = 4;
                        wall = false;
                        return false;
                    }
                    return true;
                }
            }else if (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null){
                if (form == 5){
                    if (forReal){
                        duck_power --;
                    }
                    return true;
                }else{
                    if (forReal){
                        duck_power = 10;
                        form = 5;
                        wall = true;
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
