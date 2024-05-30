package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Lion extends Piece {
    public Lion(int color) {
        super(color);
        value = 12;
        name = "Lion";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bLion.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wLion.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int yoffset = newY - y;
        int xoffset = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if(destination.isOccupied()) {
            if(destination.getPiece().getColor() == getColor()) {return false;}
        }
        if (Math.abs(yoffset) > 2){
            return false;
        }
        if (Math.abs(xoffset) > 2){
            return false;
        }
        return true;
        }
    }
