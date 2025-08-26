package Zerzuran_Chess.src.pieces.Assassins;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.royals.Keegan;
import Zerzuran_Chess.src.pieces.Moves;
import Zerzuran_Chess.src.pieces.Piece;

import javax.swing.*;

public class Action_Man extends Piece {
    public Action_Man(int color) {
        super(color);
        value = 6;
        name = "Assassin";
        fen = "as";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bAssassin.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wAssassin.png"));
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
        if (destination.getPiece() != null && !(destination.getPiece() instanceof Shadowmancer) && !(destination.getPiece() instanceof Action_Man)){
            if (destination.isLegalMove(Board.getLocationFromCords(x, y), board, false)){
                return true;
            }
        }
        return ((Math.abs(dx) <= 1) && (Math.abs(dy) <= 1));
    }
}