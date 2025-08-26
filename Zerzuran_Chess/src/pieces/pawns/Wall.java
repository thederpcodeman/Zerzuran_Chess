package Zerzuran_Chess.src.pieces.pawns;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Wall extends Pawn {
    public Wall(int color){
        super(color);
        wall = true;
        name = "Wall (pawn)";
        fen = "wa";
    }

    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (getForwardDirection() == 1){
                return(new ImageIcon("Zerzuran_Chess/src/resources/bWall.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/bupWall.png"));
            }

        } else if(color == 1) {
            if (getForwardDirection() == -1){
                return(new ImageIcon("Zerzuran_Chess/src/resources/Wall.png"));
            }else{
                return(new ImageIcon("Zerzuran_Chess/src/resources/upWall.png"));
            }
        } else {
            return null;
        }
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if (destination.getPiece() == null && (x == newX) && (newY == y + getForwardDirection())){
            return true;
        }
        return false;
    }

}
