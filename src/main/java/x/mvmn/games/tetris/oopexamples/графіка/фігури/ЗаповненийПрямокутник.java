package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;

import java.awt.Color;

public class ЗаповненийПрямокутник extends Прямокутник {

    public ЗаповненийПрямокутник(Координати ліваВерхняТочка, Координати праваНижняТочка) {
        super(ліваВерхняТочка, праваНижняТочка);
    }

    public ЗаповненийПрямокутник(Координати ліваВерхняТочка, Координати праваНижняТочка, Color колір) {
        super(ліваВерхняТочка, праваНижняТочка, колір);
    }

    @Override
    public void намалювати(Графіка графіка) {
        for (int x = Math.min(ліваВерхняТочка.getX(), праваНижняТочка.getX()); x <= Math.max(ліваВерхняТочка.getX(), праваНижняТочка.getX()); x++) {
            for (int y = Math.min(ліваВерхняТочка.getY(), праваНижняТочка.getY()); y <= Math.max(ліваВерхняТочка.getY(), праваНижняТочка.getY()); y++) {
                графіка.намалюватиПіксель(x, y, колір);
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + ліваВерхняТочка + " - " + праваНижняТочка + "), колір " + колір.getRed() + "/" + колір.getGreen() + "/" + колір.getBlue();
    }
}
