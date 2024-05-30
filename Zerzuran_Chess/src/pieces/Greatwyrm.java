package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Greatwyrm extends Piece {
    public Greatwyrm(int color) {
        super(color);
        value = 14;
        name = "Greatwyrm";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bGreatwyrm.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wGreatwyrm.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && (Moves.wyvernMove(x, y, dx, dy, board) || Moves.gryphonMove(x, y, dx, dy, board)));
    }
}
