package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Wolf extends Piece {
    private int speed;
    public Wolf(int color) {
        super(color);
        value = 10;
        speed = 3;
        name = "Wolf";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bWolf.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wWolf.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (forReal){
            if (speed < 4 || destination.getPiece() != null){
                speed++;
            }
        }
        return (Moves.allClear(color, destination) && Moves.norseMove(board, speed, x, y, newX, newY));
    }
}