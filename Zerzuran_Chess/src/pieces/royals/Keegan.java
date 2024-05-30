package pieces.royals;

import Game.Board;
import Game.Tile;
import pieces.Assassins.Action_Man;
import pieces.Moves;
import pieces.Piece;

import javax.swing.*;

public class Keegan extends Piece {
    public Keegan(int color) {
        super(color);
        value = 4;
        this.royal = true;
        name = "Keegan";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKeegan.PNG"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKeegan.PNG"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dy = newY - y;
        int dx = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && (Moves.frogMove(dx, dy) || Moves.elephantMove(dx, dy)));
    }
}