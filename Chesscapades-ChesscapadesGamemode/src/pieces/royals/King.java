package pieces.royals;

import Game.Board;
import Game.Tile;
import pieces.*;

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
            return(new ImageIcon("src/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKing.png"));
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
            return true;
        }
            if (newY == y && x == 4) {
                int direction;
                Tile tile;
                if (newX == 2)
                {
                    direction = -1;
                    tile = board.getTile(Board.getLocationFromCords(0, y));
                    Tile extraTile = board.getTile(Board.getLocationFromCords(1, y));
                    if(extraTile.getPiece() != null)
                    {
                        return false;
                    }
                }
                else if (newX == 6)
                {
                    direction = 1;
                    tile = board.getTile(Board.getLocationFromCords(7, y));
                }
                else
                {
                    return false;
                }
                int newRookDestination = Board.getLocationFromCords(newX - direction, newY);
                Tile newRookTile = board.getTile(newRookDestination);
                    if (!isInCheck(board) && tile.getPiece() != null && (tile.getPiece() instanceof Rook || tile.getPiece() instanceof Queen || tile.getPiece() instanceof Chancellor || tile.getPiece() instanceof Amazon ))
                    {
                        if(forReal)
                        {
                            newRookTile.setPiece(tile.getPiece());
                            tile.setPiece(null);
                        }
                        return true;
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
