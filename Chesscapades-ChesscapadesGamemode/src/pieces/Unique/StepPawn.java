package pieces.Unique;

import Game.Board;
import Game.Tile;
import pieces.Moves;
import pieces.pawns.Prince;

import javax.swing.*;

public class StepPawn extends Prince {
    public StepPawn(int color){
        super(color);
        value = 2;
        name = "Pawn";
        setForwardDirection(getForwardDirection() * -1);
    }

    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (getForwardDirection() == 1){
                return(new ImageIcon("src/resources/bPawn.png"));
            }else{
                return(new ImageIcon("src/resources/bupPawn.png"));
            }

        } else if(color == 1) {
            if (getForwardDirection() == -1){
                return(new ImageIcon("src/resources/wPawn.png"));
            }else{
                return(new ImageIcon("src/resources/wupPawn.png"));
            }
        } else {
            return null;
        }
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        int dy = newY - y;
        if (newX != x)
        {
            int dx = Math.abs(newX - x);
            if (dx == 1)
            {
                if(destination.isOccupied() && dy == getForwardDirection())
                {
                    if (destination.getPiece().getColor() != getColor())
                    {
                        return true;
                    }
                } else if ((Board.getLocationFromCords(newX, y) >= 0) && (Board.getLocationFromCords(newX, y) <= 63) && (board.getTile(Board.getLocationFromCords(newX, y)).isOccupied() && (dy == getForwardDirection()))){
                    if ((board.getTile(Board.getLocationFromCords(newX, y)).getPiece().moved2 != 0) && (board.getTile(Board.getLocationFromCords(newX, y)).getPiece().getForwardDirection() == getForwardDirection() * -1)){
                        if (forReal){
                            board.getTile(Board.getLocationFromCords(newX, y)).setPiece(null);
                        }
                        return true;
                    }
                    return false;
                } else if ((Board.getLocationFromCords(newX, y + (2 * getForwardDirection())) >= 0) && (Board.getLocationFromCords(newX, y + (2 * getForwardDirection())) <= 63) && (board.getTile(Board.getLocationFromCords(newX, y + (2 * getForwardDirection()))).isOccupied() && (dy == getForwardDirection()))){
                    if ((board.getTile(Board.getLocationFromCords(newX, y + (2 * dy))).getPiece().moved2 != 0) && (board.getTile(Board.getLocationFromCords(newX, y + (2 * dy))).getPiece().getForwardDirection() == getForwardDirection())){
                        if (forReal){
                            board.getTile(Board.getLocationFromCords(newX, y + (2 * dy))).setPiece(null);
                            if (color == 1){
                                board.wRadness += 1;
                            }else if (color == 0){
                                board.bRadness += 1;
                            }
                        }
                        return true;
                    }
                    return false;
                }

            }
            return false;
        }
        if(destination.isOccupied())
        {
            return false;
        }
        if(dy == getForwardDirection() || ((isOnStartingSquare(y) && dy == getForwardDirection() * 2)) && !board.getTile(Board.getLocationFromCords(newX, newY - getForwardDirection())).isOccupied())
        {
            if ((forReal) && (Math.abs(newY - y)) == 2) {
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
