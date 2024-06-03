package Zerzuran_Chess.src.pieces.Assassins;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class Shadowmancer extends Piece {
    private boolean cR;
    public Shadowmancer(int color) {
        super(color);
        value = 6;
        name = "Assassin";
        cR = false;
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
        Boolean can = true;
        if (!cR){
            Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
            if (!Moves.allClear(getColor(), destination)){
                return false;
            }else if (forReal){
                cR = true;
            }
        }
        for (int i = 0; i < 64; i++){
            if (board.getTile(i).getPiece() != null){
                if (board.getTile(i).getPiece().getColor() != getColor()){
                    if (board.getTile(i).isLegalMove(i, board, false)){
                        can = false;
                    }
                }
            }
        }
        return can;
    }
}
