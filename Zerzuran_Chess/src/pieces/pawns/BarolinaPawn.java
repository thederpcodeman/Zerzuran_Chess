package pieces.pawns;

import Game.Board;
import Game.Tile;
import pieces.Moves;
import pieces.Piece;

import javax.swing.*;

public class BarolinaPawn extends Pawn {
    private int forwards;
    public BarolinaPawn(int color) {
        super(color);
        value = 1;
        name = "Barolina Pawn";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (getForwardDirection() == 1){
                return(new ImageIcon("src/resources/bBPawn.png"));
            }else{
                return(new ImageIcon("src/resources/bupBPawn.png"));
            }

        } else if(color == 1) {
            if (getForwardDirection() == -1){
                return(new ImageIcon("src/resources/wBPawn.png"));
            }else{
                return(new ImageIcon("src/resources/wupBPawn.png"));
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

        if (dx == 0){
            if (dy == getForwardDirection() && destination.isOccupied()){
                return (Moves.allClear(color, destination));
            }
            return false;
        }else if (Math.abs(dx) == 1){
            if (dy == getForwardDirection() && !destination.isOccupied()){
                return (Moves.allClear(color, destination));
            }
        }else if (Math.abs(dx) == 2){
            if (dy == getForwardDirection() * 2 && !destination.isOccupied() && !board.getTile(Board.getLocationFromCords(x + (dx / 2), y + (dy /2))).isOccupied() ){
                return (Moves.allClear(color, destination));
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
