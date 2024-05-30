package pieces;

import Game.Board;
import Game.ChessGame;
import Game.Tile;

import javax.swing.*;

public class Spearman extends Piece {
    private int forwards;
    public Spearman(int color) {
        super(color);
        value = 1;
        name = "Spearman";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bSpear.png"));

        } else if(color == 1) {
            return(new ImageIcon("src/resources/wSpear.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        int dy = newY - y;
        int dx = newX - x;
        if (dy == 0) {
            if (Math.abs(dx) > 3){
                return false;
            }
            if (Math.abs(dx) == 1){
                return (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null);
            }
            if (Math.abs(dx) == 0){
                return false;
            }
            if (Moves.rookMove(x, y, dx, dy, board) && board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() != null && Moves.allClear(getColor(), board.getTile(Board.getLocationFromCords(newX, newY)))){
                return true;
            }
        }else if (dx == 0){
            if (Math.abs(dy) > 3){
                return false;
            }
            if (Math.abs(dy) == 1){
                return (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null);
            }
            if (Moves.rookMove(x, y, dx, dy, board) && board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() != null && Moves.allClear(getColor(), board.getTile(Board.getLocationFromCords(newX, newY)))){
                return true;
            }
        }
        return false;
    }

}
