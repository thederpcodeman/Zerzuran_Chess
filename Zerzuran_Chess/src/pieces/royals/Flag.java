package Zerzuran_Chess.src.pieces.royals;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.*;

import javax.swing.*;

public class Flag extends Piece {

    public Flag(int color) {
        super(color);
        royal = true;
        value = 4;
        name = "Flag bearer";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bFlags.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wFlag.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destinationTile = board.getTile(board.getLocationFromCords(newX, newY));
        if (!Moves.allClear(getColor(), destinationTile)){
            return false;
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if(dx <= 1 && dy <= 1)
        {
            if (forReal && isEndSquare(newY)){
                for (Tile i : board.getOccupiedTiles()){
                    if (!i.getPiece().royal){
                        i.getPiece().color = color;
                        i.getPiece().royal = true;
                        i.setPiece(i.getPiece());
                    }

                }
            }
            return true;
        }

        return false;
    }

    private boolean isEndSquare(int y)
    {
        if(getForwardDirection() == 1)
        {
            return y == 7;
        }
        else
        {
            return y == 0;
        }
    }
}
