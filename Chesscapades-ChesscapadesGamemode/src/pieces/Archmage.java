package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Archmage extends Piece {
    public int mana;
    public Archmage(int color) {
        super(color);
        value = 8;
        mana = -1;
        name = "High Mage (mana: ∞)";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bArchmage.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wArchmage.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;

        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (Math.abs(dy) <= 1 && Math.abs(dx) <= 1){
            return (destination.getPiece() == null);
        }
        if (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null){
            return (false);
        }
        if (dx == 0){
            if (Moves.allClear(getColor(), destination)){
                return true;
            }
        }else if (dy == 0){
            if (Moves.allClear(getColor(), destination)){
                return true;
            }
        }else if (Math.abs(dx) == Math.abs(dy)){
            if (Moves.allClear(getColor(), destination)){
                return true;
            }
        }
        return false;
    }
}
