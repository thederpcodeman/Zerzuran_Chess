package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

import javax.swing.*;

public class Mage extends Piece {
    public int mana;
    public Mage(int color) {
        super(color);
        value = 5;
        mana = -1;
        name = "Mage";
        fen = "ma";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (mana < 2){
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage.png"));
            } else if (mana == 2) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage1star.png"));
            } else if (mana == 3) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage2star.png"));
            } else if (mana == 4) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage3star.png"));
            } else if (mana == 5) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage4star.png"));
            } else if (mana == 6) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage5star.png"));
            } else if (mana == 7) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage6star.png"));
            } else if (mana == 8) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage7star.png"));
            } else if (mana >= 9) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/bMage8star.png"));
            }
        } else if(color == 1) {
            if (mana < 2){
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage.png"));
            } else if (mana == 2) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage1star.png"));
            } else if (mana == 3) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage2star.png"));
            } else if (mana == 4) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage3star.png"));
            } else if (mana == 5) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage4star.png"));
            } else if (mana == 6) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage5star.png"));
            } else if (mana == 7) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage6star.png"));
            } else if (mana == 8) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage7star.png"));
            } else if (mana >= 9) {
                return(new ImageIcon("Zerzuran_Chess/src/resources/Mage/wMage8star.png"));
            }
        }
        return null;
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;

        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (Math.abs(dy) <= 1 && Math.abs(dx) <= 1){
            return (destination.getPiece() == null);
        }
        if (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null){
            return (false);
        }
        if (dx == 0){
            if (Moves.allClear(getColor(), destination) && (Math.abs(dy) < mana)){
                if (forReal){
                    mana -= Math.abs(dy) + 1;
                }
                return true;
            }
        }else if (dy == 0){
            if (Moves.allClear(getColor(), destination) && (Math.abs(dx) < mana)){
                if (forReal){
                    mana -= Math.abs(dx) + 1;
                }
                return true;
            }
        }
        return false;
    }
}
