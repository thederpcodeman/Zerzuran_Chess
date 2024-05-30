package Game;

import pieces.*;
import pieces.Assassins.Assassin;
import pieces.pawns.*;
import pieces.royals.*;

public class Fen {
    public static String getFen(Piece piece) {
        if (piece instanceof King) {
            if (piece.getColor() == 1) {
                return ("K");
            } else {
                return ("k");
            }
        } else if (piece instanceof Queen) {
            if (piece.getColor() == 1) {
                return ("Q");
            } else {
                return ("q");
            }
        } else if (piece instanceof Bishop) {
            if (piece.getColor() == 1) {
                return ("B");
            } else {
                return ("b");
            }
        } else if (piece instanceof Knight) {
            if (piece.getColor() == 1) {
                return ("KN");
            } else {
                return ("kn");
            }
        } else if (piece instanceof Rook) {
            if (piece.getColor() == 1) {
                return ("R");
            } else {
                return ("r");
            }
        } else if (piece instanceof Chancellor) {
            if (piece.getColor() == 1) {
                return ("CH");
            } else {
                return ("ch");
            }
        } else if (piece instanceof Archbishop) {
            if (piece.getColor() == 1) {
                return ("AB");
            } else {
                return ("ab");
            }
        } else if (piece instanceof Amazon) {
            if (piece.getColor() == 1) {
                return ("A");
            } else {
                return ("a");
            }
        } else if (piece instanceof Boat) {
            if (piece.getColor() == 1) {
                return ("SH");
            } else {
                return ("sh");
            }
        } else if (piece instanceof Bull) {
            if (piece.getColor() == 1) {
                return ("BL");
            } else {
                return ("bl");
            }
        } else if (piece instanceof Camel) {
            if (piece.getColor() == 1) {
                return ("C");
            } else {
                return ("c");
            }
        } else if (piece instanceof Elephant) {
            if (piece.getColor() == 1) {
                return ("E");
            } else {
                return ("e");
            }
        } else if (piece instanceof Frog) {
            if (piece.getColor() == 1) {
                return ("F");
            } else {
                return ("f");
            }
        } else if (piece instanceof General) {
            if (piece.getColor() == 1) {
                return ("G");
            } else {
                return ("g");
            }
        } else if (piece instanceof Gryphon) {
            if (piece.getColor() == 1) {
                return ("GR");
            } else {
                return ("gr");
            }
        } else if (piece instanceof Lion) {
            if (piece.getColor() == 1) {
                return ("L");
            } else {
                return ("l");
            }
        } else if (piece instanceof Viking) {
            if (piece.getColor() == 1) {
                return ("V");
            } else {
                return ("v");
            }
        } else if (piece instanceof Bow) {
            if (piece.getColor() == 1) {
                return ("BW");
            } else {
                return ("bw");
            }
        } else if (piece instanceof Spearman) {
            if (piece.getColor() == 1) {
                return ("S");
            } else {
                return ("s");
            }
        } else if (piece instanceof Pegasus) {
            if (piece.getColor() == 1) {
                return ("P");
            } else {
                return ("p");
            }
        } else if (piece instanceof Immortal) {
            if (piece.getColor() == 1) {
                return ("I");
            } else {
                return ("i");
            }
        } else if (piece instanceof Mage) {
            if (piece.getColor() == 1) {
                return ("M");
            } else {
                return ("m");
            }
        } else if (piece instanceof Archmage) {
            if (piece.getColor() == 1) {
                return ("AM");
            } else {
                return ("Am");
            }
        } else if (piece instanceof Assassin) {
            if (piece.getColor() == 1) {
                return ("AS");
            } else {
                return ("as");
            }
        } else if (piece instanceof Buffalo) {
            if (piece.getColor() == 1) {
                return ("BF");
            } else {
                return ("bf");
            }
        } else if (piece instanceof Spider) {
            if (piece.getColor() == 1) {
                return ("SP");
            } else {
                return ("SP");
            }
        } else if (piece instanceof Berserker) {
            if (piece.getColor() == 1) {
                return ("BZ");
            } else {
                return ("bz");
            }
        } else if (piece instanceof Greatwyrm) {
            if (piece.getColor() == 1) {
                return ("GW");
            } else {
                return ("gw");
            }
        } else if (piece instanceof Quetzacoatl) {
            if (piece.getColor() == 1) {
                return ("QZ");
            } else {
                return ("qz");
            }
        } else if (piece instanceof Wolf) {
            if (piece.getColor() == 1) {
                return ("W");
            } else {
                return ("w");
            }
        } else if (piece instanceof Empress) {
            if (piece.getColor() == 1) {
                return ("EM");
            } else {
                return ("em");
            }
        } else if (piece instanceof Tyrant) {
            if (piece.getColor() == 1) {
                return ("T");
            } else {
                return ("t");
            }
        } else if (piece instanceof Keegan) {
            if (piece.getColor() == 1) {
                return ("KGN");
            } else {
                return ("KGN");
            }
        } else if (piece instanceof Pikeman) {
            if (piece.getColor() == 1) {
                return ("!K");
            } else {
                return ("!k");
            }
        } else if (piece instanceof Checker) {
            if (piece.getColor() == 1) {
                return ("!C");
            } else {
                return ("!c");
            }
        } else if (piece instanceof Wall) {
            if (piece.getColor() == 1) {
                return ("!W");
            } else {
                return ("!w");
            }
        } else if (piece instanceof Prince) {
            if (piece.getColor() == 1) {
                return ("!R");
            } else {
                return ("!r");
            }
        } else if (piece instanceof Soldier) {
            if (piece.getColor() == 1) {
                return ("!S");
            } else {
                return ("!s");
            }
        } else if (piece instanceof Pawn) {
            if (piece.getColor() == 1) {
                return ("!P");
            } else {
                return ("!p");
            }
        }



        return null;
    }
}

