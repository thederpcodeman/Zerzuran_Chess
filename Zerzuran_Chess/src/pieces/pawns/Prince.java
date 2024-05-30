package pieces.pawns;

import Game.Board;
import Game.Tile;
import pieces.Moves;

import javax.swing.*;

    public class Prince extends Pawn {
        public Prince(int color){
            super(color);
            value = 2;
            name = "Prince (pawn)";
        }

        public ImageIcon getImageIcon() {
            if(color == 0) {
                if (getForwardDirection() == 1){
                    return(new ImageIcon("src/resources/bPrince.png"));
                }else{
                    return(new ImageIcon("src/resources/bupPrince.png"));
                }

            } else if(color == 1) {
                if (getForwardDirection() == -1){
                    return(new ImageIcon("src/resources/wPrince.png"));
                }else{
                    return(new ImageIcon("src/resources/wupPrince.png"));
                }
            } else {
                return null;
            }
        }
        public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
            Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
            int dy = newY - y;
            int dx = newX - x;
            return ((dy == getForwardDirection() || dy == 0) && Math.abs(dx) <= 1 && Moves.allClear(getColor(), destination));
        }

        private boolean isOnStartingSquare(int y)
        {
            if(getForwardDirection() == 1)
            {
                return y <= 1;
            }
            else
            {
                return y >= 6;
            }
        }
    }
