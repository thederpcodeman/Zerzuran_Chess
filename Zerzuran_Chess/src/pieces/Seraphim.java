package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.ChessGame;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Assassins.Bladedancer;

import javax.swing.*;

public class Seraphim extends Bladedancer {
    private boolean bm;
    public Seraphim(int color) {
        super(color);
        value = 12;
        name = "Seraphim";
        fen = "se";
        bm = true;
    }
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bSeraphim.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wSeraphim.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (Math.abs(dy ) <= 1 && Math.abs(dx) <= 1){
            return (Moves.allClear(getColor(), destination) && Moves.bishopMove(x, y, dx, dy, board));
        }
        if (Math.abs(dy ) > 2 || Math.abs(dx) > 2){
            return (Moves.allClear(getColor(), destination) && Moves.bishopMove(x, y, dx, dy, board));
        }
        if (forReal){
            if (destination.getPiece() != null){
                if (bm){
                    bm = false;
                    ChessGame.turn = (1 - ChessGame.turn);
                }
            }else{
                bm = true;
            }
        }
        return (Moves.allClear(getColor(), destination));
    }
}
