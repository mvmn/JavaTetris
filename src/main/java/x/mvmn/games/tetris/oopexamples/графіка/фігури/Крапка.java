package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;

import java.awt.Color;

public class Крапка extends ГеомертичнаФігура {

    private final Координати координати;

    public Крапка(Координати координати) {
        this.координати = координати;
    }

    public Крапка(Координати координати, Color колір) {
        this(координати);
        setКолір(колір);
    }

    @Override
    public void намалювати(Графіка графіка) {
        графіка.намалюватиПіксель(координати.getX(), координати.getY(), this.getКолір());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + координати + "), колір " + колір.getRed() + "/" + колір.getGreen() + "/" + колір.getBlue();
    }
}
