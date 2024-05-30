package Game;

import Game.Board;
import Game.Tile;
import pieces.Piece;

public class moveInfo {
    public Tile start;
    public Tile end;
    public Board board;
    public moveInfo(Tile s, Tile e, Board cboard){
        start = s;
        end = e;
        board = cboard;
    }
    public double score(int rng){
        double score = 0;
        int sK = 1;
        for (Tile king : board.getKings(start.getPiece().getColor())){
            sK ++;
        }
        int eK = 1;
        for (Tile king : board.getKings(1 - start.getPiece().getColor())){
            eK ++;
        }
        if (end.getPiece() != null){
            if (end.getPiece().color != start.getPiece().getColor()){
                score += (end.getPiece().value * 100);
                if (end.getPiece().royal) {
                    score += 5000 / eK;
                }
            }else{
                score -= (end.getPiece().value * 100);
                if (end.getPiece().royal) {
                    score -= 5000 / sK;
                }
            }

        }
        if (start.isPlayableMove(end.getLocationOnBoard(), board, false) == 2){
            score -= 1500 / sK;
        }
        Piece store = end.getPiece();
        end.quietlyUpdatePiece(start.getPiece());
        start.quietlyUpdatePiece(null);
        boolean checkVoid = false;
        for (Tile foe : board.getOccupiedTilesOfColor(1 - end.getPiece().getColor())){
            if (foe.isLegalMove(end.getLocationOnBoard(), board, false)){
                score -= end.getPiece().value * 1;
                checkVoid = true;
            }
        }
        for (Tile ours : board.getOccupiedTilesOfColor(end.getPiece().getColor())){
            boolean hit = false;
            for (Tile foe : board.getOccupiedTilesOfColor(1 - end.getPiece().getColor())){
                if (foe.isLegalMove(ours.getLocationOnBoard(), board, false)){
                    score -= end.getPiece().value * 2;
                    hit = true;
                }
            }
            if (hit){
                score -= end.getPiece().value * 7;
            }
        }

        for (Tile king : board.getKings(1 - end.getPiece().getColor())){
            for(Tile tile: board.getOccupiedTilesOfColor(end.getPiece().getColor()))
            {
                if (tile.isLegalMove(king.getLocationOnBoard(), board, false)){
                    if (checkVoid){
                        score += 100 / eK;
                    }else{
                        score += 1000 / eK;
                    }
                }
            }
        }
        start.quietlyUpdatePiece(end.getPiece());
        end.quietlyUpdatePiece(store);
        score += Math.random() * rng;
        return score;
    }
}

//Import Python
//Import SkyNet.py
//SkyNet.becomeSkyNet(moveInfo.java)