package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;

import java.awt.Color;

public class ЗаповненийПрямокутник extends ГеомертичнаФігура {

    private final Координати ліваВерхняТочка;

    private final Координати праваНижняТочка;

    public ЗаповненийПрямокутник(Координати ліваВерхняТочка, Координати праваНижняТочка) {
        this.ліваВерхняТочка = ліваВерхняТочка;
        this.праваНижняТочка = праваНижняТочка;
    }

    public ЗаповненийПрямокутник(Координати ліваВерхняТочка, Координати праваНижняТочка, Color колір) {
        this(ліваВерхняТочка, праваНижняТочка);
        setКолір(колір);
    }

    @Override
    public void setКолір(Color колір) {
        super.setКолір(колір);
    }

    @Override
    public void намалювати(Графіка графіка) {
        for (int x = ліваВерхняТочка.getX(); x <= праваНижняТочка.getX(); x++) {
            for(int y = ліваВерхняТочка.getY(); y<=праваНижняТочка.getY();y++) {
                графіка.намалюватиПіксель(x, y, колір);
            }
        }
    }
}
