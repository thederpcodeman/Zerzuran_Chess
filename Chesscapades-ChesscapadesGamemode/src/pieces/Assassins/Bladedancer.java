package pieces.Assassins;

import Game.Board;
import Game.ChessGame;
import Game.Tile;
import pieces.Moves;
import pieces.Piece;

import javax.swing.*;

public class Bladedancer extends Piece {
    boolean bonusMove;
    public Bladedancer(int color) {
        super(color);
        value = 7;
        name = "Assassin";
        bonusMove = true;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bAssassin.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wAssassin.png"));
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
