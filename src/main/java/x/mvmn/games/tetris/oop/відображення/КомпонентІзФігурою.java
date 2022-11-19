package x.mvmn.games.tetris.oop.відображення;

import x.mvmn.games.tetris.oop.модель.фігури.Фігура;

import java.awt.Color;
import java.awt.Graphics;

public abstract class КомпонентІзФігурою implements КомпонентВідображення {

    protected void намалюватиФігуру(Graphics графіка, Фігура фігура, int fx, int fy) {
        for (int y = 0; y < фігура.висота(); y++) {
            for (int x = 0; x < фігура.ширина(); x++) {
                if (фігура.заповненість(x, y)) {
                    замалюватиКвадратик(графіка, фігура.колір(), 40 * x + fx,
                            40 * y + fy, true);
                }
            }
        }
    }

    protected void замалюватиКвадратик(Graphics графіка, Color колір, int x, int y, boolean анімація) {
        if (анімація) {
            int animationValue = Math.abs(32 - (int) (System.currentTimeMillis() / 10 % 64));
            графіка.setColor(колір);
            графіка.fillRoundRect(x + 4, y + 4, 36, 36, animationValue / 2, animationValue / 2);
            float[] hsb = Color.RGBtoHSB(колір.getRed(), колір.getGreen(), колір.getBlue(), null);
            графіка.setColor(Color.getHSBColor(0.5f - hsb[0], 0 + animationValue / 64.f, 1f));
            графіка.fillRoundRect(x + 8, y + 8, 28, 28, animationValue / 2, animationValue / 2);
            графіка.setColor(колір);
            графіка.fillOval(x + 14 + animationValue / 8, y + 14 + animationValue / 8, 16 - animationValue / 4, 16 - animationValue / 4);
        } else {
            графіка.setColor(колір);
            графіка.fillRoundRect(x + 4, y + 4, 36, 36, 8, 8);
            графіка.setColor(Color.WHITE);
            графіка.fillRoundRect(x + 8, y + 8, 28, 28, 16, 16);
            графіка.setColor(колір);
            графіка.fillOval(x + 14, y + 14, 16, 16);
        }
    }
}
