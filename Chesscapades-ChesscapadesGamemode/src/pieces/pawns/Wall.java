package pieces.pawns;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Wall extends Pawn {
    public Wall(int color){
        super(color);
        wall = true;
        name = "Wall (pawn)";
    }

    public ImageIcon getImageIcon() {
        return(new ImageIcon("src/resources/Wall.png"));
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if (destination.getPiece() == null && (x == newX) && (newY == y + getForwardDirection())){
            return true;
        }
        return false;
    }

}
