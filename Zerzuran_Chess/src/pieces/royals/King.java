package Zerzuran_Chess.src.pieces.royals;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.*;
import Zerzuran_Chess.src.pieces.pawns.Pawn;

import javax.swing.*;

public class King extends Piece {

    public King(int color) {
        super(color);
        royal = true;
        value = 3;
        name = "King";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("Zerzuran_Chess/src/resources/wKing.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destinationTile = board.getTile(board.getLocationFromCords(newX, newY));

        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if(dx <= 1 && dy <= 1)
        {
            return (Moves.allClear(getColor(), destinationTile));
        }
        if ((Math.abs(dx) == 2 && dy == 0) || (Math.abs(dx) == 3 && dy == 0) || (Math.abs(dy) == 2 && dx == 0) || (Math.abs(dy) == 3 && dx == 0)){
            if (destinationTile.getPiece() != null){
                if (destinationTile.getPiece().getColor() == color && !(destinationTile.getPiece() instanceof Pawn)){
                    return Moves.rookMove(x, y, newX - x, newY - y, board);
                }
            }
        }
        return false;
    }

    public boolean isInCheck(Board board)
    {
        int color = getColor();
        Tile[] enemyPieces = board.getOccupiedTilesOfColor(1 - color);
        Tile king = board.getKing(color);
        for(Tile tile: enemyPieces) {

            if (!(tile.getPiece() instanceof King))
            {
                for (Tile tile2 : tile.getLegalMoves(board))
                {
                    if (tile2 == king)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isOnStartingSquare(int y)
    {
        if(getForwardDirection() == 1)
        {
            return y < 1;
        }
        else
        {
            return y > 6;
        }
    }
}
