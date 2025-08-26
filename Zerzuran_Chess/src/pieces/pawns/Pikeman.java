package Zerzuran_Chess.src.pieces.pawns;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Moves;

import javax.swing.*;

public class Pikeman extends Pawn {
    private int forwards;
    public Pikeman(int color) {
        super(color);
        value = 1;
        name = "Pikeman (pawn)";
        fen = "pk";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (getForwardDirection() == 1){
                return(new ImageIcon("Zerzuran_Chess/src/resources/bPikeman.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/bupPikeman.png"));
            }

        } else if(color == 1) {
            if (getForwardDirection() == -1){
                return(new ImageIcon("Zerzuran_Chess/src/resources/wPikeman.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/wupPikeman.png"));
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        int dy = newY - y;
        int dx = newX - x;
        if (Math.abs(dx) > 1 || !Moves.allClear(color, destination)){
            return false;
        }else if (Math.abs(dx) == 1){
            return (dy == 0 && board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null);
        }else if (dy == getForwardDirection()) {
            return (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null);
        }else if (dy == 2 * getForwardDirection()){
            if (board.getTile(Board.getLocationFromCords(newX, y + getForwardDirection())).getPiece() == null){
                if (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() != null){
                    if (forReal){
                        moved2 = 2;
                    }
                    return true;
                }
            }else if (board.getTile(Board.getLocationFromCords(newX, y + getForwardDirection())).getPiece().moved2 > 0 && board.getTile(Board.getLocationFromCords(newX, y + getForwardDirection())).getPiece().getForwardDirection() != getForwardDirection() && board.getTile(Board.getLocationFromCords(newX, y + getForwardDirection())).getPiece().getColor() != getColor()){
                if (forReal){
                    moved2 = 2;
                    board.getTile(Board.getLocationFromCords(newX, y + getForwardDirection())).setPiece(null);
                }
                return true;
            }

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
