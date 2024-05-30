package pieces.Unique;

import Game.Board;
import Game.ChessGame;
import Game.Tile;
import pieces.pawns.Pawn;

import javax.swing.*;

public class CheckerButNot extends Pawn {
    public CheckerButNot(int color){
        super(color);
        value = 2;
        name = "Checker (pawn)";
    }

    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (royal){
                return(new ImageIcon("src/resources/bupChecker.png"));
            }else{
                return(new ImageIcon("src/resources/bChecker.png"));
            }

        } else if(color == 1) {
            if (royal){
                return(new ImageIcon("src/resources/wupChecker.png"));
            }else{
                return(new ImageIcon("src/resources/wChecker.png"));
            }
        } else {
            return null;
        }
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        int dy = newY - y;
        int dx = newX - x;
        if (dx == 0 || dy == 0){
            return false;
        }
        if (destination.getPiece() == null && Math.abs(dx) == Math.abs(dy)){
            if (Integer.signum(dy) == Integer.signum(getForwardDirection()) || (royal)){
                if (Math.abs(dy) == 1){
                    return true;
                }
                if (Math.abs(dy) == 2){
                    Tile hop = board.getTile(Board.getLocationFromCords(x + (dx / 2), y + (dy / 2)));
                    if (hop.getPiece() != null && hop.getPiece().color != color){
                        if (forReal){
                            hop.setPiece(null);
                            moved2 = 2;
                            ChessGame.turn = (1 - ChessGame.turn);
                        }
                        return true;
                    }
                }
            }
        }
        if (y == 1 && newY == 0 && Math.abs(dx) == 1){
            return true;
        }
        return false;
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
