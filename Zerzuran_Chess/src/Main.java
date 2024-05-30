package Zerzuran_Chess.src;

import Zerzuran_Chess.src.Game.ChessGame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int height = (int)size.getHeight();

        JFrame frame = new ChessGame(height-200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wKnight.png"));
        frame.pack();
        frame.setTitle("Chesscapades");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
