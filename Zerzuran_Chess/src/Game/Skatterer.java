package Zerzuran_Chess.src.Game;

import javax.swing.*;
import java.awt.*;

public class Skatterer {
    public static ImageIcon rimage(int color, int size) {
        String a = "src/resources/bAmazon.png";
        if (color == 0) {
            switch ((int) (Math.random() * 31)) {

                case 0:
                    a = "src/resources/bAmazon.png";
                    break;
                case 1:
                    a = "src/resources/bArchbishop.png";
                    break;
                case 2:
                    a = "src/resources/bArchmage.png";
                    break;
                case 3:
                    a = "src/resources/bBishop.png";
                    break;
                case 4:
                    a = "src/resources/bAssassin.png";
                    break;
                case 5:
                    a = "src/resources/bBoat.png";
                    break;
                case 6:
                    a = "src/resources/bBow.png";
                    break;
                case 7:
                    a = "src/resources/bBuffalo.png";
                    break;
                case 8:
                    a = "src/resources/bBull.png";
                    break;
                case 9:
                    a = "src/resources/bCamel.png";
                    break;
                case 10:
                    a = "src/resources/bChancellor.png";
                    break;
                case 11:
                    a = "src/resources/bChecker.png";
                    break;
                case 12:
                    a = "src/resources/bElephant.png";
                    break;
                case 13:
                    a = "src/resources/bEmpress.png";
                    break;
                case 14:
                    a = "src/resources/bFrog.png";
                    break;
                case 15:
                    a = "src/resources/bGeneral.png";
                    break;
                case 16:
                    a = "src/resources/bGreatwyrm.png";
                    break;
                case 17:
                    a = "src/resources/bGryphon.png";
                    break;
                case 18:
                    a = "src/resources/bKing.png";
                    break;
                case 19:
                    a = "src/resources/bKnight.png";
                    break;
                case 20:
                    a = "src/resources/bLion.png";
                    break;
                case 21:
                    a = "src/resources/bPawn.png";
                    break;
                case 22:
                    a = "src/resources/bPikeman.png";
                    break;
                case 23:
                    a = "src/resources/bPrince.png";
                    break;
                case 24:
                    a = "src/resources/bPsus.png";
                    break;
                case 25:
                    a = "src/resources/bQueen.png";
                    break;
                case 26:
                    a = "src/resources/bQuetz.png";
                    break;
                case 27:
                    a = "src/resources/bRook.png";
                    break;
                case 28:
                    a = "src/resources/bSoldier.png";
                    break;
                case 29:
                    a = "src/resources/bSpider.png";
                    break;
                case 30:
                    a = "src/resources/bTyrant.png";
                    break;
                default:
                    a = "src/resources/bUnknown.png";
                    break;

            }
        } else{
            switch ((int) (Math.random() * 31)) {

                case 0:
                    a = "src/resources/wAmazon.png";
                    break;
                case 1:
                    a = "src/resources/wArchbishop.png";
                    break;
                case 2:
                    a = "src/resources/wArchmage.png";
                    break;
                case 3:
                    a = "src/resources/wBishop.png";
                    break;
                case 4:
                    a = "src/resources/wAssassin.png";
                    break;
                case 5:
                    a = "src/resources/wBoat.png";
                    break;
                case 6:
                    a = "src/resources/wBow.png";
                    break;
                case 7:
                    a = "src/resources/wBuffalo.png";
                    break;
                case 8:
                    a = "src/resources/wBull.png";
                    break;
                case 9:
                    a = "src/resources/wCamel.png";
                    break;
                case 10:
                    a = "src/resources/wChancellor.png";
                    break;
                case 11:
                    a = "src/resources/wChecker.png";
                    break;
                case 12:
                    a = "src/resources/wElephant.png";
                    break;
                case 13:
                    a = "src/resources/wEmpress.png";
                    break;
                case 14:
                    a = "src/resources/wFrog.png";
                    break;
                case 15:
                    a = "src/resources/wGeneral.png";
                    break;
                case 16:
                    a = "src/resources/wGreatwyrm.png";
                    break;
                case 17:
                    a = "src/resources/wGryphon.png";
                    break;
                case 18:
                    a = "src/resources/wKing.png";
                    break;
                case 19:
                    a = "src/resources/wKnight.png";
                    break;
                case 20:
                    a = "src/resources/wLion.png";
                    break;
                case 21:
                    a = "src/resources/wPawn.png";
                    break;
                case 22:
                    a = "src/resources/wPikeman.png";
                    break;
                case 23:
                    a = "src/resources/wPrince.png";
                    break;
                case 24:
                    a = "src/resources/wPsus.png";
                    break;
                case 25:
                    a = "src/resources/wQueen.png";
                    break;
                case 26:
                    a = "src/resources/wQuetz.png";
                    break;
                case 27:
                    a = "src/resources/wRook.png";
                    break;
                case 28:
                    a = "src/resources/wSoldier.png";
                    break;
                case 29:
                    a = "src/resources/wSpider.png";
                    break;
                case 30:
                    a = "src/resources/wTyrant.png";
                    break;
                default:
                    a = "src/resources/wUnknown.png";
                    break;
            }
        }
        return new ImageIcon(new ImageIcon(a).getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
    }
}
