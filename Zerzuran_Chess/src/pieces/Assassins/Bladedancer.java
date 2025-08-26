package Zerzuran_Chess.src.pieces.Assassins;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.ChessGame;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class Bladedancer extends Piece {
    public boolean bonusMove;
    public Bladedancer(int color) {
        super(color);
        value = 7;
        name = "Assassin";
        fen = "as";
        bonusMove = true;
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
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        if (Math.abs(dy ) <= 1 && Math.abs(dx ) <= 1){
            return false;
        }
        if (Math.abs(dy ) > 2 || Math.abs(dx ) > 2){
            return false;
        }
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (forReal){
            if (destination.getPiece() != null){
                if (bonusMove){
                    bonusMove = false;
                    ChessGame.turn = (1 - ChessGame.turn);
                }
            }else{
                bonusMove = true;
            }
        }
        return (Moves.allClear(getColor(), destination));
    }
}
