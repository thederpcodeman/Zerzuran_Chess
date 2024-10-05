package Zerzuran_Chess.src.Game;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Piece;

public class moveInfo {
    public Tile start;
    public Tile end;
    public Board board;
    public moveInfo(Tile s, Tile e, Board cboard){
        start = s;
        end = e;
        board = cboard;
    }
    public double score(AI com){
        //define score
        double score = 0;
        int us = start.getPiece().getColor();

        //pre move scoreing
        //power
        score += com.power * start.getPiece().value;
        //random
        score += com.random * Math.random();
        //safety
        if (start.isPlayableMove(end.getLocationOnBoard(), board, false) == 2){
            score -= com.safety;
        }
        //pessant
        //add pessant code

        //do the move
        Piece store = end.getPiece();
        end.quietlyUpdatePiece(start.getPiece());
        start.quietlyUpdatePiece(null);

        //material
        for (Tile i : board.getOccupiedTilesOfColor(us)){
            score += com.material * i.getPiece().value;
        }
        for (Tile i : board.getOccupiedTilesOfColor(1 - us)){
            score -= com.material * i.getPiece().value;
        }



        //undo move
        start.quietlyUpdatePiece(end.getPiece());
        end.quietlyUpdatePiece(store);
        return score;
    }
}

//Import Python
//Import SkyNet.py
//SkyNet.becomeSkyNet(moveInfo.java)