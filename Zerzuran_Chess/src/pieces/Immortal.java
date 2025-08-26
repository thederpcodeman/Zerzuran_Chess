package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Immortal extends Piece{

        public Immortal(int color) {
            super(color);
            wall = true;
            value = 4;
            name = "Immortal";
            fen = "im";
        }

        @Override
        public ImageIcon getImageIcon() {
            if(color == 0) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/bImm.png"));
            } else if(color == 1) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/wImm.png"));
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
                if (destinationTile.getPiece() != null && destinationTile.getPiece().royal){
                    return false; //cannot capture royals.
                }
                return true;
            }
            return false;
        }
    }
