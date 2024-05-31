package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Crusader extends Piece {
    public Crusader(int color) {
        super(color);
        value = 3;
        name = "Crusader";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bCrus.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wCrus.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dy = newY - y;
        int dx = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (Math.signum(dy) == Math.signum(getForwardDirection())){
            return (Moves.allClear(getColor(), destination) && Moves.bishopMove(x, y, dx, dy, board));
        }else if (dy != 0){
            return (Moves.allClear(getColor(), destination) && Moves.rookMove(x, y, dx, dy, board));
        }
        return false;
    }
}
