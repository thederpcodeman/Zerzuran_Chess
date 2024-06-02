package Zerzuran_Chess.src.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HelpAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Chesscapades Help Menu");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(430, 100);

        JPanel panel = new JPanel();

        frame.add(panel);

        JLabel lbl = new JLabel("");
        lbl.setVisible(true);
        panel.add(lbl);

        JLabel lbl2 = new JLabel("");
        lbl2.setVisible(true);
        panel.add(lbl2);

        JLabel lbl3 = new JLabel("");
        lbl3.setVisible(true);
        panel.add(lbl3);

        JLabel lbl4 = new JLabel("");
        lbl4.setVisible(true);
        panel.add(lbl4);

        JLabel lbl5 = new JLabel("");
        lbl5.setVisible(true);
        panel.add(lbl5);

        String[] choices = {"Basic Rules", "Piece Specific Rules"};

        final JComboBox<String> cb = new JComboBox<String>(choices);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Zerzuran_Chess/src/resources/wKnight.png"));
        cb.setVisible(true);
        panel.add(cb);

        JButton btn = new JButton("OK");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(cb.getSelectedItem().toString(), "Basic Rules")) {
                    lbl.setText("Each player takes turns moving 1 piece, to another square,");
                    lbl2.setText("Royal pieces are highlighted in gold/purple.");
                    lbl3.setText("Capture all the opponents Royal pieces to win.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Basic Rules");
                    cb.addItem("Piece Specific Rules");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Piece Specific Rules")) {
                    lbl.setText("Each Piece has their own movement. ");
                    lbl2.setText("Some have additional rules as well.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Pawn");
                    cb.addItem("Knight");
                    cb.addItem("Rook");
                    cb.addItem("Bishop");
                    cb.addItem("Queen");
                    cb.addItem("King");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "OK")) {
                    lbl.setText("");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Basic Rules");
                    cb.addItem("Piece Specific Rules");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Pawn")) {
                    lbl.setText("Can move but not capture 1 space forwards.");
                    lbl2.setText("Can capture 1 space diagonally forwards.");
                    lbl3.setText("Can move but not capture 2 spaces forwards, ");
                    lbl4.setText("but only on it's first move, and not if too far forwards. ");
                    lbl5.setText("Promotes randomly.");
                    cb.removeAllItems();
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "King")) {
                    lbl.setText("Can move 1 space orthogonally or diagonally. ");
                    lbl2.setText("Can \"castle\"? ");
                    lbl3.setText("Is Royal.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("Castling");
                    cb.addItem("Royalty");
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Knight")) {
                    lbl.setText("Moves 2 spaces in a dimension, ");
                    lbl2.setText("and 1 in the other. ");
                    lbl3.setText("Jumps over pieces. ");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Rook")) {
                    lbl.setText("Slides orthogonally any number of spaces.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Bishop")) {
                    lbl.setText("Slides diagonally any number of spaces.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Queen")) {
                    lbl.setText("Moves like a Bishop or a Rook.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("Bishop");
                    cb.addItem("Rook");
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Castling")) {
                    lbl.setText("...");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Royalty")) {
                    lbl.setText("A player louses when all of their Royal ");
                    lbl2.setText("pieces are captured, a move will be ");
                    lbl3.setText("highlighted in red if it would ");
                    lbl4.setText("put a Royal piece in danger.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                }
            }
        });
        panel.add(btn);


    }
}