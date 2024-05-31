package Zerzuran_Chess.src.Game;

import javax.swing.*;
import java.awt.*;

public class Skatterer {
    public static ImageIcon rimage(int color, int size) {
        String a = "Zerzuran_Chess/src/resources/bAmazon.png";
        if (color == 0) {
            switch ((int) (Math.random() * 31)) {

                case 0:
                    a = "Zerzuran_Chess/src/resources/bAmazon.png";
                    break;
                case 1:
                    a = "Zerzuran_Chess/src/resources/bArchbishop.png";
                    break;
                case 2:
                    a = "Zerzuran_Chess/src/resources/bArchmage.png";
                    break;
                case 3:
                    a = "Zerzuran_Chess/src/resources/bBishop.png";
                    break;
                case 4:
                    a = "Zerzuran_Chess/src/resources/bAssassin.png";
                    break;
                case 5:
                    a = "Zerzuran_Chess/src/resources/bBoat.png";
                    break;
                case 6:
                    a = "Zerzuran_Chess/src/resources/bBow.png";
                    break;
                case 7:
                    a = "Zerzuran_Chess/src/resources/bBuffalo.png";
                    break;
                case 8:
                    a = "Zerzuran_Chess/src/resources/bBull.png";
                    break;
                case 9:
                    a = "Zerzuran_Chess/src/resources/bCamel.png";
                    break;
                case 10:
                    a = "Zerzuran_Chess/src/resources/bChancellor.png";
                    break;
                case 11:
                    a = "Zerzuran_Chess/src/resources/bChecker.png";
                    break;
                case 12:
                    a = "Zerzuran_Chess/src/resources/bElephant.png";
                    break;
                case 13:
                    a = "Zerzuran_Chess/src/resources/bEmpress.png";
                    break;
                case 14:
                    a = "Zerzuran_Chess/src/resources/bFrog.png";
                    break;
                case 15:
                    a = "Zerzuran_Chess/src/resources/bGeneral.png";
                    break;
                case 16:
                    a = "Zerzuran_Chess/src/resources/bGreatwyrm.png";
                    break;
                case 17:
                    a = "Zerzuran_Chess/src/resources/bGryphon.png";
                    break;
                case 18:
                    a = "Zerzuran_Chess/src/resources/bKing.png";
                    break;
                case 19:
                    a = "Zerzuran_Chess/src/resources/bKnight.png";
                    break;
                case 20:
                    a = "Zerzuran_Chess/src/resources/bLion.png";
                    break;
                case 21:
                    a = "Zerzuran_Chess/src/resources/bPawn.png";
                    break;
                case 22:
                    a = "Zerzuran_Chess/src/resources/bPikeman.png";
                    break;
                case 23:
                    a = "Zerzuran_Chess/src/resources/bPrince.png";
                    break;
                case 24:
                    a = "Zerzuran_Chess/src/resources/bPsus.png";
                    break;
                case 25:
                    a = "Zerzuran_Chess/src/resources/bQueen.png";
                    break;
                case 26:
                    a = "Zerzuran_Chess/src/resources/bQuetz.png";
                    break;
                case 27:
                    a = "Zerzuran_Chess/src/resources/bRook.png";
                    break;
                case 28:
                    a = "Zerzuran_Chess/src/resources/bSoldier.png";
                    break;
                case 29:
                    a = "Zerzuran_Chess/src/resources/bSpider.png";
                    break;
                case 30:
                    a = "Zerzuran_Chess/src/resources/bTyrant.png";
                    break;
                default:
                    a = "Zerzuran_Chess/src/resources/bUnknown.png";
                    break;

            }
        } else{
            switch ((int) (Math.random() * 31)) {

                case 0:
                    a = "Zerzuran_Chess/src/resources/wAmazon.png";
                    break;
                case 1:
                    a = "Zerzuran_Chess/src/resources/wArchbishop.png";
                    break;
                case 2:
                    a = "Zerzuran_Chess/src/resources/wArchmage.png";
                    break;
                case 3:
                    a = "Zerzuran_Chess/src/resources/wBishop.png";
                    break;
                case 4:
                    a = "Zerzuran_Chess/src/resources/wAssassin.png";
                    break;
                case 5:
                    a = "Zerzuran_Chess/src/resources/wBoat.png";
                    break;
                case 6:
                    a = "Zerzuran_Chess/src/resources/wBow.png";
                    break;
                case 7:
                    a = "Zerzuran_Chess/src/resources/wBuffalo.png";
                    break;
                case 8:
                    a = "Zerzuran_Chess/src/resources/wBull.png";
                    break;
                case 9:
                    a = "Zerzuran_Chess/src/resources/wCamel.png";
                    break;
                case 10:
                    a = "Zerzuran_Chess/src/resources/wChancellor.png";
                    break;
                case 11:
                    a = "Zerzuran_Chess/src/resources/wChecker.png";
                    break;
                case 12:
                    a = "Zerzuran_Chess/src/resources/wElephant.png";
                    break;
                case 13:
                    a = "Zerzuran_Chess/src/resources/wEmpress.png";
                    break;
                case 14:
                    a = "Zerzuran_Chess/src/resources/wFrog.png";
                    break;
                case 15:
                    a = "Zerzuran_Chess/src/resources/wGeneral.png";
                    break;
                case 16:
                    a = "Zerzuran_Chess/src/resources/wGreatwyrm.png";
                    break;
                case 17:
                    a = "Zerzuran_Chess/src/resources/wGryphon.png";
                    break;
                case 18:
                    a = "Zerzuran_Chess/src/resources/wKing.png";
                    break;
                case 19:
                    a = "Zerzuran_Chess/src/resources/wKnight.png";
                    break;
                case 20:
                    a = "Zerzuran_Chess/src/resources/wLion.png";
                    break;
                case 21:
                    a = "Zerzuran_Chess/src/resources/wPawn.png";
                    break;
                case 22:
                    a = "Zerzuran_Chess/src/resources/wPikeman.png";
                    break;
                case 23:
                    a = "Zerzuran_Chess/src/resources/wPrince.png";
                    break;
                case 24:
                    a = "Zerzuran_Chess/src/resources/wPsus.png";
                    break;
                case 25:
                    a = "Zerzuran_Chess/src/resources/wQueen.png";
                    break;
                case 26:
                    a = "Zerzuran_Chess/src/resources/wQuetz.png";
                    break;
                case 27:
                    a = "Zerzuran_Chess/src/resources/wRook.png";
                    break;
                case 28:
                    a = "Zerzuran_Chess/src/resources/wSoldier.png";
                    break;
                case 29:
                    a = "Zerzuran_Chess/src/resources/wSpider.png";
                    break;
                case 30:
                    a = "Zerzuran_Chess/src/resources/wTyrant.png";
                    break;
                default:
                    a = "Zerzuran_Chess/src/resources/wUnknown.png";
                    break;
            }
        }
        return new ImageIcon(new ImageIcon(a).getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
    }
}
