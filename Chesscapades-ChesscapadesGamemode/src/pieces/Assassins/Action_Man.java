package pieces.Assassins;

import Game.Board;
import Game.Tile;
import pieces.royals.Keegan;
import pieces.Moves;
import pieces.Piece;

import javax.swing.*;

public class Action_Man extends Piece {
    public Action_Man(int color) {
        super(color);
        value = 6;
        name = "Assassin";
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
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (!Moves.allClear(color, destination)){
            return false;
        }
        if (destination.getPiece() != null && !(destination.getPiece() instanceof Keegan) && !(destination.getPiece() instanceof Action_Man)){
            if (destination.isLegalMove(Board.getLocationFromCords(x, y), board, false)){
                return true;
            }
        }
        return ((Math.abs(dx) <= 1) && (Math.abs(dy) <= 1));
    }
}