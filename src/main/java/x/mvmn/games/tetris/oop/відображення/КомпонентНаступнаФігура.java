package x.mvmn.games.tetris.oop.відображення;

import x.mvmn.games.tetris.oop.модель.фігури.Фігура;

import java.awt.Color;
import java.awt.Graphics;

public class КомпонентНаступнаФігура extends КомпонентІзФігурою {

    private final int розмір;

    private final int x;
    private final int y;

    private Фігура фігура;

    public КомпонентНаступнаФігура(int x, int y, int розмір) {
        this.розмір = розмір;
        this.x = x;
        this.y = y;
    }

    @Override
    public void промалювати(Graphics графіка) {
        графіка.setColor(Color.BLACK);
        графіка.fillRoundRect(x, y, розмір, розмір, 25, 25);
        if (фігура != null) {
            намалюватиФігуру(графіка, фігура, x - фігура.ширина() * 20 + розмір / 2, y - фігура.висота() * 20 + розмір / 2);
        }
    }

    public Фігура getФігура() {
        return фігура;
    }

    public void setФігура(Фігура фігура) {
        this.фігура = фігура;
    }
}
