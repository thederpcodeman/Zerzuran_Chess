package Zerzuran_Chess.src.pieces;

import Zerzuran_Chess.src.Game.AudioPlayer;
import Zerzuran_Chess.src.Game.Board;
import Zerzuran_Chess.src.Game.Tile;
import Zerzuran_Chess.src.pieces.Assassins.Action_Man;
import Zerzuran_Chess.src.pieces.Assassins.Assassin;
import Zerzuran_Chess.src.pieces.Assassins.Bladedancer;
import Zerzuran_Chess.src.pieces.royals.General;
import Zerzuran_Chess.src.pieces.royals.King;
import Zerzuran_Chess.src.pieces.royals.Tyrant;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AugmentedKnight extends Piece {
    private int augment;
    private int augment2;

    private boolean unlock;
    private boolean unlock2;
    public AugmentedKnight(int color) {
        super(color);
        value = 5;
        name = "Augmented Knight";
        augment = -1;
        augment2 = (-1);
        unlock = true;
        unlock2 = false;
        fen = "ak";
    }

    @Override
    public ImageIcon getImageIcon() {

        ImageIcon large;

        ImageIcon small;
        if(color == 0) {
            large = (new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/bAugKnight.png"));
        } else {
            large = (new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/wAugKnight.png"));
        }


        int w = large.getIconWidth();
        int h = large.getIconHeight();

        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(large.getImage(), 0, 0, null);

        ImageIcon extraLayers;

        if (augment2 == 13){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/AugmentsQueen.png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 8 || augment2 == 8){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/AugmentsBishop.png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 9 || augment2 == 9){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/AugmentsRook.png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (!(augment == 5 || augment2 == 5 || augment == 6 || augment2 == 6 || augment == 7 || augment2 == 8 || augment == 8 || augment2 == 9 || augment == 9 || augment2 == 13)){
            if (augment == 10 || augment2 == 10){
                extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/AugmentsPsus.png");
                g.drawImage(extraLayers.getImage(), 0, 0, null);
            }else {
                extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[2,1].png");
                g.drawImage(extraLayers.getImage(), 0, 0, null);
            }
        }

        if (augment == 0 || augment2 == 0 || augment == 5 || augment2 == 5){
                extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[1,0].png");
                g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 1 || augment2 == 1 || augment == 6 || augment2 == 6){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[2,0].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 2 || augment2 == 2 || augment == 5 || augment2 == 5){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[1,1].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 3 || augment2 == 3 || augment == 6 || augment2 == 6){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[2,2].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 4 || augment2 == 4){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[3,0].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment == 7 || augment2 == 7){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[3,1].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment2 == 11){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[3,2].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }

        if (augment2 == 12){
            extraLayers = new ImageIcon("Zerzuran_Chess/src/resources/AugKnight/Augments[4,3].png");
            g.drawImage(extraLayers.getImage(), 0, 0, null);
        }






        return(new ImageIcon(combined));
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));


        if (!Moves.allClear(color, destination)){
            return false;
        }
        if (Moves.pegasusMove(x, y, dx, dy, board)){
            if (!(augment == 5 || augment2 == 5 || augment == 6 || augment2 == 6 || augment == 7 || augment2 == 8 || augment == 8 || augment2 == 9 || augment == 9 || augment2 == 13)){
                if (augment == 10 || augment2 == 10 || Moves.knightMove(dx, dy)){
                    return true;
                }
            }
        }
        if (((Math.abs(dx) == 1) && (Math.abs(dy) == 0)) || ((Math.abs(dx) == 0) && (Math.abs(dy) == 1))){
            if (augment == 0 || augment2 == 0 || augment == 5 || augment2 == 5){
                return true;
            }
        }
        if (((Math.abs(dx) == 3) && (Math.abs(dy) == 0)) || ((Math.abs(dx) == 0) && (Math.abs(dy) == 3))){
            if (augment == 4 || augment2 == 4){
                return true;
            }
        }
        if (((Math.abs(dx) == 2) && (Math.abs(dy) == 0)) || ((Math.abs(dx) == 0) && (Math.abs(dy) == 2))){
            if (augment == 1 || augment2 == 1 || augment == 6 || augment2 == 6){
                return true;
            }
        }
        if (((Math.abs(dx) == 1) && (Math.abs(dy) == 1))){
            if (augment == 2 || augment2 == 2 || augment == 5 || augment2 == 5){
                return true;
            }
        }
        if (((Math.abs(dx) == 2) && (Math.abs(dy) == 2))){
            if (augment == 3 || augment2 == 3 || augment == 6 || augment2 == 6){
                return true;
            }
        }
        if (Moves.camelMove(dx, dy)){
            if (augment == 7 || augment2 == 7){
                return true;
            }
        }
        if (Moves.bullMove(dx, dy)){
            if (augment2 == 11){
                return true;
            }
        }
        if (((Math.abs(dx) == 4) && (Math.abs(dy) == 3)) || ((Math.abs(dx) == 3) && (Math.abs(dy) == 4))){
            if (augment2 == 12){
                return true;
            }
        }
        if (Moves.bishopMove(x, y, dx, dy, board)){
            if (augment == 8 || augment2 == 8){
                return true;
            }
        }
        if (Moves.rookMove(x, y, dx, dy, board)){
            if (augment == 9 || augment2 == 9){
                return true;
            }
        }
        if (Moves.rookMove(x, y, dx, dy, board) || Moves.bishopMove(x, y, dx, dy, board)){
            if (augment2 == 13){
                if (forReal){
                    augment = -1;
                    augment2 = -1;
                    unlock = false;
                    unlock2 = false;
                }
                return true;
            }
        }
        return false;
    }
    public void setAugment(int newAugment){
        augment = newAugment;
    }
    public void setAugment2(int newAugment){
        augment2 = newAugment;
    }
    public void upAugment(Tile self){
        if (!unlock){
            return;
        }
        int newAugment;
        int otherAugment;
        double rand = (Math.random() * 4.8);
        if (rand < 1){
            newAugment = 0;
        }else if (rand < 2){
            newAugment = 1;
        }else if (rand < 3){
            newAugment = 2;
        }else if (rand < 4){
            newAugment = 3;
        }else if (rand < 4.2){
            newAugment = 4;
        }else if (rand < 4.4){
            newAugment = 5;
        }else if (rand < 4.55){
            newAugment = 6;
        }else if (rand < 4.65){
            newAugment = 7;
        }else if (rand < 4.73){
            newAugment = 8;
        }else if (rand < 4.78){
            newAugment = 9;
        }else if (rand < 4.8){
            newAugment = 10;
        }else{
            upAugment(self);
            return;
        }
        rand = (Math.random() * 4.8);
        if (rand < 1){
            otherAugment = 0;
        }else if (rand < 2){
            otherAugment = 1;
        }else if (rand < 3){
            otherAugment = 2;
        }else if (rand < 4){
            otherAugment = 3;
        }else if (rand < 4.2){
            otherAugment = 4;
        }else if (rand < 4.4){
            otherAugment = 5;
        }else if (rand < 4.55){
            otherAugment = 6;
        }else if (rand < 4.65){
            otherAugment = 7;
        }else if (rand < 4.73){
            otherAugment = 8;
        }else if (rand < 4.78){
            otherAugment = 9;
        }else if (rand < 4.8){
            otherAugment = 10;
        }else{
            upAugment(self);
            return;
        }
        int input;
        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.add(option(newAugment, false));
        if (newAugment != otherAugment){
            possibilities.add(option(otherAugment, false));
        }
        possibilities.add(option(-1, false));



        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(possibilities.toArray());
        input = JOptionPane.showConfirmDialog(null, comboBox, "Choose an augment", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();
            if (s == "1 space orthogonally") {
                augment = 0;
            } else if (s == "2 spaces orthogonally") {
                augment = 1;
            } else if (s == "1 space diagonally") {
                augment = 2;
            } else if (s == "2 spaces diagonally") {
                augment = 3;
            } else if (s == "3 spaces orthogonally") {
                augment = 4;
            } else if (s == "Become human") {
                augment = 5;
            } else if (s == "2 spaces in any direction, replaces knight move") {
                augment = 6;
            } else if (s == "Become camel") {
                augment = 7;
            } else if (s == "Become bishop") {
                augment = 8;
            } else if (s == "Become rook") {
                augment = 9;
            } else if (s == "Become pegasus") {
                augment = 10;
            }else{
                augment = -1;
            }
        }
        self.setPiece(self.getPiece());
    }
    public void upAugment2(Tile self){
        if (!unlock2){
            return;
        }
        int newAugment;
        double rand = (Math.random() * 10.05);
        if (rand < 1){
            newAugment = 0;
        }else if (rand < 2){
            newAugment = 1;
        }else if (rand < 3){
            newAugment = 2;
        }else if (rand < 4){
            newAugment = 3;
        }else if (rand < 5){
            newAugment = 4;
        }else if (rand < 6){
            newAugment = 5;
        }else if (rand < 7){
            newAugment = 6;
        }else if (rand < 7.5){
            newAugment = 7;
        }else if (rand < 8){
            newAugment = 8;
        }else if (rand < 9){
            newAugment = 9;
        }else if (rand < 9.5){
            newAugment = 10;
        }else if (rand < 9.75){
            newAugment = 11;
        }else if (rand < 10){
            newAugment = 12;
        }else if (rand < 10.05){
            newAugment = 13;
        }else{
            upAugment2(self);
            return;
        }
        int input;

        AudioPlayer.play("Zerzuran_Chess/src/resources/audio/promote.wav");
        ArrayList<String> possibilities = new ArrayList<String>();

        possibilities.add("yes");
        possibilities.add("no");

        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(possibilities.toArray());
        input = JOptionPane.showConfirmDialog(null, comboBox, "[ " + option(newAugment, true) + " ] Do you accept this Power?", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();
            if (s == "yes") {
                augment2 = newAugment;
            } else if (s == "no") {
                augment2 = -1;
            }else{
                augment = -1;
            }
        }
        self.setPiece(self.getPiece());
    }

    public String option(int op, boolean two){
        if (op == 0){
            return "1 space orthogonally";
        }else if (op == 1){
            return "2 spaces orthogonally";
        }else if (op == 2){
            return "1 space diagonally";
        }else if (op == 3){
            return "2 spaces diagonally";
        }else if (op == 4){
            return "3 spaces orthogonally";
        }else if (op == 5){
            return "Become human";
        }else if (op == 6){
            return "2 spaces in any direction, replaces knight move";
        }else if (op == 7){
            if (two){
                return "Camel move";
            }
            return "Become camel";
        }else if (op == 8){
            return "Become bishop";
        }else if (op == 9){
            return "Become rook";
        }else if (op == 10){
            return "Become pegasus";
        }else if (op == 11){
            return "Bull move";
        }else if (op == 12){
            return "Falcon's [4,3] move";
        }else if (op == 13){
            return "Become queen, at a price...";
        }else if (op == -1){
            return "Deactivate Augments";
        }else{
            return "???";
        }
    }
    public void justMoved(int newY, Tile self){
        if (((newY == 0) && getForwardDirection() == -1) || ((newY == 7) && getForwardDirection() == 1)){
            unlock = true;
            unlock2 = true;

        }
        upAugment(self);
        upAugment2(self);
    }
}
