package Zerzuran_Chess.src.Game;

import Zerzuran_Chess.src.pieces.*;
import Zerzuran_Chess.src.pieces.Assassins.Action_Man;
import Zerzuran_Chess.src.pieces.Assassins.Assassin;
import pieces.Assassins.Bladedancer;
import Zerzuran_Chess.src.pieces.pawns.*;
import Zerzuran_Chess.src.pieces.royals.*;

public class Cloning {
    public static Piece Common(int color){
        double ran = (Math.random() * (5 +  1.6 + 2.2 + 0.334 + 0.334 + 0.3 + 0.25 + 0.2 + 0.15 + 0.1));
        if (ran < 1){
            return new Rook(color);
        } ran -= 1;
        if (ran < 1){
            return new Bishop(color);
        } ran -= 1;
        if (ran < 1){
            return new Elephant(color);
        } ran -= 1;
        if (ran < 1){
            return new Frog(color);
        } ran -= 1;
        if (ran < 1){
            return new Knight(color);
        } ran -= 1;
        if (ran < 0.8){
            return new Bull(color);
        } ran -= 0.8;
        if (ran < 0.8){
            return new Camel(color);
        } ran -= 0.8;
        if (ran < 0.8){
            return new Crusader(color);
        } ran -= 0.8;
        if (ran < 0.7){
            return new Falcon(color);
        } ran -= 0.5;
        if (ran < 0.5){
            return new Viking(color);
        } ran -= 0.5;
        if (ran < 0.5){
            return new Boat(color);
        } ran -= 0.5;
        if (ran < 0.5){
            return new Bow(color);
        } ran -= 0.5;
        if (ran < 0.334){
            return new Spearman(color);
        } ran -= 0.334;
        if (ran < 0.334){
            return new AugmentedKnight(color);
        } ran -= 0.334;
        if (ran < 0.3){
            return new Pegasus(color);
        } ran -= 0.3;
        if (ran < 0.25){
            return new Immortal(color);
        } ran -= 0.25;
        if (ran < 0.2){
            return new Mage(color);
        } ran -= 0.2;
        if (ran < 0.05){
            return new Assassin(color);
        } ran -= 0.05;
        if (ran < 0.05){
            return new Bladedancer(color);
        } ran -= 0.05;
        if (ran < 0.05){
            return new Action_Man(color);
        } ran -= 0.05;
        if (ran < 0.1){
            return new Buffalo(color);
        }
        return null;
    }
    public static Piece Pawn(int color){
        return new Pawn(color);
    }
    public static Piece Advanced(int color){
        double ran = (Math.random() * (5 + 0.8 + 0.8 + 0.5 + 0.4 + 0.25 + 0.2 + 0.2 + 0.2 + 0.15 + 0.15 + 0.1 + 0.1 + 0.06 + 0.05 + 0.04 + 0.1));
        if (ran < 1){
            return new Archbishop(color);
        } ran -= 1;
        if (ran < 1){
            return new Chancellor(color);
        } ran -= 1;
        if (ran < 1){
            return new Spider(color);
        } ran -= 1;
        if (ran < 1){
            return new Gryphon(color);
        } ran -= 1;
        if (ran < 1){
            return new Manticore(color);
        } ran -= 1;
        if (ran < 0.8){
            return new Berserker(color);
        } ran -= 0.8;
        if (ran < 0.8){
            return new Queen(color);
        } ran -= 0.8;
        if (ran < 0.5){
            return new Buffalo(color);
        } ran -= 0.5;
        if (ran < 0.4){
            return new Pegasus(color);
        } ran -= 0.4;
        if (ran < 0.25){
            return new Lion(color);
        } ran -= 0.25;
        if (ran < 0.2){
            return new Amazon(color);
        } ran -= 0.2;
        if (ran < 0.2){
            return new Greatwyrm(color);
        } ran -= 0.2;
        if (ran < 0.2){
            return new King(color);
        } ran -= 0.2;
        if (ran < 0.15){
            return new Bow(color);
        } ran -= 0.15;
        if (ran < 0.15){
            return new Quetzacoatl(color);
        } ran -= 0.15;
        if (ran < 0.1){
            return new General(color);
        } ran -= 0.1;
        if (ran < 0.1){
            return new Wolf(color);
        } ran -= 0.1;
        if (ran < 0.06){
            return new Empress(color);
        } ran -= 0.06;
        if (ran < 0.05){
            return new Tyrant(color);
        } ran -= 0.05;
        if (ran < 0.04){
            return new Theocrat(color);
        } ran -= 0.04;
        if (ran < 0.1){
            return new Flag(color);
        }
        return null;
    }

    public static Piece Super(int color){
        int ran = (int) (Math.random() * 5);
        if (ran == 0){
            return new Lion(color);
        } else if (ran == 1){
            return new Amazon(color);
        } else if (ran == 2){
            return new Greatwyrm(color);
        } else if (ran == 3){
            return new Quetzacoatl(color);
        }  else if (ran == 4){
            return new Wolf(color);
        }
        return null;
    }

    public static Piece Royal(int color){
        double ran = (Math.random() * (1.2 + 1 + 0.334 + 0.25 + 0.3));
        if (ran < 1.2){
            return new King(color);
        } ran -= 1.2;
        if (ran < 1){
            return new General(color);
        } ran -= 1;
        if (ran < 0.334){
            return new Empress(color);
        } ran -= 0.334;
        if (ran < 0.25){
            return new Tyrant(color);
        } ran -= 0.25;
        if (ran < 0.05){
            return new Keegan(color);
        } ran -= 0.05;
        if (ran < 0.1){
            return new Flag(color);
        } ran -= 0.1;
        if (ran < 0.15){
            return new Theocrat(color);
        }
        return null;
    }
    public static Piece rPawn(int color){
        double ran = (Math.random() * (3 + 1.5 + 1 + 1 + 0.5 + 0.5 + 0.2 + 0.01));
        if (ran < 3){
            return new Pawn(color);
        } ran -= 3;
        if (ran < 1.5){
            return new Soldier(color);
        } ran -= 1.5;
        if (ran < 1){
            return new Pikeman(color);
        } ran -= 1;
        if (ran < 1){
            return new Prince(color);
        } ran -= 1;
        if (ran < 0.5){
            return new BarolinaPawn(color);
        } ran -= 0.5;
        if (ran < 0.5){
            return new Wall(color);
        } ran -= 0.5;
        if (ran < 0.2){
            return new Checker(color);
        }   ran -= 0.2;
        if (ran < 0.01){
            return new Duck(color);
        }
        return null;
    }
    public static Piece Fear(int color){
        int ran = (int) (Math.random() * 46);
        if (ran == 0){
            return new Elephant(color);
        } else if (ran == 1){
            return new Rook(color);
        } else if (ran == 2){
            return new Bishop(color);
        } else if (ran == 3){
            return new Frog(color);
        } else if (ran == 4){
            return new Knight(color);
        } else if (ran == 5){
            return new Camel(color);
        } else if (ran == 6) {
            return new Bull(color);
        } else if (ran == 7) {
            return new Boat(color);
        } else if (ran == 8) {
            return new King(color);
        } else if (ran == 9) {
            return new General(color);
        } else if (ran == 10) {
            return new Tyrant(color);
        } else if (ran == 11) {
            return new Amazon(color);
        } else if (ran == 12) {
            return new Archbishop(color);
        } else if (ran == 13) {
            return new Chancellor(color);
        } else if (ran == 14) {
            return new Lion(color);
        } else if (ran == 15) {
            return new Queen(color);
        } else if (ran == 16) {
            return new Gryphon(color);
        } else if (ran == 17) {
            return new Pegasus(color);
        } else if (ran == 18) {
            return new Manticore(color);
        } else if (ran == 19) {
            return new Greatwyrm(color);
        } else if (ran == 20) {
            return new Assassin(color);
        } else if (ran == 21) {
            return new Spider(color);
        } else if (ran == 22) {
            return new Checker(color);
        } else if (ran == 23){
            return new Buffalo(color);
        } else if (ran == 24){
            return new Mage(color);
        } else if (ran == 25){
            return new Keegan(color);
        } else if (ran == 26){
            return new AugmentedKnight(color);
        } else if (ran == 27){
            return new Bladedancer(color);
        } else if (ran == 28){
            return new Action_Man(color);
        } else if (ran == 29){
            return new Viking(color);
        } else if (ran == 30){
            return new Berserker(color);
        } else if (ran == 31){
            return new Wolf(color);
        } else if (ran == 32){
            return new Spearman(color);
        } else if (ran == 33){
            return new Immortal(color);
        } else if (ran == 34){
            return new Pawn(color);
        } else if (ran == 35){
            return new Pikeman(color);
        } else if (ran == 36){
            return new Prince(color);
        } else if (ran == 37){
            return new Wall(color);
        } else if (ran == 38){
            return new Soldier(color);
        } else if (ran == 39){
            return new Duck(color);
        } else if (ran == 40){
            return new Bow(color);
        } else if (ran == 41){
            return new Quetzacoatl(color);
        } else if (ran == 42){
            return new Flag(color);
        } else if (ran == 43){
            return new Theocrat(color);
        } else if (ran == 44){
            return new Empress(color);
        } else if (ran == 45){
            return new Crusader(color);
        }
        return (null);
    }
}
//