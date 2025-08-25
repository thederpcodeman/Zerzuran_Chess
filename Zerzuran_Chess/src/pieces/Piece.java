package Zerzuran_Chess.src.pieces;

import javax.swing.*;

import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;

public abstract class Piece {
    public int moved2;
    private int forwards;
    public int color;
    public boolean royal;
    public boolean wall;
    public int value;
    public boolean bomb;
    public String name;
    public String fen;
    public int damage;

    public Piece(int color) {
        name = "Piece";
        bomb = false;
        value = 0;
        damage = 0;
        royal = false;
        wall = false;
        this.color = color;
        this.moved2 = 0;
        if(getColor() == 0)
        {forwards = 1;}
        else {forwards = -1;}
    }

    public ImageIcon getImageIcon() {
        return null;
    }

    public int getColor() {
        return color;
    }

    public boolean isMovable() {
        return true;
    }

    public boolean canJump() {
        return false;
    }

    public int getForwardDirection()
    {
        return forwards;
    }

    public void setForwardDirection(int value){
        forwards = value;
    }
    public abstract boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal);

}
