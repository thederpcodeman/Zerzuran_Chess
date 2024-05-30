package Zerzuran_Chess.src.Game;

import Zerzuran_Chess.src.pieces.*;
import Zerzuran_Chess.src.pieces.Assassins.Action_Man;
import Zerzuran_Chess.src.pieces.Assassins.Assassin;
import Zerzuran_Chess.src.pieces.Assassins.Bladedancer;
import Zerzuran_Chess.src.pieces.Unique.CheckerButNot;
import Zerzuran_Chess.src.pieces.Unique.StepPawn;
import Zerzuran_Chess.src.pieces.Unique.shortRook;
import Zerzuran_Chess.src.pieces.pawns.*;
import Zerzuran_Chess.src.pieces.royals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    Board chessBoard;
    Tile selectedTile;
    public static int turn;
    public static boolean wBackWall;
    static final Color highlightedColor = new Color(0, 100, 200);
    static final Color selfColor = new Color(55, 160, 80);
    static final Color dangerColor = new Color(179, 0, 27);

    static final Color highlightedColor2 = new Color(48, 134, 215);
    static final Color allyTan = new Color(215, 255, 222);
    static final Color allyRed = new Color(150, 122, 97);

    static final Color rTan = new Color(255, 248, 182);

    static final Color rRed = new Color(235, 142, 57);

    static final Color pTan = new Color(255, 228, 252);

    static final Color pRed = new Color(215, 102, 117);

    static final Color Invicible = new Color(51, 24, 50);

    static final Color InvicibleAlly = new Color(160, 160, 160);

    static final Color IRoyal = new Color(255, 0, 85);

    Action spaceAction;
    Action helpAction;
    Action resetAction;
    Action toggleAction;
    Action aiAction;

    ArrayList<String> fens;

    public static boolean wTotalWar;

    public static boolean bTotalWar;
    public int atomic;
    public int ranged;

    public boolean touchRule;

    public int rng;

    public boolean tLocked;
    public static boolean myst;

    public static boolean re;
    public static boolean recheck;

    public boolean bStab;

    public boolean bTrayal;

    public static boolean ruth;

    public boolean decay;
    public static boolean skatter;

    public static boolean debugToggle;

    public int cooldown;

    public int gravity;

    public int wBonusTurns;

    public int bBonusTurns;

    public ChessGame(int size){
        rng = 10;
        wBackWall = false;
        debugToggle = false;
        Dimension boardSize = new Dimension(size, size);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        spaceAction = new SpaceAction();
        helpAction = new HelpAction();
        resetAction = new ResetAction();
        toggleAction = new ToggleAction();
        aiAction = new AiAction();



        chessBoard = new Board();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i, new BorderLayout(), size / 8);
            chessBoard.add(tile);
        }

        setupPieces();

        fens = new ArrayList<String>();
        fens.add(chessBoard.computeFen(turn));
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), spaceAction);
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("H"), helpAction);
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("R"), resetAction);
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("O"), toggleAction);
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("I"), aiAction);
        chessBoard.getActionMap().put(spaceAction, spaceAction);
        chessBoard.getActionMap().put(helpAction, helpAction);
        chessBoard.getActionMap().put(resetAction, resetAction);
        chessBoard.getActionMap().put(toggleAction, toggleAction);
        chessBoard.getActionMap().put(aiAction, aiAction);
    }

    public void setupPieces() {
        wBackWall = false;
        wTotalWar = false;
        bTotalWar = false;
        wBonusTurns = 0;
        bBonusTurns = 0;
        debugToggle = false;
        selectedTile = null;
        gravity = (((int) (Math.random() * 3) -1));
        if ((int) (Math.random() * 7.0) != 1){
            gravity = 0;
        }
        cooldown = 0;
        decay = ((int) (Math.random() * 8.5) == 1);
        ruth = !((int) (Math.random() * 7.0) == 1);
        re = ((int) (Math.random() * 12) == 1);
        recheck = true;
        bTrayal = ((int) (Math.random() * 6.5) == 1);
        bStab = ((int) (Math.random() * 5.5) == 1);
        touchRule = ((int) (Math.random() * 8.5) == 1);
        tLocked = false;
        myst = ((int) (Math.random() * 15) == 1);
        skatter = false;
        if (!myst){
            skatter = ((int) (Math.random() * 20) == 1);
        }
        if (myst || skatter && !touchRule){
            touchRule = ((int) (Math.random() * 2.5) == 1);
        }
        chessBoard.wRadness = 3;
        chessBoard.bRadness = 3;
        atomic = (int) (Math.random() * 25);
        if (atomic < 21){
            atomic = 0;
        } else {
            atomic -= 20;//
        }
        if ((int) (Math.random() * 6.2) == 1){
            ranged = 1;
        } else {
            ranged = 0;
        }
        for (Tile i : chessBoard.getTiles()){
            i.setBackground(i.getColor());
        }

        AudioPlayer.play("src/resources/audio/startgame.wav");

        Setup.refresh(chessBoard);
        turn = 1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    public void playMove(moveInfo move){
        int location = move.end.getLocationOnBoard();
        Tile start = move.start;
        boolean resp = (move.end.getPiece() != null && re);
        if (start != null && (start.isPlayableMove(location, chessBoard, true) != 0)) {
            if ((move.end.getPiece() != null) && (move.end.getPiece() instanceof Militia)){ // when militias die, the player controling them loses radness
                if (move.end.getPiece().getColor() == 0){
                    Board.bRadness /= 2;
                }else if (move.end.getPiece().getColor() == 1){
                    Board.wRadness /= 2;
                }
            }
            if ((move.end.getPiece() != null && move.end.getPiece() instanceof Tyrant)){ //Killing a tyrant is a huge propaganda victory
                if (start.getPiece().getColor() == 0){
                    Board.bRadness += 3;
                    Board.wRadness -= 3;
                }else if (start.getPiece().getColor() == 1){
                    Board.wRadness += 3;
                    Board.bRadness -= 3;
                }
            }
            if (start.getPiece() instanceof AugmentedKnight){
                ((AugmentedKnight) start.getPiece()).justMoved(Board.getYFromLocation(move.end.getLocationOnBoard()), start);
            }
            //process move
            tLocked = false;
            if ((chessBoard.getTile(location).getPiece() != null) && (atomic > 0)){
                boolean ks = false;
                boolean ps = false;
                if ((atomic - 1) / 2 == 0){
                    ks = true;
                }
                if (atomic % 2 == 1){
                    ps = true;
                }
                nuke(location, ks, ps);
                if (ranged == 0  && !(start.getPiece() != null && (start.getPiece() instanceof Mage || start.getPiece() instanceof Archmage) && move.end.getPiece() != null)){
                    obliterate(start.getLocationOnBoard(), ks, ps);
                }

            } else if ((chessBoard.getTile(location).getPiece() != null) && (chessBoard.getTile(location).getPiece().bomb) ) {
                nuke(location, false, true);
                if (ranged == 0  && !(start.getPiece() != null && (start.getPiece() instanceof Mage || start.getPiece() instanceof Archmage) && move.end.getPiece() != null)){
                    obliterate(start.getLocationOnBoard(), false, true);
                }
            }

            if (bStab && start.getPiece() != null){
                if (location >= 8){
                    Tile t = chessBoard.getTile(location - 8);
                    if (t.getPiece() != null && t.getPiece().getColor() != start.getPiece().getColor() && t.getPiece().getForwardDirection() == -1){
                        obliterate(location - 8, false, true);
                    }
                }if (location < 56){
                    Tile t = chessBoard.getTile(location + 8);
                    if (t.getPiece() != null && t.getPiece().getColor() != start.getPiece().getColor() && t.getPiece().getForwardDirection() == 1){
                        obliterate(location + 8, false, true);
                    }
                }
            }

            if (((ranged == 0 && !(start.getPiece() instanceof Mage || start.getPiece() instanceof Archmage)) || (move.end.getPiece() == null))){
                move.end.setPiece(start.getPiece());
                start.setPiece(null);
                if (bTrayal && move.end.getPiece() != null && !move.end.getPiece().royal && (int) (Math.random() * 5) == 1){
                    move.end.getPiece().color = (move.end.getPiece().color + 1) % 2;
                    move.end.getPiece().setForwardDirection(move.end.getPiece().getForwardDirection() * -1);
                    move.end.setPiece(move.end.getPiece());
                }
            } else {
                move.end.setPiece(null);
            }

            if (gravity == 1){
                if (move.end.getPiece() != null && Board.getXFromLocation(move.end.getLocationOnBoard()) < 7 && chessBoard.getTile(move.end.getLocationOnBoard() + 1).getPiece() == null){
                    chessBoard.getTile(move.end.getLocationOnBoard() + 1).setPiece(move.end.getPiece());
                    move.end.setPiece(null);
                }
            }else if (gravity == -1){
                if (move.end.getPiece() != null && Board.getXFromLocation(move.end.getLocationOnBoard()) > 0 && chessBoard.getTile(move.end.getLocationOnBoard() - 1).getPiece() == null){
                    chessBoard.getTile(move.end.getLocationOnBoard() - 1).setPiece(move.end.getPiece());
                    move.end.setPiece(null);
                }
            }


            start.setBackground(start.getColor());
            if (!tLocked){
                selectedTile = null;
                for (Tile rTile : chessBoard.getTiles()) {
                    rTile.setBackground(rTile.getColor());
                }
            }



            AudioPlayer.play("src/resources/audio/move-self.wav");
            if (turn == 1){
                if (wBonusTurns > 0){
                    recheck = false;
                    wBonusTurns --;
                }else{
                    recheck = true;
                    turn = 1 - turn;
                }
            }else if (turn == 0){
                if (bBonusTurns > 0){
                    recheck = false;
                    bBonusTurns --;
                }else{
                    recheck = true;
                    turn = 1 - turn;
                }
            }
            for (int check = 0; check < 64; check++){
                Piece checked = chessBoard.getTile(check).getPiece();
                if (checked instanceof Pawn){
                    Pawn p = (Pawn) checked;
                    if (p.moved2 > 0) {p.moved2 -=1;}
                } else if (checked instanceof Mage){
                    ((Mage) checked).mana += 1;
                    chessBoard.getTile(check).setPiece(chessBoard.getTile(check).getPiece());
                } else if (checked instanceof Mage){
                    ((Mage) checked).mana += 1;
                    chessBoard.getTile(check).setPiece(chessBoard.getTile(check).getPiece());
                } else if ((checked instanceof Duck) && (((Duck) checked).bonusMove > 0)){
                    ((Duck) checked).bonusMove -= 1;
                    chessBoard.getTile(check).setPiece(chessBoard.getTile(check).getPiece());
                }
            }



            //compute fen
            String fen = chessBoard.computeFen(turn);
            fens.add(fen);

            if (resp && recheck){
                if (turn == 1){
                    wBonusTurns ++;
                }else{
                    bBonusTurns ++;
                }
            }

            if (decay){
                cooldown += 1;
                if (cooldown == 5){
                    cooldown = 0;
                    Tile[] wh = chessBoard.getOccupiedTilesOfColor(1);
                    Tile sel = wh[((int) (Math.random() * wh.length))];
                    if (sel.getPiece().royal){
                        sel = wh[((int) (Math.random() * wh.length))];
                    }
                    sel.setPiece(null);
                    Tile[] bl = chessBoard.getOccupiedTilesOfColor(0);
                    sel = bl[((int) (Math.random() * bl.length))];
                    if (sel.getPiece().royal){
                        sel = bl[((int) (Math.random() * bl.length))];
                    }
                    sel.setPiece(null);
                }
            }

            int wNumPieces = 0;
            int bNumPieces = 0;
            boolean wKing = false;
            boolean bKing = false;
            for (Tile i : chessBoard.getOccupiedTilesOfColor(0)){
                bNumPieces ++;
                if (i.getPiece().royal){
                    bKing = true;
                }
            }
            if (bNumPieces <= 1){
                bKing = false;
            }
            for (Tile i : chessBoard.getOccupiedTilesOfColor(1)){
                wNumPieces ++;
                if (i.getPiece().royal){
                    wKing = true;
                }
            }
            if (wNumPieces <= 1){
                wKing = false;
            }
            if (wTotalWar){
                if (wNumPieces > 0){
                    wKing = true;
                }
            }
            if (bTotalWar){
                if (bNumPieces > 0){
                    bKing = true;
                }
            }
            if (!wKing){
                if (!bKing){
                    System.out.println("Here's the FEN for the final position!");
                    System.out.println(fens.get(fens.size() - 1));
                    int option;
                    String buttons[] = {"Replay", "Quit"};
                    option = JOptionPane.showOptionDialog(null, "A tie? Too bad. Play again?", "Draw", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
                    if (option == 0) {
                        for (int i = 0; i < 64; i++) {
                            chessBoard.getTile(i).setPiece(null);
                        }
                        fens.clear();
                        setupPieces();
                    } else {
                        System.exit(0);
                    }
                } else {
                    System.out.println("Here's the FEN for the final position!");
                    System.out.println(fens.get(fens.size() - 1));
                    int option;
                    String buttons[] = {"Replay", "Quit"};
                    option = JOptionPane.showOptionDialog(null, "Black wins! Play again?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
                    if (option == 0) {
                        for (int i = 0; i < 64; i++) {
                            chessBoard.getTile(i).setPiece(null);
                        }
                        fens.clear();
                        setupPieces();
                    } else {
                        System.exit(0);
                    }
                }
            } else if (!bKing){
                System.out.println("Here's the FEN for the final position!");
                System.out.println(fens.get(fens.size() - 1));
                int option;
                String buttons[] = {"Replay", "Quit"};
                option = JOptionPane.showOptionDialog(null, "White wins! Play again?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
                if (option == 0) {
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    setupPieces();
                } else {
                    System.exit(0);
                }
            }

            //check for three move repetition
            int priorOccurrences = 0;
            for (String oldFen : fens) {
                if (fen.equals(oldFen)) {
                    priorOccurrences++;
                }
            }
            if (priorOccurrences >= 3) {
                stalemate();
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tile[] tiles = chessBoard.getTiles();
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        if (debugToggle) {
            debugClick(tile);
        }else{
            if (tile.getPiece() == null){
                setTitle("Chesscapades");
                setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wKnight.png"));
            }else{
                String newname;
                if (myst) {
                    newname = "Unknown Piece";
                    if (tile.getPiece().royal) {
                        newname += " [Royal]";
                    }
                    if (tile.getPiece().color == 1) {
                        setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wUnknown.png"));
                    } else {
                        setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/bUnknown.png"));
                    }
                }else if (skatter){
                    newname = "Zerzuran Chess";
                    setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wKnight.png"));
                }else{
                    newname = tile.getPiece().name;
                    setIconImage(tile.getPiece().getImageIcon().getImage());
                    if (tile.getPiece() instanceof Mage){
                        newname += " (mana: " + (((Mage) tile.getPiece()).mana - 1) + ")";
                    }
                    if (tile.getPiece().royal){
                        newname += " [Royal]";
                    }
                    if (tile.getPiece().wall){
                        newname += " [Protected]";
                    }
                }
                setTitle(newname);
            }







            if (!tLocked){
                for (Tile rTile : tiles) {
                    rTile.setBackground(rTile.getColor());
                }
                if (selectedTile != null && (!selectedTile.isLegalMove(tile.getLocationOnBoard(), chessBoard, false))){
                    selectedTile = null;
                }
            }
            Piece piece = tile.getPiece();
            if (piece != null && !(selectedTile != null && selectedTile.isLegalMove(tile.getLocationOnBoard(), chessBoard, false))) {
                if (piece.getColor() == turn && !tLocked) {
                    if (!tLocked){
                        selectedTile = tile;
                    }
                    tile.setBackground(selfColor);
                    Tile[] legalMoves = chessBoard.getTiles();
                    for (Tile legalTile : legalMoves) {
                        if (tile.isPlayableMove(legalTile.getLocationOnBoard(), chessBoard, false) == 1){
                            if (legalTile.getColor() == Tile.tan) {
                                legalTile.setBackground(highlightedColor2);
                            } else {
                                legalTile.setBackground(highlightedColor);
                            }
                            if (touchRule) {
                                tLocked = true;
                            }

                        }else if (tile.isPlayableMove(legalTile.getLocationOnBoard(), chessBoard, false) == 2) {
                            legalTile.setBackground(dangerColor);
                        }else if (tile == legalTile){
                            legalTile.setBackground(selfColor);
                        }else if ((legalTile.getPiece() != null) && (legalTile.getPiece().getColor() == turn)) {
                            if (legalTile.getColor() == Tile.tan){
                                if (legalTile.getPiece().royal){
                                    if (legalTile.getPiece().wall){
                                        legalTile.setBackground(IRoyal);
                                    }else{
                                        legalTile.setBackground(rTan);
                                    }
                                }else if (legalTile.getPiece().wall){
                                    legalTile.setBackground(InvicibleAlly);
                                }else {
                                    legalTile.setBackground(allyTan);
                                }

                            }else {
                                if (legalTile.getPiece().royal){
                                    if (legalTile.getPiece().wall){
                                        legalTile.setBackground(IRoyal);
                                    }else{
                                        legalTile.setBackground(rRed);
                                    }
                                }else if (legalTile.getPiece().wall){
                                    legalTile.setBackground(InvicibleAlly);
                                }else{
                                    legalTile.setBackground(allyRed);
                                }
                            }
                        }else if ((legalTile.getPiece() != null) && (legalTile.getPiece().getColor() != turn)){
                            if (legalTile.getPiece().royal) {
                                if (legalTile.getColor() == Tile.tan) {
                                    legalTile.setBackground(pTan);
                                } else {
                                    legalTile.setBackground(pRed);
                                }
                            }else if (legalTile.getPiece().wall) {
                                if (legalTile.getPiece().color == turn){
                                    legalTile.setBackground(InvicibleAlly);
                                } else {
                                    legalTile.setBackground(Invicible);
                                }
                            }
                        }

                    }
                    return;
                }
            }
            playMove(new moveInfo(selectedTile, tile, chessBoard));
        }

    }
    public void obliterate(int square, boolean kingSlayer, boolean pawnSlayer){
        if ( (chessBoard.getTile(square) != null) && (chessBoard.getTile(square).getPiece() != null) && (kingSlayer || !(chessBoard.getTile(square).getPiece().royal)) && (pawnSlayer || !(chessBoard.getTile(square).getPiece() instanceof Pawn))) {
            chessBoard.getTile(square).setPiece(null);
        }
    }
    public void nuke(int square, boolean kingSlayer, boolean pawnSlayer) {
        int y = Board.getYFromLocation(square);
        int x = Board.getXFromLocation(square);
        int Yoff = -1;
        while (Yoff <= 1){
            int Xoff = -1;
            while (Xoff <= 1){
                if ((y + Yoff >= 0) && (y + Yoff < 8) && (x + Xoff >= 0) && (x + Xoff < 8)){
                    obliterate(Board.getLocationFromCords(x + Xoff,y + Yoff ), kingSlayer, pawnSlayer);
                }
                Xoff ++;
            }
            Yoff ++;
        }
    }

    void checkmate(int win) {
        AudioPlayer.play("src/resources/audio/win.wav");
        System.out.println("Checkmate! Here's the FEN for the final position!");
        System.out.println(fens.get(fens.size() - 1));
        int option;
        String buttons[] = {"Replay", "Quit"};
        if (win == 0) {
            option = JOptionPane.showOptionDialog(null, "Black wins! Play again or quit?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        } else {
            option = JOptionPane.showOptionDialog(null, "White wins! Play again or quit?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        }
        if (option == 0) {
            for (int i = 0; i < 64; i++) {
                chessBoard.getTile(i).setPiece(null);
            }
            fens.clear();
            setupPieces();
        } else {
            System.exit(0);
        }
    }

    void stalemate() {
        AudioPlayer.play("src/resources/audio/stalemate.wav");
        System.out.println("Stalemate! Here's the FEN for the final position!");
        System.out.println(fens.get(fens.size() - 1));
        int option;
        String buttons[] = {"Replay", "Quit"};
        option = JOptionPane.showOptionDialog(null, "Stalemate! Play again or quit?", "Stalemate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        if (option == 0) {
            for (int i = 0; i < 64; i++) {
                chessBoard.getTile(i).setPiece(null);
            }
            fens.clear();
            setupPieces();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    private void playGoodishMove(){
        ArrayList<moveInfo> choices = new ArrayList<moveInfo>();
        for (Tile myDude : chessBoard.getOccupiedTilesOfColor(turn)){
            for (Tile place : myDude.getLegalMoves(chessBoard)){
                choices.add(new moveInfo(myDude, place, chessBoard));
            }
        }
        if (choices.size() == 0){
            checkmate(turn -1);
        }
        double moveMin = -999999;
        int selected = 0;
        for (int mov = 0; mov < choices.size(); mov++){
            double sc = choices.get(mov).score(rng);
            if (sc > moveMin){
                moveMin = sc;
                selected = mov;
            }
        }
        playMove(choices.get(selected));
    }

    public class SpaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            playGoodishMove();
        }
    }
    public class ResetAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input;
            AudioPlayer.play("src/resources/audio/promote.wav");
            ArrayList<String> possibilities = new ArrayList<String>();

            possibilities.add("Simple Reset");
            possibilities.add("Full Query");
            possibilities.add("Disable All Rules");
            possibilities.add("Randomize All Rules");
            possibilities.add("Riffle");
            possibilities.add("Mystery");
            possibilities.add("Skatter");
            possibilities.add("Atomic");
            possibilities.add("Gravity");
            possibilities.add("Decay");
            possibilities.add("Backstab");
            possibilities.add("Betrayal");
            possibilities.add("Formal");
            possibilities.add("Friendly Fire");
            possibilities.add("Fast and Furious");
            possibilities.add("Exit Refresh Menu");//
            possibilities.add("Normal Chess");//
            possibilities.add("Chess 960");//
            possibilities.add("Two Thirds Chess");//
            possibilities.add("Revolt Chess");//
            possibilities.add("2/3 Revolt Chess");//
            possibilities.add("Grand Chess");//
            possibilities.add("Power Chess");//
            possibilities.add("Super Chess");//
            possibilities.add("Fear Chess");//
            possibilities.add("Cavalry Chess");//
            possibilities.add("Step Into Darkness Chess");//
            possibilities.add("End Game Chess");//
            possibilities.add("Randomized End Game Chess");//
            possibilities.add("Upside-Down Chess");//
            possibilities.add("Maharajah and the Sepoys Chess");//
            possibilities.add("Hoard Chess");
            possibilities.add("Chess vs Checkers");
            possibilities.add("Weak!");
            possibilities.add("Randomized Hoard Chess");

            JPanel jPanel = new JPanel(new GridBagLayout());
            JComboBox comboBox = new JComboBox(possibilities.toArray());
            ArrayList<String> als = new ArrayList<String>();
            als.add("OK");
            JComboBox secondary =  new JComboBox(als.toArray());
            input = JOptionPane.showConfirmDialog(null, comboBox, "Refresh menu: ", JOptionPane.DEFAULT_OPTION);
            jPanel.add(comboBox);

            if(input == JOptionPane.OK_OPTION) {
                String s = (String) comboBox.getSelectedItem();
                if (Objects.equals(s, "Exit Refresh Menu")) {
                } else if (Objects.equals(s, "Fast and Furious")) {
                    if (re){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Fast and Furious Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        re = false;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Fast and Furious Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        re = true;
                    }
                } else if (Objects.equals(s, "Friendly Fire")) {
                    if (ruth){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Friendly Fire Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        ruth = false;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Friendly Fire Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        ruth = true;
                    }
                } else if (Objects.equals(s, "Formal")) {
                    if (touchRule){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Formal Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        touchRule = false;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Formal Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        touchRule = true;
                    }
                } else if (Objects.equals(s, "Betrayal")) {
                    if (bTrayal){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Betrayal Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        bTrayal = false;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Betrayal Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        bTrayal = true;
                    }
                } else if (Objects.equals(s, "Backstab")) {
                    if (bStab){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Backstab Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        bStab = false;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Backstab Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        bStab = true;
                    }
                } else if (Objects.equals(s, "Decay")) {
                    if (decay){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Decay Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        decay = false;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Decay Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        decay = true;
                    }
                } else if (Objects.equals(s, "Gravity")) {
                    if (gravity == 1){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Gravity set to Left", JOptionPane.DEFAULT_OPTION);
                        gravity = -1;
                    }else if (gravity == -1){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Gravity chess disabled", JOptionPane.DEFAULT_OPTION);
                        gravity = 0;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Gravity Chess Enabled (set to right)", JOptionPane.DEFAULT_OPTION);
                        gravity = 1;
                    }
                } else if (Objects.equals(s, "Atomic")) { // 1: both, 2: just kings, 3: just pawns, 4: nither
                    if (atomic == 1){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Now cannot kill pawns", JOptionPane.DEFAULT_OPTION);
                        atomic = 2;
                    }else if (atomic == 2){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Now cannot kill Royal pieces", JOptionPane.DEFAULT_OPTION);
                        atomic = 3;
                    }else if (atomic == 3){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Now cannot kill Pawns or Royal pieces", JOptionPane.DEFAULT_OPTION);
                        atomic = 4;
                    }else if (atomic == 4){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Atomic chess disabled", JOptionPane.DEFAULT_OPTION);
                        atomic = 0;
                    }else if (atomic == 0){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Atomic chess enabled, can kill Pawns and Royals", JOptionPane.DEFAULT_OPTION);
                        atomic = 1;
                    }
                } else if (Objects.equals(s, "Skatter")) {
                    if (skatter){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Skatter Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        skatter = false;
                        for (Tile t : chessBoard.getOccupiedTiles()){
                            t.setPiece(t.getPiece());
                        }
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Skatter Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        skatter = true;
                        for (Tile t : chessBoard.getOccupiedTiles()){
                            t.setPiece(t.getPiece());
                        }
                    }
                } else if (Objects.equals(s, "Mystery")) {
                    if (myst){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Mystery Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        myst = false;
                        for (Tile t : chessBoard.getOccupiedTiles()){
                            t.setPiece(t.getPiece());
                        }
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Mystery Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        myst = true;
                        for (Tile t : chessBoard.getOccupiedTiles()){
                            t.setPiece(t.getPiece());
                        }
                    }
                } else if (Objects.equals(s, "Riffle")) {
                    if (ranged == 1){
                        input = JOptionPane.showConfirmDialog(null, secondary, "Riffle Chess Disabled", JOptionPane.DEFAULT_OPTION);
                        ranged = 0;
                    }else{
                        input = JOptionPane.showConfirmDialog(null, secondary, "Riffle Chess Enabled", JOptionPane.DEFAULT_OPTION);
                        ranged = 1;
                    }
                } else if (Objects.equals(s, "Disable All Rules")) {
                    if (ranged == 1) {
                        ranged = 0;
                    }
                    if (myst) {
                        myst = false;
                        for (Tile t : chessBoard.getOccupiedTiles()) {
                            t.setPiece(t.getPiece());
                        }
                    }
                    if (skatter) {
                        skatter = false;
                        for (Tile t : chessBoard.getOccupiedTiles()) {
                            t.setPiece(t.getPiece());
                        }
                    }
                    if (atomic > 0) {
                        atomic = 0;
                    }
                    if (gravity != 0) {
                        gravity = 0;
                    }
                    if (decay) {
                        decay = false;
                    }
                    if (bStab) {
                        bStab = false;
                    }
                    if (bTrayal) {
                        bTrayal = false;
                    }
                    if (touchRule) {
                        touchRule = false;
                    }
                    if (!ruth) {
                        ruth = true;
                    }
                    if (re) {
                        re = false;
                    }
                    for (int i = 0; i < 64; i++) {
                        if (chessBoard.getTile(i).getPiece() != null && chessBoard.getTile(i).getPiece().bomb) {
                            chessBoard.getTile(i).getPiece().bomb = false;
                        }
                    }
                    for (int i = 0; i < 64; i++) {
                        if (chessBoard.getTile(i).getPiece() != null && chessBoard.getTile(i).getPiece().wall && !(chessBoard.getTile(i).getPiece() instanceof Wall || chessBoard.getTile(i).getPiece() instanceof Immortal)) {
                            chessBoard.getTile(i).getPiece().wall = false;
                        }
                    }
                } else if (Objects.equals(s, "Randomize All Rules")) {
                    wBonusTurns = 0;
                    bBonusTurns = 0;
                    debugToggle = false;
                    selectedTile = null;
                    gravity = (((int) (Math.random() * 3) -1));
                    if ((int) (Math.random() * 7.0) != 1){
                        gravity = 0;
                    }
                    cooldown = 0;
                    decay = ((int) (Math.random() * 8.5) == 1);
                    ruth = !((int) (Math.random() * 7.0) == 1);
                    re = ((int) (Math.random() * 12) == 1);
                    recheck = true;
                    bTrayal = ((int) (Math.random() * 6.5) == 1);
                    bStab = ((int) (Math.random() * 5.5) == 1);
                    touchRule = ((int) (Math.random() * 8.5) == 1);
                    tLocked = false;
                    myst = ((int) (Math.random() * 15) == 1);
                    skatter = false;
                    if (!myst){
                        skatter = ((int) (Math.random() * 20) == 1);
                    }
                    if (myst || skatter && !touchRule){
                        touchRule = ((int) (Math.random() * 2.5) == 1);
                    }
                    atomic = (int) (Math.random() * 25);
                    if (atomic < 21){
                        atomic = 0;
                    } else {
                        atomic -= 20;//
                    }
                    if ((int) (Math.random() * 6.2) == 1){
                        ranged = 1;
                    } else {
                        ranged = 0;
                    }
                    for (Tile i : chessBoard.getTiles()){
                        i.setBackground(i.getColor());
                    }
                    for (int i = 0; i < 64; i++) {
                        if (chessBoard.getTile(i).getPiece() != null && chessBoard.getTile(i).getPiece().bomb) {
                            chessBoard.getTile(i).getPiece().bomb = false;
                        }
                    }
                    for (int i = 0; i < 64; i++) {
                        if (chessBoard.getTile(i).getPiece() != null && chessBoard.getTile(i).getPiece().wall && !(chessBoard.getTile(i).getPiece() instanceof Wall || chessBoard.getTile(i).getPiece() instanceof Immortal)) {
                            chessBoard.getTile(i).getPiece().wall = false;
                        }
                    }
                    Setup.mods(chessBoard);
                }else if (Objects.equals(s, "Simple Reset")) {
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    setupPieces();
                } else if (Objects.equals(s, "Full Query")) {
                    StringBuilder info = new StringBuilder();
                    if (ranged == 1){
                        info.append("Riffle Chess, ");
                    }
                    if (myst){
                        info.append("Mystery Chess, ");
                    }
                    if (skatter){
                        info.append("Skatter Chess, ");
                    }
                    if (atomic == 1){
                        info.append("Atomic Chess (1), ");
                    }else if (atomic == 2){
                        info.append("Atomic Chess (2), ");
                    }else if (atomic == 3){
                        info.append("Atomic Chess (3), ");
                    }else if (atomic == 4){
                        info.append("Atomic Chess (4), ");
                    }
                    if (gravity == 1){
                        info.append("Gravity chess (Right), ");
                    }else if (gravity == -1){
                        info.append("Gravity Chess (Left), ");
                    }
                    if (decay){
                        info.append("Decay Chess, ");
                    }
                    if (bStab){
                        info.append("Backstab Chess, ");
                    }
                    if (bTrayal){
                        info.append("Betrayal Chess, ");
                    }
                    if (touchRule){
                        info.append("Formal Chess, ");
                    }
                    if (!ruth){
                        info.append("Friendly Fire Chess, ");
                    }
                    if (re){
                        info.append("Fast & Furious Chess, ");
                    }
                    for (int i = 0; i < 64; i++){
                        if (chessBoard.getTile(i).getPiece() != null && chessBoard.getTile(i).getPiece().bomb){
                            info.append("Secret bomber at tile ").append(i).append(", ");
                        }
                    }

                    AudioPlayer.play("src/resources/audio/promote.wav");

                    final String showToUser = info.toString();


                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            JFrame frame = new JFrame("Gamemodes in play...");
                            frame.setVisible(true);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(500, 75);
                            frame.setLocation(430, 100);
                            JPanel panel = new JPanel();

                            frame.add(panel);

                            JLabel lbl = new JLabel(showToUser);
                            lbl.setVisible(true);
                            panel.add(lbl);
                            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wKnight.png"));
                        }
                    } );


                } else if (Objects.equals(s, "Chess 960")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.rChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Two Thirds Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.twoThirdsChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Revolt Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.revoltChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "2/3 Revolt Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.twoThirdsRevoltChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Grand Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.grandChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Power Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.powerChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Super Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.superChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Fear Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.fearChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Cavalry Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.cavalryChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Step Into Darkness Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.stepChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "End Game Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.egChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Randomized End Game Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.regChess(chessBoard);
                    Setup.fairness(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Normal Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.normalChess(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Upside-Down Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.upSideDownChess(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Maharajah and the Sepoys Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.MaharajahChess(chessBoard);
                    Setup.mods(chessBoard);
                } else if (Objects.equals(s, "Hoard Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.hoardChess(chessBoard);
                } else if (Objects.equals(s, "Chess vs Checkers")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.checkersVChess(chessBoard);
                }
                else if (Objects.equals(s, "Weak!")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.weakChess(chessBoard);
                    Setup.mods(chessBoard);
                }
                else if (Objects.equals(s, "Randomized Hoard Chess")) {
                    setupPieces();
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    Setup.rHoardChess(chessBoard);
                }
            }


        }
    }

    public class ToggleAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            debugToggle = !debugToggle;
        }
    }
    public class AiAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input;

            AudioPlayer.play("src/resources/audio/promote.wav");
            ArrayList<String> possibilities = new ArrayList<String>();

            possibilities.add("Rebecca"); // 0
            possibilities.add("Bob"); // 10
            possibilities.add("Mr. & Mrs. Patel"); // 25
            possibilities.add("Whitney"); // 62
            possibilities.add("Luigi"); // 155
            possibilities.add("John"); // 232
            possibilities.add("Carl"); // 348
            possibilities.add("David"); // 522
            possibilities.add("Doug"); // 783
            possibilities.add("Sarah"); // 1174
            possibilities.add("Timothy"); // 1761
            possibilities.add("Jessie"); // 2641
            possibilities.add("Peter"); // 3961
            possibilities.add("George"); // 5941
            possibilities.add("Cthulhu"); // 8911
            possibilities.add("Jerry from Hypixel Skyblock"); // 13366


            JPanel jPanel = new JPanel(new GridBagLayout());
            JComboBox comboBox = new JComboBox(possibilities.toArray());
            input = JOptionPane.showConfirmDialog(null, comboBox, "Choose an AI, none of them are really good: ", JOptionPane.DEFAULT_OPTION);
            jPanel.add(comboBox);

            if(input == JOptionPane.OK_OPTION) {
                String s = (String) comboBox.getSelectedItem();
                if (Objects.equals(s, "Rebecca")) {
                    rng = 0;
                }else if (Objects.equals(s, "Bob")) {
                    rng = 10;
                }else if (Objects.equals(s, "Mr. & Mrs. Patel")) {
                    rng = 25;
                }else if (Objects.equals(s, "Whitney")) {
                    rng = 62;
                }else if (Objects.equals(s, "Luigi")) {
                    rng = 155;
                }else if (Objects.equals(s, "John")) {
                    rng = 232;
                }else if (Objects.equals(s, "Carl")) {
                    rng = 348;
                }else if (Objects.equals(s, "David")) {
                    rng = 522;
                }else if (Objects.equals(s, "Doug")) {
                    rng = 783;
                }else if (Objects.equals(s, "Sarah")) {
                    rng = 1174;
                }else if (Objects.equals(s, "Timothy")) {
                    rng = 1761;
                }else if (Objects.equals(s, "Jessie")) {
                    rng = 2641;
                }else if (Objects.equals(s, "Peter")) {
                    rng = 3961;
                }else if (Objects.equals(s, "George")) {
                    rng = 5941;
                }else if (Objects.equals(s, "Cthulhu")) {
                    rng = 8911;
                }else if (Objects.equals(s, "Jerry from Hypixel Skyblock")) {
                    rng = 13366;
                }
            }
        }
    }

    public void debugClick(Tile tile){
        int input;

        AudioPlayer.play("src/resources/audio/promote.wav");
        ArrayList<String> possibilities = new ArrayList<String>();

        possibilities.add("Queen");//
        possibilities.add("Rook");//
        possibilities.add("Knight");//
        possibilities.add("Bishop");//
        possibilities.add("King");//
        possibilities.add("Amazon");//
        possibilities.add("Archbishop");//
        possibilities.add("Chancellor");//
        possibilities.add("Camel");//
        possibilities.add("General");//
        possibilities.add("Lion");//
        possibilities.add("Frog");//
        possibilities.add("Elephant");//
        possibilities.add("Bull");//
        possibilities.add("Gryphon");//
        possibilities.add("Pegasus");//
        possibilities.add("Tyrant");//
        possibilities.add("Boat");//
        possibilities.add("Spider");//
        possibilities.add("Assassin1");//
        possibilities.add("Assassin2");//
        possibilities.add("Assassin3");//
        possibilities.add("Manticore");//
        possibilities.add("Greatwyrm");//
        possibilities.add("Mage");//
        possibilities.add("Immortal");//
        possibilities.add("Spearman");//
        possibilities.add("Falcon");//
        possibilities.add("Keegan");//
        possibilities.add("AugKnight");//
        possibilities.add("Flag bearer");//
        possibilities.add("Duck");//
        possibilities.add("CheckerButNot");//
        possibilities.add("Empress");//
        possibilities.add("Theocrat");//
        possibilities.add("Archmage");//
        possibilities.add("Berserker");//
        possibilities.add("Archer");//
        possibilities.add("Buffalo");//
        possibilities.add("Crusader");//
        possibilities.add("Templar");//
        possibilities.add("Quetzacoatl");//
        possibilities.add("Viking");//
        possibilities.add("Wolf");//
        possibilities.add("Step Into Darkness Pawn");//
        possibilities.add("Short Rook (WIP: functions but uses rook image)");//
        possibilities.add("Pawn");//
        possibilities.add("Soldier");//
        possibilities.add("Pikeman");//
        possibilities.add("Prince");//
        possibilities.add("Checker");//
        possibilities.add("Wall");//
        possibilities.add("Barolina Pawn");//
        if (tile.getPiece() != null){
            possibilities.add("Turn around");
            possibilities.add("Change Team");
            possibilities.add("+ Royal");
            possibilities.add("- Royal");
            possibilities.add("+ Protected");
            possibilities.add("- Protected");
            possibilities.add("Empty");
        }
        possibilities.add("Exit Debug Mode");
        possibilities.add("Do Nothing.");

        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(possibilities.toArray());
        input = JOptionPane.showConfirmDialog(null, comboBox, "Choose a piece to promote to: ", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();
            int c = turn;
            if (tile.getPiece() != null){
                c = tile.getPiece().getColor();
            }
            if (Objects.equals(s, "Queen")) {
                tile.setPiece(new Queen(c));
            } else if (Objects.equals(s, "Empress")) {
                tile.setPiece(new Empress(c));
            } else if (Objects.equals(s, "Theocrat")) {
                tile.setPiece(new Theocrat(c));
            } else if (Objects.equals(s, "Archmage")) {
                tile.setPiece(new Archmage(c));
            } else if (Objects.equals(s, "Berserker")) {
                tile.setPiece(new Berserker(c));
            } else if (Objects.equals(s, "Archer")) {
                tile.setPiece(new Bow(c));
            } else if (Objects.equals(s, "Buffalo")) {
                tile.setPiece(new Buffalo(c));
            } else if (Objects.equals(s, "Crusader")) {
                tile.setPiece(new Crusader(c));
            } else if (Objects.equals(s, "Templar")) {
                tile.setPiece(new Templar(c));
            } else if (Objects.equals(s, "Quetzacoatl")) {
                tile.setPiece(new Quetzacoatl(c));
            } else if (Objects.equals(s, "Viking")) {
                tile.setPiece(new Viking(c));
            } else if (Objects.equals(s, "Wolf")) {
                tile.setPiece(new Wolf(c));
            } else if (Objects.equals(s, "Step Into Darkness Pawn")) {
                tile.setPiece(new StepPawn(c));
            } else if (Objects.equals(s, "Short Rook (WIP: functions but uses rook image)")) {
                tile.setPiece(new shortRook(c));
            } else if (Objects.equals(s, "Pawn")) {
                tile.setPiece(new Pawn(c));
            } else if (Objects.equals(s, "Soldier")) {
                tile.setPiece(new Soldier(c));
            } else if (Objects.equals(s, "Pikeman")) {
                tile.setPiece(new Pikeman(c));
            } else if (Objects.equals(s, "Prince")) {
                tile.setPiece(new Prince(c));
            } else if (Objects.equals(s, "Checker")) {
                tile.setPiece(new Checker(c));
            } else if (Objects.equals(s, "Wall")) {
                tile.setPiece(new Wall(c));
            } else if (Objects.equals(s, "Barolina Pawn")) {
                tile.setPiece(new BarolinaPawn(c));
            } else if (Objects.equals(s, "Rook")) {
                tile.setPiece(new Rook(c));
            } else if (Objects.equals(s, "Knight")) {
                tile.setPiece(new Knight(c));
            } else if (Objects.equals(s, "Bishop")) {
                tile.setPiece(new Bishop(c));
            } else if (Objects.equals(s, "King")) {
                tile.setPiece(new King(c));
            } else if (Objects.equals(s, "Turn around")) {
                tile.getPiece().setForwardDirection(tile.getPiece().getForwardDirection() * -1);
                tile.setPiece(tile.getPiece());
            } else if (Objects.equals(s, "Amazon")) {
                tile.setPiece(new Amazon(c));
            } else if (Objects.equals(s, "Archbishop")) {
                tile.setPiece(new Archbishop(c));
            } else if (Objects.equals(s, "Chancellor")) {
                tile.setPiece(new Chancellor(c));
            } else if (Objects.equals(s, "Camel")) {
                tile.setPiece(new Camel(c));
            } else if (Objects.equals(s, "Falcon")) {
                tile.setPiece(new Falcon(c));
            } else if (Objects.equals(s, "General")) {
                tile.setPiece(new General(c));
            } else if (Objects.equals(s, "Lion")) {
                tile.setPiece(new Lion(c));
            } else if (Objects.equals(s, "Frog")) {
                tile.setPiece(new Frog(c));
            } else if (Objects.equals(s, "Elephant")) {
                tile.setPiece(new Elephant(c));
            } else if (Objects.equals(s, "Bull")) {
                tile.setPiece(new Bull(c));
            } else if (Objects.equals(s, "Gryphon")) {
                tile.setPiece(new Gryphon(c));
            } else if (Objects.equals(s, "Empty")) {
                tile.setPiece(null);
            } else if (Objects.equals(s, "Boat")) {
                tile.setPiece(new Boat(c));
            } else if (Objects.equals(s, "Pegasus")) {
                tile.setPiece(new Pegasus(c));
            } else if (Objects.equals(s, "Tyrant")) {
                tile.setPiece(new Tyrant(c));
            } else if (Objects.equals(s, "Assassin1")) {
                tile.setPiece(new Assassin(c));
            } else if (Objects.equals(s, "Assassin2")) {
                tile.setPiece(new Bladedancer(c));
            } else if (Objects.equals(s, "Assassin3")) {
                tile.setPiece(new Action_Man(c));
            } else if (Objects.equals(s, "Spider")) {
                tile.setPiece(new Spider(c));
            } else if (Objects.equals(s, "Manticore")) {
                tile.setPiece(new Manticore(c));
            } else if (Objects.equals(s, "Greatwyrm")) {
                tile.setPiece(new Greatwyrm(c));
            } else if (Objects.equals(s, "Mage")) {
                tile.setPiece(new Mage(c));
            } else if (Objects.equals(s, "Spearman")) {
                tile.setPiece(new Spearman(c));
            } else if (Objects.equals(s, "Immortal")) {
                tile.setPiece(new Immortal(c));
            } else if (Objects.equals(s, "Keegan")) {
                tile.setPiece(new Keegan(c));
            } else if (Objects.equals(s, "Duck")) {
                tile.setPiece(new Duck(c));
            } else if (Objects.equals(s, "AugKnight")) {
                tile.setPiece(new AugmentedKnight(c));
            } else if (Objects.equals(s, "+ Royal")) {
                tile.getPiece().royal = true;
            } else if (Objects.equals(s, "- Royal")) {
                tile.getPiece().royal = false;
            } else if (Objects.equals(s, "+ Protected")) {
                tile.getPiece().wall = true;
            } else if (Objects.equals(s, "- Protected")) {
                tile.getPiece().wall = false;
            } else if (Objects.equals(s, "Exit Debug Mode")) {
                debugToggle = false;
            } else if (Objects.equals(s, "Change Team")) {
                tile.getPiece().color = 1 - tile.getPiece().getColor();
                tile.setPiece(tile.getPiece());
            } else if (Objects.equals(s, "Flag bearer")) {
                tile.setPiece(new Flag(c));
            }
            else if (Objects.equals(s, "CheckerButNot")) {
                tile.setPiece(new CheckerButNot(c));
            }
        }
    }
}