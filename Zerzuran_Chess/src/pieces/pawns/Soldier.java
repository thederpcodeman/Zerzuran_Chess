package Zerzuran_Chess.src.pieces.pawns;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;

import javax.swing.*;

public class Soldier extends Pawn {
    public Soldier(int color){
        super(color);
        name = "Soldier (pawn)";
        fen = "sl";
    }

    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (getForwardDirection() == 1){
                return(new ImageIcon("Zerzuran_Chess/src/resources/bSoldier.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/bupSoldier.png"));
            }

        } else if(color == 1) {
            if (getForwardDirection() == -1){
                return(new ImageIcon("Zerzuran_Chess/src/resources/wSoldier.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/wupSoldier.png"));
            }
        } else {
            return null;
        }
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if (!Moves.allClear(getColor(), destination) || x != newX){
            return false;
        }
        int dy = newY - y;

        if (dy == getForwardDirection()){
            return true;
        } else if (dy == 2 * getForwardDirection() && isOnStartingSquare(y) && board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null && board.getTile(Board.getLocationFromCords(newX, newY - getForwardDirection())).getPiece() == null){
            if (forReal){
                moved2 = 2;
            }
            return true;
        }
        return false;
    }
    private boolean isOnStartingSquare(int y)
    {
        if(getForwardDirection() == 1)
        {
            return y <= 1;
        }
        else
        {
            return y >= 6;
        }
    }
}
