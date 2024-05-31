package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.ChessGame;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Duck extends Piece {
    public int bonusMove;
    public Duck(int color) {
        super(color);
        value = -1;
        name = "Duck";
        bonusMove = 0;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bDuck.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wDuck.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (destination.getPiece() == null){
            if (forReal){
                color = 1 - color;
                if (bonusMove == 0){
                    bonusMove = 2;
                    ChessGame.turn = (1 - ChessGame.turn);
                }
            }
            return true;
        }
        return false;
    }
}
