package Zerzuran_Chess.src.Game;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Piece;
import Zerzuran_Chess.src.pieces.pawns.Pawn;
import Zerzuran_Chess.src.pieces.Mage;

import java.util.ArrayList;


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

        //pre move scoring
        //power
        score += com.power * start.getPiece().value;
        //random
        score += com.random * Math.random();
        //safety
        if (start.isPlayableMove(end.getLocationOnBoard(), board, false) == 2){
            score -= com.safety;
        }
        //peasant
        if (start.getPiece() instanceof Pawn && end.getLocationOnBoard() + (us * 16) - 8 > 64 && end.getLocationOnBoard() + (us * 16) - 8 <= 0){
            Piece p = board.getTile(end.getLocationOnBoard() + (us * 16) - 8).getPiece();
            if (p != null && p instanceof Pawn && p.color != us && p.moved2 != 0){
                score += com.pessant;
            }
        }

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

        //advancement
        for (Tile i : board.getOccupiedTiles()){
            if (i.getPiece() instanceof Pawn || i.getPiece() instanceof Mage){
                score += com.advancement * ((us * -2) + 1) * Board.getYFromLocation(i.getLocationOnBoard());
            }
        }
        //bongcloud
        for (Tile i : board.getOccupiedTilesOfColor(us)){
            if (i.getPiece().royal){
                score += com.bongcloud * ((us * -2) + 1) * Board.getYFromLocation(i.getLocationOnBoard());
            }
        }
        //finisher
        int foes = 0;
        for (Tile i : board.getOccupiedTilesOfColor(1 - us)){
            foes ++;
        }
        score -= com.finisher * Math.sqrt(foes);
        //royalty
        for (Tile i : board.getOccupiedTilesOfColor(us)){
            if (i.getPiece().royal){
                score += com.royalty;
            }
        }
        for (Tile i : board.getOccupiedTilesOfColor(1 - us)){
            if (i.getPiece().royal){
                score -= com.royalty;
            }
        }
        //control
        boolean controled;
        for (Tile i : board.getTiles()){
            controled = false;
            for (Tile j : board.getOccupiedTilesOfColor(us)){
                if (j.isLegalMove(i.getLocationOnBoard(), board, false)){
                    controled = true;
                }
            }
            if (controled){
                score += com.control;
            }
        }
        for (Tile i : board.getTiles()){
            controled = false;
            for (Tile j : board.getOccupiedTilesOfColor(1 - us)){
                if (j.isLegalMove(i.getLocationOnBoard(), board, false)){
                    controled = true;
                }
            }
            if (controled){
                score -= com.control;
            }
        }
        //center & centralcon
        ArrayList<Tile> center = new ArrayList<>();
        center.add(board.getTile(27));
        center.add(board.getTile(28));
        center.add(board.getTile(35));
        center.add(board.getTile(36));


        for (Tile i : center){
            if (i.getPiece() != null){
                if (i.getPiece().getColor() == us){
                    score += com.center;
                }
                else {
                    score -= com.center;
                }
            }
            controled = false;
            for (Tile j : board.getOccupiedTilesOfColor(us)){
                if (j.isLegalMove(i.getLocationOnBoard(), board, false)){
                    controled = true;
                }

            }
            if (controled){
                score += com.centralCon;
            }
        }

        //defence

        for (Tile i : board.getOccupiedTilesOfColor(us)){
            controled = false;
            for (Tile j : board.getOccupiedTilesOfColor(us)){
                if (i != j && j.isLegalMove(i.getLocationOnBoard(), board, false)){
                    controled = true;
                }
            }
            if (controled){
                score += com.defence;
            }
        }

        // threat, check, and checkmate

        for (Tile i : board.getOccupiedTilesOfColor(1 - us)){
            controled = false;
            for (Tile j : board.getOccupiedTilesOfColor(us)){
                if (i != j && j.isLegalMove(i.getLocationOnBoard(), board, false)){
                    controled = true;
                }
            }
            if (controled){
                score += com.threat * i.getPiece().value;
                if (i.getPiece().royal){
                    score += com.check;
                    boolean escape = false;
                    for (Tile j : board.getTiles()){
                        if (j.isPlayableMove(i.getLocationOnBoard(), board, false) == 1){
                            escape = true;
                        }
                    }
                    if (!escape){
                        score += com.checkmate;
                    }
                }
            }
        }

        //risk
        for (Tile i : board.getOccupiedTilesOfColor(us)){
            controled = false;
            for (Tile j : board.getOccupiedTilesOfColor(1 - us)){
                if (i != j && j.isLegalMove(i.getLocationOnBoard(), board, false)){
                    controled = true;
                }
            }
            if (controled){
                score -= com.risk * i.getPiece().value;
            }
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